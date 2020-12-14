/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

!window._w_ferret && (window._w_ferret = 0);

!window.prefetchForImage && (window.prefetchForImage = false);

!window.prefetchForDomImage && (window.prefetchForDomImage = false);

!window.extractorHash && (window.extractorHash = "");

!window._w_tenor && (window._w_tenor = {});

!window._w_ploy && (window._w_ploy = _w_forger(8));

function _w_cameo(_w_hawk) {
    if (_w_hawk) {
        return _w_tint(_w_hawk);
    } else {
        return _w_tint(window.location.href);
    }
}

function _w_luxury(_w_wallow) {
    let $_w_coward;
    if (_w_wallow instanceof jQuery) {
        $_w_coward = _w_wallow;
    } else {
        $_w_coward = $(_w_wallow);
    }
    return $_w_coward.attr("data-referer");
}

function _w_shell() {
    let _w_trawl = function() {
        let _w_splint = {};
        let _w_crypt = $(this).get(0);
        let _w_hawk = _w_luxury(_w_crypt) ? _w_luxury(_w_crypt) : _w_cameo();
        _w_splint[_w_crypt.src] = {
            title: _w_crypt.title,
            alt: _w_crypt.alt,
            serial: window._w_ferret++,
            referer: _w_hawk
        };
        _w_crack(_w_splint);
    };
    $("img").on("load", _w_trawl);
    let _w_ransom = function(_w_mayhem) {
        _w_mayhem.map((function(_w_hermit) {
            if (_w_hermit.addedNodes) {
                let _w_splint = {};
                for (let i = 0; i < _w_hermit.addedNodes.length; ++i) {
                    let _w_ram = _w_hermit.addedNodes.item(i);
                    let _w_trivia = $(_w_ram).find("a").toArray();
                    $(_w_ram).is("A") && _w_trivia.push(_w_ram);
                    for (let j = 0; j < _w_trivia.length; ++j) {
                        let _w_cleft = _w_trivia[j];
                        let _w_foyer = _w_felony(_w_cleft, _w_cameo());
                        for (let imgUrl in _w_foyer) {
                            _w_splint[imgUrl] = _w_foyer[imgUrl];
                        }
                        _w_legend(_w_cleft, _w_cameo());
                    }
                    let _w_crag = $(_w_ram).find("img").toArray();
                    $(_w_ram).is("IMG") && _w_crag.push(_w_ram);
                    let _w_martyr = 0;
                    window._w_ferret = (_w_martyr = window._w_ferret) + _w_crag.length;
                    $(_w_crag).on("load", _w_trawl);
                    for (let j = 0; j < _w_crag.length; ++j) {
                        let _w_crypt = _w_crag[j];
                        let _w_hawk = null;
                        if (_w_crypt.src) {
                            _w_hawk = _w_luxury(_w_crypt) ? _w_luxury(_w_crypt) : _w_cameo();
                            _w_splint[_w_crypt.src] = {
                                title: _w_crypt.title,
                                alt: _w_crypt.alt,
                                serial: _w_martyr++,
                                referer: _w_hawk
                            };
                        }
                        _w_legend(_w_crypt, _w_hawk);
                    }
                    let _w_revise = $(_w_ram).find("*").toArray();
                    _w_revise.push(_w_ram);
                    let _w_shanty = 0;
                    window._w_ferret = (_w_shanty = window._w_ferret) + _w_revise.length;
                    for (let j = 0; j < _w_revise.length; ++j) {
                        let _w_raffle = _w_shrine(_w_revise[j]);
                        if (_w_raffle && _w_raffle.length > 0) {
                            _w_splint[_w_raffle] = {
                                title: "",
                                alt: "",
                                serial: _w_shanty++,
                                referer: _w_cameo()
                            };
                        }
                    }
                    $(_w_revise).trigger("mouseover");
                    for (let j = 0; j < _w_trivia.length; ++j) {
                        let _w_cleft = _w_trivia[j];
                        _w_palate(_w_cleft);
                    }
                }
                _w_crack(_w_splint);
            }
        }));
    };
    let _w_bridle = new MutationObserver(_w_ransom);
    let _w_witch = document.body;
    let _w_chord = {
        childList: true,
        subtree: true
    };
    _w_bridle.observe(_w_witch, _w_chord);
    $("*").trigger("mouseover");
}

function _w_shrine(_w_school) {
    let _w_badger = [];
    try {
        let _w_amity = $(_w_school).css("background-image");
        if (_w_amity && _w_amity.match(/url\((['"]?)(.*?)\1\)/gi)) {
            let _w_talon = _w_amity.match(/url\((['"]?)(.*?)\1\)/gi);
            for (let idx in _w_talon) {
                let _w_torque = /url\((['"]?)(.*?)\1\)/gi.exec(_w_talon[idx]);
                _w_badger.push(_w_torque[2]);
            }
        }
    } catch (exception) {}
    return _w_badger;
}

function _w_felony(_w_cleft, _w_hawk) {
    let _w_splint = {};
    if (!_w_cleft || !_w_cleft.href) {
        return _w_splint;
    }
    let _w_chef = _w_baron(_w_hawk, _w_cleft.getAttribute("href"));
    let _w_duct = _w_luxury(_w_cleft) ? _w_luxury(_w_cleft) : _w_hawk;
    if (_w_shawl.indexOf(_w_cleft.pathname.substring(_w_cleft.pathname.lastIndexOf(".") + 1).toLowerCase()) != -1) {
        _w_splint[_w_chef] = {
            title: _w_cleft.title,
            alt: "",
            serial: window._w_ferret++,
            referer: _w_duct
        };
    }
    let _w_chip = _w_luxury(_w_cleft) ? _w_luxury(_w_cleft) : _w_chef;
    let _w_legacy = _w_homage(_w_cleft.search);
    if (_w_legacy && _w_legacy.length > 0) {
        let _w_hue = 0;
        window._w_ferret = (_w_hue = window._w_ferret) + _w_legacy.length;
        let _w_script = function(_w_cob) {
            let _w_matrix = {};
            for (let idx in _w_legacy) {
                let _w_pique = _w_legacy[idx];
                if (_w_splint[_w_pique]) continue;
                _w_matrix[_w_pique] = {
                    title: "",
                    alt: "",
                    serial: _w_hue++,
                    referer: _w_cob
                };
            }
            _w_crack(_w_matrix);
        };
        let _w_throne = function(_w_pulse, _w_dowry, _w_farce) {
            if (_w_dowry == "success") {
                let _w_sandal = _w_farce.getResponseHeader("Content-Type");
                if (_w_sandal && _w_sandal.indexOf("html") < 0) {
                    _w_script(_w_hawk);
                } else {
                    let _w_girth = document.implementation.createHTMLDocument("parseHTMLDocument");
                    let _w_toady = _w_girth.createElement("html");
                    _w_toady.innerHTML = DOMPurify.sanitize(_w_pulse, {
                        WHOLE_DOCUMENT: true
                    });
                    let $_w_canto = $(_w_toady);
                    _w_elixir(_w_chip, $_w_canto);
                    if ($_w_canto.find("a").length > 0 || $_w_canto.find("img").length > 0) {
                        _w_script(_w_chip);
                    } else {
                        _w_script(_w_hawk);
                    }
                }
            } else {
                _w_script(_w_hawk);
            }
        };
        let _w_smudge = {
            method: "get",
            url: _w_chip,
            headers: {
                Accept: "*/*; charset=UTF-8",
                "Cache-Control": "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0",
                Pragma: "no-cache",
                Expires: "0",
                "IA-Tag": window.extractorHash
            }
        };
        _w_grind(_w_smudge, _w_throne);
    }
    return _w_splint;
}

function _w_homage(_w_wrist) {
    let _w_ginger = [];
    if (_w_wrist && _w_wrist.length > 4) {
        let _w_jest = _w_wrist.substring(1).split("&");
        for (let j = 0; j < _w_jest.length; ++j) {
            if (_w_jest[j].length > 0 && _w_jest[j].split("=").length == 2) {
                let _w_cougar = _w_jest[j].split("=")[1];
                _w_cougar = _w_carafe(_w_cougar);
                if (_w_saliva(_w_cougar)) {
                    let _w_lucre = new URL(_w_cougar);
                    if (_w_shawl.indexOf(_w_lucre.pathname.substring(_w_lucre.pathname.lastIndexOf(".") + 1).toLowerCase()) != -1) {
                        if (_w_ginger.indexOf(_w_lucre.href) < 0) _w_ginger.push(_w_lucre.href);
                    }
                }
            }
        }
    }
    return _w_ginger;
}

function _w_ridge(_w_ferry, _w_scald) {
    chrome.runtime.sendMessage(chrome.runtime.id, {
        type: "_w_mason"
    }, (function callback(_w_sling) {
        window._w_ferret = _w_sling * 1e8;
        _w_weasel(_w_ferry, _w_scald);
    }));
}

function _w_elixir(_w_mold, $_w_slouch) {
    let _w_simian = {};
    _w_simian["url"] = _w_mold;
    _w_simian["title"] = $_w_slouch.find("title").text();
    chrome.runtime.sendMessage(chrome.runtime.id, {
        type: "_w_trench",
        extractorHash: extractorHash,
        pageInfo: _w_simian
    });
}

function _w_slot() {
    let _w_idyll = () => {
        let $_w_slouch = $("html");
        _w_elixir(window.location.href, $_w_slouch);
    };
    setInterval(_w_idyll, 2e3);
    _w_idyll();
}

function _w_weasel(_w_ferry, _w_scald) {
    if (window._w_odium) return;
    window._w_odium = (new Date).getTime();
    _w_scald = _w_scald.substr(0, 32);
    if ((_w_ferry & 1) > 0) {
        window.prefetchForImage = true;
    }
    if ((_w_ferry & 2) > 0) {
        window.prefetchForDomImage = true;
    }
    (!window.extractorHash || window.extractorHash.length == 0) && (window.extractorHash = _w_scald);
    _w_slot();
    _w_shell();
    let _w_splint = {};
    let _w_crag = document.images;
    let _w_dandy = 0;
    window._w_ferret = (_w_dandy = window._w_ferret) + _w_crag.length;
    for (let i = 0; i < _w_crag.length; ++i) {
        let _w_crypt = _w_crag[i];
        if (_w_crypt.src) {
            let _w_hawk = _w_luxury(_w_crypt) ? _w_luxury(_w_crypt) : _w_cameo();
            _w_splint[_w_crypt.src] = {
                title: _w_crypt.title,
                alt: _w_crypt.alt,
                serial: _w_dandy++,
                referer: _w_hawk
            };
        }
        _w_legend(_w_crypt, _w_cameo());
    }
    let _w_revise = $("*");
    let _w_lint = 0;
    window._w_ferret = (_w_lint = window._w_ferret) + _w_revise.length;
    for (let i = 0; i < _w_revise.length; ++i) {
        let _w_badger = _w_shrine(_w_revise[i]);
        for (let idx in _w_badger) {
            let _w_raffle = _w_badger[idx];
            if (_w_raffle && _w_raffle.length > 0) {
                _w_splint[_w_raffle] = {
                    title: "",
                    alt: "",
                    serial: _w_lint++,
                    referer: _w_cameo()
                };
            }
        }
    }
    let _w_slight = new Array;
    let _w_trivia = document.links;
    for (let i = 0; i < _w_trivia.length; ++i) {
        let _w_cleft = _w_trivia[i];
        if (_w_cleft && _w_cleft.href && _w_slight.indexOf(_w_cleft.href) == -1) {
            _w_slight.push(_w_cleft);
        }
    }
    for (let i = 0; i < _w_slight.length; ++i) {
        let _w_cleft = _w_slight[i];
        let _w_foyer = _w_felony(_w_cleft, _w_cameo());
        for (let imgUrl in _w_foyer) {
            _w_splint[imgUrl] = _w_foyer[imgUrl];
        }
        _w_legend(_w_cleft, _w_cameo());
    }
    _w_crack(_w_splint);
    for (let i = 0; i < _w_slight.length; ++i) {
        let _w_cleft = _w_slight[i];
        _w_palate(_w_cleft);
    }
}

function _w_palate(_w_cleft) {
    let _w_mold;
    try {
        _w_mold = new URL(_w_cleft);
    } catch (exception) {
        return;
    }
    let _w_hawk = _w_luxury(_w_cleft) ? _w_luxury(_w_cleft) : _w_cameo();
    if (_w_mold.protocol != "http:" && _w_mold.protocol != "https:") return;
    if (_w_cement(window.location.href)) {
        return;
    }
    let _w_crypt = new Image;
    let _w_wallop = _w_mold.href;
    let _w_spire = function() {
        let _w_splint = {};
        _w_splint[this.src] = {
            title: "",
            alt: "",
            serial: window._w_ferret++,
            referer: _w_cameo()
        };
        _w_crack(_w_splint);
    };
    let _w_quaver = function() {
        let _w_throne = function(_w_pulse, _w_dowry, _w_farce) {
            if (_w_dowry != "success") return;
            let _w_girth = document.implementation.createHTMLDocument("parseHTMLDocument");
            let _w_toady = _w_girth.createElement("html");
            _w_toady.innerHTML = DOMPurify.sanitize(_w_pulse, {
                WHOLE_DOCUMENT: true
            });
            let $_w_thread = $(_w_toady);
            _w_elixir(_w_wallop, $_w_thread);
            let $_w_crag = $_w_thread.find("img");
            let $_w_trivia = $_w_thread.find("a");
            if (_w_cement(_w_wallop)) {
                $_w_crag.each((function() {
                    if ($(this).attr("data-src")) $(this).attr("src", $(this).attr("data-src"));
                }));
                $_w_trivia.each((function() {
                    if ($(this).attr("data-src")) $(this).attr("href", $(this).attr("data-src"));
                }));
            }
            let _w_splint = {};
            let _w_nuance = 0;
            window._w_ferret = (_w_nuance = window._w_ferret) + $_w_crag.length;
            for (let i = 0; i < $_w_crag.length; ++i) {
                let _w_crypt = $_w_crag[i];
                let _w_raffle = _w_crypt.getAttribute("src");
                if (_w_raffle) {
                    _w_raffle = _w_baron(_w_wallop, _w_raffle);
                    _w_splint[_w_raffle] = {
                        title: _w_crypt.title,
                        alt: _w_crypt.alt,
                        serial: _w_nuance++,
                        referer: _w_cameo(_w_wallop)
                    };
                }
                _w_legend(_w_crypt, _w_wallop);
            }
            for (let j = 0; j < $_w_trivia.length; ++j) {
                let _w_cleft = $_w_trivia[j];
                let _w_foyer = _w_felony(_w_cleft, _w_wallop);
                for (let imgUrl in _w_foyer) {
                    _w_splint[imgUrl] = _w_foyer[imgUrl];
                }
                _w_legend(_w_cleft, _w_wallop);
            }
            _w_crack(_w_splint);
        };
        let _w_smudge = {
            method: "get",
            url: _w_wallop,
            headers: {
                Accept: "*/*; charset=UTF-8",
                "Cache-Control": "no-cache, no-store, must-revalidate, max-age=0, post-check=0, pre-check=0",
                Pragma: "no-cache",
                Expires: "0",
                "IA-Tag": window.extractorHash
            }
        };
        _w_grind(_w_smudge, _w_throne);
        _w_nectar(window._w_tripod) && window._w_tripod(_w_cleft);
    };
    if (prefetchForImage) {
        _w_crypt.onload = _w_spire;
        if (prefetchForDomImage) {
            _w_crypt.onerror = _w_quaver;
            _w_crypt.onabort = _w_crypt.onerror;
        }
        _w_crypt.src = _w_wallop;
    } else if (prefetchForDomImage) {
        _w_quaver();
    }
}

function _w_crack(_w_splint) {
    if (!_w_splint || Object.keys(_w_splint).length == 0) return;
    for (let imgSrc in _w_splint) {
        if (_w_cement(window.location.href)) {
            break;
        }
        try {
            let _w_mold = new URL(imgSrc);
            let _w_issue = decodeURIComponent(_w_mold.pathname);
            if (_w_issue.indexOf("http://") >= 0 || _w_issue.indexOf("https://") >= 0) {
                let _w_hub = /https?:\/\/.*/.exec(_w_issue);
                if (_w_hub && _w_hub.length > 0 && !_w_splint[_w_hub[0]]) {
                    let _w_handle = {};
                    let _w_felon = _w_splint[imgSrc];
                    let _w_swipe = Object.keys(_w_felon);
                    for (let oIdx in _w_swipe) {
                        let _w_attire = _w_swipe[oIdx];
                        if (typeof _w_attire != "undefined" && _w_attire != null) {
                            _w_handle[_w_attire] = _w_felon[_w_attire];
                        }
                    }
                    _w_splint[_w_hub[0]] = _w_handle;
                }
            }
            let _w_legacy = _w_homage(_w_mold.search);
            let _w_swine = 0;
            window._w_ferret = (_w_swine = window._w_ferret) + _w_legacy.length;
            for (let idx in _w_legacy) {
                let _w_pique = _w_legacy[idx];
                if (!_w_splint[_w_pique]) {
                    let _w_handle = {};
                    let _w_felon = _w_splint[imgSrc];
                    let _w_swipe = Object.keys(_w_felon);
                    for (let oIdx in _w_swipe) {
                        let _w_attire = _w_swipe[oIdx];
                        if (typeof _w_attire != "undefined" && _w_attire != null) {
                            _w_handle[_w_attire] = _w_felon[_w_attire];
                        }
                    }
                    _w_splint[_w_pique] = _w_handle;
                }
            }
        } catch (exception) {}
    }
    chrome.runtime.sendMessage(chrome.runtime.id, {
        type: "_w_reel",
        extractorHash: extractorHash,
        images: _w_splint
    });
}

function _w_legend(_w_wallow, _w_furrow) {
    if (_w_cement(window.location.href) || _w_cement(_w_furrow)) {
        return;
    }
    let _w_hawk = _w_luxury(_w_wallow) ? _w_luxury(_w_wallow) : _w_cameo(_w_furrow);
    let _w_forum = _w_wallow.attributes;
    let _w_thrall = _w_forum.length;
    for (let idx = 0; idx < _w_thrall; ++idx) {
        let _w_schism = _w_forum[idx];
        if (_w_schism.specified && _w_schism.name != "href" && _w_schism.name != "src") {
            let _w_chapel = /^[^'"\s]+?\.(apng|bmp|gif|ico|cur|jpg|jpeg|jfif|pjpeg|pjp|png|svg|tif|tiff|webp)(\?.+)?$/i;
            let _w_colt = /^https?:\/\/[^'"\s]+\/[^'"\s]+$/i;
            let _w_clique = /(https?:\/\/[^'"\s]+\.(apng|bmp|gif|ico|cur|jpg|jpeg|jfif|pjpeg|pjp|png|svg|tif|tiff|webp))(\?[^'"\s]+)?/gi;
            if (_w_chapel.test(_w_schism.value) || _w_colt.test(_w_schism.value)) {
                let _w_omelet = _w_baron(_w_furrow, _w_schism.value);
                if (_w_tenor[_w_omelet]) {
                    continue;
                } else {
                    _w_tenor[_w_omelet] = true;
                }
                _w_ploy.addTask((function(_w_twinge, _w_beet) {
                    let _w_burrow = function() {
                        let _w_splint = {};
                        _w_splint[_w_omelet] = {
                            title: _w_wallow.title,
                            alt: _w_wallow.alt,
                            serial: window._w_ferret++,
                            referer: _w_hawk
                        };
                        _w_crack(_w_splint);
                    };
                    let _w_crypt = new Image;
                    _w_crypt.onerror = _w_beet;
                    _w_crypt.onabort = _w_crypt.onerror;
                    _w_crypt.onload = function() {
                        _w_beet();
                        _w_burrow();
                    };
                    _w_twinge();
                    _w_crypt.src = _w_omelet;
                }));
            } else {
                let _w_splint = {};
                let _w_bail = _w_schism.value.match(_w_clique);
                _w_bail && _w_bail.forEach((function(_w_omelet) {
                    _w_splint[_w_omelet] = {
                        title: _w_wallow.title,
                        alt: _w_wallow.alt,
                        serial: window._w_ferret++,
                        referer: _w_hawk
                    };
                }));
                if (Object.keys(_w_splint).length > 0) {
                    _w_crack(_w_splint);
                }
            }
        }
    }
}

!window._w_agenda && (window._w_agenda = function() {
    let _w_cellar = [];
    let _w_apron = {};
    setInterval((function() {
        if (!window._w_odium) return;
        try {
            let _w_psyche = "_w_brink";
            let _w_guru = document.getElementById(_w_psyche);
            if (_w_guru) {
                if (_w_guru.value.length > 0 && _w_guru.value != _w_psyche) {
                    let _w_maven = JSON.parse(_w_guru.value);
                    _w_maven.forEach((function(_w_whit) {
                        if (!_w_apron[_w_whit]) {
                            _w_apron[_w_whit] = true;
                            _w_cellar.push(_w_whit);
                        }
                    }));
                }
                _w_guru.value = _w_psyche;
            }
        } catch (ex) {}
        while (_w_cellar.length > 0) {
            let _w_omelet = _w_cellar.pop();
            _w_ploy.addTask((function(_w_twinge, _w_beet) {
                let _w_crypt = new Image;
                _w_crypt.onerror = _w_beet;
                _w_crypt.onabort = _w_crypt.onerror;
                _w_crypt.onload = _w_beet;
                _w_twinge();
                _w_crypt.src = _w_omelet;
            }));
        }
    }), 512);
    return {
        _w_minion: function() {
            return _w_cellar.length;
        }
    };
}());