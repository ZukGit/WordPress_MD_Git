var isZH=!!navigator.language.match("^zh"),i18n={en:{downloaded:"Downloaded",linktxtEnter:"Press Enter to Confirm",showNumber:"Show Number on Image",new_image_added:"New Image Added"},zh:{downloaded:"已下载",linktxtEnter:"确认请按回车键",showNumber:"在图片上显示编号",new_image_added:"添加新图片"}}[isZH?"zh":"en"],_imgList=[],DefaultMinWidth=0,DefaultMinHeight=0,OutputManger=function(){},allImgList=[],allImgList1=[],downloadedImgItems={};OutputManger.prototype={allImgList:allImgList,uniDict:{},awaitingDownload:[],downloadingIndex:0,limitConcurrent:!0,rules2:JSON.parse(localStorage.rules2||"{}"),init:function(){this.commonInit(),this.bindObj();var e=this;chrome.downloads.onChanged.addListener(function(t){if(t.state&&("complete"==t.state.current&&chrome.downloads.search({id:t.id},function(t){var n=t[0];(_.find(e.allImgList,{src:n.url})||_.find(e.allImgList,{dataUrl:n.url})||_.find(e.allImgList,{src:n.url}))&&!_.find(app.downloaded,{url:n.url})&&(app.downloaded.push(n),e.afterOneDownload(n))}),e.limitConcurrent&&t.state.current.match("complete|interrupted"))){if(!navigator.userAgent.match(/QQBrowser/))if(e.downloadingIndex<e.awaitingDownload.length){var n=e.awaitingDownload[e.downloadingIndex];e.saveAs(n[0],n[1],n[2]),e.downloadingIndex++}else e.complete();if(t.state.current.match("interrupted")&&t.error&&"SERVER_FORBIDDEN"==t.error.current){var a=downloadedImgItems[t.id];a.dataUrl&&(chrome.downloads.download({url:a.dataUrl,filename:a.filename,saveAs:!1,conflictAction:"uniquify"},function(e){}),chrome.downloads.erase({id:t.id}))}}})},afterOneDownload:function(e){$('video[src="'+e.url+'"], img[src="'+e.url+'"], img[data-src="'+e.url+'"]').closest("li").addClass("complete")},complete:function(){$("#btn-stop").hide()},commonInit:function(){var e=this;String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"")},$("body").on("keydown",function(e){var t=e.keyCode;e.ctrlKey&&83==t&&(_gaq.push(["_trackEvent","output","save_ctrl_s",null,null,!1]),removeGA())}),localStorage.min_height&&localStorage.min_width||(localStorage.min_width=DefaultMinWidth,localStorage.min_height=DefaultMinHeight),$("#outputTextFormat").val(G_CONFIG.getOutputTextFormat()),$("#renameRule").val(G_CONFIG.getRenameRule()),$("#renamePanel input[name='radioRename']").each(function(e,t){$(t).val()==G_CONFIG.getRenameMode()&&$(t).click()});var t=parseInt(localStorage.min_width),n=parseInt(localStorage.min_height),a=function(a){var i=0;return"min_width"==a?i=t:"min_height"==a&&(i=n),{range:"min",value:i,min:0,max:2e3,step:10,animate:"fast",create:function(e,t){$(this).find(".ui-slider-handle").text(i)},slide:function(e,t){$(this).find(".ui-slider-handle").text(t.value)},stop:function(t,n){var a=$("#minWidthSlider").slider("value"),i=$("#minHeightSlider").slider("value");e.filter({min_width:a,min_height:i})}}};$("#minWidthSlider").slider(a("min_width")),$("#minHeightSlider").slider(a("min_height"));var i=new QueneEnginer;i.outputManager=this,i.start(),this.queneEnginer=i,chrome.storage.local.get("close-new-feature",function(e){e["close-new-feature"]||(navigator.languages&&navigator.languages.join(",").toLowerCase().indexOf("zh")>-1?$("#new-feature, #new-feature .zh").show():$("#new-feature, #new-feature .en").show(),$(".close-new-feature").click(function(){$("#new-feature").remove(),chrome.storage.local.set({"close-new-feature":Date.now()})}))})},reset:function(){app.allImgList1=[]},addImgList:function(e,t){var n=[];e.forEach(function(e){-1==n.indexOf(e.tabIndex)&&n.push(e.tabIndex)}),this.allImgList.forEach(function(e){n.indexOf(e.tabIndex)>=0&&(e.repeatedMarks=[])});var a=this;this.showNum();var i=a.allImgList.length,o=0,r=a.rules2.webp;$.each(e,function(e,n){if(!t){if(void 0!=a.uniDict[n.src]||""==n.src){var l=_.find(a.allImgList,{src:n.src});return l&&(l.repeatedMarks||(l.repeatedMarks=[]),l.mark!=n.mark&&l.repeatedMarks.push(n.mark)),!0}o++,n.index=i+o,a.uniDict[n.src]=!0,a.allImgList.push(n)}if(r)for(e=0;e<r.length;e++)if(n.src.match(r[e].reg)){n.src=n.src.replace(new RegExp(r[e].reg),r[e].rst);break}var s=n.width,c=n.height;"VIDEO"==n.type||s&&c&&s*c!=0?a.addToList({item:n}):a.queneEnginer.add(n)})},checkFilter:function(e){var t=$("#linktxt").val(),n=e.min_width,a=e.min_height,i=e.src,o=e.width,r=e.height;return!(o<n||r<a||""!=t&&i.indexOf(t)<0)},addToList:function(e){var t=e.item,n=t.src,a=t.width,i=t.height,o=(t.whsum=a*i,$("#minWidthSlider").slider("value")),r=$("#minHeightSlider").slider("value");this.checkFilter({width:a,height:i,src:n,min_width:o,min_height:r})&&(t.site=1e5+t.tabIndex+""+(1e5+t.index),t.w=t.width,t.h=t.height,app.allImgList1.push(t),0!=localStorage.dragSelect&&setTimeout(function(){$("#"+t.site).drop(function(e,t){app.toggleDel(parseInt($(this).attr("data-index")))})},1e3)),this.showNum()},bindObj:function(){var e=this;$("#btnInverse").click(function(){e.inverseSelect()}),$("#btnView").click(function(){e.view(),_gaq.push(["_trackEvent","output","view",null,null,!1])}),$("#btnSave").click(function(){e.save(),_gaq.push(["_trackEvent","output","save",null,null,!1])}),$("#btn-stop").click(function(){e.awaitingDownload=[],e.allImgList.forEach(function(e){chrome.downloads.search({url:e.src},function(e){e.length>0&&e.forEach(function(e){chrome.downloads.cancel(e.id)})})}),$(this).hide()}),$("#linktxt").keypress(function(t){if(13==t.keyCode){var n=$("#minWidthSlider").slider("value"),a=$("#minHeightSlider").slider("value");e.filter({min_width:n,min_height:a})}}),$("#list").find("li").live({mouseenter:function(){"1"==localStorage.showUrl&&$(this).find(".picurl").css("display","block")},mouseleave:function(){"1"==localStorage.showUrl&&$(this).find(".picurl").css("display","none")}}),$("#btnSaveHelpOk").click(function(){$("#saveHelp").hide(),e.saveDirect()}),$("#chrome_download_settings_link").click(function(){chrome.tabs.create({url:"chrome://settings/search#"+chrome.i18n.getMessage("chrome_download_settings_url")})}),$("#btnSaveHelpCancel").click(function(){$("#saveHelp").hide()}),$("#sortSelect").change(function(){app.sortBySelect()}),$("#btnMoreOption").toggle(function(){$(".tools").animate({height:"80px"},100),$("#proPanel").slideDown(100),_gaq.push(["_trackEvent","output","more_option",null,null,!1])},function(){$(".tools").animate({height:"40px"},100),$("#proPanel").slideUp(100)}),$("#btnOutputTextShow").click(function(){e.output(),$("#outputTextPanel").show()}),$("#btnOutputTextClose").click(function(){$("#outputTextPanel").hide()}),$("#outputTextFormat").keyup(function(){G_CONFIG.setOutputTextFormat($("#outputTextFormat").val()),e.output()}),$("#renameRule").keyup(function(){G_CONFIG.setRenameRule($("#renameRule").val())})},inverseSelect:function(){var e=this;$("#list>li").each(function(t,n){var a=$(n),i=a.hasClass("delimg");e.setImageVisible(a,i)})},filter:function(e){this.reset(),this.addImgList(this.allImgList,!0)},setImageVisible:function(e,t){},sortBySelect:function(){var e=$("#sortSelect").val().split(";");this.sort(e[0],e[1]),app.allImgList1.sort(function(t,n){return(t[e[0]]-n[e[0]])*("asc"==e[1]?1:-1)}),_gaq.push(["_trackEvent","output","sort",null,null,!1])},sort:function(e,t){$("#list>li").tsort(".aimg",{attr:e,order:t})},showNum:function(){},beforeLoad:function(){$("#loadding").show()},afterLoad:function(){$("#loadding").hide(),app.sortBySelect()},view:function(){var e=$("#list");e.hasClass("theme_view")?(e.removeClass("theme_view"),this.imgCenter($("#list .aimg"))):(e.addClass("theme_view"),$.each($("#list .aimg"),function(e,t){t.style.height="auto",t.style.width="auto",t.style.left=0,t.style.top=0}))},resizeImage:function(){this.imgCenter($("#list .aimg:not(.resize)"))},imgCenter:function(e){},getFilename:function(e,t,n,a,i,o){var r=e.lastIndexOf("/"),l=e.substr(r+1,e.length);if((r=l.lastIndexOf("?"))>=0&&(l=l.substr(0,r)),o=o||"jpg",(r=l.lastIndexOf("."))>=0&&(o=l.substr(r+1,l.length)),l.lastIndexOf(".")<0&&(l=l+"."+o),i&&(l=i+"."+o),"1"==n);else if("2"==n){var s;s=l.substring(0,l.lastIndexOf(".")||l.length),l=this.replaceVarible({text:a,index:t,ext:o,name:s})}l.lastIndexOf(".")<0&&(l+=".jpg");var c=l;try{c=decodeURIComponent(l)}catch(e){}return c.replace(/[-~|\\-\\:*?"'<>=%$@#+;,!\^]/g,"_").replace(/\.+$/g,"")},save:function(){"1"==localStorage.notFirstUseSave?this.saveDirect():$("#saveHelp").show()},saveDirect:function(){var e=this;$("#cbSaveHelp").attr("checked")&&(localStorage.notFirstUseSave="1");var t=$("#renamePanel input[name='radioRename']:checked").val(),n=$("#renameRule").val();G_CONFIG.setRenameMode(t);var a=[];function i(){var t=a.shift();if(t){var n=document.title;"useFixedName"==localStorage.savePathType&&(n=localStorage.fixedName);var o=e.getSafeDir(n)+"/"+t[1];chrome.downloads.download({url:t[0],filename:o,saveAs:!1,conflictAction:"uniquify"},function(e){});var r=0,l=setInterval(function(){++r>10?(i(),clearInterval(l)):chrome.downloads.search({url:t[0]},function(e){if(e.length>0){var t=e[0];"complete"!=t.state&&"interrupted"!=t.stat||(i(),clearInterval(l))}})},100)}}if($("#list li:not(.delimg) .aimg").each(function(i,o){var r=o.dataset.needBlob?o.dataset.dataUrl:o.dataset.src,l=o.dataset.needBlob?"":o.dataset.dataUrl,s=_.find(app.allImgList1,{index:parseInt(o.dataset.index)});s.jpgPngDataUrl&&(r=l=s.jpgPngDataUrl);var c=e.getFilename(o.dataset.src,i,t,n,o.getAttribute("alt"),o.dataset.ext);a.push([r,c,l])}),navigator.userAgent.match(/QQBrowser/))i();else{if(e.limitConcurrent){e.awaitingDownload=a,e.downloadingIndex=0;for(var o=Math.min(10,a.length),r=0;r<o;r++)e.downloadingIndex++,e.saveAs(a[r][0],a[r][1],a[r][2])}else for(r=0;r<a.length;r++)e.saveAs(a[r][0],a[r][1],a[r][2]);$("#btn-stop").show(),app.downloaded=[]}},getSafeDir:function(e){return e.replace(/[-~|\\-\\/:*?"'<>=%$@#+;,!\^]/g,"_").replace(/\.+$/g,"").substr(0,100)},saveAs:function(e,t,n){var a=t.split(".");a[0]=this.getSafeDir(a[0].substr(0,100)),a=a.join(".");var i=this;if(null!=chrome.downloads){var o=document.title;"useFixedName"==localStorage.savePathType&&(o=localStorage.fixedName);var r=this.getSafeDir(o)+"/"+a;chrome.downloads.download({url:e,filename:r,saveAs:!1,conflictAction:"uniquify"},function(t){if(t&&(downloadedImgItems[t]={url:e,filename:r,dataUrl:n}),!t&&chrome.runtime.lastError&&chrome.runtime.lastError.message.match("Invalid filename")){for(var a="",o=0;o<i.allImgList.length;o++)if(i.allImgList[o].src==e){a=i.allImgList[o].ref;break}a&&(a=(a=a.replace(/.*?:\/\//,"")).replace(/\?.*/,"")),a.match("/$")||(a+="/");var l=i.getFilename(e);chrome.downloads.download({url:e,filename:a+l,saveAs:!1,conflictAction:"uniquify"},function(e){})}})}else{var l=document.createElementNS("http://www.w3.org/1999/xhtml","a");l.href=e,l.download=t,l.click()}},replaceVarible:function(e){var t=e.text;if(t=t.replace(/\{PAGETITLE\}/g,document.title),null!=e.name&&(t=t.replace(/\{NAME\}/g,e.name)),null!=e.ext&&(t=t.replace(/\{EXT\}/g,e.ext)),null!=e.link&&(t=t.replace(/\{LINK\}/g,e.link)),null!=e.index){var n=(t=t.replace(/\{NO\}/g,e.index)).match(/\{NO([\d]+)\}/);if(n){var a=n[1],i=a.length,o=e.index+parseInt(a);(""+o).length<i&&(o=("000000000000000000000000000000"+o).substr(-i)),t=t.replace(/\{NO([\d]+)\}/g,o)}}return t},output:function(){var e=$("#outputTextFormat").val(),t=[],n=this;$("#list li:not(.delimg) .aimg").each(function(a,i){var o=i.dataset.src,r=n.replaceVarible({text:e,index:a,link:o});t.push(r)}),$("#outputTextArea").val(t.join("\n"))}};var QueneEnginer=function(){this.Quene=[]};QueneEnginer.prototype={outputManager:null,processTime:100,loadNum:0,processLeftNum:0,maxDownloadNum:5,add:function(e){this.processLeftNum++,app.processLeftNum++,this.Quene.push(e)},start:function(){var e=this;setTimeout(function(){e.process()},e.processTime)},process:function(){var e=this;if(this.Quene.length>0)for(;this.loadNum<this.maxDownloadNum;){var t=this.Quene.shift();if(null==t)break;this.loadNum++,this.loadPic2(t,function(t){if(e.loadNum--,e.processLeftNum--,app.processLeftNum--,t.success){var n=t.item;e.outputManager.addToList({item:n})}0==app.processLeftNum&&app.sortBySelect()})}this.start()},loadPic:function(e,t){var n=new Image;n.onload=function(){var n=parseInt(this.naturalWidth),a=parseInt(this.naturalHeight);e.width=n,e.height=a,t({success:!0,item:e})},n.onerror=function(){e.width=1,e.height=1,t({success:!1,item:e})},n.src=e.src},loadImage:function(e,t){"function"!=typeof t&&(t=function(e){});var n=new XMLHttpRequest;n.responseType="blob",n.onload=function(){var e=n.getResponseHeader("Content-Type");if(e){var a=e.split("/")[1];t(window.URL.createObjectURL(n.response),a)}else t("",a)},n.onerror=function(){t("")};try{n.open("GET",e,!0),n.send()}catch(e){t("")}},loadPic2:function(e,t){this.loadImage(e.src,function(n,a){if(!n)return e.width=1,e.height=1,void t({success:!1,item:e});e.dataUrl=n;var i=new Image;i.onload=function(){var n=parseInt(this.naturalWidth),o=parseInt(this.naturalHeight);if(e.width=n,e.height=o,autoConvertWebp&&"webp"==a){var r=document.createElement("canvas");r.width=i.width,r.height=i.height,r.getContext("2d").drawImage(i,0,0);var l=e.src.split("."),s=e.ext||l[l.length-1].toLowerCase();s.replace(/[?#].*/,""),"jpg"==s||"jpeg"==s?(e.jpgPngDataUrl=r.toDataURL("image/jpeg"),a=s):(e.jpgPngDataUrl=r.toDataURL("image/png"),a="png")}e.ext=e.ext||a||"",t({success:!0,item:e})},i.onerror=function(){e.width=1,e.height=1,t({success:!1,item:e})},i.src=e.dataUrl})}},$(function(){favico=document.getElementById("favico");var e=new OutputManger;e.init();var t=chrome.extension.onMessage;void 0==t&&(t=chrome.extension.onRequest),t.addListener(function(t,n,a){!function(t,n,a){switch(t.cmd){case"ADD_PIC":t.title&&(document.title=t.title),e.addImgList(t.imgList);break;case"ADD_PIC_BY_DRAG":var i=document.title;document.title=i18n.new_image_added,favico.href=t.imgList[0].src,setTimeout(function(){document.title=t.title||i,favico.href="icon-small.png"},2e3),e.addImgList(t.imgList)}}(t)})}),jQuery(function(e){0!=localStorage.dragSelect&&(e(document).drag("start",function(t,n){return e('<div class="selection" />').css("opacity",.65).appendTo(document.body)}).drag(function(t,n){e(n.proxy).css({top:Math.min(t.pageY,n.startY),left:Math.min(t.pageX,n.startX),height:Math.abs(t.pageY-n.startY),width:Math.abs(t.pageX-n.startX)})},{distance:10}).drag("end",function(t,n){e(n.proxy).remove()}),e.drop({multi:!0}))});var app=new Vue({el:"#app",data:{allImgList:allImgList,allImgList1:[],i18n:i18n,chromeI18n:chrome.i18n,processLeftNum:0,downloaded:[],showNumber:localStorage.showNumber},computed:{imgnum:function(){var e=_.filter(this.allImgList1,function(e){return!e.del}).length,t=this.allImgList1.length,n=MSG("process_show_start","")+e+"/"+t+MSG("process_end","图");return this.processLeftNum>0&&(n+=", "+this.processLeftNum+MSG("process_unknow_image","图正在获取大小")),n},downloadedNum:function(){return this.downloaded.length>0?this.downloaded.length+":"+this.i18n.downloaded:""}},methods:{toggleDel:function(e){var t=_.find(app.allImgList1,{index:e});t.del=!t.del,Vue.set(app.allImgList1,app.allImgList1.indexOf(t),t)},inverseSelect:function(){for(var e=0;e<app.allImgList1.length;e++){var t=app.allImgList1[e];t.del=!t.del,Vue.set(app.allImgList1,e,t)}},sortBySelect:function(){var e=$("#sortSelect").val().split(";");app.allImgList1.sort(function(t,n){return(t[e[0]]-n[e[0]])*("asc"==e[1]?1:-1)})},openLink:function(e){window.open(e)},share:function(e){var t={url:e,title:"#Fatkun批量下载图片#",pic:e},n=[];for(var a in t)n.push(a+"="+encodeURIComponent(t[a]||""));window.open("http://service.weibo.com/share/share.php?"+n.join("&"),"_blank","width=615,height=405")},switchShowNumber:function(){app.showNumber=!app.showNumber,localStorage.showNumber?delete localStorage.showNumber:localStorage.showNumber=1,chrome.tabs.query({},function(e){e.forEach(function(e){chrome.tabs.sendMessage(e.id,{topic:"show-number",data:app.showNumber})})})},psview:function(e){var t=document.querySelectorAll(".pswp")[0],n={index:e};new PhotoSwipe(t,PhotoSwipeUI_Default,app.allImgList1,n).init()}}});chrome.storage.local.get("adconfig",function(e){var t=e.adconfig;isZH&&(!t||!t.close||Date.now()-t.t>864e5)&&buildAd()});var autoConvertWebp=1;function buildAd(){$.ajax({url:gConfig.staticServer+"/ad/config.json?_="+Date.now(),dataType:"json",success:function(e){if(e&&1==e.status){var t=$("#ad");t.find("iframe").attr("src",e.url),t.css({width:e.w+"px",height:e.h+"px",display:"block"}),t.find(".close-btn").click(function(){return chrome.storage.local.set({adconfig:{close:!0,t:Date.now()}}),t.remove(),!1})}}})}chrome.storage.local.get("options_config",function(e){var t=e.options_config;t&&0===t.autoConvertWebp&&(autoConvertWebp=0)});