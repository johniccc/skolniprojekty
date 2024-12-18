(function logoSlider() {
    const strip = document.querySelectorAll(".carousel .strip");
    for (let i = 0; i < strip.length; i++) {
        let slideCount = strip[i].childElementCount;

        let stripWidth = slideCount * 500;
        let animationLength = slideCount * 3;

        strip[i].style.width = stripWidth + "px";
        strip[i].style.animationName = "scroll";
        strip[i].style.animationDuration = animationLength + "s";
        strip[i].style.animationTimingFunction = "linear";
        strip[i].style.animationIterationCount = "infinite";
    }
})();
