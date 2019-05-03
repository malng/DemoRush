package design.maliang;

import design.maliang.jdcp.util.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class DBUtilsDemo {
    public static void main(String[] args) {
        //1.创建可执行sql语句的对象QueryRunner
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        //2.执行sql语句，获取结果集
        String sql = "UPDATE t_money SET money = money + ? WHERE name = ?;";
        try {
            int num = qr.update(sql, 100, "李四");
            System.out.println(num > 0 ? "更改成功" : "更改失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
