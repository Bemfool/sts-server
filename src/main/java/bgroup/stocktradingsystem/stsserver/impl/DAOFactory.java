package bgroup.stocktradingsystem.stsserver.impl;

import bgroup.stocktradingsystem.stsserver.dao.*;
import bgroup.stocktradingsystem.stsserver.impl.idao.*;

public class DAOFactory {
    public static iAdminAccountDAO createAdminAccountDAO() {
        return new AdminAccountDAO();
    }

    public static iSecuritiesAccountDAO createSecuritiesAccountDAO() {
        return new SecuritiesAccountDAO();
    }

    public static iFundAccountDAO createFundAccountDAO() {
        return new FundAccountDAO();
    }

    public static iStockDAO createStockDAO() {
        return new StockDAO();
    }

    public static iIndexDAO createIndexDAO() {
        return new IndexDAO();
    }

}
