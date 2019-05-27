package bgroup.stocktradingsystem.stsserver.controller.account;

import bgroup.stocktradingsystem.stsserver.domain.account.CorporateAccount;
import bgroup.stocktradingsystem.stsserver.domain.account.PersonalAccount;
import bgroup.stocktradingsystem.stsserver.domain.response.CustomResponse;
import bgroup.stocktradingsystem.stsserver.domain.response.Result;
import bgroup.stocktradingsystem.stsserver.service.account.FundAccountService;
import bgroup.stocktradingsystem.stsserver.service.account.SecuritiesAccountService;
import bgroup.stocktradingsystem.stsserver.service.relation.SFRelationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class SecuritiesController {
    @Autowired
    SecuritiesAccountService securitiesAccountService;
    @Autowired
    FundAccountService fundAccountService;
    @Autowired
    SFRelationService sfRelationService;

    private Gson gson = new Gson();

    /* *********************** Personal Account ****************************** */

    /**
     * @param data 新的个人账户信息
     * @return 成功或失败原因 // TODO 返回新建的ID
     */
    @RequestMapping(value = "/securities/new/personal", method = POST)
    @ResponseBody
    public String createPersonalAccount(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        PersonalAccount account = gson.fromJson(data, PersonalAccount.class);
        securitiesAccountService.createPersonalAccount(account);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * @param idNo 指定身份证号
     * @return 返回指定身份证号的个人账户
     */
    @RequestMapping(value = "/securities/personal/{idNo}", method = GET)
    @ResponseBody
    public String fetchPersonalAccount(@PathVariable String idNo) {
        return new CustomResponse(new Result(true),
                securitiesAccountService.fetchPersonalAccountByIN(idNo))
                .toString();
        // TODO 失败判断
    }

    /**
     * @param oldSecuritiesId 待替换的证券账户号
     * @param newSecuritiesId 新的证券账户号
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/securities/alter/personal/" +
            "{oldSecuritiesId}/{newSecuritiesId}", method = POST)
    @ResponseBody
    public String alterPersonalAccount(@PathVariable String oldSecuritiesId,
                                       @PathVariable String newSecuritiesId) {
        PersonalAccount account = securitiesAccountService
                .fetchPersonalAccountById(Integer.valueOf(oldSecuritiesId));
        securitiesAccountService.deletePersonalAccountById(Integer.valueOf(oldSecuritiesId));
        account.setSecuritiesId(Integer.valueOf(newSecuritiesId));
        securitiesAccountService.createPersonalAccount(account);
        sfRelationService.alterSecuritiesId(Integer.valueOf(oldSecuritiesId),
                Integer.valueOf(newSecuritiesId));
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * @param idNo 个人账户身份证
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/securities/delete/personal/" +
            "{idNo}", method = POST)
    @ResponseBody
    public String deletePersonalAccount(@PathVariable String idNo) {
        securitiesAccountService.deletePersonalAccountByIN(idNo);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * @param idNo 个人账户身份证
     * @param newState 新状态
     * @return 成功或失败信息
     */
    @RequestMapping(value = "/securities/update/personal/" +
            "state/{idNo}/{newState}", method = POST)
    @ResponseBody
    public String updatePersonalState(@PathVariable String idNo, @PathVariable String newState) {
        PersonalAccount account = securitiesAccountService.fetchPersonalAccountByIN(idNo);
        account.setState(Integer.valueOf(newState));
        securitiesAccountService.updatePersonalAccount(account);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /* ********************* Corporate Account ****************************** */

    /**
     * @param data 新的法人账户信息
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/securities/new/corporate", method = POST)
    @ResponseBody
    public String createCorporateAccount(@RequestBody String data) {
        data = data.substring(1, data.length()-1).replace("\\", "");
        CorporateAccount account = gson.fromJson(data, CorporateAccount.class);
        securitiesAccountService.createCorporateAccount(account);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * @param registerNo 指定注册号
     * @return 返回指定注册号的法人账户
     */
    @RequestMapping(value = "/securities/corporate/{registerNo}", method = GET)
    @ResponseBody
    public String fetchCorporateAccount(@PathVariable String registerNo) {
        return new CustomResponse(new Result(true),
                securitiesAccountService.fetchCorporateAccountByRN(registerNo))
                .toString();
        // TODO 失败判断
    }

    /**
     * @param oldSecuritiesId 待替换的证券账户号
     * @param newSecuritiesId 新的证券账户号
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/securities/alter/corporate/" +
            "{oldSecuritiesId}/{newSecuritiesId}", method = POST)
    @ResponseBody
    public String alterCorporateAccount(@PathVariable String oldSecuritiesId,
                                       @PathVariable String newSecuritiesId) {
        CorporateAccount account = securitiesAccountService
                .fetchCorporateAccountById(Integer.valueOf(oldSecuritiesId));
        securitiesAccountService.deleteCorporateAccountById(Integer.valueOf(oldSecuritiesId));
        account.setSecuritiesId(Integer.valueOf(newSecuritiesId));
        securitiesAccountService.createCorporateAccount(account);
        sfRelationService.alterSecuritiesId(Integer.valueOf(oldSecuritiesId),
                Integer.valueOf(newSecuritiesId));
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * @param registerNo 法人账号注册号
     * @return 成功或失败原因
     */
    @RequestMapping(value = "/securities/delete/corporate/" +
            "{registerNo}", method = POST)
    @ResponseBody
    public String deleteCorporateAccount(@PathVariable String registerNo) {
        securitiesAccountService.deleteCorporateAccountByRN(registerNo);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /**
     * @param newState 新状态
     * @param registerNo 法人账户注册号
     * @return 成功或失败信息
     */
    @RequestMapping(value = "/securities/update/corporate/" +
            "state/{registerNo}/{newState}", method = POST)
    @ResponseBody
    public String updateCorporateState(@PathVariable String newState, @PathVariable String registerNo) {
        CorporateAccount account = securitiesAccountService.fetchCorporateAccountByRN(registerNo);
        account.setState(Integer.valueOf(newState));
        securitiesAccountService.updateCorporateAccount(account);
        return new CustomResponse(new Result(true)).toString();
        // TODO 失败判断
    }

    /* ********************* Both Accounts ****************************** */

    /**
     * @param securitiesId 指定证券账户号
     * @return 返回与该证券账户关联的资金账户号列表
     */
    @RequestMapping(value = "/securities/fund_connected/{securitiesId}", method = GET)
    @ResponseBody
    public String fetchConnectedFund(@PathVariable String securitiesId) {
        return new CustomResponse(new Result(true),
                sfRelationService.selectRelationWithSecurities(Integer.valueOf(securitiesId)))
                .toString();
        // TODO 失败判断
    }

}
