package bgroup.stocktradingsystem.stsserver.impl;

import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;

import java.util.List;

public interface iAdminAccountDAO {
    public void insert(AdminAccount account);
    public void update(AdminAccount account);
    public void delete(String id);
    public List<AdminAccount> select(String cond);
}
