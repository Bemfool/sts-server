package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.Command;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.CommandService;
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

    /* JSON语句转换 */
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }


    @RequestMapping(value = "/command/upload", method = POST)
    @ResponseBody
    public String uploadCommand(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Command command = gson.fromJson(data, Command.class);
        String reply = SocketCommon.doSocket(
                command.isCommandType() + "," +
                command.getFundId() + "," +
                command.getStockCode() + "," +
                command.getStockCount() + "," +
                command.getStockPrice() + "," + command.getTime());
        return new CustomResponse(new Result(true), reply).toString();
    }

    @RequestMapping(value = "/command/revoke", method = POST)
    @ResponseBody
    public String revokeCommand(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Command command = gson.fromJson(data, Command.class);
        String reply = SocketCommon.doSocket(
                command.isCommandType() + "," +
                        command.getFundId() + "," +
                        command.getStockCode() + "," + command.getTime());
        return new CustomResponse(new Result(true), reply).toString();

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
