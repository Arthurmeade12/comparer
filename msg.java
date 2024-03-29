package me.arthurmeade12.comparer;
public class msg {
    public static String drop(String a, int b) {
        return a.substring(0, a.length() - b);
    }
    public static String trim(String a, int b) {
        return a.substring(a.length() - b);
    }
    public static void out(String msg) {
        System.out.printf("\033[1;34m==>\033[0m \033[1;37m%s\033[0m%n", msg);
    }
    public static void out_nonewline(String msg) {
        System.out.printf("\033[1;34m==>\033[0m \033[1;37m%s\033[0m", msg);
    }
    public static void warn(String msg) {
        System.out.printf("\033[1;33m==>\033[0m \033[1;37mWARNING:\033[0m \033[1;37m%s\033[0m%n", msg);
    }
    public static void debug(String msg) {
        if (config.values.debug == true) {
            System.out.printf("\033[1;33m==>\033[0m \033[1;37mDEBUG:\033[0m \033[1;37m%s\033[0m%n", msg);
        }
    }
}
