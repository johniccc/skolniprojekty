<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Kontakty | Lucky Paws</title>
  <link rel="stylesheet" href="style.css">
  <?php include 'components/favicon.php'; ?>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section class="hero hero-side">
      <div class="hero-introduction">
        <h1>Kontakty</h1>
      </div>
      <img src="src/img/kontakty-hero.webp" alt="">
    </section>
    <section>
      <div class="top-contacts">
        <div class="contact-info">
          <h2>Zavolejte nám</h2>
          <div class="contact-box-container">
            <div class="contact-box">
              <img src="src/img/phone-orange.svg" alt="">
              <div class="contact-person">
                <a class="link-orange" href="tel:+420773492934">+420 773 492 934</a>
                <p class="team-position">Vedoucí útulku</p>
              </div>
            </div>
            <div class="contact-box">
              <img src="src/img/phone-orange.svg" alt="">
              <div class="contact-person">
                <a class="link-orange" href="tel:+420773492934">+420 773 492 934</a>
                <p class="team-position">Vedoucí útulku</p>
              </div>
            </div>
            <div class="contact-box">
              <img src="src/img/phone-orange.svg" alt="">
              <div class="contact-person">
                <a class="link-orange" href="tel:+420773492934">+420 773 492 934</a>
                <p class="team-position">Vedoucí útulku</p>
              </div>
            </div>
          </div>
          <h2>Napište nám</h2>
          <div class="contact-box-container">
            <div class="contact-box">
              <img src="src/img/email-orange.svg" alt="">
              <div class="contact-person">
                <a class="link-orange" href="mailto:stefacja22@zaci.spse.cz">stefacja22@zaci.spse.cz</a>
                <p class="team-position">Napište nám kdykoliv</p>
              </div>
            </div>
          </div>
        </div>
        <div class="contact-form">
          <h2>Kontaktujte nás pomocí formuláře</h2>
          <form action="">
            <label for="name">Jméno a příjmení</label>
            <input name="name" type="text">
            <label for="email">E-mail</label>
            <input name="email" type="email">
            <label for="phone">Telefon</label>
            <input name="phone" type="text">
            <label for="message">Zpráva</label>
            <textarea name="message" placeholder="Zde napište svoji zprávu..." rows="6"></textarea>
            <input class="btn-default" type="submit" value="Poslat zprávu">
          </form>
        </div>
      </div>
    </section>
    <section class="bottom-contacts">
      <div class="shipping-details">
        <h2>Fakturační údaje</h2>
        <ul>
          <li><span>Střední průmyslová škola elektrotechnická a Vyšší odborná škola Pardubice</span></li>
          <li><span>Adresa:</span> Karla IV. 13, 530 02 Pardubice</li>
          <li><span>IČO:</span> 02013762</li>
          <li><span>Identifikátor datové schránky (ID DS):</span> hrmyqwn</li>
          <li><span>Číslo účtu:</span> 227263307/0600 (Moneta Money Bank)</li>
        </ul>
      </div>
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2562.614369669857!2d15.7796168!3d50.037321399999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470dcced2d56ae4d%3A0x4504c1fb1c448739!2zS2FybGEgSVYuIDEzLzEzLCA1MzAgMDIgUGFyZHViaWNlIEktWmVsZW7DqSBQxZllZG3Em3N0w60!5e0!3m2!1scs!2scz!4v1709766246003!5m2!1scs!2scz" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>