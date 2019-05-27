package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ErrorController {

    /**
     * @return 登陆失败的消息
     */
    @RequestMapping(value = "/error/login", method = GET)
    @ResponseBody
    public String loginFailed() {
        return new CustomResponse(new Result(false, "尚未登陆")).toString();
    }
}
