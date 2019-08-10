package design.maliang.hive.util;

import java.lang.reflect.Field;

public class TableCreateUtil {

    private TableCreateUtil() {

    }

    /**
     * 将一个pojo类转换为对应的hive建表语句。
     * 例如：
     * public class OdsPerson{
     *     private int id;
     *     private String userName;
     *     ...
     * }
     * 返回的String内容为
     * create table ods_person(
     * id string,
     * user_name string)
     * row format delimited fields terminated by '\001';
     *
     * @param pojoClass
     * @return
     */
    public static String createTable(Class pojoClass){

        StringBuilder res = new StringBuilder();

        res.append("create table ");

        String tableName = StringUtil.underscoreLowerCaseName(pojoClass.getSimpleName());

        res.append(tableName + "(\r\n");

        Field[] pojoFields = pojoClass.getDeclaredFields();

        for (int i = 0; i < pojoFields.length; i++) {
            //获取字段名
            String fieldName = StringUtil.underscoreLowerCaseName(pojoFields[i].getName());
            //获取字段类型，这个地方可以该进为类型匹配
            String type = pojoFields[i].getType().getSimpleName().toLowerCase();
            //拼接字段
            if (i==pojoFields.length-1) {
                res.append(fieldName + " " +type + ")\r\n");
                break;
            }
            res.append(fieldName + " " +type + ",\r\n");
        }

        res.append("row format delimited fields terminated by '\\001';\r\n");

        return res.toString();
    }

}
