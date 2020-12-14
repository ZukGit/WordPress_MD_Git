$("#article_content").css("overflow", "overlay");
$("#article_content").css("height", "auto");
$("article.baidu_pl > div.hide-article-box").remove();
$("div.pulllog-box").remove();
$("div.container > aside > div:eq(1)").remove();
$("#asideFooter > .aside-box:eq(0)").remove();
$(".adblock").remove();
$("li.bdsharebuttonbox").remove();
$("#dmp_ad_58").remove();
$("div.recommend-box > div.recommend-ad-box").remove();

function removeRecommandAds() {
  var lastLength = $("div.recommend-box").children().length;
  for (var i = 0; i < length; i++) {
    var content = $("div.recommend-box > div").eq(i).find("div.content > a").attr("title");
    if (content === null || content === undefined) {
      $("div.recommend-box > div").eq(i).remove();
      i--;
    }
  }
  if ($(".btn-readmore").length > 0) {
    $(".btn-readmore").click();
  }
  $(".hide-article-box").remove();
  if (typeof (csdn) != "undefined") {
    csdn.copyright.init("", "", ""); //去除剪贴板劫持
  }
}
setTimeout(removeRecommandAds, 2000);


function InjectJs(jsPath) {
  jsPath = jsPath || 'js/inject.js';
  var temp = document.createElement('script');
  temp.setAttribute('type', 'text/javascript');
  temp.src = chrome.extension.getURL(jsPath);
  temp.onload = function () {
    this.parentNode.removeChild(this);
  };
  document.head.appendChild(temp);
}
InjectJs("js/clearPaster.js");