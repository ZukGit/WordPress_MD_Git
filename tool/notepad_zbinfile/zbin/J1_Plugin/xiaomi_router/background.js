//蓝翼制作 QQ:421404354
var link_id = chrome.contextMenus.create({
	"type": "normal",
	"title": "下载链接到小米路由器",
	"contexts":['link']
});
var img_id = chrome.contextMenus.create({
	"type": "normal",
	"title": "下载图片到小米路由器",
	"contexts":['image']
});
var audio_id = chrome.contextMenus.create({
	"type": "normal",
	"title": "下载音频文件到小米路由器",
	"contexts":['audio']
});
var video_id = chrome.contextMenus.create({
	"type": "normal",
	"title": "下载视频文件到小米路由器",
	"contexts":['video']
});

chrome.contextMenus.onClicked.addListener(function(info,tab) {
	var url = "";
	switch (info.menuItemId) {
		case link_id:
			url = info.linkUrl;
			break;
		case img_id:
		case audio_id:
		case video_id:
			url = info.srcUrl;
			break;
		default:
			url = info.pageUrl;
	}
	chrome.tabs.create({"url" : "http://d.miwifi.com/d2r/?url="+Base64.encodeURI(url)});
});