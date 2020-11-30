/* Lab 6 - Task 3.  This is related to DOM (not Events), but you can
   still use the prototype library */
window.onload = function() {



  var myDays = document.getElementById("myDays");
  var dayList = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"];
  var monthList = ["Jan", "Feb", "Mar", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"]


  for (var u = 0; u < dayList.length; u++) {
    var option = document.createElement("option");
    option.value = dayList[u];
    option.text = dayList[u];
    $("days").appendChild(option);
  }

  for (var s = 0; s < monthList.length; s++) {
    var option = document.createElement("option");
    option.value = monthList[s];
    option.text = monthList[s];
    $("months").appendChild(option);
  }

  for (var x = 1970; x <= 2018; x++) {
    var option = document.createElement("option");
    option.value = x;
    option.text = x;
    $("years").appendChild(option);
  }

}
