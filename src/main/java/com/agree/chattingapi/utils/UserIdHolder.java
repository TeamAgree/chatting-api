package com.agree.chattingapi.utils;

public class UserIdHolder {
    private static ThreadLocal<String> userId = ThreadLocal.withInitial(() -> null);

    public static void setUserId(String id) {
        userId.set(id);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void clear() {
        userId.remove();
    }
}

