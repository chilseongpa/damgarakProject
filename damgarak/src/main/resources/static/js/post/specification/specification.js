function searchByDate() {
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;

    if (startDate && endDate) {
        console.log(`조회 날짜: ${startDate} ~ ${endDate}`);
        alert(`조회 날짜: ${startDate} ~ ${endDate}`);

    } else {
        alert('시작 날짜와 종료 날짜를 모두 선택해주세요.');
    }
}