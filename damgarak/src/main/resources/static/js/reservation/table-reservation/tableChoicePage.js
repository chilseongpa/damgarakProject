/**
 * 
 */
let date = null;
let time = null;
let tableValue = null;

document.addEventListener("DOMContentLoaded", function() {
 
    const urlParams = new URLSearchParams(window.location.search);
    
     date = urlParams.get("date"); // 예: "2024-11-21"
     time = urlParams.get("time"); // 예: "15:00"

  
    console.log("선택한 날짜:", date);
    console.log("선택한 시간:", time);


    document.getElementById("selectedDate").textContent = date;
    document.getElementById("selectedTime").textContent = time;
});

 
function tableReservationHandle(){

    $.ajax({
        url: "/tableReservation",
        type: 'get',
        data:{
            time: time,
            date: date,
            tableNo: tableValue
        },
        success:function(result){
            if(result === 'ok'){
                alert(`${date} 일 ${time} ${tableValue} 예약을 성공했습니다.`);
        }else{
            alert(`${date} 일 ${time} ${tableValue} 예약 실패했습니다 다시 시도해주십시오.`);
        }    
    },
    error:function(err){
        alert('오류가 발생했습니다');
    }

    });
}
 
document.addEventListener("DOMContentLoaded", function() {
    const buttons = document.querySelectorAll("button");

    buttons.forEach(button => {
        button.addEventListener("click", function() {
          
            buttons.forEach(btn => {
                const pTags = btn.querySelectorAll("p");
                pTags.forEach(p => {
                    p.style.color = ""; 
                });
            });

            
            const pTags = button.querySelectorAll("p");
            pTags.forEach(p => {
                p.style.color = "red"; 
            });
            tableValue = button.value;
            let tableChoice = confirm(`${date} ${time}시 날짜로 \n선택된 테이블 ${tableValue}로 예약을 하시겠습니까?`);
            
            if(tableChoice){
                tableReservationHandle();
            }
         	
            
        });
    });
});
