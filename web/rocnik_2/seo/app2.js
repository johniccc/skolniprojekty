let methodBoxes = document.getElementsByClassName("method-box");
let copyBtns = document.getElementsByClassName("copy-btn");
for (let i = 0; i<methodBoxes.length; i++) {
  methodBoxes[i].setAttribute("id", "box" + (i+1));
  copyBtns[i].setAttribute("onclick", "copyTxt(" + "\"box" + (i+1) + "\")");
}

function copyTxt (id) {
  let text = document.getElementById(id).getElementsByClassName("code-block")[0].getElementsByTagName("p")[0].innerHTML;
  let filteredtext = text.replace(/<\/?span[^>]*>/g,"").replaceAll("&lt;","<").replaceAll("&gt;",">").replaceAll("<br>", "");
  navigator.clipboard.writeText(filteredtext);
}