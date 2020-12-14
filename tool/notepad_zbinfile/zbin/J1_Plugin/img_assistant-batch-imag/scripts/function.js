/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

window._w_novice = "0123456789";

window._w_pang = "0123456789abcdef";

window._w_ewe = "0123456789abcdefghijklmnopqrstuvwxyz";

window._w_digit = 8e3;

window._w_pyre = "https://www.pullywood.com/ImageAssistant/blank.html";

window._w_abuse = _w_hovel(window._w_pyre);

window._w_hoop = _w_hovel(window._w_pyre);

window._w_adobe = "http://www.pullywood.com/ImageAssistant/images/IAnalytic.png";

window._w_poll = "http://www.pullywood.com/publish/imageassistant-resource-list";

window._w_bulk = _w_hovel("http://www.pullywood.com/ImageAssistant/defaultRegexpUrlRule.properties");

window._w_craft = _w_hovel("http://localhost:61257/check");

window._w_marble = _w_hovel("http://localhost:61257/collection/save");

window._w_relish = _w_hovel("http://www.pullywood.com/ImageAssistant/dynamic_config.json");

window._w_hunk = false;

window._w_tiff = {};

window._w_glut = 512;

window._w_faucet = "./images/loading.gif";

window._w_pack = {
    _keyStr: "bFf2YMpEZLT6OBqN/DCtJlnc9G154W=wP+h3Rrk8xadIzjQoHmv7sAS0yeUiKVugX",
    encode: function(e) {
        let t = "";
        let n, r, i, s, o, u, a;
        let f = 0;
        e = _w_pack._utf8_encode(e);
        while (f < e.length) {
            n = e.charCodeAt(f++);
            r = e.charCodeAt(f++);
            i = e.charCodeAt(f++);
            s = n >> 2;
            o = (n & 3) << 4 | r >> 4;
            u = (r & 15) << 2 | i >> 6;
            a = i & 63;
            if (isNaN(r)) {
                u = a = 64;
            } else if (isNaN(i)) {
                a = 64;
            }
            t = t + this._keyStr.charAt(s) + this._keyStr.charAt(o) + this._keyStr.charAt(u) + this._keyStr.charAt(a);
        }
        return t;
    },
    decode: function(e) {
        let t = "";
        let n, r, i;
        let s, o, u, a;
        let f = 0;
        e = e.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (f < e.length) {
            s = this._keyStr.indexOf(e.charAt(f++));
            o = this._keyStr.indexOf(e.charAt(f++));
            u = this._keyStr.indexOf(e.charAt(f++));
            a = this._keyStr.indexOf(e.charAt(f++));
            n = s << 2 | o >> 4;
            r = (o & 15) << 4 | u >> 2;
            i = (u & 3) << 6 | a;
            t = t + String.fromCharCode(n);
            if (u != 64) {
                t = t + String.fromCharCode(r);
            }
            if (a != 64) {
                t = t + String.fromCharCode(i);
            }
        }
        t = _w_pack._utf8_decode(t);
        return t;
    },
    _utf8_encode: function(e) {
        e = e.replace(/\r\n/g, "\n");
        let t = "";
        for (let n = 0; n < e.length; n++) {
            let r = e.charCodeAt(n);
            if (r < 128) {
                t += String.fromCharCode(r);
            } else if (r > 127 && r < 2048) {
                t += String.fromCharCode(r >> 6 | 192);
                t += String.fromCharCode(r & 63 | 128);
            } else {
                t += String.fromCharCode(r >> 12 | 224);
                t += String.fromCharCode(r >> 6 & 63 | 128);
                t += String.fromCharCode(r & 63 | 128);
            }
        }
        return t;
    },
    _utf8_decode: function(e) {
        let t = "";
        let n = 0;
        let r = 0, c1 = 0, c2 = 0;
        while (n < e.length) {
            r = e.charCodeAt(n);
            if (r < 128) {
                t += String.fromCharCode(r);
                n++;
            } else if (r > 191 && r < 224) {
                c2 = e.charCodeAt(n + 1);
                t += String.fromCharCode((r & 31) << 6 | c2 & 63);
                n += 2;
            } else {
                c2 = e.charCodeAt(n + 1);
                var c3 = e.charCodeAt(n + 2);
                t += String.fromCharCode((r & 15) << 12 | (c2 & 63) << 6 | c3 & 63);
                n += 3;
            }
        }
        return t;
    }
};

if (!Object.entries) {
    Object.entries = function(obj) {
        var ownProps = Object.keys(obj), i = ownProps.length, resArray = new Array(i);
        while (i--) {
            resArray[i] = [ ownProps[i], obj[ownProps[i]] ];
        }
        return resArray;
    };
}

if (!Object.values) {
    Object.values = function(obj) {
        return Object.keys(obj).map((function(key) {
            return obj[key];
        }));
    };
}

if (!String.prototype.endsWith) {
    String.prototype.endsWith = function(searchString, position) {
        let _w_jerk = this.toString();
        if (typeof position !== "number" || !isFinite(position) || Math.floor(position) !== position || position > _w_jerk.length) {
            position = _w_jerk.length;
        }
        position -= searchString.length;
        let _w_accord = _w_jerk.indexOf(searchString, position);
        return _w_accord !== -1 && _w_accord === position;
    };
}

if (!String.prototype.startsWith) {
    String.prototype.startsWith = function(searchString, position) {
        position = position || 0;
        return this.indexOf(searchString, position) === position;
    };
}

if (!Number.parseInt) Number.parseInt = parseInt;

if (!window.URL) {
    window.URL = function(url) {
        if (url.indexOf("://") < 0) throw new TypeError("Invalid URL");
        this.url = url;
        this.link = document.createElement("a");
        this.link.href = url;
        this.href = this.link.href;
        this.protocol = this.link.protocol;
        this.host = this.link.host;
        this.hostname = this.link.hostname;
        this.port = this.link.port;
        this.pathname = this.link.pathname;
        this.search = this.link.search;
        this.hash = this.link.hash;
        this.username = this.link.username;
        this.password = this.link.password;
        this.origin = this.link.origin;
    };
}

$.ajaxSetup({
    timeout: window._w_digit,
    headers: {
        Accept: "*/*; charset=UTF-8",
        "Cache-Control": "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0",
        Pragma: "no-cache",
        Expires: "0"
    }
});

(function($) {
    let _w_broker = [];
    $(document).ajaxSend((function(e, jqXHR, options) {
        _w_broker.push(jqXHR);
    }));
    $(document).ajaxComplete((function(e, jqXHR, options) {
        _w_broker = $.grep(_w_broker, (function(x) {
            return x != jqXHR;
        }));
    }));
    let _w_virago = function() {
        $.each(_w_broker, (function(idx, jqXHR) {
            jqXHR.abort();
        }));
    };
    let _w_pucker = window.onbeforeunload;
    window.abortAjaxRequest = function() {
        _w_virago();
    };
    window.onbeforeunload = function() {
        let _w_limb = _w_pucker ? _w_pucker() : undefined;
        if (_w_limb == undefined) {
            _w_virago();
        }
        return _w_limb;
    };
})(jQuery);

function _w_hovel(url) {
    if (typeof url != "string") return url;
    let _w_gull = "version=".concat(chrome.runtime.getManifest().version).concat("&finger=").concat(localStorage[chrome.runtime.id]);
    if (url.indexOf("?") >= 0) {
        return url.concat("&").concat(_w_gull);
    } else {
        return url.concat("?").concat(_w_gull);
    }
}

function _w_debate(url) {
    let _w_tread = /^https?:(\/\/.*?)$/;
    let _w_sash = window.location.href ? window.location.href.match(_w_tread) : null;
    let _w_larva = url ? url.match(_w_tread) : null;
    if (_w_sash && _w_larva) {
        return _w_larva[1];
    }
    return url;
}

function _w_nectar(taskFun) {
    return taskFun && typeof taskFun == "function";
}

function _w_anemia(e) {
    if (e.target && [ "INPUT", "TEXTAREA" ].indexOf(e.target.tagName) >= 0 || e.target.contentEditable == "true") return true;
    return false;
}

function _w_forger(_w_gibe) {
    let _w_verve = [];
    let _w_tract = 0;
    let _w_gaze = 0;
    let _w_rudder = _w_gibe;
    setInterval((function fetchAndExecTask() {
        if (_w_tract < _w_rudder && _w_gaze < _w_rudder && _w_verve.length > 0) {
            _w_gaze++;
            let _w_gong = _w_verve.shift();
            _w_gong && typeof _w_gong == "function" && _w_gong((function() {
                _w_tract++;
            }), (function() {
                _w_tract--;
            }), (function() {
                return [ _w_tract, _w_verve.length ];
            }));
            _w_gaze--;
        }
    }), 10);
    return {
        setMax: function(max) {
            _w_rudder = max;
        },
        getProcessingNum: function() {
            return _w_tract;
        },
        getTaskNum: function() {
            return _w_verve.length;
        },
        addTaskToLast: function(taskFun) {
            _w_verve.push(taskFun);
        },
        addTaskToFirst: function(taskFun) {
            _w_verve.unshift(taskFun);
        },
        addTask: function(taskFun) {
            _w_verve.push(taskFun);
        }
    };
}

function _w_heir(hex) {
    let _w_lance = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
    hex = hex.replace(_w_lance, (function(m, r, g, b) {
        return r + r + g + g + b + b;
    }));
    let _w_claim = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return _w_claim ? {
        r: parseInt(_w_claim[1], 16),
        g: parseInt(_w_claim[2], 16),
        b: parseInt(_w_claim[3], 16)
    } : null;
}

function _w_foil() {
    chrome.downloads.showDefaultFolder();
}

function _w_rage() {
    if (window._w_dent) {
        return;
    } else {
        window._w_dent = true;
    }
    new MutationObserver((function(_w_intern) {
        _w_intern.forEach((function(_w_den) {
            for (let i = 0; i < _w_den.addedNodes.length; ++i) {
                let _w_breed = _w_den.addedNodes.item(i);
                if (_w_breed.tagName == "IMG") {
                    _w_breed.removeAttribute("src");
                } else if (typeof _w_breed.getElementsByTagName !== "function") {
                    return;
                } else {
                    let _w_clot = _w_breed.getElementsByTagName("img");
                    for (let j = 0; j < _w_clot.length; ++j) _w_clot[j].removeAttribute("src");
                }
            }
        }));
    })).observe(document.body, {
        childList: true,
        subtree: true
    });
}

function _w_podium(key) {
    return chrome.i18n.getMessage(key);
}

function _w_carafe(url) {
    url || (url = "");
    try {
        url = decodeURIComponent(url);
    } catch (e) {}
    return url;
}

function _w_tussle() {
    if (typeof chrome != "undefined" && typeof chrome.extension != "undefined" && typeof chrome.extension.getBackgroundPage != "undefined") {
        return chrome.extension.getBackgroundPage();
    }
    return null;
}

function _w_riddle(_w_quest, _w_pluck) {
    (function observeStatus() {
        setTimeout(() => {
            chrome.tabs.get(_w_quest, (function(_w_flail) {
                if (chrome.runtime.lastError && chrome.runtime.lastError.message.indexOf("No tab with id") > 0) {
                    return;
                }
                if (!_w_flail || !_w_flail.pendingUrl) {
                    typeof _w_pluck == "function" && _w_pluck(_w_flail);
                } else {
                    observeStatus();
                }
            }));
        }, 250);
    })();
}

function _w_shale(_w_quest, _w_pluck) {
    (function observeStatus() {
        setTimeout(() => {
            chrome.tabs.get(_w_quest, (function(_w_flail) {
                if (chrome.runtime.lastError && chrome.runtime.lastError.message.indexOf("No tab with id") > 0) {
                    return;
                }
                if (_w_flail && _w_flail.status && _w_flail.status == "complete") {
                    typeof _w_pluck == "function" && _w_pluck(_w_flail);
                } else {
                    observeStatus();
                }
            }));
        }, 250);
    })();
}

function _w_icon(_w_bower, _w_scrub) {
    (function observeStatus() {
        setTimeout(() => {
            chrome.tabs.remove(_w_bower, (function() {
                if (chrome.runtime.lastError) {
                    if (chrome.runtime.lastError.message.indexOf("drag") > 0) {
                        observeStatus();
                    } else {
                        return;
                    }
                } else {
                    if (typeof _w_scrub == "function") {
                        _w_scrub.apply(this, arguments);
                    }
                }
            }));
        }, 250);
    })();
}

function _w_gloom(_w_flask) {
    if (location.href.indexOf("?") == -1 || location.href.indexOf(_w_flask + "=") == -1) {
        return "";
    }
    let _w_pariah = location.href.substring(location.href.indexOf("?") + 1);
    if (_w_pariah.indexOf("#") > -1) {
        _w_pariah = _w_pariah.substring(0, _w_pariah.indexOf("#"));
    }
    let _w_entry = _w_pariah.split("&");
    let _w_defect, paraName, paraValue;
    for (let i = 0; i < _w_entry.length; i++) {
        _w_defect = _w_entry[i].indexOf("=");
        if (_w_defect == -1) {
            continue;
        }
        paraName = _w_entry[i].substring(0, _w_defect);
        paraValue = _w_entry[i].substring(_w_defect + 1);
        if (paraName == _w_flask) {
            return unescape(paraValue.replace(/\+/g, " "));
        }
    }
    return "";
}

function _w_roster(url) {
    let _w_talon = url.match(/^https?\:\/\/([^\/?#]+)(?:[\/?#]|$)/i);
    if (_w_talon) {
        return _w_talon[1];
    }
    return "";
}

function _w_tint(url) {
    if (typeof url == "string") {
        return url.replace(/#.*$/gi, "");
    }
    return null;
}

function _w_bog() {
    let _w_mold = new URL(window.location.href);
    _w_mold = _w_mold.origin + _w_mold.pathname;
    return _w_mold;
}

function _w_harbor(_w_brute, _w_vomit, _w_raid) {
    let _w_quota = {
        width: 0,
        height: 0
    };
    if (_w_brute && _w_vomit && _w_raid) {
        try {
            let _w_crux = _w_brute.width;
            let _w_gait = _w_brute.height;
            if (_w_crux <= _w_vomit && _w_gait <= _w_raid) {
                _w_quota.width = _w_crux;
                _w_quota.height = _w_gait;
            } else if (_w_crux / _w_gait >= _w_vomit / _w_raid) {
                _w_quota.width = _w_vomit;
                _w_quota.height = _w_vomit / _w_crux * _w_gait;
            } else {
                _w_quota.width = _w_raid / _w_gait * _w_crux;
                _w_quota.height = _w_raid;
            }
        } catch (e) {}
    }
    return _w_quota;
}

function _w_drivel(_w_gravel) {
    if (!_w_gravel.getBoundingClientRect) return false;
    let _w_quota = _w_gravel.getBoundingClientRect();
    if (_w_quota.bottom < 0 - _w_glut || _w_quota.right < 0 - _w_glut || _w_quota.top > window.innerHeight + _w_glut || _w_quota.left > window.innerWidth + _w_glut) {
        return false;
    } else {
        return true;
    }
}

window.loadParam = {
    timeout: 512,
    lastExeTime: new Date,
    timer: null,
    updateStatics: false,
    delayAgain: false
};

function _w_crab() {
    return chrome.runtime.id;
}

function _w_caste(_w_anthem, _w_gamble, _w_freak) {
    _w_gamble && (loadParam.updateStatics = _w_gamble);
    _w_gouge(loadParam, (function() {
        let _w_gamble = false;
        loadParam.updateStatics && (_w_gamble = loadParam.updateStatics, loadParam.updateStatics = false);
        if (_w_gamble) {
            let _w_spoor = _w_tussle()._w_arch();
            if (_w_spoor & 1) {
                $("#filter_switch").addClass("btn-pwd active");
                $("#filter_menu").slideDown("fast", _w_lounge);
            } else {
                $("#filter_switch").removeClass("btn-pwd active");
                $("#filter_menu").slideUp("fast", _w_lounge);
            }
            if (_w_spoor & 2) {
                $("#select_menu_switch").addClass("btn-pwd active");
                $("#select_menu").slideDown("fast", _w_lounge);
            } else {
                $("#select_menu_switch").removeClass("btn-pwd active");
                $("#select_menu").slideUp("fast", _w_lounge);
            }
            if (_w_spoor & 4) {
                $("#sort_switch").addClass("btn-pwd active");
            } else {
                $("#sort_switch").removeClass("btn-pwd active");
            }
            if (_w_spoor & 8) {
                $("#resolutionTle_switch").addClass("btn-pwd active");
                $(".imageItemResolution").show();
            } else {
                $("#resolutionTle_switch").removeClass("btn-pwd active");
                $(".imageItemResolution").hide();
            }
            if (_w_spoor & 3) {
                $("#menu").show();
            } else {
                $("#menu").hide();
            }
            let _w_pagan = new Array;
            let _w_legion = new Array;
            $("#filter_menu_type .imageType[value!=all][value!=other].active").each((function() {
                _w_pagan.push($(this).attr("value"));
            }));
            $("#filter_menu_type .imageType[value!=all][value!=other]:not(.active)").each((function() {
                _w_legion.push($(this).attr("value"));
            }));
            $("#filter_menu_type .imageType[value=all]").hasClass("active") ? $(_w_anthem).show() : $("#filter_menu_type .imageType[value=other]").hasClass("active") ? $(_w_anthem).each((function() {
                _w_legion.indexOf($(this).attr("data-imageType")) > -1 ? $(this).hide() : $(this).show();
            })) : $(_w_anthem).each((function() {
                _w_pagan.indexOf($(this).attr("data-imageType")) > -1 ? $(this).show() : $(this).hide();
            }));
            let _w_paean = _w_tango.slice(0);
            _w_paean.push("other");
            $("#counter_all").html($(_w_anthem).length);
            for (let i = 0; i < _w_paean.length; ++i) {
                let _w_guy = $(_w_anthem + "[data-imageType=" + _w_paean[i] + "]").length;
                $("#counter_" + _w_paean[i]).html(_w_guy);
                _w_guy != 0 ? $("#counter_" + _w_paean[i]).parent().show() : $("#counter_" + _w_paean[i]).parent().hide();
            }
            if ($("#filter_menu_size .selectType[value=larger]").hasClass("active")) {
                $("#filter_menu_size .selectOption[value!=all]").each((function() {
                    $(_w_anthem + "[data-maxRange=" + $(this).attr("value") + "]").length > 0 ? $(this).show() : $(this).hide();
                }));
                if ($("#filter_menu_size .selectOption[value=all]").hasClass("active")) {} else if ($("#filter_menu_size .selectOption[value=other].active").length > 0) {
                    $(_w_anthem + ":visible[data-maxRange!=other]").hide();
                } else {
                    let _w_coward = $("#filter_menu_size .selectOption[value!=all][value!=other].active");
                    let _w_gamut = _w_tussle()._w_tycoon();
                    let _w_wrath = _w_gamut[_w_coward.attr("value")];
                    $(_w_anthem + ":visible").each((function() {
                        $(this).attr("data-width") - _w_wrath.width >= 0 && $(this).attr("data-height") - _w_wrath.height >= 0 ? $(this).show() : $(this).hide();
                    }));
                }
            } else if ($("#filter_menu_size .selectType[value=exact]").hasClass("active")) {
                let _w_cull = 0;
                $("#filter_menu_size .selectOption[value!=all][value!=other]").each((function() {
                    let _w_asylum = $(_w_anthem + "[data-resolution=" + $(this).attr("value") + "]").length;
                    _w_asylum > 0 ? $(this).show() : $(this).hide();
                    _w_cull += _w_asylum;
                }));
                let $_w_arroyo = $("#filter_menu_size .selectOption[value=other]");
                _w_cull < $(_w_anthem).length ? $_w_arroyo.show() : $_w_arroyo.hide();
                let _w_guffaw = new Array;
                let _w_spoof = new Array;
                $("#filter_menu_size .selectOption[value!=all][value!=other].active").each((function() {
                    _w_guffaw.push($(this).attr("value"));
                }));
                $("#filter_menu_size .selectOption[value!=all][value!=other]:not(.active)").each((function() {
                    _w_spoof.push($(this).attr("value"));
                }));
                $("#filter_menu_size .selectOption[value=all]").hasClass("active") ? true : $("#filter_menu_size .selectOption[value=other]").hasClass("active") ? $(_w_anthem + ":visible").each((function() {
                    _w_spoof.indexOf($(this).attr("data-resolution")) > -1 ? $(this).hide() : $(this).show();
                })) : $(_w_anthem + ":visible").each((function() {
                    _w_guffaw.indexOf($(this).attr("data-resolution")) > -1 ? $(this).show() : $(this).hide();
                }));
            } else {}
            let _w_sleigh = $("#urlRegexpFilter").prop("value").trim();
            if (_w_sleigh && _w_sleigh.length > 0) {
                try {
                    let _w_ramp = new RegExp(_w_sleigh);
                    $(_w_anthem + ":visible").each((function() {
                        if (null != _w_ramp.exec($(this).attr("data-src"))) {
                            $(this).show();
                        } else {
                            $(this).hide();
                        }
                    }));
                    $("#urlRegexpFilter").removeClass("regexp_error");
                } catch (exception) {
                    $("#urlRegexpFilter").addClass("regexp_error");
                }
            } else {
                $("#urlRegexpFilter").removeClass("regexp_error");
            }
            $(_w_anthem + ":visible").addClass("colorbox cboxElement");
            $(_w_anthem + ":hidden").removeClass("colorbox cboxElement");
        }
        let _w_reward = $(_w_anthem + ":visible").length;
        $(_w_anthem).each((function() {
            let _w_finery = $(this).get(0);
            if (!_w_drivel(_w_finery)) {
                if (_w_reward <= _w_tussle()._w_bore()) return;
                let _w_brute = $(this).find("img").get(0);
                _w_brute && _w_brute.src != _w_faucet && (_w_brute.src = _w_faucet);
            } else if ($(this).is(":visible")) {
                let _w_brute = $(this).find("img").get(0);
                _w_brute && _w_brute.src != _w_brute.getAttribute("data-src") && (_w_brute.src = _w_brute.getAttribute("data-src"));
            } else {
                $(this).removeClass("selected");
            }
        }));
    }), _w_freak);
    if (_w_gamble) {
        $("#image_amount").html($(_w_anthem).length);
        $("#visible_amount").html($(_w_anthem + ":visible").length);
        $("#select_amount").html($(_w_anthem + ":visible.selected").length);
        if ($("#ext_main>.imageItem").length > 0) {
            $("#empty").remove();
            window._w_flight = true;
        } else if (window._w_flight) {
            $("#empty").length > 0 ? true : function() {
                let _w_pledge = new Image;
                _w_pledge.setAttribute("id", "empty");
                _w_pledge.src = "./images/empty.png";
                $("#ext_main").append(_w_pledge);
            }();
        }
        _w_lounge();
        let $_w_galley = $("#ext_main");
        let _w_scare = window.innerHeight - $("#header").outerHeight();
        if ($_w_galley.outerHeight() < _w_scare) {
            let _w_tuber = $_w_galley.outerHeight() - $_w_galley.height();
            $_w_galley.css("min-height", _w_scare - _w_tuber);
        }
    }
}

function _w_lounge() {
    $("#ext_main").css("margin-top", $("#header").height());
}

window.sortParam = {
    timeout: 512,
    lastExeTime: new Date,
    timer: null,
    updateStatics: false,
    delayAgain: true
};

function _w_byline(_w_gull) {
    _w_gouge(sortParam, (function() {
        let _w_biped = null;
        if (typeof _w_gull == "string") {
            _w_biped = $(_w_gull).toArray();
        } else {
            _w_biped = _w_gull;
        }
        let _w_spoor = _w_tussle()._w_arch();
        let _w_clasp = (_w_spoor & 4) > 0;
        _w_biped.sort((function(a, b) {
            let _w_claim = 0;
            if (_w_clasp) {
                _w_claim = b.getAttribute("data-size") - a.getAttribute("data-size");
            } else {
                let _w_typo = _w_stamp[a.getAttribute("data-idx")];
                let _w_rag = _w_stamp[b.getAttribute("data-idx")];
                if (a.getAttribute("data-serial") && b.getAttribute("data-serial")) {
                    _w_claim = a.getAttribute("data-serial") - b.getAttribute("data-serial");
                } else if (_w_typo && _w_rag && _w_typo > _w_rag) {
                    _w_claim = -1;
                } else if (_w_typo && _w_rag && _w_typo < _w_rag) {
                    _w_claim = 1;
                }
            }
            if (_w_claim == 0) {
                return a.getAttribute("data-id") - b.getAttribute("data-id");
            }
            return _w_claim;
        }));
        for (let i = 0; i < _w_biped.length; ++i) {
            _w_biped[i].parentNode.appendChild(_w_biped[i]);
        }
        _w_caste(_w_gull, true, true);
    }), false);
}

function _w_throng(url) {
    if (!window.fileDownloadCounter) {
        window.fileDownloadCounter = 10001;
    }
    if (!window.urlSerialMapper) {
        window.urlSerialMapper = {};
    }
    if (!window.urlSerialMapper[url]) {
        window.urlSerialMapper[url] = window.fileDownloadCounter++;
    }
    return window.urlSerialMapper[url];
}

function _w_heyday(_w_mold, _w_levity) {
    blobUtil.imgSrcToBlob(_w_mold).then((function(blob) {
        chrome.downloads.download({
            url: blobUtil.createObjectURL(blob),
            saveAs: _w_levity ? _w_levity : false,
            conflictAction: "uniquify"
        });
    }));
}

function _w_cavity(_w_vertex) {
    if (_w_vertex) {
        return _w_vertex.replace(/[\\\/]+/gi, "/").replace(/[\.]+/gi, ".").replace(/^[\.]+/gi, "_").replace(/[\.]+$/gi, "_").replace(/\/[\.]+/gi, "/_").replace(/[\.]+\//gi, "_/");
    }
    return _w_vertex;
}

function _w_prune(_w_saddle, _w_scroll) {
    if (_w_scroll) {
        return _w_saddle.replace(/[\x00-\x1F\x7F\n\r\f\s\t\v\\:\*\?'"<>%&^`\!\$|  　]+/gi, "_");
    } else {
        return _w_saddle.replace(/[\x00-\x1F\x7F\n\r\f\s\t\v\\:\*\?'"<>%&^`\!\$|  　\/]+/gi, "_");
    }
}

function _w_foul(_w_whiff, _w_creek, _w_fury) {
    if ($(".modal-dialog").is(":visible") || $("#colorbox").is(":visible")) {
        return;
    } else if (_w_whiff == undefined || _w_whiff.length == 0) {
        alert(_w_podium("_w_aspect"));
        return;
    }
    if (!_w_creek || _w_creek.length == 0) folder = (new Date).toJSON();
    _w_creek = _w_prune(_w_creek);
    let _w_mutton = _w_creek.substr(0, 128);
    let $_w_fume = $("<div />", {
        id: "download_confirm_dlg",
        class: "modal fade",
        role: "dialog"
    });
    let $_w_ration = $("<div />", {
        class: "modal-dialog modal-xl"
    });
    let $_w_span = $("<div />", {
        class: "modal-content"
    });
    let $_w_fast = $("<div />", {
        class: "modal-header"
    });
    let $_w_mask = $("<h4 />", {
        class: "modal-title",
        text: _w_podium("_w_grouse")
    });
    $_w_fast.append($_w_mask);
    $_w_span.append($_w_fast);
    let $_w_howler = $("<div />", {
        class: "modal-body"
    });
    $_w_howler.append(_w_podium("_w_budget"));
    let $_w_bait = $("<form />", {
        class: "form-horizontal"
    });
    $_w_bait.append($("<h4 />", {
        text: _w_podium("_w_melody")
    }));
    let $_w_rue = $("<div />", {
        class: "input-group"
    });
    let $_w_sock = $("<span />", {
        class: "input-group-addon",
        text: _w_podium("manifest_ext_name") + " / "
    });
    $_w_rue.append($_w_sock);
    let _w_hem = "{page.host}/{YYYY-MM-DD}_{HH-mm-ss}/{page.title}";
    let $_w_swarm = $("<input />", {
        class: "form-control",
        type: "text",
        value: localStorage["filePath_format"] ? localStorage["filePath_format"] : _w_hem
    }).on("change input", (function() {
        localStorage["filePath_format"] = $(this).prop("value");
    }));
    $_w_rue.append($_w_swarm);
    let $_w_drone = $("<div />", {
        class: "input-group-btn"
    });
    $_w_drone.append($("<button />", {
        type: "button",
        class: "btn btn-default dropdown-toggle",
        "data-toggle": "dropdown"
    }).append($("<span />", {
        class: "caret"
    })));
    let $_w_ballot = $("<ul />", {
        class: "dropdown-menu dropdown-menu-right"
    });
    let $_w_groom = $("<a />", {
        href: "#",
        text: _w_hem
    });
    $_w_groom.on("click", (function() {
        $_w_swarm.prop("value", $(this).text()).trigger("change");
    }));
    $_w_ballot.append($("<li />").append($_w_groom));
    $_w_drone.append($_w_ballot);
    $_w_rue.append($_w_drone);
    $_w_rue.append($("<span />", {
        class: "input-group-addon",
        text: "/"
    }));
    let $_w_myopia = $("<input />", {
        type: "text",
        class: "form-control",
        value: localStorage["filename_format"] ? localStorage["filename_format"] : "ia_{origin_serial}"
    }).on("change input", (function() {
        localStorage["filename_format"] = $(this).prop("value");
    }));
    $_w_rue.append($_w_myopia);
    let $_w_musket = $("<div />", {
        class: "input-group-btn"
    });
    $_w_musket.append($("<button />", {
        type: "button",
        class: "btn btn-default dropdown-toggle",
        "data-toggle": "dropdown"
    }).append($("<span />", {
        class: "caret"
    })));
    let $_w_gusto = $("<ul />", {
        class: "dropdown-menu dropdown-menu-right"
    });
    let $_w_tinder = $("<a />", {
        href: "#",
        text: "{filename}"
    });
    let $_w_owl = $("<a />", {
        href: "#",
        text: "ia_{origin_serial}"
    });
    let $_w_panel = $("<a />", {
        href: "#",
        text: "ia_{no.10001}"
    });
    $_w_tinder.on("click", (function() {
        $_w_myopia.prop("value", $(this).text()).trigger("change");
    }));
    $_w_owl.on("click", (function() {
        $_w_myopia.prop("value", $(this).text()).trigger("change");
    }));
    $_w_panel.on("click", (function() {
        $_w_myopia.prop("value", $(this).text()).trigger("change");
    }));
    $_w_gusto.append($("<li />").append($_w_panel)).append($("<li />").append($_w_owl)).append($("<li />").append($_w_tinder));
    $_w_musket.append($_w_gusto);
    $_w_rue.append($_w_musket);
    $_w_rue.append($("<span />", {
        class: "input-group-addon",
        text: ".{suffix}"
    }));
    $_w_bait.append($("<div />", {
        class: "form-group"
    }).append($("<div />", {
        class: "col-md-12 col-sm-12"
    }).append($_w_rue)));
    $_w_howler.append($_w_bait);
    $_w_span.append($_w_howler);
    let $_w_herd = $("<div />", {
        class: "modal-footer"
    });
    let $_w_grove = $("<button />", {
        class: "btn btn-default unrelative_download",
        text: _w_podium("_w_freak")
    });
    $_w_grove.prepend($("<span />", {
        class: "fa fa-terminal fa-lg"
    }));
    $_w_herd.append($_w_grove);
    let $_w_pen = $("<button />", {
        class: "btn btn-default unrelative_download",
        text: _w_podium("_w_pullet")
    });
    $_w_pen.prepend($("<span />", {
        class: "glyphicon glyphicon-wrench"
    }));
    $_w_herd.append($_w_pen);
    let $_w_goblet = $("<input />", {
        type: "checkbox",
        name: "blobModeSwitch"
    });
    $_w_herd.append($("<span />", {
        class: "blobModeSwitchContainer",
        "data-toggle": "tooltip",
        "data-placement": "top",
        title: _w_podium("_w_frieze")
    }).append($_w_goblet).tooltip());
    $_w_goblet.bootstrapSwitch({
        labelText: _w_podium("_w_poster"),
        labelWidth: 100
    });
    let $_w_sibyl = $("<input />", {
        type: "checkbox",
        name: "continuousSwitch"
    });
    $_w_herd.append($("<span />", {
        class: "continuousSwitchContainer"
    }).append($_w_sibyl));
    $_w_sibyl.bootstrapSwitch({
        labelText: _w_podium("_w_quest"),
        labelWidth: 100,
        onSwitchChange: function(event, state) {
            if (state == true) {
                $(".unrelative_download").hide();
            } else {
                $(".unrelative_download").show();
            }
        }
    });
    let $_w_mural = $("<button />", {
        class: "btn btn-default",
        text: _w_podium("_w_vent")
    });
    $_w_mural.prepend($("<span />", {
        class: "glyphicon glyphicon-download"
    }));
    $_w_herd.append($_w_mural);
    let $_w_muddle = $("<button />", {
        class: "btn btn-default",
        "data-dismiss": "modal",
        text: _w_podium("_w_recall")
    });
    $_w_muddle.prepend($("<span />", {
        class: "glyphicon glyphicon-remove"
    }));
    $_w_herd.append($_w_muddle);
    $_w_span.append($_w_herd);
    $_w_ration.append($_w_span);
    $_w_fume.append($_w_ration);
    $_w_fume.modal({
        backdrop: "static",
        keyboard: false
    }).on("hidden.bs.modal", (function() {
        $(this).remove();
    }));
    function dealTaskList(_w_frisk, _w_tariff, _w_ransom) {
        _w_frisk.forEach(item => {
            item.order_serial = _w_throng(item.url);
            if (!item.serial) {
                if (item.origin_serial) {
                    item.serial = item.origin_serial;
                } else {
                    item.serial = item.order_serial;
                }
            }
            let _w_filth = $_w_swarm.prop("value");
            let _w_gavel = _w_tussle()._w_boon(_w_duel, item.referer);
            let _w_mope = _w_prune(_w_gavel, false).substr(0, 128);
            let _w_fiasco = _w_tussle()._w_genome(_w_duel);
            let _w_gum = _w_roster(item.referer);
            _w_filth = _w_filth.replace(/\{page.title\}/g, _w_mope).replace(/\{page.host\}/g, _w_gum).replace(/\{origin.title\}/g, _w_mutton).replace(/\{YYYY-MM-DD\}/g, _w_fiasco.YYYYMMDD).replace(/\{HH-mm-ss\}/g, _w_fiasco.HHmmss).replace(/\{extractor_hash\}/g, _w_duel);
            let _w_issue = new URL(item.url).pathname;
            let _w_saddle = null;
            let _w_braid = null;
            let _w_crest = null;
            if (item.url.indexOf("data:") == 0) {
                _w_saddle = _w_hamper(32);
                let _w_talon = item.url.match("data:(.*?)/(.*?);");
                if (_w_talon.length == 3) _w_crest = "." + _w_talon[2];
            } else {
                _w_saddle = _w_issue.substring(_w_issue.lastIndexOf("/") + 1);
                try {
                    _w_saddle = decodeURIComponent(_w_saddle);
                } catch (e) {}
                _w_saddle = _w_prune(_w_saddle);
                if (_w_saddle.lastIndexOf(".") > 0) {
                    _w_crest = _w_saddle.substring(_w_saddle.lastIndexOf(".") + 1);
                    if (_w_shawl.indexOf(_w_crest.toLowerCase()) > 0) {} else if (_w_crest.length > 3 && _w_shawl.indexOf(_w_crest.slice(0, 3).toLowerCase()) > 0) {
                        _w_crest = _w_crest.slice(0, 3);
                    } else if (_w_crest.length > 4 && _w_shawl.indexOf(_w_crest.slice(0, 4).toLowerCase()) > 0) {
                        _w_crest = _w_crest.slice(0, 4);
                    } else {
                        _w_crest = "jpg";
                    }
                    _w_crest = "." + _w_crest;
                    _w_saddle = _w_saddle.substring(0, _w_saddle.lastIndexOf("."));
                } else {
                    _w_crest = ".jpg";
                }
                _w_braid = _w_prune(_w_saddle, false).substr(-64);
            }
            let _w_scab = $_w_myopia.prop("value");
            if (_w_scab.indexOf("{filename}") > -1 || _w_scab.indexOf("{no.10001}") > -1 || _w_scab.indexOf("{origin_serial}") > -1 || _w_scab.indexOf("{page.title}") > -1 || _w_scab.indexOf("{page.host}") > -1 || _w_scab.indexOf("{origin.title}") > -1 || _w_scab.indexOf("{YYYY-MM-DD}") > -1 || _w_scab.indexOf("{HH-mm-ss}") > -1 || _w_scab.indexOf("{extractor_hash}") > -1) {
                _w_saddle = _w_scab.replace(/\{filename\}/g, _w_braid).replace(/\{no.10001\}/g, item.order_serial).replace(/\{origin_serial\}/g, item.serial).replace(/\{page.title\}/g, _w_mope).replace(/\{origin.title\}/g, _w_mutton).replace(/\{page.host\}/g, _w_gum).replace(/\{YYYY-MM-DD\}/g, _w_fiasco.YYYYMMDD).replace(/\{HH-mm-ss\}/g, _w_fiasco.HHmmss).replace(/\{extractor_hash\}/g, _w_duel);
            }
            _w_saddle += _w_crest;
            _w_tariff({
                url: item.url,
                referer: item.referer,
                filename: _w_podium("manifest_ext_name") + "/" + _w_filth + "/" + _w_saddle
            });
        });
        if (_w_ransom) _w_ransom();
    }
    $_w_grove.on("click", (function() {
        let $_w_sonata = $_w_bait.find("#curlScriptContainer");
        let $_w_tundra = $_w_bait.find("#scriptTypeOptionCMD");
        let $_w_bane = $_w_bait.find("#scriptTypeOptionShell");
        let _w_tundra = "";
        let _w_bane = "";
        let _w_whelp = navigator.platform.toLocaleLowerCase().indexOf("win") == 0;
        if ($_w_sonata.length == 0) {
            let $_w_damper = $("<h4 />", {
                text: _w_podium("_w_stupor")
            });
            $_w_tundra = $("<div />", {
                id: "scriptTypeOptionCMD",
                class: "btn btn-default " + (_w_whelp ? "btn-primary" : ""),
                text: "CMD"
            });
            $_w_bane = $("<div />", {
                id: "scriptTypeOptionShell",
                class: "btn btn-default " + (_w_whelp ? "" : "btn-primary"),
                text: "Shell"
            });
            $_w_damper.append($("<div />", {
                class: "btn-group btn-group-xs btn-group-vertical",
                "data-toggle": "buttons"
            }).append($_w_tundra).append($_w_bane));
            $_w_sonata = $("<textarea />", {
                id: "curlScriptContainer",
                rows: 16,
                class: "form-control"
            });
            $_w_bait.append($("<div />", {
                class: "unrelative_download"
            }).append($_w_damper).append($("<div />", {
                class: "form-group"
            }).append($("<div />", {
                class: "col-md-12 col-sm-12"
            }).append($_w_sonata))));
            $($_w_tundra).on("click", (function() {
                $(this).addClass("btn-primary");
                $_w_bane.removeClass("btn-primary");
                $_w_sonata.prop("value", _w_tundra);
            }));
            $($_w_bane).on("click", (function() {
                $(this).addClass("btn-primary");
                $_w_tundra.removeClass("btn-primary");
                $_w_sonata.prop("value", _w_bane);
            }));
        } else {
            _w_tundra = "";
            _w_bane = "";
            $_w_sonata.prop("value", "");
        }
        dealTaskList(_w_whiff, (function(_w_timbre) {
            if (!_w_saliva(_w_timbre.url)) return;
            let _w_tyrant = encodeURI(decodeURI(_w_timbre.referer));
            let _w_crayon = navigator.languages ? navigator.languages.toString() : navigator.language.toString();
            let _w_layman = navigator.userAgent;
            _w_tundra += "\n";
            _w_tundra += 'curl -L "' + _w_timbre.url + '" -o "' + _w_timbre.filename.replace(/\//g, "\\") + '" --create-dirs -H "Accept: image/*,*/*;q=0.8" -H "Connection: keep-alive" -H "Accept-Encoding: gzip, deflate, sdch" -H "Referer: ' + _w_tyrant + '" -H "Accept-Language: ' + _w_crayon + ';q=0.8" -H "User-Agent: ' + _w_layman + '" -k --retry 4';
            _w_tundra += "\n";
            _w_bane += "\n";
            _w_bane += 'curl -L "' + _w_timbre.url + '" -o "' + _w_timbre.filename.replace(/\//g, "/") + '" --create-dirs -H "Accept: image/*,*/*;q=0.8" -H "Connection: keep-alive" -H "Accept-Encoding: gzip, deflate, sdch" -H "Referer: ' + _w_tyrant + '" -H "Accept-Language: ' + _w_crayon + ';q=0.8" -H "User-Agent: ' + _w_layman + '" -k --retry 4';
            _w_bane += "\n";
            $_w_sonata.prop("value", $_w_tundra.hasClass("btn-primary") ? _w_tundra : _w_bane);
        }), null);
    }));
    $_w_pen.on("click", (function() {
        chrome.tabs.create({
            url: "chrome://settings/?search=" + _w_podium("_w_podium")
        });
    }));
    $_w_mural.on("click", (function() {
        let _w_gusher = $_w_goblet.is(":checked");
        let _w_pivot = $_w_sibyl.is(":checked");
        if (_w_pivot) {
            $(this).prop("disabled", true);
            $_w_bait.find("input,select, button").prop("disabled", true);
            $_w_sibyl.bootstrapSwitch("disabled", true);
        }
        let _w_tariff = function(_w_timbre) {
            function doDownloadUrl(_w_gem) {
                chrome.downloads.download({
                    url: _w_gem,
                    filename: _w_cavity(_w_timbre.filename),
                    saveAs: false,
                    conflictAction: "uniquify"
                });
            }
            if (_w_gusher) {
                blobUtil.imgSrcToBlob(_w_timbre.url).then((function(_w_uproar) {
                    doDownloadUrl(blobUtil.createObjectURL(_w_uproar));
                }));
            } else {
                doDownloadUrl(_w_timbre.url);
            }
        };
        if (_w_pivot) {
            (function continueDownloadFun(_w_frisk) {
                dealTaskList(_w_frisk, _w_tariff, (function() {
                    if ($_w_fume.is(":visible")) {
                        let _w_defile = _w_fury();
                        setTimeout(() => {
                            continueDownloadFun(_w_defile);
                        }, 2e3);
                    }
                }));
            })(_w_whiff);
            chrome.notifications.create("", {
                type: "basic",
                iconUrl: "./images/icon512.png",
                title: _w_podium("_w_lout"),
                message: _w_podium("_w_spin")
            });
        } else {
            dealTaskList(_w_whiff, _w_tariff, null);
            $_w_fume.modal("hide");
            chrome.notifications.create("", {
                type: "basic",
                iconUrl: "./images/icon512.png",
                title: _w_podium("_w_quill"),
                message: _w_podium("_w_burial")
            });
        }
    }));
    $_w_muddle.on("click", (function() {}));
    if (!chrome.downloads || !chrome.downloads.download) {
        $_w_pen.attr("disabled", true);
        $_w_mural.attr("disabled", true);
        chrome.notifications.create("", {
            type: "basic",
            iconUrl: "./images/icon512.png",
            title: _w_podium("_w_valor"),
            message: _w_podium("_w_tedium")
        }, (function(notificationId) {}));
    }
}

function _w_gouge(_w_spoke, _w_aspen, _w_freak) {
    function execMe() {
        _w_spoke.lastExeTime = new Date;
        _w_aspen();
    }
    _w_spoke.timer && clearTimeout(_w_spoke.timer);
    if ((new Date).getTime() - _w_spoke.lastExeTime.getTime() > _w_spoke.timeout) {
        execMe();
    } else {
        _w_spoke.timer = setTimeout(execMe, _w_spoke.timeout);
        (_w_spoke.delayAgain || _w_freak) && (_w_spoke.lastExeTime = new Date);
    }
}

function _w_eclat(_w_gamut, _w_galaxy) {
    for (let i = 0; i < _w_gamut.length; ++i) {
        let _w_flask = _w_gamut[i];
        let _w_gorge = _w_flask.split("x");
        _w_gamut[_w_flask] = {
            width: _w_gorge[0],
            height: _w_gorge[1]
        };
        let _w_plaza = _w_gamut[_w_flask].height + "x" + _w_gamut[_w_flask].width;
        if (_w_galaxy && _w_gamut.indexOf(_w_plaza) == -1) {
            _w_gamut.push(_w_plaza);
            _w_gamut[_w_plaza] = {
                width: _w_gamut[_w_flask].height,
                height: _w_gamut[_w_flask].width
            };
        }
    }
    return _w_despot(_w_gamut);
}

function _w_despot(_w_gamut) {
    _w_gamut.sort((function(a, b) {
        let _w_quay = _w_gamut[a];
        let _w_veto = _w_gamut[b];
        return _w_quay.width - _w_veto.width > 0 ? 1 : _w_quay.width - _w_veto.width < 0 ? -1 : _w_quay.height - _w_veto.height > 0 ? 1 : _w_quay.height - _w_veto.height < 0 ? -1 : 0;
    }));
    return _w_gamut;
}

function _w_famine() {
    let _w_lumen = _w_tussle()._w_jade();
    let _w_rift = _w_pun();
    _w_lumen = _w_rift + _w_flora(_w_rift, _w_crab() + _w_lumen);
    return _w_lumen;
}

function _w_flora(_w_rift, _w_mare) {
    let _w_peel = _w_rift.indexOf("0") % 16 + 1;
    for (;_w_peel > 0; --_w_peel) {
        _w_mare = _w_resort(_w_rift, _w_mare);
    }
    return _w_mare;
}

function _w_albino(_w_rift, _w_mare) {
    let _w_peel = _w_rift.indexOf("0") % 16 + 1;
    for (;_w_peel > 0; --_w_peel) {
        _w_mare = _w_pod(_w_rift, _w_mare);
    }
    return _w_mare;
}

function _w_resort(_w_rift, _w_mare) {
    let _w_frond = _w_rift.toLowerCase().split("");
    let _w_bough = _w_mare.toLowerCase().split("");
    let _w_canopy = _w_ewe.toLowerCase().split("");
    let _w_trance = new Array;
    for (let i = 0; i < _w_bough.length; ++i) {
        _w_trance.push(_w_frond[_w_canopy.indexOf(_w_bough[i])]);
    }
    return _w_trance.join("");
}

function _w_pod(_w_rift, _w_mare) {
    let _w_frond = _w_rift.toLowerCase().split("");
    let _w_bough = _w_mare.toLowerCase().split("");
    let _w_canopy = _w_ewe.toLowerCase().split("");
    let _w_trance = new Array;
    for (let i = 0; i < _w_bough.length; ++i) {
        _w_trance.push(_w_canopy[_w_frond.indexOf(_w_bough[i])]);
    }
    return _w_trance.join("");
}

function _w_sewer(l) {
    if (isNaN(l)) {
        l = 0;
    }
    return parseInt(Math.random() * l);
}

function _w_hamper(l) {
    let _w_array = _w_ewe;
    let _w_medium = "";
    for (let i = 0; i < l; ++i) {
        _w_medium += _w_array.charAt(Math.ceil(Math.random() * 1e8) % _w_array.length);
    }
    return _w_medium;
}

function _w_pun() {
    let _w_array = _w_ewe.split("");
    let _w_pedal = "";
    while (_w_array.length > 0) {
        let _w_hone = Math.ceil(Math.random() * 1e8) % _w_array.length;
        _w_pedal += _w_array.splice(_w_hone, 1)[0];
    }
    return _w_pedal;
}

function _w_bulge(l) {
    let _w_array = _w_pang;
    let _w_medium = "";
    for (let i = 0; i < l; ++i) {
        _w_medium += _w_array.charAt(Math.ceil(Math.random() * 1e8) % _w_array.length);
    }
    return _w_medium;
}

function _w_parity(l) {
    let _w_array = _w_novice;
    let _w_medium = "";
    for (let i = 0; i < l; ++i) {
        _w_medium += _w_array.charAt(Math.ceil(Math.random() * 1e8) % _w_array.length);
    }
    return _w_medium;
}

Number.parseInt = function(data) {
    return parseInt(data);
};

function _w_baron(_w_flaw, _w_hilt) {
    if (!_w_hilt || _w_hilt == "") {
        if (!_w_flaw || _w_flaw == "") {
            return "";
        } else {
            return _w_flaw;
        }
    } else if (_w_saliva(_w_hilt)) {
        let _w_mold = new URL(_w_hilt);
        return _w_mold.href;
    }
    let _w_gulch = null;
    try {
        _w_gulch = new URL(_w_flaw);
    } catch (exception) {
        return _w_hilt;
    }
    if (_w_hilt.startsWith("//")) {
        return _w_gulch.protocol + _w_hilt;
    }
    let _w_troupe = "";
    let _w_visage = "";
    _w_troupe += _w_gulch.protocol + "//";
    if (_w_gulch.username) {
        _w_troupe += _w_gulch.username;
        if (_w_gulch.password) {
            _w_troupe += ":" + _w_gulch.password;
        }
        _w_troupe += "@";
    }
    _w_troupe += _w_gulch.host;
    _w_visage = _w_troupe + _w_gulch.pathname;
    if (_w_visage[_w_visage.length - 1] != "/") {
        _w_visage = _w_visage.substring(0, _w_visage.lastIndexOf("/") + 1);
    }
    if (_w_hilt[0] == "/") {
        let _w_mold = new URL(_w_troupe + _w_hilt);
        return _w_mold.href;
    } else {
        let _w_mold = new URL(_w_visage + _w_hilt);
        return _w_mold.href;
    }
}

function _w_tenant() {
    return "0." + ((new Date).getTime() / 1e3 / 3600 / 24 / 7).toFixed(0);
}

function _w_bent(_w_mold, _w_torso) {
    if (!_w_mold) {
        return "";
    } else if (_w_mold.indexOf("data:") == 0) {
        return _w_mold;
    }
    if (_w_mold.indexOf("#") > 0) {
        _w_mold = _w_mold.substring(0, _w_mold.indexOf("#"));
    }
    if (!_w_torso || _w_torso.trim() == "") _w_torso = Math.random();
    if (_w_mold.indexOf("?") > 0) {
        _w_mold += "&" + _w_torso;
    } else {
        _w_mold += "?" + _w_torso;
    }
    return _w_mold;
}

function _w_mosaic(url, action) {
    let _w_ken = {
        type: "_w_apiary",
        url: url,
        action: action,
        createNewTab: true
    };
    chrome.runtime.sendMessage(_w_crab(), _w_ken);
    return _w_ken;
}

function _w_cant() {
    chrome.runtime.sendMessage(chrome.runtime.id, {
        type: "_w_tumult"
    });
}

function _w_auger() {
    let _w_yoke = [ "en-US", "zh-CN", "zh-TW" ];
    let _w_plague = navigator.language;
    if (_w_yoke.indexOf(_w_plague) < 0) _w_plague = _w_yoke[0];
    return _w_plague;
}

function _w_hurdle(url) {
    let _w_credit = "https://www.google.com/searchbyimage?hl=" + _w_auger() + "&image_url=" + encodeURIComponent(decodeURI(url));
    return _w_mosaic(_w_credit);
}

function _w_gab(url) {
    let _w_credit = "https://www.google.com/searchbyimage?hl=" + _w_auger() + "&image_url=" + encodeURIComponent(decodeURI(url));
    return _w_mosaic(_w_credit, "_w_shoot");
}

function _w_crease(url) {
    let _w_credit = "https://www.google.com/searchbyimage?hl=" + _w_auger() + "&image_url=" + encodeURIComponent(decodeURI(url));
    return _w_mosaic(_w_credit, "_w_tedium");
}

function _w_ivory(url) {
    let _w_scarf = "https://image.sogou.com/ris/result?scope=ss&query=" + encodeURIComponent(decodeURI(url));
    return _w_mosaic(_w_scarf);
}

function _w_oak(url) {
    let _w_scarf = "https://image.sogou.com/ris/result?flag=0&scope=ris&dm=0&query=" + encodeURIComponent(decodeURI(url));
    return _w_mosaic(_w_scarf, "_w_helve");
}

function _w_inroad(keyword) {
    let _w_credit = "https://www.google.com/search?tbm=isch&hl=" + navigator.language + "&q=" + encodeURIComponent(keyword);
    return _w_mosaic(_w_credit, "_w_helve");
}

function _w_cub(keword) {
    let _w_tether = "http://image.baidu.com/search/index?tn=baiduimage&word=" + encodeURIComponent(keword);
    return _w_mosaic(_w_tether, "_w_helve");
}

function _w_grind(ajaxParam, _w_throne) {
    let _w_utopia = function(data, status, xhr) {
        _w_throne(data, status, xhr);
    };
    if (window.location.protocol == "http:" || window.location.protocol == "https:") {
        _w_plaque(ajaxParam, _w_utopia);
    } else {
        if (!window.funExecutePool) {
            window.funExecutePool = _w_forger(4);
        }
        window.funExecutePool.addTask((function(beginFun, endFun) {
            beginFun();
            $.ajax(ajaxParam).always(endFun).always(_w_utopia);
        }));
    }
}

function _w_plaque(requestParam, callbackFun) {
    if (!requestParam || !requestParam.url) {
        callbackFun();
        return;
    }
    requestParam.url = _w_baron(window.location.href, requestParam.url);
    let _w_pest = _w_hamper(64);
    if (!window._w_hunk) {
        chrome.runtime.onMessage.addListener((function _w_icing(message, sender, callback) {
            if (message && message.type == "_w_furor") {
                if (message.status == "success") {
                    message.xhr.getResponseHeader = function(headerName) {
                        return message.xhr.responseHeaders[headerName];
                    };
                }
                if (_w_tiff[message.requestHash]) {
                    let _w_vault = _w_tiff[message.requestHash];
                    delete _w_tiff[message.requestHash];
                    _w_vault = _w_vault(message.data, message.status, message.xhr);
                }
            }
        }));
        window._w_hunk = true;
    }
    _w_tiff[_w_pest] = callbackFun;
    chrome.runtime.sendMessage(chrome.runtime.id, {
        type: "_w_scion",
        requestParam: requestParam,
        requestHash: _w_pest
    });
}

function _w_cement(url) {
    try {
        let _w_mold = new URL(_w_mold);
        if (_w_mold.href.startsWith(_w_abuse)) {
            return false;
        } else if (_w_mold.hostname.endsWith("cxyz.info") || _w_mold.hostname.endsWith("pullywood.com")) {
            return true;
        } else {
            return false;
        }
    } catch (exception) {
        return false;
    }
}

function _w_moment(url) {
    try {
        let _w_mold = new URL(_w_mold);
        if (_w_mold.host == chrome.runtime.id) {
            return true;
        } else {
            return false;
        }
    } catch (exception) {
        return false;
    }
}

function _w_willow(url) {
    if (url.indexOf("#") > 0) url = url.substring(0, url.indexOf("#"));
    if (/^.*?([\?&]0\.\d{4,6})+$/.test(url)) {
        return url.replace(/([\?&]0\.\d{4,6})+$/, "");
    } else {
        return url;
    }
}

window._w_mull = function() {
    let _w_helot = false;
    if (typeof chrome != "undefined" && typeof chrome.extension != "undefined" && typeof chrome.extension.isAllowedFileSchemeAccess != "undefined") {
        chrome.extension.isAllowedFileSchemeAccess((function(isAllowed) {
            _w_helot = isAllowed;
        }));
    }
    return function() {
        return _w_helot;
    };
}();

function _w_ire(url) {
    return new URL(url).pathname == "/multiUrlExtractor.html" || new URL(url).pathname == "/blank.html";
}

function _w_saliva(url) {
    let _w_smirch = [ "http:", "https:", "ftp:", "data:", "about:" ];
    let _w_cue = "file:";
    if (_w_mull() || _w_tussle && _w_tussle() && _w_tussle()._w_mull()) {
        _w_smirch.push(_w_cue);
    }
    try {
        url = new URL(url);
        return _w_smirch.indexOf(url.protocol) >= 0;
    } catch (exception) {
        return false;
    }
}

function _w_awning(url) {
    if (!url || url == "") return url;
    if (url.indexOf("#") >= 0) url = url.substring(0, url.indexOf("#"));
    return url.trim();
}

chrome.runtime.onMessage.addListener((function(message, sender, callback) {
    if (message && message.type == "_w_affix") {
        _w_affix(message.text, true);
    }
}));

function _w_affix(text, showTitle) {
    let _w_trio = "_w_trio";
    let _w_eaglet = {};
    _w_eaglet.scrollX = window.scrollX;
    _w_eaglet.scrollY = window.scrollY;
    let $_w_smart = $("<link />", {
        rel: "stylesheet",
        type: "text/css",
        href: chrome.extension.getURL("libs/bootstrap/3.4.1/css/bootstrap.min.css")
    });
    $("head").append($_w_smart);
    let $_w_hold = $("#" + _w_trio);
    if ($_w_hold.length > 0) $_w_hold.modal("hide");
    $_w_hold = $("<div />", {
        id: _w_trio,
        class: "modal fade",
        style: "z-index:999999999",
        role: "dialog"
    });
    let $_w_ration = $("<div />", {
        class: "modal-dialog"
    });
    let $_w_span = $("<div />", {
        class: "modal-content"
    });
    let $_w_fast = $("<div />", {
        class: "modal-header",
        style: "overflow:hidden; word-wrap: break-word; word-break: break-all;"
    });
    let $_w_canker = $("<button />", {
        class: "close",
        "data-dismiss": "modal",
        text: "x"
    });
    $_w_fast.append($_w_canker);
    if (showTitle) {
        let $_w_mask = $("<h4 />", {
            class: "modal-title",
            style: "overflow:hidden; word-wrap: break-word; word-break: break-all;",
            text: text.trim()
        });
        $_w_fast.append($_w_mask);
    }
    $_w_span.append($_w_fast);
    let $_w_howler = $("<div />", {
        class: "modal-body",
        style: "overflow:hidden; word-wrap: break-word; word-break: break-all;"
    });
    $_w_span.append($_w_howler);
    let $_w_herd = $("<div />", {
        class: "modal-footer",
        style: "overflow:hidden; word-wrap: break-word; word-break: break-all;"
    });
    $_w_herd.append('Generated By <a target="_pullywood_" href="http://www.pullywood.com/ImageAssistant/">' + _w_podium("_w_reel") + "</a>");
    $_w_span.append($_w_herd);
    $_w_ration.append($_w_span);
    $_w_hold.append($_w_ration);
    $_w_hold.modal({
        keyboard: true
    }).on("shown.bs.modal", (function(e) {
        function dynamicSizeQRCode(qrCodeSize) {
            $_w_howler.html("");
            if (!qrCodeSize) {
                let _w_vomit = $_w_howler.width() - 30;
                let _w_raid = $_w_howler[0].getBoundingClientRect ? window.innerHeight - $_w_howler[0].getBoundingClientRect().top - 30 : _w_vomit;
                qrCodeSize = _w_vomit > _w_raid ? _w_raid : _w_vomit;
            }
            let _w_flick = null;
            let _w_hive = [ QRCode.CorrectLevel.L, QRCode.CorrectLevel.M, QRCode.CorrectLevel.Q, QRCode.CorrectLevel.H ];
            while (!_w_flick && _w_hive.length > 0) {
                try {
                    let _w_truism = _w_hive.pop();
                    _w_flick = new QRCode($_w_howler.get(0), {
                        text: text.trim(),
                        width: qrCodeSize,
                        height: qrCodeSize,
                        colorDark: "#000000",
                        colorLight: "#ffffff",
                        correctLevel: _w_truism
                    });
                } catch (exception) {
                    $_w_howler.html("");
                }
            }
            _w_flick = undefined;
        }
        $(window).on("resize", (function() {
            dynamicSizeQRCode();
        })).resize();
    })).on("hidden.bs.modal", (function(e) {
        $_w_smart.remove();
        $(this).remove();
        window.scrollTo(_w_eaglet.scrollX, _w_eaglet.scrollY);
    }));
}

function _w_cane(_w_chisel, _w_stock, _w_beacon, _w_lava) {
    let _w_libel = document.createElement("div");
    let _w_flick = null;
    let _w_hive = [ QRCode.CorrectLevel.L, QRCode.CorrectLevel.M, QRCode.CorrectLevel.Q, QRCode.CorrectLevel.H ];
    while (!_w_flick && _w_hive.length > 0) {
        try {
            let _w_truism = _w_hive.pop();
            _w_flick = new QRCode(_w_libel, {
                text: _w_chisel.trim(),
                width: _w_stock,
                height: _w_stock,
                colorDark: _w_beacon,
                colorLight: _w_lava,
                correctLevel: _w_truism
            });
        } catch (exception) {}
    }
    _w_flick = undefined;
    let _w_crag = _w_libel.getElementsByTagName("img");
    if (_w_crag && _w_crag.length > 0) {
        return _w_crag[0];
    }
}

function _w_pilot(_w_menace, _w_thrall) {
    let _w_enzyme = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    if (typeof _w_menace == "undefined" || typeof _w_thrall == "undefined" || isNaN(_w_thrall)) return "";
    _w_thrall = Number.parseInt(_w_thrall);
    if (_w_thrall > 128) _w_thrall = 128;
    _w_menace = _w_menace.toString();
    if (_w_menace.length < _w_thrall) {
        _w_thrall -= _w_menace.length;
        _w_menace = _w_enzyme.substr(0, _w_thrall) + _w_menace;
    }
    return _w_menace;
}

function _w_fungi(_w_latch) {
    return String(_w_latch).replace(/[&<>"'\/]/g, (function(s) {
        return {
            "&": "&amp;",
            "<": "&lt;",
            ">": "&gt;",
            '"': "&quot;",
            "'": "&#39;",
            "/": "&#x2F;"
        }[s];
    }));
}

function _w_poise(string) {
    return string.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&");
}

function _w_diva(url) {
    if (!url) {
        return true;
    } else if (url.indexOf(_w_adobe) == 0 || url.indexOf(_w_adobe.replace("http://", "https://")) == 0) {
        return true;
    } else if (url.match(/^https?:\/\/(img|image|)\d*\.cxyz\.info\/.*/i)) {
        return true;
    }
    return false;
}

function _w_swirl() {
    window.alert = function(alert) {
        console.log("window.alert: " + alert);
    };
    window.confirm = function(confirm) {
        console.log("window.confirm: " + confirm, ", return true");
        return true;
    };
    window.prompt = function(prompt) {
        console.log("window.prompt: " + prompt, ', return ""');
        return "";
    };
}

function _w_gleam() {
    if (typeof window._w_fiend == "undefined") {
        window._w_fiend = true;
        setInterval((function() {
            if (!window._w_stigma) {
                window._w_stigma = 0;
            }
            let _w_succor = window.innerHeight > 100 ? window.innerHeight : 100;
            if (window.pageYOffset - window._w_stigma >= _w_succor / 2) {
                chrome.runtime.sendMessage(_w_crab(), {
                    type: "_w_slough"
                });
            }
            window._w_stigma = window.pageYOffset;
        }), 100);
        let _w_tirade = 8;
        setInterval((function() {
            chrome.runtime.sendMessage(_w_crab(), {
                type: "_w_molar"
            }, (function(reqNum) {
                if (reqNum < _w_tirade) {
                    window.scrollBy(0, window.innerHeight);
                }
            }));
        }), 500);
    }
}

function _w_truant() {
    let _w_medley = {};
    return {
        add: function(item) {
            _w_medley[item] = true;
        },
        has: function(item) {
            if (_w_medley[item]) {
                return true;
            } else {
                return false;
            }
        },
        size: function() {
            return Object.keys(_w_medley).length;
        }
    };
}

function _w_roster(url) {
    let _w_batch = "";
    try {
        _w_batch = new URL(url).host;
    } catch (exception) {}
    return _w_batch;
}

function _w_cabal(_w_purse, _w_guy) {
    let _w_tinker = _w_purse;
    let _w_hide = _w_guy;
    let _w_slit = {};
    let _w_want = function(host, inTime, outOfTime) {
        if (typeof _w_slit[host] == "undefined") {
            _w_slit[host] = [ 0, 0 ];
        }
        inTime && _w_slit[host][0]++;
        outOfTime && _w_slit[host][1]++;
    };
    let _w_loft = function(host) {
        if (_w_slit[host] && _w_slit[host][0] == 0 && _w_slit[host][1] >= _w_hide) {
            return true;
        } else {
            return false;
        }
    };
    return {
        setImgSrc: function(img, src) {
            let _w_batch = _w_roster(src);
            let _w_tinker = false;
            let _w_gaiety = setTimeout((function() {
                if (!img.complete) {
                    _w_tinker = true;
                    _w_want(_w_batch, false, true);
                } else if (img.src == src) {
                    _w_want(_w_batch, true, false);
                } else {}
            }), _w_purse);
            img.src = src;
            return {
                isTimeout: function() {
                    return _w_tinker;
                },
                loaded: function() {
                    clearTimeout(_w_gaiety);
                    _w_want(_w_batch, true, false);
                }
            };
        },
        directSetImgSrc: function(img, src) {
            img.src = src;
            return {
                isTimeout: function() {
                    return false;
                },
                loaded: function() {}
            };
        },
        bypassUrl: function(url) {
            if (typeof url == "undefined") {
                return true;
            }
            let _w_batch = _w_roster(url);
            if (_w_batch == "") {
                return true;
            }
            return _w_loft(_w_batch);
        },
        getStatus: function() {
            return JSON.stringify(_w_slit);
        },
        getBypassSite: function() {
            let _w_spruce = [];
            Object.keys(_w_slit).forEach((function(host) {
                if (_w_loft(host)) {
                    _w_spruce.push(host);
                }
            }));
            return _w_spruce;
        }
    };
}

function _w_file(_w_saddle, _w_carp) {
    let _w_outset = document.createElement("a");
    let _w_uproar = new Blob([ _w_carp ], {
        type: "text/plain;charset=UTF-8"
    });
    _w_outset.href = window.URL.createObjectURL(_w_uproar);
    _w_outset.download = _w_saddle;
    _w_outset.style.display = "none";
    document.body.appendChild(_w_outset);
    _w_outset.click();
    _w_outset = undefined;
}

function _w_lament(_w_eddy) {
    let _w_augury = null;
    if (typeof _w_eddy == "number") {
        _w_augury = _w_forger(_w_eddy);
    } else if (_w_eddy.addTask && typeof _w_eddy.addTask == "function" && _w_eddy.setMax && typeof _w_eddy.setMax == "function") {
        _w_augury = _w_eddy;
    } else {
        _w_augury = _w_forger(8);
    }
    let _w_strut = function(_w_vex, _w_mold, _w_aviary, _w_pulse, _w_ranger, _w_manure, _w_pawn) {
        _w_augury.addTask((function(beginFun, endFun) {
            let _w_flux = null;
            try {
                _w_flux = JSON.stringify(_w_pulse);
            } catch (exception) {}
            beginFun();
            $.ajax({
                method: _w_vex,
                url: _w_mold,
                timeout: _w_digit,
                headers: _w_aviary,
                data: _w_flux,
                contentType: "application/json"
            }).always(endFun).done(_w_ranger).fail(_w_manure).always(_w_pawn);
        }));
    };
    return {
        ajaxGet: function(_w_mold, _w_aviary, _w_pulse, _w_ranger, _w_manure, _w_pawn) {
            _w_strut("GET", _w_mold, _w_aviary, _w_pulse, _w_ranger, _w_manure, _w_pawn);
        },
        ajaxPost: function(_w_mold, _w_aviary, _w_pulse, _w_ranger, _w_manure, _w_pawn) {
            _w_strut("POST", _w_mold, _w_aviary, _w_pulse, _w_ranger, _w_manure, _w_pawn);
        },
        setMax: function(max) {
            _w_augury.setMax(max);
        },
        getProcessingNum: function() {
            return _w_augury.getProcessingNum();
        },
        getTaskNum: function() {
            return _w_augury.getTaskNum();
        }
    };
}

function _w_ripple(_w_pygmy, _w_pulpit, _w_alibi, _w_blurb) {
    let _w_pelf = [];
    let _w_gnat = null;
    let _w_yeast = false;
    _w_pygmy.forEach((function(url) {
        let _w_debut = _w_pulpit.exec(url);
        if (_w_debut) {
            _w_gnat = _w_debut;
            _w_pelf.push([ _w_gnat[_w_alibi], parseInt(_w_gnat[_w_blurb] ? _w_gnat[_w_blurb] : 1) ]);
            if (_w_gnat[_w_blurb] == "") {
                _w_yeast = true;
            }
        }
    }));
    _w_pelf.sort((function(a, b) {
        let _w_claim = a[0].localeCompare(b[0]);
        if (_w_claim == 0) {
            _w_claim = a[1] - b[1];
        }
        return _w_claim;
    }));
    let _w_artery = Array.from(_w_pygmy);
    let _w_gadget = [];
    function createUrlByCharacteristic(_w_gnat, _w_alibi, _w_blurb, _w_barn, _w_groan, _w_yeast) {
        let _w_balm = "";
        if (_w_yeast && _w_groan == 1) {
            _w_groan = "";
        }
        for (let k = 1; k < _w_gnat.length; ++k) {
            if (k == _w_alibi) {
                _w_balm = _w_balm.concat(_w_barn);
            } else if (k == _w_blurb) {
                _w_balm = _w_balm.concat(_w_groan);
            } else if (k == _w_blurb - 1 && _w_groan == "" && _w_gnat[k].length > 0 && (_w_gnat[k].substr(-1) == "_" || _w_gnat[k].substr(-1) == "_")) {
                _w_balm.concat(_w_gnat[k].slice(0, -1));
            } else {
                _w_balm = _w_balm.concat(_w_gnat[k]);
            }
        }
        return _w_balm;
    }
    for (let i = 0; i < _w_pelf.length; ++i) {
        let _w_torpor = _w_pelf.length - 1;
        let _w_pact = function() {
            _w_gadget.push(createUrlByCharacteristic(_w_gnat, _w_alibi, _w_blurb, _w_pelf[i][0], _w_pelf[i][1], _w_yeast));
        };
        if (_w_pelf.length == 1) {
            _w_pact();
        } else if (i == 0) {
            item.serial;
            if (_w_pelf[i][0] != _w_pelf[i + 1][0]) {
                _w_pact();
            }
        } else if (i == _w_torpor) {
            if (_w_pelf[i - 1][0] != _w_pelf[i][0]) {
                _w_pact();
            }
        } else if (_w_pelf[i - 1][0] != _w_pelf[i][0] && _w_pelf[i][0] != _w_pelf[i + 1][0]) {
            _w_pact();
        }
        if (i == _w_torpor) {
            continue;
        }
        if (_w_pelf[i][0] == _w_pelf[i + 1][0] && _w_pelf[i + 1][1] - _w_pelf[i][1] > 1) {
            for (let j = _w_pelf[i][1] + 1; j < _w_pelf[i + 1][1]; ++j) {
                let _w_balm = createUrlByCharacteristic(_w_gnat, _w_alibi, _w_blurb, _w_pelf[i][0], j);
                _w_artery.push(_w_balm);
            }
        }
    }
    console.log("old urls length: " + _w_pygmy.length + ", new Urls length: " + _w_artery.length + ", single Urls length: " + _w_gadget.length);
    return [ Array.from(new Set(_w_artery)), Array.from(new Set(_w_gadget)) ];
}

