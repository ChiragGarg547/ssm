package com.zzuli.eassy;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * <p>
 * Author: Administrator
 * Date: 2018-1-17 19:04
 * Created with IDEA
 */
public class FileTool {

    private static UserDefinedFileAttributeView userDefinedFileAttributeView;

    private static BasicFileAttributeView basicFileAttributeView;

    private static final String key = "code";
    public static byte[] readImg(String fileName){
        try{
            //获取后缀
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
            File file = new File(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage img = ImageIO.read(file);
            ImageIO.write(img,fileType,baos);
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean generateImg(byte[] bytes,String fileName,String fileType){
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage bi1 = ImageIO.read(bais);
            File out = new File(fileName);
            ImageIO.write(bi1,fileType ,out);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    //verify:""
    public boolean setEncryptionAttr(String code, String filename){
        try {
            Path path = Paths.get(filename);
            basicFileAttributeView = Files.getFileAttributeView(path,BasicFileAttributeView.class);
            userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            userDefinedFileAttributeView.write(key, Charset.defaultCharset().encode(code));
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public String getEncryptionAttr(String filename){
        try {
            Path path = Paths.get(filename);
            userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            if(!userDefinedFileAttributeView.list().contains(key)) return null;
            ByteBuffer bb = ByteBuffer.allocate(userDefinedFileAttributeView.size(key)); // 准备一块儿内存块读取
            userDefinedFileAttributeView.read(key, bb);
            bb.flip();
            String value = Charset.defaultCharset().decode(bb).toString();
            return value;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean copyFile(String srcPath, String destPath) throws IOException {

        // 打开输入流
        FileInputStream fis = new FileInputStream(srcPath);
        // 打开输出流
        FileOutputStream fos = new FileOutputStream(destPath);

        // 读取和写入信息
        int len = 0;
        // 创建一个字节数组，当做缓冲区
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            fos.write(b);
        }

        // 关闭流  先开后关  后开先关
        fos.close(); // 后开先关
        fis.close(); // 先开后关
        return true;
    }

    public static void main(String[] args) throws IOException {
        FileTool ft = new FileTool();
//        ft.setEncryptionAttr("shdjkahdjashdjkashdjhaskdhasdkjasdhk","C:\\Users\\Administrator\\Desktop\\1.png");
//        System.out.println(ft.getEncryptionAttr("D:\\Intell_workspace\\ssm\\target\\signature\\img\\safe\\me.jpg"));
//        FileTool.copyFile("D:\\Intell_workspace\\ssm\\target\\signature\\img\\safe\\me.jpg","C:\\Users\\Administrator\\Desktop\\aaa.jpg");
//
//        System.out.println(ft.getEncryptionAttr("C:\\Users\\Administrator\\Desktop\\aaa.jpg"));



//        SHAImplement sha = new SHAImplement();
//        RSAImplement rsa = new RSAImplement();
        System.out.println(ft.getEncryptionAttr("D:\\Intell_workspace\\ssm\\target\\signature\\img\\safe\\me.jpg"));
        byte[] bs = FileTool.readImg("D:\\Intell_workspace\\ssm\\target\\signature\\img\\safe\\me.jpg");
        bs = FileTool.readImg("D:\\Intell_workspace\\ssm\\target\\signature\\img\\me.jpg");
//        System.out.println(bs.length);
//        List<Byte> list = new ArrayList<Byte>();
//        for(int i = 0; i < bs.length; i++){
//            list.add(bs[i]);
//        }
//        String digest = sha.process(bs);
//        byte[] bDigest = ByteHexUtil.Hex2Byte(digest);
//        for(int i = 0; i < bDigest.length; i++){
//            list.add(bDigest[i]);
//        }
//        System.out.println(list.size());
//        bs = Bytes.toArray(list);
          FileTool.generateImg(bs,"C:\\Users\\Administrator\\Desktop\\aaa.jpg","jpg");
        bs = FileTool.readImg("C:\\Users\\Administrator\\Desktop\\aaa.jpg");
        System.out.println(ft.getEncryptionAttr("C:\\Users\\Administrator\\Desktop\\aaa.jpg"));
//        bs = FileTool.readImg("C:\\Users\\Administrator\\Desktop\\test.png");
//        System.out.println(bs.length);
    }
}
