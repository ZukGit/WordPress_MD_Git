/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

window._w_poster = null;

window._w_elm = "";

window._w_crater = null;

window._w_sprout = null;

window._w_spat = null;

window._w_baton = [];

window._w_lure = null;

window._w_barge = null;

window._w_valor = null;

window._w_spin = null;

window._w_pullet = null;

chrome.tabs.getCurrent((function(tab) {
    window._w_lure = tab.id;
    $(initEditor);
}));

window._w_sliver = {
    timeout: 512,
    lastExeTime: new Date,
    timer: null,
    updateStatics: false,
    delayAgain: true
};

window._w_magpie = {
    timeout: 2048,
    lastExeTime: new Date,
    timer: null,
    updateStatics: false,
    delayAgain: true
};

function _w_code(_w_peer, _w_mantle) {
    let _w_saddle = "";
    if (!_w_peer.startsWith("data:image")) {
        let _w_acorn = {};
        _w_acorn[_w_peer] = _w_mantle;
        _w_tussle()._w_sway(_w_acorn, _w_lure, true);
        _w_saddle = _w_peer.substring(_w_peer.lastIndexOf("/") + 1);
    } else {
        _w_saddle = "ia_" + _w_hamper(16) + ".png";
    }
    _w_vim(_w_peer, _w_saddle, false, false);
}

function _w_fern(_w_peer, _w_mantle) {
    if (!_w_peer.startsWith("data:image")) {
        let _w_acorn = {};
        _w_acorn[_w_peer] = _w_mantle;
        _w_tussle()._w_sway(_w_acorn, _w_lure, true);
        $("#watermarkImageUrl").prop("value", _w_peer).attr("data-referer", _w_mantle);
    }
    chrome.tabs.update(_w_lure, {
        active: true
    }, (function(tab) {
        chrome.windows.update(tab.windowId, {
            focused: true,
            drawAttention: false
        }, (function(window) {}));
    }));
    _w_sanity(_w_peer, false);
}

function _w_vim(_w_peer, _w_saddle, _w_recipe, _w_hut) {
    if (!_w_poster) _w_poster = new Image;
    if (_w_recipe) {
        _w_escort(_w_poster, _w_saddle, _w_hut);
    } else {
        _w_poster.onload = function() {
            if (!_w_recipe) {
                $("#scaleRate").prop("value", 1);
                $("#scWidth").prop("value", _w_poster.width);
                $("#scWidth").attr("max", _w_poster.width);
                $("#scHeight").prop("value", _w_poster.height);
                $("#scHeight").attr("max", _w_poster.height);
                window._w_elm = _w_saddle;
            }
            _w_escort(this, _w_saddle, _w_hut);
        };
        _w_poster.src = _w_peer;
    }
}

function _w_sanity(_w_ream, _w_recipe) {
    if (!_w_spin) _w_spin = new Image;
    if (_w_recipe) {
        _w_ledger();
    } else {
        _w_spin.onload = function() {
            if (!_w_recipe) {
                $("#wmImageCutUp").prop("value", null);
                $("#wmImageCutRight").prop("value", null);
                $("#wmImageCutDown").prop("value", null);
                $("#wmImageCutLeft").prop("value", null);
                $("#watermarkScaleRate").prop("value", 1);
                $("#wmScWidth").attr("max", _w_spin.width).prop("value", _w_spin.width);
                $("#wmScHeight").attr("max", _w_spin.height).prop("value", _w_spin.height);
                $("#brToAlpha").prop("checked", false);
                $("#brToAlphaReverse").prop("checked", false).prop("disabled", true);
                $("#pixelAlphaRateRange").prop("value", 1).prop("disabled", true);
                if (_w_ream == window._w_barge.watermarkImageUrl) {
                    $("#wmImageCutUp").prop("value", window._w_barge.imageCutUp);
                    $("#wmImageCutRight").prop("value", window._w_barge.imageCutRight);
                    $("#wmImageCutDown").prop("value", window._w_barge.imageCutDown);
                    $("#wmImageCutLeft").prop("value", window._w_barge.imageCutLeft);
                    $("#watermarkScaleRate").prop("value", window._w_barge.watermarkImageWidth / _w_spin.watermarkImageHeight);
                    $("#wmScWidth").prop("value", window._w_barge.watermarkImageWidth);
                    $("#wmScHeight").prop("value", window._w_barge.watermarkImageHeight);
                    $("#brToAlpha").prop("checked", window._w_barge.brToAlpha);
                    $("#brToAlphaReverse").prop("checked", window._w_barge.brToAlphaReverse).prop("disabled", window._w_barge.brToAlpha ? false : true);
                    $("#pixelAlphaRateRange").prop("value", window._w_barge.pixelAlphaRateRange).prop("disabled", window._w_barge.brToAlpha ? false : true);
                }
            }
            _w_ledger();
        };
        _w_spin.src = _w_ream;
    }
}

function _w_notch() {
    let _w_tyro = $("#qrCodeFgColor").prop("value");
    let _w_hoof = $("#qrCodeBgColor").prop("value");
    let _w_yacht = _w_heir(_w_hoof);
    let _w_nova = parseInt($("#qrCodeAlpha").prop("value")) / 100;
    _w_hoof = "RGBA(" + _w_yacht.r + ", " + _w_yacht.g + ", " + _w_yacht.b + ", " + _w_nova + ")";
    let _w_stock = $("#qrCodeSize").prop("value");
    let _w_girdle = $("#qrCodeText").prop("value");
    window._w_pullet = _w_cane(_w_girdle, _w_stock, _w_tyro, _w_hoof);
    setTimeout(_w_ledger, 256);
}

function _w_totem() {
    if (!window._w_barge) window._w_barge = {};
    let _w_dagger = window._w_barge;
    _w_dagger.textWatermark = $(".typeMenuItem.textWatermark").is(".active");
    _w_dagger.imageWatermark = $(".typeMenuItem.imageWatermark").is(".active");
    _w_dagger.qrCodeWatermark = $(".typeMenuItem.qrCodeWatermark").is(".active");
    _w_dagger.font = $("select#fontSelecter option:selected").prop("value");
    _w_dagger.fontSize = parseInt($("#fontSize").prop("value"));
    _w_dagger.fontColor = $("#fontColor").prop("value");
    _w_dagger.fontAlpha = parseInt($("#fontAlpha").prop("value"));
    _w_dagger.borderFont = $("#borderFont:checkbox").is(":checked");
    _w_dagger.text = $("#watermarkText").prop("value");
    _w_dagger.imageAlpha = parseInt($("#imageAlpha").prop("value"));
    if (_w_spin && _w_saliva(_w_spin.src)) {
        _w_dagger.watermarkImageUrl = $("#watermarkImageUrl").prop("value");
        _w_dagger.watermarkImageReferer = $("#watermarkImageReferer").attr("data-referer");
        _w_dagger.imageCutUp = $("#wmImageCutUp").prop("value");
        _w_dagger.imageCutRight = $("#wmImageCutRight").prop("value");
        _w_dagger.imageCutDown = $("#wmImageCutDown").prop("value");
        _w_dagger.imageCutLeft = $("#wmImageCutLeft").prop("value");
        _w_dagger.watermarkImageWidth = $("#wmScWidth").prop("value");
        _w_dagger.watermarkImageHeight = $("#wmScHeight").prop("value");
        _w_dagger.brToAlpha = $("#brToAlpha").prop("checked");
        _w_dagger.brToAlphaReverse = $("#brToAlphaReverse").prop("checked");
        _w_dagger.pixelAlphaRateRange = $("#pixelAlphaRateRange").prop("value");
    }
    _w_dagger.qrCodeFgolor = $("#qrCodeFgColor").prop("value");
    _w_dagger.qrCodeBgolor = $("#qrCodeBgColor").prop("value");
    _w_dagger.qrCodeSize = $("#qrCodeSize").prop("value");
    _w_dagger.qrCodeAlpha = parseInt($("#qrCodeAlpha").prop("value"));
    _w_dagger.qrCodeBorder = parseInt($("#qrCodeBorder").prop("value"));
    _w_dagger.qrCodeText = $("#qrCodeText").prop("value");
    _w_dagger.tileWatermark = $(".presentationMenuItem.tileWatermark").is(".active");
    _w_dagger.locationWatermark = $(".presentationMenuItem.locationWatermark").is(".active");
    _w_dagger.offsetX = parseInt($("#offsetX").prop("value"));
    _w_dagger.offsetY = parseInt($("#offsetY").prop("value"));
    _w_dagger.marginX = parseInt($("#marginX").prop("value"));
    _w_dagger.marginY = parseInt($("#marginY").prop("value"));
    _w_dagger.rotate = parseInt($("#rotation").prop("value"));
    _w_dagger.alignX = parseInt($("#alignX").prop("value"));
    _w_dagger.alignY = parseInt($("#alignY").prop("value"));
    _w_dagger.middleAlignX = $("#middleAlignX").is(":checked");
    _w_dagger.middleAlignY = $("#middleAlignY").is(":checked");
    localStorage["watermarkConfigure"] = JSON.stringify(_w_dagger);
    window._w_barge = _w_dagger;
}

function _w_drill() {
    let _w_dagger = localStorage["watermarkConfigure"];
    if (_w_dagger && _w_dagger.length > 0) {
        _w_dagger = JSON.parse(_w_dagger);
        $("select#fontSelecter option[value='" + _w_dagger.font + "']").attr("selected", true);
        $("#fontSizeRange,#fontSize").prop("value", _w_dagger.fontSize);
        $("#fontColor").prop("value", _w_dagger.fontColor);
        $("#fontAlphaRange,#fontAlpha").prop("value", _w_dagger.fontAlpha);
        $("#borderFont:checkbox").prop("checked", _w_dagger.borderFont);
        $("#middleAlignX").prop("checked", _w_dagger.middleAlignX);
        $("#middleAlignY").prop("checked", _w_dagger.middleAlignY);
        $("#watermarkText").prop("value", _w_dagger.text);
        $("#imageAlphaRange,#imageAlpha").prop("value", _w_dagger.imageAlpha);
        if (_w_dagger.watermarkImageUrl && _w_saliva(_w_dagger.watermarkImageUrl)) {
            $("#watermarkImageUrl").prop("value", _w_dagger.watermarkImageUrl);
            $("#watermarkImageReferer").attr("data-referer", _w_dagger.watermarkImageReferer);
            $("#wmImageCutUp").prop("value", _w_dagger.imageCutUp);
            $("#wmImageCutRight").prop("value", _w_dagger.imageCutRight);
            $("#wmImageCutDown").prop("value", _w_dagger.imageCutDown);
            $("#wmImageCutLeft").prop("value", _w_dagger.imageCutLeft);
            $("#wmScWidth").prop("value", _w_dagger.watermarkImageWidth);
            $("#wmScHeight").prop("value", _w_dagger.watermarkImageHeight);
            $("#brToAlpha").prop("checked", _w_dagger.brToAlpha);
            $("#brToAlphaReverse").prop("checked", _w_dagger.brToAlphaReverse).prop("disabled", _w_dagger.brToAlpha ? false : true);
            $("#pixelAlphaRateRange").prop("value", _w_dagger.pixelAlphaRateRange).prop("disabled", _w_dagger.brToAlpha ? false : true);
        }
        $("#qrCodeFgColor").prop("value", _w_dagger.qrCodeFgolor);
        $("#qrCodeBgColor").prop("value", _w_dagger.qrCodeBgolor);
        $("#qrCodeSizeRange,#qrCodeSize").prop("value", _w_dagger.qrCodeSize);
        $("#qrCodeAlphaRange,#qrCodeAlpha").prop("value", _w_dagger.qrCodeAlpha);
        $("#qrCodeBorderRange,#qrCodeBorder").prop("value", _w_dagger.qrCodeBorder);
        $("#qrCodeText").prop("value", _w_dagger.qrCodeText);
        $("#offsetXRange,#offsetX").prop("value", _w_dagger.offsetX);
        $("#offsetYRange,#offsetY").prop("value", _w_dagger.offsetY);
        $("#marginXRange,#marginX").prop("value", _w_dagger.marginX);
        $("#marginYRange,#marginY").prop("value", _w_dagger.marginY);
        $("#rotationRange,#rotation").prop("value", _w_dagger.rotate);
        $("#alignXRange,#alignX").prop("value", _w_dagger.alignX);
        $("#alignYRange,#alignY").prop("value", _w_dagger.alignY);
        if (_w_dagger.watermarkImageUrl && _w_dagger.watermarkImageUrl.length > 0) {
            if (!_w_dagger.watermarkImageUrl.startsWith("data:image")) {
                let _w_acorn = {};
                _w_acorn[_w_dagger.watermarkImageUrl] = _w_dagger.watermarkImageReferer;
                _w_tussle()._w_sway(_w_acorn, _w_lure, true);
            }
            _w_sanity(_w_dagger.watermarkImageUrl, false);
        }
        if (_w_dagger.textWatermark) {
            $(".typeMenuItem.textWatermark").trigger("click");
        } else if (_w_dagger.imageWatermark) {
            $(".typeMenuItem.imageWatermark").trigger("click");
        } else if (_w_dagger.qrCodeWatermark) {
            $(".typeMenuItem.qrCodeWatermark").trigger("click");
        }
        if (_w_dagger.tileWatermark) {
            $(".presentationMenuItem.tileWatermark").trigger("click");
        } else if (_w_dagger.locationWatermark) {
            $(".presentationMenuItem.locationWatermark").trigger("click");
        }
        window._w_barge = _w_dagger;
    } else {
        _w_lesion();
    }
}

function _w_lesion() {
    let _w_dagger = {
        textWatermark: true,
        imageWatermark: false,
        qrCodeWatermark: false,
        font: "微软雅黑",
        fontSize: 32,
        fontColor: "#000000",
        fontAlpha: 50,
        borderFont: false,
        text: _w_podium("_w_tussle"),
        imageAlpha: 50,
        watermarkImageUrl: "http://www.pullywood.com/ImageAssistant/images/logo.png",
        watermarkImageReferer: "http://www.pullywood.com/",
        imageCutUp: null,
        imageCutRight: null,
        imageCutDown: null,
        imageCutLeft: null,
        watermarkImageWidth: 128,
        watermarkImageHeight: 128,
        brToAlpha: true,
        brToAlphaReverse: false,
        pixelAlphaRateRange: 1,
        qrCodeFgolor: "#008000",
        qrCodeBgolor: "#ffffff",
        qrCodeSize: 128,
        qrCodeAlpha: 32,
        qrCodeBorder: 10,
        qrCodeText: "https://www.pullywood.com/ImageAssistant/",
        tileWatermark: true,
        locationWatermark: false,
        offsetX: 0,
        offsetY: 0,
        marginX: 32,
        marginY: 32,
        rotate: -30,
        alignX: -20,
        alignY: -20,
        middleAlignX: false,
        middleAlignY: false
    };
    localStorage["watermarkConfigure"] = JSON.stringify(_w_dagger);
    _w_drill();
}

function _w_ledger() {
    if (!_w_poster) return;
    _w_gouge(window._w_sliver, (function() {
        _w_vim(_w_poster.src, _w_elm, true, false);
    }), false);
    _w_totem();
}

function _w_kiosk() {
    if (!_w_spin || !_w_poster) return;
    _w_gouge(window._w_sliver, (function() {
        _w_sanity(_w_poster.src, true);
    }), false);
    _w_totem();
}

chrome.runtime.onMessage.addListener((function(message, sender, callback) {
    message && message.type == "_w_cravat" && _w_code(message._w_patch, message._w_hawk);
    message && message.type == "_w_noose" && _w_fern(message._w_patch, message._w_hawk);
}));

function initEditor() {
    $("#header_link_container").append($("<a />", {
        class: "navbar-brand",
        text: _w_podium("_w_reel")
    }).prepend($("<img />", {
        src: "./images/icon128.png",
        id: "brand_text"
    })));
    $("#navbar").append($("<ul />", {
        class: "nav navbar-nav navbar-right"
    }).append($("<li />").append($("<a />", {
        target: "_pullywood_",
        href: "http://www.pullywood.com/",
        text: _w_podium("_w_colony")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-home"
    })))).append($("<li />").append($("<a />", {
        id: "_cxyz_fav_",
        target: "_imageAssistant_favorite",
        href: "./favorite.html",
        text: _w_podium("_w_attic")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-folder-open"
    })))));
    $.getJSON(_w_craft, (function(data) {
        let $_w_sloven = $("#_cxyz_fav_");
        if (data.shortName) {
            let _w_spleen = data.icon;
            let $_w_plank = $("<div />", {
                id: "popup_user_info"
            });
            let $_w_gaggle = $("<span />", {
                text: " [ " + data.shortName + " ] "
            });
            $_w_plank.append($_w_gaggle);
            $_w_sloven.attr("href", _w_craft);
            $_w_sloven.append($_w_plank);
        } else {}
    }));
    $("#imageLoader,#multipleImageInput").attr("accept", "image/" + "*");
    document.onpaste = function(event) {
        let _w_uproar = event.clipboardData.items[0].getAsFile();
        if (!_w_uproar.type || !_w_uproar.type.startsWith("image")) return;
        let _w_crater = new FileReader;
        _w_crater.onload = function(event) {
            _w_vim(event.target.result, _w_bulge(16), false, false);
        };
        _w_crater.readAsDataURL(_w_uproar);
    };
    chrome.fontSettings && chrome.fontSettings.getFontList((function populateFontList(fontArr) {
        fontArr.sort().reverse();
        for (let key in fontArr) {
            let _w_gale = fontArr[key].displayName;
            _w_gale = _w_gale.replace(/^\s\s*/, "").replace(/\s\s*$/, "");
            if (_w_gale.match(/[_\-\s]Italic$/) || _w_gale.match(/[_\-\s](Demi)?[Bb]old$/) || _w_gale.match(/[_\-\s]Medium$/) || _w_gale.match(/[_\-\s](Ultra)?[Ll]ight$/) || _w_gale.match(/[_\-\s]Condensed$/)) _w_baton.push(_w_gale); else {
                _w_baton.push(_w_gale);
            }
        }
        let $_w_horn = $("#fontSelecter");
        for (let i = 0; i < _w_baton.length; ++i) {
            let _w_gale = _w_baton[i];
            $_w_horn.append($("<option />", {
                value: _w_gale,
                text: _w_gale,
                style: "font-family:" + _w_gale + ";",
                selected: window._w_barge && window._w_barge.font && window._w_barge.font == _w_gale
            }));
        }
    }));
    $(window).on("resize", _w_ledger);
    $("#imageLoader").on("change", (function(funEvent) {
        let _w_donor = $(this).get(0).files;
        if (_w_donor.length == 0) return;
        let _w_jamb = _w_donor[0];
        let $_w_miser = $(this);
        if (!_w_crater) _w_crater = new FileReader;
        _w_crater.onload = function(event) {
            _w_vim(event.target.result, _w_jamb.name, false, false);
        };
        _w_crater.readAsDataURL(_w_jamb);
    }));
    $("#scaleRate,#scWidth,#scHeight").on("change", (function() {
        let _w_slew = parseFloat($("#scaleRate").prop("value"));
        let _w_loaf = parseInt($("#scWidth").prop("value"));
        let _w_feud = parseInt($("#scHeight").prop("value"));
        let _w_vomit = parseInt($("#scWidth").prop("max"));
        let _w_raid = parseInt($("#scHeight").prop("max"));
        if ($(this).is("#scaleRate")) {
            _w_loaf = Math.round(_w_vomit * _w_slew);
            _w_feud = Math.round(_w_raid * _w_slew);
        } else if ($(this).is("#scWidth")) {
            _w_slew = _w_loaf / _w_vomit;
            _w_feud = Math.round(_w_raid * _w_slew);
        } else if ($(this).is("#scHeight")) {
            _w_slew = _w_feud / _w_raid;
            _w_loaf = Math.round(_w_vomit * _w_slew);
        } else {
            return;
        }
        $("#scaleRate").prop("value", _w_slew);
        $("#scWidth").prop("value", _w_loaf);
        $("#scHeight").prop("value", _w_feud);
        _w_ledger();
    }));
    $(".typeMenuItem").on("click", (function() {
        $(".typeMenuItem").removeClass("active");
        $(this).addClass("active");
        $(".typeMenuTab").hide();
        if ($(this).is(".textWatermark")) {
            $(".typeMenuTab.textWatermark").show();
            _w_ledger();
        } else if ($(this).is(".imageWatermark")) {
            $(".typeMenuTab.imageWatermark").show();
            _w_kiosk();
        } else if ($(this).is(".qrCodeWatermark")) {
            $(".typeMenuTab.qrCodeWatermark").show();
            _w_notch();
        }
    }));
    $("#fontSelecter").on("change", (function() {
        _w_ledger();
    }));
    $("#fontSizeRange,#fontSize").on("change", (function() {
        if ($(this).is("#fontSizeRange")) {
            $("#fontSize").prop("value", $(this).prop("value"));
        } else if ($(this).is("#fontSize")) {
            $("#fontSizeRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#fontColor").on("input", (function() {
        _w_ledger();
    }));
    $("#fontAlphaRange,#fontAlpha").on("change", (function() {
        if ($(this).is("#fontAlphaRange")) {
            $("#fontAlpha").prop("value", $(this).prop("value"));
        } else if ($(this).is("#fontAlpha")) {
            $("#fontAlphaRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#borderFont").on("change", (function() {
        _w_ledger();
    }));
    $("#watermarkText").on("change", (function() {
        _w_ledger();
    }));
    $("#watermarkImage").on("change", (function(funEvent) {
        let _w_donor = $(this).get(0).files;
        if (_w_donor.length == 0) return;
        let _w_jamb = _w_donor[0];
        let $_w_miser = $(this);
        if (!_w_valor) _w_valor = new FileReader;
        _w_valor.onload = function(event) {
            _w_sanity(event.target.result, false);
        };
        _w_valor.readAsDataURL(_w_jamb);
    }));
    $("#wmImageCutUp,#wmImageCutRight,#wmImageCutDown,#wmImageCutLeft").on("change", (function() {
        if (_w_spin == null) return;
        let _w_parley = parseInt($("#wmImageCutUp").prop("value"));
        _w_parley = _w_parley ? _w_parley : 0;
        let _w_sledge = parseInt($("#wmImageCutRight").prop("value"));
        _w_sledge = _w_sledge ? _w_sledge : 0;
        let _w_dither = parseInt($("#wmImageCutDown").prop("value"));
        _w_dither = _w_dither ? _w_dither : 0;
        let _w_shuck = parseInt($("#wmImageCutLeft").prop("value"));
        _w_shuck = _w_shuck ? _w_shuck : 0;
        let _w_vomit = _w_spin.width - _w_shuck - _w_sledge;
        let _w_raid = _w_spin.height - _w_parley - _w_dither;
        $("#wmScWidth").prop("max", _w_vomit);
        $("#wmScHeight").prop("max", _w_raid);
        $("#watermarkScaleRate").trigger("change");
        _w_ledger();
    }));
    $("#watermarkScaleRate,#wmScWidth,#wmScHeight").on("change", (function() {
        if (_w_spin == null) return;
        let _w_muck = parseFloat($("#watermarkScaleRate").prop("value"));
        let _w_ditch = parseInt($("#wmScWidth").prop("value"));
        let _w_ewer = parseInt($("#wmScHeight").prop("value"));
        let _w_vomit = parseInt($("#wmScWidth").prop("max"));
        let _w_raid = parseInt($("#wmScHeight").prop("max"));
        if ($(this).is("#watermarkScaleRate")) {
            _w_ditch = Math.round(_w_vomit * _w_muck);
            _w_ewer = Math.round(_w_raid * _w_muck);
        } else if ($(this).is("#wmScWidth")) {
            _w_muck = _w_ditch / _w_vomit;
            _w_ewer = Math.round(_w_raid * _w_muck);
        } else if ($(this).is("#wmScHeight")) {
            _w_muck = _w_ewer / _w_raid;
            _w_ditch = Math.round(_w_vomit * _w_muck);
        } else {
            return;
        }
        $("#watermarkScaleRate").prop("value", _w_muck);
        $("#wmScWidth").prop("value", _w_ditch);
        $("#wmScHeight").prop("value", _w_ewer);
        _w_kiosk();
    }));
    $("#brToAlpha,#brToAlphaReverse").on("change", (function() {
        if ($("#brToAlpha").is(":checked")) {
            $("#brToAlphaReverse,#pixelAlphaRateRange").attr("disabled", false);
        } else {
            $("#brToAlphaReverse,#pixelAlphaRateRange").attr("disabled", true);
        }
        _w_ledger();
    }));
    $("#pixelAlphaRateRange").on("change", (function() {
        _w_ledger();
    }));
    $("#imageAlphaRange,#imageAlpha").on("change", (function() {
        if ($(this).is("#imageAlphaRange")) {
            $("#imageAlpha").prop("value", $(this).prop("value"));
        } else if ($(this).is("#imageAlpha")) {
            $("#imageAlphaRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_kiosk();
    }));
    $("#qrCodeFgColor").on("input", (function() {
        _w_notch();
    }));
    $("#qrCodeBgColor").on("input", (function() {
        _w_notch();
    }));
    $("#qrCodeSizeRange,#qrCodeSize").on("change", (function() {
        if ($(this).is("#qrCodeSizeRange")) {
            $("#qrCodeSize").prop("value", $(this).prop("value"));
        } else if ($(this).is("#qrCodeSize")) {
            $("#qrCodeSizeRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_notch();
    }));
    $("#qrCodeAlphaRange,#qrCodeAlpha").on("change", (function() {
        if ($(this).is("#qrCodeAlphaRange")) {
            $("#qrCodeAlpha").prop("value", $(this).prop("value"));
        } else if ($(this).is("#qrCodeAlpha")) {
            $("#qrCodeAlphaRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_notch();
    }));
    $("#qrCodeBorderRange,#qrCodeBorder").on("change", (function() {
        if ($(this).is("#qrCodeBorderRange")) {
            $("#qrCodeBorder").prop("value", $(this).prop("value"));
        } else if ($(this).is("#qrCodeBorder")) {
            $("#qrCodeBorderRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_notch();
    }));
    $("#qrCodeText").on("input", (function() {
        _w_notch();
    }));
    $(".presentationMenuItem").on("click", (function() {
        $(".presentationMenuItem").removeClass("active");
        $(this).addClass("active");
        $(".presentationMenuTab").hide();
        if ($(this).is(".tileWatermark")) {
            $(".presentationMenuTab.tileWatermark").show();
        } else if ($(this).is(".locationWatermark")) {
            $(".presentationMenuTab.locationWatermark").show();
        }
        _w_ledger();
    }));
    $("#offsetXRange,#offsetX").on("change", (function() {
        if ($(this).is("#offsetXRange")) {
            $("#offsetX").prop("value", $(this).prop("value"));
        } else if ($(this).is("#offsetX")) {
            $("#offsetXRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#offsetYRange,#offsetY").on("change", (function() {
        if ($(this).is("#offsetYRange")) {
            $("#offsetY").prop("value", $(this).prop("value"));
        } else if ($(this).is("#offsetY")) {
            $("#offsetYRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#marginXRange,#marginX").on("change", (function() {
        if ($(this).is("#marginXRange")) {
            $("#marginX").prop("value", $(this).prop("value"));
        } else if ($(this).is("#marginX")) {
            $("#marginXRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#marginYRange,#marginY").on("change", (function() {
        if ($(this).is("#marginYRange")) {
            $("#marginY").prop("value", $(this).prop("value"));
        } else if ($(this).is("#marginY")) {
            $("#marginYRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#rotationRange,#rotation").on("change", (function() {
        if ($(this).is("#rotationRange")) {
            $("#rotation").prop("value", $(this).prop("value"));
        } else if ($(this).is("#rotation")) {
            $("#rotationRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#alignXRange,#alignX").on("change", (function() {
        if ($(this).is("#alignXRange")) {
            $("#alignX").prop("value", $(this).prop("value"));
        } else if ($(this).is("#alignX")) {
            $("#alignXRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#alignYRange,#alignY").on("change", (function() {
        if ($(this).is("#alignYRange")) {
            $("#alignY").prop("value", $(this).prop("value"));
        } else if ($(this).is("#alignY")) {
            $("#alignYRange").prop("value", $(this).prop("value"));
        } else {
            return;
        }
        _w_ledger();
    }));
    $("#middleAlignX").on("change", (function() {
        if ($(this).is(":checked")) {
            $("#alignXRange,#alignX").attr("disabled", true);
        } else {
            $("#alignXRange,#alignX").attr("disabled", false);
        }
        _w_ledger();
    }));
    $("#middleAlignY").on("change", (function() {
        if ($(this).is(":checked")) {
            $("#alignYRange,#alignY").attr("disabled", true);
        } else {
            $("#alignYRange,#alignY").attr("disabled", false);
        }
        _w_ledger();
    }));
    $("#downloadCanvasImage").on("click", (function() {
        _w_vim(_w_poster.src, _w_elm, false, true);
    }));
    $("#batchImage").on("click", (function() {
        $("#multipleImageInput").get(0).click();
    }));
    $("#openDownloadFolderBtn").on("click", _w_foil);
    $("#multipleImageInput").on("change", (function() {
        let _w_donor = $(this).get(0).files;
        let $_w_miser = $(this);
        let _w_sonnet = Object.keys(_w_donor);
        let _w_cinder = _w_sonnet.length;
        $(window).off("resize", _w_ledger);
        let _w_grease = document.title;
        (function makeWM() {
            if (_w_sonnet.length == 0) {
                document.title = _w_podium("_w_flail") + _w_podium("_w_lounge");
                setTimeout((function() {
                    document.title = _w_grease;
                }), 2e3);
                $(window).on("resize", _w_ledger);
                _w_ledger();
            } else {
                document.title = _w_podium("_w_flail") + (_w_cinder - _w_sonnet.length) + "/" + _w_cinder;
                let _w_jamb = _w_donor[_w_sonnet.shift()];
                if (!_w_crater) _w_crater = new FileReader;
                _w_crater.onload = function(event) {
                    let _w_prank = (new Date).getTime();
                    _w_vim(event.target.result, _w_jamb.name, false, true);
                    let _w_hinge = (new Date).getTime();
                    let _w_plain = _w_hinge - _w_prank;
                    let _w_psalm = _w_plain > 256 ? _w_plain * 2 : 256;
                    (function ctAction() {
                        chrome.downloads.search({
                            urlRegex: "^data:image/.*$",
                            state: "in_progress"
                        }, (function(data) {
                            if (data.length > 2) {
                                setTimeout(ctAction, _w_psalm);
                            } else {
                                makeWM();
                            }
                        }));
                    })();
                };
                _w_crater.readAsDataURL(_w_jamb);
            }
        })();
    }));
    $("#resetConfigure").on("click", (function() {
        _w_lesion();
        _w_ledger();
    }));
    document.title = _w_podium("_w_hoard");
    $("#editor_i18n_1001").html(_w_podium("_w_hail"));
    $("#editor_i18n_1002").html(_w_podium("_w_temper"));
    $("#editor_i18n_1003").html(_w_podium("_w_nib"));
    $("#editor_i18n_1004").html(_w_podium("_w_pygmy"));
    $("#editor_i18n_1005").html(_w_podium("_w_attire"));
    $("#editor_i18n_1006").html(_w_podium("_w_tare"));
    $("#editor_i18n_1007").html(_w_podium("_w_rubble"));
    $("#editor_i18n_1008").html(_w_podium("_w_artery"));
    $("#editor_i18n_1009").html(_w_podium("_w_snarl"));
    $("#editor_i18n_1010").html(_w_podium("_w_span"));
    $("#editor_i18n_1011").html(_w_podium("_w_wraith"));
    $("#editor_i18n_1012").html(_w_podium("_w_fold"));
    $("#editor_i18n_1013").html(_w_podium("_w_safe"));
    $("#editor_i18n_1014").html(_w_podium("_w_clout"));
    $("#editor_i18n_1015").html(_w_podium("_w_scurvy"));
    $("#wmImageCutUp").attr("placeHolder", _w_podium("_w_jamb"));
    $("#wmImageCutRight").attr("placeHolder", _w_podium("_w_prop"));
    $("#wmImageCutDown").attr("placeHolder", _w_podium("_w_grease"));
    $("#wmImageCutLeft").attr("placeHolder", _w_podium("_w_fast"));
    $("#editor_i18n_1020").html(_w_podium("_w_veneer"));
    $("#editor_i18n_1021").html(_w_podium("_w_loom"));
    $("#editor_i18n_1022").html(_w_podium("_w_sermon"));
    $("#editor_i18n_1023").html(_w_podium("_w_bore"));
    $("#editor_i18n_1024").html(_w_podium("_w_morsel"));
    $("#editor_i18n_1025").html(_w_podium("_w_fascia"));
    $("#editor_i18n_1026").html(_w_podium("_w_fidget"));
    $("#editor_i18n_1027").html(_w_podium("_w_venom"));
    $("#editor_i18n_1028").html(_w_podium("_w_crux"));
    $("#editor_i18n_1029").html(_w_podium("_w_query"));
    $("#editor_i18n_1030").html(_w_podium("_w_curd"));
    $("#editor_i18n_1031").html(_w_podium("_w_prose"));
    $("#editor_i18n_1032").html(_w_podium("_w_psyche"));
    $("#editor_i18n_1033").html(_w_podium("_w_vault"));
    $("#editor_i18n_1034").html(_w_podium("_w_pier"));
    $("#editor_i18n_1035").html(_w_podium("_w_marvel"));
    $("#editor_i18n_1036").html(_w_podium("_w_gutter"));
    $("#editor_i18n_1037").html(_w_podium("_w_warp"));
    $("#editor_i18n_1038").html(_w_podium("_w_coffer"));
    $("#editor_i18n_1039").html(_w_podium("_w_scar"));
    $("#editor_i18n_1040").html(_w_podium("_w_almond"));
    $("#editor_i18n_1041").html(_w_podium("_w_squash"));
    $("#editor_i18n_1042").html(_w_podium("_w_lore"));
    $("#editor_i18n_1043").html(_w_podium("_w_sprout"));
    $("#editor_i18n_1044").html(_w_podium("_w_mania"));
    $("#editor_i18n_1045").html(_w_podium("_w_anemia"));
    $("#editor_i18n_1046").html(_w_podium("_w_tycoon"));
    $("#editor_i18n_1047").html(_w_podium("_w_tumult"));
    _w_drill();
}

function _w_escort(_w_crypt, _w_saddle, _w_hut) {
    if (!_w_sprout) _w_sprout = $("#canvas").get(0);
    let $_w_croak = $(_w_sprout).parent();
    _w_sprout.width = $("#scWidth").prop("value");
    _w_sprout.height = $("#scHeight").prop("value");
    let _w_vomit = _w_sprout.parentElement.offsetWidth;
    if (_w_sprout.width <= _w_vomit) {
        _w_sprout.style.width = _w_sprout.width + "px";
        _w_sprout.style.height = _w_sprout.height + "px";
        $("#displayRate").html(_w_podium("_w_flare") + "100%");
    } else {
        _w_sprout.style.width = _w_vomit + "px";
        _w_sprout.style.height = _w_vomit / _w_sprout.width * _w_sprout.height + "px";
        $("#displayRate").html(_w_podium("_w_flare") + (_w_vomit / _w_sprout.width * 100).toFixed(1) + "%");
    }
    if (!_w_spat) _w_spat = _w_sprout.getContext("2d");
    _w_spat.drawImage(_w_crypt, 0, 0, _w_sprout.width, _w_sprout.height);
    let _w_savant = function(_w_group) {
        let _w_chisel = $("#watermarkText").prop("value");
        if (_w_chisel.length == 0) return;
        let _w_shred = parseInt($("#fontSize").prop("value"));
        let _w_gutter = _w_heir($("#fontColor").prop("value"));
        let _w_speck = parseInt($("#fontAlpha").prop("value")) / 100;
        let _w_strip = "rgba(" + _w_gutter.r + ", " + _w_gutter.g + ", " + _w_gutter.b + ", " + _w_speck + ")";
        _w_spat.font = _w_shred + "px " + $("select#fontSelecter option:selected").prop("value");
        _w_spat.strokeStyle = _w_strip;
        _w_spat.fillStyle = _w_strip;
        let _w_solo = {};
        _w_solo.width = _w_spat.measureText(_w_chisel).width;
        _w_solo.height = _w_shred;
        _w_group(_w_solo, (function(x, y) {
            if ($("#borderFont:checkbox").is(":checked")) {
                _w_spat.strokeText(_w_chisel, x, y + _w_solo.height);
            } else {
                _w_spat.fillText(_w_chisel, x, y + _w_solo.height);
            }
        }));
    };
    let _w_pulp = null;
    let _w_huddle = function(_w_group) {
        if (_w_spin == null) return;
        if (_w_pulp == null) {
            let _w_parley = parseInt($("#wmImageCutUp").prop("value"));
            _w_parley = _w_parley ? _w_parley : 0;
            let _w_sledge = parseInt($("#wmImageCutRight").prop("value"));
            _w_sledge = _w_sledge ? _w_sledge : 0;
            let _w_dither = parseInt($("#wmImageCutDown").prop("value"));
            _w_dither = _w_dither ? _w_dither : 0;
            let _w_shuck = parseInt($("#wmImageCutLeft").prop("value"));
            _w_shuck = _w_shuck ? _w_shuck : 0;
            let _w_bigot = _w_spin.width - _w_shuck - _w_sledge;
            let _w_midget = _w_spin.height - _w_parley - _w_dither;
            let _w_fold = document.createElement("canvas");
            _w_fold.width = _w_bigot;
            _w_fold.height = _w_midget;
            let _w_vermin = _w_fold.getContext("2d");
            _w_vermin.drawImage(_w_spin, _w_shuck, _w_parley, _w_bigot, _w_midget, 0, 0, _w_bigot, _w_midget);
            if ($("#brToAlpha").is(":checked")) {
                let _w_niche = $("#brToAlphaReverse").is(":checked");
                let _w_toll = parseFloat($("#pixelAlphaRateRange").prop("value"));
                let _w_clamp = _w_vermin.getImageData(0, 0, _w_fold.width, _w_fold.height);
                let _w_awl = _w_clamp.data.length;
                for (let i = 0; i < _w_awl; i += 4) {
                    let _w_viand = .299 * _w_clamp.data[i] + .587 * _w_clamp.data[i + 1] + .114 * _w_clamp.data[i + 2];
                    let _w_oracle = _w_viand;
                    if (_w_niche) {
                        _w_oracle = 255 - _w_oracle;
                    }
                    _w_oracle = _w_oracle * _w_toll;
                    if (_w_clamp.data[i + 3] > 0) _w_clamp.data[i + 3] = _w_oracle;
                }
                _w_vermin.putImageData(_w_clamp, 0, 0);
            }
            _w_pulp = _w_fold;
        }
        let _w_boast = parseInt($("#wmScWidth").prop("value"));
        let _w_alert = parseInt($("#wmScHeight").prop("value"));
        let _w_bonnet = parseInt($("#imageAlpha").prop("value")) / 100;
        let _w_solo = {};
        _w_solo.width = _w_boast;
        _w_solo.height = _w_alert;
        _w_group(_w_solo, (function(x, y) {
            _w_spat.save();
            _w_spat.globalAlpha = _w_bonnet;
            _w_spat.drawImage(_w_pulp, 0, 0, _w_pulp.width, _w_pulp.height, x, y, _w_boast, _w_alert);
            _w_spat.restore();
        }));
    };
    let _w_tangle = function(_w_group) {
        if (window._w_pullet == null) return;
        let _w_boast = window._w_pullet.width;
        let _w_alert = window._w_pullet.height;
        let _w_hoof = $("#qrCodeBgColor").prop("value");
        let _w_yacht = _w_heir(_w_hoof);
        let _w_nova = parseInt($("#qrCodeAlpha").prop("value")) / 100;
        _w_hoof = "RGBA(" + _w_yacht.r + ", " + _w_yacht.g + ", " + _w_yacht.b + ", " + _w_nova + ")";
        let _w_eulogy = parseInt($("#qrCodeBorderRange").prop("value"));
        let _w_solo = {};
        _w_solo.width = _w_boast + 2 * _w_eulogy;
        _w_solo.height = _w_alert + 2 * _w_eulogy;
        _w_group(_w_solo, (function(x, y) {
            let _w_con = _w_spat.fillStyle;
            _w_spat.fillStyle = _w_hoof;
            _w_spat.fillRect(x, y, _w_boast + 2 * _w_eulogy, _w_alert + 2 * _w_eulogy);
            _w_spat.fillStyle = _w_con;
            _w_spat.drawImage(window._w_pullet, 0, 0, window._w_pullet.width, window._w_pullet.height, x + _w_eulogy, y + _w_eulogy, _w_boast, _w_alert);
        }));
    };
    let _w_nil = function(_w_group) {
        if ($(".typeMenuItem.textWatermark").is(".active")) {
            _w_savant(_w_group);
        } else if ($(".typeMenuItem.imageWatermark").is(".active")) {
            _w_huddle(_w_group);
        } else if ($(".typeMenuItem.qrCodeWatermark").is(".active")) {
            _w_tangle(_w_group);
        }
    };
    let _w_botany = function() {
        let _w_bout = {};
        _w_bout.width = parseInt($("#marginX").prop("value"));
        _w_bout.height = parseInt($("#marginY").prop("value"));
        let _w_hoist = parseInt($("#rotation").prop("value"));
        let _w_vanity = {};
        _w_vanity.x = parseInt($("#offsetX").prop("value"));
        _w_vanity.y = parseInt($("#offsetY").prop("value"));
        let _w_lyric = Math.sqrt(Math.pow(_w_sprout.width + Math.abs(_w_vanity.x), 2) + Math.pow(_w_sprout.height + Math.abs(_w_vanity.y), 2));
        let _w_group = function(_w_solo, _w_strait) {
            _w_solo.width += _w_bout.width;
            _w_solo.height += _w_bout.height;
            _w_spat.rotate(_w_hoist * Math.PI / 180);
            for (let x = 0; x < _w_lyric; x += _w_solo.width) {
                for (let y = 0; y < _w_lyric; y += _w_solo.height) {
                    _w_strait(x + _w_bout.width + _w_vanity.x, y + _w_vanity.y);
                }
            }
            for (let x = 0; x < _w_lyric; x += _w_solo.width) {
                for (let y = -_w_solo.height; y > -_w_lyric; y -= _w_solo.height) {
                    _w_strait(x + _w_bout.width + _w_vanity.x, y + _w_vanity.y);
                }
            }
            for (let x = -_w_solo.width; x > -_w_lyric; x -= _w_solo.width) {
                for (let y = -_w_solo.height; y > -_w_lyric; y -= _w_solo.height) {
                    _w_strait(x + _w_bout.width + _w_vanity.x, y + _w_vanity.y);
                }
            }
            for (let x = -_w_solo.width; x > -_w_lyric; x -= _w_solo.width) {
                for (let y = 0; y < _w_lyric; y += _w_solo.height) {
                    _w_strait(x + _w_bout.width + _w_vanity.x, y + _w_vanity.y);
                }
            }
        };
        _w_nil(_w_group);
    };
    let _w_dearth = function() {
        let _w_maxim = parseInt($("#alignX").prop("value"));
        let _w_sheaf = parseInt($("#alignY").prop("value"));
        let _w_quill = $("#middleAlignX").is(":checked");
        let _w_valve = $("#middleAlignY").is(":checked");
        let _w_group = function(_w_solo, _w_strait) {
            let _w_void = {
                x: 0,
                y: 0
            };
            if (_w_quill) {
                _w_void.x = Math.round(_w_sprout.width / 2 - _w_solo.width / 2);
            } else if (_w_maxim > 0) {
                _w_void.x = _w_maxim;
            } else if (_w_maxim < 0) {
                _w_void.x = _w_sprout.width - _w_solo.width + _w_maxim + 1;
            }
            if (_w_valve) {
                _w_void.y = Math.round(_w_sprout.height / 2 - _w_solo.height / 2);
            } else if (_w_sheaf > 0) {
                _w_void.y = _w_sheaf;
            } else if (_w_sheaf < 0) {
                _w_void.y = _w_sprout.height - _w_solo.height + _w_sheaf + 1;
            }
            _w_strait(_w_void.x, _w_void.y);
        };
        _w_nil(_w_group);
    };
    if ($(".presentationMenuItem.tileWatermark").is(".active")) {
        _w_botany();
    } else if ($(".presentationMenuItem.locationWatermark").is(".active")) {
        _w_dearth();
    }
    _w_saddle = "watermark_" + _w_saddle;
    _w_saddle = _w_saddle.replace(/^(.*?)(\.[\w]{3,4})?$/, "$1.png");
    let _w_lien = function() {
        blobUtil.canvasToBlob(_w_sprout).then((function(blob) {
            chrome.downloads.download({
                url: blobUtil.createObjectURL(blob),
                filename: _w_cavity(_w_podium("manifest_ext_name") + "/" + _w_podium("_w_ration") + "/" + _w_saddle),
                saveAs: false,
                conflictAction: "uniquify"
            });
        }));
    };
    if (_w_hut) {
        _w_lien();
    }
    let _w_sinew = parseInt(_w_sprout.style.width.replace("px", ""));
    let _w_stance = parseInt(_w_sprout.style.height.replace("px", ""));
    $(_w_sprout).parent().off("mouseover mouseout mousemove");
    if (_w_sinew < _w_sprout.width) {
        let _w_ruse = function(event) {
            let _w_croak = $(_w_sprout).parent().get(0);
            let _w_doyen = _w_croak.offsetTop;
            let _w_grief = _w_croak.offsetLeft;
            let _w_siege = event.pageX - _w_grief;
            let _w_penury = event.pageY - _w_doyen;
            let _w_dirge = -(_w_sprout.width - _w_sinew) * _w_siege / _w_sinew;
            let _w_choir = -(_w_sprout.height - _w_stance) * _w_penury / _w_stance;
            $(_w_sprout).css("margin-top", _w_choir);
            $(_w_sprout).css("margin-left", _w_dirge);
        };
        $(_w_sprout).parent().on("mouseover", (function(event) {
            $(_w_sprout).css("width", _w_sprout.width);
            $(_w_sprout).css("height", _w_sprout.height);
            $(this).off("mousemove").on("mousemove", _w_ruse);
        })).on("mouseout", (function() {
            $(_w_sprout).css("width", _w_sinew);
            $(_w_sprout).css("height", _w_stance);
            $(_w_sprout).css("margin", "0px auto");
            $(this).off("mousemove", _w_ruse);
        }));
    }
}