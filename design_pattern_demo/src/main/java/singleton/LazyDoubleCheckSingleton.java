package singleton;

public class LazyDoubleCheckSingleton {

    private LazyDoubleCheckSingleton(){}

    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;

    public static LazyDoubleCheckSingleton getInstance(){
        if (lazyDoubleCheckSingleton == null) {
            synchronized(LazyDoubleCheckSingleton.class){
                if (lazyDoubleCheckSingleton == null) {
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                    //1.分配内存
                    //2.初始化对象
                    //3.设置lazyDoubleCheckSingleton指向内存分配地址
                    //2与3可能发生重排序
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
