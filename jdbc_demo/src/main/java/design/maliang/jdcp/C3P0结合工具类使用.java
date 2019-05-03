package design.maliang.jdcp;

import design.maliang.jdcp.util.C3P0Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class C3P0结合工具类使用 {
    public static void main(String[] args) {
        /*
         * 1. 获取连接对象
         * 2. 获取可以执行SQL语句的对象.
         * 3. 执行SQL语句, 获取结果集.
         * 4. 操作结果集.
         * 5. 释放资源.
         */
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
			/*//获取数据库连接池对象
			DataSource ds = C3P0Utils.getDataSource();
			//从数据库连接池对象中获取 连接对象
			conn = ds.getConnection();*/

            conn = C3P0Utils.getConnection();

            //2
            stat = conn.createStatement();
            //3
            String sql = "select * from t_money;";
            rs = stat.executeQuery(sql);
            //4
            while(rs.next()) {
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                long money = rs.getLong("money");
                System.out.println(uid + "---" + name + "----" + money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.release(conn, stat, rs);
        }
    }
}
