function toggleNav() {
    const nav = document.getElementById("full-hamburger-nav");
    const navBtnOpen = document.querySelector(".menu-btn.open");
    const navBtnClosed = document.querySelector(".menu-btn.closed");
    nav.classList.toggle("active");
    navBtnOpen.classList.toggle("active");
    navBtnClosed.classList.toggle("active");
}

document.addEventListener("scroll", function () {
    const header = document.querySelector("header");
    if (window.scrollY > 100) {
        header.classList.add("scrolled");
    } else {
        header.classList.remove("scrolled");
    }
});
