package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.securities.CorporateAccountDAO;
import bgroup.stocktradingsystem.stsserver.dao.securities.PersonalAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.CorporateAccount;
import bgroup.stocktradingsystem.stsserver.domain.PersonalAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecuritiesAccountService {
    @Autowired
    PersonalAccountDAO personalAccountDAO;

    @Autowired
    CorporateAccountDAO corporateAccountDAO;

    public void createPersonalAccount(PersonalAccount account) {
        personalAccountDAO.insert(account);
    }

    public void createCorporateAccount(CorporateAccount account) {
        corporateAccountDAO.insert(account);
    }

}
