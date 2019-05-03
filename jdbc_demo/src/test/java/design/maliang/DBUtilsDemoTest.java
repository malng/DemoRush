package design.maliang;

import design.maliang.domain.Account;
import design.maliang.jdcp.util.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DBUtilsDemoTest {


    private QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
    private String sql= "select * from t_money;";


    /**
     * 将每个结果封装成一个数组
     */
    @Test
    public void ArrayListHandlerDemo(){
        try {
            List<Object[]> query = qr.query(sql, new ArrayListHandler());
            for (Object[] objects : query) {
                System.out.println(Arrays.toString(objects));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将第一个查询结果封装成数组
     */
    @Test
    public void ArrayHandlerDemo(){
        try {
            Object[] query = qr.query(sql, new ArrayHandler());
            System.out.println(Arrays.toString(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将查询到的第一个结果封装成对象
     */
    @Test
    public void BeanHandlerDemo(){
        try {
            Account query = qr.query(sql, new BeanHandler<>(Account.class));
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将查询到的结果封装成对象的list
     */
    @Test
    public void BeanListHandlerDemo(){
        try {
            List<Account> query = qr.query(sql, new BeanListHandler<>(Account.class));
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将查询到的第一个结果封装成map
     */
    @Test
    public void MapHandlerDemo(){
        try {
            Map<String, Object> query = qr.query(sql, new MapHandler());
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将查询到的结果封装成mapList
     */
    @Test
    public void MapListHandlerDemo(){
        try {
            List<Map<String, Object>> query = qr.query(sql, new MapListHandler());
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将查到的结果封装成mapMap
     */
    @Test
    public void KeyedHandlerDemo(){
        try {
            Map<Integer, Map<String, Object>> query = qr.query(sql, new KeyedHandler<Integer>("uid"));
            for (Integer uid : query.keySet()) {
                System.out.println(query.get(uid));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集中指定的列的字段值，封装到一个 List 集合中
     */
    @Test
    public void ColumnListHandlerDemo(){
        try {
            List<String> name = qr.query(sql, new ColumnListHandler<String>("name"));
            System.out.println(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 它是用于单数据。例如 select count(*) from 表操作。
     */
    @Test
    public void ScalarHandlerDemo(){
        try {
            sql = "select count(*) from t_money;";
            Object query = qr.query(sql, new ScalarHandler<>());
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}