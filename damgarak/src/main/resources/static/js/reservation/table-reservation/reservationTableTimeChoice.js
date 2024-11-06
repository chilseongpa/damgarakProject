document.addEventListener("DOMContentLoaded", () => {
    generateCalendar();     
    setupTimeButtons();
    updateArrowVisibility(); // 초기 버튼 가시성 설정
});

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();

let selectedDayButton = null; 
let selectedTimeButton = null;
const maxOffset = 2; // 현재 달 기준 최대 2개월 앞까지 이동 가능

function generateCalendar() {
    const calendar = document.getElementById("calendar");
    const currentDate = new Date();
    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    const maxDate = new Date();
    maxDate.setDate(currentDate.getDate() + 30); // 오늘 기준 30일 뒤까지 예약 가능

    calendar.innerHTML = `
        <div class="calendar-header">
            <button id="left-arrow" class="arrow-button left-arrow" onclick="changeMonth(-1)">&lt;</button>
            <h3>${currentYear}년 ${monthNames[currentMonth]}</h3>
            <button id="right-arrow" class="arrow-button right-arrow" onclick="changeMonth(1)">&gt;</button>
        </div>
        <div class="calendar-grid"></div>`;

    const grid = calendar.querySelector(".calendar-grid");

    const dayNames = ["일", "월", "화", "수", "목", "금", "토"];
    dayNames.forEach(day => {
        const dayHeader = document.createElement("div");
        dayHeader.className = "calendar-day-name";
        dayHeader.innerText = day;
        grid.appendChild(dayHeader);
    });

    const firstDay = new Date(currentYear, currentMonth, 1).getDay();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

    for (let i = 0; i < firstDay; i++) {
        const emptyCell = document.createElement("div");
        emptyCell.className = "calendar-empty";
        grid.appendChild(emptyCell);
    }
    for (let day = 1; day <= daysInMonth; day++) {
        const date = new Date(currentYear, currentMonth, day);
        const dayButton = document.createElement("button");
        dayButton.className = "calendar-day";
        dayButton.innerText = day;

        if (date >= currentDate && date <= maxDate) {
            dayButton.addEventListener("click", () => selectDate(currentYear, currentMonth, day));
        } else {
            dayButton.disabled = true;
            dayButton.classList.add("disabled-day");
        }
        grid.appendChild(dayButton);
    }

    // 새 달력을 생성할 때마다 버튼 가시성 업데이트
    updateArrowVisibility();
}

function changeMonth(offset) {
    let targetMonth = currentMonth + offset;
    let targetYear = currentYear;

    // 목표 달과 연도를 계산하여 최대 2개월 이상 이동하지 않도록 제한 설정
    if (targetMonth < 0) {
        targetYear -= 1;
        targetMonth = 11;
    } else if (targetMonth > 11) {
        targetYear += 1;
        targetMonth = 0;
    }

    const minMonth = today.getMonth(); 
    const minYear = today.getFullYear();
    const maxMonth = (today.getMonth() + maxOffset) % 12;
    const maxYear = today.getFullYear() + Math.floor((today.getMonth() + maxOffset) / 12);

    // 현재 달 이전으로 이동 불가
    if (targetYear < minYear || (targetYear === minYear && targetMonth < minMonth)) {
        return;
    }

    // 현재 달 기준 최대 2개월 이후로 이동 불가
    if (targetYear > maxYear || (targetYear === maxYear && targetMonth > maxMonth)) {
        return;
    }

    currentMonth = targetMonth;
    currentYear = targetYear;

    selectedDayButton = null;
    generateCalendar();
}

function updateArrowVisibility() {
    const leftArrow = document.getElementById("left-arrow");
    const rightArrow = document.getElementById("right-arrow");

    // 현재 날짜 기준으로 두 달 앞까지만 이동 가능하게 조정
    const minMonth = today.getMonth(); 
    const minYear = today.getFullYear();
    const maxMonth = (today.getMonth() + maxOffset) % 12;
    const maxYear = today.getFullYear() + Math.floor((today.getMonth() + maxOffset) / 12);

    // 현재 달 이전으로 이동 불가 시 왼쪽 화살표 숨기기
    if (currentYear === minYear && currentMonth === minMonth) {
        leftArrow.style.visibility = "hidden";
    } else {
        leftArrow.style.visibility = "visible";
    }
    
    // 현재 달 기준 2개월 이후로 이동 불가 시 오른쪽 화살표 숨기기
    if (currentYear === maxYear && currentMonth === maxMonth) {
        rightArrow.style.visibility = "hidden";
    } else {
        rightArrow.style.visibility = "visible";
    }
}

function selectDate(year, month, day) {
    const formattedDate = `${year}-${(month + 1).toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
    document.getElementById("date").value = formattedDate;

    if (selectedDayButton) {
        selectedDayButton.classList.remove("selected-day");
    }
    const calendarDays = document.querySelectorAll(".calendar-day");
    calendarDays.forEach(dayButton => {
        if (parseInt(dayButton.innerText) === day) {
            dayButton.classList.add("selected-day");
            selectedDayButton = dayButton;
        }
    });
}
let confirmState = null

document.getElementById("reserveBtn").addEventListener("click", () => {
    const selectedDate = document.getElementById("date").value;
    const selectedTime = selectedTimeButton ? selectedTimeButton.value : null;

    if (!selectedDate || !selectedTime) {
        alert("날짜와 시간을 모두 선택해 주세요.");
    } else {
        confirmState =  confirm(`선택한 예약 날짜: ${selectedDate}\n선택한 예약 시간: ${selectedTime}\n현재 날짜로 진행하겠습니까?`);

    	 if (confirmState) {
            
            
            window.location.href = `/reservation/table-reservation/tableChoicePage?date=${encodeURIComponent(selectedDate)}&time=${encodeURIComponent(selectedTime)}`;
        }
    }
});
function setupTimeButtons() {
    document.querySelectorAll(".time-button").forEach(button => {
        button.addEventListener("click", () => {
            if (selectedTimeButton) {
                selectedTimeButton.classList.remove("selected-time");
            }
            button.classList.add("selected-time");
            selectedTimeButton = button;
        });
    });
}

