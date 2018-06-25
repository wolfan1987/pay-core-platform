package org.andrew.commons.utils.string;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ValidateCodeUtil {
    // 图片的宽度。
    private int           width     = 160;
    // 图片的高度。
    private int           height    = 40;
    // 验证码字符个数
    private int           codeCount = 5;
    // 验证码干扰线数
    private int           lineCount = 150;
    // 验证码
    private String        code      = null;
    // 验证码图片Buffer
    private BufferedImage buffImg   = null;


    private char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    public ValidateCodeUtil() {
        this.createCode();
    }


    /**
     * 构造方法。
     *
     * @param width  图片宽
     * @param height 图片高
     */
    public ValidateCodeUtil(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     * 构造方法。
     *
     * @param width     图片宽
     * @param height    图片高
     * @param codeCount 字符个数
     * @param lineCount 干扰线条数
     */
    public ValidateCodeUtil(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    /**
     * 生成验证码。
     */
    public void createCode() {
        int xaxis = width / (codeCount + 1);//每个字符的宽度
        int codeY = height - 4;
        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = buffImg.createGraphics();
        // 生成随机数
        Random random = new Random();
        // 将图像填充为白色
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, width, height);
        // 创建字体
        //ImgFontByte imgFont=new ImgFontByte();
        //Font font =imgFont.getFont(fontHeight);
        int fontHeight = height - 2;//字体的高度
        graphics2D.setFont(new Font("Times New Roman", Font.ITALIC, fontHeight));
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i = 0; i < lineCount; i++) {
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            graphics2D.setColor(new Color(red, green, blue));
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            graphics2D.drawLine(xs, ys, xe, ye);
        }
        // randomCode记录随机产生的验证码
        StringBuilder randomCode = new StringBuilder();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 产生随机的颜色值，让输出的每个字符的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            //g.setColor(new Color(red, green, blue));
            graphics2D.setColor(Color.BLACK);
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            graphics2D.drawString(strRand, (i) * xaxis, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        code = randomCode.toString();
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }

}
