package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.Command;
import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.CommandService;
import bgroup.stocktradingsystem.stsserver.service.StockService;
import bgroup.stocktradingsystem.stsserver.service.TransactionLogService;
import bgroup.stocktradingsystem.stsserver.service.account.FundAccountService;
import bgroup.stocktradingsystem.stsserver.socketutils.SocketCommon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * CommandController用于客户端发送买卖指令的控制。
 * 需要将从客户端发送过来的指令转发给中央处理系统。
 * 包括以下操作：
 * <ul>
 *     <li>上传指令</li>
 *     <li>撤销指令</li>
 * </ul>
 *
 * @version 0.0.1
 */
@RestController
public class CommandController {
    final private CommandService commandService;
    final private FundAccountService fundAccountService;
    final private StockService stockService;
    final private TransactionLogService transactionLogService;

    /* JSON语句转换 */
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    /**
     * 初始化
     *
     * @param commandService 指令服务
     * @param fundAccountService 资金账户服务
     * @param stockService  股票服务
     * @param transactionLogService 交易记录服务
     */
    @Autowired
    public CommandController(CommandService commandService,
                             FundAccountService fundAccountService,
                             StockService stockService,
                             TransactionLogService transactionLogService) {
        this.commandService = commandService;
        this.fundAccountService = fundAccountService;
        this.stockService = stockService;
        this.transactionLogService = transactionLogService;
    }

    /**
     * 上传指令
     *
     * @param data 待上传的指令
     * @return 成功或失败信息
     */
    @RequestMapping(value = "/command/upload", method = POST)
    @ResponseBody
    public String uploadCommand(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Command command = gson.fromJson(data, Command.class);
        if(command.isCommandType()) {
            // 买指令判断钱是否够
            if(fundAccountService.fetchAccount(command.getFundId()).getBalance()
                    > command.getStockCount() * command.getStockPrice()) {
                // 若钱足够，则提前扣掉这部分钱
                fundAccountService.updateBalance(command.getFundId(),
                        command.getStockCount() * command.getStockPrice());
                // 同时增加一则交易记录
                transactionLogService.addLog(new TransactionLog(
                        command.getFundId(),
                        command.getTime(),
                        command.getStockCount() * command.getStockPrice(),
                        "买入股票支出"));
            } else {
                return new CustomResponse(new Result(false, "账户资金不足")).toString();
            }
        } else {
            // 卖指令判断股票是否够
            if(stockService.fetchStockHold(command.getFundId(),command.getStockCode()) > command.getStockCount()) {
                // 如果足够则删除这部分股票
                stockService.updateStockHold(command.getFundId(), command.getStockCode(), command.getStockCount());
            } else {
                // 不够则返回错误信息
                return new CustomResponse(new Result(false, "账户持有股票不足")).toString();
            }
        }
        Thread thread = new SocketCommon(
                command.isCommandType() + "," +
                command.getFundId() + "," +
                command.getStockCode() + "," +
                command.getStockCount() + "," +
                command.getStockPrice() + "," + command.getTime());
        thread.start();
        return new CustomResponse(new Result(true), thread.getState()).toString();
    }

    /**
     * 撤销指令
     *
     * @param data 待撤销的指令
     * @return 成功或失败信息
     */
    @RequestMapping(value = "/command/revoke", method = POST)
    @ResponseBody
    public String revokeCommand(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Command command = gson.fromJson(data, Command.class);
        Thread thread = new SocketCommon(
                command.isCommandType() + "," +
                        command.getFundId() + "," +
                        command.getStockCode() + "," + command.getTime());
        thread.start();
        return new CustomResponse(new Result(true), thread.getState()).toString();
    }

    @RequestMapping(value = "/command/in", method = POST)
    @ResponseBody
    public String fetchCertainInCmd(@RequestBody String data) {
        System.out.println(data);
        data = data.substring(1, data.length()-1).replace("\\", "");
        String stockCode = gson.fromJson(data, String.class);
        System.out.println("stock code: " + stockCode);
        List<Command> cmds = commandService.fetchCertainInCmd("stock_code = '" + stockCode + "'");
        if(cmds != null)
            return new CustomResponse(new Result(true), cmds).toString();
        else
            return new CustomResponse(new Result(false, "获取买指令失败")).toString();
    }

    @RequestMapping(value = "/command/out", method = POST)
    @ResponseBody
    public String fetchCertainOutCmd(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        String stockCode = gson.fromJson(data, String.class);
        System.out.println("stock code: " + stockCode);
        List<Command> cmds = commandService.fetchCertainOutCmd("stock_code = '" + stockCode + "'");
        if(cmds != null)
            return new CustomResponse(new Result(true), cmds).toString();
        else
            return new CustomResponse(new Result(false, "获取卖指令失败")).toString();
    }
}
