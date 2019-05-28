package bgroup.stocktradingsystem.stsserver.controller.account;

import bgroup.stocktradingsystem.stsserver.domain.TransactionLog;
import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.TransactionLogService;
import bgroup.stocktradingsystem.stsserver.service.account.FundAccountService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class FundController {
    @Autowired
    FundAccountService fundAccountService;
    @Autowired
    TransactionLogService transactionLogService;

    private Gson gson = new Gson();

    @RequestMapping(value = "/fund/new/", method = GET)
    @ResponseBody
    public String createAccount(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        FundAccount account = gson.fromJson(data, FundAccount.class);
        return new CustomResponse(new Result(true),
                fundAccountService.createAccount(account)).toString();
        // TODO 失败判断
    }

    @RequestMapping(value = "/fund/update/password/{fundId}/{newPassword}", method = POST)
    @ResponseBody
    public String changePassword(@PathVariable String fundId, @PathVariable String newPassword) {
        fundAccountService.changePassword(Integer.valueOf(fundId), newPassword);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    @RequestMapping(value = "/fund/check/state/{fundId}", method = GET)
    @ResponseBody
    public String checkState(@PathVariable String fundId) {
        return new CustomResponse(new Result(true),
                fundAccountService.getState(Integer.valueOf(fundId))).toString();
        // TODO 失败判断
    }

    @RequestMapping(value = "/fund/change/state/{fundId}", method = POST)
    @ResponseBody
    public String changeState(@PathVariable String fundId) {
        fundAccountService.changeState(Integer.valueOf(fundId));
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    @RequestMapping(value = "/fund/{fundId}", method = GET)
    @ResponseBody
    public String fetchAccount(@PathVariable String fundId) {
        return new CustomResponse(new Result(true),
                fundAccountService.fetchAccount(Integer.valueOf(fundId))).toString();
        // TODO 失败判断
    }

    @RequestMapping(value = "/fund/delete/{fundId}", method = POST)
    @ResponseBody
    public String deleteAccount(@PathVariable String fundId) {
        fundAccountService.deleteAccount(Integer.valueOf(fundId));
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    @RequestMapping(value = "/fund/transfer/", method = POST)
    @ResponseBody
    public String transfer(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        TransactionLog log = gson.fromJson(data, TransactionLog.class);
        fundAccountService.updateBalance(log.getFundId(), log.getChangeAmount());
        transactionLogService.addLog(log);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

}
