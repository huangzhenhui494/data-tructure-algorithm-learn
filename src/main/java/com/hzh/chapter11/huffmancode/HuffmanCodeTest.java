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
                ", 压缩后为:" + bytes.length + ", 压缩率:" + ((double) content.length() - (double) bytes.length)  / (double) content.length());

        Map<Byte, String> codes = HuffmanCode.getCodes(HuffmanCode.getNodes(content.getBytes()).get(0));

        byte[] res = HuffmanCode.decode(codes, bytes);
        System.out.println(new String(res));

    }

    /**
     * 压缩文件
     */
    @Test
    public void zipFile() {
        String srcFile = "D:\\data-tructure-algorithm-learn\\src\\main\\java\\com\\hzh\\chapter11\\huffmancode\\123.doc";
        String dstFile = "D:\\新建文件夹\\dst.zip";
        HuffmanCode.zipFile(srcFile, dstFile);
    }

    @Test
    public void unZipFile() {
        String zipFile = "D:\\新建文件夹\\dst.zip";
        String dstFile = "D:\\新建文件夹\\123.doc";
        HuffmanCode.unZipFile(zipFile, dstFile);

    }




}
