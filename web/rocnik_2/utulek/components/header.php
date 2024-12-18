<style>
  header {
    width: 100%;
    padding: 15px 30px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    background-color: var(--primary);
  }

  header img {
    height: 55px;
  }

  header #logo,
  header #logo-small {
    display: none;
  }

  header #logo-big {
    display: block;
  }

  header #desktop-nav {
    display: block;
  }

  header #mobile-nav {
    display: none;
  }

  @media only screen and (max-width: 1366px) {

    header #logo-big,
    header #logo-small {
      display: none;
    }

    header #logo {
      display: block;
    }
  }

  @media only screen and (max-width: 1100px) {
    header #desktop-nav {
      display: none;
    }

    header #mobile-nav {
      display: block;
    }
  }

  @media only screen and (max-width: 767px) {

    header #logo,
    header #logo-big {
      display: none;
    }

    header #logo-small {
      display: block;
    }
  }



  #links {
    display: flex;
    gap: 30px;
    align-items: center;
    font-size: 1.25rem;
  }


  #links a {
    font-weight: 500;
    letter-spacing: 1px;
  }

  #links .btn-default:hover {
    color: var(--white);
  }

  #links .btn-default::after {
    background: var(--white);
  }

  #ham-icon {
    width: 55px;
    height: 40px;
    position: relative;
    transform: rotate(0deg);
    transition: 0.5s ease-in-out;
    cursor: pointer;
    z-index: 4;
  }

  #ham-icon span {
    display: block;
    position: absolute;
    height: 5px;
    width: 100%;
    background-color: black;
    border-radius: 9px;
    opacity: 1;
    left: 0;
    transform: rotate(0deg);
    transition: 200ms ease-in-out;
  }

  #ham-icon span:nth-child(1) {
    top: 0px;
  }

  #ham-icon span:nth-child(2),
  #ham-icon span:nth-child(3) {
    top: 18px;
  }

  #ham-icon span:nth-child(4) {
    top: 36px;
  }

  #ham-icon.open span:nth-child(1) {
    top: 18px;
    width: 0%;
    left: 50%;
  }

  #ham-icon.open span:nth-child(2) {
    transform: rotate(45deg);
  }

  #ham-icon.open span:nth-child(3) {
    transform: rotate(-45deg);
  }

  #ham-icon.open span:nth-child(4) {
    top: 18px;
    width: 0%;
    left: 50%;
  }

  #ham-icon.open span {
    background-color: black;
  }

  #mobile-nav-menu {
    position: absolute;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    z-index: 3;
    background: url(src/img/hero-background-overlay.webp) var(--primary);
    background-blend-mode: multiply;
    background-repeat: no-repeat;
    ;
    padding: 0 30px;
    top: 0;
    right: 0;
    transform: translateX(100%);
    transition: all 350ms ease-out;
  }

  #mobile-nav-menu.open {
    transform: translateX(0%);
  }

  .black-background {
    opacity: 0;
    visibility: hidden;
    height: 100vh;
    width: 100vw;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 2;
    background-color: rgba(0, 0, 0, 0.6);
    transition: all 400ms;
  }

  #mobile-nav-menu.open~.black-background {
    opacity: 1;
    visibility: visible;
  }

  #mobile-links {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 20px;

  }

  #mobile-links a {
    font-size: 1.5rem;
    font-weight: 600;
  }
</style>



<header>
  <a href="index.php" id="logo-big"><img src="src/img/logo-big.svg" alt=""></a>
  <a href="index.php" id="logo"><img src="src/img/logo.svg" alt=""></a>
  <a href="index.php" id="logo-small"><img src="src/img/logo-small.svg" alt=""></a>
  <nav id="desktop-nav">
    <div id="links">
      <a href="onas.php">O nás</a>
      <a href="udalosti.php">Události a zprávy</a>
      <a href="jak-pomoci.php">Jak pomoci?</a>
      <a href="kontakt.php">Kontakty</a>
      <a class="btn-default" href="adopce.php">Adoptujte si!</a>
    </div>
  </nav>
  <nav id="mobile-nav">
    <div id="ham-icon" onclick="navIconAnim()">
      <span class="bar"></span>
      <span class="bar"></span>
      <span class="bar"></span>
      <span class="bar"></span>
    </div>
    <div id="mobile-nav-menu">
      <div id="mobile-links">
        <a href="onas.php">O nás</a>
        <a href="udalosti.php">Události a zprávy</a>
        <a href="jak-pomoci.php">Jak pomoci?</a>
        <a href="kontakt.php">Kontakty</a>
        <a class="btn-default" href="adopce.php">Adoptujte si!</a>
      </div>
    </div>
    <div class="black-background"></div>
  </nav>
</header>

<script src="src/js/hamburger.js"></script>