<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <?php include 'components/links.php'; ?>
  <title>Jídelní lístek | Fabiano's Ristorante</title>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section style="background-image: url(src/img/spaghetti\ \(3\).webp);">
      <div class="container feature">
        <h1>Jídelní lístek</h1>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="sidebar">
          <a class="link-anim" href="#predkrmy">Předkrmy</a>
          <a class="link-anim" href="#prvni-chody">První chody</a>
          <a class="link-anim" href="#hlavni-chody">Hlavní chody</a>
          <a class="link-anim" href="#salaty">Saláty</a>
          <a class="link-anim" href="#dezerty">Dezerty</a>
          <a class="link-anim" href="#prilohy">Přílohy</a>
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
                  <h3>Insalata Caprese</h3>
                  <p>Čerstvé plátky rajčat a mozzarelly s bazalkou a extra panenským olivovým olejem.</p>
                </div>
                <div class="price">
                  <p>160 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Carpaccio di Manzo</h3>
                  <p>Tenké plátky hovězího masa marinované v citronovém dresingu, podávané s rukolou a parmazánem.</p>
                </div>
                <div class="price">
                  <p>180 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Arancini</h3>
                  <p>Italské rýžové kuličky plněné sýrem, smažené dozlatova, podávané s domácí rajčatovou omáčkou.</p>
                </div>
                <div class="price">
                  <p>150 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Gamberi all'Aglio</h3>
                  <p>Krevety grilované s česnekem a bylinkami, podávané na čerstvém salátu.</p>
                </div>
                <div class="price">
                  <p>220 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Frittura di Calamari</h3>
                  <p>Smažené kalamáry s citronovou majonézou a čerstvým citrónem.</p>
                </div>
                <div class="price">
                  <p>190 Kč</p>
                </div>
              </div>
              <img src="src/img/bruschetta (2).webp" alt="">
            </div>
          </div>
          <div id="prvni-chody" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>První chody</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Risotto ai Funghi Porcini</h3>
                  <p>Rizoto s hříbky, Parmezánem a vínem.</p>
                </div>
                <div class="price">
                  <p>250 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Linguine alle Vongole</h3>
                  <p>Těstoviny s mušlemi, česnekem, petrželkou a bílým vínem.</p>
                </div>
                <div class="price">
                  <p>240 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Gnocchi al Gorgonzola</h3>
                  <p>Domácí bramborové gnocchi s omáčkou z gorgonzoly a smetany.</p>
                </div>
                <div class="price">
                  <p>230 Kč</p>
                </div>
              </div>

              <img src="src/img/spiz.webp" alt="">
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
                  <p>320 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Pollo alla Cacciatora</h3>
                  <p>Kuřecí maso dusené s rajčaty, cibulí, olivami a bylinkami.</p>
                </div>
                <div class="price">
                  <p>280 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Ossobuco alla Milanese</h3>
                  <p>Kost v telecím hovězím masem, vařená ve víně s citronem a petrželkou.</p>
                </div>
                <div class="price">
                  <p>380 Kč</p>
                </div>
              </div>
              <img src="src/img/pizza (2).webp" alt="">
            </div>
          </div>
          <div id="salaty" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Saláty</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Insalata di Rucola e Parmigiano</h3>
                  <p>Salát s rukolou, plátky Parmezánu, cherry rajčaty a balsamikovým dresinkem.</p>
                </div>
                <div class="price">
                  <p>150 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Insalata Greca</h3>
                  <p>Klasický řecký salát s okurkou, rajčaty, paprikou, olivami a fetou.</p>
                </div>
                <div class="price">
                  <p>170 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Insalata Mista</h3>
                  <p>Směs sezónní zeleniny s olivovým olejem a octem.</p>
                </div>
                <div class="price">
                  <p>120 Kč</p>
                </div>
              </div>
              <img src="src/img/salad.webp" alt="">
            </div>
          </div>
          <div id="dezerty" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Dezerty</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Panna Cotta con Frutti di Bosco</h3>
                  <p>Krémový dezert s lesním ovocem a malinovým sirupem.</p>
                </div>
                <div class="price">
                  <p>180 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Cannoli Siciliani</h3>
                  <p>Tradiční sicilské cukroví plněné ricottou a ovocem.</p>
                </div>
                <div class="price">
                  <p>160 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Gelato Misti</h3>
                  <p>Výběr z různých italských zmrzlin.</p>
                </div>
                <div class="price">
                  <p>130 Kč</p>
                </div>
              </div>
              <img src="src/img/dezerty (1).webp" alt="">
            </div>
          </div>
          <div id="prilohy" class="menu-category">
            <div class="title">
              <svg width="84" height="40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 27c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8M0 9c5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8 5.467 0 8.533-8 14-8 5.467 0 8.533 8 14 8" stroke="#C5AD89" />
              </svg>
              <h2>Přílohy</h2>
            </div>
            <div class="menu-list">
              <div class="menu-item">
                <div class="name">
                  <h3>Patate Arrosto</h3>
                  <p>Křupavě pečené brambory s rozmarýnem a olivovým olejem.</p>
                </div>
                <div class="price">
                  <p>90 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Verdure Grigliate</h3>
                  <p>Grilovaná zelenina marinovaná v bylinkovém oleji.</p>
                </div>
                <div class="price">
                  <p>110 Kč</p>
                </div>
              </div>
              <div class="menu-item">
                <div class="name">
                  <h3>Risotto ai Funghi</h3>
                  <p>Rýžová příloha s hříbky, smetanou a sýrem Parmezán.</p>
                </div>
                <div class="price">
                  <p>120 Kč</p>
                </div>
              </div>
              <img style="aspect-ratio: 1/1; object-fit: cover" src="src/img/priloha.webp" alt="">
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>