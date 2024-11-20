document.addEventListener("DOMContentLoaded", () => {
    // 메뉴 파일 불러오기 및 메뉴 토글
    let btn = document.querySelector("#bttn");
    let menu = document.querySelector("#nav");

    if (btn && menu) {
        btn.addEventListener("click", () => {
            btn.classList.toggle("active");
            menu.classList.toggle("active");
        });
    }

    // 세션 정보 확인 후 데이터 로드
    const suggestionTableBody = document.querySelector(".table tbody");
    if (suggestionTableBody) {
        fetch("/emp/mySuggestData")
            .then(response => response.json())
            .then(data => {
                if (data.error) {
                    alert("로그인이 필요합니다.");
                } else {
                    // 데이터가 있으면 테이블에 렌더링
                    data.suggestions.forEach((suggestion, index) => {
                        const row = suggestionTableBody.insertRow();
                        row.insertCell(0).textContent = index + 1;
                        row.insertCell(1).innerHTML = `<a href="/manager/suggestDetail/${suggestion.postNo}">${suggestion.title}</a>`;
                        row.insertCell(2).textContent = suggestion.creationDate;
                    });
                }
            })
            .catch(error => console.error("Error loading suggestions:", error));
    }
});

// 추가한 기존의 정렬, 테이블 클릭, 이미지 전환 등의 기능
document.addEventListener("DOMContentLoaded", () => {
    const sortByOrderButton = document.getElementById("sort-by-order");
    const sortByTimeButton = document.getElementById("sort-by-time");
    const tableBody = document.querySelector("#reservation-table tbody");

    if (sortByOrderButton && sortByTimeButton && tableBody) {
        let isOrderAsc = true;
        let isTimeAsc = true;

        function sortTableByOrder() {
            let rows = Array.from(tableBody.querySelectorAll("tr"));
            rows.sort((a, b) => {
                let numA = parseInt(a.cells[0].textContent);
                let numB = parseInt(b.cells[0].textContent);
                return isOrderAsc ? numA - numB : numB - numA;
            });
            tableBody.append(...rows);
            isOrderAsc = !isOrderAsc;
        }

        function sortTableByTime() {
            let rows = Array.from(tableBody.querySelectorAll("tr"));
            rows.sort((a, b) => {
                let timeA = a.cells[4].textContent.split(":").map(Number);
                let timeB = b.cells[4].textContent.split(":").map(Number);
                return isTimeAsc ? (timeA[0] - timeB[0] || timeA[1] - timeB[1]) : (timeB[0] - timeA[0] || timeB[1] - timeA[1]);
            });
            tableBody.append(...rows);
            isTimeAsc = !isTimeAsc;
        }

        sortByOrderButton.addEventListener("click", sortTableByOrder);
        sortByTimeButton.addEventListener("click", sortTableByTime);
    }
});

// 체크박스 및 셀렉트 박스에 대한 처리
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll('table.fff tr').forEach((row, index) => {
        if (index === 0) return;

        const checkbox = row.querySelector('input[type="checkbox"]');
        const select = row.querySelector('select');
        const imgCells = row.querySelectorAll('td img');

        if (checkbox && select) {
            checkbox.addEventListener('change', () => {
                select.disabled = !checkbox.checked;
                imgCells.forEach(img => img.style.display = checkbox.checked ? 'inline' : 'none');
            });

            select.addEventListener('change', () => {
                const selectedValue = select.value;
                imgCells.forEach(img => img.style.display = (img.alt === selectedValue) ? 'inline' : 'none');
            });
        }
    });
});

// 추천 메뉴 관리
document.addEventListener("DOMContentLoaded", () => {
    const recommendedInput = document.querySelector('#recommendedMenu');
    let selectedMenus = [];

    if (recommendedInput) {
        document.querySelectorAll('table.fff tr').forEach((row, index) => {
            if (index === 0) return;

            const checkbox = row.querySelector('input[type="checkbox"]');
            const select = row.querySelector('select');
            const imgCells = row.querySelectorAll('td img');
            const menuName = row.querySelector('td:nth-child(2)').textContent.trim();

            if (checkbox && select) {
                checkbox.addEventListener('change', () => {
                    select.disabled = !checkbox.checked;
                    if (checkbox.checked && !selectedMenus.includes(menuName)) {
                        selectedMenus.push(menuName);
                    } else {
                        selectedMenus = selectedMenus.filter(name => name !== menuName);
                    }
                    recommendedInput.value = selectedMenus.join(", ");
                });

                select.addEventListener('change', () => {
                    const selectedValue = select.value;
                    imgCells.forEach(img => img.style.display = (img.alt === selectedValue) ? 'inline' : 'none');
                });
            }
        });
    }
});

// 스크롤 애니메이션 기능
document.addEventListener("DOMContentLoaded", () => {
    document.addEventListener("scroll", () => {
        const sections = document.querySelectorAll("section div");

        sections.forEach((section) => {
            const rect = section.getBoundingClientRect();
            if (rect.top < window.innerHeight && rect.bottom > 0) {
                section.classList.add("section-visible");
            }
        });
    });
});
