package com.hzh.chapter11.huffmancode;

import org.junit.Test;
import sun.security.krb5.internal.crypto.HmacMd5ArcFourCksumType;

import java.util.Arrays;
import java.util.Map;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/10 6:50
 */
public class HuffmanCodeTest {

    /**
     * 赫夫曼压缩
     */
    @Test
    public void test1() {

        String content = "i like like like java do you like a java";
        byte[] bytes = HuffmanCode.huffmanZip(content.getBytes());
        System.out.println("压缩前为40个字节=>压缩后:" + Arrays.toString(bytes) +
                ", 压缩后为:" + bytes.length + ", 压缩率:" + (40d - (double) bytes.length)  / 40d);

        Map<Byte, String> codes = HuffmanCode.getCodes(HuffmanCode.getNodes(content.getBytes()).get(0));

        byte[] res = HuffmanCode.decode(codes, bytes);
        System.out.println(new String(res));

    }


}
