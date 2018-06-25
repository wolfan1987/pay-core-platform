package org.andrew.commons.utils.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * jaxb解析xml工具类。
 *
 * @author andrewliu
 */
public final class JaxbUtil {

    /**
     * 编码。
     */
    private static final String CHARSET_NAME = "UTF-8";

    private JaxbUtil() {
    }

    /**
     * JavaBean转换成xml 默认编码UTF-8。
     *
     * @param obj 待转换的javaBean
     * @return 转换后的xml字符串
     */
    public static String convertToXml(Object obj) {
        return convertToXml(obj, CHARSET_NAME);
    }

    /**
     * JavaBean转换成xml。
     *
     * @param obj      待转换的javaBean
     * @param encoding 编码
     * @return 转换后的xml字符串
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception ex) {
            throw new RuntimeException("转换xml出错", ex);
        }
        return result;
    }

    /**
     * xml转换成JavaBean。
     *
     * @param xml   待转换的xml字符串
     * @param clazz 转换的目标对象类型
     * @return 转换后的javaBean对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> clazz) {
        T object = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception ex) {
            throw new RuntimeException("xml转换为bean出错{}", ex);
        }
        return object;
    }

    /**
     * xml文件字节输入流转换为javaBean。
     *
     * @param in    输入字节流
     * @param clazz 要转换的目标对象类型
     * @return 转换后的目标对象
     */
    public static <T> T converyToJavaBean(InputStream in, Class<T> clazz) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, CHARSET_NAME));
            StringBuilder xmlSb = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                xmlSb.append(str);
            }
            return converyToJavaBean(xmlSb.toString(), clazz);
        } catch (IOException ex) {
            throw new RuntimeException("读取xml内容出错", ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    throw new RuntimeException("关闭流出错", ex);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    throw new RuntimeException("关闭流出错", ex);
                }
            }
        }
    }
}
