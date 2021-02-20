package com.hzh.chapter1.singlelinkedlist;

import org.junit.Test;

/**
 * @description: 对比C++和Java的引用, 来自知乎 https://zhuanlan.zhihu.com/p/131612029
 * @Author huangzhenhui
 * @Date 2021/2/20 10:15
 */
public class ComparedQuote {

    /**
     * 可以看出实参并没有任何变化，其实直接传递引用和直接传递一个指针是一样的，传递进去的只是实参的一个拷贝值，
     * 赋值运算符只是让引用指向了一个新对象，其本身是没有任何影响的。其实也就是num1此时指向的是一个新的地址（这块地址存的是10），
     * 而num指向的还是原来那块地址（还是1）。（如果想要传进去后有改变的话，就得提供一个改变自身的方法，例如上例中可以在Number类里设置一个Setter的方法）
     */
    @Test
    public void test1() {
        Value value = new Value(1);
        System.out.println(value.getNum1());
        change(value);
        System.out.println(value.getNum1());
    }

    /**
     * ① 传递给swap的确是拷贝值，是一个地址的拷贝指
     * ② java中每一次new后，都会在堆中开辟一个新的内存，并且将产生的地址值赋给它的引用，然后引用通过这个地址值对堆中的数据进行操作。
     * 所以这里调用swap方法后，传递了一个拷贝地址值给swap的引用，这时这个引用就能拿着这个地址值对堆中的数据进行操作了。
     * ③ 首先在main中，通过Value value = new Value(); 我们可以让value引用拿到对象的地址值，
     * 此时value引用就可以拿着地址值对堆区的对象进行操作。这时通过value.num1 = 10; value.num2 = 20;给num1，num2附上值。
     * ④调用swap方法，swap(value) 此时我们就会将main中value引用的地址【拷贝值】传过去，swap获取到了地址值后，它也可以对刚刚在main
     * 中new出来的那个Value对象进行操作，因为现在swap中的value引用也拿到了对应的地址值。这时候通过交换，就可以看到我们上面的结果。
     */
    @Test
    public void test2() {
        Value value = new Value();
        value.num1 = 10;
        value.num2 = 20;
        System.out.println("***********交换前***********");
        System.out.println("num1 = " + value.num1 + "," + "num2 = " + value.num2);
        System.out.println("****************************");
        System.out.println("***********交换后***********");
        swap(value);
        System.out.println("========main========");
        System.out.println("num1 = " + value.num1 + "," + "num2 = " + value.num2);
        System.out.println("====================");
        System.out.println("****************************");
        value = null;  // 通过结果我们可以看到并没有任何的变化，也就是说实参根本没受到影响
    }

    /**
     * 此时的value是main方法中value引用地址的拷贝值
     * @param value
     */
    public static void change(Value value) {
        value = new Value(10);
    }

    /**
     * 传入引用进行交换
     */
    static void swap(Value value) {
        int temp = value.num1;
        value.num1 = value.num2;
        value.num2 = temp;
        System.out.println("========swap========");
        System.out.println("num1 = " + value.num1 + "," + "num2 = " + value.num2);
        System.out.println("====================");
    }

    public static class Value {
        public int num1;
        public int num2;

        public Value() {
        }

        public Value(int num1) {
            this.num1 = num1;
        }

        public int getNum1() {
            return num1;
        }

        public void setNum1(int num1) {
            this.num1 = num1;
        }

        public int getNum2() {
            return num2;
        }

        public void setNum2(int num2) {
            this.num2 = num2;
        }
    }
}
