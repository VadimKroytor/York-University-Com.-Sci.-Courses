/* Lab 6 - Task 1 */
/* add your code here. Recommend you use the prototype library */
/* There is a local copy in your folder */

window.onload = function()
{
  changeImg();
  $("myImg").observe("mouseover", changeImg);

}



var currentImg = 0;

function changeImg()
{
  var imgList = ["face1.png", "face2.png", "face3.png", "face4.png"];
  var imgNum = Math.floor(Math.random() * 4);
  while (currentImg == imgNum)
  {
    imgNum = Math.floor(Math.random() * 4);
  }
  currentImg = imgNum;
  $("myImg").src = imgList[imgNum];

}
