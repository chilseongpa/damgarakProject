document.addEventListener("DOMContentLoaded", () => {
    // URL에서 결제 성공 정보를 가져와 화면에 표시
    const urlParams = new URLSearchParams(window.location.search);
    const amount = urlParams.get("amount");

    // 결제 금액 표시
    document.getElementById("amount").textContent = `${amount}원`;

    // 주문한 메뉴 가져오기 및 표시
    const orderList = JSON.parse(localStorage.getItem("orderList")) || [];
    const orderedMenu = orderList.map(item => item.menuName).join(", ");
    document.getElementById("orderedMenu").textContent = orderedMenu;

    // 주문번호 관리: 이전 주문번호가 있으면 고정, 없으면 +1 증가
    let lastOrderId = parseInt(localStorage.getItem("lastOrderId"), 10) || 1;
    const currentOrderId = localStorage.getItem("currentOrderId");

    // 현재 주문번호가 없을 때만 lastOrderId 사용하여 고정
    if (!currentOrderId) {
        localStorage.setItem("currentOrderId", lastOrderId);
        localStorage.setItem("lastOrderId", lastOrderId + 1);
    }

    // 주문번호 표시
    document.getElementById("orderId").textContent = String(localStorage.getItem("currentOrderId")).padStart(5, '0');
});
