package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.FundAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.domain.FundAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundAccountService {
    @Autowired
    FundAccountDAO fundAccountDAO;

    public FundAccount fetchAccount(String id) {
        List<FundAccount> accounts = fundAccountDAO.select("id = '" + id + "'");
        if(accounts.isEmpty())
            return null;
        else
            return accounts.get(0);
    }
}
