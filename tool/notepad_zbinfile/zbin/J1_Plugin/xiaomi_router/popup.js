//蓝翼制作 QQ:421404354
document.getElementById("download").onclick = function (e) {
	var url = document.getElementById("url").value;
	chrome.tabs.create({url: "http://d.miwifi.com/d2r/?url="+Base64.encodeURI(url)});
};