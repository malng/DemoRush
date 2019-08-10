package singleton;

import java.io.Serializable;

public class HungrySingleton implements Serializable {

    //声明final的变量必须在类加载完成时就已经赋值
    private final static HungrySingleton hungrySingleton;

    static {
        hungrySingleton = new HungrySingleton();
    }

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

    //如果涉及序列化，添加readResolve方法避免序列化破坏
    private Object readResolve(){
        return hungrySingleton;
    }
}
