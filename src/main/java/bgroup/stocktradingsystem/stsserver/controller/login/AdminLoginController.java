package bgroup.stocktradingsystem.stsserver.controller.login;



import bgroup.stocktradingsystem.stsserver.domain.account.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.account.AdminAccountService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * AdminLoginController是用于管理员登陆相关的控制类。
 * 包括以下操作：
 * <ul>
 *     <li>登陆操作</li>
 *     <li>修改密码</li>
 *     <li>注销操作</li>
 * </ul>
 *
 * @Version 0.0.1
 */
@RestController
public class AdminLoginController {
    @Autowired
    AdminAccountService adminAccountService;

    private Gson gson = new Gson();

    /**
     * 管理员登陆
     *
     * @param data 带有账号和密码的管理员账户类型
     * @param request 用于获得Session
     * @return 成功或者失败原因
     */
    @RequestMapping(value = "/admin/login", method = POST)
    @ResponseBody
    public String adminLogin(@RequestBody String data, HttpServletRequest request){
        data = data.substring(1, data.length()-1).replace("\\", "");
        AdminAccount adminAccount = gson.fromJson(data, AdminAccount.class);
        AdminAccount localAccount = adminAccountService.fetchAccount(adminAccount.getId());
        if(localAccount!=null)
            if(localAccount.getPassword().equals(adminAccount.getPassword())) {
                HttpSession session = request.getSession();
                if(session.getAttribute("ADMIN_SESSION_ID") != null)
                    return new CustomResponse(new Result(false, "重复登陆")).toString();
                else
                    session.setAttribute("ADMIN_SESSION_ID", adminAccount.getId());
                return new CustomResponse(new Result(true, "登陆成功"), localAccount).toString();
            } else
                return new CustomResponse(new Result(false, "密码不正确")).toString();
        else
            return new CustomResponse(new Result(false, "该用户不存在")).toString();
    }

    /**
     * 更新密码
     *
     * @param newPassword 新的密码
     * @param request 用于获得Session, 从而获得账户id
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/admin/update/password/{newPassword}", method = POST)
    @ResponseBody
    public String adminChangePassword(HttpServletRequest request, @PathVariable String newPassword) {
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("ADMIN_SESSION_ID");
        if(adminAccountService.changePassword(id, newPassword)) {
            return new CustomResponse(new Result(true)).toString();
        } else {
            return new CustomResponse(new Result(false, "账号不存在")).toString();
        }
    }

    /**
     * 注销管理员账户
     *
     * @param request 用于清空Session中绑定的ID
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/admin/logout", method = POST)
    @ResponseBody
    public String adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("ADMIN_SESSION_ID", null);
        return new CustomResponse(new Result(true)).toString();
    }


}