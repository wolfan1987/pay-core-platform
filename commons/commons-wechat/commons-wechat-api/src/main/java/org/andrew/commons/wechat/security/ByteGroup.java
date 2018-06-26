package org.andrew.commons.wechat.security;

import java.util.ArrayList;

public class ByteGroup {

    ArrayList<Byte> byteContainer = new ArrayList<>();

    /**
     * 字节码集合转换为字节码数组。
     *
     * @return byte[]
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    /**
     * 添加字节码数组中所有字节码添加到byteContainer集合中。
     *
     * @param bytes byte[]
     * @return ByteGroup
     */
    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }

    /**
     * 字节码集合中的字节码长度。
     *
     * @return int
     */
    public int size() {
        return byteContainer.size();
    }
}
