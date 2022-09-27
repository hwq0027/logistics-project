package cn.hwq.utils;

public class UserHolder {
    private static final ThreadLocal<Object>  tl = new ThreadLocal<>();

    public static void saveObject(Object object){
        tl.set(object);
    }

    public static Object getObject(){
        return tl.get();
    }

    public static void removeObject(){
        tl.remove();
    }
}