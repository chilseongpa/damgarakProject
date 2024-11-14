
 onload =()=>{
 
var modal = document.getElementById("myModal");
var btn = document.getElementById("openModal");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
 }
function changePassword(){
    const  password = document.getElementById('nowPassword').value;
    const  updatePassword = document.getElementById('updatePassword').value;
    const  checkPassword = document.getElementById('updatePasswordCheck').value;
	
    $.ajax({
        url: '/checkPassword',
        data: {
            password:password,
        },
        type: 'post',
        success:function(result){
            if(result === 'true'){
                updateUserPassword(updatePassword, checkPassword);
            }else{
                alert('잘못된 비밀번호입니다.');
            }
        },
        error:function(err){
            console.log(err);
            
        }
    });
} 
function updateUserPassword(password, passwordCheck){
    if(password != passwordCheck){
        alert('변경 비밀번호와 비밀번호 확인 값이 다릅니다.');
    }else{
        $.ajax({
            url: '/updatePassword',
            type: 'post',
            data:{
                password: password
            },
            success:function(result){
                if(result === 'success'){
                    alert('비밀번호가 변경되었습니다.');
                }else{
                    alert('확인된 비밀번호가 다릅니다.');
                }
            },
            error:function(err){
                alert(err);
            }
        });
    }
    
}


