package design.maliang.io;

import org.junit.Test;

import java.io.*;

public class OutPutStreamWriterDemo {

    private final FileOutputStream out = new FileOutputStream("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile",true);
    private final OutputStreamWriter osw = new OutputStreamWriter(out,"GBK");

    public OutPutStreamWriterDemo() throws FileNotFoundException, UnsupportedEncodingException { }

    //写一个字符
    @Test
    public void write1() throws IOException {
        int ch = 97;
        osw.write(ch);
        osw.close();
    }

    //写一个字符数组
    @Test
    public void write2() throws IOException {
        char[] chars= {'a','b','c','d'};
        osw.write(chars, 1, 3);
        osw.flush();
        osw.close();
    }

    //写一个字符串
    @Test
    public void write3() throws IOException {
        osw.write("HelloWorld", 5, 5);
        osw.close();
    }

}
