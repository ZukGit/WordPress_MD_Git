/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

function _w_kidney(_w_intent) {
    let _w_fresco = [];
    for (let idx in _w_intent) {
        let _w_mold = _w_intent[idx];
        if ("string" != typeof _w_mold || !_w_mold.startsWith("http")) continue;
        let _w_opiate = _w_mold.split(/([0-9]+)/);
        _w_fresco.push(_w_opiate);
    }
    return _w_fresco;
}

function _w_scope(_w_intent) {
    let _w_fresco = [];
    for (let idx in _w_intent) {
        let _w_mold = _w_intent[idx];
        if ("string" != typeof _w_mold || !_w_mold.startsWith("http")) continue;
        let _w_opiate = _w_mold.split(/([^0-9a-zA-Z%]+|\d+(?=[\/_\?#]))/);
        let _w_helmet = _w_mold.split(/([^0-9a-zA-Z%\-_]+)/);
        _w_fresco.push(_w_opiate);
        if (JSON.stringify(_w_opiate) != JSON.stringify(_w_helmet)) {
            _w_fresco.push(_w_helmet);
        }
    }
    return _w_fresco;
}

function _w_pelt(_w_slaver, _w_peep, _w_tonic) {
    let _w_trivia = document.links;
    let _w_intent = [];
    let _w_ogle = {};
    let _w_gag = window.location.href;
    if (_w_gag.indexOf("#") > 0) _w_gag = _w_gag.substring(0, _w_gag.indexOf("#"));
    for (let idx in _w_trivia) {
        let _w_cleft = _w_trivia[idx];
        if (_w_cleft.protocol && (_w_cleft.protocol == "http:" || _w_cleft.protocol == "https:")) {
            let _w_mote = _w_cleft.href.trim();
            if (_w_mote.indexOf("#") > 0) _w_mote = _w_mote.substring(0, _w_mote.indexOf("#"));
            if (_w_intent.indexOf(_w_mote) < 0) {
                _w_intent.push(_w_mote);
                _w_ogle[_w_mote] = _w_cleft;
            } else if (_w_cleft.text.trim().length > _w_ogle[_w_mote].text.trim().length) {
                _w_ogle[_w_mote] = _w_cleft;
            }
        }
    }
    if (_w_intent.indexOf(_w_gag) < 0) {
        _w_intent.push(_w_gag);
    }
    _w_ogle[_w_gag] = $("<a />", {
        href: _w_gag,
        title: document.title,
        text: document.title
    })[0];
    let _w_fresco = _w_slaver(_w_intent);
    let _w_cipher = {};
    for (let i = 0; i < _w_fresco.length; ++i) {
        for (let j = i + 1; j < _w_fresco.length; ++j) {
            let _w_stem = _w_fresco[i];
            let _w_dismay = _w_fresco[j];
            if (_w_stem.length != _w_dismay.length) continue;
            let _w_pony = 0;
            let _w_breach = _w_stem.length;
            let _w_mast = "";
            let _w_lust = "";
            let _w_tissue = "";
            let _w_alkali = 0;
            let _w_glow = [];
            for (let cnt = 0; cnt < _w_breach; ++cnt) {
                if (_w_stem[cnt] != _w_dismay[cnt]) {
                    if (_w_pony != 0) {
                        _w_pony = Number.MAX_SAFE_INTEGER;
                        break;
                    }
                    _w_lust = _w_mast;
                    _w_mast = "";
                    _w_alkali = cnt;
                    _w_glow.push(_w_stem[cnt]);
                    _w_glow.push(_w_dismay[cnt]);
                    _w_pony++;
                } else {
                    _w_mast += _w_stem[cnt];
                }
            }
            _w_tissue = _w_mast;
            _w_mast = _w_lust + "*" + _w_tissue;
            if (_w_pony != 1) {
                continue;
            }
            let _w_clamor = function(a, b) {
                return a + b;
            };
            let _w_scrap = [ _w_stem.reduce(_w_clamor), _w_dismay.reduce(_w_clamor) ];
            let _w_libel = _w_cipher[_w_mast];
            if (_w_libel == null) {
                _w_libel = {};
                _w_libel["joinedStrBeforeDiff"] = _w_lust;
                _w_libel["joinedStrAfterDiff"] = _w_tissue;
                _w_libel["joinedStrPattern"] = _w_mast;
                _w_libel["diffIndex"] = _w_alkali;
                _w_libel["diffIsNumeric"] = true;
                _w_libel["diffBreakCount"] = 0;
                _w_libel["diffList"] = _w_glow;
                _w_libel["urlList"] = _w_scrap;
                _w_libel["containCurrentPageUrl"] = false;
                _w_libel["currentPageUrl"] = _w_gag;
                _w_cipher[_w_mast] = _w_libel;
            } else {
                for (let idx in _w_glow) {
                    if (_w_libel["diffList"].indexOf(_w_glow[idx]) < 0) {
                        _w_libel["diffList"].push(_w_glow[idx]);
                    }
                }
                for (let idx in _w_scrap) {
                    if (_w_libel["urlList"].indexOf(_w_scrap[idx]) < 0) {
                        _w_libel["urlList"].push(_w_scrap[idx]);
                    }
                }
            }
            if (_w_gag == _w_scrap[0] || _w_gag == _w_scrap[1]) {
                _w_libel["containCurrentPageUrl"] = true;
            }
        }
    }
    let _w_toupee = [];
    for (let pattern in _w_cipher) {
        _w_toupee.push(_w_cipher[pattern]);
    }
    for (let idx in _w_toupee) {
        let _w_apogee = _w_toupee[idx];
        _w_apogee["diffList"].sort((function(a, b) {
            if (jQuery.isNumeric(a) && jQuery.isNumeric(b)) {
                return parseInt(a) - parseInt(b);
            } else {
                return a - b;
            }
        }));
        let _w_tack = 0;
        let _w_screw = 5;
        let _w_glow = _w_apogee["diffList"];
        for (let idx = 0; idx < _w_glow.length - 1; ++idx) {
            if (idx == 0 && _w_glow[idx] == "") {
                continue;
            } else if (!jQuery.isNumeric(_w_glow[idx]) || !jQuery.isNumeric(_w_glow[idx + 1])) {
                _w_tack = Number.MAX_VALUE;
                _w_apogee["diffIsNumeric"] = false;
            } else if (_w_glow[idx + 1] - _w_glow[idx] > 1) {
                _w_tack += 1;
            }
        }
        _w_apogee["diffBreakCount"] = _w_tack;
        if (_w_tack > _w_screw) {
            _w_apogee["fillList"] = _w_apogee["diffList"];
        } else if (_w_glow[_w_glow.length - 1] - _w_glow.length > 1024) {
            _w_apogee["fillList"] = _w_apogee["diffList"];
        } else {
            let _w_flare = _w_glow[0].toString()[0] == "0" && _w_glow[0].toString().length > 1 ? _w_glow[0].toString().length : 1;
            _w_apogee["fillList"] = [];
            for (let i = 0; i <= _w_glow[_w_glow.length - 1]; ++i) {
                _w_apogee["fillList"].push(_w_pilot(i, _w_flare));
            }
        }
        let _w_lust = _w_apogee["joinedStrBeforeDiff"];
        let _w_tissue = _w_apogee["joinedStrAfterDiff"];
        _w_apogee["urlList"] = [];
        _w_apogee["urlText"] = {};
        _w_glow = _w_apogee["diffList"];
        for (let itemIdx in _w_glow) {
            let _w_marvel = _w_glow[itemIdx];
            let _w_mold = _w_lust + _w_marvel + _w_tissue;
            let _w_nick = _w_ogle[_w_mold] ? _w_ogle[_w_mold].text : "无文字链接[" + _w_marvel + "]";
            _w_nick = _w_nick.trim().length > 0 ? _w_nick.trim() : _w_mold;
            _w_apogee["urlList"].push(_w_mold);
            _w_apogee["urlText"][_w_mold] = _w_nick;
        }
        _w_apogee["fillUrlList"] = [];
        _w_apogee["fillUrlText"] = {};
        let _w_quiver = _w_apogee["fillList"];
        let _w_hyphen = false;
        for (let itemIdx in _w_quiver) {
            let _w_spate = _w_quiver[itemIdx];
            let _w_mold = _w_lust + _w_spate + _w_tissue;
            let _w_nick = _w_ogle[_w_mold] ? _w_ogle[_w_mold].text : "智能填充链接[" + _w_spate + "]";
            _w_nick = _w_nick.trim().length > 0 ? _w_nick.trim() : _w_mold;
            let _w_cape = _w_lust.substr(-1);
            if (!_w_hyphen && (_w_cape == "-" || _w_cape == "_" || _w_cape == "/") && (_w_tissue.length == 0 || _w_tissue[0] == ".") && (_w_spate == 0 || _w_spate == 1)) {
                let _w_curd;
                if (_w_cape == "/") {
                    _w_curd = _w_lust;
                } else {
                    _w_curd = _w_lust.slice(0, -1) + _w_tissue;
                }
                let _w_fawn = _w_ogle[_w_curd] ? _w_ogle[_w_curd].text : "智能填充链接[ ]";
                _w_apogee["fillUrlList"].push(_w_curd);
                _w_apogee["fillUrlText"][_w_curd] = _w_fawn;
                _w_hyphen = true;
                if (_w_apogee["currentPageUrl"] == _w_curd) {
                    _w_apogee["containCurrentPageUrl"] = true;
                    _w_apogee["urlList"].push(_w_curd);
                    _w_apogee["urlText"][_w_curd] = _w_fawn;
                }
            }
            _w_apogee["fillUrlList"].push(_w_mold);
            _w_apogee["fillUrlText"][_w_mold] = _w_nick;
        }
    }
    _w_toupee.sort((function(a, b) {
        let _w_claim = 0;
        if (a["containCurrentPageUrl"] && !b["containCurrentPageUrl"]) {
            _w_claim = -1;
        } else if (!a["containCurrentPageUrl"] && b["containCurrentPageUrl"]) {
            _w_claim = 1;
        }
        if (_w_claim == 0) {
            if (a["diffIsNumeric"] && !b["diffIsNumeric"]) {
                _w_claim = -1;
            } else if (!a["diffIsNumeric"] && b["diffIsNumeric"]) {
                _w_claim = 1;
            }
        }
        if (_w_claim == 0) {
            _w_claim = a["diffBreakCount"] - b["diffBreakCount"];
        }
        if (_w_claim == 0) {
            if (a["diffList"] && b["diffList"]) {
                _w_claim = b["diffList"].length - a["diffList"].length;
            } else {
                _w_claim = a - b;
            }
        }
        if (_w_claim == 0 && a.diffIndex != null && b.diffIndex != null) {
            _w_claim = a.diffIndex - b.diffIndex;
        }
        return _w_claim;
    }));
    let _w_chore = {};
    Object.entries(_w_ogle).forEach(arr => {
        _w_chore[arr[0]] = arr[1].text;
    });
    let _w_buggy = function() {
        chrome.runtime.sendMessage(chrome.runtime.id, {
            type: "_w_patina",
            collections: _w_toupee,
            links: _w_chore,
            currentPageUrl: _w_gag,
            currentPageTitle: document.title,
            channel: _w_peep
        });
    };
    setTimeout(() => {
        _w_buggy();
        _w_tonic && _w_cant();
    }, 500);
}

