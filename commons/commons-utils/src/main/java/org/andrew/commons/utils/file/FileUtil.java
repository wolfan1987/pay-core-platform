package org.andrew.commons.utils.file;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;

/**
 * 文件与字节数组互转。
 *
 * @author andrewliu 2017年4月24日下午2:49:45
 */
public class FileUtil {

    /**
     * 字节数组转换为文件。
     *
     * @param data   非空字节数组
     * @param tagSrc 目标文件
     */
    public static void bytes2File(byte[] data, String tagSrc) throws Exception {
        FileOutputStream fout = null;
        try {
            File file = new File(tagSrc);
            if (!file.getParentFile().exists()) {
                boolean success = file.getParentFile().mkdir();
                if (!success) {
                    throw new RuntimeException("服务器创建文件目录失败");
                }
            }
            fout = new FileOutputStream(file);
            // 将字节写入文件
            fout.write(data);
            fout.close();
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
    }

    /**
     * 字节数组转换为文件。
     *
     * @param data 非空字节数组
     * @param file 目标文件
     */
    public static void bytes2File(byte[] data, File file) throws Exception {
        FileOutputStream fout = null;
        try {
            if (!file.getParentFile().exists()) {
                boolean success = file.getParentFile().mkdir();
                if (!success) {
                    throw new RuntimeException("服务器创建文件目录失败");
                }
            }
            fout = new FileOutputStream(file);
            // 将字节写入文件
            fout.write(data);
            fout.close();
        } finally {
            if (fout != null) {
                fout.close();
            }
        }
    }

    /**
     * 字节数组转换为文件。
     *
     * @param data         非空字节数组
     * @param outputStream 目标文件输出流
     */
    public static void bytes2File(byte[] data, OutputStream outputStream) throws Exception {
        // 将字节写入文件
        outputStream.write(data);
        outputStream.close();
    }

    /**
     * 字节数组转换为图片。
     *
     * @param data   非空字节数组
     * @param tagSrc 目标文件
     */
    public static void bytes2Image(byte[] data, String tagSrc) throws Exception {
        if (data.length < 3 || tagSrc.equals("")) {
            return;
        }
        File file = new File(tagSrc);
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdir();
            if (!success) {
                throw new RuntimeException("服务器创建文件目录失败");
            }
        }
        FileImageOutputStream imageOutput = new FileImageOutputStream(file);
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
    }

    /**
     * 字节数组转换为图片。
     *
     * @param data 非空字节数组
     * @param file 目标文件
     */
    public static void bytes2Image(byte[] data, File file) throws Exception {
        if (data.length < 3 || null == file) {
            return;
        }
        if (!file.getParentFile().exists()) {
            boolean success = file.getParentFile().mkdir();
            if (!success) {
                throw new RuntimeException("服务器创建文件目录失败");
            }
        }
        FileImageOutputStream imageOutput = new FileImageOutputStream(file);
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
    }

    /**
     * 字节数组转换为图片。
     *
     * @param data         非空字节数组
     * @param outputStream 目标文件输出流
     */
    public static void bytes2Image(byte[] data, OutputStream outputStream) throws Exception {
        if (data.length < 3 || null == outputStream) {
            return;
        }
        outputStream.write(data, 0, data.length);
        outputStream.close();
    }

    /**
     * 文件转为字节数组。
     *
     * @param imgSrc 源文件
     * @return 字节数组
     */
    public static byte[] image2Bytes(String imgSrc) throws Exception {
        File file = new File(imgSrc);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在:" + imgSrc);
        }
        FileImageInputStream input = new FileImageInputStream(file);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        byte[] data = output.toByteArray();
        output.close();
        input.close();
        return data;
    }

    /**
     * 文件转为字节数组。
     *
     * @param file 源文件
     * @return 字节数组
     */
    public static byte[] image2Bytes(File file) throws Exception {
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        FileImageInputStream input = new FileImageInputStream(file);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        byte[] data = output.toByteArray();
        output.close();
        input.close();
        return data;
    }

    /**
     * 文件转为字节数组。
     *
     * @param input 源文件输入流
     * @return 字节数组
     */
    public static byte[] image2Bytes(InputStream input) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int numBytesRead = 0;
        while ((numBytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, numBytesRead);
        }
        byte[] data = output.toByteArray();
        output.close();
        input.close();
        return data;
    }

}
