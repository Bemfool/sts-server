package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.CommandDAO;
import bgroup.stocktradingsystem.stsserver.domain.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 */
@Service
public class CommandService {
    @Autowired
    CommandDAO commandDAO;

    public List<Command> fetchAllInCmd() {
        return commandDAO.select("command_type = 1");
    }

    public List<Command> fetchAllOutCmd() {
        return commandDAO.select("command_type = 0");
    }

    public List<Command> fetchCertainInCmd(String cond) {
        return commandDAO.select("command_type = 1 AND " + cond);
    }

    public List<Command> fetchCertainOutCmd(String cond) {
        return commandDAO.select("command_type = 0 AND " + cond);
    }
}
