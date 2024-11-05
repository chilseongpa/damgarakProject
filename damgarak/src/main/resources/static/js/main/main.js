onload = function(){
let currentSection = 0;
const sections = document.querySelectorAll(".section");

function scrollToSection(sectionIndex) {
  sections[sectionIndex].scrollIntoView({ behavior: "smooth" });
}

window.addEventListener("wheel", (event) => {
  if (event.deltaY > 0 && currentSection < sections.length - 1) {
    currentSection++;
  } else if (event.deltaY < 0 && currentSection > 0) {
    currentSection--;
  }
  scrollToSection(currentSection);
});
}

