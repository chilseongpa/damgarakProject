async function main() {
    const tossPayments = TossPayments("test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm");
    const widgets = tossPayments.widgets({ customerKey: "fs2_D6zylfludIaYSJ9pZ" });

    // localStorage에서 최종 가격 불러오기
    let finalPrice = parseInt(localStorage.getItem("finalPrice"), 10) || 100; // 기본값 100원 설정
    document.getElementById("priceDisplay").textContent = finalPrice; // 최종 가격 표시

    // 결제 금액 설정
    await widgets.setAmount({
        currency: "KRW",
        value: finalPrice
    });

    // 결제 UI 및 약관 UI 렌더링
    await Promise.all([
        widgets.renderPaymentMethods({
            selector: "#payment-method",
            variantKey: "DEFAULT",
        }),
        widgets.renderAgreement({ selector: "#agreement", variantKey: "AGREEMENT" })
    ]);

    // 결제하기 버튼 클릭 시 결제 요청
    document.getElementById("payment-button").addEventListener("click", async function () {
        const uniqueOrderId = "order_" + Date.now(); // 고유한 orderId 생성
        const amount = finalPrice;

        try {
            // 결제 요청
            await widgets.requestPayment({
                orderId: uniqueOrderId,
                orderName: "도시락 주문",
                successUrl: `${window.location.origin}/LunchBoxyeongsujeung?orderId=${uniqueOrderId}&amount=${amount}`,
                failUrl: window.location.origin + "/LunchBoxyeongsujeung.html",
            });
        } catch (error) {
            console.error("결제 요청에 실패했습니다.", error);
        }
    });
}

// main 함수 실행
main();
