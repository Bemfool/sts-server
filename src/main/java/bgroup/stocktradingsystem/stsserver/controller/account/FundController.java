package bgroup.stocktradingsystem.stsserver.controller.account;

import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;
import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.TransactionLogService;
import bgroup.stocktradingsystem.stsserver.service.account.FundAccountService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * FundController用于资金账户业务的控制。
 * 包括以下操作：
 * <ul>
 *     <li>创建新资金账户</li>
 *     <li>更新资金账户密码</li>
 *     <li>检查账户状态</li>
 *     <li>改变账户状态</li>
 *     <li>指定ID获取资金账户</li>
 *     <li>删除资金账户</li>
 *     <li>完成交易</li>
 * </ul>
 *
 * @version 0.0.1
 */
@RestController
public class FundController {
    /* 需要使用到的服务 */
    final private FundAccountService fundAccountService;
    final private TransactionLogService transactionLogService;

    /* JSON语句处理 */
    private Gson gson = new Gson();

    @Autowired
    public FundController(FundAccountService fundAccountService, TransactionLogService transactionLogService) {
        this.fundAccountService = fundAccountService;
        this.transactionLogService = transactionLogService;
    }

    /* 日志 */
//    private static final Logger logger = LoggerFactory.getLogger(FundController.class);

    /**
     * 创建新的资金账户
     *
     * @param data 待新建的资金账户
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/fund/new", method = POST)
    @ResponseBody
    public String createAccount(@RequestBody String data) {
        System.out.println("创建新用户: " + data);
        /* JSON语句处理 */
        data = data.substring(1, data.length()-1).replace("\\", "");
        FundAccount account;
        try {
            account = gson.fromJson(data, FundAccount.class);
        } catch(JsonSyntaxException | JsonIOException e) {
            /* JSON语句格式异常 */
            return new CustomResponse(new Result(false, "JSON语句出错")).toString();
        }
        int id;
        try {
            /* 创建新的资金账户, 返回新生成的ID */
            id = fundAccountService.createAccount(account);
            return new CustomResponse(new Result(true), id).toString();
        }  catch(DataAccessException e) {
            /* 数据库访问异常 */
            SQLException exception = (SQLException)e.getCause();
//            logger.error("数据库异常");
            return new CustomResponse(new Result(false, "数据库异常: " + exception.toString())).toString();
        }
    }

    /**
     * 修改资金账户密码
     *
     * @param fundId 待修改账户的ID
     * @param newPassword 新密码
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/fund/update/password/{fundId}/{newPassword}", method = POST)
    @ResponseBody
    public String changePassword(@PathVariable String fundId, @PathVariable String newPassword) {
        fundAccountService.changePassword(Integer.valueOf(fundId), newPassword);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * 检查资金账户状态
     *
     * @param fundId 待检查的账户ID
     * @return 资金账户的状态
     */
    @RequestMapping(value = "/fund/check/state/{fundId}", method = GET)
    @ResponseBody
    public String checkState(@PathVariable String fundId) {
        return new CustomResponse(new Result(true),
                fundAccountService.getState(Integer.valueOf(fundId))).toString();
        // TODO 失败判断
    }

    /**
     * 修改资金账户状态
     *
     * @param fundId 待修改的账户ID
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/fund/change/state/{fundId}", method = POST)
    @ResponseBody
    public String changeState(@PathVariable String fundId) {
        fundAccountService.changeState(Integer.valueOf(fundId));
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * 指定ID获取资金账户
     *
     * @param fundId 指定ID
     * @return 资金账户
     */
    @RequestMapping(value = "/fund/{fundId}", method = GET)
    @ResponseBody
    public String fetchAccount(@PathVariable String fundId) {
        return new CustomResponse(new Result(true),
                fundAccountService.fetchAccount(Integer.valueOf(fundId))).toString();
        // TODO 失败判断
    }

    /**
     * 删除资金账户
     *
     * @param fundId 待删除的账号ID
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/fund/delete/{fundId}", method = POST)
    @ResponseBody
    public String deleteAccount(@PathVariable String fundId) {
        fundAccountService.deleteAccount(Integer.valueOf(fundId));
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * 完成交易。
     * 包括以下操作：
     * <ul>
     *     <li>更新余额</li>
     *     <li>增加交易记录</li>
     * </ul>
     *
     * @param data 交易记录
     * @return 成功或失败信息
     */
    @RequestMapping(value = "/fund/transfer/", method = POST)
    @ResponseBody
    public String transfer(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        TransactionLog log = gson.fromJson(data, TransactionLog.class);
        try {
            /* 更新余额 */
            fundAccountService.updateBalance(log.getFundId(), log.getChangeAmount());
            /* 新添交易记录 */
            transactionLogService.addLog(log);
            return new CustomResponse(new Result(true)).toString();
        } catch(DataAccessException e) {
            /* 数据库异常 */
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception.toString());
            return new CustomResponse(new Result(false, "数据库异常: " + exception.toString())).toString();
        }
    }

    @RequestMapping(value = "/fund/settle_interest", method = POST)
    @ResponseBody
    public String settleInterest() {
        boolean result;
        try {
            result = fundAccountService.settleInterest();
        } catch(DataAccessException e) {
            /* 数据库异常 */
            SQLException exception = (SQLException)e.getCause();
            System.out.println(exception.toString());
            return new CustomResponse(new Result(false, "数据库异常: " + exception.toString())).toString();
        }
        if(result)
            return new CustomResponse(new Result(true)).toString();
        else
            return new CustomResponse(new Result(false, "不存在该账户")).toString();
    }

    @RequestMapping(value = "/fund/transaction_log/{fundId}", method = GET)
    @ResponseBody
    public String fetchTransactionLog(@PathVariable String fundId) {
        return new CustomResponse(new Result(true),
                transactionLogService.fetchLog(Integer.valueOf(fundId))).toString();
    }


}
