package design.maliang.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 把当前项目目录下的StringDemo.java内容复制到当前项目目录下的Copy.java中(改进版)
 *
 * 转换流的名字比较长，而我们常见的操作都是按照本地默认编码实现的，所以，为了简化我们的书写，转换流提供了对应的子类。
 * FileWriter:用来写入字符文件的便捷类
 * 		OutputStreamWriter
 * FileReader:用来读取字符文件的便捷类
 * 		InputStreamReader
 *
 * OutputStreamWriter = FileOutputStream + 编码表(GBK)
 * FileWriter = FileOutputStream + 编码表(GBK)
 * 构造方法：
 * FileWriter(String fileName)
 *
 * InputStreamReader = FileInputStream + 编码表(GBK)
 * FileReader = FileInputStream + 编码表(GBK)
 * 构造方法：
 * FileReader(String fileName)
 *
 * 数据源：
 * 		StringDemo.java---读数据---字符流---InputStreamReader---FileReader
 * 目的地：
 * 		Copy.java---写数据---字符流---OutputStreamWriter---FileWriter
 */
public class CopyCharFileTest {
    public static void main(String[] args) throws IOException {
        //封装数据源
        FileReader fr = new FileReader("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        //封装目的地
        FileWriter fw = new FileWriter("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile2");

        //读写数据
        char[] chars = new char[1024];
        int len;
        while ((len=fr.read(chars)) != -1) {
            fw.write(chars,0,len);
        }
        //释放资源
        fw.close();
        fr.close();
    }
}
