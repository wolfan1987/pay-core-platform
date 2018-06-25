package org.andrew.commons.utils.qrbar;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 一维码，二维码生成工具。
 *
 * @author andrewliu
 */
public final class QrBarCodeUtil {

    private static final String CODE         = "utf-8";
    private static final int    BLACK        = 0xff000000;
    private static final int    WHITE        = 0xFFFFFFFF;
    private static       String IMG_TYPE_PNG = "png";

    private QrBarCodeUtil() {
    }

    /**
     * 生成RQ二维码。
     *
     * @param str    内容
     * @param height 高度（px）
     */
    public static BufferedImage getQrCode(String str, Integer height) throws WriterException {
        if (height == null || height < 100) {
            height = 200;
        }
        // 文字编码
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, CODE);
        //设置二维码纠错级别为最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, height,
                                                             height, hints);
        bitMatrix = updateBit(bitMatrix, 10);
        return toBufferedImage(bitMatrix);
    }


    /**
     * 生成一维码（128）。
     *
     * @param str    条形码内容
     * @param width  宽度
     * @param height 高度
     * @return BufferedImage
     */
    public static BufferedImage getBarcode(String str, Integer width, Integer height)
        throws WriterException {
        if (width == null || width < 200) {
            width = 200;
        }
        if (height == null || height < 50) {
            height = 50;
        }
        // 文字编码
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, CODE);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.CODE_128, width,
                                                             height, hints);
        bitMatrix = updateBit(bitMatrix, 10);
        return toBufferedImage(bitMatrix);
    }

    /**
     * 把图片流转为base64文本。
     *
     * @param img 图片流
     * @return base64文本
     * @throws IOException IOException
     */
    public static String toBase64(BufferedImage img) throws IOException {
        ByteArrayOutputStream boutputStream = new ByteArrayOutputStream();
        ImageIO.write(img, IMG_TYPE_PNG, boutputStream);
        byte[] byteArray = boutputStream.toByteArray();
        return new String(Base64.encodeBase64(byteArray),CODE);
    }

    /**
     * 生成二维码图片的base64编码。
     *
     * @param str    二维码内容
     * @param height 高度
     * @return base64图片文本
     * @throws WriterException WriterException
     * @throws IOException     IOException
     */
    public static String getQrCodeBase64(String str, Integer height)
        throws WriterException, IOException {
        BufferedImage img = QrBarCodeUtil.getQrCode(str, height);
        return toBase64(img);
    }

    /**
     * 获取生成的二维码的base64编码。
     *
     * @param str   二维码内容
     * @param width 宽度
     * @param heigh 高度
     * @return base64编码图片文本
     */
    public static String getBarcodeBase64(String str, Integer width, Integer heigh)
        throws WriterException, IOException {
        BufferedImage img = QrBarCodeUtil.getBarcode(str, width, heigh);
        return toBase64(img);
    }

    /**
     * 生成二维码，写到文件中。
     *
     * @param str    二维码内容
     * @param height 高度
     * @param file   文件
     * @throws IOException IOException
     */
    public static void getQrWriteFile(String str, Integer height, File file)
        throws IOException, WriterException {
        BufferedImage image = getQrCode(str, height);
        ImageIO.write(image, IMG_TYPE_PNG, file);
    }

    /**
     * 生成一维码，写到文件中。
     *
     * @param str    条形码内容
     * @param width  宽度
     * @param height 高度
     * @param file   文件
     * @throws IOException IOException
     */
    public static void getBarcodeWriteFile(String str, Integer width, Integer height, File file)
        throws IOException, WriterException {
        BufferedImage image = getBarcode(str, width, height);
        ImageIO.write(image, IMG_TYPE_PNG, file);
    }

    /**
     * 转换成图片。
     *
     * @param matrix BitMatrix
     * @return BufferedImage
     */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 解码(二维、一维均可)。
     *
     * @param file 需要解码的文件
     * @return 文件
     */
    public static String read(File file) throws NotFoundException, IOException {
        Assert.notNull(file, "file is null");
        if (!file.exists()) {
            throw new RuntimeException("file not found ：".concat(file.getPath()));
        }
        BufferedImage image = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        Result result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 自定义白边边框宽度。
     *
     * @param matrix BitMatrix
     * @param margin 边框宽度
     * @return BitMatrix
     */
    private static BitMatrix updateBit(final BitMatrix matrix, final int margin) {
        int tempM = margin * 2;
        // 获取二维码图案的属性
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + tempM;
        int resHeight = rec[3] + tempM;
        // 按照自定义边框生成新的BitMatrix
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        // 循环，将二维码图案绘制到新的bitMatrix中
        for (int i = margin; i < resWidth - margin; i++) {
            for (int j = margin; j < resHeight - margin; j++) {
                if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
                    resMatrix.set(i, j);
                }
            }
        }
        return resMatrix;
    }
}
