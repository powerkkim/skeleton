package com.powernote.skeleton.exception;


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * FutuTask 에서 메시지 처리를 못하고 void 로 리턴되는 경우의 비동기 메시지 처리.
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
//        LogUtils.info("#### Async log insert ERROR ####");
//        LogUtils.info("#### Exception Message :: " + ex.getMessage());
//        LogUtils.info("#### Method Name :: " + method.getName());
//        LogUtils.info("#### Async log insert ERROR END ####");
    }

}