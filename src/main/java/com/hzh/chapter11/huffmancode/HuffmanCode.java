package com.hzh.chapter11.huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.function.BinaryOperator;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/3/9 20:33
 */
public class HuffmanCode {

    private static Map<Byte, String> huffmanCodes = new HashMap<>();

    private static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {

//
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);
//        Node huffmanTree = createHuffmanTree(getNodes(contentBytes));
//        // 前序遍历
////        preOrder(huffmanTree);
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
//        // 压缩, 压缩后为133
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println(Arrays.toString(huffmanCodeBytes));  // 17个   40-17 / 40 => 压缩率

    }

    /**
     * 解压
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件输出流
        OutputStream os = null;

        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取压缩后的赫夫曼数组, 什么顺序存的就什么顺序读
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写到dst文件
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件压缩
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {

        // 文件输入流, 输出流, 对象输出流
        OutputStream os = null;
        FileInputStream is = null;
        ObjectOutputStream oos = null;

        try {
            // 创建文件输入流
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] buffer = new byte[is.available()];
            // 读取文件
            is.read(buffer);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(buffer);
            // 创建文件输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件 输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 赫夫曼编码写入到压缩文件, 解压用
            oos.writeObject(huffmanCodes);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {  // 先开后关
                oos.close();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        // 1 先得到 huffmanBytes 对应的二进制字符串 形式如 "101010010...."
        StringBuilder stringBuilder = new StringBuilder();
        // 2 将byte数组转成二进制的字符串
         for(int i = 0; i < huffmanBytes.length; i++) {
             byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换  a -> 100  100 -> a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建集合存放byte
        List<Byte> list = new ArrayList<>();
        for(int i = 0; i < stringBuilder.length();) {
            // 双指针, 滑动窗口
            int count = 1;
            Byte b = null;
            String key;
            while (true) {
                key = stringBuilder.substring(i, i + count);
                if (!map.containsKey(key)) {
                    count++;
                } else {
                    b = map.get(key); // 匹配到
                    break;
                }
            }
            list.add(b);
            i += count; // i移动到count
        }

        // list中有了所有字符, 把list中的数据放入到byte[]
        byte[] bytes = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成一个二进制的字符串, 最后一位如果是负, 说明肯定满8位直接截取, 如果是正数要补高位
     * @param flag 标志是否需要补高位, 如果是true则需要取高位, 如果是false不补, 如果是最后一个字节, 无需补高位
     * @param b 该b对应的二进制的字符串, 注意是按补码返回  即  101010000(补码) 的 原码为-88
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {

        // 使用变量保存b
        int temp = b;  // 将byte转int
        // 如果是正数我们还需要补高位
        if (flag) {
            temp |= 256; // 按位与256   1 0000 0000  |  0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);  // 返回的是一个二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }




    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 根据nodes创建赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        // 生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
        // 根据赫夫曼编码对原来的字节数组进行压缩
        return zip(bytes, huffmanCodes);
    }

    /**
     *
     * @param bytes 原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例: String content = "i like like like java do you like a java" => content.getBytes();
     * return: 返回的是 1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
     * 对应的byte[] huffmanCodesBytes, 即最后要返回的,  即8位对应一个byte放到huffmanCodeBytes
     *
     * huffmanCodeBytes[0] = 10101000(补码) => (转成一个byte) -> 补码转成真正的byte
     *  ① 即转成它的反码[补码的符号位(首位1)不动,剩下的 -1]
     *     即10101000 - 1 = 10100111(反码)
     *  ② 反码转成原码(符号位不变, 其他再取反)
     *     即10100111  => 11011000
     *
     *     11011000 => 十进制为88, 前面是1为负数, 即-88
     *
     *  所以huffmanCodeBytes[0] = -88
     *
     *
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        // 1 利用huffmanCodes将bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodes.get(aByte));
        }
        // 将sb.toString转成byte[]

        // 统计返回byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 写成一句话就是  len = (stringBuilder.length() + 7) / 8

        // 创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录第几个byte;
        for(int i = 0; i < stringBuilder.length(); i += 8) { // 因为是每8位(bit)对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                // 该条件成立 即 循环的当前一轮已经是最后一轮, 且stringBuilder不能被8整除
                strByte = stringBuilder.substring(i);  // 取到最后
            } else {
                strByte = stringBuilder.substring(i, i + 8); // 左闭右开, 为了省去-1操作
            }
            // 将strByte转成一个byte放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index ++;
        }
        return huffmanCodeBytes;
    }


    /**
     * 根据赫夫曼树根节点获取赫夫曼编码
     * @param root
     * @return
     */
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.getLeft(), "0", stringBuilder);
        getCodes(root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 思路:
     *  1 将赫夫曼编码表存放在Map<Byte, String> 形式类似 32->01, 97->100
     *  2 在生成赫夫曼编码表示, 需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
     *
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到, 并放入到huffmanCodes
     * @param node 传入结点
     * @param code 路径: 左子结点是0, 右子结点1
     * @param stringBuilder   用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        // 每一个栈帧里面的StringBuilder是不同对象, 栈退出了该对象就没了, 即回溯(所以不需要清空操作什么的)
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 拼接
        stringBuilder2.append(code);
        // 如果node==null, 例如:上个节点是非叶子节点且左边的值即当前节点为null, 则不处理回到上一个栈帧, 找到上个节点的右节点, 此时当前栈帧append后的StringBuilder已经没用了, 退出后清除, 回到上一个栈帧的sb
        if (node != null) {
            // 没有存放数据说明该节点不是叶子节点
            if (node.getData() == null) {
                // 向左右递归, 左0右1
                getCodes(node.getLeft(), "0", stringBuilder2);
                getCodes(node.getRight(), "1", stringBuilder2);
            } else {
                // 找到叶子节点, 放入Map
                huffmanCodes.put(node.getData(), stringBuilder2.toString());
            }
        }
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(Node root) {
        if (root == null) {
            throw new RuntimeException("空");
        }
        root.preOrder();
    }

    /**
     * @param bytes  接收字节数组
     * @return       返回nodes
     */
    public static List<Node> getNodes(byte[] bytes) {

        // ① 创建一个list
        List<Node> nodes = new ArrayList<>();
        // ② 遍历bytes, 统计每一个byte出现的次数
        Map<Byte, Integer> bMap = new HashMap<>();
        for (byte b : bytes) {
            if (bMap.get(b) == null) {
                bMap.put(b, 1);
            } else {
                bMap.put(b, bMap.get(b) + 1);
            }
        }
        // ③ 遍历Map, 把每个键值对转成一个node对象,并加入nodes
        for (Map.Entry<Byte, Integer> entry : bMap.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * @param nodes 创建赫夫曼树
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        // ④ 生成赫夫曼树
        while (nodes.size() > 1) {
            // 排序, 从小到大
            Collections.sort(nodes);
            // 取出第一,二颗最小的二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 创建一颗新的二叉树, 根结点没有data只有权值
            Node parent = new Node(null, left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            // 将已经处理的两颗二叉树移除
            nodes.remove(left);
            nodes.remove(right);
            // 加入新结点
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
