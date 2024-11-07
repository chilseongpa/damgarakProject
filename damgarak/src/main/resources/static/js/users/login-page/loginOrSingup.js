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
        
    };
    
    document.getElementById('emailCheckBtn').addEventListener('click', function (event) {
        
        event.preventDefault();
       
    });
    
document.querySelector('.email-btn').onclick = () => {
    const usersEmail = document.getElementById('usersEmail').value;

    $.ajax({
        url: '/checkEmail',   
        type: 'GET',
        data: { email: usersEmail },
        success: (response) => {
            if (response === "사용불가") {
                alert('이미 사용 중인 이메일입니다.');
            } else {
                $.ajax({
                    url: '/mail',   // 인증 번호 발송 엔드포인트
                    type: 'POST',
                    data: { email: usersEmail },
                    success: (result) => {
                        alert('인증 번호가 발송되었습니다.');
                        startCountdown(document.querySelector('#authButton')); // 인증번호 발송 후 카운트다운 시작
                    },
                    error: (err) => {
                        console.log(err);
                    }
                });
            }
        },
        error: (err) => {
            console.log(err);
        }
    });
};

function startCountdown(buttonElement) {
    let time = 180; 
    let isCountingDown = true; // 카운트다운 진행 여부를 나타내는 플래그

    const countdownInterval = setInterval(() => {
        const minutes = Math.floor(time / 60);
        const seconds = time % 60;
        buttonElement.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`; 
        time--;

     
        if (isCountingDown) {
            buttonElement.onclick; 
        }

        // 타이머가 종료되면
        if (time < 0) {
            clearInterval(countdownInterval);
            buttonElement.textContent = "확인";
            buttonElement.onclick = function() { startCountdown(buttonElement); }; 
            isCountingDown = false; 
        }
    }, 1000);
}
document.querySelector('.auth-email-btn').onclick = () => {
    const usersEmail = document.getElementById('usersEmail').value;
    const userAuthCode = document.getElementById('authCode').value;

    $.ajax({
        type: 'POST',
        url: '/check',  
        data: JSON.stringify({ email: usersEmail, code: userAuthCode }), 
        contentType: 'application/json; charset=UTF-8',  
        success: (data) => {
            if (data === "success") {  // 서버 응답이 "success"일 때
                alert("이메일 인증이 완료되었습니다.");
            } else {
                alert("인증번호가 맞지 않습니다. 다시 확인해주세요.");
            }
        },
        error: (err) => {
            console.log(err);
            alert("인증 과정에서 오류가 발생했습니다. 다시 시도해주세요.");
        }
    });
};

}

// 추가한 부분
function pwdCheck(event) {
    const pwd = document.getElementById('usersPassword').value;
    const pwdCheck = document.getElementById('passwordCheck').value;

    // 비밀번호 패턴 검사
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\W_]).{10,15}$/;
    if (!passwordPattern.test(pwd)) {
        alert('비밀번호는 10자 이상 15자 이하로 영문, 숫자, 특수문자를 포함해야 합니다.');
        if (event) event.preventDefault();  // 폼 제출 방지
        return false;
    }

    // 비밀번호 확인 검사
    if (pwd !== pwdCheck) {
        alert('비밀번호가 다릅니다. 다시 입력해주세요.');
        if (event) event.preventDefault();  // 폼 제출 방지
        return false;
    } else {
        alert('비밀번호 확인이 완료되었습니다.');
        return true;
    }
}

// 폼 제출 이벤트에 연결
document.getElementById('signupForm').onsubmit = function(event) {
    return pwdCheck(event);  // pwdCheck 함수 호출, 조건에 따라 폼 제출 여부 결정
};

// 추가한 부분

let isIdChecked = false;

function idCheck(event) {
    const userName = document.getElementById('usersId').value;
    const usernamePattern = /^[a-zA-Z0-9]{8,15}$/;

    if (!usernamePattern.test(userName)) {
        alert('아이디는 8자 이상 15자 이하의 영문 또는 숫자만 가능합니다.');
        event.preventDefault();
        return;
    }

    // 아이디 중복 확인 AJAX 요청
    $.ajax({
        url: '/idCheck.me',
        type: 'get',
        data: { usersId: userName },
        success: function (result) {
            if (result === 'false') {
                alert('이미 사용 중인 아이디입니다.');
    
                isIdChecked = false;
            } else {
                alert('사용 가능한 아이디입니다.');
             
                isIdChecked = true;
            }
        },
        error: function (err) {
            console.log(err);
            isIdChecked = false;
        }
    });
}


