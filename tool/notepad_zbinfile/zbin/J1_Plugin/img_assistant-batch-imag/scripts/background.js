/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

window._w_idol = [ "zh_CN", "zh", "en_US", "en" ];

window._w_core = "";

window._w_demise = [];

window._w_fosse = null;

window._w_list = {};

window._w_lash = {};

window._w_hymn = 1;

window._w_gash = 1e11;

window._w_frolic = _w_forger(4);

window._w_wig = _w_lament(1);

window._w_wig.ajaxGet(_w_bulk, null, null, (function(data) {
    localStorage["_pullywood_RegexpUrlRule"] = data;
    _w_welter();
}), null, null);

window._w_wig.ajaxGet("/defaultRegexpUrlRule.properties", null, null, (function(data) {
    window._w_core = data;
    _w_welter();
}), null, null);

chrome.i18n.getAcceptLanguages((function(data) {
    window._w_idol = data;
}));

chrome.runtime.onInstalled.addListener((function(details) {
    if (details.reason == "install") {
        chrome.tabs.create({
            url: _w_poll
        });
    } else if (details.reason == "update") {}
}));

function _w_salute() {
    return window._w_idol;
}

function _w_crust(_w_bower) {
    if (typeof _w_bower == "undefined") console.trace("tabId is undefined.");
    window._w_lash[_w_bower] = {
        tabId: _w_bower
    };
    window._w_lash[_w_bower]._w_harp = [];
    window._w_lash[_w_bower]._w_cygnet = {};
    window._w_lash[_w_bower]._w_slosh = {};
    window._w_lash[_w_bower]._w_nerve = {};
    window._w_lash[_w_bower].extractorHash = _w_bulge(32);
    window._w_lash[_w_bower].url = window._w_list[_w_bower] ? window._w_list[_w_bower].url : "";
    let _w_egoism = moment();
    window._w_lash[_w_bower].timeStamp = _w_egoism;
    window._w_lash[_w_bower].timeStamp.YYYYMMDD = _w_egoism.format("YYYY-MM-DD");
    window._w_lash[_w_bower].timeStamp.HHmmss = _w_egoism.format("HH-mm-ss");
}

function _w_hike(_w_bower) {
    window._w_list[_w_bower] && !window._w_lash[_w_bower] && _w_crust(_w_bower);
    return window._w_lash[_w_bower];
}

function _w_plot(_w_bower, _w_cult) {
    let _w_groove = window._w_lash[_w_bower];
    if (_w_groove) {
        _w_groove["extractorHash_2"] = _w_cult;
        let _w_cache = _w_qualm(_w_bower);
        let _w_splint = {};
        _w_cache.forEach(item => _w_splint[item] = _w_cache[item]);
        _w_petal(_w_splint, _w_cult);
    }
}

function _w_qualm(_w_bower) {
    let _w_splint = null;
    if (_w_hike(_w_bower)) {
        _w_splint = window._w_lash[_w_bower]._w_harp;
    }
    return _w_splint;
}

function _w_writ(_w_rave) {
    for (let _w_bower in window._w_lash) {
        if (window._w_lash[_w_bower].extractorHash == _w_rave) {
            return window._w_lash[_w_bower];
        }
    }
    return null;
}

function _w_bedlam(_w_rave) {
    let _w_splint = null;
    let _w_moat = _w_writ(_w_rave);
    if (_w_moat) _w_splint = _w_moat._w_harp;
    return _w_splint;
}

function _w_pleat(_w_bower) {
    let _w_tare = null;
    if (_w_hike(_w_bower)) {
        _w_tare = window._w_lash[_w_bower]._w_nerve;
    }
    return _w_tare;
}

function _w_rigor(_w_rave) {
    let _w_tare = null;
    let _w_moat = _w_writ(_w_rave);
    if (_w_moat) _w_tare = _w_moat._w_nerve;
    return _w_tare;
}

function _w_coven(_w_bower) {
    let _w_heed = null;
    if (_w_hike(_w_bower)) {
        _w_heed = window._w_lash[_w_bower]._w_cygnet;
    }
    return _w_heed;
}

function _w_stench(_w_bower) {
    let _w_usury = null;
    if (_w_hike(_w_bower)) {
        _w_usury = window._w_lash[_w_bower]._w_slosh;
    }
    return _w_usury;
}

function _w_pundit(_w_bower) {
    let _w_duel = null;
    if (_w_hike(_w_bower)) {
        _w_duel = window._w_lash[_w_bower].extractorHash;
    }
    return _w_duel;
}

function _w_clip(_w_bower) {
    let _w_duel = null;
    if (_w_hike(_w_bower)) {
        _w_duel = window._w_lash[_w_bower].extractorHash_2;
    }
    return _w_duel;
}

function _w_rivet(_w_rave) {
    let _w_bower = null;
    let _w_moat = _w_writ(_w_rave);
    if (_w_moat) _w_bower = _w_moat.tabId;
    return _w_bower;
}

window._w_gust = function() {
    let _w_lull = {};
    window._w_lull = _w_lull;
    let _w_litter = {};
    window._w_litter = _w_litter;
    let _w_facet = {
        urls: [ "<all_urls>" ]
    };
    let _w_timber = function(details) {
        let _w_spunk = function(error, statusCode) {
            let _w_quarry = _w_lull[details.requestId] ? parseInt(details.timeStamp - _w_lull[details.requestId].timeStamp) : -1;
            let _w_piazza = _w_litter[details.tabId];
            if (_w_piazza) {
                let _w_vessel = _w_piazza["extractorTabId"];
                let _w_helm = _w_piazza["tabId"];
                let _w_snatch = _w_coven(_w_vessel);
                let _w_awe = _w_coven(_w_helm);
                let _w_mantle = _w_snatch && _w_snatch[details.url] ? _w_snatch[details.url] : _w_awe && _w_awe[details.url] ? _w_awe[details.url] : null;
                if (typeof _w_piazza["lastRequest"] != "undefined") {
                    _w_piazza["lastRequest"] = (new Date).getTime();
                }
                if (_w_piazza["requestLog"]) {
                    _w_piazza["requestLog"][details.url] = {
                        referer: _w_mantle,
                        error: error,
                        statusCode: statusCode,
                        timeCost: _w_quarry
                    };
                }
            }
            delete _w_lull[details.requestId];
        };
        if (details.error) {
            _w_spunk(details.error, null);
        } else if (details.statusCode) {
            _w_spunk(null, details.statusCode);
        } else {
            if (!/^https?:\/\/.*$/gi.test(details.url)) return;
            _w_lull[details.requestId] = {
                requestId: details.requestId,
                timeStamp: details.timeStamp,
                tabId: details.tabId,
                url: details.url,
                type: details.type
            };
        }
    };
    chrome.webRequest.onBeforeRequest.addListener(_w_timber, _w_facet, []);
    chrome.webRequest.onCompleted.addListener(_w_timber, _w_facet);
    chrome.webRequest.onErrorOccurred.addListener(_w_timber, _w_facet);
    let _w_farrow = function(tabIds) {
        if (Number.isInteger(tabIds)) {
            tabIds = [ tabIds ];
        }
        if (Array.isArray(tabIds)) {
            return Object.values(_w_lull).map((function(item) {
                return item.tabId;
            })).filter((function(item) {
                return tabIds.indexOf(item) >= 0;
            })).length;
        } else {
            Object.keys(_w_lull).length;
        }
    };
    let _w_critic = function(tabId) {
        delete _w_litter[tabId];
    };
    let _w_fret = function(tabId) {
        let _w_piazza = _w_litter[tabId];
        if (_w_piazza) {
            if (typeof _w_piazza["lastPushImage"] != "undefined") {
                _w_piazza["lastPushImage"] = (new Date).getTime();
            }
        }
    };
    let _w_edict = function(tabId) {
        let _w_piazza = _w_litter[tabId];
        if (_w_piazza) {
            if (typeof _w_piazza["lastFullScroll"] != "undefined") {
                _w_piazza["lastFullScroll"] = (new Date).getTime();
            }
        }
    };
    let _w_blotch = function(tabId) {
        _w_critic(tabId);
        Object.keys(_w_lull).forEach((function(requestId) {
            if (_w_lull[requestId].tabId == tabId) {
                delete _w_lull[requestId];
            }
        }));
    };
    return {
        requestNum: _w_farrow,
        registerTab: function(tabId, item) {
            item["requestNum"] = function(tabIds) {
                if (typeof tabIds == "undefined") {
                    return _w_farrow([ tabId ]);
                } else {
                    return _w_farrow(tabIds);
                }
            };
            _w_litter[tabId] = item;
        },
        unRegisterTab: _w_critic,
        notifyPushImage: _w_fret,
        notifyFullScroll: _w_edict,
        notifyRemoveTab: _w_blotch
    };
}();

function _w_stern(tab) {
    if (chrome.runtime.lastError) return;
    window._w_list[tab.id] = tab;
    _w_hike(tab.id);
}

function _w_ensign(_w_bower, changeInfo, tab) {
    window._w_list[tab.id] = tab;
    _w_hike(tab.id);
    if (changeInfo.url) {
        let _w_balm = _w_tint(changeInfo.url);
        if (_w_balm != window._w_lash[_w_bower].url) {
            _w_crust(_w_bower);
            window._w_lash[_w_bower].url = _w_balm;
        }
    }
}

function _w_prig(_w_bower) {
    _w_gust.notifyRemoveTab(_w_bower);
    delete window._w_list[_w_bower];
    delete window._w_lash[_w_bower];
}

chrome.tabs.onCreated.addListener(_w_stern);

chrome.tabs.onUpdated.addListener(_w_ensign);

chrome.tabs.onRemoved.addListener(_w_prig);

(function() {
    function _w_dummy() {
        chrome.tabs.query({}, (function(results) {
            results.forEach((function(tab) {
                if (!window._w_list[tab.id]) {
                    window._w_list[tab.id] = tab;
                    _w_hike(tab.id);
                }
            }));
        }));
    }
    function _w_bust() {
        let _w_lumber = Object.keys(window._w_list);
        let _w_pollen = Object.keys(window._w_lash);
        for (let idx in _w_pollen) if (_w_lumber.indexOf(_w_pollen[idx]) < 0) _w_lumber.push(_w_pollen[idx]);
        _w_lumber.forEach((function(tabId) {
            tabId = parseInt(tabId);
            chrome.tabs.get(tabId, (function(tab) {
                if (chrome.runtime.lastError) {
                    _w_prig(tabId);
                }
            }));
        }));
    }
    setInterval(_w_dummy, 4e3);
    setInterval(_w_bust, 4e3);
    _w_asthma();
    _w_welter();
})();

function _w_asthma() {
    let _w_haunt = /Chrome\/([0-9]+)/.exec(navigator.userAgent);
    let _w_lace = _w_haunt ? parseInt(_w_haunt[1]) : -1;
    chrome.webRequest.onHeadersReceived.addListener((function(details) {
        if (details.tabId < 0) {
            return;
        }
        let _w_aviary = details.responseHeaders;
        for (let i = 0; i < _w_aviary.length; ++i) {
            _w_aviary[_w_aviary[i].name] = _w_aviary[i].value;
        }
        let _w_sandal = _w_aviary["Content-Type"];
        if (_w_sandal) _w_sandal = _w_sandal.toLowerCase();
        if (details.type && details.type == "image" || _w_sandal && _w_sandal.startsWith("image/")) {
            let _w_coward = {};
            let _w_blast = window._w_list[details.tabId];
            if ("undefined" == typeof curentTab) {
                chrome.tabs.get(details.tabId, _w_stern);
            } else {
                let _w_avowal = new URL(_w_blast.url);
                _w_coward.pageTitle = _w_blast.title;
                _w_coward.pageURL = _w_avowal.origin + _w_avowal.pathname + _w_avowal.search;
                _w_coward.pageDomain = _w_avowal.hostname;
                _w_coward.pageHash = _w_avowal.hash;
            }
            let _w_recall = new URL(details.url);
            _w_coward.url = _w_recall.origin + _w_recall.pathname + _w_recall.search;
            _w_coward.domain = _w_recall.hostname;
            _w_coward.contentType = details.type;
            _w_coward.size = null;
            _w_coward.resolution = null;
            _w_coward.filename = null;
            let _w_fealty = null;
            if (_w_aviary["Content-Length"]) {
                if (_w_aviary["Content-Length"] >= 1024 * 1024 * 1024) {
                    _w_fealty = (_w_aviary["Content-Length"] / 1024 / 1024 / 1024).toFixed(2) + "GB";
                } else if (_w_aviary["Content-Length"] >= 1024 * 1024) {
                    _w_fealty = (_w_aviary["Content-Length"] / 1024 / 1024).toFixed(2) + "MB";
                } else {
                    _w_fealty = (_w_aviary["Content-Length"] / 1024).toFixed(2) + "KB";
                }
                _w_coward.size = _w_fealty;
            }
            _w_coward.filename = _w_recall.pathname.substring(_w_recall.pathname.lastIndexOf("/") + 1);
            let _w_facade = {};
            let _w_tag = _w_coven(details.tabId);
            let _w_hoe;
            let _w_slosh = _w_stench(details.tabId);
            let _w_tire = details.url;
            if (_w_slosh) {
                while (_w_slosh[_w_tire] && _w_tire != _w_slosh[_w_tire]) {
                    _w_tire = _w_slosh[_w_tire];
                    if (_w_tire == details.url) {
                        break;
                    }
                }
            }
            _w_tag && (_w_hoe = _w_tag[_w_tire]);
            _w_facade[_w_tire] = {
                title: "",
                alt: "",
                serial: _w_gash++,
                referer: _w_hoe
            };
            let _w_vogue = _w_qualm(details.tabId);
            if (_w_vogue && !_w_vogue[_w_tire]) {
                let _w_hawser = _w_pundit(details.tabId);
                _w_petal(_w_facade, _w_hawser);
            }
        } else if (details.type && details.type == "media" || _w_sandal && _w_sandal.indexOf("video/") > -1 || _w_sandal && _w_sandal.indexOf("audio/") > -1) {}
    }), {
        urls: [ "<all_urls>" ]
    }, [ "blocking", "responseHeaders" ]);
    chrome.webRequest.onBeforeRedirect.addListener((function(details) {
        if (details.redirectUrl == details.url) return;
        if (!window._w_list[details.tabId]) {
            return;
        }
        let _w_quest = details.tabId;
        let _w_spike = window._w_list[_w_quest].url;
        if (_w_cement(_w_spike) || /^[a-z]+-extension:\/\//gi.test(_w_spike)) {
            let _w_cygnet = _w_coven(_w_quest);
            if (_w_cygnet && _w_cygnet[details.url] && !_w_cygnet[details.redirectUrl]) {
                _w_cygnet[details.redirectUrl] = _w_cygnet[details.url];
            }
        }
        let _w_slosh = _w_stench(_w_quest);
        if (_w_slosh) {
            let _w_loop = details.url;
            let _w_blush = true;
            while (_w_slosh[_w_loop] && _w_loop != _w_slosh[_w_loop]) {
                _w_loop = _w_slosh[_w_loop];
                if (_w_loop == details.redirectUrl) {
                    _w_blush = false;
                    break;
                }
            }
            if (_w_blush) _w_slosh[details.redirectUrl] = details.url;
        }
    }), {
        urls: [ "<all_urls>" ]
    }, [ "responseHeaders" ]);
    let _w_salve = [ "blocking", "requestHeaders" ];
    if (_w_lace >= 72) _w_salve.push("extraHeaders");
    chrome.webRequest.onBeforeSendHeaders.addListener((function(details) {
        let _w_lot = false;
        let _w_scurvy = false;
        let _w_forte = [];
        if (details.tabId == -1) {
            return _w_nausea(details);
        }
        for (let i = 0; i < details.requestHeaders.length; ++i) {
            if (details.requestHeaders[i].name === "Referer") {
                _w_lot = true;
                _w_forte.push(i);
                let _w_cygnet = _w_coven(details.tabId);
                if (_w_cygnet && _w_cygnet[details.url]) {
                    details.requestHeaders[i].value = _w_cygnet[details.url];
                } else if (_w_cygnet) {
                    _w_cygnet[details.url] = details.requestHeaders[i].value;
                }
                let _w_mantle = details.requestHeaders[i].value;
                if (_w_mantle == _w_abuse || _w_mantle == _w_hoop || _w_mantle == null) {
                    _w_scurvy = true;
                }
            } else if (details.requestHeaders[i].name === "IA-Tag") {
                _w_forte.push(i);
                _w_scurvy = true;
            }
        }
        if (_w_scurvy && _w_forte.length > 0) {
            _w_forte.reverse();
            for (let idx in _w_forte) {
                details.requestHeaders.splice(_w_forte[idx], 1);
            }
        }
        if (!_w_lot) {
            let _w_cygnet = _w_coven(details.tabId);
            if (_w_cygnet && _w_cygnet[details.url] && _w_cygnet[details.url] != _w_abuse && _w_cygnet[details.url] != _w_hoop) {
                details.requestHeaders.push({
                    name: "Referer",
                    value: _w_cygnet[details.url]
                });
            }
        }
        return {
            requestHeaders: details.requestHeaders
        };
    }), {
        urls: [ "<all_urls>" ]
    }, _w_salve);
}

function _w_boon(_w_duel, url) {
    let _w_lore = "{origin.title}";
    let _w_nerve = _w_rigor(_w_duel);
    if (_w_nerve) {
        url = _w_tint(url);
        let _w_strife = _w_nerve[url];
        if (_w_strife && _w_strife.title && _w_strife.title.length > 0) {
            _w_lore = _w_strife.title;
        }
    }
    return _w_lore;
}

function _w_genome(_w_rave) {
    let _w_heresy = {
        YYYYMMDD: "YYYY-MM-DD",
        HHmmss: "HH-mm-ss"
    };
    let _w_moat = _w_writ(_w_rave);
    if (_w_moat) _w_heresy = _w_moat.timeStamp;
    return _w_heresy;
}

function _w_nausea(details) {
    let _w_lot = false;
    let _w_hush = false;
    let _w_wit = -1;
    let _w_din = undefined;
    for (let i = 0; i < details.requestHeaders.length; ++i) {
        if (details.requestHeaders[i].name === "Referer") {
            _w_lot = true;
            _w_wit = i;
        }
    }
    Object.values(_w_lash).forEach((function(tab) {
        if (tab._w_cygnet && tab._w_cygnet[details.url] && tab._w_cygnet[details.url] != _w_abuse && tab._w_cygnet[details.url] != _w_hoop) {
            _w_din = tab._w_cygnet[details.url];
        }
    }));
    if (_w_din && _w_lot) {
        details.requestHeaders[_w_wit].value = _w_din;
    } else if (_w_din) {
        details.requestHeaders.push({
            name: "Referer",
            value: _w_din
        });
    }
    return {
        requestHeaders: details.requestHeaders
    };
}

_w_famine();

chrome.runtime.onMessage.addListener((function(message, sender, callback) {
    message && message.type == "_w_tumult" && _w_icon(sender.tab.id);
    message && message.type == "_w_domain" && _w_domain(message._w_bower, message._w_ferry);
    message && message.type == "_w_coop" && _w_coop(message._w_bower, message._w_ferry, message._w_dunce);
    message && message.type == "_w_reel" && _w_petal(message.images, message.extractorHash);
    message && message.type == "_w_slough" && window._w_gust && window._w_gust.notifyFullScroll(sender.tab.id);
    message && message.type == "_w_errand" && _w_sway(message._w_cygnet, sender.tab.id, true);
    message && message.type == "_w_trench" && _w_signal(message.pageInfo, message.extractorHash);
    message && message.type == "_w_mason" && callback(_w_rafter());
    message && message.type == "_w_apiary" && _w_apiary(message.url, message.action, sender.tab.id, message.createNewTab);
    message && message.type == "_w_yokel" && _w_yokel(sender.tab.id, message.fetchLevel);
    message && message.type == "_w_scion" && window._w_frolic.addTask((function(beginFun, endFun) {
        _w_riffle(message.requestParam, message.requestHash, sender.tab.id, beginFun, endFun);
    }));
    message && message.type == "_w_browse" && (localStorage["folderMark"] = message.folderMark);
    message && message.type == "_w_tycoon" && callback(_w_tycoon());
    message && message.type == "_w_molar" && callback(_w_gust.requestNum(sender.tab.id));
}));

function _w_iodine(_w_duel, callback) {
    chrome.runtime.sendMessage(_w_crab(), {
        type: "_w_iodine",
        extractorHash: _w_duel
    }, callback);
}

function _w_bile(_w_bather, _w_skewer) {
    !_w_skewer && (_w_skewer = false);
    let _w_boom = function(_w_moat) {
        _w_riddle(_w_moat.id, (function(observedTab) {
            let _w_loop = observedTab.url;
            let _w_peep = _w_hamper(32);
            let _w_squash = "multiUrlExtractor.html?msgChannel=" + _w_peep;
            if (_w_saliva(_w_loop)) {
                _w_squash += "&originalUrl=" + encodeURIComponent(_w_loop);
            }
            chrome.tabs.create({
                url: _w_squash,
                active: true
            }, (function(newTab) {
                if (_w_saliva(observedTab.url)) {
                    _w_mute(observedTab.id, newTab.id, _w_peep, _w_skewer);
                }
            }));
        }));
    };
    if (_w_bather) {
        _w_boom(_w_bather);
    } else {
        chrome.tabs.query({
            active: true,
            currentWindow: true
        }, (function(tabArray) {
            if (!tabArray || tabArray.length === 0) return;
            let _w_fringe = tabArray[0];
            _w_boom(_w_fringe);
        }));
    }
}

function _w_lasso(_w_ferry) {
    chrome.tabs.query({
        active: true,
        currentWindow: true
    }, (function(tabArray) {
        if (!tabArray || tabArray.length == 0) return;
        let _w_fringe = tabArray[0];
        _w_yokel(_w_fringe.id, _w_ferry);
    }));
}

function _w_plume(url, _w_ferry) {
    chrome.tabs.create({
        url: url,
        active: false
    }, (function(tab) {
        chrome.tabs.onUpdated.addListener((function updatedFun(tabId, changeInfo, updatedTab) {
            if (tabId == tab.id && changeInfo.url) {
                chrome.tabs.onUpdated.removeListener(updatedFun);
                _w_yokel(tabId, _w_ferry);
            }
        }));
    }));
}

function _w_clause(url) {
    chrome.tabs.create({
        url: url,
        active: true
    }, (function(tab) {
        chrome.tabs.onUpdated.addListener((function updatedFun(tabId, changeInfo, updatedTab) {
            if (tabId == tab.id && changeInfo.url) {
                chrome.tabs.onUpdated.removeListener(updatedFun);
                _w_bile(tab, true);
            }
        }));
    }));
}

function _w_yokel(_w_bower, _w_ferry) {
    _w_polish(_w_bower, _w_ferry, true);
}

function _w_polish(_w_bower, _w_ferry, _w_treaty) {
    chrome.tabs.get(_w_bower, (function(tab) {
        if (_w_saliva(tab.url) || _w_ire(tab.url)) {
            let _w_gaiety = setTimeout((function() {
                chrome.tabs.create({
                    index: tab.index + 1,
                    url: "imageExtractor.html?tabId=" + tab.id + "&fetchLevel=" + _w_ferry,
                    active: _w_treaty ? true : false
                });
            }), 512);
            _w_iodine(_w_pundit(tab.id), (function callback(data) {
                if (!chrome.runtime.lastError && data) {
                    clearTimeout(_w_gaiety);
                    chrome.tabs.update(data.tabId, {
                        active: true
                    }, (function(tab) {
                        chrome.windows.update(tab.windowId, {
                            focused: true,
                            drawAttention: false
                        });
                    }));
                }
            }));
        } else {
            chrome.notifications.create("", {
                type: "basic",
                iconUrl: "./images/icon512.png",
                title: _w_podium("_w_brat"),
                message: _w_podium("_w_nadir")
            });
        }
    }));
}

function _w_domain(_w_bower, _w_ferry) {
    let _w_dunce = _w_pundit(_w_bower) + _w_famine();
    _w_coop(_w_bower, _w_ferry, _w_dunce);
}

function _w_coop(_w_bower, _w_ferry, _w_dunce) {
    _w_verse(_w_bower, [ {
        file: "libs/jquery/3.4.1/jquery-3.4.1.min.js",
        runAt: "document_end",
        allFrames: true
    }, {
        file: "libs/DOMPurify/2.0.8/purify.min.js",
        runAt: "document_end",
        allFrames: true
    }, {
        file: "scripts/function.js",
        runAt: "document_end",
        allFrames: true
    }, {
        file: "scripts/mime.js",
        runAt: "document_end",
        allFrames: true
    }, {
        file: "scripts/script.js",
        runAt: "document_end",
        allFrames: true
    }, {
        code: "_w_ridge(" + _w_ferry + ', "' + _w_dunce + '");',
        runAt: "document_end",
        allFrames: true
    } ]);
}

function _w_mute(_w_sop, _w_lode, _w_acuity, _w_skewer) {
    _w_verse(_w_sop, [ {
        file: "libs/jquery/3.4.1/jquery-3.4.1.min.js",
        runAt: "document_end",
        allFrames: false
    }, {
        file: "scripts/function.js",
        runAt: "document_end",
        allFrames: false
    }, {
        file: "scripts/mime.js",
        runAt: "document_end",
        allFrames: false
    }, {
        file: "scripts/characteristicUrlExtract.js",
        runAt: "document_end",
        allFrames: false
    }, {
        code: '_w_pelt(_w_scope, "' + _w_acuity + '", ' + _w_skewer + ");",
        runAt: "document_end",
        allFrames: false
    } ]);
}

function _w_apiary(_w_mold, _w_weed, _w_colony, _w_gall) {
    let _w_whimsy = function(execTab) {
        if (!_w_weed || _w_weed.trim() == "") return;
        chrome.tabs.onUpdated.addListener((function updatedFun(tabId, changeInfo) {
            if (tabId == execTab.id && changeInfo.url) {
                chrome.tabs.onUpdated.removeListener(updatedFun);
                _w_verse(tabId, [ {
                    file: "libs/jquery/3.4.1/jquery-3.4.1.min.js",
                    runAt: "document_end",
                    allFrames: true
                }, {
                    file: "scripts/function.js",
                    runAt: "document_start",
                    allFrames: true
                }, {
                    file: "scripts/scriptForthirdPartPage.js",
                    runAt: "document_start",
                    allFrames: true
                }, {
                    code: _w_weed + "();",
                    runAt: "document_start",
                    allFrames: true
                } ]);
            }
        }));
    };
    if (_w_gall) {
        chrome.tabs.create({
            url: _w_mold,
            active: true
        }, _w_whimsy);
    } else {
        chrome.tabs.update(_w_colony, {
            url: _w_mold,
            active: true
        }, _w_whimsy);
    }
}

window._w_bit = true;

function _w_lever(url) {
    let _w_feint = [];
    for (let idx in window._w_demise) {
        let _w_buckle = window._w_demise[idx]["urlRegexp"];
        if (_w_buckle.test(url)) {
            _w_feint.push(window._w_demise[idx]["urlRuleStr"]);
            if (!window._w_bit) break;
        }
    }
    return _w_feint;
}

function _w_rhyme(url, deepth) {
    let _w_pygmy = {};
    if (!Number.isInteger(deepth)) {
        deepth = 4;
    } else if (deepth <= 0) {
        return Object.keys(_w_pygmy);
    } else {
        deepth--;
    }
    for (let idx in window._w_demise) {
        let _w_buckle = window._w_demise[idx]["urlRegexp"];
        let _w_rein = window._w_demise[idx]["urlReplace"];
        if (_w_buckle.test(url)) {
            let _w_cord = url.replace(_w_buckle, _w_rein);
            if (_w_cord != url) {
                _w_pygmy[_w_cord] = true;
                let _w_clam = _w_rhyme(_w_cord, deepth);
                _w_clam.forEach((function(result_url) {
                    _w_pygmy[result_url] = true;
                }));
                if (!window._w_bit) break;
            }
        }
    }
    return Object.keys(_w_pygmy);
}

function _w_signal(_w_simian, _w_duel) {
    let _w_quest = _w_rivet(_w_duel);
    let _w_ballad = _w_clip(_w_quest);
    if (_w_ballad) {
        _w_trench(_w_simian, _w_rivet(_w_ballad));
    }
    _w_trench(_w_simian, _w_rivet(_w_duel));
}

function _w_trench(_w_simian, _w_bower) {
    if (!_w_bower) {
        return;
    }
    let _w_otter = _w_pleat(_w_bower);
    let _w_clay = _w_tint(_w_simian["url"]);
    _w_simian["url"] = _w_clay;
    _w_otter[_w_clay] = _w_simian;
}

function _w_petal(_w_splint, _w_duel) {
    let _w_quest = _w_rivet(_w_duel);
    let _w_ballad = _w_clip(_w_quest);
    if (_w_ballad) {
        _w_reel(_w_splint, _w_rivet(_w_ballad));
    }
    _w_reel(_w_splint, _w_rivet(_w_duel));
}

function _w_reel(_w_splint, _w_bower) {
    if (!_w_bower) {
        return;
    }
    if (window._w_gust) {
        window._w_gust.notifyPushImage(_w_bower);
    }
    let _w_vogue = _w_qualm(_w_bower);
    let _w_enamel = _w_cement(window._w_list[_w_bower].url);
    let _w_hull = function(item, _w_woe, _w_rung) {
        if (_w_diva(item)) {} else if (!_w_vogue[item]) {
            _w_vogue[item] = _w_woe;
            _w_vogue.push(item);
        } else {
            let _w_dotage = 0;
            if (_w_woe.title && _w_woe.title.length > 0 && _w_vogue[item].title != _w_woe.title) {
                _w_vogue[item].title = _w_woe.title;
                _w_dotage |= 1;
            }
            if (_w_woe.alt && _w_woe.alt.length > 0 && _w_vogue[item].alt != _w_woe.alt) {
                _w_vogue[item].alt = _w_woe.alt;
                _w_dotage |= 2;
            }
            if (_w_woe.referer && _w_woe.referer.length > 0 && _w_vogue[item].referer != _w_woe.referer) {
                _w_vogue[item].referer = _w_woe.referer;
                _w_dotage |= 4;
            }
            if (_w_woe.serial && _w_woe.serial < _w_vogue[item].serial && _w_vogue[item].serial != _w_woe.serial) {
                _w_vogue[item].serial = _w_woe.serial;
                _w_dotage |= 8;
            }
            if (_w_dotage > 0) _w_rung[_w_vogue.indexOf(item)] = _w_dotage;
        }
    };
    let _w_rung = {};
    for (let item in _w_splint) {
        try {
            let _w_woe = _w_splint[item];
            item = _w_awning(item);
            _w_woe.referer = _w_awning(_w_woe.referer);
            if (_w_enamel) item = _w_willow(item);
            try {
                let _w_loon = _w_rhyme(item);
                for (let ridx in _w_loon) {
                    _w_hull(_w_loon[ridx], _w_woe, _w_rung);
                }
            } catch (exception) {}
            _w_hull(item, _w_woe, _w_rung);
        } catch (exception) {}
    }
    chrome.runtime.sendMessage(_w_crab(), {
        type: "_w_jigsaw",
        extractorHash: _w_pundit(_w_bower)
    });
    if (Object.keys(_w_rung).length > 0) {
        chrome.runtime.sendMessage(_w_crab(), {
            type: "_w_augur",
            extractorHash: _w_pundit(_w_bower),
            ItemIdxMap: _w_rung
        });
    }
}

function _w_sway(_w_tag, _w_bower, _w_ode) {
    let _w_cygnet = _w_coven(_w_bower);
    for (let _w_mettle in _w_tag) {
        if (_w_mettle.indexOf(_w_abuse) == 0 || _w_mettle.indexOf(_w_hoop) == 0) {
            continue;
        }
        if (_w_cygnet && (!_w_cygnet[_w_mettle] || _w_cygnet[_w_mettle].length == 0 || _w_ode)) {
            _w_cygnet[_w_mettle] = _w_tag[_w_mettle];
        }
    }
}

function _w_verse(_w_bower, _w_slice, _w_scrub) {
    function createCallback(_w_bower, injectDetails, innerCallback) {
        return function() {
            chrome.tabs.executeScript(_w_bower, injectDetails, innerCallback);
        };
    }
    while (_w_slice.length > 0) {
        _w_scrub = createCallback(_w_bower, _w_slice.pop(), _w_scrub);
    }
    if (_w_scrub !== null) {
        _w_scrub();
    }
}

function _w_fleck(_w_bower, _w_slice, _w_scrub) {
    function createCallback(_w_bower, _w_fusion, _w_spear) {
        return function() {
            chrome.tabs.insertCSS(_w_bower, _w_fusion, _w_spear);
        };
    }
    while (_w_slice.length > 0) {
        _w_scrub = createCallback(_w_bower, _w_slice.pop(), _w_scrub);
    }
    if (_w_scrub !== null) {
        _w_scrub();
    }
}

chrome.commands.onCommand.addListener((function(command) {
    if (command == "command_extract_images") {
        _w_lasso(0);
    } else if (command == "command_multi_extract_images") {
        _w_bile();
    }
}));

function _w_ulcer() {
    let _w_tusk = {
        width: 10,
        height: 10
    };
    let _w_brook = Number.parseInt(_w_maze("defaultFunnelWidth"));
    let _w_wink = Number.parseInt(_w_maze("defaultFunnelHeight"));
    _w_brook && _w_brook > 0 && (_w_tusk.width = _w_brook);
    _w_wink && _w_wink > 0 && (_w_tusk.height = _w_wink);
    return _w_tusk;
}

function _w_entrée(width, height) {
    let _w_brook = Number.parseInt(width);
    let _w_wink = Number.parseInt(height);
    _w_brook && _w_aspect("defaultFunnelWidth", _w_brook);
    _w_wink && _w_aspect("defaultFunnelHeight", _w_wink);
}

function _w_tycoon() {
    let _w_gamut;
    let _w_nicest = _w_maze("image_size");
    _w_nicest ? _w_gamut = _w_eclat(_w_nicest.split(",")) : _w_gamut = _w_vent();
    return _w_gamut;
}

function _w_vent() {
    return _w_eclat(_w_votary.slice(0));
}

function _w_gauge() {
    let _w_gamut = _w_vent();
    _w_aspect("image_size", _w_gamut);
}

function _w_lurch(width, height) {
    let _w_shack = Number.parseInt(width);
    let _w_margin = Number.parseInt(height);
    let _w_chaff = _w_shack && _w_margin ? _w_shack + "x" + _w_margin : null;
    let _w_gamut = _w_tycoon();
    if (!_w_gamut[_w_chaff]) {
        _w_gamut.push(_w_chaff);
        _w_gamut = _w_eclat(_w_gamut);
        _w_gamut[_w_chaff] = {
            width: _w_shack,
            height: _w_margin
        };
        _w_aspect("image_size", _w_gamut);
    }
}

function _w_minnow(width, height) {
    let _w_shack = Number.parseInt(width);
    let _w_margin = Number.parseInt(height);
    let _w_fidget = _w_shack && _w_margin ? _w_shack + "x" + _w_margin : null;
    let _w_gamut = _w_tycoon();
    if (_w_gamut[_w_fidget]) {
        delete _w_gamut[_w_fidget];
        let _w_brood = _w_gamut.indexOf(_w_fidget);
        _w_brood > -1 && _w_gamut.splice(_w_brood, 1);
        _w_aspect("image_size", _w_gamut);
    }
}

function _w_arch() {
    let _w_bluff = 11;
    let _w_chant = 15;
    let _w_dowry = Number.parseInt(_w_maze("menu_status"));
    if (_w_dowry >= 0) {
        _w_bluff = _w_chant & _w_dowry;
    }
    return _w_bluff;
}

function _w_cast(status) {
    let _w_bluff = 15;
    let _w_trowel = Number.parseInt(status);
    if (_w_trowel >= 0) {
        _w_trowel = _w_trowel & _w_bluff;
        _w_aspect("menu_status", _w_trowel);
    }
}

function _w_jade() {
    let _w_barb = _w_crab();
    let _w_lumen = _w_maze(_w_barb);
    if (!_w_lumen || _w_lumen.length < 32) {
        _w_aspect(_w_barb, _w_hamper(32));
        _w_lumen = _w_maze(_w_barb);
    }
    return _w_lumen;
}

function _w_bore() {
    let _w_sage = Number.parseInt(localStorage["dyLoadSize"]);
    if (!_w_sage) {
        _w_sage = 256;
        _w_flak(_w_sage);
    }
    return _w_sage;
}

function _w_flak(_w_hatch) {
    _w_hatch = Number.parseInt(_w_hatch);
    if (!_w_hatch) _w_hatch = 256; else if (_w_hatch < 64) _w_hatch = 64; else if (_w_hatch > 2048) _w_hatch = 2048;
    localStorage["dyLoadSize"] = _w_hatch;
}

function _w_addict() {
    let _w_grotto = Number.parseInt(localStorage["extMaxLoad"]);
    if (!_w_grotto) {
        _w_grotto = 1024;
        _w_satire(_w_grotto);
    }
    return _w_grotto;
}

function _w_satire(_w_pedant) {
    _w_pedant = Number.parseInt(_w_pedant);
    if (!_w_pedant) _w_pedant = 1024; else if (_w_pedant < 1024) _w_pedant = 1024; else if (_w_pedant > 4096) _w_pedant = 4096;
    localStorage["extMaxLoad"] = _w_pedant;
}

function _w_sod() {
    let _w_azure = localStorage["extClickAct"];
    if (typeof _w_azure == "undefined") {
        _w_azure = false;
        _w_mallet(_w_azure);
    }
    return _w_azure;
}

function _w_mallet(_w_knack) {
    if (_w_knack) {
        _w_knack = true;
    } else {
        _w_knack = false;
    }
    localStorage["extClickAct"] = _w_knack;
}

function _w_armada() {
    let _w_harrow = localStorage["regexpUrlRule"];
    if (!_w_harrow || _w_harrow.trim().length == 0) {
        _w_harrow = window._w_core;
        setTimeout((function() {
            _w_clown(_w_harrow);
        }), 2e3);
    }
    return _w_harrow;
}

function _w_clown(_w_tease) {
    localStorage["regexpUrlRule"] = _w_tease;
    return _w_welter();
}

function _w_welter() {
    let _w_squall = [];
    let _w_strand = [];
    let _w_epic = localStorage["_pullywood_RegexpUrlRule"];
    if (!_w_epic) {
        _w_epic = "";
    }
    let _w_smut = _w_armada().concat("\n\r").concat(window._w_core).concat("\n\r").concat(_w_epic).split(/[\n\r]+/);
    window._w_linen = _w_truant();
    for (let idx in _w_smut) {
        let _w_fault = _w_smut[idx].trim();
        if (_w_fault.length == 0 || _w_fault.startsWith("//") || _w_fault.split("{#^_^#}").length != 2) {
            continue;
        } else {
            if (window._w_linen.has(_w_fault)) continue;
            window._w_linen.add(_w_fault);
            let _w_rifle = _w_fault.split("{#^_^#}");
            let _w_buckle = _w_rifle[0];
            let _w_rein = _w_rifle[1];
            try {
                _w_buckle = new RegExp(_w_buckle);
                _w_squall.push({
                    urlRegexp: _w_buckle,
                    urlReplace: _w_rein,
                    urlRuleStr: _w_fault
                });
            } catch (exception) {
                _w_strand.push(_w_buckle);
            }
        }
    }
    window._w_demise = _w_squall;
    return _w_strand;
}

function _w_maze(_w_burial) {
    if (!_w_burial || _w_burial.length == 0) return;
    let _w_taboo = localStorage[_w_burial];
    chrome.storage.sync.get(_w_burial, (function(data) {
        let _w_brace = data[_w_burial];
        if ((!_w_brace || _w_brace.length == 0) && _w_taboo && _w_taboo.length > 0) {
            _w_aspect(_w_burial, _w_taboo);
        } else if (_w_brace && _w_taboo != _w_brace && _w_brace.length > 0) {
            localStorage[_w_burial] = _w_brace;
        }
    }));
    return _w_taboo;
}

function _w_aspect(keyStr, valueStr) {
    valueStr = String(valueStr);
    if (!keyStr || keyStr.length == 0 || !valueStr || valueStr.length == 0) return;
    if (keyStr == _w_crab()) {
        chrome.storage.sync.get(keyStr, (function(data) {
            let _w_brace = data[keyStr];
            if ((!_w_brace || _w_brace.length != 32) && valueStr.length == 32) {
                let _w_annex = {};
                _w_annex[keyStr] = valueStr;
                chrome.storage.sync.set(_w_annex, (function() {}));
                localStorage[keyStr] = valueStr;
            } else {
                localStorage[keyStr] = _w_brace;
            }
        }));
        return;
    }
    let _w_annex = {};
    _w_annex[keyStr] = valueStr;
    chrome.storage.sync.set(_w_annex, (function() {}));
    localStorage[keyStr] = valueStr;
}

chrome.storage.onChanged.addListener((function(changes, namespace) {
    for (let key in changes) {
        let _w_skunk = changes[key];
        localStorage[key] = _w_skunk.newValue;
    }
}));

$.ajax({
    method: "get",
    url: "http://www.pullywood.com/ImageAssistant/version.json?" + Math.random(),
    contentType: "application/json",
    mimeType: "application/json"
}).done((function(data) {
    if (data && data.version) {
        localStorage["version"] = data.version;
    }
})).always((function() {
    if (localStorage["version"] && localStorage["version"] > chrome.runtime.getManifest().version) {
        chrome.browserAction.setBadgeText({
            text: "new"
        });
        chrome.browserAction.setBadgeBackgroundColor({
            color: "#ff3f3f"
        });
    }
}));

function _w_rafter() {
    return window._w_hymn++;
}

function _w_riffle(_w_cuddle, _w_fiat, tabId, _w_tilt, _w_glaze) {
    _w_tilt();
    _w_cuddle["timeout"] = _w_digit;
    _w_cuddle["headers"]["Accept"] = "*/*; charset=UTF-8";
    _w_cuddle["headers"]["Cache-Control"] = "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0";
    _w_cuddle["headers"]["Pragma"] = "no-cache";
    _w_cuddle["headers"]["Expires"] = "0";
    $.ajax(_w_cuddle).always((function(data, status, xhr) {
        _w_glaze();
        if (status == "success") {
            let _w_flange = {};
            if (xhr.getAllResponseHeaders) {
                let _w_drove = xhr.getAllResponseHeaders().split("\n");
                for (let idx in _w_drove) {
                    let _w_locust = _w_drove[idx];
                    let _w_canary = _w_locust.indexOf(":");
                    let _w_ravine = _w_locust.slice(0, _w_canary).trim();
                    let _w_temper = _w_locust.slice(_w_canary + 1).trim();
                    if (_w_ravine.length == 0 || _w_temper.length == 0) {
                        continue;
                    } else if (!_w_flange[_w_ravine]) {
                        _w_flange[_w_ravine] = _w_temper;
                    } else if (typeof _w_flange[_w_ravine] == "string") {
                        let _w_coffer = [];
                        _w_coffer.push(_w_flange[_w_ravine]);
                        _w_coffer.push(_w_temper);
                        _w_flange[_w_ravine] = _w_coffer;
                    } else if (_w_flange[_w_ravine].push) {
                        _w_flange[_w_ravine].push(_w_temper);
                    }
                }
            }
            xhr["responseHeaders"] = _w_flange;
        }
        chrome.tabs.sendMessage(tabId, {
            type: "_w_furor",
            data: data,
            status: status,
            xhr: xhr,
            requestHash: _w_fiat
        });
    }));
}

chrome.contextMenus.create({
    title: _w_podium("_w_reel"),
    id: "_w_cone",
    contexts: [ "all" ]
}, (function() {
    chrome.contextMenus.create({
        title: _w_podium("_w_curb"),
        id: "_w_charm",
        parentId: "_w_cone",
        contexts: [ "link" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_gait"),
        id: "_w_wag",
        parentId: "_w_cone",
        contexts: [ "link" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_snivel"),
        id: "_w_gab",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_quiver"),
        id: "_w_crease",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_fume"),
        id: "_w_hurdle",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_howler"),
        id: "_w_oak",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_spell"),
        id: "_w_ivory",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_larva"),
        id: "_w_rote",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_azure"),
        id: "_w_seam",
        parentId: "_w_cone",
        contexts: [ "selection" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_quack"),
        id: "_w_stroke",
        parentId: "_w_cone",
        contexts: [ "selection" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_chisel"),
        id: "_w_lair",
        parentId: "_w_cone",
        contexts: [ "selection" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_wax").trim(),
        id: "_w_bauble",
        parentId: "_w_cone",
        contexts: [ "page", "frame" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_tatter").trim(),
        id: "_w_easel",
        parentId: "_w_cone",
        contexts: [ "page", "frame" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_puddle").trim(),
        id: "_w_robe",
        parentId: "_w_cone",
        contexts: [ "page", "frame" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_crumb").trim(),
        id: "_w_gear",
        parentId: "_w_cone",
        contexts: [ "page", "frame" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_entry"),
        id: "_w_amble",
        parentId: "_w_cone",
        contexts: [ "page", "frame" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_consul"),
        id: "_w_hulk",
        parentId: "_w_cone",
        contexts: [ "link" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_dent"),
        id: "_w_cravat",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
    chrome.contextMenus.create({
        title: _w_podium("_w_flak"),
        id: "_w_arson",
        parentId: "_w_cone",
        contexts: [ "image" ]
    }, (function() {}));
}));

chrome.contextMenus.onClicked.addListener((function(info, tab) {
    let _w_ken = null;
    if (info.menuItemId == "_w_cravat") {
        _w_ken = _w_cravat(info.srcUrl, info.pageUrl, tab.id);
    } else if (info.menuItemId == "_w_arson") {
        _w_ken = _w_arson(info.srcUrl, info.pageUrl, tab.id);
    } else if (info.menuItemId == "_w_gab") {
        _w_ken = _w_gab(info.srcUrl);
    } else if (info.menuItemId == "_w_crease") {
        _w_ken = _w_crease(info.srcUrl);
    } else if (info.menuItemId == "_w_hurdle") {
        _w_ken = _w_hurdle(info.srcUrl);
    } else if (info.menuItemId == "_w_oak") {
        _w_ken = _w_oak(info.srcUrl);
    } else if (info.menuItemId == "_w_ivory") {
        _w_ken = _w_ivory(info.srcUrl);
    } else if (info.menuItemId == "_w_seam") {
        _w_ken = _w_inroad(info.selectionText);
    } else if (info.menuItemId == "_w_stroke") {
        _w_ken = _w_cub(info.selectionText);
    } else if (info.menuItemId == "_w_charm") {
        _w_plume(info.linkUrl, 0);
    } else if (info.menuItemId == "_w_wag") {
        _w_clause(info.linkUrl);
    } else if (info.menuItemId == "_w_bauble") {
        _w_yokel(tab.id, 0);
    } else if (info.menuItemId == "_w_easel") {
        _w_yokel(tab.id, 1);
    } else if (info.menuItemId == "_w_robe") {
        _w_yokel(tab.id, 3);
    } else if (info.menuItemId == "_w_gear") {
        _w_bile();
    } else if (info.menuItemId == "_w_lair") {
        _w_wile(tab.id, info.selectionText);
    } else if (info.menuItemId == "_w_rote") {
        _w_wile(tab.id, info.srcUrl);
    } else if (info.menuItemId == "_w_hulk") {
        _w_wile(tab.id, info.linkUrl);
    } else if (info.menuItemId == "_w_amble") {
        _w_wile(tab.id, info.pageUrl);
    } else {
        return;
    }
    if (_w_ken) {
        if (_w_cement(info.pageUrl)) _w_ken.url = _w_willow(_w_ken.url);
        _w_apiary(_w_ken.url, _w_ken.action, tab.id, _w_ken.createNewTab);
    }
}));

function _w_cravat(_w_mettle, _w_hawk, _w_bower) {
    if (!_w_mettle) {
        return;
    } else if (!_w_mettle.startsWith("data:image") && _w_cement(_w_hawk)) {
        let _w_cygnet = _w_coven(_w_bower);
        if (_w_cygnet[_w_mettle]) {
            _w_hawk = _w_cygnet[_w_mettle];
        }
    }
    chrome.tabs.create({
        url: "imageEditor.html",
        active: true
    }, (function(tab) {
        _w_shale(tab.id, (function(_w_lackey) {
            chrome.tabs.sendMessage(_w_lackey.id, {
                type: "_w_cravat",
                _w_patch: _w_mettle,
                _w_hawk: _w_hawk
            });
        }));
    }));
}

function _w_arson(_w_mettle, _w_hawk, _w_bower) {
    if (!_w_mettle.startsWith("data:image") && _w_cement(_w_hawk)) {
        let _w_cygnet = _w_coven(_w_bower);
        if (_w_cygnet[_w_mettle]) {
            _w_hawk = _w_cygnet[_w_mettle];
        }
    }
    chrome.runtime.sendMessage(chrome.runtime.id, {
        type: "_w_noose",
        _w_patch: _w_mettle,
        _w_hawk: _w_hawk
    });
}

function _w_wile(_w_bower, _w_sentry) {
    if (!_w_bower) return;
    chrome.tabs.get(_w_bower, (function(tab) {
        let _w_mold = new URL(tab.url);
        if (_w_moment(_w_mold.href)) {
            chrome.runtime.sendMessage(_w_crab(), {
                type: "_w_affix",
                text: _w_sentry
            });
        } else if (_w_mold.protocol == "http:" || _w_mold.protocol == "https:") {
            _w_verse(_w_bower, [ {
                file: "libs/jquery/3.4.1/jquery-3.4.1.min.js",
                allFrames: false
            }, {
                file: "libs/qrcode/dist/qrcode.js",
                allFrames: false
            }, {
                file: "libs/bootstrap/3.4.1/js/bootstrap.min.js",
                allFrames: false
            }, {
                file: "scripts/function.js",
                allFrames: false
            }, {
                code: "_w_affix(" + JSON.stringify(_w_sentry) + ", true);",
                allFrames: false
            } ]);
        } else {
            chrome.notifications.create("", {
                type: "basic",
                iconUrl: "./images/icon512.png",
                title: _w_podium("_w_brat"),
                message: _w_podium("_w_pulpit")
            });
        }
    }));
}