package bgroup.stocktradingsystem.stsserver.service.account;

import bgroup.stocktradingsystem.stsserver.dao.account.AdminAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.account.AdminAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAccountService {
    @Autowired
    AdminAccountDAO adminAccountDAO;

    public AdminAccount fetchAccount(String id) {
        List<AdminAccount> accounts = adminAccountDAO.select("id = '" + id + "'");
        if(accounts.isEmpty())
            return null;
        else
            return accounts.get(0);
    }

    public Boolean changePassword(String id, String newPassword) {
        AdminAccount account = fetchAccount(id);
        if(account!=null) {
            account.setPassword(newPassword);
            adminAccountDAO.update(account);
            return true;
        } else {
            return false;
        }
    }

    public void newAccount(AdminAccount account) {
        adminAccountDAO.insert(account);
    }

}
