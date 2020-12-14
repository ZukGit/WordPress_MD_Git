/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

window._w_bower = parseInt(_w_gloom("tabId"));

window._w_ferry = parseInt(_w_gloom("fetchLevel"));

window._w_gamut = _w_tussle()._w_tycoon();

window._w_tusk = _w_tussle()._w_ulcer();

window._w_duel = null;

window._w_regime = 0;

window._w_splint = [];

window._w_stamp = {};

window._w_blade = {};

window._w_flight = false;

window._w_lure = null;

window._w_raisin = null;

window._w_dart = null;

window._w_grill = 0;

window._w_jazz = 8;

window._w_gaffe = _w_tussle()._w_addict();

window._w_salmon = null;

window._w_ordeal = _w_cabal(8e3, 16);

window._w_sham = {};

window._w_miasma = _w_forger(4);

chrome.tabs.get(_w_bower, (function(tab) {
    if (!tab) return;
    window._w_raisin = tab.title;
    window._w_dart = tab.url;
    let _w_lope = function() {
        return _w_raisin + " - " + _w_raisin;
    };
    setInterval((function() {
        document.title = "[" + _w_grill + "/" + _w_regime + "/" + _w_splint.length + "-retry:" + _w_miasma.getProcessingNum() + "/" + _w_miasma.getTaskNum() + "]/" + _w_lope();
    }), 100);
    window._w_duel = _w_tussle()._w_pundit(_w_bower);
}));

chrome.tabs.getCurrent((function(tab) {
    window._w_lure = tab.id;
    $((function() {
        _w_riddle(_w_bower, _w_balk);
    }));
}));

chrome.runtime.onMessage.addListener((function(message, sender, callback) {
    message && message.type == "_w_iodine" && _w_duel && _w_duel.length > 0 && message.extractorHash.length && message.extractorHash == _w_duel && callback({
        tabId: _w_lure
    });
    message && message.type == "_w_jigsaw" && _w_duel && _w_duel.length > 0 && message.extractorHash.length && message.extractorHash == _w_duel && _w_cadet(_w_splint);
    message && message.type == "_w_augur" && _w_duel && _w_duel.length > 0 && message.extractorHash.length && message.extractorHash == _w_duel && _w_spurn(message.ItemIdxMap);
}));

function _w_balk(_w_nettle) {
    if (!_w_bower || _w_bower.length == 0) {
        $("#ext_main").html(_w_podium("_w_ore"));
        return;
    } else if (_w_bower == _w_lure) {
        $("#ext_main").html(_w_podium("_w_lode"));
        return;
    } else if (!_w_tussle()._w_list[_w_bower]) {
        $("#ext_main").html(_w_podium("_w_ore"));
        return;
    } else {
        try {
            let _w_spike = new URL(_w_nettle.url);
            if (!_w_saliva(_w_spike.href) && !_w_ire(_w_spike.href)) {
                $("#ext_main").html(_w_podium("_w_coma"));
                return;
            }
        } catch (e) {
            console.log("parse url error." + _w_nettle.url);
            return;
        }
    }
    window._w_duel = _w_tussle()._w_pundit(_w_bower);
    if (!_w_duel || _w_duel.length == 0) {
        $("#ext_main").html(_w_podium("_w_ore"));
        return;
    } else {
        let _w_lash = _w_tussle()._w_lash;
        let _w_ladle = setInterval((function updateTabPosition() {
            if (!_w_lash[_w_bower]) {
                clearInterval(_w_ladle);
            } else if (_w_lash[_w_bower].extractorHash == _w_duel) {
                chrome.tabs.get(_w_bower, (function(tab) {
                    chrome.tabs.getCurrent((function(currentTab) {
                        if (currentTab.windowId != tab.windowId || currentTab.index != tab.index + 1) {
                            let _w_infant = tab.windowId;
                            let _w_havoc = tab.index + 1;
                            if (_w_infant == currentTab.windowId && currentTab.index < tab.index) _w_havoc = tab.index;
                            try {
                                chrome.tabs.move(currentTab.id, {
                                    windowId: _w_infant,
                                    index: _w_havoc
                                }, (function() {}));
                            } catch (exception) {}
                        }
                    }));
                }));
            }
        }), 512);
    }
    let _w_spike = new URL(_w_tussle()._w_list[_w_bower].url);
    if (!_w_ire(_w_spike.href)) {
        _w_tussle()._w_domain(_w_bower, _w_ferry);
    }
    let _w_gaiety = setTimeout((function() {
        _w_splint = _w_tussle()._w_bedlam(_w_duel);
        _w_cadet(_w_splint);
    }), 512);
    _w_tussle()._w_iodine(_w_duel, (function callback(data) {
        if (data && data.tabId && data.tabId != _w_lure) {
            clearTimeout(_w_gaiety);
            chrome.tabs.update(data.tabId, {
                active: true
            }, (function(tab) {
                chrome.windows.update(tab.windowId, {
                    focused: true,
                    drawAttention: true
                }, (function(window) {
                    _w_icon(_w_lure);
                }));
            }));
        }
    }));
}

function _w_screed(_w_prey, _w_bard) {
    if (_w_bard && $("a[data-idx=" + _w_prey + "]").length == 0) return;
    _w_sham[_w_prey] = true;
    let _w_mirth = null;
    let _w_pastel = function() {
        _w_grill--;
        _w_mirth && _w_mirth.loaded();
        delete _w_sham[_w_prey];
        this.onload = null;
        this.onabort = null;
        this.onerror = null;
        let _w_dimple = this.src;
        let _w_fodder = _w_splint[_w_dimple];
        let _w_chunk = _w_fodder.referer;
        if (this.width < _w_tusk.width || this.height < _w_tusk.height) return;
        let _w_issue = new URL(_w_dimple).pathname;
        let _w_saddle = _w_issue.substring(_w_issue.lastIndexOf("/") + 1);
        let _w_logjam = _w_saddle.lastIndexOf(".") > -1 ? _w_saddle.substring(_w_saddle.lastIndexOf(".") + 1) : "";
        _w_logjam = _w_logjam.toLowerCase();
        _w_logjam = _w_tremor[_w_logjam] ? _w_tremor[_w_logjam] : "other";
        this.title = _w_podium("_w_yoke") + this.width + "x" + this.height + " / " + _w_podium("_w_ruse") + _w_logjam.toUpperCase();
        _w_fodder.title && _w_fodder.title.length > 0 && (this.title += " / Title: " + _w_fungi(_w_fodder.title));
        _w_fodder.alt && _w_fodder.alt.length > 0 && _w_fodder.title != _w_fodder.alt && (this.title += " / Alt: " + _w_fungi(_w_fodder.alt));
        this.title = this.title;
        this.setAttribute("data-idx", _w_prey);
        let _w_cleft = document.createElement("a");
        _w_cleft.setAttribute("class", "imageItem");
        _w_cleft.setAttribute("data-width", this.width);
        _w_cleft.setAttribute("data-height", this.height);
        _w_cleft.setAttribute("data-size", this.width * this.height);
        _w_cleft.setAttribute("data-resolution", this.width + "x" + this.height);
        _w_cleft.setAttribute("data-id", (new Date).getTime() + _w_parity(4));
        _w_cleft.setAttribute("data-serial", _w_fodder.serial);
        _w_cleft.setAttribute("data-title", _w_fodder.title);
        _w_cleft.setAttribute("data-alt", _w_fodder.alt);
        _w_cleft.setAttribute("data-idx", _w_prey);
        _w_cleft.href = _w_dimple;
        _w_cleft.title = this.title;
        _w_cleft.setAttribute("data-imageType", _w_logjam.toLowerCase());
        _w_cleft.setAttribute("data-referer", _w_chunk);
        this.setAttribute("data-referer", _w_chunk);
        let _w_lobby = _w_chunk ? _w_chunk.match(/([\d]+|[^\d]+)/g) : [];
        for (let arrIdx in _w_lobby) if (!isNaN(_w_lobby[arrIdx])) _w_lobby[arrIdx] = _w_pilot(_w_lobby[arrIdx], 128);
        _w_stamp[_w_prey] = _w_lobby;
        _w_cleft.setAttribute("data-maxRange", "other");
        for (let i in _w_gamut) {
            if (isNaN(i)) continue;
            let _w_flask = _w_gamut[i];
            let _w_coward = _w_gamut[_w_flask];
            this.width - _w_coward.width >= 0 && this.height - _w_coward.height >= 0 && _w_cleft.setAttribute("data-maxRange", _w_flask);
        }
        this.setAttribute("data-src", _w_dimple);
        _w_cleft.setAttribute("data-src", _w_dimple);
        _w_cleft.appendChild(this);
        $(_w_cleft).append($("<div />", {
            class: "imageItemResolution",
            text: this.width + "x" + this.height
        }));
        let _w_deluge = false;
        let _w_malice = this.width * this.height;
        let _w_split = window._w_blade["serial_" + _w_fodder.serial];
        if (_w_split && _w_split > _w_malice) {
            return;
        } else if ($("a[data-serial=" + _w_fodder.serial + "][data-idx!=" + _w_prey + "]").length > 0) {
            let $_w_gene = $("a[data-serial=" + _w_fodder.serial + "][data-idx!=" + _w_prey + "]");
            if ($_w_gene.is(".selected")) _w_deluge = true;
            $_w_gene.remove();
        }
        window._w_blade["serial_" + _w_fodder.serial] = _w_malice;
        {
            let $_w_closet = $("a[data-idx=" + _w_prey + "]");
            if ($_w_closet.length > 0) {
                if ($_w_closet.is(".selected")) _w_deluge = true;
                if (_w_deluge) _w_cleft.classList.add("selected");
                $_w_closet.replaceWith(_w_cleft);
            } else {
                $("#empty").remove();
                $("#ext_main").append(_w_cleft);
            }
        }
        _w_cleft.style.width = _w_cleft.getBoundingClientRect().width + "px";
        _w_cleft.style.height = _w_cleft.getBoundingClientRect().height + "px";
        this.style.maxWidth = this.getBoundingClientRect().width + "px";
        this.style.maxHeight = this.getBoundingClientRect().height + "px";
        _w_cleft.style.lineHeight = this.getBoundingClientRect().height - 4 + "px";
        $(_w_cleft).colorbox({
            rel: "imageItem",
            photo: true,
            maxWidth: "99%",
            slideshow: true,
            slideshowAuto: false,
            slideshowSpeed: 5e3,
            maxHeight: function() {
                return localStorage["verticalViewMode"] != "fullView" ? "99%" : "";
            },
            href: function() {
                let _w_chef = $(this).attr("href");
                let _w_mill = $(this).children(":first-child").attr("data-src");
                return _w_saliva(_w_mill) ? _w_mill : _w_chef;
            },
            onOpen: function(event) {
                let $_w_enmity = $.colorbox.element();
                if ($_w_enmity.hasClass("preview_ignore_configure")) {
                    $("#colorbox").show();
                } else if ("true" == _w_tussle()._w_sod()) {
                    $_w_enmity.hasClass("selected") ? $_w_enmity.removeClass("selected") : $_w_enmity.addClass("selected");
                    _w_caste("#ext_main>.imageItem", true, false);
                    $.colorbox.close();
                    $("#colorbox").hide();
                } else {
                    $("#colorbox").show();
                }
                $_w_enmity.removeClass("preview_ignore_configure");
                let $_w_scorn = $("<style />", {
                    id: "hideScrollBarStyle",
                    type: "text/css",
                    text: "body::-webkit-scrollbar {display: none;}"
                });
                $("head").append($_w_scorn);
            },
            onCleanup: function() {
                $("#hideScrollBarStyle").remove();
            },
            onComplete: function() {
                $("#cboxTitle").each((function() {
                    $("#cboxTitle").attr("style", "width:" + ($(this).parent().width() - 90) + "px; white-space:nowrap; overflow:hidden;");
                    $("#cboxContent").attr("title", _w_podium("_w_acumen"));
                }));
                _w_stake();
            }
        });
        if (!_w_drivel(this) && $("#ext_main>.imageItem:visible").length > _w_tussle()._w_bore()) {
            this.src = _w_faucet;
        }
        _w_byline("#ext_main>.imageItem");
        $("#image_amount").html($(".imageItem").length);
    };
    let _w_entity = _w_splint[_w_prey];
    let _w_chunk = _w_splint[_w_entity].referer;
    {
        let _w_acorn = {};
        _w_acorn[_w_entity] = _w_chunk;
        _w_tussle()._w_sway(_w_acorn, _w_lure, true);
    }
    let _w_herald = 4;
    let _w_crypt = new Image;
    _w_crypt.onload = _w_pastel;
    _w_crypt.onerror = function() {
        _w_grill--;
        if (!_w_mirth.isTimeout() && _w_herald-- > 0) {
            let _w_mill = _w_crypt.src;
            let _w_saga = function() {
                setTimeout((function() {
                    _w_grill++;
                    _w_crypt.src = _w_mill;
                }), 2e3);
            };
            _w_miasma.addTask((function(_w_twinge, _w_beet) {
                _w_twinge();
                function clearReferer(_w_raffle) {
                    let _w_acorn = {};
                    _w_acorn[_w_raffle] = null;
                    _w_tussle()._w_sway(_w_acorn, _w_lure, true);
                }
                $.ajax({
                    method: "get",
                    timeout: _w_digit,
                    headers: {
                        Accept: "*/*; charset=UTF-8",
                        "Cache-Control": "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0",
                        Pragma: "no-cache",
                        Expires: "0"
                    },
                    url: _w_mill
                }).done((function(data, status, xhr) {
                    _w_mirth && _w_mirth.loaded();
                    _w_herald = -1;
                    if (xhr.status == 200 && data.match && data.match(/(html|script|style|head|body)/i) != null) {
                        clearReferer(_w_mill);
                    }
                    _w_saga();
                })).fail((function(xhr) {
                    _w_mirth && _w_mirth.loaded();
                    _w_herald = -1;
                    if (xhr.status == 404) {
                        delete _w_sham[_w_prey];
                    } else if (xhr.status == 403) {
                        clearReferer(_w_mill);
                        _w_saga();
                    } else {
                        _w_saga();
                    }
                })).always(_w_beet);
            }));
        }
    };
    _w_crypt.onabort = _w_crypt.onerror;
    if (_w_ordeal.bypassUrl(_w_entity)) {
        _w_grill++;
        _w_mirth = _w_ordeal.directSetImgSrc(_w_crypt, _w_entity);
    } else {
        _w_grill++;
        _w_mirth = _w_ordeal.setImgSrc(_w_crypt, _w_entity);
    }
}

function _w_cadet(_w_splint) {
    for (;_w_regime < _w_splint.length; ) {
        if (_w_grill >= _w_jazz) {
            break;
        } else if ($("a.imageItem").length >= _w_gaffe - _w_grill) {
            break;
        } else {
            _w_screed(_w_regime++, false);
        }
    }
}

setInterval((function() {
    _w_cadet(_w_splint);
}), 500);

function _w_spurn(_w_crutch) {
    for (let idx in _w_crutch) {
        if (!_w_splint) continue;
        _w_screed(idx, true);
    }
}

$((function() {
    document.title = _w_podium("_w_regent");
    $.extend($.colorbox.settings, {
        current: _w_podium("_w_deputy"),
        previous: _w_podium("_w_pastel"),
        next: _w_podium("_w_gravel"),
        close: _w_podium("_w_cavern"),
        xhrError: _w_podium("_w_dagger"),
        imgError: _w_podium("_w_clasp"),
        slideshowStart: _w_podium("_w_ditch"),
        slideshowStop: _w_podium("_w_pastry")
    });
}));

$((function() {
    $(window).bind("scroll resize", (function() {
        _w_caste("#ext_main>.imageItem", false, false);
    }));
}));

$((function() {}));

window.selectParam = {
    timeout: 128,
    lastExeTime: new Date,
    timer: null,
    updateStatics: true,
    delayAgain: false
};

$((function() {
    $("body").on("mousedown", (function dnFun(dnEvent) {
        if (dnEvent.button == 0) {
            let $_w_imp = $(dnEvent.target);
            if (!$_w_imp.is("#ext_main") && $_w_imp.parents("#ext_main").length <= 0) {
                return;
            } else if ($(".modal-dialog").is(":visible") || $("#colorbox").is(":visible")) {
                return;
            }
            dnEvent.preventDefault();
            $(".selectorDiv").remove();
            $(this).off("mousemove");
            $(this).off("mouseup");
            let $_w_anthem = $("<div />", {
                class: "selectorDiv"
            });
            $(this).append($_w_anthem);
            let _w_onset, mvFun, moveStep = 0;
            $(this).on("mousemove", mvFun = function(mvEvent) {
                let _w_comic = {};
                _w_comic.x1 = dnEvent.pageX <= mvEvent.pageX ? dnEvent.pageX : mvEvent.pageX;
                _w_comic.y1 = dnEvent.pageY <= mvEvent.pageY ? dnEvent.pageY : mvEvent.pageY;
                _w_comic.x2 = dnEvent.pageX >= mvEvent.pageX ? dnEvent.pageX : mvEvent.pageX;
                _w_comic.y2 = dnEvent.pageY >= mvEvent.pageY ? dnEvent.pageY : mvEvent.pageY;
                if (++moveStep == 1 || _w_comic.x2 - _w_comic.x1 < 4 && _w_comic.y2 - _w_comic.y1 < 4) return true;
                _w_gouge(selectParam, (function() {
                    $_w_anthem.css("z-index", "1020").css("border", "2px solid #3399ff").css("background-color", "rgba(51, 153, 255, 0.2)").css("position", "absolute").css("left", _w_comic.x1 + "px").css("top", _w_comic.y1 + "px").css("width", _w_comic.x2 - _w_comic.x1 + "px").css("height", _w_comic.y2 - _w_comic.y1 + "px");
                    $(".imageItem").each((function() {
                        let _w_belch = $(this).get(0);
                        let _w_mesa = {
                            x1: _w_belch.offsetLeft,
                            y1: _w_belch.offsetTop,
                            x2: _w_belch.offsetLeft + _w_belch.offsetWidth,
                            y2: _w_belch.offsetTop + _w_belch.offsetHeight
                        };
                        if (_w_mesa.y2 < _w_comic.y1 || _w_mesa.x2 < _w_comic.x1 || _w_mesa.y1 > _w_comic.y2 || _w_mesa.x1 > _w_comic.x2) {
                            $(this).removeClass("preSelect preUnSelect");
                        } else {
                            $(this).hasClass("selected") ? $(this).addClass("preUnSelect") : $(this).addClass("preSelect");
                        }
                    }));
                }), false);
            }).on("mouseup", _w_onset = function(upEvent) {
                if (upEvent.button == 0) {
                    $(this).off("mousemove", mvFun);
                    $(this).off("mouseup", _w_onset);
                    $_w_anthem.remove();
                    $(".imageItem").removeClass("preSelect preUnSelect");
                    let _w_comic = {};
                    _w_comic.x1 = dnEvent.pageX <= upEvent.pageX ? dnEvent.pageX : upEvent.pageX;
                    _w_comic.y1 = dnEvent.pageY <= upEvent.pageY ? dnEvent.pageY : upEvent.pageY;
                    _w_comic.x2 = dnEvent.pageX >= upEvent.pageX ? dnEvent.pageX : upEvent.pageX;
                    _w_comic.y2 = dnEvent.pageY >= upEvent.pageY ? dnEvent.pageY : upEvent.pageY;
                    if (moveStep == 1 || _w_comic.x2 - _w_comic.x1 < 4 && _w_comic.y2 - _w_comic.y1 < 4) return true;
                    $(upEvent.target).one("click", (function(event) {
                        event.preventDefault();
                        event.stopPropagation();
                    }));
                    _w_gouge(selectParam, (function() {
                        $(".imageItem").each((function() {
                            let _w_belch = $(this).get(0);
                            let _w_mesa = {
                                x1: _w_belch.offsetLeft,
                                y1: _w_belch.offsetTop,
                                x2: _w_belch.offsetLeft + _w_belch.offsetWidth,
                                y2: _w_belch.offsetTop + _w_belch.offsetHeight
                            };
                            if (_w_mesa.y2 < _w_comic.y1 || _w_mesa.x2 < _w_comic.x1 || _w_mesa.y1 > _w_comic.y2 || _w_mesa.x1 > _w_comic.x2) {} else {
                                $(this).hasClass("selected") ? $(this).removeClass("selected") : $(this).addClass("selected");
                            }
                        }));
                        _w_caste("#ext_main>.imageItem", true, false);
                    }), false);
                }
            });
        }
    }));
}));

function _w_fascia() {
    $(".imageItem:visible").addClass("selected");
    _w_stake();
    _w_caste("#ext_main>.imageItem", true, true);
}

function _w_crumb() {
    $(".imageItem:visible").removeClass("selected");
    _w_stake();
    _w_caste("#ext_main>.imageItem", true, true);
}

function _w_shroud() {
    let $_w_urchin = $(".imageItem:visible.selected");
    let $_w_fervor = $(".imageItem:visible:not(.selected)");
    $_w_urchin.removeClass("selected");
    $_w_fervor.addClass("selected");
    _w_stake();
    _w_caste("#ext_main>.imageItem", true, true);
}

function _w_query() {
    if ($("#cboxLoadedContent").length > 0) {
        let _w_pique = $("#cboxLoadedContent img").attr("src");
        $.colorbox.element().remove();
        $.colorbox.next();
        $(".imageItem:visible").length == 0 && $.colorbox.close();
    } else {
        $(".selected:visible").remove();
        _w_stake();
    }
    _w_caste("#ext_main>.imageItem", true, true);
}

function _w_scowl() {
    $(".imageItem:hidden").remove();
    _w_stake();
    _w_caste("#ext_main>.imageItem", true, true);
}

function _w_aorta() {
    if ($(".selected:visible").length > 0) {
        $(".imageItem:not(.selected):visible").remove();
        _w_stake();
        _w_caste("#ext_main>.imageItem", true, true);
    }
}

function _w_fury() {
    $(".imageItem.selected:visible").remove();
    $(".imageItem:visible").addClass("selected");
    let _w_frisk = new Array;
    $(".imageItem.selected:visible").each((function() {
        _w_frisk.push({
            name: "",
            url: $(this).attr("href"),
            referer: $(this).attr("data-referer")
        });
    }));
    return _w_frisk;
}

function _w_hassle() {
    let _w_frisk = new Array;
    if ($("#cboxLoadedContent").length > 0) {
        _w_heyday($("#cboxLoadedContent img").attr("src"), true);
    } else {
        $(".selected:visible").each((function() {
            _w_frisk.push({
                name: "",
                url: $(this).attr("href"),
                referer: $(this).attr("data-referer"),
                serial: $(this).attr("data-serial")
            });
        }));
    }
    _w_foul(_w_frisk, _w_raisin, _w_fury);
}

function _w_swamp() {
    let _w_frisk = new Array;
    $(".imageItem:visible").each((function() {
        _w_frisk.push({
            name: "",
            url: $(this).attr("href"),
            referer: $(this).attr("data-referer"),
            serial: $(this).attr("data-serial")
        });
    }));
    _w_foul(_w_frisk, _w_raisin, _w_fury);
}

function _w_grace() {
    let _w_slag = function() {
        $("#pullywood_production").popover({
            title: "<span class='glyphicon glyphicon-info-sign'></span> " + _w_podium("_w_tangle"),
            content: _w_podium("_w_girder"),
            placement: "auto",
            html: true
        }).popover("show").next().on("click", (function() {
            $(this).popover("destroy");
        })).prev().on("mouseover", (function() {
            $(this).popover("destroy");
        }));
    };
    $.ajax({
        method: "get",
        url: _w_craft,
        dataType: "json",
        contentType: "application/json",
        mimeType: "application/json"
    }).done((function(data) {
        if (data && data.version) {
            _w_mosque();
        } else if (data && !data.version) {
            alert(_w_podium("_w_sash"));
        } else {
            _w_slag();
        }
    })).fail((function() {
        _w_slag();
    }));
}

function _w_mosque() {
    let _w_frisk = $(".imageItem.selected:visible").toArray();
    if (_w_frisk.length <= 0) {
        alert(_w_podium("_w_apex"));
        return;
    }
    let $_w_fume = $("<div />", {
        id: "add_favorite_dlg",
        class: "modal fade",
        role: "dialog"
    });
    let $_w_ration = $("<div />", {
        class: "modal-dialog"
    });
    let $_w_span = $("<div />", {
        class: "modal-content"
    });
    let $_w_fast = $("<div />", {
        class: "modal-header"
    });
    let $_w_mask = $("<h4 />", {
        class: "modal-title"
    });
    let $_w_howler = $("<div />", {
        class: "modal-body"
    });
    let $_w_herd = $("<div />", {
        class: "modal-footer"
    });
    $_w_fast.append($_w_mask);
    $_w_span.append($_w_fast);
    $_w_span.append($_w_howler);
    $_w_span.append($_w_herd);
    $_w_ration.append($_w_span);
    $_w_fume.append($_w_ration);
    $_w_mask.append($("<span />", {
        class: "glyphicon glyphicon-folder-open"
    })).append(_w_podium("_w_stigma"));
    let $_w_silt = $("<div />", {
        class: "alert alert-info",
        html: _w_podium("_w_toil") + _w_frisk.length
    });
    $_w_howler.append($_w_silt);
    let $_w_sibyl = $("<input />", {
        type: "checkbox",
        name: "continuousSwitch"
    });
    $_w_herd.append($("<span />", {
        class: "continuousSwitchContainer"
    }).append($_w_sibyl));
    $_w_sibyl.bootstrapSwitch({
        labelText: _w_podium("_w_gong")
    });
    let $_w_dome = $("<button />", {
        class: "btn btn-primary",
        disabled: false,
        text: _w_podium("_w_parity")
    });
    $_w_dome.prepend($("<span />", {
        class: "glyphicon glyphicon-floppy-open"
    }));
    $_w_dome.on("click", (function() {
        $_w_dome.attr("disabled", true);
        let _w_leash = $_w_sibyl.is(":checked");
        let _w_grain = _w_frisk;
        _w_frisk = new Array;
        let _w_pulse = [];
        _w_grain.forEach((function(taskItem) {
            _w_pulse.push({
                src: $(taskItem).attr("data-src"),
                referer: $(taskItem).attr("data-referer"),
                description: typeof $(taskItem).attr("title") != "undefined" ? $(taskItem).attr("title").replace(/分辨率:\s\d+x\d+\s\/\s类型:\s[a-zA-Z0-9]+(\s\/\s)?/gi, "") : "",
                width: $(taskItem).attr("data-width"),
                height: $(taskItem).attr("data-height"),
                extHash: _w_duel,
                serial: $(taskItem).attr("data-serial")
            });
        }));
        $_w_silt.text(_w_podium("_w_fetter"));
        if (!$_w_fume.is(":visible")) {
            return;
        }
        $.ajax({
            method: "post",
            url: _w_marble,
            data: JSON.stringify(_w_pulse),
            dataType: "json",
            contentType: "application/json",
            mimeType: "application/json"
        }).done((function(result) {
            $_w_silt.text(_w_podium("_w_kin"));
        })).fail((function(a, b, c) {
            console.log("fialed", a, b, c);
            _w_frisk = _w_grain;
            let $_w_razor = $_w_dome.children().first();
            $_w_dome.text(_w_podium("_w_cone")).prepend($_w_razor).attr("disabled", _w_leash ? true : false);
            $_w_silt.text(_w_podium("_w_flange"));
        })).always((function() {
            if (_w_leash) {
                let _w_lug = 500;
                if (_w_frisk.length > 0) {
                    console.log(JSON.stringify(_w_frisk));
                    $_w_silt.text(_w_podium("_w_mettle"));
                } else {
                    $(".imageItem.selected:visible").remove();
                    $_w_silt.text(_w_podium("_w_poise"));
                }
                let _w_dynamo = setInterval((function continuousAdd() {
                    if (_w_frisk.length == 0) {
                        _w_fascia();
                        _w_frisk = $(".imageItem.selected:visible").toArray();
                    }
                    if (_w_frisk && _w_frisk.length > 0) {
                        clearInterval(_w_dynamo);
                        $_w_dome.click();
                    }
                }), _w_lug);
            } else {
                $(this).remove();
            }
        }));
    }));
    $_w_herd.append($_w_dome);
    let $_w_muddle = $("<button />", {
        class: "btn btn-default",
        "data-dismiss": "modal",
        text: _w_podium("_w_fell")
    });
    $_w_muddle.prepend($("<span />", {
        class: "glyphicon glyphicon-remove"
    }));
    $_w_muddle.on("click", (function() {}));
    $_w_herd.append($_w_muddle);
    $_w_fume.modal({
        backdrop: "static",
        keyboard: false
    }).on("hidden.bs.modal", (function() {
        $(this).remove();
    }));
}

function _w_die() {
    if ($("#cboxLoadedContent").length > 0) {
        $("#colorbox").addClass("colorboxSelect");
        $(".imageItem:not(.selected):visible[href='" + $("#cboxLoadedContent img").attr("src") + "']").addClass("selected");
    }
}

function _w_warp() {
    if ($("#cboxLoadedContent").length > 0) {
        $("#colorbox").removeClass("colorboxSelect");
        $(".imageItem.selected:visible[href='" + $("#cboxLoadedContent img").attr("src") + "']").removeClass("selected");
    }
}

function _w_stake() {
    if ($("#cboxLoadedContent").length > 0) {
        $("#colorbox").removeClass("colorboxSelect");
        $.colorbox.element().is(".selected:visible") && $("#colorbox").addClass("colorboxSelect");
    }
}

function _w_nadir() {
    let _w_tipple = {
        0: 1,
        1: 3,
        3: 2,
        2: 0
    };
    let _w_spoor = _w_tussle()._w_arch();
    let _w_trowel = _w_tipple[_w_spoor];
    "undefined" == typeof _w_trowel && (_w_trowel = 3);
    _w_tussle()._w_cast(_w_trowel);
    _w_caste("#ext_main>.imageItem", true, false);
}

function _w_loom() {
    let _w_trowel = _w_tussle()._w_arch();
    if (_w_trowel & 4) {
        _w_trowel &= ~4;
    } else {
        _w_trowel |= 4;
    }
    _w_tussle()._w_cast(_w_trowel);
    _w_byline("#ext_main>.imageItem");
    _w_caste("#ext_main>.imageItem", true, false);
}

function _w_hack() {
    let _w_trowel = _w_tussle()._w_arch();
    if (_w_trowel & 8) {
        _w_trowel &= ~8;
    } else {
        _w_trowel |= 8;
    }
    _w_tussle()._w_cast(_w_trowel);
    _w_caste("#ext_main>.imageItem", true, false);
}

function _w_leak() {
    if ($("#colorbox").is(":visible")) {
        if (localStorage["verticalViewMode"] != "fullView") {
            localStorage["verticalViewMode"] = "fullView";
        } else {
            localStorage["verticalViewMode"] = "";
        }
        $.colorbox.element().colorbox({
            open: true
        });
    }
}

function _w_mien() {
    chrome.tabs.get(_w_bower, (function(tab) {
        if (tab && _w_dart == tab.url) {
            chrome.tabs.remove(tab.id, (function() {
                window.close();
            }));
        } else {
            window.close();
        }
    }));
}

$((function() {
    let $_w_curfew = $("<div>", {
        class: "btn-group btn-group-xs"
    });
    let $_w_poncho = $("<a />", {
        target: "_configure_",
        class: "btn btn-pwd",
        href: "options.html",
        text: _w_podium("_w_nomad")
    });
    $_w_poncho.prepend($("<span />", {
        class: "glyphicon glyphicon-wrench"
    }));
    let $_w_studio = $("<a />", {
        target: "_configure_",
        class: "btn btn-home",
        href: "options.html?showMsg=about",
        text: _w_podium("_w_mite")
    });
    $_w_studio.prepend($("<span />", {
        class: "glyphicon glyphicon-copyright-mark"
    }));
    let $_w_influx = $("<a />", {
        target: "_imageAssistant_favorite",
        id: "_cxyz_fav_",
        class: "btn btn-primary",
        href: "./favorite.html",
        text: _w_podium("_w_attic")
    });
    $_w_influx.prepend($("<span />", {
        class: "glyphicon glyphicon-folder-open"
    }));
    let $_w_queue = $("<a />", {
        target: "_pullywood_production_",
        class: "btn btn-home",
        href: "http://www.pullywood.com",
        text: _w_podium("_w_paean")
    });
    $_w_queue.prepend($("<span />", {
        class: "glyphicon glyphicon-home"
    }));
    $_w_curfew.append($_w_poncho).append($_w_studio).append($_w_influx).append($_w_queue);
    $("#pullywood_production").append($_w_curfew);
    let _w_pulse = [ {
        name: _w_podium("_w_minion"),
        showText: true,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-refresh",
        fun: function() {
            $("#ext_main .imageItem").remove();
            _w_regime = 0;
            _w_cadet(_w_splint);
        }
    }, {
        name: _w_podium("_w_nova"),
        showText: true,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-align-justify",
        fun: _w_fascia
    }, {
        name: "",
        showText: false,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-collapse-down",
        fun: function() {},
        subMenu: [ {
            name: _w_podium("_w_pylon"),
            showText: true,
            className: "mainMenuItem",
            iconClass: "glyphicon glyphicon-list",
            fun: _w_crumb
        }, {
            name: _w_podium("_w_browse"),
            showText: true,
            className: "mainMenuItem",
            iconClass: "glyphicon glyphicon-retweet",
            fun: _w_shroud
        } ]
    }, {
        name: _w_podium("_w_schism"),
        showText: true,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-trash",
        fun: _w_query
    }, {
        name: "",
        showText: false,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-collapse-down",
        fun: function() {},
        subMenu: [ {
            name: _w_podium("_w_nicest"),
            className: "mainMenuItem",
            iconClass: "glyphicon glyphicon-remove",
            fun: _w_scowl
        }, {
            name: _w_podium("_w_impact"),
            showText: true,
            className: "mainMenuItem",
            iconClass: "glyphicon glyphicon-log-in",
            fun: _w_aorta
        } ]
    }, {
        name: _w_podium("_w_savant"),
        showText: true,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-cloud-download",
        fun: _w_hassle
    }, {
        name: "",
        showText: false,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-collapse-down",
        fun: function() {},
        subMenu: [ {
            name: _w_podium("_w_fad"),
            showText: true,
            className: "mainMenuItem",
            iconClass: "glyphicon glyphicon-download",
            fun: _w_swamp
        } ]
    }, {
        name: _w_podium("_w_gusher"),
        showText: true,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-folder-open",
        fun: _w_foil
    }, {
        name: _w_podium("_w_dart"),
        showText: true,
        className: "mainMenuItem",
        iconClass: "glyphicon glyphicon-folder-open",
        fun: _w_grace
    } ];
    let $_w_waffle = $("#select_menu");
    $_w_waffle.addClass("container btn-group btn-group-sm");
    $_w_waffle.attr("role", "group");
    for (let i in _w_pulse) {
        if (isNaN(i)) continue;
        let _w_coward = _w_pulse[i];
        if (_w_coward.subMenu) {
            let $_w_needle = $("<div />", {
                class: "btn-group btn-group-sm",
                role: "group"
            });
            let $_w_hawker = $("<button />", {
                type: "button",
                class: "btn btn-default dropdown-toggle",
                "data-toggle": "dropdown",
                "aria-expanded": "false"
            });
            $_w_hawker.append($("<span />", {
                class: "caret"
            }));
            $_w_needle.append($_w_hawker);
            $_w_waffle.append($_w_needle);
            let $_w_gist = $("<ul />", {
                class: "dropdown-menu dropdown-menu-right",
                role: "menu"
            });
            for (let j in _w_coward.subMenu) {
                if (isNaN(j)) continue;
                let _w_canard = _w_coward.subMenu[j];
                let $_w_sermon = $("<li />");
                let $_w_cleft = $("<a />", {
                    href: "#"
                });
                $_w_cleft.append($("<span />", {
                    class: _w_canard.iconClass,
                    "aria-hidden": true
                }));
                $_w_cleft.append(" " + _w_canard.name);
                $_w_sermon.append($_w_cleft);
                $_w_gist.append($_w_sermon);
                $_w_cleft.on("click", _w_canard.fun);
            }
            $_w_needle.append($_w_gist);
            $_w_waffle.append($_w_needle);
        } else {
            let $_w_hawker = $("<button />", {
                class: "btn btn-default"
            });
            $_w_hawker.append($("<span />", {
                class: _w_coward.iconClass,
                "aria-hidden": true
            }));
            _w_coward.showText && $_w_hawker.append(" " + _w_coward.name);
            $_w_waffle.append($_w_hawker);
            $_w_hawker.on("click", _w_coward.fun);
        }
    }
    $(document).on("keydown", (function(e) {
        if (_w_anemia(e)) return;
        if ($("#download_confirm_dlg").length > 0) return true;
        e.which == 88 && e.shiftKey && e.altKey && _w_mien();
        e.which == 86 && e.ctrlKey == false && e.altKey == false && _w_leak();
        e.which == 68 && e.ctrlKey && _w_hassle() & e.preventDefault();
        e.which == 109 && !e.ctrlKey && _w_warp() & e.preventDefault();
        e.which == 107 && !e.ctrlKey && _w_die() & e.preventDefault();
        (e.which == 46 || e.which == 110) && _w_query() & e.preventDefault();
        if ($("#cboxOverlay, .modal-dialog").is(":visible")) return true;
        e.which == 65 && e.ctrlKey && _w_fascia() & e.preventDefault();
        e.which == 90 && e.ctrlKey && _w_crumb() & e.preventDefault();
        e.which == 82 && e.ctrlKey && _w_shroud() & e.preventDefault();
        e.which == 83 && e.ctrlKey && _w_aorta() & e.preventDefault();
        e.which == 70 && e.ctrlKey && _w_grace() & e.preventDefault();
        e.which == 77 && e.altKey && _w_nadir() & e.preventDefault();
        e.which == 83 && e.altKey && _w_loom() & e.preventDefault();
        e.which == 84 && e.altKey && _w_hack() & e.preventDefault();
    }));
    let _w_truce = [ {
        name: _w_podium("_w_felon"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "glyphicon glyphicon-picture",
        fun: function() {
            let _w_cello = $(this).attr("data-id");
            $(".imageItem[data-id='" + _w_cello + "']").addClass("preview_ignore_configure").trigger("click");
        }
    }, {
        name: _w_podium("_w_dandy"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "glyphicon glyphicon-download",
        fun: function() {
            let _w_pique = $(this).attr("data-src");
            _w_heyday(_w_pique, true);
        }
    }, {
        name: _w_podium("_w_skein"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "glyphicon glyphicon-qrcode",
        fun: function() {
            $(this).attr("data-src") && _w_affix($(this).attr("data-src"), true);
        }
    }, {
        name: _w_podium("_w_dent"),
        showText: true,
        className: "imageContextMenuURLE",
        iconClass: "glyphicon glyphicon-edit",
        fun: function() {
            $(this).attr("data-src") && _w_tussle()._w_cravat($(this).attr("data-src"), $(this).attr("data-referer"), -1);
        }
    }, {
        name: _w_podium("_w_flak"),
        showText: true,
        className: "imageContextMenuURLE",
        iconClass: "glyphicon glyphicon-picture",
        fun: function() {
            $(this).attr("data-src") && _w_tussle()._w_arson($(this).attr("data-src"), $(this).attr("data-referer"), -1);
        }
    }, {
        name: _w_podium("_w_snivel"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "fab fa-google",
        fun: function() {
            $(this).attr("data-src") && _w_gab($(this).attr("data-src"));
        }
    }, {
        name: _w_podium("_w_quiver"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "fab fa-google",
        fun: function() {
            $(this).attr("data-src") && _w_crease($(this).attr("data-src"));
        }
    }, {
        name: _w_podium("_w_fume"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "fab fa-google",
        fun: function() {
            $(this).attr("data-src") && _w_hurdle($(this).attr("data-src"));
        }
    }, {
        name: _w_podium("_w_howler"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "fa fa-paw",
        fun: function() {
            $(this).attr("data-src") && _w_oak($(this).attr("data-src"));
        }
    }, {
        name: _w_podium("_w_spell"),
        showText: true,
        className: "imageContextMenuURL",
        iconClass: "fa fa-paw",
        fun: function() {
            $(this).attr("data-src") && _w_ivory($(this).attr("data-src"));
        }
    } ];
    _w_pulse = _w_pulse.slice(0, 0).concat(_w_truce).concat(_w_pulse.slice(0));
    let $_w_laity = $("<ul />", {
        class: "dropdown-menu",
        role: "menu",
        style: "z-index:9999;"
    });
    for (let i in _w_pulse) {
        if (isNaN(i)) continue;
        let _w_coward = _w_pulse[i];
        if (_w_coward.subMenu) {
            let _w_fillet = _w_coward.subMenu;
            for (let j in _w_fillet) {
                if (isNaN(j)) continue;
                let _w_canard = _w_fillet[j];
                let $_w_fraud = $("<li />", {
                    role: "presentation"
                });
                let $_w_shoal = $("<a />", {
                    role: "menuitem",
                    class: _w_canard.className,
                    tabIndex: "-1",
                    href: "#"
                });
                $_w_shoal.append($("<span />", {
                    class: _w_canard.iconClass
                }));
                $_w_shoal.append(" " + _w_canard.name);
                $_w_fraud.append($_w_shoal);
                $_w_laity.append($_w_fraud);
                $_w_shoal.on("click", _w_canard.fun);
            }
            continue;
        }
        let $_w_fraud = $("<li />", {
            role: "presentation"
        });
        let $_w_shoal = $("<a />", {
            role: "menuitem",
            class: _w_coward.className,
            tabIndex: "-1",
            href: "#"
        });
        $_w_shoal.append($("<span />", {
            class: _w_coward.iconClass
        }));
        $_w_shoal.append(" " + _w_coward.name);
        $_w_fraud.append($_w_shoal);
        $_w_shoal.on("click", _w_coward.fun);
        $_w_laity.append($_w_fraud);
    }
    $_w_laity.on("mousedown mousemove mouseup click", (function(e) {}));
    $("body").append($_w_laity);
    $_w_laity.dropdown();
    $_w_laity.hide();
    $(document).on("keydown scroll", (function(e) {
        (e.which == 27 || e.type == "scroll") && $_w_laity.fadeOut("fast");
    }));
    $("html").on("click", (function(e) {
        $_w_laity.fadeOut("fast");
    }));
    $_w_laity.on("click", (function(e) {
        $_w_laity.fadeOut("fast");
    }));
    $(document).on("contextmenu", (function(e) {
        $(".context_menu").hide();
        $_w_laity.hide();
        if ($(".modal-dialog").is(":visible") || $("#colorbox").is(":visible")) {
            return true;
        }
        let $_w_axiom = $(e.target.parentElement);
        if ($_w_axiom && $_w_axiom.hasClass("imageItem")) {
            $(".imageContextMenuURLE").attr("data-src", $_w_axiom.attr("data-src")).attr("data-id", $_w_axiom.attr("data-id")).attr("data-referer", $_w_axiom.attr("data-referer")).show();
            if ($_w_axiom.attr("data-src").indexOf("data:") != 0) {
                $(".imageContextMenuURL").attr("data-src", $_w_axiom.attr("data-src")).attr("data-id", $_w_axiom.attr("data-id")).attr("data-referer", $_w_axiom.attr("data-referer")).show();
            } else {
                $(".imageContextMenuURL").attr("data-src", "").hide();
            }
            $(".mainMenuItem").hide();
        } else {
            $(".imageContextMenuURLE").attr("data-src", "").hide();
            $(".imageContextMenuURL").attr("data-src", "").hide();
            $(".mainMenuItem").show();
        }
        $_w_laity.css("left", e.pageX + "px").css("top", e.pageY + "px");
        $_w_laity.fadeIn("fast");
        $_w_laity.offset().left + $_w_laity.outerWidth() >= window.scrollX + window.innerWidth && $_w_laity.css("left", e.pageX - $_w_laity.outerWidth() + "px");
        $_w_laity.offset().top + $_w_laity.outerHeight() >= window.scrollY + window.innerHeight && $_w_laity.css("top", e.pageY - $_w_laity.outerHeight() + "px");
        return false;
    }));
    let $_w_morale = $("#image_summary");
    let $_w_suture = $("<span />", {
        id: "image_amount",
        role: "presentation",
        class: "badge",
        text: "0"
    });
    let $_w_brawl = $("<label />", {
        class: "col-md-4 col-sm-4"
    });
    $_w_brawl.append(_w_podium("_w_jest"));
    $_w_brawl.append($_w_suture);
    $_w_morale.append($_w_brawl);
    let $_w_spell = $("<span />", {
        id: "visible_amount",
        role: "presentation",
        class: "badge",
        text: "0"
    });
    let $_w_lark = $("<label />", {
        class: "col-md-4 col-sm-4"
    });
    $_w_lark.append(_w_podium("_w_smut"));
    $_w_lark.append($_w_spell);
    $_w_morale.append($_w_lark);
    let $_w_mire = $("<span />", {
        id: "select_amount",
        role: "presentation",
        class: "badge",
        text: "0"
    });
    let $_w_fang = $("<label />", {
        class: "col-md-4 col-sm-4"
    });
    $_w_fang.append(_w_podium("_w_plight"));
    $_w_fang.append($_w_mire);
    $_w_morale.append($_w_fang);
    let $_w_trifle = $("#menu_switch");
    let $_w_rascal = $("<div />", {
        class: "btn-group btn-group-xs"
    });
    $_w_trifle.append($_w_rascal);
    let $_w_carat = $("<div />", {
        class: "btn btn-default",
        id: "filter_switch",
        title: _w_podium("_w_fathom")
    });
    let $_w_putter = $("<span />", {
        class: "glyphicon glyphicon-filter"
    });
    $_w_carat.append($_w_putter);
    $_w_carat.on("click", (function() {
        let _w_trowel = _w_tussle()._w_arch();
        if ($(this).hasClass("active")) {
            _w_trowel &= ~1;
        } else {
            _w_trowel |= 1;
        }
        _w_tussle()._w_cast(_w_trowel);
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    $_w_rascal.append($_w_carat);
    let $_w_pane = $("<div />", {
        class: "btn btn-default",
        id: "select_menu_switch",
        title: _w_podium("_w_blur")
    });
    let $_w_muscle = $("<span />", {
        class: "glyphicon glyphicon-tasks"
    });
    $_w_pane.append($_w_muscle);
    $_w_pane.on("click", (function() {
        let _w_trowel = _w_tussle()._w_arch();
        if ($(this).hasClass("active")) {
            _w_trowel &= ~2;
        } else {
            _w_trowel |= 2;
        }
        _w_tussle()._w_cast(_w_trowel);
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    $_w_rascal.append($_w_pane);
    let $_w_anchor = $("<div />", {
        class: "btn btn-default",
        id: "sort_switch",
        title: _w_podium("_w_gulch")
    });
    let $_w_rubble = $("<span />", {
        class: "glyphicon glyphicon-sort-by-order-alt"
    });
    $_w_anchor.append($_w_rubble);
    $_w_anchor.on("click", _w_loom);
    $_w_rascal.append($_w_anchor);
    let $_w_almond = $("<div />", {
        class: "btn btn-default",
        id: "resolutionTle_switch",
        title: _w_podium("_w_gallop")
    });
    let $_w_nymph = $("<span />", {
        class: "glyphicon glyphicon-text-background"
    });
    $_w_almond.append($_w_nymph);
    $_w_almond.on("click", _w_hack);
    $_w_rascal.append($_w_almond);
    let $_w_armory = $("<div />", {
        class: "btn btn-success",
        title: _w_podium("_w_nick")
    });
    $_w_armory.on("click", (function() {
        chrome.tabs.get(_w_bower, (function(tab) {
            if (tab && _w_dart == tab.url) {
                chrome.tabs.update(tab.id, {
                    active: true
                }, (function() {}));
            }
        }));
    }));
    $_w_armory.append($("<span />", {
        class: "fa fa-exchange-alt"
    }));
    let $_w_falcon = $("<div />", {
        class: "btn btn-danger",
        title: _w_podium("_w_thrall")
    });
    $_w_falcon.on("click", _w_mien);
    $_w_falcon.append($("<span />", {
        class: "glyphicon glyphicon-remove"
    }));
    $_w_trifle.append($("<div />", {
        class: "btn-group btn-group-xs"
    }).append($_w_armory).append($_w_falcon));
    let $_w_lease = $("#filter_menu_type");
    $_w_lease.append($("<label />", {
        text: _w_podium("_w_blight"),
        class: "col-md-2"
    }));
    let _w_paean = _w_tango.slice(0);
    _w_paean.unshift("all");
    _w_paean.push("other");
    let $_w_mint = $("<div />", {
        class: "btn-toolbar",
        role: "toolbar"
    });
    for (let i in _w_paean) {
        if (isNaN(i)) continue;
        let $_w_tureen = $("<div />", {
            class: "btn btn-default btn-xs imageType btn-pwd active",
            value: _w_paean[i],
            text: _w_paean[i].toUpperCase()
        });
        let $_w_sap = $("<span />", {
            id: "counter_" + _w_paean[i],
            role: "presentation",
            class: "badge",
            text: "0"
        });
        $_w_tureen.append($_w_sap);
        if (_w_paean[i] != "all") $_w_tureen.hide();
        $_w_mint.append($_w_tureen);
    }
    $_w_lease.append($_w_mint);
    $("#filter_menu_type .imageType").click((function(e) {
        $(this).hasClass("active") ? $(this).removeClass("btn-pwd active") : $(this).addClass("btn-pwd active");
        if ($(this).attr("value") == "all") {
            $(this).hasClass("active") ? $(this).siblings().addClass("btn-pwd active") : $(this).siblings().removeClass("btn-pwd active");
        }
        $("#filter_menu_type .imageType[value!=all]:not(.active)").length > 0 ? $("#filter_menu_type .imageType[value=all]").removeClass("btn-pwd active") : $("#filter_menu_type .imageType[value=all]").addClass("btn-pwd active");
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    let _w_reed = [ {
        name: _w_podium("_w_combat"),
        value: "larger",
        active: true
    }, {
        name: _w_podium("_w_harbor"),
        value: "exact"
    } ];
    let _w_boding = [];
    for (let item in _w_gamut) {
        _w_boding[item] = _w_gamut[item];
    }
    _w_boding.unshift("all");
    _w_boding["all"] = {
        width: 0,
        height: 0
    };
    _w_boding.push("other");
    _w_boding["other"] = {
        width: 0,
        height: 0
    };
    let $_w_curb = $("#filter_menu_size");
    $_w_curb.append($("<label />", {
        text: _w_podium("_w_gaiety"),
        class: "col-md-2 form-horizontal"
    }));
    let $_w_renown = $("<div />", {
        class: "col-md-offset-2"
    });
    let $_w_sect = $("<div />", {
        class: "btn-toolbar",
        role: "toolbar"
    });
    for (let i in _w_reed) {
        if (isNaN(i)) continue;
        let $_w_mete = $("<div />", {
            class: "btn btn-default btn-xs selectType",
            value: _w_reed[i].value,
            text: _w_reed[i].name
        });
        _w_reed[i].active && $_w_mete.addClass("btn-pwd active");
        $_w_sect.append($_w_mete);
    }
    let _w_reaper = [];
    for (let item in _w_gamut) {
        _w_reaper[item] = _w_gamut[item];
    }
    let _w_crook = _w_tusk.width + "x" + _w_tusk.height + _w_podium("_w_fabric");
    _w_reaper.unshift(_w_crook);
    _w_reaper[_w_crook] = {
        width: _w_tusk.width,
        height: _w_tusk.height
    };
    let _w_stitch = document.createElement("select");
    _w_stitch.setAttribute("id", "funnelOptionSelect");
    for (let i in _w_reaper) {
        if (isNaN(i)) continue;
        let _w_elegy = document.createElement("option");
        _w_elegy.value = _w_reaper[i];
        _w_elegy.text = _w_reaper[i];
        _w_stitch.appendChild(_w_elegy);
        i == 0 && (_w_elegy.selected = true);
    }
    let $_w_mirage = $("<span />", {
        class: "ext_page_input"
    });
    $_w_mirage.append($("<b />", {
        text: _w_podium("_w_import")
    }));
    $_w_mirage.append(_w_stitch);
    $_w_sect.append($_w_mirage);
    $(_w_stitch).on("change", (function() {
        let _w_down = _w_reaper[_w_stitch.selectedOptions[0].value];
        _w_tusk.width = _w_down.width;
        _w_tusk.height = _w_down.height;
        let _w_veil = [];
        $("#ext_main .imageItem").each((function() {
            if ($(this).attr("data-width") - _w_down.width < 0 || $(this).attr("data-height") - _w_down.height < 0) {
                $(this).remove();
            }
        }));
        $(this).blur();
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    $_w_renown.append($_w_sect);
    $_w_curb.append($_w_renown);
    let $_w_attic = $("<div />", {
        class: "col-md-offset-2"
    });
    let $_w_rind = $("<div />", {
        class: "btn-toolbar",
        role: "toolbar"
    });
    for (let i in _w_boding) {
        if (isNaN(i)) continue;
        let $_w_flock = $("<div />", {
            class: "btn btn-default btn-xs selectOption btn-pwd active",
            value: _w_boding[i],
            text: _w_boding[i].toUpperCase()
        });
        if (_w_boding[i] != "all") $_w_flock.hide();
        $_w_rind.append($_w_flock);
    }
    $_w_attic.append($_w_rind);
    $_w_curb.append($_w_attic);
    $("#filter_menu_size .selectType").click((function() {
        $(this).addClass("btn-pwd active").siblings().removeClass("btn-pwd active");
        $("#filter_menu_size .selectOption").addClass("btn-pwd active");
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    $("#filter_menu_size .selectOption").click((function() {
        if ($("#filter_menu_size .selectType[value=larger]").hasClass("active")) {
            if ($(this).attr("value") == "all") {
                $(this).addClass("btn-pwd active").siblings().addClass("btn-pwd active");
            } else {
                $(this).addClass("btn-pwd active").siblings().removeClass("btn-pwd active");
            }
        } else if ($("#filter_menu_size .selectType[value=exact]").hasClass("active")) {
            $(this).hasClass("active") ? $(this).removeClass("btn-pwd active") : $(this).addClass("btn-pwd active");
            if ($(this).attr("value") == "all") {
                $(this).hasClass("active") ? $(this).siblings().addClass("btn-pwd active") : $(this).siblings().removeClass("btn-pwd active");
            } else {
                $("#filter_menu_size .selectOption[value!=all]:not(.active)").length > 0 ? $("#filter_menu_size .selectOption[value=all]").removeClass("btn-pwd active") : $("#filter_menu_size .selectOption[value=all]").addClass("btn-pwd active");
            }
        }
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    let $_w_mentor = $("#filter_menu_regexp");
    $_w_mentor.append($("<label />", {
        text: _w_podium("_w_school"),
        class: "col-md-2"
    }));
    let $_w_husk = $("<div />", {
        class: "col-md-offset-2 btn-toolbar",
        role: "toolbar"
    });
    let $_w_staple = $("<input />", {
        id: "urlRegexpFilter",
        class: "ext_page_input",
        size: 64,
        placeHolder: "Input Regexp Expression to part/full match URL address."
    });
    $_w_husk.append($_w_staple);
    $_w_mentor.append($_w_husk);
    $_w_staple.on("change input", (function() {
        _w_caste("#ext_main>.imageItem", true, false);
    }));
    _w_caste("#ext_main>.imageItem", true, true);
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
}));

function _w_consul() {
    let _w_pastor = new Date;
    let _w_saddle = "".concat(_w_pastor.getFullYear()).concat(_w_pastor.getDate()).concat(_w_pastor.getMonth() + 1).concat(_w_pastor.getHours()).concat(_w_pastor.getMinutes()).concat(_w_pastor.getSeconds()).concat(".js");
    let _w_carp = "let _w_regime = ".concat(_w_regime).concat(";\nlet _w_jazz = 0;\n").concat("let entityData = ").concat(JSON.stringify(Object.entries(_w_splint).filter((function(item) {
        if (isNaN(item[0])) {
            return item;
        }
    })))).concat(";\n");
    _w_file(_w_saddle, _w_carp);
}

function _w_fallow(_w_medal) {
    let _w_harp = [];
    _w_medal.forEach((function(item) {
        if (typeof item[0] != "number") {
            _w_harp.push(item[0]);
            _w_harp[item[0]] = item[1];
        }
    }));
    _w_splint = _w_harp;
}

function _w_bin(_w_ban) {
    if (!_w_sham) return;
    let _w_violet = Object.keys(_w_sham);
    let _w_sluice = null;
    _w_sluice = setInterval((function() {
        if (_w_grill >= _w_jazz || $("a.imageItem").length >= _w_gaffe - _w_grill) {
            return;
        } else {
            if (_w_violet.length <= 0) {
                clearInterval(_w_sluice);
                return;
            }
            let _w_shrub = _w_violet.shift();
            if (_w_nectar(_w_ban) && _w_ban(_w_splint[_w_shrub])) {
                delete _w_sham[_w_shrub];
                return;
            }
            _w_screed(_w_shrub, false);
        }
    }), 50);
}

