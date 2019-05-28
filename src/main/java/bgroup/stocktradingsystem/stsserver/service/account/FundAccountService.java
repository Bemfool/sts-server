package bgroup.stocktradingsystem.stsserver.service.account;

import bgroup.stocktradingsystem.stsserver.dao.account.FundAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundAccountService {
    @Autowired
    FundAccountDAO fundAccountDAO;

    public FundAccount fetchAccount(int id) {
        List<FundAccount> accounts = fundAccountDAO.select("fund_id = " + id);
        if(accounts.isEmpty())
            return null;
        else
            return accounts.get(0);
    }

    public int createAccount(FundAccount account) {
        account.setFundId(fundAccountDAO.maxId() + 1);
        fundAccountDAO.insert(account);
        return account.getFundId();
    }

    public void changePassword(int fundId, String newPassword) {
        FundAccount account = fetchAccount(fundId);
        account.setPassword(newPassword);
        fundAccountDAO.update(account);
    }

    public boolean getState(int fundId) {
        return fetchAccount(fundId).isState();
    }

    public void changeState(int fundId) {
        FundAccount account = fetchAccount(fundId);
        account.setState(!account.isState());
        fundAccountDAO.update(account);
    }

    public void deleteAccount(int fundId) {
        fundAccountDAO.delete(fundId);
    }

    public void updateBalance(int fundId, double amount) {
        FundAccount account = fetchAccount(fundId);
        account.setBalance(account.getBalance() + amount);
        fundAccountDAO.update(account);
    }
}
