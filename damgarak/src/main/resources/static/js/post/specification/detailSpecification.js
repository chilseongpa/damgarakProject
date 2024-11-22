/* 사이드바 js */
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