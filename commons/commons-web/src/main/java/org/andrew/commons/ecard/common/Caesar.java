package org.andrew.commons.ecard.common;

public class Caesar {
    /**
     * 加密。
     */
    public static String encryption(String str, int key) {
        //加密
        String string = "";
        string = getString(str, key, string);
        //System.out.println(str+" 加密后为： "+string);
        return string;
    }

    /**
     * 解密。
     */
    public static String decrypt(String str, int index) {
        //解密
        int key = Integer.parseInt("-" + index);
        String string = "";
        string = getString(str, key, string);
        //System.out.println(str+" 加密后为： "+string);
        return string;
    }

    private static String getString(String str, int key, String string) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                //如果字符串中的某个字符是小写字母
                ch += key % 26;//移动key%26;
                if (ch < 'a') {
                    ch += 26;
                }
                if (ch > 'z') {
                    ch -= 26;
                }

            } else if (ch >= 'A' && ch <= 'Z') {
                //如果字符串中的某个字符是大写字母
                ch += key % 26;//移动key%26;
                if (ch < 'A') {
                    ch += 26;
                }
                if (ch > 'Z') {
                    ch -= 26;
                }
            }
            string += ch;//将解密后的字符串连成字符串
        }
        return string;
    }

}
