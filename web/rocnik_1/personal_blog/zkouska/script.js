function dropdown1() {
  var x = document.getElementById("dropdown-content1");
  var y = window.matchMedia("(max-width: 1200px)")
  if(y.matches){
    if (x.style.display === "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }
}
function dropdown2() {
  var x = document.getElementById("dropdown-content2");
  var y = window.matchMedia("(max-width: 1200px)")
  if(y.matches){
    if (x.style.display === "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }
}
function dropdown3() {
  var x = document.getElementById("dropdown-content3");
  var y = window.matchMedia("(max-width: 1200px)")
  if(y.matches){
    if (x.style.display === "block") {
      x.style.display = "none";
    } else {
      x.style.display = "block";
    }
  }
}