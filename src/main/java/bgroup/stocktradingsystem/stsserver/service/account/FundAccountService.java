package bgroup.stocktradingsystem.stsserver.service.account;

import bgroup.stocktradingsystem.stsserver.dao.account.FundAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.account.FundAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FundAccountService {
    final private FundAccountDAO fundAccountDAO;

    @Autowired
    public FundAccountService(FundAccountDAO fundAccountDAO) {
        this.fundAccountDAO = fundAccountDAO;
    }

    public FundAccount fetchAccount(int id) {
        List<FundAccount> accounts = fundAccountDAO.select("fund_id = " + id);
        if(accounts.isEmpty())
            return null;
        else
            return accounts.get(0);
    }

    private List<FundAccount> fetchAllAccount() {
        return fundAccountDAO.select("");
    }


    @Transactional
    public int createAccount(FundAccount account) {
        account.setFundId(fundAccountDAO.maxId() + 1);
        System.out.println("新ID: " + account.getFundId());
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

    @Transactional
    public void updateBalance(int fundId, double amount) {
        System.out.println("更新余额ID: " + fundId);
        FundAccount account = fetchAccount(fundId);
        account.setBalance(account.getBalance() + amount);
        fundAccountDAO.update(account);
    }

    public List<Integer> fetchConnectedFundAccount( int securitiesId) {
        List<Integer> list = fundAccountDAO.selectFromPFRelation(securitiesId);
        list.addAll(fundAccountDAO.selectFromCFRelation(securitiesId));
        return list;
    }

    public void alterSecuritiesId(int oldId, int newId) {
        fundAccountDAO.alterSecuritiesId(oldId, newId);
    }

    @Transactional
    public boolean settleInterest() {
        List<FundAccount> accounts = fetchAllAccount();
        System.out.println("更新利息");
        double rate = fundAccountDAO.getRate();
        for(FundAccount account : accounts) {
            double interest = account.getInterest() + account.getBalance() * rate;
            System.out.println("新利息: " + interest);
            int intInterest = (int) interest;
            System.out.println("新利息整数部分: " + intInterest);
            double doubleInterest = interest - (double) intInterest;
            System.out.println("新利息小数部分: " + doubleInterest);
            account.setBalance(account.getBalance() + intInterest);
            account.setInterest(doubleInterest);
            fundAccountDAO.update(account);
        }
        return true;
    }
}
