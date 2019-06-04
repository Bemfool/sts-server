package bgroup.stocktradingsystem.stsserver.impl.idao;

import bgroup.stocktradingsystem.stsserver.domain.Command;

import java.util.List;

/**
 *
 *
 */
public interface iCommandDAO {
    void insert(Command command);
    void delete(Command command);
    List<Command> select(String cond);
}
