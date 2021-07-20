package Database;
import java.sql.*;

public class Database {
	private static final String URL = "jdbc:mysql://localhost:3306/kid?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "wyb43564356";
    private static final String Driver = "com.mysql.jdbc.Driver";

    private static Connection conn = null;

    static {
    	//连接数据库
    	//System.out.println("daoci");
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("数据库连接成功!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() { return conn; }
}
