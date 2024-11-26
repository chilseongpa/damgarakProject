/**
 * 
 */
let date = null;
let time = null;
let tableValue = null;
let reservationNo = null;

document.addEventListener("DOMContentLoaded", function() {
 
    const urlParams = new URLSearchParams(window.location.search);
    
     date = urlParams.get("date"); 
     time = urlParams.get("time"); 

    console.log("선택한 날짜:", date);
    console.log("선택한 시간:", time);
    
    reservationNo = urlParams.get("reservationNo");

});


function tableReservationJson(){

    const dateTime = `${date} ${time}`;
    return{
         reservation:{
            reservationNo: reservationNo,
            reservationDate: dateTime, 
            reservationStatus: "y"
        },
         tableReservation:{
            tableNo: tableValue
         }
    }
};

function tableReservationHandle(){
     const reservationData	= tableReservationJson();
     console.log("전달되는 데이터 값 확인 ", reservationData);
     console.log("전달되는 데이터 값 확인 ", reservationNo);

     const url = reservationNo ? "/updateTable" : "/tableReservation";
     const methodType = reservationNo ? 'put' : 'post'; 


    $.ajax({
        url: url,
        type: methodType,
        contentType: 'application/json',
        data: JSON.stringify(reservationData),

        success:function(result){
            if(result === 'ok'){
                alert(`${date} 일 ${time} ${tableValue} 예약이 ${reservationNo ? '수정' : '성공'}되었습니다.`);
                 window.location.href = '/reservationInquiry';
        }else{
            alert(`${date} 일 ${time} ${tableValue} 예약 ${reservationNo ? '수정' : '생성'} 실패했습니다. 다시 시도해주십시오.`);
        }    
    },
    error:function(err){
        alert('오류가 발생했습니다', err);
    }
    });
}

// 새로 추가한 코드들.... 
// 기능은 이미 해당 날짜 해당 시간대에 저장된 테이블을 구별을 하기 위해서 
document.addEventListener("DOMContentLoaded", function(){
    const urlParams = new URLSearchParams(window.location.search);
    const date = urlParams.get("date");
    const time = urlParams.get("time");

    searchTableReservation(date, time);
})

function searchTableReservation(date, time){
    $.ajax({
        url: "/getSearchTableState",
        type: "get",
        data: {
            date: date,
            time: time
        },
        success:function(resultTable){
            const button = document.querySelectorAll("button");

            button.forEach(button =>{
                const tableNo = button.value;
                
                if(resultTable.includes(tableNo)){
                    button.disabled = true;
             
                    button.querySelectorAll("p").forEach(p => {
                        p.style.color = "#222"; 
                        p.style.textDecoration = "line-through"; 
                    });

                    button.classList.add("reserved");

                }else{
                    button.addEventListener("click", handleTableSelection);
                }

            });
        },
        error:function(err){
            console.log('에러가 발생했습니다.', err);  
        }

    });
}

function handleTableSelection(event) {
    const button = event.currentTarget;

    const buttons = document.querySelectorAll("button");
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
    console.log("선택한 테이블 ", tableValue);

    if (tableChoice) {
        tableReservationHandle();
    }
}



