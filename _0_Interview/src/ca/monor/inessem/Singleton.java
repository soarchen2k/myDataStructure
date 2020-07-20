package ca.monor.inessem;

/**
 * 单例模式 Singleton
 */
public class Singleton {
    // 初始化一个 Singleton 类型的全局变量
    private static Singleton instance;

    // 无参构造
    private Singleton() {  }

    // get 方法，创建一个 Singleton
    public static Singleton getInstance() {

        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
