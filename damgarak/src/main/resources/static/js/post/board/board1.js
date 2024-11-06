document.addEventListener("DOMContentLoaded", () => {
  // 메뉴 파일 불러오기
  fetch("menu.html")
    .then(response => response.text())
    .then(data => {
      document.getElementById("menu-container").innerHTML = data;

      // 메뉴 파일이 로드된 후 버튼과 메뉴 선택자 설정
      let btn = document.querySelector("#bttn");
      let menu = document.querySelector("#nav");

      // 버튼 클릭 이벤트 설정
      btn.addEventListener("click", () => {
        btn.classList.toggle("active");
        menu.classList.toggle("active");
      });
    });
});

document.addEventListener("DOMContentLoaded", () => {
  new Chart(document.getElementById("bar-chart"), {
      type: 'bar',
      data: {
          labels: ["10월25일", "10월26일", "10월27일", "10월28일", "10월29일", "10월30일"],
          datasets: [
              {
                  label: "원",
                  backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#e8c3b9", "#c45850", "#3e84cd"],
                  data: [250000, 490000, 357000, 850000, 430000, 500000]
              }
          ]
      },
      options: {
          responsive: true,
          legend: { display: false },
          title: {
              display: true,
              text: '일별 매출'
          }
      }
  });
});

document.addEventListener("DOMContentLoaded", () => {
    const sortByOrderButton = document.getElementById("sort-by-order");
    const sortByTimeButton = document.getElementById("sort-by-time");
    const tableBody = document.querySelector("#reservation-table tbody");

      // 초기 정렬 방향 (true: 오름차순, false: 내림차순)
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
          isOrderAsc = !isOrderAsc; // 정렬 방향 반전
      }

      function sortTableByTime() {
          let rows = Array.from(tableBody.querySelectorAll("tr"));
          rows.sort((a, b) => {
              let timeA = a.cells[4].textContent.split(":").map(Number);
              let timeB = b.cells[4].textContent.split(":").map(Number);
              return isTimeAsc ? (timeA[0] - timeB[0] || timeA[1] - timeB[1]) : (timeB[0] - timeA[0] || timeB[1] - timeA[1]);
          });
          tableBody.append(...rows);
          isTimeAsc = !isTimeAsc; // 정렬 방향 반전
      }

      sortByOrderButton.addEventListener("click", sortTableByOrder);
      sortByTimeButton.addEventListener("click", sortTableByTime);
});

document.addEventListener("DOMContentLoaded", ()=>{
    document.querySelectorAll('table.fff tr').forEach((row, index) => {
        if (index === 0) return; // 첫 번째 헤더 행은 무시
        
        const checkbox = row.querySelector('input[type="checkbox"]');
        const select = row.querySelector('select');
        const imgCells = row.querySelectorAll('td img');
      
        // 체크박스 변경 이벤트
        checkbox.addEventListener('change', () => {
          if (checkbox.checked) {
            select.disabled = false; // 체크되면 선택 가능하게 설정
          } else {
            select.disabled = true;  // 체크 해제 시 선택 불가능하게 설정
            imgCells.forEach(img => img.style.display = 'none'); // 이미지 숨기기
          }
        });
      
        // 셀렉트박스 변경 이벤트
        select.addEventListener('change', () => {
          const selectedValue = select.value;
          if (selectedValue === "") {
            // "선택하세요" 선택 시 모든 이미지 숨기기
            imgCells.forEach(img => img.style.display = 'none');
          } else {
            imgCells.forEach(img => {
              if (img.alt === selectedValue) {
                img.style.display = 'inline'; // 선택된 이미지 보이기
              } else {
                img.style.display = 'none'; // 나머지 이미지 숨기기
              }
            });
          }
        });
      });
});

document.addEventListener("DOMContentLoaded",()=>{
    const recommendedInput = document.querySelector('#recommendedMenu'); // 현재 추천 메뉴 input
    let selectedMenus = []; // 선택된 메뉴명을 저장할 배열

document.querySelectorAll('table.fff tr').forEach((row, index) => {
  if (index === 0) return; // 첫 번째 헤더 행은 무시
  
  const checkbox = row.querySelector('input[type="checkbox"]');
  const select = row.querySelector('select');
  const imgCells = row.querySelectorAll('td img');
  const menuName = row.querySelector('td:nth-child(2)').textContent.trim(); // 메뉴명 가져오기

  // 체크박스 변경 이벤트
  checkbox.addEventListener('change', () => {
    if (checkbox.checked) {
      select.disabled = false; // 체크되면 선택 가능하게 설정
      if (!selectedMenus.includes(menuName)) {
        selectedMenus.push(menuName); // 선택된 메뉴명 배열에 추가
      }
    } else {
      select.disabled = true;  // 체크 해제 시 선택 불가능하게 설정
      imgCells.forEach(img => img.style.display = 'none'); // 이미지 숨기기
      selectedMenus = selectedMenus.filter(name => name !== menuName); // 배열에서 메뉴명 제거
    }
    recommendedInput.value = selectedMenus.join(", "); // 현재 추천 메뉴에 선택된 메뉴명 표시
  });

  // 셀렉트박스 변경 이벤트
  select.addEventListener('change', () => {
    const selectedValue = select.value;
    if (selectedValue === "") {
      // "선택하세요" 선택 시 모든 이미지 숨기기
      imgCells.forEach(img => img.style.display = 'none');
    } else {
      imgCells.forEach(img => {
        if (img.alt === selectedValue) {
          img.style.display = 'inline'; // 선택된 이미지 보이기
        } else {
          img.style.display = 'none'; // 나머지 이미지 숨기기
        }
      });
    }
  });
});
})

document.addEventListener("DOMContentLoaded",()=>{
  document.addEventListener("scroll", function() {
    const sections = document.querySelectorAll("section div");
    
    sections.forEach((section) => {
        const rect = section.getBoundingClientRect();
        if (rect.top < window.innerHeight && rect.bottom > 0) {
            section.classList.add("section-visible");
        }
    });
});
})