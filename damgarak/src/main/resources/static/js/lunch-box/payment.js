// debounce 함수 정의
function debounce(func, delay) {
    let timeout;
    return function(...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => func.apply(this, args), delay);
    };
}

async function main() {
    const tossPayments = TossPayments("test_gck_docs_Ovk5rk1EwkEbP0W43n07xlzm");
    const widgets = tossPayments.widgets({ customerKey: "fs2_D6zylfludIaYSJ9pZ" });

    let finalPrice = parseInt(localStorage.getItem("finalPrice"), 10) || 100;
    document.getElementById("priceDisplay").textContent = finalPrice;

    await widgets.setAmount({ currency: "KRW", value: finalPrice });

    await Promise.all([
        widgets.renderPaymentMethods({
            selector: "#payment-method",
            variantKey: "DEFAULT",
        }),
        widgets.renderAgreement({ selector: "#agreement", variantKey: "AGREEMENT" }),
    ]);

    // 디바운스를 적용한 결제 요청
    document.getElementById("payment-button").addEventListener("click", debounce(async function () {
        const uniqueOrderId = "order_" + Date.now();
        const amount = finalPrice;

        try {
            await widgets.requestPayment({
                orderId: uniqueOrderId,
                orderName: "도시락 주문",
                successUrl: `${window.location.origin}/LunchBoxyeongsujeung?orderId=${uniqueOrderId}&amount=${amount}`,
                failUrl: `${window.location.origin}/LunchBoxyeongsujeung.html`,
            });
        } catch (error) {
            console.error("결제 요청에 실패했습니다.", error);
        }
    }, 300)); // 300ms 디바운스
}

// main 실행
main();
