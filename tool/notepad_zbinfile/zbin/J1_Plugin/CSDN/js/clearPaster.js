if (typeof (csdn) != "undefined") {
    csdn.copyright.init("", "", ""); //去除剪贴板劫持
  }
// $("#check-adblock-time").text(999999999999);
// $(".check-adblock-bg").hide();
var hookedInterval = window.setInterval;
//拦截页面一切定时器，过于霸道可能会有误伤
window.setInterval = function(callback, seconds) {
    // Magic time
    if (seconds == 1e3) {
        document.querySelector('#check-adblock-time').remove();
        return;
    }
    hookedInterval(callback, seconds);
};
window.csdn.anonymousUserLimit && (window.csdn.anonymousUserLimit.judgment = function() {return true;})&&(window.csdn.anonymousUserLimit.Jumplogin = function () {console.log("Fuck CSDN :)");});