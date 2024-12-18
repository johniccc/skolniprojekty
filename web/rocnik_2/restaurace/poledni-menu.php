<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <?php include 'components/links.php'; ?>
  <title>Polední menu | Fabiano's Ristorante</title>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section style="background-image: url(src/img/pizza\ \(4\).webp);">
      <div id="poledni-menu" class="container feature">
        <h1>Polední menu</h1>
        <p>(11:00 - 14:00)</p>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="sidebar">
          <a class="link-anim" href="#predkrmy">Předkrmy</a>
          <a class="link-anim" href="#hlavni-chody">Hlavní chody</a>
        </div>
        <div class="menu">
          <div id="predkrmy" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Předkrmy</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Křupavé kroužky kalmaru (Calamari Fritti)</h3>
                  <p>Křupavé smažené kroužky kalmaru s citronovou majonézou a čerstvým citrónem.</p>
                </div>
                <div class="price">
                  <p>160 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Caprese salát</h3>
                  <p>Čerstvé plátky rajčat a mozzarelly s bazalkou a extra panenským olivovým olejem.</p>
                </div>
                <div class="price">
                  <p>140 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Hovězí carpaccio</h3>
                  <p>Tenké plátky hovězího masa marinované v citronovém dresingu, podávané s rukolou a parmazánem.</p>
                </div>
                <div class="price">
                  <p>150 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Arancini</h3>
                  <p>Italské rýžové kuličky plněné sýrem, smažené dozlatova, podávané s domácí rajčatovou omáčkou.</p>
                </div>
                <div class="price">
                  <p>130 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Grilované krevety s česnekem</h3>
                  <p>Krevety grilované s česnekem a bylinkami, podávané na čerstvém salátu.</p>
                </div>
                <div class="price">
                  <p>180 Kč</p>
                </div>
              </div>
              <img src="src/img/predkrm.webp" alt="">
            </div>
          </div>
          <div id="hlavni-chody" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Hlavní chody</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Filetto di Branzino al Limone</h3>
                  <p>Filet z mořského vlka s citronovou omáčkou a olivami.</p>
                </div>
                <div class="price">
                  <p>240 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Kuřecí pokrm alla Cacciatora</h3>
                  <p>Kuřecí maso dusené s rajčaty, cibulí, olivami a bylinkami.</p>
                </div>
                <div class="price">
                  <p>200 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Ossobuco alla Milanese</h3>
                  <p>Kost v telecím hovězím masem, vařená ve víně s citronem a petrželkou.</p>
                </div>
                <div class="price">
                  <p>280 Kč</p>
                </div>
              </div>
              <img src="src/img/spaghetti (4).webp" alt="">
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>