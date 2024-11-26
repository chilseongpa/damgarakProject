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
        let dateA = new Date(a.cells[3].textContent.trim()); // 3번째 셀에서 날짜 문자열 가져오기
        let dateB = new Date(b.cells[3].textContent.trim()); // 3번째 셀에서 날짜 문자열 가져오기
        
        // 날짜를 비교하여 정렬 (오름차순 또는 내림차순)
        return isTimeAsc ? dateA - dateB : dateB - dateA;
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
// Ajax를 통해 댓글을 전송하고 저장 후 화면에 표시
function addReply() {
  const replyContent = $("#content").val().trim();
  
  if (replyContent.length > 0) {
      $.ajax({
          url: '/manager/insertReply',  // 서버로 댓글 데이터를 전송할 엔드포인트
          type: 'POST',
          contentType: 'application/json',
          data: JSON.stringify({
              postNo: getPostNo(),
              replyComment: replyContent
          }),
          success: function(response) {
              if (response.status === 'success') {
                  $("#content").val(''); // 입력창 초기화
                  addNewReplyToDOM(response.replyData); // 새 댓글을 DOM에 추가
                  alert('댓글 추가에 실패했습니다.');
              } else {
                  alert('댓글 추가에 성공했습니다.');
              }
          },
          error: function(err) {
              console.log('댓글 추가 요청 실패');
              console.log(err);
          }
      });
  } else {
      alert("내용을 입력 후 추가 가능합니다.");
  }
}

// 새 댓글을 화면에 추가하는 함수
function addNewReplyToDOM(replyData) {
  const newReplyDiv = document.createElement("div");
  newReplyDiv.setAttribute("style", "display: flex; justify-content: space-between;");

  const userNameSpan = document.createElement("span");
  userNameSpan.setAttribute("style", "text-align: left; flex: 1;");
  userNameSpan.textContent = replyData.usersName;

  const commentSpan = document.createElement("span");
  commentSpan.setAttribute("style", "text-align: center; flex: 1;");
  commentSpan.textContent = replyData.replyComment;

  const dateSpan = document.createElement("span");
  dateSpan.setAttribute("style", "text-align: right; flex: 1;");
  dateSpan.textContent = new Date(replyData.creationDate).toLocaleString();

  newReplyDiv.appendChild(userNameSpan);
  newReplyDiv.appendChild(commentSpan);
  newReplyDiv.appendChild(dateSpan);

  document.getElementById("replyList").appendChild(newReplyDiv);
}

// URL에서 postNo 추출하는 함수
function getPostNo() {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get('postNo');
}

function deleteEmployee() {
  const userId = document.getElementById("userId").value;
  console.log('확인용 : ', userId);

  $.ajax({
      url: '/manager/fireEmployee',
      type: 'get',
      data:{
        usersId: userId,
      },
      success:function(result){
        if(result === 'success'){
          alert('해고 성공입니다.');
        }else{
          alert('해고 실패입니다.');
        }
      },
      error:function(error){
        alert(error);
      }
  
  })
  
  }
  
  
document.addEventListener('DOMContentLoaded', () => {
    function changePassword() {
        const currentPassword = document.getElementById('currentPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        // 비밀번호 조건 검증
        if (!validatePassword(newPassword)) {
            alert('비밀번호는 10자 이상 15자 이하이며, 영문, 숫자, 특수문자를 하나 이상 포함해야 합니다.');
            return;
        }

        $.ajax({
            url: '/checkPassword',
            data: {
                password: currentPassword,
            },
            type: 'post',
            success: function (result) {
                if (result === 'true') {
                    updateUserPassword(newPassword, confirmPassword);
                } else {
                    alert('잘못된 비밀번호입니다.');
                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    }

    // 버튼 클릭 이벤트 바인딩
    document.querySelector('.btn-danger').addEventListener('click', changePassword);
});


function updateUserPassword(password, passwordCheck) {
    if (password !== passwordCheck) {
        alert('변경 비밀번호와 비밀번호 확인 값이 다릅니다.');
    } else {
        $.ajax({
            url: '/updatePassword',
            type: 'post',
            data: {
                password: password
            },
            success: function(result) {
                if (result === 'success') {
                    alert('비밀번호가 변경되었습니다.');
                } else {
                    alert('비밀번호 변경에 실패했습니다. 다시 시도해주세요.');
                }
            },
            error: function(err) {
                console.log(err);
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    }
}

function validatePassword(password) {
    const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,15}$/;
    return regex.test(password);
}