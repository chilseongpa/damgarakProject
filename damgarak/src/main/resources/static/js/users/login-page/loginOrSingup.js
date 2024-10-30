onload = function () {
    const signUpButton = document.getElementById('signUp');
    const signInButton = document.getElementById('signIn');
    const container = document.getElementById('container');

    signUpButton.addEventListener('click', () => {
        container.classList.add("right-panel-active");
    });
    signInButton.addEventListener('click', () => {
        container.classList.remove("right-panel-active");
    });

    document.getElementById('signupForm').onsubmit = function (event) {
        const userName = document.getElementById("userName").value;
        const userPassowrd = document.getElementById("userPassword").value;
        const usernamePattern = /^[a-zA-Z0-9]{8,15}$/;

        if (!usernamePattern.test(userName)) {
            alert('아이디는 8자 이상 15자 이하의 영문 또는 숫자만 가능합니다');
            event.preventDefault();
            return;
        }
        const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\W_]).{10,15}$/;
        if (!passwordPattern.test(userPassowrd)) {
            alert('비밀번호 10자 이상 15자 이하로 영문, 숫자, 특수문자를 포함해서');
            event.preventDefault();
        }
    };

    document.getElementById('emailCheckBtn').addEventListener('click', function (event) {
        event.preventDefault();
    });



    document.querySelector('.email-btn').onclick = () => {
        const UsersEmail = document.getElementById('usersEmail').value;

        $.ajax({
            url: 'mail',   // 요청 방식(type:method)
            type: 'post',  //요청 주소(url)
            data: {         // 요청 시 전달 데이터(파라미터) (data)
                email: UsersEmail
            },
            success: (result) => {
                alert(result);
            },
            error: (err) => {
                console.log(err);
            }
        });
    };

    document.querySelector('.auth-email-btn').onclick = () => {
        const usersEmail = document.getElementsByName('email')[0].value;
        const userAuthCode = document.getElementById('authCode').value;
        $.ajax({
            type: 'post',
            url: '/check',
            data: {
                code: userAuthCode,
                email: usersEmail
            },
            success: (data) => {
                alert(data);
            },
            error: (err) => {
                console.log(err);
            }
        });
    };

}


function pwdCheck() {
    const pwd = document.getElementById('usersPassword').value;
    const pwdCheck = document.getElementById('passwordCheck').value;

    if (pwd !== pwdCheck) {
        alert('비밀번호가 다릅니다');
        return false;
    }
}

let isIdChecked = false;

function idCheck() {
    const checkId = document.getElementById('usersId').value;
    const messageElement = document.getElementById('idCheckMessage');

    if (!checkId) {
        messageElement.textContent = "아이디를 입력해주세요.";
        isIdChecked = false;
        return;
    }
    $.ajax({
        url: '/idCheck.me',
        type: 'get',
        data: { usersId: checkId },
        success: function (result) {
            if (result === 'false') {
                alert('이미 사용 중인 아이디 입니다.');
                isIdChecked = false;
            } else {
                alert('사용 가능한 아이디 입니다.');
                isIdChecked = true;
            }
        },
        error: function (err) {
            console.log(err);
            isIdChecked = false;
        }
    });
}