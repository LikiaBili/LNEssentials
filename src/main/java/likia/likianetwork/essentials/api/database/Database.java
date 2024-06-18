package likia.likianetwork.essentials.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection connection = null;
    private Statement statement = null;

    public Connection getConnection(){
        return connection;
    }

    public Statement getStatement(){
        return statement;
    }

    public Database(String databaseName,String user,String pass){
        connect(databaseName,user,pass);
    }

    public void connect(String databaseName,String user,String pass) {
        if(!connectStatus()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String DB_URL = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            try {
                connection = DriverManager.getConnection(DB_URL,user,pass);
                statement = connection.createStatement();
            } catch (SQLException se) {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception e) {
                // 处理 Class.forName 错误
                e.printStackTrace();
            }
        }
    }
    public boolean connectStatus() {
        if (connection != null && statement != null) {
            return true;
        } else {
            return false;
        }
    }

    public void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
