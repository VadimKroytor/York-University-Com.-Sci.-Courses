/* Task4.js - Add your Java Script Code Here */
function myFunction(numOne, numTwo)
{
  var p = document.getElementById("mydata");
    numOne = Math.floor((Math.random()*6)+1);
    numTwo = Math.floor((Math.random()*6)+ 1);

        p.innerHTML = "Dice rolls are " + '"' + numOne + '"' + " and " + '"' + numTwo + '"';

  if (numOne == numTwo) {
    alert("DOUBLES!");
  }
}
