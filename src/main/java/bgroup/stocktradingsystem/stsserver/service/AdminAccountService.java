package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.AdminAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.AdminAccount;
import bgroup.stocktradingsystem.stsserver.impl.DAOFactory;
import bgroup.stocktradingsystem.stsserver.impl.iAdminAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAccountService {
    @Autowired
    AdminAccountDAO adminAccountDAO;

}
