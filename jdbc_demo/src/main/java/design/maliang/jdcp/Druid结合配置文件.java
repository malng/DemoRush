package design.maliang.jdcp;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import design.maliang.jdbc.util.JDBCUtilDemo2;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Druid结合配置文件 {

    private static Connection conn;
    private static Statement stat;
    private static ResultSet rs;

    public static void main(String[] args) {

        Properties pp = new Properties();
        try {
            pp.load(Druid结合配置文件.class.getResourceAsStream("/config.properties"));
            ClassLoader loader = Druid结合配置文件.class.getClassLoader();
            URL url = loader.getResource("config.properties");
            String path = url.getPath();
            System.out.println(path);

            DataSource ds = DruidDataSourceFactory.createDataSource(pp);
            conn = ds.getConnection();
            stat = conn.createStatement();
            String sql = "SELECT * FROM t_money";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int uid = rs.getInt(1);
                String name = rs.getString(2);
                long money = rs.getLong(3);
                System.out.println(uid + "-" + name + "-" + money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtilDemo2.release(conn, stat, rs);
        }
    }
}
