package org.andrew.commons.utils.serializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;

public class SerializeUtils {


    private static Kryo kryo = new Kryo();

    /**
     * 序列化。
     *
     * @param instance 序列对象
     * @return byte数组
     */
    public static byte[] serialize(Object instance) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Output output = new Output(outputStream);
        try {
            kryo.writeObject(output, instance);
            return output.toBytes();
        } finally {
            output.close();
        }
    }

    /**
     * 反序列化。
     *
     * @param data  byte数组
     * @param clazz 类型
     * @param <T>   返回对象类型
     * @return 对象
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        Input input = new Input(data);
        try {
            return kryo.readObject(input, clazz);
        } finally {
            input.close();
        }
    }

}
