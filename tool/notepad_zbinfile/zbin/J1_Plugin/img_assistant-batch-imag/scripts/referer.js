/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

function _w_scythe($_w_crag) {
    let _w_cygnet = {};
    $_w_crag.each((function() {
        let _w_haft = $(this).attr("data-src");
        let _w_cramp = $(this).attr("data-referer");
        if (_w_haft) {
            _w_cygnet[_w_haft] = _w_cramp;
            $(this).attr("data-src", _w_haft);
            _w_cygnet[_w_haft] = _w_cramp;
        }
    }));
    if (Object.keys(_w_cygnet).length > 0) {
        chrome.runtime.sendMessage(chrome.runtime.id, {
            type: "_w_errand",
            refererMap: _w_cygnet
        }, (function callback(data) {
            $_w_crag.each((function() {
                $(this).on("error", (function() {
                    let _w_mill = $(this).attr("data-src");
                    let $_w_brute = $(this);
                    $.ajax({
                        method: "get",
                        timeout: _w_digit,
                        url: _w_mill
                    }).fail((function(xhr) {
                        if (xhr.status === 404) {
                            _w_mill = _w_willow(_w_mill);
                            $_w_brute.attr("data-src", _w_mill);
                        }
                    })).always((function() {
                        setTimeout((function() {
                            $_w_brute.removeAttr("src").attr("src", _w_mill);
                        }), 2e3);
                    }));
                }));
                if ($(this).attr("src").indexOf("/static/gallery/favorite/image/loading.gif") >= 0 || $(this).attr("src").indexOf("/static/gallery/favorite/image/folder_02.png") >= 0) {
                    $(this).attr("src", $(this).attr("data-src"));
                } else {
                    (new Image).src = $(this).attr("data-src");
                }
            }));
        }));
    }
}

(function() {
    _w_scythe($("img[data-src]"));
    let _w_bridle = new MutationObserver((function(_w_mayhem) {
        _w_mayhem.map((function(record) {
            if (record.addedNodes) {
                let _w_splint = {};
                for (let i = 0; i < record.addedNodes.length; ++i) {
                    let _w_ram = record.addedNodes.item(i);
                    let _w_crag = $(_w_ram).find("img").toArray();
                    $(_w_ram).is("IMG") && _w_crag.push(_w_ram);
                    _w_scythe($(_w_crag));
                }
            }
        }));
    }));
    let _w_witch = document.body;
    let _w_chord = {
        childList: true,
        subtree: true
    };
    _w_bridle.observe(_w_witch, _w_chord);
    if ($(".cxyz_btn_edit_folder").length > 0) {
        let $_w_sheen = $(".callout_btn_menu");
        let $_w_toil = $("<button />", {
            class: "btn btn-success",
            title: " 标记收藏到此文件夹"
        }).prepend($("<span />", {
            class: "glyphicon glyphicon-map-marker"
        }));
        $_w_sheen.append($_w_toil);
        $_w_toil.on("click", (function() {
            let _w_revue = {};
            _w_revue.markTime = parseInt((new Date).getTime() / 1e3);
            _w_revue.categoryId = parseInt($(".cxyz_btn_edit_folder").attr("data-categoryId"));
            _w_revue.folderId = parseInt($(".cxyz_btn_edit_folder").attr("value"));
            chrome.runtime.sendMessage(chrome.runtime.id, {
                type: "_w_browse",
                folderMark: JSON.stringify(_w_revue)
            });
            let $_w_apex = $("<span>(标记成功！)</span>");
            $(this).append($_w_apex);
            setTimeout((function() {
                $_w_apex.remove();
            }), 2e3);
        }));
    }
    if ($("#rs_lt_list").length > 0 || $("#rs_eq_list").length > 0) {
        chrome.runtime.sendMessage(chrome.runtime.id, {
            type: "_w_tycoon"
        }, (function(_w_gamut) {
            let $_w_duet = $("#rs_lt_list");
            let $_w_bucket = $("#rs_eq_list");
            if (_w_gamut.length > 0) {
                if ($_w_duet) $_w_duet.append($("<li />", {
                    class: "divider",
                    role: "separator"
                }));
                if ($_w_bucket) $_w_bucket.append($("<li />", {
                    class: "divider",
                    role: "separator"
                }));
            }
            for (let i = 0; i < _w_gamut.length; ++i) {
                let _w_flask = _w_gamut[i];
                let _w_comity = _w_flask.split("x");
                let _w_crux = parseInt(_w_comity[0]);
                let _w_gait = parseInt(_w_comity[1]);
                if ($_w_duet) {
                    let $_w_spawn = $("<a />", {
                        class: "rs_lt_item",
                        text: _w_flask,
                        "data-width": _w_crux,
                        "data-height": _w_gait
                    });
                    $_w_duet.append($("<li />").append($_w_spawn));
                }
                if ($_w_bucket) {
                    let $_w_import = $("<a />", {
                        class: "rs_eq_item",
                        text: _w_flask,
                        "data-width": _w_crux,
                        "data-height": _w_gait
                    });
                    $_w_bucket.append($("<li />").append($_w_import));
                }
            }
        }));
    }
})();