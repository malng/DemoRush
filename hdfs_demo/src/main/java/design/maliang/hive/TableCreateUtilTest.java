package design.maliang.hive;

import design.maliang.hive.pojo.OdsWeblogOrigin;
import design.maliang.hive.util.TableCreateUtil;

public class TableCreateUtilTest {
    public static void main(String[] args) {
        String statement = TableCreateUtil.createTable(OdsWeblogOrigin.class);
        System.out.println(statement);
    }
}
