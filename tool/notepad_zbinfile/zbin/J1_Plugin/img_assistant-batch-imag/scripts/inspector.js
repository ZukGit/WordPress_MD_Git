/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

if (typeof _w_psyche == "undefined") {
    window._w_psyche = "_w_brink";
}

if (document.querySelector("input#" + window._w_psyche) == null && typeof chrome != "undefined" && typeof chrome.runtime != "undefined" && typeof chrome.runtime.id != "undefined" && typeof chrome.runtime.getURL != "undefined") {
    let _w_cuff = null;
    _w_cuff = setInterval((function() {
        let _w_blur = document.getElementsByTagName("head").item(0);
        if (typeof _w_blur == "object") {
            clearInterval(_w_cuff);
            let _w_twig = document.createElement("script");
            _w_twig.type = "text/javascript";
            _w_twig.src = chrome.runtime.getURL("/scripts/inspector.js");
            _w_blur.appendChild(_w_twig);
            let _w_guru = document.createElement("input");
            _w_guru.type = "hidden";
            _w_guru.id = _w_psyche;
            _w_blur.appendChild(_w_guru);
        }
    }), 1e3);
} else if (!window._w_agenda) {
    window._w_agenda = function() {
        let _w_cellar = [];
        let _w_apron = {};
        let _w_nude = /(['"\s\n\r])[^'"\s\n\r]*?\.(apng|bmp|gif|ico|cur|jpg|jpeg|jfif|pjpeg|pjp|png|svg|tif|tiff|webp)(\?[^'"\s\n\r]*)?(?=\1)/gi;
        let _w_rail = function(_w_scarp) {
            _w_scarp && _w_scarp.forEach((function(item) {
                let _w_whit = item.replace(/[\\'"\s\n\r]+/gi, "");
                if (!_w_apron[_w_whit]) {
                    _w_apron[_w_whit] = true;
                    _w_cellar.push(_w_whit);
                }
            }));
        };
        XMLHttpRequest.prototype.realSend = XMLHttpRequest.prototype.send;
        XMLHttpRequest.prototype.send = function(value) {
            this.addEventListener("load", (function() {
                if (!this.responseType || this.responseType === "text") {
                    let _w_scarp = this.responseText.match(_w_nude);
                    _w_rail(_w_scarp);
                }
            }), false);
            this.realSend(value);
        };
        const _w_phial = window.fetch;
        window.fetch = function() {
            return new Promise((resolve, reject) => {
                _w_phial.apply(this, arguments).then((function(response) {
                    response.clone && response.clone().text().then((function(_w_mode) {
                        let _w_scarp = _w_mode.match(_w_nude);
                        _w_rail(_w_scarp);
                    }));
                    resolve(response);
                })).catch((function(response) {
                    reject(response);
                }));
            });
        };
        let _w_scarp = document.documentElement.innerHTML.match(_w_nude);
        _w_rail(_w_scarp);
        setInterval((function() {
            let _w_guru = document.getElementById(_w_psyche);
            if (_w_guru && _w_guru.value.length > 0 && _w_guru.value == _w_psyche && _w_cellar.length > 0) {
                _w_guru.value = JSON.stringify(_w_cellar);
                _w_cellar = [];
            }
        }), 512);
        return {
            _w_minion: function() {
                return _w_cellar.length;
            }
        };
    }();
}