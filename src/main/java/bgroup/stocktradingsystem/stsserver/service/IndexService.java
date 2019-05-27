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

    public List<Index> fetchAllIndex() {
        return indexDAO.select("");
    }

    public Index fetchCertainIndex(String code) {
        return indexDAO.select("index_code = '" + code + "'").get(0);
    }

    public void updateIndex(Index index) {
        indexDAO.update(index);
    }

    public void updateIndexList(List<Index> indices) {
        for (Index index : indices)
            indexDAO.update(index);
    }
}
