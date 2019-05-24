package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.DAOFactory;
import bgroup.stocktradingsystem.stsserver.impl.iAdminAccountDAO;

public class AdminAccountService {
    private iAdminAccountDAO adminAccountDAO = DAOFactory.createAdminAccountDAO();

}
