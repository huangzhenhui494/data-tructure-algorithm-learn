package com.hzh.chapter1.sparsearray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/**
 * @description:
 * @Author huangzhenhui
 * @Date 2021/2/17 16:07
 */
public class SparseArrayTest {


    private static final int ROW = 11;
    private static final int COL = 11;

    private static final String FILE_PATH = "E:\\H\\FileObject\\sparseArrayTest.out";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int[][] oriArr = new int[ROW][COL];
        oriArr[1][2] = 1;
        oriArr[2][3] = 2;
        oriArr[3][4] = 2;
        oriArr[10][10] = 1;
        int count= 4;

        // 转为稀疏数组
        int[][] sparseArr = convertToSparseArr(oriArr, count);
        for (int[] ints : sparseArr) {
            System.out.println(Arrays.toString(ints));
        }
        // 序列化
        arrSerialize(sparseArr);
        // 反序列化
        int[][] sparseArr2 = arrDeserialization();
        // 稀疏数组转原数组
        int[][] originArr2 = convertToOriArr(sparseArr2);
        for  (int[] ints : originArr2) {
            System.out.println(Arrays.toString(ints));
        }

    }

    /**
     * 转为稀疏数组
     * @param oriArr
     * @param count
     * @return
     */
    public static int[][] convertToSparseArr(int[][] oriArr, int count) {

        if (count == 0) {
            throw new ArithmeticException("??????????????????????");
        }

        int[][] sparseArr = new int[count + 1][3];
        sparseArr[0][0] = oriArr.length;
        sparseArr[0][1] = oriArr[0].length;
        sparseArr[0][2] = count;
        int sparseIndex = 1;
        for(int i = 0; i < oriArr.length; i++) {
            for(int j = 0; j < oriArr[i].length; j++) {
                if (oriArr[i][j] > 0) {
                    sparseArr[sparseIndex][0] = i;
                    sparseArr[sparseIndex][1] = j;
                    sparseArr[sparseIndex][2] = oriArr[i][j];
                    sparseIndex++;
                }
            }
        }
        return sparseArr;
    }

    /**
     * 稀疏数组转为原数组
     * @param sparseArr
     * @return
     */
    public static int[][] convertToOriArr(int[][] sparseArr) {

        int[][] oriArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1; i < sparseArr.length; i++) {
            oriArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return oriArr;
    }

    /**
     * 序列化
     * @param arr
     */
    public static void arrSerialize(int[][] arr) throws IOException {

        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(arr);
        oos.flush();
        oos.close();
    }

    /**
     * 反序列化
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static int[][] arrDeserialization() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(FILE_PATH);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (int[][]) ois.readObject();
    }


}
