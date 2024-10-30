/**
 * 
 */
onload = function () {
		

         const lunchH1 = document.querySelector('.lunch-h1');
            const lunchBoxDiv = document.getElementsByClassName('order-lunch-box')[0]; 
            const lunchA = document.getElementById('lunch-a-order'); 
            const lunch = document.getElementsByClassName('lunch-box-comment')[0];
            window.addEventListener('scroll', function(){
                let value = window.scrollY;
                console.log("scrolltype" + value);
                if(value === 0){
                    lunchH1.style.animation = "lunchbox 2s ease-out forwards";
                    lunchBoxDiv.style.animation = "lunchbox-a 2s ease-out forwards"; 
                    lunchA.style.animation = "lunchbox-a 2s ease-out forwards"; 
                    lunch.style.animation = "lunch-box-comment 3s ease-out forwards";
                }else{
                    lunchH1.style.animation = "lunchreturn 2s ease-out forwards";
                    lunchBoxDiv.style.animation = "lunchreturn-a 2s ease-out forwards"; 
                    lunchA.style.animation = "lunchreturn-a 2s ease-out forwards"; 
                    lunch.style.animation = "lunch-box-comment-return 3s ease-out forwards";
                }
            });
          }
                 document.addEventListener("DOMContentLoaded", function () {
                const swiper = new Swiper(".mySwiper", {
                    slidesPerView: 3,
                    centeredSlides: true,
                    spaceBetween: 30,
                    pagination: {
                        el: ".swiper-pagination",
                        clickable: true,
                    },
                    autoplay: {
                        delay: 3000,
                        disableOnInteraction: false,
                    },
                    navigation: {
                        nextEl: ".swiper-button-next",
                        prevEl: ".swiper-button-prev",
                    },
                });
            });
            
            function checkBoxList() {
            const checkboxes = document.getElementsByClassName('select-menu-list');
            let bestOrSuggest = null;
            const menuList = [];

            for (let checkbox of checkboxes) {
                if (checkbox.checked) {
                    if(checkbox.type === 'radio' ){
                        bestOrSuggest = checkbox.value;
                    }else{
                        // 필요한 모든 태그 검출
                        menuList.push(checkbox.value);
                    }
                }
            }
            // AJAX로 서버에 위 필요한 태그에 맞는 데이터 요청
            // 요청에 의해 받은 response로 데이터 출력
            console.log("Selected values:", menuList);

            $.ajax({
                url: '/menuList',
                type: 'GET',
                data: {
                    selectMenu: menuList,
                    bestOrSuggest: bestOrSuggest
                },
                traditional: true, // 배열 데이터를 GET 방식으로 전송할 때 필요하다. 
                success:function(result){
                    const menuContainer = document.querySelector('.menu-con');
                    menuContainer.innerHTML = ''; 

                    result.array.forEach(menu => {
                       const item = `
                        <ul class="menu-board-list">
                        <li>
                            <div class="img">
                                <img src="${menu.menuImage}" alt="${menu.menuName}">
                            </div>
                            <div class="info">
                                <div class="ko-name">${menu.menuName}</div>
                                <div class="txt">${menu.menuDescription}</div>
                            </div>
                        </li>
                    </ul> 
                       `;
                       menuContainer.insertAdjacentHTML('beforeend', item);
        
                    });
                },
                error:function(err){
                    console.log(err);
                }

            });


        }