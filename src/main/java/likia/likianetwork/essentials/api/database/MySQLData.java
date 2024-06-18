package likia.likianetwork.essentials.api.database;

import java.sql.*;
import java.util.Locale;

public class MySQLData {
    public static Integer getInt(String tableName, String primaryKey, String dataName,Database database) {
        Integer data = null;
        try {
            ResultSet result = database.getStatement().executeQuery("select " + dataName + " from " + tableName + " where uuid='"+primaryKey+"';");

            result.next();
            data = result.getInt(dataName);

            result.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return data;
    }

    public static String getString(String tableName, String primaryKey, String dataName,Database database) {
        String data = null;
        try {
            ResultSet result = database.getStatement().executeQuery("select " + dataName + " from " + tableName + " where uuid='"+primaryKey+"';");

            result.next();
            data = result.getString(dataName);

            result.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return data;
    }

    public static Byte getByte(String tableName, String primaryKey, String dataName,Database database) {
        Byte data = null;
        try {
            ResultSet result = database.getStatement().executeQuery("select " + dataName + " from " + tableName + " where uuid='"+primaryKey+"';");

            result.next();
            data = result.getByte(dataName);

            result.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return data;
    }

    public static Boolean getBoolean(String tableName, String primaryKey, String dataName,Database database) {
        Boolean data = null;
        try {
            ResultSet result = database.getStatement().executeQuery("select " + dataName + " from " + tableName + " where uuid='"+primaryKey+"';");

            result.next();
            data = result.getBoolean(dataName);

            result.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return data;
    }

    public static void setString(String tableName, String primaryKey, String primaryKeyName, String dataName, String setData,Database database) {
        try {
            database.getStatement().executeQuery("UPDATE " + tableName + " SET " + dataName + "='" + setData + "' WHERE " + primaryKeyName + "='" + primaryKey + "'").close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setInt(String tableName, String primaryKey, String primaryKeyName, String dataName, Integer setData,Database database) {
        try {
            database.getStatement().executeQuery("UPDATE " + tableName + " SET " + dataName + "=" + setData + " WHERE " + primaryKeyName + "='" + primaryKey + "'").close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setByte(String tableName, String primaryKey, String primaryKeyName, String dataName, Byte setData,Database database) {
        try {
            database.getStatement().executeQuery("UPDATE " + tableName + " SET " + dataName + "=" + setData + " WHERE " + primaryKeyName + "='" + primaryKey + "'").close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBoolean(String tableName, String primaryKey, String primaryKeyName, String dataName, Boolean setData,Database database) {
        Short booleanToBit = 0;
        if(setData){booleanToBit = 1;}
        setInt(tableName,primaryKey,primaryKeyName,dataName,(int) booleanToBit,database);
    }

    public static Object executeCommand(String command,Database database) {
        if (!command.toLowerCase(Locale.ROOT).contains("drop")) {
            return "You have no permission to delete database or stuff!";
        }

        Object data = null;
        try {
            Object result = database.getStatement().executeQuery(command);
            data = result;
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return data;
    }
}
