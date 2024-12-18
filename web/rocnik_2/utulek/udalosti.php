<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Události a zprávy | Lucky Paws</title>
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="calendar.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <?php include 'components/favicon.php'; ?>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section class="hero hero-side">
      <div class="hero-introduction">
        <h1>Události a zprávy</h1>
      </div>
      <img src="src/img/udalosti-hero.webp" alt="">
    </section>
    <section id="news" class="boxes">
      <h2>Nejnovější články</h2>
      <div class="box-container">
        <div class="box news-box">
          <img src="src/img/news-thumbnails/lan.jpg" alt="">
          <p class="tag">Akce</p>
          <h3 class="news-title">10. ročník psích her</h3>
          <p class="news-excerpt">V této chvíli se otevírají brány k jedinečnému jubilejnímu zážitku pro všechny milovníky čtyřnohých přátel – Psí hry slaví svůj desátý ročník! Od začátku této úžasné tradice...</p>
          <a class="read-more" href="">Čti více »</a>
        </div>
        <div class="box news-box">
          <img src="src/img/news-thumbnails/meat.jpg" alt="">
          <p class="tag">Recepty</p>
          <h3 class="news-title">Výborné stehenní stejky</h3>
          <p class="news-excerpt">Stehenní steaky v medově hořčičné marinádě představují dokonalý způsob, jak zpestřit váš stůl chutným a šťavnatým pokrmem. Tato receptura se neobejde...</p>
          <a class="read-more" href="">Čti více »</a>
        </div>
        <div class="box news-box">
          <img src="src/img/news-thumbnails/run.jpg" alt="">
          <p class="tag">Akce</p>
          <h3 class="news-title">Psí maraton</h3>
          <p class="news-excerpt">Každoročně se stává fenomenálním středem pozornosti pro milovníky psů - Psí maraton. Tato událost není jen běžným závodem, ale spíše oslavou pouti, odhodlání a nezdolného společenství mezi...</p>
          <a class="read-more" href="">Čti více »</a>
        </div>
      </div>
      <a href="negr.html" class="load-more">Načti více</a>
    </section>
    <section id="calendar-section">
      <h2>Kalendář událostí</h2>
      <div class="container">
        <div class="left">
          <div class="calendar">
            <div class="month">
              <i class="fas fa-angle-left prev"></i>
              <div class="date">december 2015</div>
              <i class="fas fa-angle-right next"></i>
            </div>
            <div class="weekdays">
              <div>Ne</div>
              <div>Po</div>
              <div>Út</div>
              <div>St</div>
              <div>Čt</div>
              <div>Pá</div>
              <div>So</div>
            </div>
            <div class="days"></div>
            <div class="goto-today">
              <div class="goto">
                <input type="text" placeholder="mm/yyyy" class="date-input" />
                <button class="goto-btn">Přejít</button>
              </div>
              <button class="today-btn">Dnes</button>
            </div>
          </div>
        </div>
        <div class="right">
          <div class="today-date">
            <div class="event-day">wed</div>
            <div class="event-date">12th december 2022</div>
          </div>
          <div class="events"></div>
          <div class="add-event-wrapper">
            <div class="add-event-header">
              <div class="title">Přidat událost</div>
              <i class="fas fa-times close"></i>
            </div>
            <div class="add-event-body">
              <div class="add-event-input">
                <input type="text" placeholder="Název události" class="event-name" />
              </div>
              <div class="add-event-input">
                <input type="text" placeholder="Čas od" class="event-time-from" />
              </div>
              <div class="add-event-input">
                <input type="text" placeholder="Čas do" class="event-time-to" />
              </div>
            </div>
            <div class="add-event-footer">
              <button class="add-event-btn">Přidat</button>
            </div>
          </div>
        </div>
        <button class="add-event">
          <i class="fas fa-plus"></i>
        </button>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
  <script src="src/js/calendar.js"></script>
</body>

</html>