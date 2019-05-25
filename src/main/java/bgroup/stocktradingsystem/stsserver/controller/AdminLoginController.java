package bgroup.stocktradingsystem.stsserver.controller;


import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.AdminAccountService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class AdminLoginController {
    @Autowired
    AdminAccountService adminAccountService;

    private Gson gson = new Gson();

    @RequestMapping(value = "/admin/login", method = POST)
    @ResponseBody
    public String adminLogin(@RequestBody String data, HttpServletRequest request){
        data = data.substring(1, data.length()-1).replace("\\", "");
        AdminAccount adminAccount = gson.fromJson(data, AdminAccount.class);

        AdminAccount localAccount = adminAccountService.fetchAccount(adminAccount.getId());
        if(localAccount!=null) {
            if(localAccount.getPassword().equals(adminAccount.getPassword())) {
                HttpSession session = request.getSession();
                if(session.getAttribute("ADMIN_SESSION_ID") != null) {
                    return new CustomResponse(new Result(false, "重复登陆")).toString();
                } else {
                    session.setAttribute("ADMIN_SESSION_ID", adminAccount.getId());
                }
                return new CustomResponse(new Result(true, "登陆成功"), localAccount).toString();
            } else {
                return new CustomResponse(new Result(false, "密码不正确")).toString();
            }
        } else {
            return new CustomResponse(new Result(false, "该用户不存在")).toString();
        }
    }

    @RequestMapping(value = "/admin/update/password", method = POST)
    @ResponseBody
    public String adminChangePassword(@RequestBody String data, HttpServletRequest request) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        String newPassword = gson.fromJson(data, String.class);
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("ADMIN_SESSION_ID");
        if(adminAccountService.changePassword(id, newPassword)) {
            return new CustomResponse(new Result(true)).toString();
        } else {
            return new CustomResponse(new Result(false, "账号不存在")).toString();
        }

    }

}