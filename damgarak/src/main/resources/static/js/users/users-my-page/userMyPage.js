/**
 * 
 */
 
 onload = () =>{
        document.getElementById('openModal-div').addEventListener('click', function() {
        window.location.href = "/changeUserMyPage";
});
		var modal = document.getElementById("myModal");
		var btn = document.getElementById("openModal");
		var span = document.getElementsByClassName("close")[0];

		btn.onclick = function() {
			modal.style.display = "block";
		}

		span.onclick = function() {
			modal.style.display = "none";
		}

		window.onclick = function(event) {
			if (event.target == modal) {
				modal.style.display = "none";
			}
		}

        var modal = document.getElementById("myModal");
    var btn = document.getElementById("openModal");
    var span = document.getElementsByClassName("close")[0];

    btn.onclick = function() {
        modal.style.display = "block";
    }

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
    var tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
		var tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));

		var tooltipBtn = document.getElementById('tooltip-btn-style');
		tooltipBtn.setAttribute('data-bs-title', 
			'<strong>현재 회원님 혜택은</strong> <br> <span style="color: #FF5400; font-weight: bold;">할인율 5%</span>입니다. <br> <em>이용해주셔서 감사합니다.</em>'
		);

		
		tooltipList.forEach(function(tooltip) {
			tooltip.dispose();  
		});
	
		var tooltipListNew = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
}
  
 