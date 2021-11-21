
function c_confirmAlert(notiTxt){
    return swal({
        title: '확인',
        text: notiTxt,
        icon: 'info',
        buttons: {
            confirm: {
                text: 'Confirm',
                value: true,
                visible: true,
                className: 'btn btn-info',
                closeModal: true
            },
            cancel: {
                text: 'Cancel',
                value: null,
                visible: true,
                className: 'btn btn-default',
                closeModal: true
            }
        }
    })
}

function c_notiAlert(txt){
    return swal({
        title: '알림',
        text: txt,
        icon: 'success',
        buttons: {
            confirm: {
                text: 'Confirm',
                value: true,
                visible: true,
                className: 'btn btn-success',
                closeModal: true
            }
        }
    })
}

function c_showErrorAlert(e) {
    if(e.status == 401) e.responseText = "권한 없음 오류입니다. (로그인 유효시간 초과 또는 접근불가 메뉴)";
    swal({
        title: e.status + " ERROR",
        icon: 'warning',
        text: e.responseText ? e.responseText : "",
        buttons: {
            confirm: {
                text: 'Confirm',
                value: true,
                visible: true,
                className: 'btn btn-warning',
                closeModal: true
            }
        }
    }).then(function(result){
        if (e.status == 401 || e.status == 403) {
            location.href = '/login';
        }
    });
}