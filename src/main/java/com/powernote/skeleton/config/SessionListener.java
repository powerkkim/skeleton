package com.powernote.skeleton.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SimpleDateFormat sf = new SimpleDateFormat( "yyyy년 MM월 dd일 a HH:mm:ss");
        String strNowDate = sf.format(System.currentTimeMillis());
        System.out.println( strNowDate +" :session created");
//        se.getSession().setMaxInactiveInterval(10);
        se.getSession();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SimpleDateFormat sf = new SimpleDateFormat( "yyyy년 MM월 dd일 a HH:mm:ss");
        String strNowDate = sf.format(System.currentTimeMillis());
        System.out.println( strNowDate +" :session destroyed");
    }
}
