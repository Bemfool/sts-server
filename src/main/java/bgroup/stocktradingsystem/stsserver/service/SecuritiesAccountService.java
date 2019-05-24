package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.SecuritiesAccountDAO;
import bgroup.stocktradingsystem.stsserver.domain.SecuritiesAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecuritiesAccountService {
    @Autowired
    SecuritiesAccountDAO securitiesAccountDAO;
}
