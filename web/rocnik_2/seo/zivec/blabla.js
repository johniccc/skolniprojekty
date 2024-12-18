<script src="
https://cdn.jsdelivr.net/npm/@splidejs/splide-extension-auto-scroll@0.5.3/dist/js/splide-extension-auto-scroll.min.js
"></script>

<script>
  const speed = 12;
  function timeOutFunction() {
    timeout = setTimeout(SliderInit, 300);
  }
  function SliderInit() {
    new Splide( '.auto-slide1', {
  autoScroll: {
    speed: speed,
  },
} ).mount( window.splide.Extensions);
    new Splide( '.auto-slide2', {
  autoScroll: {
    speed: speed,
  },
} ).mount( window.splide.Extensions);
    clearTimeout(timeout);
  }
  
  timeOutFunction();
</script>