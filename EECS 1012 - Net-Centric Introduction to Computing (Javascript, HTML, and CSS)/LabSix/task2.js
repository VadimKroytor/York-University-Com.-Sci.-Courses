/* Lab 6, Task 2.  Use the prototype library */
window.onload = function() {
  $("submitButton").onclick = submitCheck;
  $("input1").observe("keydown", keyPressOne);
  $("input2").observe("keydown", keyPressTwo);
  $("input3").observe("keydown", keyPressThree);

}

function keyPressOne(event) {
  $("input1").value = "";
  $("input1").stopObserving("keydown");
}
function keyPressTwo(event) {
  $("input2").value = "";
  $("input2").stopObserving("keydown");
}

function keyPressThree(event) {
  $("input3").value = "";
  $("input3").stopObserving("keydown");
}

function submitCheck() {
if (($("input1").value == "") || ($("input2").value == "") ||
($("input3").value == "") || ($("input1").value == "Enter Name") ||
($("input2").value == "Enter Student ID")
|| ($("input3").value == "Enter Email" ))  {
ErrorMessage.innerHTML = ("Please enter text before pressing submit!");
}
else {
$("myForm").submit();
}
}
