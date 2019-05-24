package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.AdminAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAccountService {
    @Autowired
    AdminAccountDAO adminAccountDAO;

}
