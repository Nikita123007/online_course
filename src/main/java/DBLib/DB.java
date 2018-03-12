package DBLib;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

public class DB {
    private static String configPath = "D:/MPP/Web1/src/main/resources/config.properties";

    public static Connection GetConnection() throws SQLException, IOException {
        FileInputStream fis = null;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(configPath);
            property.load(fis);

            String url = property.getProperty("db.url");
            String username = property.getProperty("db.username");
            String password = property.getProperty("db.password");

            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            throw new SQLException("Ошибка при попытке чтения config.properties.");
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    //String query = "SELECT * FROM Table WHERE Column1 = ? AND Column2 >= ?"; (SELECT only)
    //String[] args = ['column1Value', 'column2Value']
    public static ResultSet ExecuteQuery(String query, Connection con, String[] args) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(query);
            for (Integer i = 0; i < args.length; i++) {
                stmt.setString(i + 1, args[i]);
            }
            return stmt.executeQuery(query);
        }finally {
            if (stmt != null){
                stmt.close();
            }
        }
    }

    //String query = "INSERT INTO JC_CONTACT (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES (?, ?, ?,?)"; (INSERT, DELETE, UPDATE)
    //String[] args = ['param1', 'param2', 'param3', 'param4']
    public static int ExecuteUpdate(String query, Connection con, String[] args) throws SQLException {
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(query);
            for (Integer i = 0; i < args.length; i++) {
                stmt.setString(i + 1, args[i]);
            }
            return stmt.executeUpdate(query);
        }finally {
            if (stmt != null){
                stmt.close();
            }
        }
    }
}
