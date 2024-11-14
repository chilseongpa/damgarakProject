onload = () => {
    var modal = document.getElementById("myModal");
    var btn = document.getElementById("openModal");
    var span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        modal.style.display = "block";
    };

    span.onclick = function() {
        modal.style.display = "none";
    };

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
};
function changePassword() {
    const password = document.getElementById('nowPassword').value;
    const updatePassword = document.getElementById('updatePassword').value;
    const checkPassword = document.getElementById('updatePasswordCheck').value;

    // 비밀번호 조건 검증
    if (!validatePassword(updatePassword)) {
        alert('비밀번호는 10자 이상 15자 이하이며, 영문, 숫자, 특수문자를 하나 이상 포함해야 합니다.');
        return;
    }

    $.ajax({
        url: '/checkPassword',
        data: {
            password: password,
        },
        type: 'post',
        success: function(result) {
            if (result === 'true') {
                updateUserPassword(updatePassword, checkPassword);
            } else {
                alert('잘못된 비밀번호입니다.');
            }
        },
        error: function(err) {
            console.log(err);
        }
    });
}

function updateUserPassword(password, passwordCheck) {
    if (password !== passwordCheck) {
        alert('변경 비밀번호와 비밀번호 확인 값이 다릅니다.');
    } else {
        $.ajax({
            url: '/updatePassword',
            type: 'post',
            data: {
                password: password
            },
            success: function(result) {
                if (result === 'success') {
                    alert('비밀번호가 변경되었습니다.');
                } else {
                    alert('비밀번호 변경에 실패했습니다. 다시 시도해주세요.');
                }
            },
            error: function(err) {
                console.log(err);
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    }
}
function validatePassword(password) {
    const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,15}$/;
    return regex.test(password);
}