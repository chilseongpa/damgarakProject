// 초기 메뉴 데이터 설정
let menuData = {}; // 서버에서 가져온 메뉴 데이터를 저장할 객체
let currentPage = 1;
const itemsPerPage = 6;
let orderList = [];
let bentoSelections = JSON.parse(localStorage.getItem("selectedBentoList")) || []; // 도시락 선택 정보

// 도시락별 메뉴 제한 설정
const menuLimits = {
    main: 1,
    soup: 1,
};

// 선택한 도시락 수에 따라 반찬 수 계산
function getSideLimit(bentoCount) {
    return bentoCount - menuLimits.main - menuLimits.soup;
}

// 메뉴를 화면에 렌더링하는 함수
function renderMenu(category) {
    const menuList = document.getElementById('menuList');
    menuList.innerHTML = ''; // 기존 메뉴 목록 초기화

    const itemsToDisplay = category === 'all' 
        ? Object.values(menuData).flat() 
        : menuData[category];
    
    const start = (currentPage - 1) * itemsPerPage;
    const end = start + itemsPerPage;
    const pagedItems = itemsToDisplay.slice(start, end);

    pagedItems.forEach(item => {
        const imgSrc = `/static/img/menu/menu-all-image/${item.menuImage}`; // 이미지 경로 설정
        const menuCard = document.createElement('div');
        menuCard.classList.add('menu-card');

        menuCard.innerHTML = `
            <img src="${imgSrc}" alt="${item.menuName}">
            <div class="menu-info">
                <h5>${item.menuName}</h5>
                <p class="price">${item.price.toLocaleString()} 원</p>
                <p class="calorie">${item.calorie} kcal</p>
                <button class="add-btn" onclick="addToOrder('${item.menuNo}', '${item.menuName}', ${item.price}, ${item.calorie}, '${imgSrc}', '${category}')">담기</button>
            </div>
        `;
        menuList.appendChild(menuCard);
    });

    updatePaginationButtons(itemsToDisplay.length);
}

// 카테고리 필터 기능
function filterCategory(category) {
    const allCategories = document.querySelectorAll('.category');
    allCategories.forEach(button => button.classList.remove('active'));

    const activeCategory = document.querySelector(`.category[onclick="filterCategory('${category}')"]`);
    if (activeCategory) activeCategory.classList.add('active');

    currentPage = 1; // 새로운 카테고리 선택 시 첫 페이지로 초기화
    renderMenu(category);
}

// 주문 목록에 메뉴 추가
function addToOrder(menuNo, menuName, price, calorie, img, category) {
    const selectedBento = bentoSelections[0]; // 첫 번째 도시락 기준으로 적용
    const bentoCount = parseInt(selectedBento.value[0], 10); // 3첩, 5첩, 7첩에서 숫자만 추출

    const mainLimit = menuLimits.main;
    const soupLimit = menuLimits.soup;
    const sideLimit = getSideLimit(bentoCount);

    // 각 메뉴의 선택 수량 제한 체크
    if (category === 'main' && orderList.filter(item => item.category === 'main').length >= mainLimit) {
        alert("메인 메뉴는 최대 1개만 선택할 수 있습니다.");
        return;
    }
    if (category === 'soup' && orderList.filter(item => item.category === 'soup').length >= soupLimit) {
        alert("국물 요리는 최대 1개만 선택할 수 있습니다.");
        return;
    }
    if (category === 'side' && orderList.filter(item => item.category === 'side').length >= sideLimit) {
        alert(`반찬은 최대 ${sideLimit}개만 선택할 수 있습니다.`);
        return;
    }

    const existingItem = orderList.find(item => item.menuNo === menuNo);

    if (existingItem) {
        existingItem.quantity++;
    } else {
        orderList.push({ menuName, price, calorie, img, category, quantity: 1 });
    }

    updateOrderSummary();
}

// 주문 요약을 업데이트하는 함수
function updateOrderSummary() {
    const orderListElement = document.getElementById('orderList');
    orderListElement.innerHTML = '';

    orderList.forEach(item => {
        const orderItem = document.createElement('div');
        orderItem.classList.add('order-item');

        orderItem.innerHTML = `
            <img src="${item.img}" alt="${item.menuName}">
            <div class="order-item-details">
                <span class="order-item-menuName">${item.menuName}</span>
                <div class="order-item-controls">
                    <button class="quantity-btn" onclick="decreaseQuantity('${item.menuName}')">-</button>
                    <span class="quantity">${item.quantity}</span>
                    <button class="quantity-btn" onclick="increaseQuantity('${item.menuName}')">+</button>
                </div>
            </div>
            <span class="price">${(item.price * item.quantity).toLocaleString()} 원</span>
        `;

        orderListElement.appendChild(orderItem);
    });

    updateTotals();
    updateBentoSummary();
}

// 주문 금액과 칼로리 계산
function updateTotals() {
    const totalWithoutTax = orderList.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    const totalCalories = orderList.reduce((sum, item) => sum + (item.calorie * item.quantity), 0);

    document.getElementById('total').textContent = `${totalWithoutTax.toLocaleString()} 원`;
    document.getElementById('totalCalories').textContent = `${totalCalories.toLocaleString()} kcal`;
}

// 도시락별 선택한 메뉴 요약 표시
function updateBentoSummary() {
    const bentoSummary = document.getElementById('bentoSummary');
    bentoSummary.innerHTML = '';

    bentoSelections.forEach(bento => {
        const bentoCount = parseInt(bento.value[0], 10);
        const selectedMain = orderList.find(item => item.category === 'main');
        const selectedSoup = orderList.find(item => item.category === 'soup');
        const selectedSides = orderList.filter(item => item.category === 'side');

        const summaryText = `
            ${selectedMain ? selectedMain.menuName : "메인 메뉴 없음"} +
            ${selectedSoup ? selectedSoup.menuName : "국물 요리 없음"} +
            ${selectedSides.length ? selectedSides.map(item => item.menuName).join(" + ") : "반찬 없음"}
        `;

        const bentoSummaryItem = document.createElement('p');
        bentoSummaryItem.textContent = `${bento.value} : ${summaryText}`;
        bentoSummary.appendChild(bentoSummaryItem);
    });
}

// 수량 증가
function increaseQuantity(menuName) {
    const item = orderList.find(item => item.menuName === menuName);
    if (item) {
        item.quantity++;
        updateOrderSummary();
    }
}

// 수량 감소
function decreaseQuantity(menuName) {
    const item = orderList.find(item => item.menuName === menuName);
    if (item && item.quantity > 1) {
        item.quantity--;
    } else {
        orderList = orderList.filter(item => item.menuName !== menuName);
    }
    updateOrderSummary();
}

// 페이지네이션 이전 버튼
function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        const activeCategory = document.querySelector('.category.active').getAttribute('onclick').split("'")[1];
        renderMenu(activeCategory);
    }
}

// 페이지네이션 다음 버튼
function nextPage() {
    const activeCategory = document.querySelector('.category.active').getAttribute('onclick').split("'")[1];
    const items = activeCategory === 'all' ? Object.values(menuData).flat() : menuData[activeCategory];
    
    if (currentPage < Math.ceil(items.length / itemsPerPage)) {
        currentPage++;
        renderMenu(activeCategory);
    }
}

// 페이지네이션 버튼 상태 업데이트
function updatePaginationButtons(totalItems) {
    document.getElementById('prevButton').disabled = currentPage === 1;
    document.getElementById('nextButton').disabled = currentPage === Math.ceil(totalItems / itemsPerPage);
}

// 사이드바 애니메이션 토글 기능
document.addEventListener("DOMContentLoaded", () => {
    const toggleButton = document.querySelector("#toggleButton");
    const sidebar = document.querySelector("#sidebar");

    if (toggleButton && sidebar) {
        toggleButton.addEventListener("click", () => {
            sidebar.classList.toggle("hidden");
        });
    } else {
        console.warn("Toggle button or sidebar not found.");
    }
});

// 초기화 버튼 기능
function resetOrder() {
    orderList = [];
    updateOrderSummary();
}

document.addEventListener("DOMContentLoaded", () => {
    const resetButton = document.querySelector('.reset-order-btn');
    if (resetButton) {
        resetButton.addEventListener('click', resetOrder);
    } else {
        console.warn("Reset button (.reset-order-btn) not found.");
    }
});

// 페이지 로드 시 기본 메뉴 렌더링
window.onload = function() {
    initMenuList((data) => {
        menuData = data;
        renderMenu('all');
        updateBentoSummary();
    });
};

// 서버에서 메뉴 데이터를 가져오는 함수
const initMenuList = (callback) => {
    fetch("/lunchBoxMenuList", {
        method: "get"
    })
    .then(response => response.json())
    .then(data => {
        callback(data);
    })
    .catch(error => console.error("데이터 가져오기 실패:", error));
};
