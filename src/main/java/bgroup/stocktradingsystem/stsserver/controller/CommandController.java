package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.Command;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    Gson gson = new Gson();

    @RequestMapping(value = "/command/upload", method = POST)
    @ResponseBody
    public String uploadCommand(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Command command = gson.fromJson(data, Command.class);
        // TODO 传输给中央交易系统
        return new CustomResponse(new Result(true)).toString();
    }

    @RequestMapping(value = "/command/revoke", method = POST)
    @ResponseBody
    public String revokeCommand(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        Command command = gson.fromJson(data, Command.class);
        // TODO 传输给中央交易系统
        return new CustomResponse(new Result(true)).toString();
    }
}
