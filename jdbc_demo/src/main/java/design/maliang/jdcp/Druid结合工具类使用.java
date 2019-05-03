package design.maliang.jdcp;

import design.maliang.jdcp.util.DruidUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Druid结合工具类使用 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            //1, 这个连接对象是我们自己创建的, 效率低. 我们改为: 从数据库连接池中获取.
            //conn = JDBCUtils.getConnection();

            //获取数据库连接池对象
            //DataSource ds = DruidUtils.getDataSource();
            //conn = ds.getConnection();

            conn = DruidUtil.getConnection();

            //2
            stat = conn.createStatement();
            //3
            String sql = "select * from t_money;";
            rs = stat.executeQuery(sql);
            //4
            while(rs.next()) {
                int aid = rs.getInt("uid");
                String aname = rs.getString("name");
                Long money = rs.getLong("money");
                System.out.println(aid + "..." + aname + "..." + money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.release(conn, stat, rs);
        }
    }
}
