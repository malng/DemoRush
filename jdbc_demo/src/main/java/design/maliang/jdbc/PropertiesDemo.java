package design.maliang.jdbc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {

        Properties pp = new Properties();

        try {
            pp.load(PropertiesDemo.class.getResourceAsStream("/a.properties"));
            //pp.load(new FileReader("..\\jdbc_demo\\src\\main\\resources\\a.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("修改前的数据" + pp);

        pp.setProperty("name", "ml");
        pp.setProperty("group", "design.maling");


        try {
            pp.store(new FileWriter("..\\jdbc_demo\\src\\main\\resources\\a.properties"), "admin01");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("存储失败");
        }

        System.out.println("修改后的数据" + pp);
    }
}
