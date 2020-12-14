/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

function _w_helve() {
    chrome.runtime.sendMessage(_w_crab(), {
        type: "_w_yokel",
        fetchLevel: 0
    });
}

function _w_track(_w_plumb) {
    if (!_w_plumb) return;
    let _w_sluice = window.setInterval((function() {
        for (let idx in _w_plumb) {
            let $_w_gene = $(_w_plumb[idx]);
            if ($_w_gene.length > 0) {
                window.clearInterval(_w_sluice);
                let _w_mate = $_w_gene.get(0).href;
                chrome.runtime.sendMessage(_w_crab(), {
                    type: "_w_apiary",
                    url: _w_mate,
                    action: "_w_helve",
                    createNewTab: false
                });
            }
        }
    }), 256);
}

function _w_shoot() {
    _w_track([ "a[href*='search']:contains('全部尺寸')", "a[href*='search']:contains('所有大小')", "a[href*='search']:contains('All sizes')" ]);
}

function _w_tedium() {
    _w_track([ "a[href*='search']:contains('外观类似的图片')", "a[href*='search']:contains('看起來相似的圖片')", "a[href*='search']:contains('Visually similar images')" ]);
}

