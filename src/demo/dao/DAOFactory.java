package demo.dao;

/**
 * Created by hhh on 2017/4/12.
 */
public class DAOFactory {

    public static KeywordDAO getInstance() throws Exception {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        return new KeywordDAOImpl(databaseConnection.getConnection());
    }
}
