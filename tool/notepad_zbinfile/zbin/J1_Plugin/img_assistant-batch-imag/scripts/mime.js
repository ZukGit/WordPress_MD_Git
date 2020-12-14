/**
 * ImageAssistant
 * Project Home: http://www.pullywood.com/ImageAssistant/
 * Author: 睡虫子(Joey)
 * Copyright (C) 2013-2020 普利坞(Pullywood.com)
**/
"use strict";

window._w_rib = new Array;

window._w_shawl = new Array;

window._w_cell = {
    apng: "image/apng",
    bmp: "image/bmp",
    gif: "image/gif",
    ico: "image/x-icon",
    cur: "image/x-icon",
    pjp: "image/jpeg",
    pjpeg: "image/jpeg",
    jfif: "image/jpeg",
    jpeg: "image/jpeg",
    jpg: "image/jpeg",
    png: "image/png",
    svg: "image/svg+xml",
    tiff: "image/tiff",
    tif: "image/tiff",
    webp: "image/webp"
};

for (let suffix in _w_cell) {
    _w_shawl.push(suffix);
    _w_rib.push(_w_cell[suffix]);
}

window._w_tango = [ "jpeg", "png", "apng", "bmp", "gif", "ico", "svg", "tiff", "webp", "apng" ];

window._w_tremor = {
    apng: "apng",
    bmp: "bmp",
    gif: "gif",
    ico: "ico",
    cur: "ico",
    pjp: "jpeg",
    pjpeg: "jpeg",
    jfif: "jpeg",
    jpeg: "jpeg",
    jpg: "jpeg",
    png: "png",
    svg: "svg",
    tiff: "tiff",
    tif: "tiff",
    webp: "webp"
};

window._w_votary = _w_eclat([ "10x10", "100x100", "160x128", "200x200", "220x176", "300x300", "320x240", "400x240", "400x400", "480x320", "480x360", "500x500", "600x600", "640x360", "640x480", "800x480", "800x600", "854x480", "960x540", "960x640", "1024x768", "1280x720", "1280x800", "1280x1024", "1360x768", "1366x768", "1440x900", "1600x900", "1680x1050", "1920x1080", "1920x1200", "2560x1080", "2560x1440", "2560x1600", "3840x1080", "3840x2160" ], true);