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
            $('#orderNo').text(orderDetails.orderNo);
            $('#usersName').text(orderDetails.usersName);

            const orderItems = orderDetails.items || [];

            if (Array.isArray(orderItems)) {
                const orderItemBody = $('#orderItemBody');
                orderItemBody.empty();

                let totalPrice = 0;

                orderItems.forEach(item => {
                    const row = `<tr>
                        <td>${item.menuName}</td>
                        <td>${item.menuCount}</td>
                        <td>${item.paymentMethod}</td>
                        <td>${item.price}원</td>
                    </tr>`;
                    orderItemBody.append(row);
                    totalPrice += parseInt(item.price) * parseInt(item.menuCount);
                });

                $('#total-price').text(`${totalPrice}원`);
            } else {
                console.error("주문 항목이 배열이 아닙니다.");
                alert("주문 항목 데이터가 올바르지 않습니다.");
            }
        },
        error: function(err) {
            console.error("주문 상세 정보를 가져오는 중 오류 발생:", err);
            alert("주문 정보를 불러오는 중 오류가 발생했습니다. " + (err.responseText || err.statusText));
        }
    });
});
