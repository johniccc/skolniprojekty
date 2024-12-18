<!DOCTYPE html>
<html lang="cs">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <?php include 'components/links.php'; ?>
  <title>Kontakty | Fabiano's Ristorante</title>
</head>

<body>
  <?php include 'components/header.php'; ?>
  <main>
    <section style="background-image: url(src/img/phone.webp);">
      <div class="container feature">
        <h1>Kontakty</h1>
      </div>
    </section>
    <section>
      <div id="contacts" class="container">
        <div class="contact-box">
          <h3>Kontakty</h3>
          <a class="link-anim" href="tel:+420773492934">+420 773 492 934</a>
          <a class="link-anim" href="mailto:stefacja22@zaci.spse.cz">stefacja22@zaci.spse.cz</a>
          <a class="link-anim" href="mailto:spse@spse.cz">spse@spse.cz</a>
        </div>
        <div class="contact-box">
          <h3>Otevírací doba</h3>
          <p>Pondělí - Pátek: 7:00 - 16:00</p>
          <p>Sobota: 7:00 - 11:00</p>
          <p>Neděle: 7:00 - 9:00</p>
        </div>
        <div class="contact-box">
          <h3>Adresa</h3>
          <p>SPŠE a VOŠ Pardubice</p>
          <p>Karla IV. 13</p>
          <p>530 02 Pardubice</p>
          <p>Česká Republika</p>
          <a class="link-button" href="https://maps.app.goo.gl/4dxbn7qnXTTytPsJ9">Cesta k nám</a>
        </div>
        <div class="contact-box">
          <h3>Zřizovatel</h3>
          <p>Pardubický kraj</p>
          <p>IČO: 02013762</p>
          <p>DIČ: CZ02013762</p>
        </div>
      </div>
    </section>
    <section>
      <div class="container">
        <div class="contact-form">
          <h2>Rezervujte si u nás stůl!</h2>
          <form action="">
            <label for="name">Jméno a příjmení</label>
            <input name="name" type="text">
            <label for="email">E-mail</label>
            <input name="email" type="email">
            <label for="phone">Telefon</label>
            <input name="phone" type="text">
            <label for="message">Zpráva</label>
            <textarea name="message" placeholder="Zde napište svoji zprávu..." rows="6"></textarea>
            <input class="link-button" type="submit" value="Poslat zprávu">
          </form>
        </div>
      </div>
    </section>
  </main>
  <?php include 'components/footer.php'; ?>
</body>

</html>