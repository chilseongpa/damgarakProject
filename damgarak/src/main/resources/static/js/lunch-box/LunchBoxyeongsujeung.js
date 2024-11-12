// LunchBoxyeongsujeung.js

document.addEventListener("DOMContentLoaded", () => {
    // URL에서 결제 성공 정보를 가져와 화면에 표시
    const urlParams = new URLSearchParams(window.location.search);
    const amount = urlParams.get("amount");
    document.getElementById("amount").textContent = `${amount}원`;

    // 선택한 도시락 타입 가져오기 및 표시
    const selectedLunchboxType = localStorage.getItem("selectedLunchboxType") || "선택된 도시락 없음";
    document.getElementById("orderedMenu").textContent = `주문한 메뉴: ${selectedLunchboxType}`;

    // 주문한 메뉴 요약 가져오기 및 표시
    const orderList = JSON.parse(localStorage.getItem("orderSummary")) || [];
    const orderedMenu = orderList.map(item => `${item.menuName} x ${item.quantity}`).join(", ");
    document.getElementById("orderedMenu").textContent = `${selectedLunchboxType} - ${orderedMenu}`;

    // 주문번호 생성
    let lastOrderId = parseInt(localStorage.getItem("lastOrderId"), 10) || 1;
    let currentOrderId = localStorage.getItem("currentOrderId");

    if (!currentOrderId) {
        localStorage.setItem("currentOrderId", lastOrderId);
        localStorage.setItem("lastOrderId", lastOrderId + 1);
    }

    // 주문번호 표시
    document.getElementById("orderId").textContent = String(localStorage.getItem("currentOrderId")).padStart(5, '0');
});
