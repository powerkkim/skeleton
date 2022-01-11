package com.powernote.skeleton.exception.error;

public enum MessageType {

    // common Error
    OK("OK")
    , NOK( "FAIL" )
    , ERROR_TYPE_000( "알수 없는 오류가 발생하였습니다.")
    , ERROR_TYPE_001( "파라미터가 유효하지 않습니다.")
    , ERROR_TYPE_002( "강제에러 발생 .")

    , ERROR_LOGIN_001( "로그인 정보가 올바르지 않습니다.")


    , ERROR_PAGE_400( "400 잘못된 요청입니다." )
    , ERROR_PAGE_403( "403 접근 권한이 없습니다.")
    , ERROR_PAGE_404( "404 요청하신 페이지를 찾을 수 없습니다.")
    , ERROR_PAGE_500( "500 서버 처리중 오류가 발생하였습니다." )

    // User Error

    // DB Error

    // File Error

    // FTP 에러


    ;

    private String message;

    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
