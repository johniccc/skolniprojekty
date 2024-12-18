(function logoSlider() {
  const strip = document.querySelector('#carousel #strip')

  const slideCount = strip.childElementCount;

  const stripWidth = slideCount * 250;
  const animationLength = slideCount * 1.2;

  strip.style.width = stripWidth + 'px';
  strip.style.animationName = 'scroll';
  strip.style.animationDuration = animationLength + 's';
  strip.style.animationTimingFunction = 'linear';
  strip.style.animationIterationCount = 'infinite';
})();

/*import { animate, inView, stagger } from "motion";
  
animate(
  ".header-text-container",
  { y:[-100, 0], opacity: [0, 1] },
  { duration: 1 }
);
animate(
  ".header-img-container",
  { x:[100, 0], opacity: [0, 1] },
  { duration: 1, delay: 1 }
);

inView(".boxes", ({target}) => {
  animate(target.querySelectorAll(".box"), 
  {opacity: [0, 1]},
  {duration: 1.2, delay: stagger(0.4, { start: 0.2 })})
});

inView(".search-engine-boxes", ({target}) => {
  animate(target.querySelectorAll(".search-engine-box"), 
  {opacity: [0, 1]},
  {duration: 1, delay: stagger(0.5, { start: 0.2 })})
});

inView("#promo", ({target}) => {
  animate(target.querySelectorAll(".promo-bg"), 
  {opacity: [0, 1]},
  {duration: 3})
});*/