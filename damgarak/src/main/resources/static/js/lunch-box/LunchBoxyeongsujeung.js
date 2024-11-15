document.addEventListener("DOMContentLoaded", () => {
    // URL에서 결제 성공 정보를 가져와 화면에 표시
    const urlParams = new URLSearchParams(window.location.search);
    const amount = urlParams.get("amount");
    const orderId = urlParams.get("orderId");

    if (amount && orderId) {
        // 결제 금액과 주문 번호를 화면에 표시
        document.getElementById("amount").textContent = `${amount} 원`;
        document.getElementById("orderId").textContent = orderId;
        document.getElementById("paymentStatus").textContent = "결제가 성공적으로 완료되었습니다!";
    } else {
        document.getElementById("paymentStatus").textContent = "결제 정보를 불러올 수 없습니다.";
    }

    // 선택한 도시락 타입과 주문 요약 가져오기
    const selectedLunchboxType = localStorage.getItem("selectedLunchboxType") || "선택된 도시락 없음";
    const orderList = JSON.parse(localStorage.getItem("orderSummary")) || [];

    // 주문한 메뉴 요약 가져오기 및 화면 표시
    let orderedMenu = orderList.map(item => `${item.menuName} x ${item.quantity}`).join(", ");
    if (orderedMenu) {
        orderedMenu = `${selectedLunchboxType}, ${orderedMenu}`;
    } else {
        orderedMenu = selectedLunchboxType; // 주문한 메뉴가 없을 경우 도시락 타입만 표시
    }
    document.getElementById("orderedMenu").textContent = orderedMenu;

    // 완료 버튼 클릭 시 메인 페이지로 이동
    document.getElementById("completeBtn").addEventListener("click", () => {
        window.location.href = "/index.html";
    });
    
});
