document.addEventListener("DOMContentLoaded", () => {
    // URL에서 결제 성공 정보를 가져와 화면에 표시
    const urlParams = new URLSearchParams(window.location.search);
    const amount = urlParams.get("amount");
    const orderId = urlParams.get("orderId");

    // 결제 상태 표시
    if (amount && orderId) {
        document.getElementById("amount").textContent = `${amount} 원`;
        document.getElementById("orderId").textContent = orderId;
        document.getElementById("paymentStatus").textContent = "결제가 성공적으로 완료되었습니다!";
    } else {
        document.getElementById("paymentStatus").textContent = "결제 정보를 불러올 수 없습니다.";
    }

    // 로컬 스토리지에서 선택된 도시락과 주문 정보 가져오기
    const selectedLunchboxType = localStorage.getItem("selectedLunchboxType") || "선택된 도시락 없음";
    const orderSummary = JSON.parse(localStorage.getItem("orderSummary")) || [];

    // 주문한 메뉴 요약 생성
    let orderedMenu = orderSummary.map(item => `${item.menuName} x ${item.quantity}`).join(", ");
    if (!orderedMenu) {
        orderedMenu = "주문한 메뉴가 없습니다.";
    }

    // 주문 정보 화면에 표시
    document.getElementById("orderedMenu").textContent = `${selectedLunchboxType}, ${orderedMenu}`;

    // 완료 버튼 클릭 시 메인 페이지로 이동
    document.getElementById("completeBtn").addEventListener("click", () => {
        window.location.href = "index.html";
    });
});
