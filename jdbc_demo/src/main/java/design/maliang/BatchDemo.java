package design.maliang;

import design.maliang.jdbc.util.JDBCUtilDemo2;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class BatchDemo {

    private static Connection conn;
    private static PreparedStatement ps;

    public static void main(String[] args) {
        try {
            Properties pp = new Properties();
            pp.load(new FileReader("D:\\workspace\\DemoRush\\jdbc_demo\\src\\main\\resources\\config.properties"));
            Class.forName(pp.getProperty("driverClass"));
            conn = DriverManager.getConnection(pp.getProperty("url"), pp.getProperty("username"), pp.getProperty("password"));
            String sql1 = "UPDATE t_money SET money = money + ? WHERE name = ?;";
            ps = conn.prepareStatement(sql1);
            ps.setLong(1, 100);
            ps.setString(2, "李四");
            ps.addBatch();
            ps.setLong(1, -100);
            ps.setString(2, "王五");
            ps.addBatch();
            int[] ints = ps.executeBatch();
            ps.clearBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtilDemo2.release(conn,ps);
        }
    }
}
