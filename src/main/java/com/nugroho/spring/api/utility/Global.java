package com.nugroho.spring.api.utility;

import io.sentry.Sentry;

public class Global {

    public static void report(String message) {
        Sentry.captureMessage(message);
    }

    public static void report(Throwable throwable) {
        Sentry.captureException(throwable);
    }
}
