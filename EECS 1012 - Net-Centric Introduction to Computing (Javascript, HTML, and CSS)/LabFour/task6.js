/* Task4.js - Add your Java Script Code Here */

var output = 40;
function myFunction()
{
  var p = document.getElementById("mydata");
  var counter = 0;
  for (var i = 0; i <= output; i++) {
  if (i == 0) {
    output = output - 1;
  }

    p.innerHTML = output; //}

    if (output <= 0) {
      p.innerHTML = "BOOM";
    }
  }
}
