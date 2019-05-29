package bgroup.stocktradingsystem.stsserver.controller;

import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * ErrorController用于对filter过滤出的错误访问进行重定向。
 * 包括的错误信息有:
 * <ul>
 *     <li>尚未登陆的访问</li>
 * </ul>
 * <p>
 * 在该部分中的Response类型中Result中的status都为false。
 *
 * @version 0.0.1
 */
@RestController
public class ErrorController {

    /**
     * 未登陆时访问其他url时的重定向。
     *
     * @return 登陆失败的消息
     */
    @RequestMapping(value = "/error/login", method = GET)
    @ResponseBody
    public String loginFailed() {
        return new CustomResponse(new Result(false, "尚未登陆")).toString();
    }
}
