

function cancelReservation(reservationNo){
    const cencelState = confirm('정말로 예약을 취소하시겠습니까?');

    if(cencelState){
        $.ajax({
            url: '/cancelReservation',
            data: {
                reservationNo :reservationNo
            },
            type: 'post',
            
            success:function(result){
                if(result === 'ok'){
                    alert('예약이 취소되었습니다.');
                    location.reload();
                }else{
                    alert('예약 취소가 실패되었습니다.\n 다시 시도해주세요.');
                }
            },
            error:function(error){
                console.log(error);
            }
        });
    }
}

function updateReservation(reservationNo){
    const updateState = confirm('예약을 변경하시겠습니까?');
    
    if(updateState){
        location.href = `/updateReservation?reservationNo=${encodeURIComponent(reservationNo)}`    
    }
}