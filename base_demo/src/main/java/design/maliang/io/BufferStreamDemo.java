package design.maliang.io;

import java.io.*;

/*
 * 字符缓冲区流：
 * BufferedWriter：
 * 将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
 * 可以指定缓冲区的大小，或者接受默认的大小。在大多数情况下，默认值就足够大了。
 * 构造方法：
 * 		BufferedWriter(Writer out)
 *
 * BufferedReader：
 * 从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
 * 可以指定缓冲区的大小，或者可使用默认的大小。大多数情况下，默认值就足够大了。
 * 构造方法：
 * 		BufferedReader(Reader in)
 *
 * 	* 数据源：
 * 		a.txt---读数据---字符流---InputStreamReader---FileReader---BufferedReader
 * 目的地：
 * 		b.txt---写数据---字符流---OutputStreamWriter---FileWriter---BufferedWriter
 * 特殊功能
 * BufferedWriter
 * 		void newLine():写入一个行分隔符,这个行分隔符是由系统决定的
 *
 * BufferedReader
 * 		String readLine():包含该行内容的字符串，不包含任何行终止符，如果已到达流末尾，则返回 null

 */
public class BufferStreamDemo {
    public static void main(String[] args) throws IOException {
        //封装数据源
        BufferedReader br = new BufferedReader(new FileReader("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile"));
        //封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile2"));

        //读写数据
        String line;
        while((line=br.readLine())!=null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        //释放资源
        bw.close();
        br.close();

    }
}
