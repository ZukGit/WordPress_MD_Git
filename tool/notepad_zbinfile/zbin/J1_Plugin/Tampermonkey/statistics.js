Registry.require(["promise","syncinfo","uri","helper"],function(){(function(f){var u=Registry.get("promise"),C=Registry.get("syncinfo"),D=Registry.get("uri"),E=Registry.get("helper"),v=rea.FEATURES,p=v.HTML5.LOCALSTORAGE,q=function(b,c){b&&c&&Object.keys(c).forEach(function(a){b[a]=c[a]})},h=function(b,c){var a;if("string"===typeof g)a="G";else{a=b||"default";a=g.enabled&&!1===g.enabled[a]?null:g.tracker[a];if(null===a)return;a=(a||g.tracker["default"]).match(/[0-9]*/g).join("").split("").map(function(a){return String.fromCharCode(a.charCodeAt(0)+
23)}).join("")}return[a].concat(c?[c]:[]).join(".")},l=function(){return{enabled:["api",1>100*Math.random()],options:[],tracker:"default UA-40782729-1 script UA-40782729-2 script_update UA-40782729-2 naverr UA-40782729-3 api UA-40782729-4".split(" ")}},w=function(){return"UA-40861355-1"},x=function(){return"UA-57518333-1"},y=function(){return{options:[],tracker:["default","UA-83591358-1","api",null]}},r={"default":l,gcal:l,G3XV:l,fire:l,firb:l,neuu:x,jecn:x,dhdg:function(){return{enabled:["default",
1>100*Math.random(),"api",.01>100*Math.random()],options:[],tracker:["script","UA-40861355-2","script_update","UA-40861355-3","error","UA-40861355-4","naverr","UA-40861355-5","default","UA-40861355-6","api","UA-40861355-7","browser",null]}},dcgo:w,bnim:w,mfdh:l,gz80:l,heif:y,"5snx":y},g=function(b){var c=b();"string"===typeof c&&(c={tracker:["default",c]});var a,d={options:{},tracker:{},enabled:{}};Object.keys(d).forEach(function(b){if(c[b])for(a=0;a<c[b].length;a+=2)d[b][c[b][a]]=c[b][a+1]});return d}(r[rea.runtime.short_id]||
r["default"]),F=[{msg:"a disconnected port"},{msg:"Function.prototype.apply: Arguments list has wrong type",url:"event_bindings"},{msg:"Script error."}],z=!1,A=!1,m=!1;f._gaq=f._gaq||[];var n=[],G=function(){console.log("statistics: Unable to load GA -> enable emergency mode");var b={},c=function(){var a=p?a=p.getItem("ga_clientId"):E.createUUID();p&&p.setItem("ga_clientId",a);return a};f.ga=function(a,d,e,f,h,m,g){if("create"==a)b[f.name]=d;else{a=a.split(".");var k,l,n;(k=a[0])&&(l=b[k])&&(n=a[1])&&
"send"==n&&"object"!=typeof f&&(g={v:1,aip:1,t:d,dl:location.href,cid:c(),tid:l,ni:g&&g.nonInteraction?1:void 0,z:Date.now()},q(g,"event"==d?{ec:e,ea:f,el:h,ev:void 0}:{dp:e}),d="https://www.google-analytics.com/collect?"+D.hash2params(g),e=new XMLHttpRequest,e.onerror=function(a){console.log("statistics: sending data failed",a)},e.open("GET",d),e.send(null))}};f.ga.getByName=function(){return{get:function(a){if("clientId"===a)return c()}}}},H=function(){var b=function(){for(var a;a=n.shift();)a();
n=!1},c=function(){G();b()};v.RUNTIME.FIREFOX?c():function(a,d,e,f){a.GoogleAnalyticsObject="ga";a.ga=a.ga||function(){(a.ga.q=a.ga.q||[]).push(arguments)};a.ga.l=1*new Date;e=d.createElement("script");f=d.getElementsByTagName("script")[0];e.async=!0;e.src="https://ssl.google-analytics.com/analytics.js";e.addEventListener("load",b,!1);e.addEventListener("error",c,!1);f.parentNode.insertBefore(e,f)}(f,document)},k=function(b){if(b){var c=arguments;B().done(function(){f.ga.apply(f,c)})}},J=function(){A||
(n.push(function(){var b=[],c={};Object.keys(g.tracker).forEach(function(a){var d=h(a);if(d&&!c[d]){c[d]=!0;var e={name:d};q(e,null);q(e,(g.options||{})[a]);b.push(["create",g.tracker[a],"auto",e]);b.push([d+".set","checkProtocolTask",null]);b.push([d+".set","anonymizeIp",!0]);b.push([d+".set","forceSSL",!0])}});b.forEach(function(a){f.ga.apply(f,a)});k(h("pageview","send"),"pageview",f.location.pathname||f.location.href);r[rea.runtime.short_id]||window.setTimeout(I,3E4)}),H(),A=!0)},I=function(){m&&
k(h("extension","send"),"event","Extension","ID",rea.runtime.id+" @ "+navigator.userAgent,null,{nonInteraction:!0})},t=function(b,c,a){if(m&&b.search){void 0===c&&(c="");void 0===a&&(c+=" "+window.location.href,a="");var d=!1;F.forEach(function(a){if(a.msg||a.url)a.msg&&-1==b.search(a.msg)||a.url&&-1==c.search(a.url)||(d=!0)});d||k(h("error","send"),"event","Error",b,c+":"+a,null,{nonInteraction:!0})}},B=function(){var b=u();!1===n?b.resolve():n.push(b.resolve);return b.promise()},K=function(){var b=
null,c;return function(){return c?c:null===b?c=C.getUtc().then(function(a){c=null;b=a.getTime()-Date.now();window.setTimeout(function(){b=null},6E4);return b}).fail(function(){window.setTimeout(function(){c=null},6E5)}):u.Pledge(b)}}();Registry.register("statistics","5770",{init:function(b,c){window.onerror=function(a,d,e,f,g){var h="";if(g)try{h=g.stack}catch(k){}t(a,c+" "+b+"@"+rea.extension.urls.prepareForReport(d),[e+":"+f,h].join(";"))};document.onsecuritypolicyviolation=function(a){var d=
"";if(a)try{d=a.stack}catch(e){}t("CSP violation of "+a.effectiveDirective,c+" "+b+"@"+rea.extension.urls.prepareForReport(a.documentURI),[a.sourceFile," -> ",a.lineNumber+":"+a.columnNumber,d].join(";"))};z=!0},setEnabled:function(b){z&&(m=b)&&J()},isActive:function(b){return m&&!!h(b)},tB:function(b){if(m){var c="";b.updated&&(c="Updated");k(h("browser","send"),"event","Browser",c,navigator.userAgent,null,{nonInteraction:!0})}},tA:function(b,c){if(m){var a={grant:"Granted",run_at:"RunAt"}[c]||"Used";
k(h("api","send"),"event","API",a,b,null,{nonInteraction:!1})}},tS:function(b,c,a){if(m){var d="",e;"i"===a?(d="Installed",b+=" <"+(c?c:"?")+">"):"u"===a?(d="Updated",e=h("script_update","send")):"b"===a?(d="Blacklisted",b+=" <"+(c?c:"?")+">"):"m"===a?(d="Revealed",b=c):d="Removed";k(e||h("script","send"),"event","Script",d,b,null,{nonInteraction:!1})}},tE:t,tG:function(b,c,a){if(m){var d="",e="";"clicked"===b?(e="Click",d=c+":"+a):"button"===b?(e="Button",d=c):"dialog"===b&&(e="Dialog");k(h("begging",
"send"),"event","Begging",e,d,null,{nonInteraction:!1})}},tN:function(b,c,a){if(m){var d;K().then(function(a){d=a;return B()}).done(function(){k(h("naverr","send"),"pageview",b,{metric1:a,dimension1:f.ga.getByName(h()).get("clientId"),dimension2:String(Date.now()+d),nonInteraction:!1})})}}})})(window)});