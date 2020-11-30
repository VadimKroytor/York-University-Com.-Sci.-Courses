/* Lab 6 - Task 2  */
 window.onload = function() {
   $("userid").observe("keyup", myFunction1);
   $("code").observe("keyup", myFunction2);
   $("number").observe("keyup", myFunction3);
   var validOne = myFunction1();
   var validTwo = myFunction2();
   var validThree = myFunction3();
   //$("submitButton").observe("click", myFunction4(validOne, validTwo, validThree));
   $("submitButton").observe("click", myFunction4);
   $("clear").observe("click", myFunction5);
  /* Please have a look at the HTML file for the ids of the input elements */
  /* add in your event handlers here */
  /* you are highly encourage to look at the lecture notes and code on JS and Forms */
}

function myFunction1() {
  var re1 = /^[A-Z][A-Z]+$/i;
  var re2 = /^[A-Z][0-9]+$/i;
  var message = document.getElementsByClassName("message");
  var valid = true;
  var testString = $("userid").value;

  for (var i = 0; i < testString.length; i++) {
    if ((re1.test(testString) == true) || (re2.test(testString) == true))  {
      $("userid").nextElementSibling.innerHTML = "CORRECT";
      message.innerHTML = "CORRECT";
      valid = true;
    }

      else {
        $("userid").nextElementSibling.innerHTML = "INCORRECT";
        message.innerHTML = "INCORRECT";
        valid = false;
      }
  }
  return valid;
}

function myFunction2() {
  var valid = true;
  var testString = $("code").value;
  for (var i = 0; i < testString.length; i++) {
    if (testString == "EECS" || testString == "ESSE" || testString == "MATH"
    || testString == "HIST" || testString == "CHEM" || testString == "BIO") {
      $("code").nextElementSibling.innerHTML = "CORRECT";
      valid = true;
    }
    else {
      $("code").nextElementSibling.innerHTML = "INCORRECT";
      valid = false;
    }
  }
  return valid;
}

function myFunction3() {
  var valid = true;
  var re = /^[0-9][0-9][0-9][0-9]$/;
  var testString = $("number").value;
  for (var i = 0; i < testString.length; i++) {
    if (re.test(testString) == true) {
      $("number").nextElementSibling.innerHTML = "CORRECT";
      valid = true;
    }
    else {
      $("number").nextElementSibling.innerHTML = "INCORRECT";
      valid = false;
    }
  }
  return valid;
}

function myFunction4(/* validOne, validTwo, validThree*/) {
  var spans = document.getElementsByClassName("message");
  var valid = true;
  for (var i =0; i < spans.length; i++) {
    if (spans[i].innerHTML == "CORRECT") {
        $("myForm").submit();
    }

      else {
        alert("Form is Incorrect.");
      }
    }
}

function myFunction5() {
  var spans = document.getElementsByClassName("message");
  var inputboxes = document.getElementsByTagName("input");
  var valid = true;
  for (var i =0; i < spans.length; i++) {
    if (spans[i].innerHTML == "INCORRECT") {
        inputboxes[i].value = "";
        spans[i].innerHTML = "";
    }

      else {
        valid = false;
      }
    }

}
