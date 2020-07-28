package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 实现FastDFS文件管理
 * 包含：文件上传
 *       文件删除
 *       文件下载
 *       文件信息获取
 *       storage信息获取
 *       Tracker信息获取
 */
public class FastDFSUtil {

    /**
     * 加载Tracker连接信息
     */
    static {
        try {
            //查找classpath下的文件路径
            String filename = new ClassPathResource("fdfs_client.conf").getPath();
            //加载Tracker连接信息
            ClientGlobal.init(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param fastDFSFile:上传的文件信息封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{
        //附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author","sunbl");

        //创建一个Tracker访问的客户端对象 TrackerClient
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient访问TrackerServer服务，获取连接信息
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer的链接信息可以获取storage的链接信息，创建StorageClient对象存储storage的链接信息
        StorageClient storageClient = new StorageClient(trackerServer, null);
        /**
         * 通过StorageClient访问storage，实现文件上传，并且获取文件上传后的存储信息
         * upload_file参数解释：
         * 1、上传文件的字节数组
         * 2、文件的扩展名 jpg
         * 3、附加参数  eg：拍摄地址：北京
         *
         * upload[]
         *      upload[0]:文件上传所存储的Storage组的名字   group1
         *      upload[1]:文件存储到Storage上的文件的名字   M00/02/44/111.jpg
         */
        String[] uploads = storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile.getExt(), meta_list);
        return uploads;
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws Exception
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取storage的信息，创建StorageClient对象存储
        StorageClient storageClient = new StorageClient(trackerServer,null);
        //获取文件信息
        FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
        return fileInfo;
    }


    /**
     * 文件下载
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws Exception
     */
    public static InputStream downloadFile(String groupName, String remoteFileName) throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取storage的信息，创建StorageClient对象存储
        StorageClient storageClient = new StorageClient(trackerServer,null);
        //下载文件
        byte[] buffer = storageClient.download_file(groupName, remoteFileName);
        return new ByteArrayInputStream(buffer);
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过TrackerServer获取storage的信息，创建StorageClient对象存储
        StorageClient storageClient = new StorageClient(trackerServer,null);
        //删除文件
        storageClient.delete_file(groupName, remoteFileName);
    }

    /**
     * 获取storage信息
     * @throws Exception
     */
    public static StorageServer getStorages() throws Exception{
        //创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackerClient获取TrackerServer的连接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取storage信息
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        return storeStorage;
    }

    public static void main(String[] args) throws Exception{
//        FileInfo fileInfo = getFile("group1","M00/00/00/wKgAa178KRCAOMI5AAZ7oCuAWCI484.jpg");
//        System.out.println(fileInfo.getSourceIpAddr());
//        System.out.println(String.valueOf(fileInfo.getFileSize()));


        //文件下载
//        InputStream is = downloadFile("group1", "M00/00/00/wKgAa178KRCAOMI5AAZ7oCuAWCI484.jpg");
//
//        FileOutputStream os = new FileOutputStream("D:/1.jpg");
//
//        byte[] buffer = new byte[1024];
//        while (is.read(buffer) !=-1){
//            os.write(buffer);
//        }
//        os.flush();
//        os.close();
//        is.close();

        //文件删除
//        deleteFile("group1", "M00/00/00/wKgAa178KRCAOMI5AAZ7oCuAWCI484.jpg");

        //获取storage信息
        StorageServer storageServer = getStorages();
        System.out.println(storageServer.getStorePathIndex());
        System.out.println(storageServer.getInetSocketAddress().toString());

    }

}
