<style>
  footer {
    background-color: var(--darkaccentorange);
    padding: 50px 12%;
    color: var(--white);
    margin-top: 100px;
  }

  footer p,
  footer h1,
  footer h2,
  footer h3,
  footer h4,
  footer h5,
  footer h6 {
    color: var(--white);
  }

  .footer-container {
    display: flex;
    justify-content: space-between;
    gap: 80px;
  }

  footer ul li,
  footer ul li a {
    color: var(--white);
    font-weight: 500;
  }

  footer ul li {
    display: flex;
    align-items: center;
  }

  footer ul li img {
    margin-right: 10px;
    width: 1.5rem;
  }

  footer h3 {
    font-size: 2rem;
    margin-bottom: 0.8rem;
  }

  footer ul {
    list-style-type: none;
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  .information {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
    gap: 40px;
  }

  .address {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    gap: 1rem;
  }

  .address img,
  .bank img {
    width: 60px;
  }

  .address-details a,
  .bank-details p {
    color: var(--white);
    font-size: 1.1rem;
    font-weight: 700;
  }

  .address iframe {
    margin-top: 0.75rem;
  }

  .address-text,
  .bank-text {
    display: flex;
    align-items: center;
    gap: 20px;
  }

  .contacts {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }

  .copyright {
    display: flex;
    justify-content: space-between;
    width: 100%;
    align-items: flex-end;
    margin-top: 3rem;
    padding-top: 3rem;
    border-top: dotted 3px;
    border-color: var(--white);
    filter: opacity(0.85);
  }

  .copyright p {
    color: var(--white);
  }

  .footer-container a::after {
    background-color: var(--accentorangehover);
  }

  .map {
    width: 650px;
  }

  @media only screen and (max-width: 1400px) {
    .map {
      width: 400px;
    }
  }
  @media only screen and (max-width: 1000px) {
    .footer-container {
      flex-direction: column;
    }
    .map {
      width: 100%;
    }
    .copyright {
      flex-direction: column;
      justify-content: center;
      align-items: center;
      gap: 10px;
    }
  }

  @media only screen and (max-width: 767px) {
    footer {
      padding: 50px 8%;
    }
  }
</style>



<footer>
  <div class="footer-container">
    <div class="information">
      <div class="contacts">
        <h3>Kontakty</h3>
        <ul>
          <li><img src="src/img/phone.svg" alt=""><a href="tel:+420773492934">+420 773 492 934</a></li>
          <li><img src="src/img/email.svg" alt=""><a href="mailto:stefacja22@zaci.spse.cz">stefacja22@zaci.spse.cz</a></li>
          <li><img src="src/img/facebook.svg" alt=""><a href="https://www.facebook.com">Facebook</a></li>
          <li><img src="src/img/instagram.svg" alt=""><a href="https://www.instagram.com">Instagram</a></li>
          <li><img src="src/img/youtube.svg" alt=""><a href="https://www.youtube.com">Youtube</a></li>
        </ul>
      </div>
      <div class="open-hours">
        <h3>Otevírací doba</h3>
        <ul>
          <li>Pondělí: 7:00 - 16:00</li>
          <li>Úterý: 7:00 - 16:00</li>
          <li>Středa: 7:00 - 16:00</li>
          <li>Čtvrek: 7:00 - 16:00</li>
          <li>Pátek: 7:00 - 16:00</li>
          <li>Sobota: 7:00 - 11:00</li>
          <li>Neděle: 7:00 - 9:00</li>
        </ul>
      </div>
      <div class="bank">
        <h3>Bankovní spojení</h3>
        <div class="bank-text">
          <img src="src/img/bank.svg" alt="">
          <div class="bank-details">
            <ul>
              <li><a href="negr.html">227263307/0600</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="address">
      <h3>Kde nás najdete?</h3>
      <div class="address-text">
        <img src="src/img/map-marker.svg" alt="">
        <div class="address-details">
          <a href="">Karla IV. 13<br>530 02 Pardubice I</a>
        </div>
      </div>
      <div class="map"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2562.614369669857!2d15.7796168!3d50.037321399999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470dcced2d56ae4d%3A0x4504c1fb1c448739!2zS2FybGEgSVYuIDEzLzEzLCA1MzAgMDIgUGFyZHViaWNlIEktWmVsZW7DqSBQxZllZG3Em3N0w60!5e0!3m2!1scs!2scz!4v1709766246003!5m2!1scs!2scz" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></div>
    </div>
  </div>
  <div class="copyright">
    <a href="index.php"><img src="src/img/logo-white.svg" alt=""></a>
    <p>© 2024 Všechna práva vyhrazena | Lucky Paws</p>
  </div>
</footer>