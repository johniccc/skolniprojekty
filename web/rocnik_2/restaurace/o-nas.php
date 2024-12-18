<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <?php include 'components/links.php'; ?>
  <title>O nás | Fabiano's Ristorante</title>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section style="background-image: url(src/img/about-us.webp);">
      <div class="container feature">
        <h1>O nás</h1>
      </div>
    </section>
    <section class="row">
      <div class="container">
        <img src="src/img/dough.webp" alt="">
        <div class="text">
          <h2>Dlouholetá rodinná tradice</h2>
          <p>Už od roku 1965 naše restaurace láká hosty svým jedinečným prostředím a vynikající kuchyní. Od besedního klubu v minulosti až po moderní gastronomický ráj dnes, naše restaurace představuje spojení minulosti s přítomností. Přijďte a okuste atmosféru, která se rodí z více než padesáti let trvající rodinné tradice!</p>
        </div>
      </div>
    </section>
    <section class="row-with-bg">
      <div class="container">
        <div class="text">
          <h2>Akce ve Fabiano's jsou nezapomenutelné</h2>
          <p>Naše restaurace je renomovaným místem pro pořádání rautů, svateb a dalších společenských událostí. Navíc nabízíme špičkový catering a rozvoz jídla přímo k vám domů. Nezapomeňte si rezervovat stůl přes náš odkaz a zažijte jedinečnou atmosféru v Fabiano's!</p>
        </div>
        <img src="src/img/akce.webp" alt="">
      </div>
    </section>
    <section id="team">
      <div class="container">
        <h2>Naši kuchaři</h2>
        <div class="team-gallery">
          <div class="team-gallery-box">
            <img src="src/img/team/adam-novak.webp" alt="">
            <h3>Adam Novák</h3>
            <p>Manažer restaurace</p>
          </div>
          <div class="team-gallery-box">
            <img src="src/img/team/michal-svoboda.webp" alt="">
            <h3>Michal Svoboda</h3>
            <p>Šéfkuchař</p>
          </div>
          <div class="team-gallery-box">
            <img src="src/img/team/david-dvorak.webp" alt="">
            <h3>David Dvořák</h3>
            <p>Kuchař</p>
          </div>
          <div class="team-gallery-box">
            <img src="src/img/team/pavel-kovarik.webp" alt="">
            <h3>Pavel Kovařík</h3>
            <p>Kuchař (Pizzaiolo)</p>
          </div>
          <div class="team-gallery-box">
            <img src="src/img/team/lukas-marek.webp" alt="">
            <h3>Lukáš Marek</h3>
            <p>Kuchař (Sommelier)</p>
          </div>
        </div>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>