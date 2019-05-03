package design.maliang.jdbc.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import static org.junit.Assert.*;

public class JDBCUtilDemo2Test {

    private Connection conn;
    private PreparedStatement preStat;
    private ResultSet rs;
    private String username;
    private String password;

    @Test
    public void release() {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号：");
        username = sc.nextLine();

        System.out.println("请输入密码：");
        password = sc.nextLine();

        try {
            conn = JDBCUtilDemo2.getConnection();
            String sql = "select * from t_users where username = ? and password = ? ;";
            preStat = conn.prepareStatement(sql);
            preStat.setString(1, username);
            preStat.setString(2, password);
            rs = preStat.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt("uid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(uid + "---" + username + "---" + password);
            }else {
                System.out.println("登陆失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtilDemo2.release(conn, preStat, rs);
        }
    }
}