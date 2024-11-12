document.addEventListener("DOMContentLoaded", () => {
    // URL에서 결제 성공 정보를 가져와 화면에 표시
    const urlParams = new URLSearchParams(window.location.search);
    const amount = urlParams.get("amount");
    document.getElementById("amount").textContent = `${amount}원`;

    // 선택한 도시락 타입 가져오기 및 표시
    const selectedLunchboxType = localStorage.getItem("selectedLunchboxType") || "선택된 도시락 없음";
    const orderList = JSON.parse(localStorage.getItem("orderSummary")) || [];

    // 주문한 메뉴 요약 가져오기 및 표시
    let orderedMenu = orderList.map(item => `${item.menuName} x ${item.quantity}`).join(", ");
    if (orderedMenu) {
        orderedMenu = `${selectedLunchboxType}, ${orderedMenu}`; // 도시락 타입과 메뉴 요약을 콤마로 구분
    } else {
        orderedMenu = selectedLunchboxType; // 주문한 메뉴가 없으면 도시락 타입만 표시
    }
    document.getElementById("orderedMenu").textContent = orderedMenu;

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
