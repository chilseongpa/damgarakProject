// 사용자가 선택한 도시락 목록을 저장할 배열
let selectedBentoList = [];

// 사이드바 슬라이드 효과 토글 기능
document.addEventListener("DOMContentLoaded", () => {
    const toggleButton = document.getElementById("toggleButton");
    const sidebar = document.getElementById("sidebar");
    const mainContent = document.querySelector(".main-content");

    toggleButton.addEventListener("click", () => {
        sidebar.classList.toggle("hidden");
        mainContent.classList.toggle("expanded");
    });

    // 페이지 로드 시 localStorage에서 선택 항목을 불러오기
    loadSelectedBento();
});

// 도시락 메뉴를 선택했을 때, 목록에 추가하는 함수
function addToOrder(selectedValue) {
    // 이미 선택한 도시락이 있는지 확인
    const existingItem = selectedBentoList.find(item => item.value === selectedValue);

    if (existingItem) {
        // 이미 선택한 도시락이면 수량 증가
        existingItem.count += 1;
    } else {
        // 새로운 도시락 항목 추가
        selectedBentoList.push({ value: selectedValue, count: 1 });
    }

    // 선택 항목을 화면에 업데이트
    updateSelectedBentoDisplay();
}

// 선택한 도시락을 화면에 표시하는 함수
function updateSelectedBentoDisplay() {
    const displayArea = document.getElementById("selectedBentoList");
    displayArea.innerHTML = ""; // 기존 내용을 초기화

    selectedBentoList.forEach(item => {
        const listItem = document.createElement("p");
        listItem.textContent = `${item.value} (수량: ${item.count}개)`;
        displayArea.appendChild(listItem);
    });

    // localStorage에 저장
    saveSelectedBento();
}

// localStorage에 선택 항목 저장
function saveSelectedBento() {
    localStorage.setItem("selectedBentoList", JSON.stringify(selectedBentoList));
}

// localStorage에서 선택 항목을 불러오는 함수
function loadSelectedBento() {
    const savedBentoList = JSON.parse(localStorage.getItem("selectedBentoList")) || [];

    if (savedBentoList.length > 0) {
        selectedBentoList = savedBentoList;
        updateSelectedBentoDisplay();
    }
}

// 초기화 - 선택 항목과 화면 초기화
function resetSelection() {
    selectedBentoList = []; // 도시락 리스트 초기화
    updateSelectedBentoDisplay(); // 화면에서 선택한 도시락 제거
    localStorage.removeItem("selectedBentoList"); // localStorage에서 선택 내역 제거
}

// 다음 단계로 이동 (선택한 도시락 수를 저장하고 bb.html로 이동)
function nextStep() {
    if (selectedBentoList.length < 1) {
        alert("최소 1개의 도시락을 선택해야 합니다.");
        return;
    }

    // 선택한 도시락 수 계산
    const totalBentoCount = selectedBentoList.reduce((count, item) => count + item.count, 0);
    localStorage.setItem("selectedBentoCount", totalBentoCount); // 총 도시락 수 저장

    // lunchBoxMenu.html로 이동
    window.location.href = "/lunchBoxMenu";

}

