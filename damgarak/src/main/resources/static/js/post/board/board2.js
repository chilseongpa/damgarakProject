document.addEventListener("DOMContentLoaded", () => {
  // 메뉴 파일 불러오기
  
      // 메뉴 파일이 로드된 후 버튼과 메뉴 선택자 설정
      let btn = document.querySelector("#bttn");
      let menu = document.querySelector("#nav");

      // 버튼 클릭 이벤트 설정
      btn.addEventListener("click", () => {
        btn.classList.toggle("active");
        menu.classList.toggle("active");
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

document.addEventListener("DOMContentLoaded", () => {
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

document.addEventListener("DOMContentLoaded", () => {
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

document.addEventListener("DOMContentLoaded", () => {
  document.addEventListener("scroll", function () {
    const sections = document.querySelectorAll("section div");

    sections.forEach((section) => {
      const rect = section.getBoundingClientRect();
      if (rect.top < window.innerHeight && rect.bottom > 0) {
        section.classList.add("section-visible");
      }
    });
  });
})

function updateUser() {
      const urlParams = new URLSearchParams(window.location.search);
      const usersId = urlParams.get('usersId'); // URL의 usersId 파라미터 값 가져오기
      const usersName = document.getElementById('userName').value;
      const email = document.getElementById('email').value;
      const jobCode = document.getElementById('jobCode').value;

      fetch('/manager/updateUser', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json'
          },
          body: JSON.stringify({
              usersId: usersId,
              usersName: usersName,
              email: email,
              jobCode: jobCode
          })
      })
      .then(response => response.text())
      .then(data => {
          if(data === "ok") {
              alert('정보가 성공적으로 수정되었습니다.');
              window.location.href = '/manager/empDetails?usersId=' + usersId;
          }
      })
      .catch(error => {
          console.error('오류 발생:', error);
          alert('정보 수정에 실패했습니다.');
      });
  }
function updatePassword() {
    const currentPassword = document.getElementById("currentPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (newPassword !== confirmPassword) {
        alert("새 비밀번호가 일치하지 않습니다.");
        return;
    }

    fetch('/manager/updatePassword', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ newPassword: newPassword })
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
    })
    .catch(error => console.error('Error:', error));
}

function addReply() {
    const replyComment = document.getElementById("content").value;

    // URL에서 postNo 값을 동적으로 가져옴
    const urlParams = new URLSearchParams(window.location.search);
    const postNo = urlParams.get("postNo");

    if (!postNo) {
        alert("게시글 번호를 찾을 수 없습니다.");
        return;
    }

    if (replyComment.trim() === "") {
        alert("댓글을 입력해주세요.");
        return;
    }

    fetch("/manager/insertReply", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            postNo: postNo,
            replyComment: replyComment
        })
    })
    .then(response => response.text())
    .then(data => {
        if (data === "success") {
            document.getElementById("content").value = "";
            fetchReplies(postNo); // 댓글 목록 갱신
        } else {
            alert("댓글 등록에 실패했습니다.");
        }
    })
    .catch(error => console.error("Error:", error));
}
function fetchReplies(postNo) {
    fetch(`/manager/suggestDetail?postNo=${postNo}`)
    .then(response => response.json())
    .then(replyList => {
        const replyArea = document.getElementById("replyArea");
        replyArea.innerHTML = ""; // 댓글 목록 초기화

        // 댓글 목록을 순회하며 각 댓글을 HTML로 추가
        replyList.forEach(reply => {
            const replyRow = document.createElement("div");
            replyRow.style.display = "flex";
            replyRow.style.justifyContent = "space-between";

            replyRow.innerHTML = `
                <span style="text-align: left; flex: 1;">${reply.usersName}</span>
                <span style="text-align: center; flex: 1;">${reply.replyComment}</span>
                <span style="text-align: right; flex: 1;">${new Date(reply.creationDate).toLocaleString()}</span>
            `;
            
            replyArea.appendChild(replyRow);
        });
    })
    .catch(error => console.error("Error fetching replies:", error));
}

function submitNotice() {
  const noticeTitle = document.getElementById("noticeTitle").value;
  const noticeContent = document.getElementById("noticeContent").value;

  // 유효성 검사 (필요 시 추가)
  if (!noticeTitle || !noticeContent) {
    alert("제목과 내용을 입력해 주세요.");
    return;
  }

  // 공지사항 데이터를 JSON 형식으로 서버에 전송
  fetch('/manager/insertNotice', { // 변경된 경로
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({
        noticeTitle: noticeTitle,
        noticeContent: noticeContent
    })
	})
  .then(response => response.text())
  .then(result => {
    if (result === 'success') {
      alert("공지사항이 등록되었습니다.");
      location.reload(); // 페이지를 새로고침하여 등록 완료 표시
    } else {
      alert("공지사항 등록에 실패했습니다.");
    }
  })
  .catch(error => console.error('Error:', error));
}
