function navIconAnim() {
  const navIcon = document.getElementById('ham-icon');
  const nav = document.getElementById('mobile-nav-menu');
  
  navIcon.classList.toggle('open');
  nav.classList.toggle('open');
}