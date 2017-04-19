package demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库连接
 * Created by hhh on 2017/4/12.
 */
public class DatabaseConnection {
    private Connection connection = null;

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/data";
    private static final String MYSQL_PASSWORD = "123456";

    public DatabaseConnection() throws Exception {
        /*
            Class.forName(xxx.xx.xx)返回的是一个类
            Class.forName(xxx.xx.xx)的作用是要求JVM查找并加载指定的类，
            也就是说JVM会执行该类的静态代码段
         */
        Class.forName(MYSQL_DRIVER);

        connection = DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws Exception {
        if(connection != null){
            connection.close();
        }
    }
}
