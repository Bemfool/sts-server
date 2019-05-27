package bgroup.stocktradingsystem.stsserver.controller.login;

import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.account.FundAccountService;
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
public class ClientLoginController {
    @Autowired
    FundAccountService fundAccountService;

    private Gson gson = new Gson();

    /**
     * @param data 带有账号和密码的资金账户类型
     * @param request 用于获得Session
     * @return 成功或者失败原因
     */
    @RequestMapping(value = "/client/login", method = POST)
    @ResponseBody
    public String clientLogin(@RequestBody String data, HttpServletRequest request){
        data = data.substring(1, data.length()-1).replace("\\", "");
        FundAccount fundAccount = gson.fromJson(data, FundAccount.class);

        FundAccount localAccount = fundAccountService.fetchAccount(fundAccount.getFundId());
        if(localAccount!=null) {
            if(localAccount.getPassword().equals(fundAccount.getPassword())) {
                HttpSession session = request.getSession();
                if(session.getAttribute("CLIENT_SESSION_ID") != null) {
                    return new CustomResponse(new Result(false, "重复登陆")).toString();
                } else {
                    session.setAttribute("CLIENT_SESSION_ID", fundAccount.getFundId());
                }
                return new CustomResponse(new Result(true, "登陆成功"), localAccount).toString();
            } else {
                return new CustomResponse(new Result(false, "密码不正确")).toString();
            }
        } else {
            return new CustomResponse(new Result(false, "该用户不存在")).toString();
        }
    }

}
