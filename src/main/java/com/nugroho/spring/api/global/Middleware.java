package com.nugroho.spring.api.global;

public class Middleware {

    public static final String CHECK_AUTH = "CHECK_AUTH";
    public static final String CHECK_ROLE = "CHECK_ROLE";
    public static final String CHECK_PERMISSION = "CHECK_PERMISSION";
    public static final String CHECK_IS_MY_ROLE = "CHECK_IS_MY_ROLE";
    public static final String SEPARATOR = " | ";

    public static String KEY;
    public static String ROLE;
    public static String PERMISSION;

    public static void parseParam(Object param) {
        String[] dataParam = param.toString().split(Middleware.SEPARATOR);

        KEY = dataParam[0];
        if (dataParam.length > 1) {
            ROLE = dataParam[1];
            PERMISSION = dataParam[1];
        }
    }
}
