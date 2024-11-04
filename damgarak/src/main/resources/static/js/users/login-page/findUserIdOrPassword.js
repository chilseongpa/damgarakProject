/**
 * 
 */

    function findIdBtn() {
        const userName = document.querySelector('.user-name-chk').value;
        const userMail = document.querySelector('.user-mail-chk').value;

        $.ajax({
            url: '/findUserId',
            type: 'GET',
            data: {
                userName: userName,
                userMail: userMail
            },
            dataType: 'text',
            success: function(result) {
                if (result === null || result === "") {
                    alert('조회된 ID가 없습니다.');
                } else {
                    alert('가입된 회원님의 ID는 ' + result + '입니다.');
                }
            },
            error: function() {
                alert('아이디 조회 중 오류가 발생했습니다.');

            }
        });
    }

   function findIdPwd(){

    const userName = document.querySelector('#find-pwd-name').value;
    const userId = document.querySelector('.user-id-chk').value;
    const userMail = document.querySelector('#find-pwd-mail').value;

    $.ajax({
        url: '/findPwd',
        type: 'POST',
        dataType: 'text',
        data:{
            userName:userName,
            userId:userId,
            userMail:userMail
        },
        success:function(result){
        console.log("서버 응답:", result);
            alert(result);
        },
        error:function(err){
            alert('조회중 에러가 발생했습니다.');
        }
    });

   }
