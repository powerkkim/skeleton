package com.powernote.skeleton.exception;

import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 * Controller 및 Service 단에서 비지니스 로직 처리시 발생한 Exception 처리 .
 * BaseErrorController 를 통하지 않는다.
 *
 */
@Slf4j
@ControllerAdvice(basePackages = "com.powernote.skeleton.controller")
public class WebExceptionHandler {

    // error.html이 있는경우는 해당 페이지로 이동하고 없는경우 error/4xx.html error/5xx.html 로 찾아 status 를 찾아 이동한다.
    // page가 있는경우( 404 등 ) BasicErrorController 를 통하지 않는다.
    @ExceptionHandler(value = Exception.class)
    public ModelAndView pageHandlerException(HttpServletRequest request, Model model, Exception e) {
        String defaultError = MessageType.ERROR_TYPE_000.toString();
        log.info("WebExceptionHandler  ");

        model.addAttribute("timestamp",   new Date().toString()  );
        model.addAttribute( "status", String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value() ) );
        model.addAttribute( "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() );
        model.addAttribute( "errorCode",   defaultError );
        model.addAttribute( "message", MessageUtil.getMessage( defaultError ) );   // MessageUtil을 통한 메시지 전달 처리 적용 필요.
        model.addAttribute( "path", request.getRequestURI() );
        model.addAttribute( "exception", Exception.class.getName() );

        log.info( "[ Exception, " + String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value() ) +   MessageUtil.getMessage( defaultError ) + "]");

        log.info("pageHandlerException");
        e.printStackTrace();
        return new ModelAndView("/error/error");
    }


    /**
     * 기본적인 에러 페이지의 경우에만 처리 하면 되므로 case별 처리는 필요 없어 보임.
     */
//    @ExceptionHandler(value = BaseException.class)
//    public ModelAndView handleBaseException(HttpServletRequest request, Model model, BaseException e) {
//        log.info("WEB handleBaseException");
//
//        model.addAttribute("timestamp",   new Date().toString()  );
//        model.addAttribute( "status", String.valueOf( HttpStatus.OK.value() ) );
//        model.addAttribute( "error", HttpStatus.OK.getReasonPhrase() );
//        model.addAttribute( "errorCode",   e.getMessagecode() );
//        String msg = StringUtils.isEmpty(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode());
//        model.addAttribute( "message", msg );   // MessageUtil을 통한 메시지 전달 처리 적용 필요.
//        model.addAttribute( "path", request.getRequestURI() );
//        model.addAttribute( "exception", Exception.class.getName() );
//
//        log.info( "[ Exception, " + String.valueOf( HttpStatus.OK.value() ) +   msg + "]");
//        return new ModelAndView("/error/error");
//    }




}
