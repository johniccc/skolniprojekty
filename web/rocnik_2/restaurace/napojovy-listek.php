<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <?php include 'components/links.php'; ?>
  <title>Nápojový lístek | Fabiano's Ristorante</title>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section style="background-image: url(src/img/wine\ \(2\).webp);">
      <div class="container feature">
        <h1>Nápojový lístek</h1>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="sidebar">
          <a class="link-anim" href="#teple-napoje">Teplé nápoje</a>
          <a class="link-anim" href="#studene-napoje">Studené nápoje</a>
          <a class="link-anim" href="#alkoholicke-napoje">Alkoholické nápoje</a>
          <a class="link-anim" href="#destilaty">Destiláty</a>
          <a class="link-anim" href="#bezalkoholicke-napoje">Bezalkoholické nápoje</a>
        </div>
        <div class="menu">
          <div id="teple-napoje" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Teplé nápoje</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Espresso</h3>
                </div>
                <div class="price">
                  <p>45 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Cappuccino</h3>
                </div>
                <div class="price">
                  <p>55 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Latte</h3>
                </div>
                <div class="price">
                  <p>60 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Čaj</h3>
                  <p>(různé druhy)</p>
                </div>
                <div class="price">
                  <p>40 Kč</p>
                </div>
              </div>
              <img src="src/img/coffee.webp" alt="">
            </div>
          </div>
          <div id="studene-napoje" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Studené nápoje</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Limonáda</h3>
                </div>
                <div class="price">
                  <p>50 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Ledový čaj</h3>
                </div>
                <div class="price">
                  <p>55 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Smoothie</h3>
                  <p>(různé ovoce)</p>
                </div>
                <div class="price">
                  <p>65 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Mléčné koktejly</h3>
                  <p>(různé příchutě)</p>
                </div>
                <div class="price">
                  <p>70 Kč</p>
                </div>
              </div>
              <img style="aspect-ratio: 1/1; object-fit: cover" src="src/img/lemonade.webp" alt="">
            </div>
          </div>
          <div id="alkoholicke-napoje" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Alkoholické nápoje</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Víno</h3>
                  <p>(bílé, červené, růžové)</p>
                </div>
                <div class="price">
                  <p>120 Kč / sklenka</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Prosecco</h3>
                </div>
                <div class="price">
                  <p>150 Kč / sklenka</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Aperol Spritz</h3>
                </div>
                <div class="price">
                  <p>180 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Pivo</h3>
                  <p>(různé druhy)</p>
                </div>
                <div class="price">
                  <p>70 Kč / půllitr</p>
                </div>
              </div>
              <img style="aspect-ratio: 1/1; object-fit: cover" src="src/img/wine.webp" alt="">
            </div>
          </div>
          <div id="destilaty" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Destiláty</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Grappa</h3>
                </div>
                <div class="price">
                  <p>90 Kč / panák</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Limoncello</h3>
                </div>
                <div class="price">
                  <p>80 Kč / panák</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Amaretto</h3>
                </div>
                <div class="price">
                  <p>100 Kč / panák</p>
                </div>
              </div>
              <img src="src/img/rum.webp" alt="">
            </div>
          </div>
          <div id="bezalkoholicke-napoje" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Bezalkoholické nápoje</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Minerální voda</h3>
                  <p>(perlivá/neperlivá)</p>
                </div>
                <div class="price">
                  <p>35 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Džus</h3>
                  <p>(různé příchutě)</p>
                </div>
                <div class="price">
                  <p>45 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Coca-Cola / Sprite / Fanta</h3>
                </div>
                <div class="price">
                  <p>40 Kč</p>
                </div>
              </div>
              <img src="src/img/juice.webp" alt="">
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>