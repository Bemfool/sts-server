package bgroup.stocktradingsystem.stsserver.service;

import bgroup.stocktradingsystem.stsserver.dao.IndexDAO;
import bgroup.stocktradingsystem.stsserver.domain.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {
    @Autowired
    IndexDAO indexDAO;

    public List<Index> fetchAllIndex() {return indexDAO.select("");}

    public List<Index> fetchCertainIndex(String cond) {return indexDAO.select(cond);}
}
