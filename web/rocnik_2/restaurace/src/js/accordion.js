const acc = document.getElementsByClassName("accordion");
let panelHeights = [];

for (let i = 0; i < acc.length; i++) {
    let panel = acc[i].nextElementSibling;
    panelHeights[i] = panel.offsetHeight;
    panel.style.maxHeight = "0px";
    acc[i].addEventListener("click", function () {
        acc[i].classList.toggle("active");
        panel.classList.toggle("active");
        if (panel.style.maxHeight === "0px") {
            panel.style.maxHeight = panelHeights[i] + "px";
        } else {
            panel.style.maxHeight = "0px";
        }
    });
}
