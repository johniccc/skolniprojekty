function divLink(el) {
    /*document.getElementById(el).scrollIntoView({
        behavior: "smooth",
        block: "center",
        inline: "center",
    });*/
    var element = document.getElementById(el);
    window.scrollTo({
        behavior: "smooth",
        top:
            element.offsetTop -
            (window.innerHeight - element.offsetHeight) / 2 -
            50,
    });
}

const animinViewport = (entries, observer) => {
    entries.forEach((entry) => {
        //entry.target.classList.add(entry.isIntersecting ? "is-inViewport" : "");
        entry.target.classList.toggle("is-inViewport", entry.isIntersecting);
    });
};

const changeNav = (entries, observer) => {
    entries.forEach((entry) => {
        if (entry.isIntersecting) {
            document.querySelector(".active").classList.remove("active");
            var id = entry.target.getAttribute("id");
            var newLink = document
                .getElementById(id + "btn")
                .classList.add("active");
        }
    });
};

const obsOptions = {
    rootMargin: "-50% 0px -50% 0px",
};

const divObs = new IntersectionObserver(changeNav, obsOptions);
const animObs = new IntersectionObserver(animinViewport);

const ele = document.querySelectorAll(".imp");
ele.forEach((EL) => {
    divObs.observe(EL);
});

const anim = document.querySelectorAll("[data-inviewport]");
anim.forEach((EL) => {
    animObs.observe(EL);
});
