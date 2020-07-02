package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

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
//    static {
//        try {
//            //查找classpath下的文件路径
//            String filename = new ClassPathResource("fdfs_client.conf").getPath();
//            String filename2 = new ClassPathResource("fdfsclient.properties").getPath();
//            String filename1 = "E:\\ideaAllWorkspace\\workspaceChangGou\\changgou-parent\\changgou-service\\changgou-service-file\\src\\main\\resources\\fdfs_client.conf";
//            //加载Tracker连接信息
//            ClientGlobal.init(filename);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 文件上传
     * @param fastDFSFile:上传的文件信息封装
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception{
        String filename = new ClassPathResource("fdfs_client.conf").getPath();
        //加载Tracker连接信息
        ClientGlobal.init(filename);
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



//    public void test(){
//        // 3、创建一个TrackerClient对象。
//        TrackerClient trackerClient = new TrackerClient();
//        // 4、创建一个TrackerServer对象。
//        TrackerServer trackerServer = trackerClient.getConnection();
//        // 5、声明一个StorageServer对象，null。
////        StorageServer storageServer = null;
//        // 6、获得StorageClient对象。
//        StorageClient storageClient = new StorageClient(trackerServer, null);
//
//        // 7、直接调用StorageClient对象方法上传文件即可。
////        URL url = new URL("http://a.hiphotos.baidu.com/image/pic/item/0e2442a7d933c895e97204b1d81373f0830200ef.jpg");//65536
//        //            URL url = new URL("http://localhost:8080/dd/dddd.jpg");//65536
////        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        //设置超时间为3秒
////        conn.setConnectTimeout(3 * 1000);
//        //防止屏蔽程序抓取而返回403错误
////        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
////        int fileLength = conn.getContentLength();//重要：网络文件的大小
//        //得到输入流
////        InputStream inputStream = conn.getInputStream();
//        NameValuePair[] metaList = new NameValuePair[3];
//        metaList[0] = new NameValuePair("fileName", "dddddd.jpg");
//        metaList[1] = new NameValuePair("fileExtName", "jpg");
//        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));
//
////        UploadFileSender us = new UploadFileSender(inputStream);
//
////        String[] strings = storageClient.upload_file(null,fileLength,us, "jpg", metaList);
//
////        for (String string : strings) {
////            System.out.println(string);
////        }
//    }

}
