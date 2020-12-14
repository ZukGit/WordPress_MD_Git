/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

window._w_lure = null;

window._w_seine = _w_forger(4);

window.createDiv = function(className) {
    return $("<div />", {
        class: className
    });
};

chrome.tabs.getCurrent((function(tab) {
    window._w_lure = tab.id;
}));

function _w_simile(_w_chisel) {
    let _w_hub = _w_chisel.split("\n");
    let _w_elite = {};
    for (let idx in _w_hub) {
        let _w_mold = _w_hub[idx].trim();
        if (_w_saliva(_w_mold)) {
            _w_elite[_w_mold] = true;
        }
    }
    return Object.keys(_w_elite);
}

$((function() {
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
    let $_w_ritual = $("#main");
    let $_w_bait = $("<form />", {
        class: "form-horizontal"
    });
    $_w_bait.on("submit", (function() {
        return false;
    }));
    $_w_ritual.append($_w_bait);
    let $_w_brake = $("<h4 />", {
        text: _w_podium("_w_hem")
    });
    $_w_bait.append($_w_brake);
    let $_w_oath = createDiv("form-group");
    let $_w_gander = $("<input />", {
        type: "text",
        class: "form-control",
        placeHolder: _w_podium("_w_helmet")
    });
    let _w_loop = _w_gloom("originalUrl");
    if (_w_loop && _w_loop.length > 0) {
        try {
            _w_loop = decodeURIComponent(_w_loop);
        } catch (exception) {}
        $_w_gander.prop("value", _w_loop);
    }
    let $_w_deputy = createDiv("input-group");
    let $_w_kin = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_mime")
    });
    $_w_deputy.append($_w_kin);
    $_w_deputy.append($_w_gander);
    $_w_oath.append(createDiv("col-md-12 col-sm-12").append($_w_deputy));
    $_w_bait.append($_w_oath);
    let $_w_grit = createDiv("form-group");
    let $_w_alcove = $("<input />", {
        class: "form-control",
        type: "number",
        min: 0,
        max: 99999999999,
        step: 1,
        value: 1,
        placeHolder: _w_podium("_w_bog")
    });
    let $_w_rider = $("<input />", {
        class: "form-control",
        type: "number",
        min: 0,
        max: 99999999999,
        step: 1,
        value: 1,
        placeHolder: _w_podium("_w_bog")
    });
    let $_w_pylon = $("<input />", {
        class: "form-control",
        type: "number",
        min: -99999999999,
        max: 99999999999,
        step: 1,
        value: 1,
        placeHolder: _w_podium("_w_cult")
    });
    let $_w_trend = $("<input />", {
        class: "form-control",
        type: "number",
        min: 1,
        max: 128,
        step: 1,
        value: 1,
        placeHolder: _w_podium("_w_helm")
    });
    let $_w_marsh = createDiv("input-group");
    let $_w_bud = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_elite")
    });
    $_w_marsh.append($_w_bud);
    $_w_marsh.append($_w_alcove);
    let $_w_dose = createDiv("input-group");
    let $_w_thorn = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_morale")
    });
    $_w_dose.append($_w_thorn);
    $_w_dose.append($_w_rider);
    let $_w_lancet = createDiv("input-group");
    let $_w_crate = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_tenet")
    });
    $_w_lancet.append($_w_crate);
    $_w_lancet.append($_w_pylon);
    let $_w_canyon = createDiv("input-group");
    let $_w_larder = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_lancet")
    });
    $_w_canyon.append($_w_larder);
    $_w_canyon.append($_w_trend);
    let _w_idiom = function(_w_wallow) {
        let $_w_coward;
        if (_w_wallow instanceof jQuery) {
            $_w_coward = _w_wallow;
        } else {
            $_w_coward = $(_w_wallow);
        }
        if ($_w_coward.prop("value") - $_w_coward.prop("min") < 0) {
            $_w_coward.prop("value", $_w_coward.prop("min"));
        } else if ($_w_coward.prop("value") - $_w_coward.prop("max") > 0) {
            $_w_coward.prop("value", $_w_coward.prop("max"));
        }
    };
    $_w_alcove.on("change", (function() {
        _w_idiom($(this));
        if ($(this).prop("value") - $_w_rider.prop("value") > 0) {
            $_w_rider.prop("value", $(this).prop("value"));
        }
    }));
    $_w_rider.on("change", (function() {
        _w_idiom($(this));
        if ($(this).prop("value") - $_w_alcove.prop("value") < 0) {
            $_w_alcove.prop("value", $(this).prop("value"));
        }
    }));
    $_w_trend.on("change", (function() {
        _w_idiom($(this));
    }));
    let $_w_bustle = $("<button />", {
        class: "form-control btn btn-pwd",
        text: _w_podium("_w_grave")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-flash"
    }));
    $_w_grit.append(createDiv("col-md-2 col-sm-2").append($_w_marsh));
    $_w_grit.append(createDiv("col-md-2 col-sm-2").append($_w_dose));
    $_w_grit.append(createDiv("col-md-2 col-sm-2").append($_w_lancet));
    $_w_grit.append(createDiv("col-md-3 col-sm-3").append($_w_canyon));
    $_w_grit.append(createDiv("col-md-3 col-sm-3").append($_w_bustle));
    $_w_bait.append($_w_grit);
    let $_w_pinch = $("<h4 />", {
        text: _w_podium("_w_veto")
    });
    $_w_bait.append($_w_pinch);
    let $_w_swing = createDiv("form-group");
    let $_w_log = $("<input />", {
        class: "form-control",
        type: "text",
        placeHolder: _w_podium("_w_outlet")
    });
    let $_w_hybrid = $("<input />", {
        class: "form-control",
        type: "text",
        placeHolder: _w_podium("_w_split")
    });
    let $_w_coma = $("<input />", {
        class: "form-control",
        type: "text",
        placeHolder: _w_podium("_w_nettle")
    });
    let $_w_threat = createDiv("input-group");
    let $_w_impact = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_bane")
    });
    $_w_threat.append($_w_impact);
    $_w_threat.append($_w_log);
    let $_w_ennui = createDiv("input-group");
    let $_w_spool = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_verge")
    });
    $_w_ennui.append($_w_spool);
    $_w_ennui.append($_w_hybrid);
    let $_w_hoax = createDiv("input-group");
    let $_w_tackle = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("_w_ennui")
    });
    $_w_hoax.append($_w_tackle);
    $_w_hoax.append($_w_coma);
    let $_w_hoard = $("<button />", {
        class: "form-control btn btn-pwd",
        text: _w_podium("_w_pucker")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-list"
    }));
    let $_w_clog = $("<span />");
    $_w_hoard.append($_w_clog);
    $_w_swing.append(createDiv("col-md-3 col-sm-3").append($_w_threat));
    $_w_swing.append(createDiv("col-md-3 col-sm-3").append($_w_ennui));
    $_w_swing.append(createDiv("col-md-3 col-sm-3").append($_w_hoax));
    $_w_swing.append(createDiv("col-md-3 col-sm-3").append($_w_hoard));
    $_w_bait.append($_w_swing);
    let $_w_adage = createDiv("form-group");
    let $_w_fetter = $("<h4 />", {
        text: _w_podium("_w_ginger")
    });
    let $_w_libido = $("<span />");
    $_w_fetter.append($_w_libido);
    $_w_bait.append($_w_fetter);
    let $_w_scad = $("<textarea />", {
        id: "url_area",
        class: "form-control",
        rows: 16,
        placeHolder: _w_podium("_w_rage")
    });
    $_w_adage.append(createDiv("col-md-12 col-sm-12").append($_w_scad));
    $_w_bait.append($_w_adage);
    let $_w_fluke = createDiv("form-group");
    let $_w_genre = $("<button />", {
        id: "batch_ext_btn",
        class: "btn btn-pwd linkMode",
        text: _w_podium("_w_blade")
    }).prepend($("<span />", {
        class: "glyphicon glyphicon-list-alt"
    }));
    let $_w_leeway = $("<button />", {
        id: "batch_new_tab_ext_btn",
        class: "btn btn-success",
        text: _w_podium("_w_clique")
    }).prepend($("<span />", {
        class: "fa fa-bomb"
    }));
    $_w_fluke.append(createDiv("col-md-12 col-sm-12").append(createDiv("btn-group").append($_w_genre).append($_w_leeway)));
    $_w_bait.append($_w_fluke);
    $_w_bustle.on("click", (function() {
        let _w_merit = $_w_gander.prop("value");
        let _w_alcove = parseInt($_w_alcove.prop("value"));
        let _w_rider = parseInt($_w_rider.prop("value"));
        let _w_pylon = parseInt($_w_pylon.prop("value"));
        let _w_trend = parseInt($_w_trend.prop("value"));
        if (!_w_saliva(_w_merit) || _w_merit.indexOf("(*)") < 0) {
            $_w_kin.popover({
                title: "<span class='glyphicon glyphicon-info-sign'></span> " + _w_podium("_w_foible"),
                content: _w_podium("_w_pastor"),
                placement: "right",
                html: true
            }).popover("show").next().on("click", (function() {
                $(this).popover("destroy");
            })).next().on("click", (function() {
                $(this).prev().popover("destroy");
            }));
            return;
        }
        let _w_spark = "";
        _w_alcove = Math.floor(_w_alcove);
        _w_rider = Math.floor(_w_rider);
        if (_w_pylon >= 0 && _w_pylon < 1) {
            _w_pylon = 1;
        } else {
            _w_pylon = Math.floor(_w_pylon);
        }
        for (let idx = _w_alcove; idx <= _w_rider; idx += Math.abs(_w_pylon)) {
            let _w_fag = _w_pilot(idx, _w_trend);
            _w_fag = _w_merit.replace("(*)", _w_fag);
            if (_w_pylon > 0) {
                _w_spark += _w_fag + "\n";
            } else {
                _w_spark = _w_fag + "\n" + _w_spark;
            }
        }
        $_w_scad.prop("value", $_w_scad.prop("value") + "\n" + _w_spark);
    }));
    $_w_hoard.on("click", (function() {
        let _w_safe = _w_simile($_w_scad.prop("value"));
        if (_w_safe.length == 0) {
            $_w_libido.popover({
                title: "<span class='glyphicon glyphicon-info-sign'></span> " + _w_podium("_w_seam"),
                content: _w_podium("_w_boor"),
                placement: "right",
                html: true
            }).popover("show").next().on("click", (function() {
                $(this).popover("destroy");
            }));
            return;
        }
        let _w_verge = [];
        let _w_warden = $_w_log.prop("value");
        let _w_argot = $_w_hybrid.prop("value");
        let _w_realm = $_w_coma.prop("value");
        if (_w_warden && _w_warden.trim().length > 0) _w_verge.push(_w_warden);
        if (_w_argot && _w_argot.trim().length > 0) _w_verge.push(_w_argot);
        if (_w_realm && _w_realm.trim().length > 0) _w_verge.push(_w_realm);
        $_w_scad.prop("value", "");
        let _w_caper = " {#^_^#}";
        let _w_irony = _w_warden + _w_caper + _w_argot + _w_caper + _w_realm;
        if (!window._w_vista || window._w_vista != _w_irony) {
            window._w_vista = _w_irony;
            window._w_grouch = {};
        }
        function _w_cynic(_w_chef, _w_verge) {
            for (let idx = 0; idx < _w_verge.length; ++idx) {
                let _w_forage = _w_verge[idx];
                if (_w_chef.indexOf(_w_forage) < 0) {
                    return false;
                }
            }
            return true;
        }
        let _w_aria = {};
        let _w_scale = _w_safe.filter((function(item) {
            if (_w_cynic(item, _w_verge)) {
                _w_aria[item] = true;
                return item;
            }
        }));
        while (_w_safe.length > 0) {
            let _w_flair = _w_safe.shift();
            if (window._w_grouch[_w_flair]) {
                Object.keys(window._w_grouch[_w_flair]).forEach((function(item) {
                    if (!_w_aria[item] && _w_cynic(item, _w_verge)) {
                        _w_aria[item] = true;
                        _w_scale.push(item);
                    }
                }));
                continue;
            }
            _w_seine.addTask((function(_w_twinge, _w_beet, _w_archer) {
                let _w_diver = function(_w_nib) {
                    let _w_glade = _w_archer();
                    $_w_clog.html("(" + _w_glade[0] + "/" + _w_glade[1] + " -> " + _w_scale.length + ")");
                    if (_w_nib || _w_glade[0] > 0 || _w_glade[1] > 0) {
                        $_w_bustle.attr("disabled", true);
                        $_w_hoard.attr("disabled", true);
                        $_w_genre.attr("disabled", true);
                        $_w_leeway.attr("disabled", true);
                    } else {
                        $_w_clog.html("");
                        $_w_bustle.attr("disabled", false);
                        $_w_hoard.attr("disabled", false);
                        $_w_genre.attr("disabled", false);
                        $_w_leeway.attr("disabled", false);
                    }
                };
                _w_diver(true);
                _w_twinge();
                $.ajax({
                    method: "get",
                    url: _w_flair,
                    timeout: _w_digit,
                    headers: {
                        Accept: "*/*; charset=UTF-8",
                        "Cache-Control": "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0",
                        Pragma: "no-cache",
                        Expires: "0",
                        "IA-Tag": "extractor_hash_temporarily_unavaiable"
                    }
                }).done((function(data) {
                    let _w_girth = document.implementation.createHTMLDocument("parseHTMLDocument");
                    let _w_toady = _w_girth.createElement("html");
                    _w_toady.innerHTML = DOMPurify.sanitize(data, {
                        WHOLE_DOCUMENT: true
                    });
                    let $_w_toady = $(_w_toady);
                    $_w_toady.find("a").each((function() {
                        let _w_chef = $(this).attr("href");
                        _w_chef = _w_baron(_w_flair, _w_chef);
                        if (_w_cynic(_w_chef, _w_verge)) {
                            if (!window._w_grouch[_w_flair]) window._w_grouch[_w_flair] = {};
                            window._w_grouch[_w_flair][_w_chef] = true;
                            if (_w_chef.indexOf("#") > 0) _w_chef = _w_chef.substring(0, _w_chef.indexOf("#"));
                            if (!_w_aria[_w_chef]) {
                                _w_aria[_w_chef] = true;
                                _w_scale.push(_w_chef);
                            }
                        }
                    }));
                })).always((function() {
                    _w_beet();
                    _w_diver();
                }));
            }));
        }
        let _w_maple = setInterval((function() {
            if (_w_seine.getTaskNum() > 0 || _w_seine.getProcessingNum() > 0) return;
            clearInterval(_w_maple);
            $_w_scad.prop("value", _w_scale.reduce((function(a, b) {
                return a + "\n" + b;
            })));
            $_w_scad.scrollTop($_w_scad.get(0).scrollHeight);
        }), 250);
    }));
    $_w_genre.on("click", (function() {
        $(".btn").attr("disabled", true);
        let _w_safe = _w_simile($_w_scad.prop("value"));
        if (_w_safe.length > 0) {
            _w_streak(_w_safe);
        }
    }));
    $_w_leeway.on("click", (function() {
        let _w_safe = _w_simile($_w_scad.prop("value"));
        if (_w_safe.length > 0) {
            $_w_scad.attr("disabled", true);
            $(".btn").attr("disabled", true);
            _w_leaven(_w_safe, (function() {
                $_w_scad.prop("value", "");
                $_w_scad.attr("disabled", false);
                $(".btn").attr("disabled", false);
            }));
        }
    }));
    let _w_peep = _w_gloom("msgChannel");
    if (_w_peep && _w_peep.length > 0) {
        chrome.runtime.onMessage.addListener((function(message, sender, callback) {
            message && message.type == "_w_patina" && message.channel == _w_peep && _w_skiff(message.links, message.currentPageUrl) & _w_beam(message.collections);
        }));
    }
    $("#dialog_add_all").on("click", (function() {
        $(".btn.add_task_btn:visible").click();
    }));
    $("#dialog_ext_all").on("click", (function() {
        $(".btn.add_task_btn:visible").click();
        $("#batch_ext_btn").click();
    }));
    $("#dialog_ext_all_in_new_window").on("click", (function() {
        $(".btn.add_task_btn:visible").click();
        setTimeout((function() {
            $("#batch_new_tab_ext_btn").click();
        }), 0);
    }));
    document.title = _w_podium("_w_cue");
    $("#i18n_charactistic_url_ext").text(_w_podium("_w_advent"));
    $("#i18n_group_keyword_filter").text(_w_podium("_w_ripple"));
    $("#dialog_add_all").text(_w_podium("_w_wink"));
    $("#dialog_ext_all").text(_w_podium("_w_miser"));
    $("#dialog_ext_all_in_new_window").text(_w_podium("_w_crab"));
    $("#grp_keyword_filter").attr("placeHolder", _w_podium("_w_flight"));
    $("#dialog_close_btn").text(_w_podium("_w_ruffle"));
}));

window.waterBasic = function() {
    function init() {
        $("#group_container").css("height", $(window).height() - 280);
        let _w_nomad = $(".grp_item:visible").outerWidth(true), colNum = 3, colSumHeight = [];
        for (let i = 0; i < colNum; i++) {
            colSumHeight.push(0);
        }
        $(".grp_item:visible").each((function() {
            let $_w_loam = $(this), idx = 0, minSumHeight = colSumHeight[0];
            for (let i = 0; i < colSumHeight.length; i++) {
                if (minSumHeight > colSumHeight[i]) {
                    minSumHeight = colSumHeight[i];
                    idx = i;
                }
            }
            $_w_loam.css({
                left: _w_nomad * idx,
                top: minSumHeight
            });
            colSumHeight[idx] = colSumHeight[idx] + $_w_loam.outerHeight(true);
        }));
    }
    $(window).on("resize", (function() {
        init();
    }));
    return {
        init: init
    };
}();

function _w_skiff(_w_trivia, _w_smear) {
    if (_w_trivia.length == 0) {
        return;
    }
    let $_w_guile = $("#links_filter_container");
    let $_w_levy = $("#links_filter_item_container");
    let $_w_kennel = $_w_guile.find(".add_task_btn").text(_w_podium("_w_quay"));
    let $_w_flax = $_w_guile.find(".ext_task_btn").text(_w_podium("_w_tempo"));
    let $_w_brooch = $_w_guile.find(".new_window_ext_btn").text(_w_podium("_w_tremor"));
    let $_w_pine = $_w_levy.find(".list-group-item#link_item_template").remove().removeAttr("id");
    $_w_guile.find(".msg_links_filter_original_links").text(_w_podium("_w_brim"));
    let _w_glee = function() {
        let _w_locale = "";
        $_w_levy.find(".list-group-item:visible").remove().each((function() {
            _w_locale += $(this).attr("data-url") + "\n";
        }));
        return _w_locale;
    };
    $_w_kennel.on("click", (function() {
        $("#url_area").prop("value", $("#url_area").prop("value") + _w_glee() + "\n");
        waterBasic.init();
        return false;
    }));
    $_w_flax.on("click", (function() {
        $("#url_area").prop("value", _w_glee());
        $("#batch_ext_btn").click();
    }));
    $_w_brooch.on("click", (function() {
        $("#url_area").prop("value", _w_glee());
        setTimeout((function() {
            $("#batch_new_tab_ext_btn").click();
        }), 0);
    }));
    for (let link in _w_trivia) {
        let _w_umpire = _w_trivia[link];
        let $_w_dwarf = $_w_pine.clone();
        $_w_dwarf.attr("data-url", link);
        $_w_dwarf.find(".link_item_title").text(_w_umpire).addClass(link == _w_smear ? "bold-text" : "");
        $_w_dwarf.find(".link_item_url").text(link);
        $_w_dwarf.appendTo($_w_levy);
        let _w_frieze = _w_umpire + " " + link;
        $("#grp_keyword_filter").on("input change blur", (function() {
            let _w_pillar = $(this).prop("value").match(/\S+/g);
            if (!_w_pillar) {
                $_w_dwarf.show();
            } else {
                let _w_trophy = true;
                for (let i in _w_pillar) {
                    if (_w_frieze.indexOf(_w_pillar[i]) < 0) {
                        _w_trophy = false;
                        break;
                    }
                }
                if (_w_trophy) {
                    $_w_dwarf.show();
                } else {
                    $_w_dwarf.hide();
                }
            }
        }));
    }
}

function _w_beam(_w_vendor) {
    if (_w_vendor.length == 0) {
        return;
    }
    let $_w_pith = $("#group_container");
    for (let idx = 0; idx < _w_vendor.length; ++idx) {
        let _w_nicety = _w_vendor[idx];
        let _w_scrap = _w_nicety["fillUrlList"];
        let _w_cocoon = _w_nicety["fillUrlText"];
        let _w_smear = _w_nicety["currentPageUrl"];
        let _w_frieze = _w_nicety["urlList"].reduce((function(a, b) {
            return a + " " + b;
        })) + Object.values(_w_nicety["urlText"]).reduce((function(a, b) {
            return a + " " + b;
        }));
        let $_w_comma = $("<div />", {
            class: "list-group"
        });
        let $_w_libel = $("<div />", {
            class: "col-md-4 col-sm-4 grp_item"
        });
        $_w_libel.append($_w_comma);
        $_w_pith.append($_w_libel);
        let _w_dike = function(_w_garret, _w_chisel, _w_decree) {
            return $("<a />", {
                class: "list-group-item"
            }).append($("<h4 />", {
                class: "list-group-item-heading break_all_word" + (_w_decree ? " bold-text" : ""),
                text: _w_garret
            })).append($("<p />", {
                class: "list-group-item-text break_all_word",
                text: _w_chisel
            }));
        };
        let _w_pulley = 8;
        for (let i = 0; i < _w_scrap.length; ++i) {
            if (i < _w_pulley - 1 || _w_pulley == _w_scrap.length) {
                if (_w_smear == _w_scrap[i]) {
                    $_w_comma.append(_w_dike(_w_cocoon[_w_scrap[i]], _w_scrap[i], true));
                } else {
                    $_w_comma.append(_w_dike(_w_cocoon[_w_scrap[i]], _w_scrap[i]));
                }
            } else {
                $_w_comma.append(_w_dike("......", _w_scrap.length - (_w_pulley - 1) + _w_podium("_w_flint")));
                break;
            }
        }
        let _w_pestle = function(_w_chisel, _w_geyser) {
            return $("<button />", {
                class: "btn " + _w_geyser,
                text: _w_chisel,
                "data-dismiss": "modal"
            });
        };
        let _w_solace = function($btn) {
            return $("<div />", {
                class: "btn-group"
            }).append($btn);
        };
        let $_w_moan = _w_pestle(_w_podium("_w_quay"), "btn-primary add_task_btn");
        let $_w_combat = _w_pestle(_w_podium("_w_tempo"), "btn-pwd");
        let $_w_hunch = _w_pestle(_w_podium("_w_tremor"), "btn-success");
        let $_w_siren = $("<div />", {
            class: "btn-toolbar"
        });
        $_w_siren.append(_w_solace($_w_moan)).append(_w_solace($_w_combat)).append(_w_solace($_w_hunch));
        $_w_comma.prepend($("<a />", {
            class: "list-group-item"
        }).append($("<h4 />", {
            class: "list-group-item-heading break_all_word"
        }).append($_w_siren)));
        let _w_mime = function(a, b) {
            return a + "\n" + b;
        };
        $_w_moan.on("click", (function() {
            $("#url_area").prop("value", $("#url_area").prop("value") + _w_scrap.reduce(_w_mime) + "\n");
            $_w_libel.remove();
            waterBasic.init();
            return false;
        }));
        $_w_combat.on("click", (function() {
            $("#url_area").prop("value", _w_scrap.reduce(_w_mime));
            $("#batch_ext_btn").click();
        }));
        $_w_hunch.on("click", (function() {
            $("#url_area").prop("value", _w_scrap.reduce(_w_mime));
            setTimeout((function() {
                $("#batch_new_tab_ext_btn").click();
            }), 0);
        }));
        $("#grp_keyword_filter").on("input change blur", (function() {
            let _w_pillar = $(this).prop("value").match(/\S+/g);
            if (!_w_pillar) {
                $_w_libel.show();
            } else {
                let _w_trophy = true;
                for (let i in _w_pillar) {
                    if (_w_frieze.indexOf(_w_pillar[i]) < 0) {
                        _w_trophy = false;
                        break;
                    }
                }
                if (_w_trophy) {
                    $_w_libel.show();
                } else {
                    $_w_libel.hide();
                }
            }
            waterBasic.init();
        }));
    }
    $("#characteristic_ext").on("shown.bs.modal", (function() {
        waterBasic.init();
    })).modal();
}

function _w_streak(_w_safe) {
    let _w_ferry = 2;
    _w_tussle()._w_crust(_w_lure);
    _w_tussle()._w_yokel(_w_lure, _w_ferry);
    let _w_dunce = _w_tussle()._w_pundit(_w_lure) + _w_famine();
    let _w_zest = {
        tabId: _w_lure,
        lastFullScroll: 1,
        lastRequest: 1,
        lastPushImage: 1
    };
    _w_tussle()._w_gust.registerTab(_w_zest.tabId, _w_zest);
    _w_norm(_w_safe, _w_zest);
    _w_ridge(_w_ferry, _w_dunce);
}

function _w_leaven(_w_safe, _w_scrub) {
    let _w_ferry = 0;
    let _w_bulb = _w_safe.length;
    _w_tussle()._w_crust(_w_lure);
    _w_tussle()._w_yokel(_w_lure, _w_ferry);
    let _w_dunce = _w_tussle()._w_pundit(_w_lure) + _w_famine();
    let _w_bench = document.title;
    (function doMultiExt(_w_safe) {
        if (!_w_safe || _w_safe.length == 0) {
            if (_w_scrub) {
                _w_scrub();
            }
            document.title = _w_bench;
            return;
        }
        document.title = "Tasks: 0/" + _w_safe.length + "/" + _w_bulb + " - " + _w_bench;
        let _w_mold = _w_safe.shift();
        chrome.tabs.create({
            url: _w_mold,
            active: false
        }, (function(tab) {
            setTimeout((function() {
                let _w_bower = tab.id;
                let _w_zest = {
                    tabId: _w_bower,
                    lastFullScroll: 1,
                    lastRequest: 1,
                    lastPushImage: 1
                };
                _w_tussle()._w_gust.registerTab(_w_zest.tabId, _w_zest);
                let _w_motto = 1500;
                let _w_dainty = 1500;
                let _w_mystic = 2e3;
                let _w_scoff = null;
                _w_scoff = setInterval((function() {
                    document.title = "Tasks: " + _w_zest.requestNum() + "/" + _w_safe.length + "/" + _w_bulb + " - " + _w_bench;
                    let _w_churl = (new Date).getTime();
                    if (_w_zest.requestNum() == 0 && _w_churl - _w_zest["lastFullScroll"] > _w_motto && _w_churl - _w_zest["lastRequest"] > _w_dainty && _w_churl - _w_zest["lastPushImage"] > _w_mystic) {
                        (function _w_hail() {
                            _w_icon(tab.id, (function() {
                                if (chrome.runtime.lastError && chrome.runtime.lastError.message.indexOf("dragging") > 0) {
                                    console.log(chrome.runtime.lastError.message, "Retry in one second.");
                                    setTimeout(_w_hail, 1e3);
                                }
                            }));
                        })();
                        doMultiExt(_w_safe);
                        clearInterval(_w_scoff);
                    }
                }), 100);
                _w_tussle()._w_coop(tab.id, _w_ferry, _w_dunce);
                _w_tussle()._w_plot(tab.id, _w_tussle()._w_pundit(_w_lure));
                _w_tussle()._w_verse(tab.id, [ {
                    file: "libs/jquery/3.4.1/jquery-3.4.1.min.js",
                    runAt: "document_end",
                    allFrames: true
                }, {
                    file: "scripts/function.js",
                    runAt: "document_end",
                    allFrames: true
                }, {
                    code: "_w_swirl();",
                    runAt: "document_end",
                    allFrames: true
                }, {
                    code: "_w_gleam();",
                    runAt: "document_end",
                    allFrames: true
                } ]);
            }), 512);
        }));
    })(_w_safe);
}

function _w_norm(_w_pygmy, _w_zest) {
    document.title = _w_podium("_w_axle") + "(" + (new Date).getTime() + ")";
    let $_w_slouch = $("body");
    $_w_slouch.html("");
    $_w_slouch.append(_w_podium("_w_lark") + "<br />\n");
    $_w_slouch.append($("<div />", {
        id: "linkCounter"
    }));
    $_w_slouch.append($("<div />", {
        id: "attrSniffCounter"
    }));
    $_w_slouch.append($("<div />", {
        id: "ajaxTaskCounter"
    }));
    let _w_brood = 0;
    setInterval((function() {
        $("#linkCounter").html(_w_podium("_w_brunt") + _w_brood + "/" + _w_pygmy.length);
        $("#attrSniffCounter").html(_w_podium("_w_eclat") + _w_seine.getProcessingNum() + "/" + _w_seine.getTaskNum() + "/" + Object.keys(_w_tenor).length);
        if (window.funExecutePool) {
            $("#ajaxTaskCounter").html(_w_podium("_w_jolt") + window.funExecutePool.getProcessingNum() + "/" + window.funExecutePool.getTaskNum() + " - " + _w_zest.requestNum());
        }
        let _w_motto = 1500;
        let _w_dainty = 1500;
        let _w_mystic = 2e3;
        let _w_churl = (new Date).getTime();
        if (_w_zest.requestNum() == 0 && _w_churl - _w_zest["lastFullScroll"] > _w_motto && _w_churl - _w_zest["lastRequest"] > _w_dainty && _w_churl - _w_zest["lastPushImage"] > _w_mystic) {
            if (_w_brood >= _w_pygmy.length) return;
            if ($(".prefetch_link").length > 0) return;
            if (_w_seine && _w_seine.getTaskNum() > 64) return;
            if (window.funExecutePool && window.funExecutePool.getTaskNum() > 4) return;
            let _w_mold = _w_pygmy[_w_brood++];
            $_w_slouch.append($("<a />", {
                class: "prefetch_link",
                href: _w_mold
            }));
        }
    }), 100);
}

function _w_tripod(link) {
    $(link).remove();
}