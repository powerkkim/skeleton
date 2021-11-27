package com.powernote.skeleton.util;

import com.powernote.skeleton.exception.error.MessageType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageUtil {

    public static String getMessage(String m){
        String mesage = "";
        log.info( "MessageUtil  m {}", m );
        MessageType errorType = MessageType.valueOf(m);

        return errorType.getMessage();
    }
}

/**
 * 다국어 처리를 위한 MessageSource 사용시에는 아래를 사용필요.
 */
//@Component
//public class MessageUtil {
//    private static MessageSource messageSource;
//
//    @Autowired
//    public void setMessageSource(MessageSource messageSource) {
//        MessageUtil.messageSource = messageSource;
//    }
//
//    public static String getMessage(String m){
//        String mesage = "";
//        try {
//            mesage = messageSource.getMessage(m, null, Locale.getDefault());
//        } catch (NoSuchMessageException e) {
////            e.printStackTrace();
////            LogUtils.debug("잘못된 메시지 코드 입니다. " + m);
//        } finally {
//
//        }
//
//        return mesage;
//    }
//
//    public static String getMessage(String m, Object[] objs) {
//        return messageSource.getMessage(m, objs, Locale.getDefault());
//    }
//
//    public static String getMessage(String m, Object[] objs, Locale locale) {
//        return messageSource.getMessage(m, objs, locale);
//    }
//}
