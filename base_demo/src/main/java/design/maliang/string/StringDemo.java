package design.maliang.string;

import org.junit.Test;

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

}
