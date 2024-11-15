$(document).ready(function () {
    $('#search-button').click(function () {
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();

        if (!startDate || !endDate) {
            alert("시작 날짜와 종료 날짜를 모두 입력해 주세요.");
            return;
        }

        $.ajax({
            url: `/manager/filterOrders`,
            type: 'GET',
            data: { startDate, endDate },
            success: function (data) {
                const resultBody = $('#result-body');
                resultBody.empty();

                if (data.length === 0) {
                    resultBody.append("<tr><td colspan='4' class='text-center'>조회 결과가 없습니다.</td></tr>");
                } else {
                    data.forEach(order => {
                        const row = `<tr data-order-no="${order.orderNo}">
                            <td>${order.orderNo}</td>
                            <td>${order.usersName}</td>
                            <td>${order.memberLevel}</td>
                            <td>${order.orderDate}</td>
                        </tr>`;
                        resultBody.append(row);
                    });
                }
            },
            error: function (err) {
                console.log('에러가 발생했습니다.', err);
            }
        });
    });

    $('#result-body').on('click', 'tr', function () {
        const orderNo = $(this).data('order-no');
        window.location.href = `/manager/detailSpecification?orderNo=${orderNo}`;
    });
});
