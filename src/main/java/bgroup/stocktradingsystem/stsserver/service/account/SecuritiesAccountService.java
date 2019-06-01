package bgroup.stocktradingsystem.stsserver.service.account;

import bgroup.stocktradingsystem.stsserver.dao.account.securities.CorporateAccountDAO;
import bgroup.stocktradingsystem.stsserver.dao.account.securities.PersonalAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.Stock;
import bgroup.stocktradingsystem.stsserver.domain.account.CorporateAccount;
import bgroup.stocktradingsystem.stsserver.domain.account.PersonalAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecuritiesAccountService {
    @Autowired
    PersonalAccountDAO personalAccountDAO;

    @Autowired
    CorporateAccountDAO corporateAccountDAO;

    /************************ Personal Account *******************************/

    public void createPersonalAccount(PersonalAccount account) {
        personalAccountDAO.insert(account);
    }

    public PersonalAccount fetchPersonalAccountByIN(String idNo) {
        return personalAccountDAO.select("id_no = '" + idNo + "'").get(0);
    }

    public PersonalAccount fetchPersonalAccountById(int id) {
        List<PersonalAccount> result = personalAccountDAO.select("securities_id = " + id);
        if(result.size()!=0)
            return result.get(0);
        else
            return null;
    }

    public void deletePersonalAccountById(int id) {
        PersonalAccount account = fetchPersonalAccountById(id);
        personalAccountDAO.deleteById(id);
        personalAccountDAO.saveDeleted(account);
    }

    public void deletePersonalAccountByIN(String idNo) {
        PersonalAccount account = fetchPersonalAccountByIN(idNo);
        personalAccountDAO.deleteByIN(idNo);
        personalAccountDAO.saveDeleted(account);
    }

    public void updatePersonalAccount(PersonalAccount account) {
        personalAccountDAO.update(account);
    }

    /************************ Corporate Account *******************************/

    public void createCorporateAccount(CorporateAccount account) {
        corporateAccountDAO.insert(account);
    }

    public CorporateAccount fetchCorporateAccountByRN(String registerNo) {
        return corporateAccountDAO
                .select("register_no = '" + registerNo + "'").get(0);
    }

    public CorporateAccount fetchCorporateAccountById(int id) {
        List<CorporateAccount> result = corporateAccountDAO.select("securities_id = " + id);
        if(result.size()!=0)
            return result.get(0);
        else
            return null;
    }

    public void deleteCorporateAccountById(int id) {
        CorporateAccount account = fetchCorporateAccountById(id);
        corporateAccountDAO.deleteById(id);
        corporateAccountDAO.saveDeleted(account);
    }

    public void deleteCorporateAccountByRN(String registerNo) {
        CorporateAccount account = fetchCorporateAccountByRN(registerNo);
        corporateAccountDAO.deleteByRN(registerNo);
        corporateAccountDAO.saveDeleted(account);
    }

    public void updateCorporateAccount(CorporateAccount account) {
        corporateAccountDAO.update(account);
    }

}
