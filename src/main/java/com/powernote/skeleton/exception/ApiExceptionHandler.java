package com.powernote.skeleton.exception;

import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.exception.basic.BaseException;
import com.powernote.skeleton.exception.basic.BusinessException;
import com.powernote.skeleton.exception.basic.ClientException;
import com.powernote.skeleton.exception.basic.SystemException;
import com.powernote.skeleton.exception.error.ErrorRes;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.util.CommonUtil;
import com.powernote.skeleton.util.MessageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//@ControllerAdvice(annotations = RestController.class)
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandler {

//    @Value("${hjapp.appname}")
//    private String APPNAME;

    // 기본 에러 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRes> exception(HttpServletRequest request, Exception e) {
        String defaultError = MessageType.ERROR_TYPE_000.toString();
        ErrorRes response = ErrorRes.builder().build();

        response.setTimestamp(  new Date().toString()  );
        response.setStatus( String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value() ) );
        response.setError( HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() );
        response.setErrorCode( defaultError );
        response.setMessage(  MessageUtil.getMessage( defaultError ) );
        response.setPath( request.getRequestURI() );

//        LogUtils.tExlog( request , "[ Exception, " + String.valueOf( HttpStatus.INTERNAL_SERVER_ERROR.value() ) +   MessageUtil.getMessage( defaultError ) + "]");

//        ResponseDto message = new ResponseDto(MessageType.ERROR_TYPE_000, response);
//        return  new ResponseEntity<>(message, CommonUtil.getHeader(), HttpStatus.INTERNAL_SERVER_ERROR);

        return  new ResponseEntity<>(response, CommonUtil.getHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRes> processValidationError(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();

        builder.append("Parameter Error : ");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("]");
        }

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorRes response = ErrorRes.builder().build();
        response.setTimestamp(  new Date().toString()  );
        response.setStatus( String.valueOf(httpStatus.value()) );
        response.setError( httpStatus.getReasonPhrase() );
        response.setMessage(  builder.toString() );
        response.setPath( request.getRequestURI() );
        response.setException( ClientException.class.getName() );

        String logMsg = !StringUtils.hasLength(builder.toString()) ? "" : MessageUtil.getMessage(builder.toString());
//        LogUtils.tExlog( request , "[ MethodArgumentNotValidException , " + String.valueOf( HttpStatus.BAD_REQUEST.value() ) +  e.getMessage() + "]");

//        ResponseDto message = new ResponseDto(MessageType.ERROR_PAGE_400, response);
//        return  new ResponseEntity<>(message, CommonUtil.getHeader(), HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>(response, CommonUtil.getHeader(), HttpStatus.BAD_REQUEST);
    }

    // Controller - layer error
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErrorRes> clientException(HttpServletRequest request, ClientException e) {
        ErrorRes response = ErrorRes.builder().build();

        response.setTimestamp(  new Date().toString()  );
        response.setStatus( String.valueOf( e.getHttpStatus().value() ) );
        response.setError( e.getHttpStatus().getReasonPhrase() );
        response.setMessage( !StringUtils.hasLength(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode()) );
        response.setPath( request.getRequestURI() );
        response.setErrorCode( e.getMessagecode() );
        response.setException( ClientException.class.getName() );

        String logMsg = !StringUtils.hasLength(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode());
//        LogUtils.tExlog( request , "[ ClientException , " + String.valueOf( e.getHttpStatus().value() ) +  logMsg + "]");


//        ResponseDto message = new ResponseDto(MessageType.ERROR_PAGE_400, response);
//        return  new ResponseEntity<>(message, CommonUtil.getHeader(), HttpStatus.BAD_REQUEST);

        return  new ResponseEntity<>(response, CommonUtil.getHeader(), HttpStatus.BAD_REQUEST);
    }

    // Service - layer error
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorRes> businessException(HttpServletRequest request, BusinessException e) {
        ErrorRes response = ErrorRes.builder().build();

        response.setTimestamp(  new Date().toString()  );
        response.setStatus( String.valueOf( e.getHttpStatus().value() ) );
        response.setError( e.getHttpStatus().getReasonPhrase() );
        response.setPath( request.getRequestURI() );
        response.setMessage( !StringUtils.hasLength(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode()) );
        response.setErrorCode( e.getMessagecode() );
        response.setException( BusinessException.class.getName() );

        String logMsg = !StringUtils.hasLength(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode());
//        LogUtils.tExlog( request , "[ BusinessException , " + String.valueOf( e.getHttpStatus().value() ) + logMsg + "]");

//        ResponseDto message = new ResponseDto(MessageType.ERROR_PAGE_400, response);
//        return  new ResponseEntity<>(message, CommonUtil.getHeader(), e.getHttpStatus());

        return  new ResponseEntity<>(response, CommonUtil.getHeader(), e.getHttpStatus());
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ErrorRes> systemException(HttpServletRequest request, SystemException e) {
        ErrorRes response = ErrorRes.builder().build();

        response.setTimestamp(  new Date().toString()  );
        response.setStatus( String.valueOf( e.getHttpStatus().value() ) );
        response.setError( e.getHttpStatus().getReasonPhrase() );
        response.setErrorCode( e.getMessage() );
        response.setMessage( !StringUtils.hasLength(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode()) );
        response.setPath( request.getRequestURI() );
        response.setException( ClientException.class.getName() );

        String logMsg = !StringUtils.isEmpty(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode());
//        LogUtils.tExlog( request , "[ SystemException , " + String.valueOf( e.getHttpStatus().value() ) + logMsg + "]");

//        ResponseDto message = new ResponseDto(MessageType.ERROR_PAGE_500, response);
//        return  new ResponseEntity<>(message, CommonUtil.getHeader(), HttpStatus.INTERNAL_SERVER_ERROR);

        return  new ResponseEntity<>(response, CommonUtil.getHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity handleBaseException(HttpServletRequest request, BaseException e) {

        ResponseDto rDto = new ResponseDto( MessageType.NOK );
        rDto.setMessage( !StringUtils.hasLength(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode()) );

//        String logMsg = StringUtils.isEmpty(e.getMessagecode()) ? "" : MessageUtil.getMessage(e.getMessagecode());
//        LogUtils.tExlog( request , "[ BaseException , " + String.valueOf( HttpStatus.OK.value() ) + logMsg + "]");

        return new ResponseEntity(rDto, HttpStatus.OK);

    }


}
