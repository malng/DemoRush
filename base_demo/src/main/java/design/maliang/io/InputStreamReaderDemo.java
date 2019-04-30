package design.maliang.io;

import org.junit.Test;

import java.io.*;

public class InputStreamReaderDemo {

    private FileInputStream fis = new FileInputStream("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
    private InputStreamReader isr = new InputStreamReader(fis,"GBK");

    public InputStreamReaderDemo() throws FileNotFoundException, UnsupportedEncodingException { }

    //一次读取一个字符
    @Test
    public void read() throws IOException {
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.println((char) ch);
        }
        isr.close();
    }

    //一次读取一个数组
    @Test
    public void read2() throws IOException {
        char[] chars = new char[1024];
        int len;
        while ((len = isr.read(chars)) != -1) {
            System.out.println(new String(chars, 0, len));
        }
        isr.close();
    }
}
