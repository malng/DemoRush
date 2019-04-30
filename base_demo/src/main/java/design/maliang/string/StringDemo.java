package design.maliang.string;

import org.junit.Test;

import java.util.Scanner;

public class StringDemo {

    /**
     * String(char[] value)
     * 分配一个新的 String，使其表示字符数组参数中当前包含的字符序列。
     */
    @Test
    public void Constructor1(){
        char[] value = {'h','e','l','l','o'};
        String str = new String(value);
        System.out.println(str);
    }

    /**
     * String(char[] value, int offset, int count)
     * 分配一个新的 String，它包含取自字符数组参数一个子数组的字符。
     */
    @Test
    public void Constructor2(){
        char[] chars = {'j','a','v','a','e','e'};
        String str = new String(chars,0,4);
        System.out.println(str);
    }

    /**
     * 遍历字符串
     */
    @Test
    public void traverseStr(){
        String str = "abced";
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
        }
    }

    /**
     * 拼接字符串
     * 举例：int[] arr = {1,2,3}
     * 输出结果：[1,2,3]
     */
    @Test
    public void concatStr(){
        int[] arr = {1,2,3};
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i!=arr.length-1) {
                sb.append(',');
            }
        }
        sb.append(']');
        System.out.println(sb);
    }

    /**
     * 反转字符串
     * 举例：键盘录入"abc"
     * 输出结果："cba"
     */
    @Test
    public void reverseStr(){
        //1.获得一个字符串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        String str = sc.nextLine();
        //2.反转字符串
        String revStr = new StringBuilder(str).reverse().toString();
        //3.输出字符串
        System.out.println(revStr);
    }

}
