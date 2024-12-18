<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <?php include 'components/links.php'; ?>
  <link href="https://unpkg.com/photoswipe@5.2.2/dist/photoswipe.css" rel="stylesheet">
  <title>Galerie | Fabiano's Ristorante</title>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section style="background-image: url(src/img/obecne\ \(1\).webp); background-position: 50% 20%">
      <div class="container feature">
        <h1>Galerie</h1>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="pswp-gallery" id="my-gallery">
          <a href="src/img/galerie/bruschetta (1).webp" data-pswp-width="1800" data-pswp-height="1800" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/bruschetta (1).webp" alt="" />
          </a>
          <a href="src/img/galerie/dezerty (2).webp" data-pswp-width="2400" data-pswp-height="1729" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/dezerty (2).webp" alt="" />
          </a>
          <a href="src/img/galerie/chef.webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/chef.webp" alt="" />
          </a>
          <a href="src/img/galerie/inside.webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/inside.webp" alt="" />
          </a>
          <a href="src/img/galerie/outside (2).webp" data-pswp-width="1202" data-pswp-height="1800" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/outside (2).webp" alt="" />
          </a>
          <a href="src/img/galerie/outside.webp" data-pswp-width="1202" data-pswp-height="1800" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/outside.webp" alt="" />
          </a>
          <a href="src/img/galerie/outside-night.webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/outside-night.webp" alt="" />
          </a>
          <a href="src/img/galerie/pasta-making.webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/pasta-making.webp" alt="" />
          </a>
          <a href="src/img/galerie/pizza (1).webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/pizza (1).webp" alt="" />
          </a>
          <a href="src/img/galerie/pizza (2).webp" data-pswp-width="1800" data-pswp-height="1800" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/pizza (2).webp" alt="" />
          </a>
          <a href="src/img/galerie/pizza (3).webp" data-pswp-width="1362" data-pswp-height="1800" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/pizza (3).webp" alt="" />
          </a>
          <a href="src/img/galerie/pizza (5).webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/pizza (5).webp" alt="" />
          </a>
          <a href="src/img/galerie/pizza.webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/pizza.webp" alt="" />
          </a>
          <a href="src/img/galerie/spaghetti (1).webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/spaghetti (1).webp" alt="" />
          </a>
          <a href="src/img/galerie/steak.webp" data-pswp-width="2400" data-pswp-height="1600" target="_blank">
            <img class="gallery-thumbnail" src="src/img/galerie/scaled/steak.webp" alt="" />
          </a>
        </div>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
  <script type="module">
    import PhotoSwipeLightbox from 'https://unpkg.com/photoswipe/dist/photoswipe-lightbox.esm.js';

    const lightbox = new PhotoSwipeLightbox({
      gallery: '#my-gallery',
      children: 'a',
      pswpModule: () => import('https://unpkg.com/photoswipe'),
    });

    lightbox.init();
  </script>
</body>

</html>