package com.nugroho.spring.api.global;

public class Middleware {
    
    public static final String CHECK_ROLE = "CHECK_ROLE";
    public static final String CHECK_PERMISSION = "CHECK_PERMISSION";
    public static final String CHECK_IS_MY_ROLE = "CHECK_IS_MY_ROLE";
    public static final String SEPARATOR = " | ";
    
    public static String key;
    public static String role;
    public static String permission;

    public static void parseParam(Object param) {
        String[] dataParam = param.toString().split(Middleware.SEPARATOR);

        key = dataParam[0];
        role = dataParam[1];
        permission = dataParam[1];
    }
}
