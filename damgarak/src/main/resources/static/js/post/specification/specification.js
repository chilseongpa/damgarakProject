$(document).ready(function () {

    $('#search-button').click(function () {
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();

        if (!startDate || !endDate) {
            alert("시작 날짜와 종료 날짜를 모두 입력해 주세요.");
            return;
        }

  
        fetchOrders(startDate, endDate, 1);
    });


    $(document).on('click', '.pagination .page-link', function (e) {
        e.preventDefault();

        const page = $(this).data('page');
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();

        fetchOrders(startDate, endDate, page);
    });

 
    function fetchOrders(startDate, endDate, page) {
        $.ajax({
            url: `/manager/filterOrders`,
            type: 'GET',
            data: { startDate, endDate, page },
            success: function (response) {
                const resultBody = $('#result-body');
                const paginationContainer = $('.pagination');
                const { pageInfo, orders } = response;

             
                resultBody.empty();

                if (orders.length === 0) {
                    resultBody.append("<tr><td colspan='4' class='text-center'>조회 결과가 없습니다.</td></tr>");
                } else {
                    orders.forEach(order => {
                        const row = `<tr data-order-no="${order.orderNo}">
                            <td>${order.orderNo}</td>
                            <td>${order.usersName}</td>
                            <td>${order.memberLevel}</td>
                            <td>${order.orderDate}</td>
                        </tr>`;
                        resultBody.append(row);
                    });
                }

                paginationContainer.empty();

                paginationContainer.append(`
                    <li class="page-item ${pageInfo.currentPage === 1 ? 'disabled' : ''}">
                        <a class="page-link" href="#" data-page="${pageInfo.currentPage - 1}">Previous</a>
                    </li>
                `);

                for (let i = pageInfo.startPage; i <= pageInfo.endPage; i++) {
                    paginationContainer.append(`
                        <li class="page-item ${pageInfo.currentPage === i ? 'active' : ''}">
                            <a class="page-link" href="#" data-page="${i}">${i}</a>
                        </li>
                    `);
                }

                paginationContainer.append(`
                    <li class="page-item ${pageInfo.currentPage === pageInfo.maxPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" data-page="${pageInfo.currentPage + 1}">Next</a>
                    </li>
                `);
            },
            error: function (err) {
                console.log('에러가 발생했습니다.', err);
            }
        });
    }

    $('#result-body').on('click', 'tr', function () {
        const orderNo = $(this).data('order-no');
        window.location.href = `/manager/detailSpecification?orderNo=${orderNo}`;
    });
});

document.addEventListener("DOMContentLoaded", () => {
    let btn = document.querySelector("#bttn");
    let menu = document.querySelector("#nav");

    btn.addEventListener("click", () => {
        btn.classList.toggle("active");
        menu.classList.toggle("active");
    });
});
