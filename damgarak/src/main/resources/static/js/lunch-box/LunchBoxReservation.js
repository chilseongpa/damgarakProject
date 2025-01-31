document.addEventListener("DOMContentLoaded", () => {
    generateCalendar();     
    setupTimeButtons();
    updateArrowVisibility(); 

    // 초기화 버튼 클릭 시 선택 초기화
    document.getElementById("cancelBtn").addEventListener("click", resetSelection);

    // 예약 버튼 클릭 시 예약 페이지로 이동
    document.getElementById("reserveBtn").addEventListener("click", () => {
        const selectedDate = document.getElementById("date").value;
        const selectedTime = selectedTimeButton ? selectedTimeButton.value : null;

        if (!selectedDate || !selectedTime) {
            alert("날짜와 시간을 모두 선택해 주세요.");
        } else {
            const confirmState = confirm(`선택한 예약 날짜: ${selectedDate}\n선택한 예약 시간: ${selectedTime}\n예약을 진행하시겠습니까?`);
            if (confirmState) {
                window.location.href = "choiceLunchBox"; // 선택한 도시락 페이지로 이동
            }
        }
    });
});

let today = new Date();
let currentMonth = today.getMonth();
let currentYear = today.getFullYear();
let selectedDayButton = null; 
let selectedTimeButton = null;
const maxOffset = 2;

function generateCalendar() {
    const calendar = document.getElementById("calendar");
    const currentDate = new Date();
    const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
    const maxDate = new Date();
    maxDate.setDate(currentDate.getDate() + 30);

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
    updateArrowVisibility();
}

function changeMonth(offset) {
    let targetMonth = currentMonth + offset;
    let targetYear = currentYear;

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

    if (targetYear < minYear || (targetYear === minYear && targetMonth < minMonth)) {
        return;
    }

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

    const minMonth = today.getMonth(); 
    const minYear = today.getFullYear();
    const maxMonth = (today.getMonth() + maxOffset) % 12;
    const maxYear = today.getFullYear() + Math.floor((today.getMonth() + maxOffset) / 12);

    leftArrow.style.visibility = (currentYear === minYear && currentMonth === minMonth) ? "hidden" : "visible";
    rightArrow.style.visibility = (currentYear === maxYear && currentMonth === maxMonth) ? "hidden" : "visible";
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

function resetSelection() {
    document.getElementById("date").value = "";
    if (selectedDayButton) selectedDayButton.classList.remove("selected-day");
    if (selectedTimeButton) selectedTimeButton.classList.remove("selected-time");
    selectedDayButton = null;
    selectedTimeButton = null;
}

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
