//chrome.tabs.onUpdated.addListener(function(tabId,changeInfo,tab) {
//	chrome.pageAction.show(tabId);
//	})

//if we have new version show the updated info
chrome.runtime.onInstalled.addListener(function (details) {
    if (["update"].indexOf(details.reason) !== -1 && localStorage.getItem("cmfirst")) {
        //set it to 1 if we have update info to show
        localStorage.showUpdatedInfo = 0;
        localStorage.cmfirst = 1;
        openOptions();
    }
});

var defaultConfig = {
    cfgver: 2.3,
    others: {
        tuilink: false
    },
    normal: {
        gesture: true,
        drag: true,
        scroll: false,
        autocancel: false,
        autocancelvalue: 2,
        lasttab: false,
        scrolleffects: true,
        newtabposition: "chrome",

        minilength: 10,
        capturetype: "jpeg",
        jpegquality: 100,
        cancelcontextmenu: true,
        dbclicktime: 600
    },

    gesture: {
        gestureui: true,
        stroke: true,
        direct: true,
        tooltip: true,
        strokecolor: "4E1485",
        strokewidth: 5,
        strokeopa: 0.8,
        directcolor: "5E6A88",
        directopa: 0.9,
        tooltipcolor: "120310",
        tooltipwidth: 18,
        tooltipopa: 0.9,
        geskey: "right",
        stenable: false,
        gholdkey: "none",
        gesPos: "cc",

        gesture: [
            {
                direct: "L",
                action: "G_back"
            },
            {
                direct: "R",
                action: "G_go"
            },
            {
                direct: "U",
                action: "G_up"
            },
            {
                direct: "D",
                action: "G_down"
            },
            {
                direct: "DR",
                action: "G_close",
                moreCloseopts: "close",
                moreClosesel: "chrome",
                moreCloseurl: "chrome://newtab/"
            },
            {
                direct: "LU",
                action: "G_reclosedtab",
                moreTarget: "newfront",
                morePosition: "chrome",
                morePinned: "unpinned",
                moreDes: chrome.i18n.getMessage("G_reclosedtab")
            },
            {
                direct: "RD",
                action: "G_bottom"
            },
            {
                direct: "RU",
                action: "G_top"
            },
            {
                direct: "UD",
                action: "G_reload"
            },
            {
                direct: "UDU",
                action: "G_reloadclear"
            },
            {
                direct: "UL",
                action: "G_lefttab"
            },
            {
                direct: "UR",
                action: "G_righttab"
            },
            {
                direct: "DRU",
                action: "G_newwindow"
            },
            {
                direct: "URD",
                action: "G_closewindow"
            },
            {
                direct: "RDLU",
                action: "G_crxsettings",
                moreDes: chrome.i18n.getMessage("G_crxsettings"),
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newfront"
            }
        ]
    },
    drag: {
        dragui: true,
        dstroke: true,
        ddirect: true,
        dtooltip: true,
        dstrokecolor: "4E1485",
        dstrokewidth: 5,
        dstrokeopa: 0.8,
        ddirectcolor: "5E6A88",
        ddirectopa: 0.9,
        dtooltipcolor: "120310",
        dtooltipwidth: 18,
        dtooltipopa: 0.9,
        dragtext: true,
        draglink: true,
        dragimage: true,
        draginput: false,
        setdragurl: true,
        imgfirstcheck: false,
        imgfirst: "none",
        dholdkey: "none",

        text: [
            {
                direct: "L",
                action: "T_search",
                moreDes: chrome.i18n.getMessage("valuetsearch") + "(" + chrome.i18n.getMessage("newback") + ")",
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newback",
                moreTsearch: "sgoogle"
            },
            {
                direct: "R",
                action: "T_search",
                moreDes: chrome.i18n.getMessage("valuetsearch") + "(" + chrome.i18n.getMessage("newfront") + ")",//"使用%name搜索:"%s"",
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newfront",
                moreTsearch: "sgoogle"
            },
            {
                direct: "D",
                action: "T_copytext"
            }
        ],
        link: [
            {
                direct: "L",
                action: "L_open",
                moreDes: chrome.i18n.getMessage("L_open") + "(" + chrome.i18n.getMessage("newback") + ")",//"打开链接",
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newback"
            },
            {
                direct: "R",
                action: "L_open",
                moreDes: chrome.i18n.getMessage("L_open") + "(" + chrome.i18n.getMessage("newfront") + ")",//"打开链接",
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newfront"
            },
            {
                direct: "D",
                action: "L_copytext"
            },
            {
                direct: "U",
                action: "L_copyurl"
            }
        ],
        image: [
            {
                direct: "L",
                action: "I_open",
                moreDes: chrome.i18n.getMessage("I_open") + "(" + chrome.i18n.getMessage("newback") + ")",
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newback"
            },
            {
                direct: "R",
                action: "I_open",
                moreDes: chrome.i18n.getMessage("I_open") + "(" + chrome.i18n.getMessage("newfront") + ")",
                morePinned: "unpinned",
                morePosition: "chrome",
                moreTarget: "newfront"
            },
            {
                direct: "D",
                action: "I_save"
            }
        ]
    },

    scroll: {
        smooth: true,
        scrollspeed: 3,
        scrollaccele: 1
    },

    scrollgesture: {
        tablist: true,
        tablistkey: "right",
        tablistVisual: true,
        sgsleftenable: true,
        sgsrightenable: false,
        sgsleft: [
            {action: "G_top"},
            {action: "G_bottom"}
        ],
        sgsright: [
            {action: "G_top"},
            {action: "G_bottom"}
        ],
        fastSwitch: false,
        reverseFS: false


    },

    strokegesture: {
        strpress: "up",
        strleftenable: true,
        strleft: [
            {action: "G_none"},
            {action: "G_righttab"}
        ],
        strmiddleenable: false,
        strmiddle: [
            {action: "G_lefttab"},
            {action: "G_righttab"}
        ],
        strrightenable: true,
        strright: [
            {action: "G_lefttab"},
            {action: "G_none"}
        ]
    }


};


var action = {
    gesture: [
        {action: "G_none"},
        //导航
        {action: "G_back"},
        {action: "G_go"},
        {action: "G_goparent"},
        {action: "G_stop"},
        {action: "G_stopall"},
        {action: "G_trynext"},
        {action: "G_tryprev"},
        //{action: "G_homePage"},
        //滚动
        {action: "G_up"},
        {action: "G_down"},
        {action: "G_top"},
        {action: "G_bottom"},
        {action: "G_left"},
        {action: "G_right"},
        //加载
        {action: "G_reload"},
        {action: "G_reloadclear"},
        {action: "G_reloadframe"},
        {action: "G_reloadall"},
        {action: "G_reclosedtab"},
        {action: "G_openclipurl"},
        //标签页
        {action: "G_close"},
        {action: "G_closelefttabs"},
        {action: "G_closerighttabs"},
        {action: "G_closeothers"},
        {action: "G_newtab"},
        {action: "G_newusertab"},
        {action: "G_copytab"},
        {action: "G_movetowindow"},
        {action: "G_pin"},
        {action: "G_splitTab"},
        {action: "G_mergeTab"},
        //标签页导航
        {action: "G_lefttab"},
        {action: "G_righttab"},
        {action: "G_firsttab"},
        {action: "G_lasttab"},
        //窗口
        {action: "G_newwindow"},
        {action: "G_incognito"},
        {action: "G_closewindow"},
        {action: "G_closewindows"},
        {action: "G_windowmax"},
        {action: "G_windowmin"},
        {action: "G_fullscreen"},
        //复制
        {action: "G_copyurl"},
        {action: "G_copytitle"},
        {action: "G_copyaslink"},
        {action: "G_copyuser"},
        //其他
        {action: "G_capture"},
        {action: "G_chromepage"},
        {action: "G_viewsource"},
        {action: "G_crxsettings"},
        {action: "G_tostop"},
        {action: "G_ZoomIn"},
        {action: "G_ZoomOut"},
        {action: "G_userscript"},
        {action: "G_bookmark"},
        {action: "G_BmManager"}

    ],

    text: [
        {action: "G_none"},
        {action: "T_search"},
        {action: "T_searchuser"},
        {action: "T_copytext"}
    ],

    link: [
        {action: "G_none"},
        {action: "L_open"},
        {action: "L_copytext"},
        {action: "L_copyurl"},
        {action: "L_copyaslink"},
        {action: "L_copyuser"},
        {action: "L_bookmark"}
    ],

    image: [
        {action: "G_none"},
        {action: "I_open"},
        {action: "I_save"},
        {action: "I_saveback"},
        {action: "I_copyurl"},
        {action: "I_search"},
        {action: "I_searchuser"}
    ],

    Target: ["newfront", "newback", "curfront", "incog"/*,"incogback"*/],

    Tsearch: ["sgoogle", "sbaidu", "sbing", "syandex", "syahoo", "swiki", "staobao", "samazon", "ssogou", "s360"],

    Isearch: ["sgoogleimage", "sbaiduimage", "stineyeimage"],

    Chromepage: ["crdownloads", "crhistory", "crbookmarks", "crextensions", "crsettings", "crflags"],

    Position: ["chrome", "right", "left", "head", "last"],

    Pinned: ["unpinned", "pinned"],

    Capturetype: ["jpeg", "png"],

    Closeopts: ["close", "unclose"],

    Closesel: ["chrome", "left", "right"]

};

var config, isRocker = false;
var cmgtest = false;
if (!localStorage.getItem("config")) {
    config = JSON.parse(JSON.stringify(defaultConfig));
    localStorage.setItem("config", JSON.stringify(defaultConfig));
}
else {
    config = JSON.parse(localStorage.getItem("config"))
}
config.extid = chrome.runtime.id ? chrome.runtime.id : "none";

if (!localStorage.getItem("cmfirst") || JSON.parse(localStorage.getItem("openoptspage"))) {
    chrome.windows.getAll({populate: true}, function (windows) {
        for (var i = 0; i < windows.length; i++) {
            for (var ii = 0; ii < windows[i].tabs.length; ii++) {
                try {
                    if (windows[i]) {
                        chrome.tabs.executeScript(windows[i].tabs[ii].id, {
                            file: "js/jq.js",
                            allFrames: true,
                            runAt: "document_start"
                        }, function () {
                            if (windows[i]) {
                                chrome.tabs.executeScript(windows[i].tabs[ii].id, {
                                    file: "js/event.js",
                                    allFrames: true,
                                    runAt: "document_start"
                                });
                            }
                        });
                    }
                } catch (e) {
                    console.log(e);
                }
            }
        }
    });

    //chrome.tabs.create({url: "options.html", active: true});
    openOptions();
    localStorage.setItem("cmfirst", 1);
    localStorage.setItem("openoptspage", false);
    //if(config.openoptspage){delete config.openoptspage;}
}

/*check config elements*/
var checksave = false;
var notifiEnable = false;
if (config.normal.minilength === undefined) {
    notifiEnable = true;
}
for (var i in defaultConfig.normal) {
    if (config.normal[i] === undefined) {
        config.normal[i] = defaultConfig.normal[i];
        checksave = true;
    }
}

if (config.scroll === undefined) {
    config.scroll = {};
    config.normal.scroll = false;
    checksave = true;
}
for (var i in defaultConfig.scroll) {
    if (config.scroll[i] === undefined) {
        config.scroll[i] = defaultConfig.scroll[i];
        checksave = true;
    }
}
if (config.gesture.gesture === undefined) {
    checksave = true;
    var _cgesture = config.gesture;
    config.gesture = {};
    for (var i in defaultConfig.gesture) {//alert(config.gesture)
        config.gesture[i] = defaultConfig.gesture[i];
    }
    config.gesture.gesture = _cgesture;

    var _ctext = config.text;
    var _clink = config.link;
    var _cimage = config.image;
    config.drag = {}
    for (var i in defaultConfig.drag) {
        config.drag[i] = defaultConfig.drag[i];
        checksave = true;
    }
    config.drag.text = _ctext;
    config.drag.link = _clink;
    config.drag.image = _cimage;
    delete config.text;
    delete config.link;
    delete config.image;

    config.scrollgesture = defaultConfig.scrollgesture;
}

if (config.drag.draginput === undefined) {
    config.drag.draginput = defaultConfig.drag.draginput;
    checksave = true;
}
if (config.drag.setdragurl === undefined) {
    config.drag.setdragurl = defaultConfig.drag.setdragurl;
    checksave = true;
}

if (config.gesture.geskey === undefined) {
    config.gesture.geskey = defaultConfig.gesture.geskey;
    config.gesture.stenable = defaultConfig.gesture.stenable;
    config.gesture.gholdkey = defaultConfig.gesture.gholdkey;
    config.drag.imgfirstcheck = defaultConfig.drag.imgfirstcheck;
    config.drag.imgfirst = defaultConfig.drag.imgfirst;
    config.drag.dholdkey = defaultConfig.drag.dholdkey;
    checksave = true;
}

if (!config.strokegesture) {
    config.strokegesture = {};
    config.strokegesture = defaultConfig.strokegesture;

    config.scrollgesture = {};
    config.scrollgesture = defaultConfig.scrollgesture;
    checksave = true;
}

/**/

if (!config.cfgver) {
    var posvalue = config.normal.newtabposition;

    function upgrade2(obj1, obj2) {
        for (var i = 0; i < config[obj1][obj2].length; i++) {
            var cfg2gesture = config[obj1][obj2][i];
            if (cfg2gesture.action == "G_reclosedtab") {
                cfg2gesture.moreTarget = "newfront";
                cfg2gesture.morePosition = "chrome";
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.moreDes = CMi18n("G_reclosedtab");
            }
            else if (cfg2gesture.action == "G_crxsettings") {
                cfg2gesture.moreDes = CMi18n("G_crxsettings");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newfront";
            }
            else if (cfg2gesture.action == "G_newusertab") {
                cfg2gesture.moreName = config[obj1][obj2][i].more1;
                cfg2gesture.moreURL = config[obj1][obj2][i].more2;
                cfg2gesture.moreDes = CMi18n("valuenewusertab");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newfront";
            }
            else if (cfg2gesture.action == "G_newusertabback") {
                cfg2gesture.action = "G_newusertab";
                cfg2gesture.moreName = config[obj1][obj2][i].more1;
                cfg2gesture.moreURL = config[obj1][obj2][i].more2;
                cfg2gesture.moreDes = CMi18n("valuenewusertab");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newback";
            }
            else if (cfg2gesture.action == "G_newusertabcur") {
                cfg2gesture.action = "G_newusertab";
                cfg2gesture.moreName = config[obj1][obj2][i].more1;
                cfg2gesture.moreURL = config[obj1][obj2][i].more2;
                cfg2gesture.moreDes = CMi18n("valuenewusertab");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "curfront";
            }
            else if (cfg2gesture.action == "G_settings"
                || cfg2gesture.action == "G_downloads"
                || cfg2gesture.action == "G_bookmarks"
                || cfg2gesture.action == "G_history") {
                cfg2gesture.moreDes = CMi18n("valuechromepage");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newfront";
                cfg2gesture.moreChromepage = "cr" + cfg2gesture.action.substr(2);
                cfg2gesture.action = "G_chromepage";
            }
            else if (cfg2gesture.action == "G_newtab") {
                cfg2gesture.action = "G_newtab";
                cfg2gesture.moreDes = CMi18n("G_newtab");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newfront";
            }
            else if (cfg2gesture.action == "G_newtabback") {
                cfg2gesture.action = "G_newtab";
                cfg2gesture.moreDes = CMi18n("G_newtab");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newback";
            }
            else if (cfg2gesture.action == "G_viewsource") {
                cfg2gesture.action = "G_viewsource";
                cfg2gesture.moreDes = CMi18n("G_viewsource");
                cfg2gesture.morePinned = "unpinned";
                cfg2gesture.morePosition = posvalue;
                cfg2gesture.moreTarget = "newfront";
            }
            else if (cfg2gesture.action == "G_userscript") {
                cfg2gesture.action = "G_userscript";
                cfg2gesture.moreName = cfg2gesture.more1;
                cfg2gesture.moreScript = cfg2gesture.more2;
                cfg2gesture.moreDes = cfg2gesture.des;
            }


            if (config[obj1][obj2][i].more1) {
                delete config[obj1][obj2][i].more1
            }
            if (config[obj1][obj2][i].more2) {
                delete config[obj1][obj2][i].more2
            }
            if (config[obj1][obj2][i].des) {
                delete config[obj1][obj2][i].des
            }

        }

    }

    upgrade2("gesture", "gesture");
    upgrade2("scrollgesture", "sgsleft");
    upgrade2("scrollgesture", "sgsright");
    upgrade2("strokegesture", "strleft");
    upgrade2("strokegesture", "strright");

    for (var i = 0; i < config.drag.text.length; i++) {
        var cfg2text = config.drag.text[i];
        if (config.drag.text[i].action == "T_gsearch") {
            cfg2text.action = "T_search";
            cfg2text.moreDes = CMi18n("valuetsearch") + "(" + CMi18n("newfront") + ")";
            cfg2text.morePinned = "unpinned";
            cfg2text.morePosition = "chrome";
            cfg2text.moreTarget = "newfront";
            cfg2text.moreTsearch = "sgoogle";
        }
        else if (config.drag.text[i].action == "T_gsearchback") {
            cfg2text.action = "T_search";
            cfg2text.moreDes = CMi18n("valuetsearch") + "(" + CMi18n("newback") + ")";
            cfg2text.morePinned = "unpinned";
            cfg2text.morePosition = "chrome";
            cfg2text.moreTarget = "newback";
            cfg2text.moreTsearch = "sgoogle";
        }
        else if (config.drag.text[i].action == "T_searchuser") {
            cfg2text.action = "T_searchuser";
            cfg2text.moreDes = config.drag.text[i].des;
            cfg2text.moreName = config.drag.text[i].more1;
            cfg2text.morePinned = "unpinned";
            cfg2text.morePosition = "chrome";
            cfg2text.moreTarget = "newfront";
            cfg2text.moreURL = config.drag.text[i].more2;
        }
        else if (config.drag.text[i].action == "T_searchuserback") {
            cfg2text.action = "T_searchuser";
            cfg2text.moreDes = config.drag.text[i].des;
            cfg2text.moreName = config.drag.text[i].more1;
            cfg2text.morePinned = "unpinned";
            cfg2text.morePosition = "chrome";
            cfg2text.moreTarget = "newback";
            cfg2text.moreURL = config.drag.text[i].more2;
        }
        else if (config.drag.text[i].action == "T_sbaidu"
            || config.drag.text[i].action == "T_sbing"
            || config.drag.text[i].action == "T_syandex"
            || config.drag.text[i].action == "T_syahoo"
            || config.drag.text[i].action == "T_swiki"
            || config.drag.text[i].action == "T_staobao"
            || config.drag.text[i].action == "T_samazon") {
            cfg2text.action = "T_search";
            cfg2text.moreDes = CMi18n("valuetsearch") + "(" + CMi18n("newfront") + ")";
            cfg2text.morePinned = "unpinned";
            cfg2text.morePosition = "chrome";
            cfg2text.moreTarget = "newfront";
            cfg2text.moreTsearch = "s" + config.drag.text[i].action.substr(3);//"sgoogle";
        }
        else if (config.drag.text[i].action == "T_sbaiduback"
            || config.drag.text[i].action == "T_sbingback"
            || config.drag.text[i].action == "T_syandexback"
            || config.drag.text[i].action == "T_syahooback"
            || config.drag.text[i].action == "T_swikiback"
            || config.drag.text[i].action == "T_staobaoback"
            || config.drag.text[i].action == "T_samazonback") {
            cfg2text.action = "T_search";
            cfg2text.moreDes = CMi18n("valuetsearch") + "(" + CMi18n("newback") + ")";
            cfg2text.morePinned = "unpinned";
            cfg2text.morePosition = "chrome";
            cfg2text.moreTarget = "newback";
            cfg2text.moreTsearch = "s" + config.drag.text[i].action.substr(3, config.drag.text[i].action.length - 7);//"sgoogle";
        }

        if (config.drag.text[i].more1) {
            delete config.drag.text[i].more1
        }
        if (config.drag.text[i].more2) {
            delete config.drag.text[i].more2
        }
        if (config.drag.text[i].des) {
            delete config.drag.text[i].des
        }

    }

    for (var i = 0; i < config.drag.link.length; i++) {
        var cfg2link = config.drag.link[i];
        if (config.drag.link[i].action == "L_openback") {
            cfg2link.action = "L_open";
            cfg2link.moreDes = CMi18n("L_open") + "(" + CMi18n("newback") + ")";//"打开链接",
            cfg2link.morePinned = "unpinned";
            cfg2link.morePosition = "chrome";
            cfg2link.moreTarget = "newback";
        }
        else if (config.drag.link[i].action == "L_open") {
            cfg2link.action = "L_open";
            cfg2link.moreDes = CMi18n("L_open") + "(" + CMi18n("newfront") + ")";//"打开链接",
            cfg2link.morePinned = "unpinned";
            cfg2link.morePosition = "chrome";
            cfg2link.moreTarget = "newfront";
        }
        if (config.drag.link[i].more1) {
            delete config.drag.link[i].more1
        }
        if (config.drag.link[i].more2) {
            delete config.drag.link[i].more2
        }
        if (config.drag.link[i].des) {
            delete config.drag.link[i].des
        }
    }

    for (var i = 0; i < config.drag.image.length; i++) {
        var cfg2image = config.drag.image[i];
        if (config.drag.image[i].action == "I_open") {
            cfg2image.action = "I_open";
            cfg2image.moreDes = CMi18n("I_open") + "(" + CMi18n("newfront") + ")";//"打开链接",
            cfg2image.morePinned = "unpinned";
            cfg2image.morePosition = "chrome";
            cfg2image.moreTarget = "newfront";
        }
        else if (config.drag.image[i].action == "I_openback") {
            cfg2image.action = "I_open";
            cfg2image.moreDes = CMi18n("I_open") + "(" + CMi18n("newback") + ")";//"打开链接",
            cfg2image.morePinned = "unpinned";
            cfg2image.morePosition = "chrome";
            cfg2image.moreTarget = "newback";
        }
        else if (config.drag.image[i].action == "I_sgoogle"
            || config.drag.image[i].action == "I_sbaidu"
            || config.drag.image[i].action == "I_stineye") {
            cfg2image.action = "I_search";
            cfg2image.moreDes = CMi18n("valueisearch");//"打开链接",
            cfg2image.morePinned = "unpinned";
            cfg2image.morePosition = "chrome";
            cfg2image.moreTarget = "newfront";
            cfg2image.moreIsearch = config.drag.image[i].action.substr(2) + "image";
        }
        else if (config.drag.image[i].action == "I_sgoogleback"
            || config.drag.image[i].action == "I_sbaiduback"
            || config.drag.image[i].action == "I_stineyeback") {
            cfg2image.action = "I_search";
            cfg2image.moreDes = CMi18n("valueisearch");//"打开链接",
            cfg2image.morePinned = "unpinned";
            cfg2image.morePosition = "chrome";
            cfg2image.moreTarget = "newback";
            cfg2image.moreIsearch = config.drag.image[i].action.substr(2, config.drag.image[i].action.length - 6) + "image";
        }
        if (config.drag.image[i].more1) {
            delete config.drag.image[i].more1
        }
        if (config.drag.image[i].more2) {
            delete config.drag.image[i].more2
        }
        if (config.drag.image[i].des) {
            delete config.drag.image[i].des
        }

    }

    config.cfgver = 2;
    checksave = true;
}

if (config.cfgver < 2.1) {
    for (var i = 0; i < config.gesture.gesture.length; i++) {
        if (config.gesture.gesture[i].action == "G_close") {
            if (config.normal.lasttab) {
                config.gesture.gesture[i].moreCloseopts = "unclose";
            }
            else {
                config.gesture.gesture[i].moreCloseopts = "close";
            }
            config.gesture.gesture[i].moreCloseurl = "chrome://newtab/";
            config.gesture.gesture[i].moreClosesel = "chrome";
        }
    }
    config.cfgver = 2.1;
    checksave = true;
}

if (config.cfgver < 2.2) {
    config.others = {};
    config.others.tuilink = false;
    config.cfgver = 2.2;
    checksave = true;
}

if (config.cfgver < 2.3) {
    for (i = 0; i < config.gesture.gesture.length; i++) {
        if (config.gesture.gesture[i].action == "G_close") {
            config.gesture.gesture[i].moreCloseopts = "close";
            config.gesture.gesture[i].moreClosesel = "chrome";
            config.gesture.gesture[i].moreCloseurl = "chrome://newtab/";
        }
    }
    config.cfgver = 2.3;
    checksave = true;
}

if (checksave) {
    localStorage.setItem("config", JSON.stringify(config));
    checksave = false;
    if (config.sync) {//sync config now
        chrome.storage.sync.set(config, function () {
        });
    }
}
/**/


/*sync*/
//config=JSON.parse(localStorage.getItem("config"));
if (!config.sync) {
    chrome.storage.sync.get(null, function (items) {
        if (!items.sync) {
            config.sync = true;
            localStorage.setItem("config", JSON.stringify(config));
            chrome.storage.sync.set(config, function () {
            });
        }
        else {
            chrome.storage.sync.get(null, function (items) {
                localStorage.setItem("config", JSON.stringify(items))
            });
        }
    })
}
else if (config.sync == "local") {//fix config import
    config.sync = true;
    chrome.storage.sync.set(config, function () {
    });
}
else {
    chrome.storage.sync.get(null, function (items) {
        localStorage.setItem("config", JSON.stringify(items))
    })
}
config = JSON.parse(localStorage.getItem("config"));
/**/

/*for test notifi*/
if (cmgtest) {
    chrome.runtime.onInstalled.addListener(function (details) {
        if (details.reason == "update") {
            var notification = webkitNotifications.createNotification(
                'icon.png',  // icon url - can be relative
                "CrxMouse Beta更新提示",  // notification title
                "1,新增,添加书签操作."  // notification body text
            );
            notification.show();
        }
    })
}

/**/
/*notifi*/
var updatetext = "";
if (window.navigator.language == "zh-CN") {
    updatetext = "Gestures for Chrome(TM)汉化版 已经更新,并更名为CrxMouse."
}
else if (window.navigator.language == "zh-TW") {
    updatetext = "Gestures for Chrome(TM)繁體版已經更新,並更名為CrxMouse."
}
else {
    updatetext = "Gestures for Chrome(TM) Plus has been updated,and renamed to CrxMouse."
}
//alert(notifiEnable)
if (notifiEnable) {
    chrome.runtime.onInstalled.addListener(function (details) {
        if (details.reason == "update") {
            var notification = webkitNotifications.createNotification(
                'icon.png',  // icon url - can be relative
                "CrxMouse",  // notification title
                updatetext  // notification body text
            );
            notification.show();
        }
    });
}

//alert(chrome.runtime.id)	
//alert(chrome.runtime.getManifest().version)


/*Desktop Notifications*/
//var thisnotifinum=2;
//var notifinum=JSON.parse(localStorage.getItem("notifi"));
//if(!notifinum){
//	var notification = webkitNotifications.createHTMLNotification('html/notifi.html');
//	notification.show();
//	localStorage.setItem("notifi",1);
//	}
//else if(thisnotifinum>notifinum){
//	var notification = webkitNotifications.createHTMLNotification('html/notifi.html');
//	notification.show();
//	localStorage.setItem("notifi",thisnotifinum);
//	}

/*bookmarks*/
//chrome.bookmarks.getTree(function(BookmarkParentNode){
//	chrome.bookmarks.getSubTree(BookmarkParentNode[0].children[0].id,function(crxmouseNode){
//		for(var i=0;i<crxmouseNode.length;i++){
//			//if(crxmouseNode[i].title=="CrxMouse"){alert(crxmouseNode[i].id);}
//			if(i==crxmouseNode.length-1&&crxmouseNode[i].title!="CrxMouse"){
//				chrome.bookmarks.create({parentId:BookmarkParentNode[0].children[0].id,title:"CrxMouse"},function(createdNode){alert(createdNode.id)})
//				}
//			}
//		})
//	})


/*bookmarks end*/

var closedTabs = {};
var closedTabsId = [];
var lastTabs = {};
var lastTabsId = [];
var curId;

chrome.tabs.onRemoved.addListener(function (tabId, removeInfo) {
    closedTabs["id" + tabId] = lastTabs["id" + tabId];
    closedTabsId.push(lastTabs["id" + tabId]);
    for (id in lastTabsId) {
        if (lastTabsId[id].id == tabId) {
        }
    }

});
chrome.tabs.onUpdated.addListener(function (tabId, changeInfo, tab) {
    lastTabs["id" + tab.id] = tab;
    var _flag = true;
    for (var i = 0; i < lastTabsId.length; i++) {
        if (lastTabsId[i].id == tabId) {
            _flag = false;
            lastTabsId[i] = tab;
            break;
        }
    }
    if (_flag) {
        lastTabsId.push(tab)
    }
});

var actionFn = {
    needCurTab: false,
    needCurWindow: false,
    needCurWindows: false,
    needOpener: false,
    curTab: null,
    curWindow: null,
    curWindows: null,
    toIndex: null,
    request: null,
    curWindowState: [],

    AllCreate: function () {
        if (actionFn.newtabType == "create") {
            chrome.tabs.create({
                url: actionFn.URL,
                selected: actionFn.newtabSel,
                index: actionFn.toIndex,
                pinned: actionFn.pinned
            });
        }
        else if (actionFn.newtabType == "incog") {
            chrome.windows.create({url: actionFn.URL, incognito: true})
        }
        else {
            chrome.tabs.update({url: actionFn.URL, pinned: actionFn.pinned});
        }
        actionFn.URL = null;
    },

    G_reclosedtab: function () {
        var _closedtab = closedTabsId[closedTabsId.length - 1];
        if (_closedtab.id) {
            if (actionFn.newtabType == "create") {
                chrome.tabs.create({
                    url: _closedtab.url,
                    selected: actionFn.newtabSel,
                    index: actionFn.toIndex,
                    pinned: actionFn.pinned
                });
            }
            else {
                chrome.tabs.update({url: _closedtab.url, pinned: actionFn.pinned});
            }
            closedTabsId.length = closedTabsId.length - 1;
        }
    },
    G_capture: function () {
        window.setTimeout(_capturedelay, 100);
        function _capturedelay() {
            chrome.tabs.captureVisibleTab({format: actionFn.request.moreCapturetype}, function (dataUrl) {
                if (actionFn.newtabType == "create") {
                    chrome.tabs.create({url: dataUrl, selected: true, index: actionFn.toIndex})
                }
                else {
                    chrome.tabs.update({url: dataUrl, pinned: actionFn.pinned});
                }
            })
        }
    },

    G_reload: function () {
        chrome.tabs.reload({bypassCache: true});
    },
    G_reloadclear: function () {
        chrome.tabs.reload({bypassCache: false});
    },
    G_newwindow: function () {
        chrome.windows.create({focused: true})
    },
    G_incognito: function () {
        chrome.windows.create({incognito: true})
    },

    /**/
    G_close: function () {
        if (actionFn.request.moreCloseopts == "unclose" && actionFn.curWindow.tabs.length == 1) {
            chrome.tabs.create({url: actionFn.request.moreCloseurl, selected: false}, function (tab) {
                chrome.tabs.remove(actionFn.curTab.id, function () {
                });
            });
            return;
        }
        chrome.tabs.remove(actionFn.curTab.id, function () {
        });
        if (actionFn.curTab.index != 0 && actionFn.request.moreClosesel == "left") {
            for (var i in actionFn.curWindow.tabs) {
                if (actionFn.curWindow.tabs[i].index == actionFn.curTab.index - 1) {
                    //chrome.tabs.remove(actionFn.curWindow.tabs[i].id,function(){});
                    chrome.tabs.update(actionFn.curWindow.tabs[i].id, {active: true});
                    break;
                }
            }
        }
        else if (actionFn.curTab.index != actionFn.curWindow.tabs.length - 1 && actionFn.request.moreClosesel == "right") {
            for (var i in actionFn.curWindow.tabs) {
                if (actionFn.curWindow.tabs[i].index == actionFn.curTab.index + 1) {
                    chrome.tabs.update(actionFn.curWindow.tabs[i].id, {active: true});
                    break;
                }
            }
        }
    },

    G_movetowindow: function () {
        chrome.windows.create({focused: true}, function (window) {
            chrome.tabs.move(actionFn.curTab.id, {index: -1, windowId: window.id}, function (tab) {
            });
            chrome.tabs.remove(window.tabs[0].id);
        })
    },
    G_closeothers: function () {
        for (var i in actionFn.curWindow.tabs) {
            var aTab = actionFn.curWindow.tabs[i];
            if ((!aTab.highlighted || !aTab.active) && !aTab.pinned) {
                chrome.tabs.remove(aTab.id)
            }
        }
    },
    G_closelefttabs: function () {
        for (var i = 0 in actionFn.curWindow.tabs) {
            var aTab = actionFn.curWindow.tabs[i];
            if ((aTab.index < actionFn.curTab.index) && !aTab.pinned) {
                chrome.tabs.remove(aTab.id)
            }
        }
    },
    G_closerighttabs: function () {
        for (var i = 0 in actionFn.curWindow.tabs) {
            var aTab = actionFn.curWindow.tabs[i];
            if ((aTab.index > actionFn.curTab.index) && !aTab.pinned) {
                chrome.tabs.remove(aTab.id)
            }
        }
    },
    G_closewindows: function () {
        for (var _id in actionFn.curWindows) {
            chrome.windows.remove(actionFn.curWindows[_id].id)
        }
    },
    G_closewindow: function () {
        chrome.windows.remove(actionFn.curWindow.id)
    },
    G_copytab: function () {
        chrome.tabs.duplicate(actionFn.curTab.id)
    },
    G_windowmax: function () {
        if (actionFn.curWindow.state == "maximized") {
            chrome.windows.update(actionFn.curWindow.id, {state: "normal"});
        }
        else {
            chrome.windows.update(actionFn.curWindow.id, {state: "maximized"});
        }
    },
    G_windowmin: function () {
        chrome.windows.update(actionFn.curWindow.id, {state: "minimized"});
    },
    G_pin: function () {
        if (actionFn.curTab.pinned) {
            chrome.tabs.update({pinned: false});
        }
        else {
            chrome.tabs.update({pinned: true});
        }
    },
    G_splitTab: function () {
        chrome.windows.create({tabId: actionFn.curTab.id, focused: true}, function (window) {
            var tabs = [];
            for (var i = 0 in actionFn.curWindow.tabs) {
                var aTab = actionFn.curWindow.tabs[i];
                if (aTab.index > actionFn.curTab.index) {
                    tabs.push(aTab.id);
                }
            }
            chrome.tabs.move(tabs, {windowId: window.id, index: -1});
        });

    },
    G_mergeTab: function () {
        chrome.windows.getAll({populate: true, windowTypes: ["normal"]}, function (wins) {
            var winLen = wins.length;
            if (winLen > 1) {
                for (var i = 1; i < winLen; i++) {
                    var tabLen = wins[i].tabs.length, tabs = [];
                    for (var j = 0; j < tabLen; j++) {
                        tabs.push(wins[i].tabs[j].id);
                    }
                    chrome.tabs.move(tabs, {windowId: wins[0].id, index: -1}, function () {
                        chrome.windows.remove(wins[i].id);
                    });
                }
            }
        })

    },
    G_fullscreen: function () {
        if (actionFn.curWindow.state == "fullscreen") {
            chrome.windows.update(actionFn.curWindow.id, {state: actionFn.curWindowState[actionFn.curWindow.id]})
        }
        else {
            actionFn.curWindowState[actionFn.curWindow.id] = actionFn.curWindow.state;
            chrome.windows.update(actionFn.curWindow.id, {state: "fullscreen"})
        }
    },
    G_lefttab: function () {
        if (actionFn.curTab.index != 0) {
            for (var i in actionFn.curWindow.tabs) {
                if (actionFn.curWindow.tabs[i].index == actionFn.curTab.index - 1) {
                    if (isRocker) {
                        chrome.tabs.sendMessage(actionFn.curWindow.tabs[i].id, {cmd: "disabledRC"});
                        isRocker = false; //restore the status
                    }
                    chrome.tabs.update(actionFn.curWindow.tabs[i].id, {active: true});
                    break;
                }
            }
        }
        else {
            chrome.tabs.update(actionFn.curWindow.tabs[actionFn.curWindow.tabs.length - 1].id, {active: true})
        }
    },
    G_righttab: function () {
        if (actionFn.curTab.index != actionFn.curWindow.tabs.length - 1) {
            for (var i in actionFn.curWindow.tabs) {
                if (actionFn.curWindow.tabs[i].index == actionFn.curTab.index + 1) {
                    chrome.tabs.update(actionFn.curWindow.tabs[i].id, {active: true});
                    break;
                }
            }
        }
        else {
            chrome.tabs.update(actionFn.curWindow.tabs[0].id, {active: true});
        }
    },
    G_firsttab: function () {
        chrome.tabs.update(actionFn.curWindow.tabs[0].id, {active: true});
    },
    G_lasttab: function () {
        chrome.tabs.update(actionFn.curWindow.tabs[actionFn.curWindow.tabs.length - 1].id, {active: true});
    },
    G_reloadall: function () {
        for (var i in actionFn.curWindow.tabs) {
            chrome.tabs.reload(actionFn.curWindow.tabs[i].id, {bypassCache: false});
        }
    },
    G_copyurl: function () {
        for (var i in actionFn.curWindow.tabs) {
            if (actionFn.curWindow.tabs[i].active) {
                var _text = document.createElement("textarea");
                _text.id = "crxmousetextarea";
                document.body.appendChild(_text);
                clipobj = document.getElementById("crxmousetextarea");
                clipobj.value = actionFn.curWindow.tabs[i].url;
                clipobj.select();
                document.execCommand('copy', false, null);
                clipobj.parentNode.removeChild(clipobj);
                break;
            }
        }
    },
    G_copytitle: function () {
        for (var i in actionFn.curWindow.tabs) {
            if (actionFn.curWindow.tabs[i].active) {
                var _text = document.createElement("textarea");
                _text.id = "crxmousetextarea";
                document.body.appendChild(_text);
                clipobj = document.getElementById("crxmousetextarea");
                clipobj.value = actionFn.curWindow.tabs[i].title;
                clipobj.select();
                document.execCommand('copy', false, null);
                clipobj.parentNode.removeChild(clipobj);
                break;
            }
        }
    },
    T_copytext: function () {
        for (var i in actionFn.curWindow.tabs) {
            if (actionFn.curWindow.tabs[i].active) {
                var _text = document.createElement("textarea");
                _text.id = "crxmousetextarea";
                document.body.appendChild(_text);
                clipobj = document.getElementById("crxmousetextarea");
                clipobj.value = actionFn.request.seltext;
                clipobj.select();
                document.execCommand('copy', false, null);
                clipobj.parentNode.removeChild(clipobj);
                break;
            }
        }
    },
    L_copytext: function () {
        for (var i in actionFn.curWindow.tabs) {
            if (actionFn.curWindow.tabs[i].active) {
                var _text = document.createElement("textarea");
                _text.id = "crxmousetextarea";
                document.body.appendChild(_text);
                clipobj = document.getElementById("crxmousetextarea");
                clipobj.value = actionFn.request.seltext;
                clipobj.select();
                document.execCommand('copy', false, null);
                clipobj.parentNode.removeChild(clipobj);
                break;
            }
        }
    },
    L_copyurl: function () {
        for (var i in actionFn.curWindow.tabs) {
            if (actionFn.curWindow.tabs[i].active) {
                var _text = document.createElement("textarea");
                _text.id = "crxmousetextarea";
                document.body.appendChild(_text);
                clipobj = document.getElementById("crxmousetextarea");
                clipobj.value = actionFn.request.sellink;
                clipobj.select();
                document.execCommand('copy', false, null);
                clipobj.parentNode.removeChild(clipobj);
                break;
            }
        }
    },
    I_save: function () {
//		chrome.downloads.download({url:actionFn.request.selimg/*,saveAs:true*/},function(id){
        var a = $("<a>").attr("href", actionFn.request.selimg).attr("download", actionFn.request.selimg.split('/').pop()).appendTo("body");
        a[0].click();
        a.remove();
        chrome.tabs.sendMessage(actionFn.curTab.id, {notifitype: "isave", notifitext: CMi18n("downimgtooltip")});
//			});
    },
    I_saveback: function () {
//        chrome.downloads.download({url: actionFn.request.selimg, saveAs: true}, function (id) {
        var a = $("<a>").attr("href", actionFn.request.selimg).attr("download", actionFn.request.selimg.split('/').pop()).appendTo("body");
        a[0].click();
        a.remove();
        chrome.tabs.sendMessage(actionFn.curTab.id, {notifitype: "isaveback", notifitext: CMi18n("downimgtooltip")});
//        });
    },
    I_copyurl: function () {
        for (var i in actionFn.curWindow.tabs) {
            if (actionFn.curWindow.tabs[i].active) {
                var _text = document.createElement("textarea");
                _text.id = "crxmousetextarea";
                document.body.appendChild(_text);
                clipobj = document.getElementById("crxmousetextarea");
                clipobj.value = actionFn.request.selimg;
                clipobj.select();
                document.execCommand('copy', false, null);
                clipobj.parentNode.removeChild(clipobj);
                break;
            }
        }
    },
    G_stopall: function () {
        chrome.windows.getAll({populate: true}, function (windows) {
            for (var i = 0; i < windows.length; i++) {
                for (var ii = 0; ii < windows[i].tabs.length; ii++) {
                    chrome.tabs.executeScript(windows[i].tabs[ii].id, {
                        code: "window.stop();",
                        allFrames: true,
                        runAt: "document_start"
                    }, function () {
                    })
                }
            }
        })
    },

    G_copyaslink: function () {
        var _text = document.createElement("textarea");
        _text.id = "crxmousetextarea";
        document.body.appendChild(_text);
        clipobj = document.getElementById("crxmousetextarea");
        clipobj.value += "<a href='" + actionFn.curTab.url + "'>" + actionFn.curTab.title + "<\/a>";
        clipobj.select();
        document.execCommand('copy', false, null);
        clipobj.parentNode.removeChild(clipobj);
    },
    L_copyaslink: function () {
        var _text = document.createElement("textarea");
        _text.id = "crxmousetextarea";
        document.body.appendChild(_text);
        clipobj = document.getElementById("crxmousetextarea");
        clipobj.value += "<a href='" + actionFn.request.sellink + "'>" + actionFn.request.seltext + "<\/a>";
        clipobj.select();
        document.execCommand('copy', false, null);
        clipobj.parentNode.removeChild(clipobj);
    },
    G_copyuser: function () {
        var _text = document.createElement("textarea");
        _text.id = "crxmousetextarea";
        document.body.appendChild(_text);
        clipobj = document.getElementById("crxmousetextarea");

        function _CopyDateescape() {
            if (actionFn.request.moreCopystyle.indexOf("\\n") != -1) {
                actionFn.request.moreCopystyle = actionFn.request.moreCopystyle.replace("\\n", "\n");
                _CopyDateescape();
            }
        }

        _CopyDateescape();

        var _linkstyleobj = actionFn.request.moreCopystyle;

        function _linkstylefn() {
            if (_linkstyleobj.indexOf("%title") != -1 || _linkstyleobj.indexOf("%url") != -1) {
                _linkstyleobj = _linkstyleobj.replace("%title", actionFn.curTab.title).replace("%url", actionFn.curTab.url);
                _linkstylefn();
            }
        }

        _linkstylefn();
        /**/

        clipobj.value += _linkstyleobj;
        clipobj.select();
        document.execCommand('copy', false, null);
        clipobj.parentNode.removeChild(clipobj);
    },
    L_copyuser: function () {
        var _text = document.createElement("textarea");
        _text.id = "crxmousetextarea";
        document.body.appendChild(_text);
        clipobj = document.getElementById("crxmousetextarea");

        function _CopyDateescape() {
            if (actionFn.request.moreCopystyle.indexOf("\\n") != -1) {
                actionFn.request.moreCopystyle = actionFn.request.moreCopystyle.replace("\\n", "\n");
                _CopyDateescape();
            }
        }

        _CopyDateescape();

        var _linkstyleobj = actionFn.request.moreCopystyle;

        function _linkstylefn() {
            if (_linkstyleobj.indexOf("%title") != -1 || _linkstyleobj.indexOf("%url") != -1) {
                _linkstyleobj = _linkstyleobj.replace("%title", actionFn.request.seltext).replace("%url", actionFn.request.sellink);
                _linkstylefn();
            }
        }

        _linkstylefn();
        /**/

        clipobj.value += _linkstyleobj;
        clipobj.select();
        document.execCommand('copy', false, null);
        clipobj.parentNode.removeChild(clipobj);
    },
    _CheckURL: function (url) {
        if (url == "") {
            return false;
        }

        var url = url.toLowerCase();
        if ((url.indexOf("http://") != 0) &&
            (url.indexOf("https://") != 0) &&
            (url.indexOf("ftp://") != 0) &&
            (url.indexOf("chrome://") != 0) &&
            (url.indexOf("chrome-extension://") != 0)) {
            url = "http://" + url;
        }

        if (url.substr(0, 6) == "chrome") {
            return url;
        }
        var regexp = /^((chrome|chrome-extension|ftp|http(s)?):\/\/)([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
        if (regexp.test(url)) {
            return url;
        } else {
            return false
        }
    },
    G_openclipurl: function () {
        var _text = document.createElement("textarea");
        _text.id = "crxmousetextarea";
        document.body.appendChild(_text);
        clipobj = document.getElementById("crxmousetextarea");

        clipobj.focus();
        document.execCommand('paste', false, null);
        if (actionFn._CheckURL(clipobj.value)) {
            if (actionFn.newtabType == "create") {
                chrome.tabs.create({
                    url: actionFn._CheckURL(clipobj.value),
                    selected: actionFn.newtabSel,
                    index: actionFn.toIndex
                });
            }
            else {
                chrome.tabs.update({url: actionFn._CheckURL(clipobj.value)});
            }
        }
        clipobj.parentNode.removeChild(clipobj);
    },

    G_trynextto: function () {
        if (actionFn.newtabType == "create") {
            chrome.tabs.create({
                url: actionFn.request.transurl,
                selected: actionFn.newtabSel,
                index: actionFn.toIndex,
                pinned: actionFn.pinned
            });
        }
        else {
            chrome.tabs.update({url: actionFn.request.transurl, pinned: actionFn.pinned});
        }
    },
    G_tryprevto: function () {
        if (actionFn.newtabType == "create") {
            chrome.tabs.create({
                url: actionFn.request.transurl,
                selected: actionFn.newtabSel,
                index: actionFn.toIndex,
                pinned: actionFn.pinned
            });
        }
        else {
            chrome.tabs.update({url: actionFn.request.transurl, pinned: actionFn.pinned});
        }
    },
    //G_homePage:function(){
    //
    //},
    G_bookmark: function () {
        this.bookmark(actionFn.curTab.title, actionFn.curTab.url);
    },
    L_bookmark: function () {
        this.bookmark(actionFn.request.seltext, actionFn.request.sellink);
    },
    bookmark: function (bktitle, bkurl) {
        if (!bkurl) {
            return;
        }
        chrome.bookmarks.getTree(function (BookmarkParentNode) {
            for (var i = 0; i < BookmarkParentNode[0].children[0].children.length; i++) {
                if (BookmarkParentNode[0].children[0].children[i].children && BookmarkParentNode[0].children[0].children[i].title == "CrxMouse") {
                    if (BookmarkParentNode[0].children[0].children[i].children.length == 0) {
                        chrome.bookmarks.create({
                            parentId: BookmarkParentNode[0].children[0].children[i].id,
                            title: bktitle,
                            url: bkurl
                        }, function (createdNode) {
                            chrome.tabs.sendMessage(actionFn.curTab.id, {
                                notifitype: "bookmark",
                                notifitext: CMi18n("bookmarknotifi")
                            })
                        });
                    }
                    for (var ii = 0; ii < BookmarkParentNode[0].children[0].children[i].children.length; ii++) {
                        if (BookmarkParentNode[0].children[0].children[i].children[ii].url == bkurl) {
                            chrome.tabs.sendMessage(actionFn.curTab.id, {
                                notifitype: "bookmark",
                                notifitext: CMi18n("bookmarkfailnotifi")
                            }, function (response) {
                            });
                            break;
                        }
                        if (ii == BookmarkParentNode[0].children[0].children[i].children.length - 1 && BookmarkParentNode[0].children[0].children[i].children[ii].url != bkurl) {
                            chrome.bookmarks.create({
                                parentId: BookmarkParentNode[0].children[0].children[i].id,
                                title: bktitle,
                                url: bkurl
                            }, function (createdNode) {
                                chrome.tabs.sendMessage(actionFn.curTab.id, {
                                    notifitype: "bookmark",
                                    notifitext: CMi18n("bookmarknotifi")
                                });
                            });
                        }
                    }
                    break;
                }
                if (i == BookmarkParentNode[0].children[0].children.length - 1 && BookmarkParentNode[0].children[0].children[i].title != "CrxMouse") {
                    chrome.bookmarks.create({
                        parentId: BookmarkParentNode[0].children[0].id,
                        title: "CrxMouse"
                    }, function (createdNode) {
                    });
                }
            }
        })
    },
    G_BmManager: function () {
        chrome.tabs.create({url: "chrome://bookmarks"});
    },
    tablist: function () {

    }

};

chrome.extension.onMessage.addListener(
    function (request, sender, sendResponse) {
        config = JSON.parse(localStorage.getItem("config"));
        /**/
        actionFn.curTab = sender.tab;
        chrome.windows.get(sender.tab.windowId, {populate: true}, function (window) {
            actionFn.curWindow = window;
        });
        switch (request.type) {
            case "gesture":
            case "text":
            case "link":
            case "image":
                var _sentobj1;
                var _sentobj2 = request.type;
                if (_sentobj2 == "gesture") {
                    _sentobj1 = "gesture"
                } else {
                    _sentobj1 = "drag"
                }
                for (var i = 0; i < config[_sentobj1][_sentobj2].length; i++) {
                    if (request.direct == config[_sentobj1][_sentobj2][i].direct) {
                        sendResponse(config[_sentobj1][_sentobj2][i]);
                        return;
                    }
                }
                break;
            case "backToFn":
                actionFn.request = request;
                if (request.action == "L_openback" || "L_open" || "I_openback" || "I_open") {
                    actionFn.needOpener = true;
                }
                else {
                    actionFn.needOpener = false;
                }

                if (request.moreTarget) {
                    if (request.moreTarget == "newfront") {
                        actionFn.newtabType = "create";
                        actionFn.newtabSel = true;
                    }
                    else if (request.moreTarget == "newback") {
                        actionFn.newtabType = "create";
                        actionFn.newtabSel = false;
                    }
                    else if (request.moreTarget == "curfront") {
                        actionFn.newtabType = "update";
                        actionFn.newtabSel = true;
                    }
                    else if (request.moreTarget == "incog") {
                        actionFn.newtabType = "incog";
                        actionFn.newtabSel = true;
                    }
                    else if (request.moreTarget == "incogback") {
                        actionFn.newtabType = "incog";
                        actionFn.newtabSel = false;
                    }
                }

                if (request.morePinned == "pinned") {
                    actionFn.pinned = true;
                }
                else {
                    actionFn.pinned = false;
                }

                //if()
            function FnURL() {
                if (request.moreChromepage) {
                    switch (request.moreChromepage) {
                        case"crsettings":
                            actionFn.URL = "chrome://settings";
                            break;
                        case"crbookmarks":
                            actionFn.URL = "chrome://bookmarks";
                            break;
                        case"crdownloads":
                            actionFn.URL = "chrome://downloads";
                            break;
                        case"crextensions":
                            actionFn.URL = "chrome://extensions";
                            break;
                        case"crhistory":
                            actionFn.URL = "chrome://history";
                            break;
                        case"crflags":
                            actionFn.URL = "chrome://flags";
                            break;
                    }
                }

                if (request.moreTsearch) {
                    switch (request.moreTsearch) {
                        case"sgoogle":
                            actionFn.URL = "http://www.google.com/search?q=" + actionFn.request.seltext;
                            break;
                        case"sbaidu":
                            actionFn.URL = "http://www.baidu.com/s?wd=" + actionFn.request.seltext;
                            break;
                        case"syandex":
                            actionFn.URL = "http://www.yandex.com/yandsearch?text=" + actionFn.request.seltext;
                            break;
                        case"sbing":
                            actionFn.URL = "http://www.bing.com/search?q=" + actionFn.request.seltext;
                            break;
                        case"syahoo":
                            actionFn.URL = "http://search.yahoo.com/search?p=" + actionFn.request.seltext;
                            break;
                        case"swiki":
                            actionFn.URL = "http://en.wikipedia.org/w/index.php?search=" + actionFn.request.seltext;
                            break;
                        case"staobao":
                            actionFn.URL = "http://s.taobao.com/search?q=" + actionFn.request.seltext;
                            break;
                        case"samazon":
                            actionFn.URL = "http://www.amazon.com/s/&field-keywords=" + actionFn.request.seltext;
                            break;
                        case"ssogou":
                            actionFn.URL = "https://www.sogou.com/web?query=" + actionFn.request.seltext;
                            break;
                        case"s360":
                            actionFn.URL = "http://www.haosou.com/s?q=" + actionFn.request.seltext;
                            break;
                    }
                }

                if (request.moreIsearch) {
                    switch (request.moreIsearch) {
                        case"sgoogleimage":
                            actionFn.URL = "https://www.google.com/searchbyimage?image_url=" + actionFn.request.selimg;
                            break;
                        case"sbaiduimage":
                            actionFn.URL = "http://stu.baidu.com/i?objurl=" + encodeURIComponent(actionFn.request.selimg) + "&filename=&rt=0&rn=10&ftn=searchimage&ct=1&tn=baiduimagepc";
                            break;
                        case"stineyeimage":
                            actionFn.URL = "http://www.tineye.com/search?url=" + actionFn.request.selimg;
                            break;
                    }
                }

                if (request.action == "T_searchuser") {
                    actionFn.URL = actionFn.request.moreURL.replace("%s", actionFn.request.seltext);
                }
                else if (request.action == "I_searchuser") {
                    actionFn.URL = actionFn.request.moreURL.replace("%s", actionFn.request.selimg);
                }
                else if (request.action == "G_newtab") {
                    actionFn.URL = "chrome://newtab/"// actionFn.request.moreURL.replace("%s",actionFn.request.selimg);
                }
                else if (request.action == "G_newusertab") {
                    actionFn.URL = actionFn.request.moreURL;// actionFn.request.moreURL.replace("%s",actionFn.request.selimg);
                }
                else if (request.action == "G_viewsource") {
                    actionFn.URL = "view-source:" + actionFn.curTab.url;
                }
                else if (request.action == "G_crxsettings") {
                    actionFn.URL = "options.html";
                }
                else if (request.action == "L_open") {
                    actionFn.URL = actionFn.request.sellink;
                }
                else if (request.action == "I_open") {
                    actionFn.URL = actionFn.request.selimg;
                }

            }

                /**/
                actionFn.imageDownloadId = null;
                chrome.windows.getCurrent({populate: true}, function (window) {
                    var _curIndex, _toIndex;
                    //actionFn.curWindow = window;
                    for (var i in window.tabs) {
                        if (window.tabs[i].highlighted) {
                            _curIndex = window.tabs[i].index;
                            //actionFn.curTab = window.tabs[i];
                            actionFn.curTab = sender.tab;
                            break;
                        }
                    }

                    switch (request.morePosition/*config.normal.newtabposition*/) {
                        case"chrome":
                            if (!actionFn.needOpener) {
                                _toIndex = _curIndex + 1;
                                break;
                            }
                            for (var i = window.tabs.length - 1; i > 0; i--) {
                                if (window.tabs[i].openerTabId !== undefined && (window.tabs[i].openerTabId == window.tabs[_curIndex].id)) {
                                    _toIndex = i + 1;
                                    break;
                                }
                                else if (i == 1) {
                                    _toIndex = _curIndex + 1;
                                    break;
                                }
                            }
                            break;
                        case"left":
                            if (_curIndex == 0) {
                                _toIndex = 0;
                            } else {
                                _toIndex = _curIndex;
                            }
                            break;
                        case"right":
                            _toIndex = _curIndex + 1;
                            break;
                        case"head":
                            _toIndex = 0;
                            break;
                        case"last":
                            _toIndex = window.tabs.length;
                            break;
                    }
                    actionFn.toIndex = _toIndex;
                    FnURL();

                    //actionFn.L_boomark();return;
                    if (actionFn.URL) {
                        actionFn.AllCreate()
                    }
                    else {
                        actionFn[request.action]();
                    }

                });

                break;
            case "tipshow":
                var _tipobj1;
                var _tipobj2 = request.tiptype;
                if (_tipobj2 == "gesture") {
                    _tipobj1 = "gesture"
                } else {
                    _tipobj1 = "drag"
                }
                for (var i = 0; i < config[_tipobj1][_tipobj2].length; i++) {
                    if (request.direct == config[_tipobj1][_tipobj2][i].direct) {
                        if (config[_tipobj1][_tipobj2][i].moreDes) {
                            if (config[_tipobj1][_tipobj2][i].action.indexOf("newusertab") != -1) {
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", config[_tipobj1][_tipobj2][i].moreName),
                                    type: request.type
                                });
                            }
                            else if (config[_tipobj1][_tipobj2][i].action.indexOf("search") != -1) {
                                var _repname;
                                if (config[_tipobj1][_tipobj2][i].moreName) {
                                    _repname = config[_tipobj1][_tipobj2][i].moreName
                                }
                                else if (config[_tipobj1][_tipobj2][i].moreTsearch) {
                                    _repname = CMi18n(config[_tipobj1][_tipobj2][i].moreTsearch)
                                }
                                else if (config[_tipobj1][_tipobj2][i].moreIsearch) {
                                    _repname = CMi18n(config[_tipobj1][_tipobj2][i].moreIsearch)
                                }
                                //alert(config[_tipobj1][_tipobj2][i].moreDes+_repname+config[_tipobj1][_tipobj2][i].moreDes.replace("%name",_repname))
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", _repname).replace("%s", request.seltext.length > 10 ? request.seltext.substr(0, 10) + "..." : request.seltext),
                                    type: request.type
                                });
                            }

                            else if (config[_tipobj1][_tipobj2][i].action.indexOf("userscript") != -1) {
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", config[_tipobj1][_tipobj2][i].moreName),
                                    type: request.type
                                });
                            }
                            else if (config[_tipobj1][_tipobj2][i].action.indexOf("chromepage") != -1) {
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", CMi18n(config[_tipobj1][_tipobj2][i].moreChromepage)),
                                    type: request.type
                                });
                            }

                            else if (config[_tipobj1][_tipobj2][i].action.indexOf("copyuser") != -1) {
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", config[_tipobj1][_tipobj2][i].moreName),
                                    type: request.type
                                });
                            }

                            if (!request.seltext) {
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", config[_tipobj1][_tipobj2][i].more1),
                                    type: request.type
                                });
                            }
                            else {
                                sendResponse({
                                    moreDes: config[_tipobj1][_tipobj2][i].moreDes.replace("%name", config[_tipobj1][_tipobj2][i].more1).replace("%s", request.seltext.length > 10 ? request.seltext.substr(0, 10) + "..." : request.seltext),
                                    type: request.type
                                });
                            }
                        }
                        else {
                            if (!request.seltext) {
                                sendResponse({
                                    moreDes: CMi18n(config[_tipobj1][_tipobj2][i].action),
                                    type: request.type
                                })
                            }
                            else {
                                sendResponse({
                                    moreDes: CMi18n(config[_tipobj1][_tipobj2][i].action).replace("%s", request.seltext.length > 10 ? request.seltext.substr(0, 10) + "..." : request.seltext),
                                    type: request.type
                                })
                            }
                        }
                        break;
                    }
                    if (i == config[_tipobj1][_tipobj2].length - 1) {
                        sendResponse({moreDes: "none", type: request.type});
                        return;
                    }
                }
                //return;
                break;
            case "config":
                config = JSON.parse(localStorage.getItem("config"));
                config.extid = chrome.runtime.id ? chrome.runtime.id : "none";
                sendResponse(config);
                break;
            case "actioncfg":
                sendResponse(action);
                break;
            case "tablist":
                chrome.windows.getCurrent({populate: true}, function (window) {
                    var tbtabs = window.tabs;
                    var tbcurtab;
                    for (var i in window.tabs) {
                        if (window.tabs[i].highlighted) {
                            //_curIndex=window.tabs[i].index;
                            tbcurtab = window.tabs[i];
                            break;
                        }
                    }
                    chrome.tabs.sendMessage(tbcurtab.id, {
                        tabs: window.tabs,
                        curTab: tbcurtab,
                        type: "tablist"
                    }, function (response) {
                    })
                });
                break;
            case "tablistend":
                chrome.windows.getCurrent({populate: true}, function (window) {
                    chrome.tabs.query({windowId: window.id, index: request.tablistIndex}, function (tabs) {
                        chrome.tabs.update(tabs[0].id, {active: true});
                    })
                });
                break;
            case "mousestate":

                config.mouse = request.state;
                break;
            case "getmouse":
                sendResponse({mouse: config.mouse});
                break;
            case "scrollgesture":
                var _sgsId;
                if (request.UD == "up") {
                    _sgsId = 0;
                } else {
                    _sgsId = 1
                }
                sendResponse(config.scrollgesture[request.LR][_sgsId]);
                break;
            case "strokegesture":

                switch (request.hold) {
                    case"left":
                        sendResponse(config.strokegesture["str" + request.hold][request.LR - 1]);
                        break;
                    case"middle":
                        sendResponse(config.strokegesture["str" + request.hold][request.LR == 2 ? request.LR - 1 : request.LR]);
                        break;
                    case"right":
                        isRocker = true;
                        sendResponse(config.strokegesture["str" + request.hold][request.LR]);
                        break;
                }
                break;
            case "syncdown":
                /*sync*/
                config = JSON.parse(localStorage.getItem("config"));
                if (!config.sync) {
                    chrome.storage.sync.get(null, function (items) {
                        if (!items.sync) {
                            config.sync = true;
                            localStorage.setItem("config", JSON.stringify(config));
                            chrome.storage.sync.set(config, function () {
                            });
                        }
                        else {
                            chrome.storage.sync.get(null, function (items) {
                                localStorage.setItem("config", JSON.stringify(items))
                            });
                        }
                    })
                }
                else {
                    chrome.storage.sync.get(null, function (items) {
                        localStorage.setItem("config", JSON.stringify(items))
                    })
                }
                break;
            case "syncup":
                config = JSON.parse(localStorage.getItem("config"));
                if (config.sync) {
                    chrome.storage.sync.set(config);
                }
                break;
            case "syncclear":
                chrome.storage.sync.clear(function () {
                    chrome.storage.sync.set(JSON.parse(localStorage.getItem("config")));
                });
                break;
            case "fastSwitch":
                var direct = request.rv ? (!request.direction) : request.direction;
                chrome.windows.getCurrent({populate: true}, function (window) {
                    var curTab, windowTabs = window.tabs, curTansLen = windowTabs.length;
                    for (var i in windowTabs) {
                        if (window.tabs[i].active) {
                            curTab = window.tabs[i];
                        }
                    }
                    tabIndex = direct ? curTab.index + 1 : curTab.index - 1;
                    if (tabIndex === curTansLen) {
                        tabIndex = 0;
                    } else if (tabIndex === -1) {
                        tabIndex = curTansLen - 1;
                    }
                    chrome.tabs.update(window.tabs[tabIndex].id, {active: true});
                });
                break;
            case"zoom":
                chrome.tabs.getZoom(sender.tab.id, function (zoomFactor) {
                    if (!request.zoomIn) {
                        if (zoomFactor < 2) {
                            zoomFactor -= 0.15;
                        } else if (3 > zoomFactor && zoomFactor > 2) {
                            zoomFactor -= 0.5;
                        } else {
                            zoomFactor -= 1;
                        }
                    } else {
                        if (zoomFactor < 2) {
                            zoomFactor += 0.15;
                        } else if (3 > zoomFactor && zoomFactor > 2) {
                            zoomFactor += 0.5;
                        } else {
                            zoomFactor += 1;
                        }
                    }
                    chrome.tabs.setZoom(sender.tab.id, zoomFactor);
                });

        }

    });

function CMi18n(i18nstr) {
    var config;
    if (!localStorage.getItem("config")) {
        var config = JSON.parse(JSON.stringify(defaultConfig));
        localStorage.setItem("config", JSON.stringify(defaultConfig));
    }
    else {
        var config = JSON.parse(localStorage.getItem("config"))
    }

    if (!config.normal.language) {
        if (chrome.i18n.getMessage(i18nstr)) {
            return chrome.i18n.getMessage(i18nstr);
        }
        else {
            return null;
        }
    }
    else {
        if (chrome.i18n.getMessage("machine" + i18nstr)) {
            return chrome.i18n.getMessage("machine" + i18nstr)
        }
        else if (chrome.i18n.getMessage(i18nstr)) {
            return chrome.i18n.getMessage(i18nstr);
        }
        else {
            return null;
        }
    }
}

function openOptions() {
    var t = chrome.tabs;
    t.query({url: "chrome-extension://jlgkpaicikihijadgifklkbpdajbkhjo/options.html"}, function (tabs) {
        if (tabs && tabs.length !== 0) {
            t.update(tabs[0].id, {active: true});
        } else {
            t.create({url: "options.html"});
        }
    });
}