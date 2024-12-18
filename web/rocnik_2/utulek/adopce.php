<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Adopce | Lucky Paws</title>
  <link rel="stylesheet" href="style.css">
  <?php include 'components/favicon.php'; ?>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section class="hero hero-side">
      <div class="hero-introduction">
        <h1>Adopce</h1>
      </div>
      <img src="src/img/adopce-hero.webp" alt="">
    </section>
    <section class="adoption-list">
      <div class="list-head">
        <h2>Pejsci k adopci</h2>
        <div class="sort-options">
          <select name="filter" id="filter">
            <option value="alphabetical">Abecedně</option>
            <option value="price">Cena</option>
            <option value="age">Věk</option>
          </select>
          <select name="orderby" id="orderby">
            <option value="asc">Vzestupně</option>
            <option value="desc">Sestupně</option>
          </select>
        </div>
      </div>
      <div class="dog-list">
        <div class="dog-box">
          <img style="object-position: top;" src="src/img/adopce/luna.webp" alt="">
          <h3 class="name">Luna</h3>
          <p class="breed">Labrador retrívr</p>
          <div class="qualities">
            <div class="quality-box">
              <p class="quality-name">Věk</p>
              <p class="quality-value">3 roky</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Pohlaví</p>
              <p class="quality-value">fena</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Váha</p>
              <p class="quality-value">25 kg</p>
            </div>
          </div>
        </div>
        <div class="dog-box">
          <img src="src/img/adopce/jackrussel.webp" alt="">
          <h3 class="name">Max</h3>
          <p class="breed">Jack Russell teriér</p>
          <div class="qualities">
            <div class="quality-box">
              <p class="quality-name">Věk</p>
              <p class="quality-value">5 let</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Pohlaví</p>
              <p class="quality-value">pes</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Váha</p>
              <p class="quality-value">7 kg</p>
            </div>
          </div>
        </div>
        <div class="dog-box">
          <img src="src/img/adopce/zlatyretrivr.webp" alt="">
          <h3 class="name">Bella</h3>
          <p class="breed">Zlatý retrívr</p>
          <div class="qualities">
            <div class="quality-box">
              <p class="quality-name">Věk</p>
              <p class="quality-value">2 roky</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Pohlaví</p>
              <p class="quality-value">Fena</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Váha</p>
              <p class="quality-value">30 kg</p>
            </div>
          </div>
        </div>
        <div class="dog-box">
          <img src="src/img/adopce/ovcak.webp" alt="">
          <h3 class="name">Rocky</h3>
          <p class="breed">Německý ovčák</p>
          <div class="qualities">
            <div class="quality-box">
              <p class="quality-name">Věk</p>
              <p class="quality-value">4 roky</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Pohlaví</p>
              <p class="quality-value">Pes</p>
            </div>
            <div class="quality-box">
              <p class="quality-name">Váha</p>
              <p class="quality-value">40 kg</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="adoption-process">
      <h2>Proces adopce</h2>
      <div class="adoption-steps">
        <div class="step">
          <div>
            <div class="circle">1</div>
          </div>
          <div>
            <div class="title">Výběr a seznámení</div>
            <div class="caption">Prohlédněte si profily dostupných psů v našem útulku na našich webových stránkách nebo přímo v útulku. Vyberte si pejska, který vás zaujal, a seznamte se s jeho historií, charakterem a potřebami.</div>
          </div>
        </div>
        <div class="step">
          <div>
            <div class="circle">2</div>
          </div>
          <div>
            <div class="title">Žádost o adopci</div>
            <div class="caption">Vyplňte online žádost o adopci, která nám poskytne informace o vašem domově, životním stylu a schopnostech pejska se o vás postarat.</div>
          </div>
        </div>
        <div class="step">
          <div>
            <div class="circle">3</div>
          </div>
          <div>
            <div class="title">Konverzace a setkání</div>
            <div class="caption">Na základě vaší žádosti budeme s vámi konzultovat možnost adopce a odpovídat na vaše otázky. Domluvíme si schůzku, aby jste se osobně setkali s vybraným psem a ověřili si vzájemnou kompatibilitu.</div>
          </div>
        </div>
        <div class="step">
          <div>
            <div class="circle">4</div>
          </div>
          <div>
            <div class="title">Domovní Návštěva</div>
            <div class="caption">Po pozitivním setkání uspořádáme krátkou domovní návštěvu, abychom se ujistili, že váš domov je bezpečný a připravený pro nového člena rodiny.</div>
          </div>
        </div>
        <div class="step">
          <div>
            <div class="circle last">5</div>
          </div>
          <div>
            <div class="title">Adopce a podpora</div>
            <div class="caption">Pokud vše proběhne hladce, připravíme adopční smlouvu a poskytneme vám všechny potřebné informace o péči o nového člena rodiny. Po podepsání smlouvy můžete nového psího kamaráda přivést domů, kde budete dostávat podporu od našeho týmu v průběhu přizpůsobení.</div>
          </div>
        </div>
      </div>
    </section>
    <section class="cta-section">
      <div class="cta">
        <p>Máte zájem si pejska adoptovat?</p>
        <a class="link-orange" href="">Kontaktujte nás!</a>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>