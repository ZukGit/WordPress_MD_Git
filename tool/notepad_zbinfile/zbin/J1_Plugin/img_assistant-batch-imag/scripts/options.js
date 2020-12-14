/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

$((function() {
    document.title = _w_podium("_w_arena");
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
    $("#sideNavbar").append($("<ul />", {
        class: "nav nav-sidebar"
    }).append($("<li />").append($("<a />", {
        class: "sidbarItem",
        href: "#",
        contentId: "extOption",
        text: _w_podium("_w_saliva")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-wrench"
    })))).append($("<li />").append($("<a />", {
        class: "sidbarItem",
        href: "#",
        contentId: "aboutExt",
        text: _w_podium("_w_guffaw")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-copyright-mark"
    })))));
    $("#defSizeOptTle").append(_w_podium("_w_boom"));
    $("#defSizeOptAlert").append(_w_podium("_w_smirch"));
    $("#sizeOptionTle").append(_w_podium("_w_hoist"));
    $("#defautlOperation").append($("<div />", {
        class: "btn btn-primary",
        id: "selectAllButton",
        text: _w_podium("_w_eddy")
    })).append($("<div />", {
        class: "btn btn-primary",
        id: "selectNoneButton",
        text: _w_podium("_w_mirth")
    }));
    $("#customSizeOption").append(_w_podium("_w_treaty"));
    $("#newItemWidth").attr("placeHolder", _w_podium("_w_demise"));
    $("#newItemHeight").attr("placeHolder", _w_podium("_w_slosh"));
    $("#newItemButton").append(_w_podium("_w_cocoon"));
    $("#aboutExTle").append(_w_podium("_w_reaper"));
    $("#aboutExtCnt").append($("<h4 />", {
        class: "media-heading",
        text: _w_podium("_w_ivory")
    })).append($("<p />", {
        text: _w_podium("_w_repute")
    })).append($("<p />", {
        html: _w_podium("_w_chore")
    })).append($("<div />", {
        class: "pwd-callout pwd-callout-info"
    }).append($("<h4 />", {
        class: "page-header",
        text: _w_podium("_w_toupee")
    })).append($("<p />").append(_w_podium("_w_carp")).append($("<a />", {
        target: "_pullywood_",
        href: "http://www.pullywood.com/ImageAssistant/#docs-download",
        text: chrome.runtime.getManifest().version
    }).append($("<span />", {
        id: "newVersion",
        text: "Latest: " + localStorage["version"]
    })))).append($("<p />").append(_w_podium("_w_mayhem")).append($("<a />", {
        target: "_chromeAppStore_",
        href: "https://chrome.google.com/webstore/detail/dbjbempljhcmhlfpfacalomonjpalpko",
        text: "https://chrome.google.com/webstore/detail/dbjbempljhcmhlfpfacalomonjpalpko"
    }))).append($("<p />").append(_w_podium("_w_muscle")).append($("<a />", {
        target: "_edgeAppStore_",
        href: "https://microsoftedge.microsoft.com/addons/detail/odphnbhiddhdpoccbialllejaajemdio",
        text: "https://microsoftedge.microsoft.com/addons/detail/odphnbhiddhdpoccbialllejaajemdio"
    }))).append($("<p />").append(_w_podium("_w_matrix")).append($("<a />", {
        target: "_firefoxAppStore_",
        href: "https://addons.mozilla.org/zh-CN/firefox/addon/ia-batch-image-downloader/",
        text: "https://addons.mozilla.org/zh-CN/firefox/addon/ia-batch-image-downloader/"
    }))).append($("<p />").append(_w_podium("_w_shroud")).append($("<a />", {
        target: "_source_list_",
        href: "http://www.pullywood.com/publish/imageassistant-resource-list",
        text: "http://www.pullywood.com/publish/imageassistant-resource-list"
    }))).append($("<p />").append(_w_podium("_w_rift")).append($("<a />", {
        target: "_pullywood_",
        href: "https://www.pullywood.com/ImageAssistant/",
        text: _w_podium("_w_cuff")
    }))).append($("<p />").append(_w_podium("_w_lance")).append($("<a />", {
        target: "_pullywood_",
        href: "https://www.pullywood.com/publish/",
        text: _w_podium("_w_glut")
    }))).append($("<p />").append("Twitter: ").append($("<a />", {
        target: "_twitter_",
        href: "https://twitter.com/pullywood",
        text: "https://twitter.com/pullywood"
    }))).append($("<p />").append($("<img />", {
        height: 128,
        src: "./images/wechat_offical.jpg"
    })).append($("<img />", {
        height: 128,
        src: "./images/wechat.jpg"
    })))).append($("<p />").append(_w_podium("_w_scoff")).append(" &copy; 2013 - 2019 ").append($("<a />", {
        target: "_pullywood_",
        href: "http://www.pullywood.com/",
        text: _w_podium("_w_magpie")
    })).append($("<span> | </span>").append($("<a />", {
        target: "_privacy_",
        href: _w_podium("_w_gust"),
        text: _w_podium("_w_solace")
    }))));
    $("#extClickAct").bootstrapSwitch({
        labelText: _w_podium("_w_poncho"),
        onText: _w_podium("_w_intent"),
        offText: _w_podium("_w_icing"),
        labelWidth: 100,
        state: "true" == _w_tussle()._w_sod(),
        onSwitchChange: function(event, state) {
            _w_tussle()._w_mallet(state);
        }
    });
    $("#dyLoadSize").prop("value", _w_tussle()._w_bore()).on("change", (function() {
        let _w_sage = $(this).prop("value");
        _w_tussle()._w_flak(_w_sage);
        $(this).prop("value", _w_tussle()._w_bore());
    }));
    $("#extMaxLoad").prop("value", _w_tussle()._w_addict()).on("change", (function() {
        let _w_grotto = $(this).prop("value");
        _w_tussle()._w_satire(_w_grotto);
        $(this).prop("value", _w_tussle()._w_addict());
    }));
    $("#regexpUrlRule").html(_w_tussle()._w_armada()).on("blur", (function() {
        let _w_harrow = $(this).text();
        let _w_molt = _w_tussle()._w_clown(_w_harrow);
        let _w_score = _w_tussle()._w_armada();
        for (let idx in _w_molt) {
            _w_score = _w_score.replace(_w_molt[idx], "<crule>" + _w_molt[idx] + "</crule>");
        }
        $(this).html(_w_score);
    })).trigger("blur");
    $("#resetRegexpUrlRule").on("click", (function() {
        $("#regexpUrlRule").html("").trigger("blur");
    }));
    $("#regexpUrlTestInput").on("blur", (function() {
        let $_w_glitch = $("#regexpUrlTestOutput");
        $_w_glitch.html("");
        $("#regexpUrlRule").trigger("blur");
        let _w_sloth = $(this).prop("value");
        if (!_w_sloth || _w_sloth.trim("").length == 0) {
            $_w_glitch.html("");
            return;
        } else if (!_w_sloth.startsWith("http://") && !_w_sloth.startsWith("https://")) {
            $_w_glitch.html(_w_podium("_w_spat"));
            return;
        }
        let _w_tenet = _w_tussle()._w_lever(_w_sloth);
        if (!Array.isArray(_w_tenet) || _w_tenet.length == 0) {
            $_w_glitch.html(_w_podium("_w_wig"));
            return;
        } else {
            _w_tenet.forEach((function(matchedRule) {
                $("#regexpUrlRule").html($("#regexpUrlRule").html().replace(matchedRule, "<mrule>" + matchedRule + "</mrule>"));
            }));
        }
        let _w_skit = _w_tussle()._w_rhyme(_w_sloth, 1);
        $_w_glitch.append(_w_podium("_w_slew") + _w_sloth + "\n");
        let $_w_asset = $("<span />", {
            text: _w_podium("_w_merit")
        });
        $_w_glitch.append($_w_asset).append("\n");
        let _w_scamp = new Image;
        _w_scamp.onload = function() {
            $_w_asset.append(this.width + " x " + this.height);
        };
        _w_scamp.onerror = function() {
            _w_scamp.onerror = null;
            $_w_asset.append("N/A");
        };
        _w_scamp.src = _w_sloth;
        _w_skit.forEach((function(matchedUrl) {
            console.log(matchedUrl);
            $_w_glitch.append("<hr />" + _w_podium("_w_salve") + matchedUrl + "\n");
            let $_w_fabric = $("<span />", {
                text: _w_podium("_w_roe")
            });
            $_w_glitch.append($_w_fabric).append("\n");
            let _w_shoddy = new Image;
            _w_shoddy.onload = function() {
                $_w_fabric.append(this.width + " x " + this.height);
            };
            _w_shoddy.onerror = function() {
                _w_shoddy.onerror = null;
                $_w_fabric.append("N/A");
            };
            _w_shoddy.src = matchedUrl;
        }));
    }));
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
    if (localStorage["version"] && localStorage["version"] > chrome.runtime.getManifest().version) {
        $("#newVersion").show();
    }
    let _w_delta = _w_gloom("showMsg");
    if (_w_delta != null && _w_delta == "about") {
        $("#aboutExt").slideDown().siblings().slideUp();
        let $_w_coward = $(".sidbarItem[contentId=aboutExt]").parent();
        $_w_coward.addClass("btn-pwd active");
    } else {
        $("#extOption").slideDown().siblings().slideUp();
        let $_w_coward = $(".sidbarItem[contentId=extOption]").parent();
        $_w_coward.addClass("btn-pwd active");
    }
    let _w_tusk = _w_tussle()._w_ulcer();
    $("#defaultFunnelWidth").val(_w_tusk.width);
    $("#defaultFunnelHeight").val(_w_tusk.height);
    $(".defaultFunnelSize").on("input", (function() {
        _w_tusk.width = $("#defaultFunnelWidth").val();
        _w_tusk.height = $("#defaultFunnelHeight").val();
        _w_tussle()._w_entrée(_w_tusk.width, _w_tusk.height);
    }));
    let _w_gamut = _w_tussle()._w_tycoon();
    let _w_votary = _w_tussle()._w_vent();
    _w_pecan(_w_gamut, _w_votary);
    $("#extClickActOptTle").html(_w_podium("_w_fiend"));
    $("#extClickActOptDesc").html(_w_podium("_w_agony"));
    $("#dyLoadSizeOptTle").html(_w_podium("_w_lease"));
    $("#i18n_dynamic_load_desc").html(_w_podium("_w_vista"));
    $("#i18n_dynamic_load_desc_1").html(_w_podium("_w_rue"));
    $("#extMaxLoadOptTle").html(_w_podium("_w_maxim"));
    $("#i18n_selector_page_max_load_image_desc").html(_w_podium("_w_sock"));
    $("#regexpUrlRuleOptTle").html(_w_podium("_w_zest"));
    $("#i18n_image_url_regexp_replace_expression_desc").html(_w_podium("_w_saddle"));
    $("#i18n_btn_default_rule").html(_w_podium("_w_pawn"));
    $("#i18n_test_url").html(_w_podium("_w_logjam"));
}));

function _w_pecan(_w_gamut, _w_votary) {
    let _w_pistol = [];
    let _w_feat = [];
    function preDealContainer(_w_gamut, _w_feat, _w_pistol) {
        for (let i = 0; i < _w_gamut.length; ++i) {
            let _w_wrath = _w_gamut[i];
            let _w_stupor = _w_gamut[_w_wrath];
            let _w_sprig = _w_stupor.width - _w_stupor.height > 0 ? _w_stupor.width : _w_stupor.height;
            let _w_lathe = _w_stupor.width - _w_stupor.height < 0 ? _w_stupor.width : _w_stupor.height;
            let _w_thatch = _w_sprig + "x" + _w_lathe;
            _w_sprig && _w_lathe && _w_pistol.indexOf(_w_thatch) == -1 && _w_feat.indexOf(_w_thatch) == -1 && _w_feat.push(_w_thatch);
        }
    }
    preDealContainer(_w_votary, _w_pistol, _w_pistol);
    preDealContainer(_w_gamut, _w_feat, _w_pistol);
    _w_pistol = _w_eclat(_w_pistol);
    _w_feat = _w_eclat(_w_feat);
    function htmlContainerFill(_w_gamut, _w_plane, $_w_mitten) {
        for (let i = 0; i < _w_plane.length; ++i) {
            let $_w_blight = $("<div />", {
                class: "btn-group btn-group-sm"
            });
            let _w_wrath = _w_plane[_w_plane[i]];
            let _w_lobe = _w_wrath.width + "x" + _w_wrath.height;
            let $_w_memoir = $("<div />", {
                class: "btn btn-default sizeItemOption",
                value: _w_lobe,
                text: _w_lobe
            });
            _w_gamut.indexOf(_w_lobe) > -1 && $_w_memoir.addClass("btn-pwd active");
            let _w_tact = _w_wrath.height + "x" + _w_wrath.width;
            let $_w_snivel = $("<div />", {
                class: "btn btn-default sizeItemOption",
                value: _w_tact,
                text: _w_tact
            });
            _w_gamut.indexOf(_w_tact) > -1 && $_w_snivel.addClass("btn-pwd active");
            $_w_blight.append($_w_memoir);
            _w_wrath.width - _w_wrath.height != 0 && $_w_blight.append($_w_snivel);
            $_w_mitten.append($_w_blight);
        }
    }
    let $_w_libel = $("#sizeConfigure");
    let $_w_tempo = $("#sizeConfigurExt");
    htmlContainerFill(_w_gamut, _w_pistol, $_w_libel);
    htmlContainerFill(_w_gamut, _w_feat, $_w_tempo);
    $("#selectAllButton").on("click", (function() {
        $("#sizeConfigure .sizeItemOption").each((function() {
            let _w_quota = $(this).attr("value").split("x");
            _w_quota[0] = parseInt(_w_quota[0]);
            _w_quota[1] = parseInt(_w_quota[1]);
            $(this).addClass("btn-pwd active");
            let _w_coward = _w_gamut[$(this).attr("value")];
            _w_tussle()._w_lurch(_w_quota[0], _w_quota[1]);
        }));
    }));
    $("#selectNoneButton").on("click", (function() {
        $("#sizeConfigure .sizeItemOption").each((function() {
            let _w_quota = $(this).attr("value").split("x");
            _w_quota[0] = parseInt(_w_quota[0]);
            _w_quota[1] = parseInt(_w_quota[1]);
            $(this).removeClass("btn-pwd active");
            _w_tussle()._w_minnow(_w_quota[0], _w_quota[1]);
        }));
    }));
    $(".sizeItemOption").on("click", (function() {
        let _w_quota = $(this).attr("value").split("x");
        _w_quota[0] = parseInt(_w_quota[0]);
        _w_quota[1] = parseInt(_w_quota[1]);
        if ($(this).is(".active")) {
            $(this).removeClass("btn-pwd active");
            _w_tussle()._w_minnow(_w_quota[0], _w_quota[1]);
        } else {
            $(this).addClass("btn-pwd active");
            let _w_coward = _w_gamut[$(this).attr("value")];
            _w_tussle()._w_lurch(_w_quota[0], _w_quota[1]);
        }
    }));
    $("#newItemButton").on("click", (function() {
        let _w_crux = parseInt($("#newItemWidth").prop("value"));
        let _w_gait = parseInt($("#newItemHeight").prop("value"));
        _w_crux && _w_gait && _w_crux > 0 && _w_gait > 0 && _w_tussle()._w_lurch(_w_crux, _w_gait);
        window.location.reload();
    }));
    $(".sidbarItem").on("click", (function() {
        $(this).parent().addClass("btn-pwd active").siblings().removeClass("btn-pwd active");
        $("#" + $(this).attr("contentId")).slideDown().siblings().slideUp();
    }));
}