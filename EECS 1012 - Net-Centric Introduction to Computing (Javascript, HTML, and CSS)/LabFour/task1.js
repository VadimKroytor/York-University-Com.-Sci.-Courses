/* Task1.js - Add your Java Script Code Here */
function myFunction()
{
  var p = document.getElementById("mydata");
    var randomNum = Math.floor(Math.random()*4);
    if (randomNum > 0.5) {
      p.innerHTML = "YES";
    }

    else {
      p.innerHTML = "NO";

    }
  // set p.innerHTML equal to YES or NO

}
