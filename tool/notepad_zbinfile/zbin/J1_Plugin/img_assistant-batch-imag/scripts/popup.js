/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

$((function() {
    document.title = _w_podium("_w_acorn");
    $("#pop_main").append($("<ul />", {
        class: "nav nav-pills nav-stacked"
    }).append($("<li />").append($("<a />", {
        class: "extBtn",
        href: "#",
        "data-level": 0,
        text: _w_podium("_w_wax")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-flash"
    })))).append($("<li />").append($("<a />", {
        class: "extBtn",
        href: "#",
        "data-level": 1,
        text: _w_podium("_w_tatter")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-warning-sign"
    })))).append($("<li />").append($("<a />", {
        class: "extBtn",
        href: "#",
        "data-level": 2,
        text: _w_podium("_w_puddle")
    }).prepend($("<span />", {
        class: "fa fa-bomb"
    })))).append($("<li />").append($("<a />", {
        id: "multiUrlExtractor",
        class: "multiExtBtn font-weight-bold info",
        target: "_blank",
        href: "./multiUrlExtractor.html",
        text: _w_podium("_w_dearth")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-list-alt"
    })))).append($("<li />", {
        class: "divider"
    })).append($("<li />").append($("<a />", {
        id: "_imageAssistant_qrcode_",
        class: "optionBtn",
        href: "#",
        text: _w_podium("_w_rudder")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-qrcode"
    })))).append($("<li />").append($("<a />", {
        id: "_imageAssistant_editor_",
        class: "optionBtn",
        target: "_imageAssistant_editor_",
        href: "./imageEditor.html",
        text: _w_podium("_w_mien")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-edit"
    })))).append($("<li />", {
        class: "divider"
    })).append($("<li />").append($("<a />", {
        id: "_cxyz_fav_",
        class: "optionBtn",
        target: "_imageAssistant_favorite",
        href: "./favorite.html",
        text: _w_podium("_w_attic")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-folder-open"
    })))).append($("<li />", {
        class: "divider"
    })).append($("<li />").append($("<a />", {
        class: "optionBtn",
        target: "_imageAssistant_options",
        href: "./options.html",
        text: _w_podium("_w_icicle")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-wrench"
    })))).append($("<li />").append($("<a />", {
        class: "optionBtn",
        target: "_imageAssistant_options",
        href: "./options.html?showMsg=about",
        text: _w_podium("_w_unison")
    }).append($("<span />", {
        id: "newVersion",
        text: "new"
    })).prepend($("<span />", {
        class: "glyphicon glyphicon-copyright-mark"
    })))));
    $(".extBtn").click((function() {
        let _w_ferry = $(this).attr("data-level");
        _w_tussle()._w_lasso(_w_ferry);
        window.close();
    }));
    if (localStorage["version"] && localStorage["version"] > chrome.runtime.getManifest().version) {
        $("#newVersion").show();
    }
    $.getJSON(_w_craft, (function(data) {
        let $_w_sloven = $("#_cxyz_fav_");
        if (data.shortName) {
            let _w_spleen = DOMPurify.sanitize(data.icon, {
                WHOLE_DOCUMENT: true
            });
            let $_w_plank = $("<div />", {
                id: "popup_user_info"
            });
            let $_w_reign = $("<img />", {
                src: _w_spleen,
                id: "popup_avatar"
            });
            let $_w_gaggle = $("<span />", {
                text: " " + data.shortName
            });
            $_w_plank.append($_w_reign);
            $_w_plank.append($_w_gaggle);
            $_w_sloven.attr("href", _w_craft);
            $_w_sloven.append($_w_plank);
        } else {}
    }));
    $("#multiUrlExtractor").on("click", (function() {
        _w_tussle()._w_bile();
        return false;
    }));
    $("#_imageAssistant_qrcode_").on("click", (function() {
        chrome.tabs.query({
            active: true,
            currentWindow: true
        }, (function(_w_pore) {
            if (!_w_pore || _w_pore.length === 0) return;
            let _w_fringe = _w_pore[0];
            let _w_loop = _w_fringe.url;
            if (_w_loop.indexOf("#") > 0) {
                _w_loop = _w_loop.substring(0, _w_loop.indexOf("#"));
            }
            _w_affix(_w_loop, false);
        }));
        return false;
    }));
}));