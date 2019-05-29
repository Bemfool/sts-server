package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.Command;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.CommandService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
