package design.maliang.io;

import java.io.*;

public class CopyByteFileTest {

    private static File inputFile = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpPic\\IO流的概述和分类.bmp");
    private static File outputFile = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\IO流的概述和分类.bmp");

    public static void main(String[] args) throws IOException {
        //记录开始时间
        double start = System.currentTimeMillis();

        //执行方法
        method1();
        //基本字节流一次读写一个字节    耗时:27.917秒
        //method2();
        //基本字节流一次读写一个字节数组 耗时:0.058秒
        //method3();
        //缓冲节流一次读写一个字节      耗时:0.217秒
        //method4();
        //缓冲节流一次读写一个字节数组   耗时:0.027秒

        //记录结束时间
        double end = System.currentTimeMillis();
        System.out.println("耗时:"+(end - start)/1000+"秒");

    }

    //基本字节流一次读写一个字节
    private static void method1() throws IOException {
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);
        int by;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }
        fos.close();
        fis.close();
    }

    //基本字节流一次读写一个字节数组
    private static void method2() throws IOException {
        FileInputStream fis = new FileInputStream(inputFile);
        FileOutputStream fos = new FileOutputStream(outputFile);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes,0,len);
        }
        fos.close();
        fis.close();
    }

    //缓冲节流一次读写一个字节
    private static void method3() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));
        int by ;
        while ((by = bis.read()) != -1) {
            bos.write(by);
        }
        bos.close();
        bis.close();
    }

    //缓冲节流一次读写一个字节数组
    private static void method4() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        bos.close();
        bis.close();
    }
}
