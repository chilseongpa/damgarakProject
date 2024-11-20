$(document).ready(function() {
    const urlParams = new URLSearchParams(window.location.search);
    const orderNo = urlParams.get('orderNo');

    if (!orderNo) {
        alert("주문 번호가 유효하지 않습니다.");
        return;
    }

    $.ajax({
        url: `/manager/orderDetails`,
        type: 'GET',
        data: { orderNo: orderNo },
        success: function(orderDetails) {
            console.log("주문 상세 데이터:", orderDetails);

            $('#orderNo').text(orderDetails.orderNo || "정보 없음");
            $('#usersName').text(orderDetails.usersName || "정보 없음");

            const orderItems = orderDetails.items || [];
            const orderItemBody = $('#orderItemBody');
            orderItemBody.empty();

            if (orderItems.length > 0) {
                let totalPrice = 0;
                orderItems.forEach(item => {
                    const row = `
                        <tr>
                            <td>${item.menuName}</td>
                            <td>${item.menuCount}</td>
                            <td>${item.paymentMethod || "결제 정보 없음"}</td>
                            <td>${item.price}원</td>
                        </tr>
                    `;
                    orderItemBody.append(row);
                    totalPrice += item.price * item.menuCount;
                });
                $('#total-price').text(`${totalPrice}원`);
            } else {
                orderItemBody.html("<tr><td colspan='4' class='text-center'>주문 항목이 없습니다.</td></tr>");
            }
        },
        error: function(err) {
            console.error("주문 상세 정보를 가져오는 중 오류 발생:", err);
            alert("주문 정보를 불러오는 중 오류가 발생했습니다.");
        }
    });
});
