package bgroup.stocktradingsystem.stsserver.controller;


import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.resp.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.resp.Result;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class AdminLoginController {

    @RequestMapping(value = "/admin/login", method = POST)
    @ResponseBody
    public String adminLogin(@RequestBody String data, HttpServletRequest request){
        data = data.substring(1, data.length()-1).replace("\\", "");
        AdminAccount adminAccount = new Gson().fromJson(data, AdminAccount.class);
        if(adminAccount.isExist()) {
            if(adminAccount.isValid()) {
                HttpSession session = request.getSession();
                if(session.getAttribute("ADMIN_SESSION_ID") != null) {
                    return new CustomResponse(new Result(false, "重复登陆")).toString();
                } else {
                    session.setAttribute("ADMIN_SESSION_ID", adminAccount.getId());
                }
                return new CustomResponse(new Result(true, "登陆成功")).toString();
            } else {
                return new CustomResponse(new Result(false, "密码不正确")).toString();
            }
        } else {
            return new CustomResponse(new Result(false, "该用户不存在")).toString();
        }
    }




}