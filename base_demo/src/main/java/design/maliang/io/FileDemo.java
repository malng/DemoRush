package design.maliang.io;

import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class FileDemo {

    /**
     * File(String pathname)
     */
    @Test
    public void Constructor1(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\");
    }

    /**
     * File(String parent, String child)
     */
    @Test
    public void Constructor2(){
        File file = new File("..\\base_demo\\src\\main\\resources\\tmpfolder\\","tmpfile");
    }

    /**
     * File(File parent, String child)
     */
    @Test
    public void Constructor3(){
        File parentFile = new File("..\\base_demo\\src\\main\\resources\\tmpfolder\\");
        File file = new File(parentFile, "tmpFile");
    }

    //创建一个文件夹
    @Test
    public void mkDir(){
        File file = new File(".\\aaa\\");
        System.out.println(file.mkdir());
    }

    //创建一个文件
    @Test
    public void createFile() throws IOException {
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        System.out.println(file.createNewFile());
    }

    //删除 文件夹/文件
    @Test
    public void delete(){
        //删除文件
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        System.out.println(file.delete());
        //删除文件夹
        file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\");
        System.out.println(file.delete());
    }

    //创建多级目录
    @Test
    public void mkDirs(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder1\\tmpfolder2\\");
        System.out.println(file.mkdirs());
    }

    //判断是否是目录
    @Test
    public void isDirectory(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\");
        System.out.println(file.isDirectory());
    }

    //判断是否是文件
    @Test
    public void isFile(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        System.out.println(file.isFile());
    }

    //获取绝对路径
    @Test
    public void getAbsolutePath(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
    }

    //获取相对路径
    @Test
    public void getPath(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        String path = file.getPath();
        System.out.println(path);
    }

    //获取名称
    @Test
    public void getName(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        String name = file.getName();
        System.out.println(name);
    }

    //得到父目录
    @Test
    public void getParent(){
        File file = new File("D:\\workspace\\DemoRush\\base_demo\\src\\main\\resources\\tmpfolder\\tmpFile");
        String parent = file.getParent();
        System.out.println(parent);
    }
}
