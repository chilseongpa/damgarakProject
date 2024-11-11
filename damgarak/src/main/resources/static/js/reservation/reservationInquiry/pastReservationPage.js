/**
 * 
 */
    document.addEventListener("DOMContentLoaded", function() {
        
        const form = document.querySelector("form");
        
        form.addEventListener("submit", function(event) {
            
            const selectedType = document.querySelector('input[name="select-list-type"]:checked');
            
            const selectedYear = document.querySelector('select[name="year"]').value;
            
            const selectedMonth = document.querySelector('select[name="month"]').value;
            
            if (!selectedType || !selectedYear || !selectedMonth) {
                event.preventDefault();  
                alert("모든 항목을 선택해 주세요.");
            }
        });
    });
 