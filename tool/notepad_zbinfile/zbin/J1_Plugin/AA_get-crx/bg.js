function get_crx() {
	chrome.tabs.getSelected(null,function(tab) {
		var filename = tab.title.split(" - Chrome Web Store")[0]+".crx";
		filename = filename.replace(/[&\/\\:"*<>|?]/g, '');
		var tab_url = tab.url.split("/")[6].split('?')[0];
		var url = "https://clients2.google.com/service/update2/crx?response=redirect&acceptformat=crx2,crx3&x=id%3D"+tab_url+"%26uc&prodversion="+navigator.userAgent.split("Chrome/")[1].split(" ")[0];
		if (!chrome.downloads) {
			var a = document.createElement('a');
			a.href = url;
			a.download = filename;
			(document.body || document.documentElement).appendChild(a);
			a.click();
			a.remove();
			return;
		}
		chrome.downloads.download({
			url: url,
			filename: filename,
			saveAs: true
		}, function(downloadId) {
			if (chrome.runtime.lastError) {
				alert('Error: Failed saving  ' + filename + ':\n\n' +
					chrome.runtime.lastError.message);
			}
		});
	});
}
chrome.contextMenus.create({
	'title': 'Get CRX of this extension',
	'contexts': ['all'],
	'onclick': get_crx,
	'documentUrlPatterns': ['https://chrome.google.com/webstore/detail/*']
});