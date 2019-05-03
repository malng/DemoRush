package design.maliang;

import design.maliang.jdbc.util.JDBCUtilDemo2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionDemo {

    private static Connection conn;
    private static String outAccount;
    private static String inAccount;
    private static long money;
    private static PreparedStatement preStat;

    public static void main(String[] args) {

        //录入信息
        EntryInfo();

        //转账事务
        TransferTx();
    }

    /**
     * 转账事务
     */
    private static void TransferTx() {
        try {
            //获取连接
            conn = JDBCUtilDemo2.getConnection();

            //开启事务
            conn.setAutoCommit(false);

            //设置事务隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            //拼接转账sql
            String outAcoutSQL = "UPDATE t_money SET money = money - ? WHERE name = ?;";
            String inAcoutSQL = "UPDATE t_money SET money = money + ? WHERE name = ?;";

            //获取执行Statement
            preStat = conn.prepareStatement(outAcoutSQL);
            preStat.setLong(1, money);
            preStat.setString(2, outAccount);
            int outRes = preStat.executeUpdate();
            preStat = conn.prepareStatement(inAcoutSQL);
            preStat.setLong(1, money);
            preStat.setString(2, inAccount);
            int inRes = preStat.executeUpdate();


            if (inRes == outRes && inRes == 1) {
                conn.commit();
                System.out.println("转账成功");
            }else {
                conn.rollback();
                System.out.println("转账失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtilDemo2.release(conn, preStat);
        }
    }

    /**
     * 录入信息
     */
    private static void EntryInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入汇款人姓名");
        outAccount = sc.nextLine();
        System.out.println("请输入收款人姓名");
        inAccount = sc.nextLine();
        System.out.println("请输入转账金额");
        money = sc.nextLong();
    }
}
