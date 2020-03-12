package ca.monor.recursion;
/**
 * 测试一个程序运行所需要的时长
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimesTester {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void test(String title, Task task) {
        if (task == null) return;
        title = title == null ? "" : ("[ " + title + " ]");
        System.out.println(title);
        System.out.println("Begin: " + sdf.format(new Date()));
        long begin = System.currentTimeMillis();
        task.execute();
        long end = System.currentTimeMillis();
        System.out.println("End: " + sdf.format(new Date()));
        double delta = (end - begin);
        System.out.println("Consumed "+ delta+" ms");
        System.out.println("------------------cutter----------------");

    }
}
