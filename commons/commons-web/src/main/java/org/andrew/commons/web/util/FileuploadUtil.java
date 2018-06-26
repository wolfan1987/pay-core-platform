package org.andrew.commons.web.util;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by HJpower on 2017/5/24。
 */
public class FileuploadUtil {

    /**
     * 日志输出。
     */
    private static final Logger logger = LoggerFactory.getLogger(FileuploadUtil.class);

    /**
     * 上传图片。
     *
     * @param picture     图片
     * @param imgFilePath 图片路径
     * @return 上传结果
     */
    public static String uploadImage(MultipartFile picture, String imgFilePath, String fileUpload) {
        String fileName = filePath(fileUpload, imgFilePath) + "/" + ImageUtil.getFileName();
        String fileThumbnail = ImageUtil.toThumbnail(fileName.substring(0, fileName.indexOf(".")));
        OutputStream out = null;
        try {
            InputStream in = picture.getInputStream();
            out = new BufferedOutputStream(new FileOutputStream(fileName));
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException exception) {
            logger.error("上传图片异常", exception);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException exception) {
                logger.error("关闭io异常", exception);
            }
        }

        // 上传压缩图
        try {
            Thumbnails.of(fileName).scale(1.0f).toFile(fileThumbnail);
        } catch (IOException exception) {
            logger.error("上传图片异常", exception);
        }

        return fileName.substring(fileName.indexOf(imgFilePath) + imgFilePath.length(),
                                  fileName.length());
    }


    /**
     * 保存截取图片。
     *
     * @param imageStr base64图片数据
     * @return String 返回图片路径
     */
    public static String uploadImg(String imageStr, String imgFilePath, String fileUpload) {
        byte[] imgByte = ImageUtil.decode(imageStr);

        String fileName = filePath(fileUpload, imgFilePath) + "/" + ImageUtil.getFileName();
        String fileThumbnail = ImageUtil.toThumbnail(fileName.substring(0, fileName.indexOf(".")));
        OutputStream out = null;
        InputStream in = new ByteArrayInputStream(imgByte);
        // 上传原图
        try {
            out = new BufferedOutputStream(new FileOutputStream(fileName));
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException exception) {
            logger.error("上传图片异常", exception);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException exception) {
                logger.error("关闭io流异常", exception);
            }
        }
        // 上传压缩图
        try {
            Thumbnails.of(fileName).scale(1.0f).toFile(fileThumbnail);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return fileName.substring(fileName.indexOf(imgFilePath) + imgFilePath.length(),
                                  fileName.length());
    }

    /**
     * 获取上传图片的日期文件目录。
     *
     * @return String 文件目录
     */
    public static String filePath(String fileUpload, String imagePath) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(new Date());
        String yearDir = fileUpload + imagePath + "/" + dateStr.substring(0, 4);
        File yearFile = new File(yearDir);
        if (!yearFile.exists() && !yearFile.isDirectory()) {
            yearFile.mkdir();
        }
        String monthDir = yearDir + "/" + dateStr.substring(4, 6);
        File monthFile = new File(monthDir);
        if (!monthFile.exists() && !monthFile.isDirectory()) {
            monthFile.mkdir();
        }
        String dayDir = monthDir + "/" + dateStr.substring(6, 8);
        File dayFile = new File(dayDir);
        if (!dayFile.exists() && !dayFile.isDirectory()) {
            dayFile.mkdir();
        }
        return dayDir;
    }

    /**
     * makeFileName。
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     */
    public static String makeFileName(String filename) {  //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + filename;
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储。
     *
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     * @Method: makePath
     * @Description:
     */
    public static String makePath(String filename, String savePath) {
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4;  //0-15
        //构造新的保存目录
        String dir =
            savePath + File.separator + dir1 + File.separator + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if (!file.exists()) {
            //创建目录
            file.mkdirs();
        }
        return dir;
    }


}
