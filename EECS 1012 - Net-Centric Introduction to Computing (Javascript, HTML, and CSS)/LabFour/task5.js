/* Task4.js - Add your Java Script Code Here */
function myFunction()
{
  var p = document.getElementById("mydata");
  var myDate = new Date();
  var day = myDate.getDay();
  var date = myDate.getDate();
  dayList = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"]
  var year = myDate.getFullYear();
  var month = myDate.getMonth();
  monthList = ["January", "February", "March", "April", "May", "June", "July", "August", "September",
  "October", "November", "December"];
  p.innerHTML = "Today is the " + date + " (" + dayList[day] + ") " + monthList[month] + " " + year;
}
