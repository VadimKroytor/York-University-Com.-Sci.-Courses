/* Task3.js - Add your Java Script Code Here */
function myFunction(num)
{
  var p = document.getElementById("mydata");
    var counter = 0;
      for (var i = 0; i <= num; i++) {
        counter = counter + i;
        p.innerHTML = "Result ="+ counter;
      }

}
