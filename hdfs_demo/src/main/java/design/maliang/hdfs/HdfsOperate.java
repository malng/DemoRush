package design.maliang.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.testng.annotations.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HdfsOperate {

    /**
     * 读取hdfs的文件
     * 通过java的urlAPI来读取数据
     */
    @Test
    public void readFile() throws Exception {

        //第一步：注册驱动
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
        String url = "hdfs://node01:8020/export/servers/hello.txt";
        //通过输入流读取文件
        InputStream inputStream = new URL(url).openStream();
        //定义我们的输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\hello.txt"));
        //new OutputStream()
        IOUtils.copy(inputStream,fileOutputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(fileOutputStream);

    }


    /**
     * hdfs当中的文件系统
     * 如何获取一个抽象类对象
     * FileSyste 抽象类
     * 两种方式：1、找子类   2、有没有提供什么方法，返回他本身
     *
     */

    /**
     * 第一种方式获取分布式文件系统
     */
    @Test
    public  void  getFileSystem() throws IOException {
        //获取到fileSystem
        Configuration configuration = new Configuration();
        //fs.defaultFS  默认值 file:///   改成   hdfs://node01:8020  就是获取分布式文件系统
        configuration.set("fs.defaultFS","hdfs://node01:8020");
        FileSystem fileSystem = FileSystem.get(configuration);
        System.out.println(fileSystem.toString());
    }

    /**
     * 第二种方式获取分布式文件系统
     */
    @Test
    public  void  getFileSystem2() throws Exception {
        //file:///
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        System.out.println(fileSystem.toString());
    }

    /**
     * 第三种方式获取分布式文件系统客户端
     */
    @Test
    public  void getFileSystem3() throws URISyntaxException, IOException {

        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020/"), new Configuration());
        System.out.println(fileSystem.toString());

    }

    /**
     * 第四种方式获取分布式文件系统客户端
     */
    @Test
    public void getFileSystem4() throws  Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.52.100:8020");
        FileSystem fileSystem = FileSystem.newInstance(configuration);
        System.out.println(fileSystem.toString());
    }


    /**
     * 递归遍历hdfs上面所有的文件
     */
    @Test
    public void  getAllFileOnHdfs() throws  Exception{
        //获取分布式文件系统客户端
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020/"), new Configuration());
        Path path = new Path("/");
        //获取到/路径下面所有的文件的状态
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        //循环遍历文件的状态
        for (FileStatus fileStatus : fileStatuses) {
            //判断文件究竟是文件夹还是文件
            if(fileStatus.isDirectory()){
                //如果是文件夹，继续遍历
                getFiles(fileSystem,fileStatus.getPath());
            }else{
                //打印文件的路径
                System.out.println(fileStatus.getPath().toString());
            }
        }
        //关闭客户端
        fileSystem.close();
    }

    public  void  getFiles(FileSystem fileSystem,Path path) throws  Exception{
        FileStatus[] fileStatuses = fileSystem.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {
            if(fileStatus.isDirectory()){
                getFiles(fileSystem,fileStatus.getPath());
            }else{
                System.out.println(fileStatus.getPath().toString());
            }
        }
    }


    //通过hdfs的API直接遍历
    @Test
    public  void getAllFileWithHdfs() throws  Exception{

        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        RemoteIterator<LocatedFileStatus> fileIterator = fileSystem.listFiles(new Path("/"), true);
        while (fileIterator.hasNext()){
            LocatedFileStatus fileStatus = fileIterator.next();
            Path path = fileStatus.getPath();
            System.out.println(path.toString());
        }
        fileSystem.close();
    }


    /**
     * hdfs下载文件到本地
     */
    @Test
    public  void downFile()throws  Exception{
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://node01:8020"), new Configuration());
        //获取到文件的输入流
        FSDataInputStream open = fileSystem.open(new Path("hdfs://node01:8020/export/servers/hello.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\hello2.txt"));
        IOUtils.copy(open,fileOutputStream);
        IOUtils.closeQuietly(open);
        IOUtils.closeQuietly(fileOutputStream);
        fileSystem.close();
    }


    /*
     *  hdfs创建文件夹
     */
    @Test
    public  void  createDirs() throws  Exception{
        //获取文件系统的客户端
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020"), new Configuration());
        fileSystem.mkdirs(new Path("/mytest/mydir"));
        fileSystem.close();
    }

    /**
     * 文件上传
     */
    @Test
    public void uploadFile() throws Exception {
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020"), new Configuration());
        fileSystem.copyFromLocalFile(new Path("file:///D:\\hello2.txt"),new Path("hdfs://node01:8020/mytest/mydir"));
        fileSystem.close();
    }

    /**
     * hdfs上面创建文件
     */
    @Test
    public void createFile() throws  Exception{
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020"), new Configuration());
        fileSystem.createNewFile(new Path("hdfs://node01:8020/mytest/hellonew.txt"));
        fileSystem.close();
    }

    /**
     * hdfs权限控制
     */
    @Test
    public void downCoreFile() throws  Exception{
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020"), new Configuration(),"root");
        //  hdfs   ==>   localFile
        fileSystem.copyToLocalFile(new Path("hdfs://node01:8020/config/core-site.xml"),new Path("file:///D:\\core-site.xml"));
        fileSystem.close();
    }


    /**
     * hdfs的小文件的合并
     */
    @Test
    public  void mergeSmallFile() throws  Exception{
        //将本地的小文件，在hdfs上面合并成一个大文件
        FileSystem fileSystem = FileSystem.newInstance(new URI("hdfs://node01:8020"), new Configuration(), "root");
        //往hdfs上面写文件，得到一个输出流
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("hdfs://node01:8020/test/bigfile.xml"));
        //通过本地文件系统来读取本地的小文件
        LocalFileSystem localFileSystem = FileSystem.getLocal(new Configuration());
        FileStatus[] fileStatuses = localFileSystem.listStatus(new Path("file:///D:\\smallFile"));
        for (FileStatus fileStatus : fileStatuses) {
            Path path = fileStatus.getPath();
            //获取我们文件的输入流
            FSDataInputStream inputStream = localFileSystem.open(path);
            IOUtils.copy(inputStream,fsDataOutputStream);
            IOUtils.closeQuietly(inputStream);
        }
        IOUtils.closeQuietly(fsDataOutputStream);
        localFileSystem.close();
        fileSystem.close();
    }
}
