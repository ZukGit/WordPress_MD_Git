(function(){'use strict';var $jscomp=$jscomp||{};$jscomp.scope={};$jscomp.arrayIteratorImpl=function(a){var b=0;return function(){return b<a.length?{done:!1,value:a[b++]}:{done:!0}}};$jscomp.arrayIterator=function(a){return{next:$jscomp.arrayIteratorImpl(a)}};$jscomp.ASSUME_ES5=!1;$jscomp.ASSUME_NO_NATIVE_MAP=!1;$jscomp.ASSUME_NO_NATIVE_SET=!1;$jscomp.SIMPLE_FROUND_POLYFILL=!1;
$jscomp.defineProperty=$jscomp.ASSUME_ES5||"function"==typeof Object.defineProperties?Object.defineProperty:function(a,b,d){a!=Array.prototype&&a!=Object.prototype&&(a[b]=d.value)};$jscomp.getGlobal=function(a){return"undefined"!=typeof window&&window===a?a:"undefined"!=typeof global&&null!=global?global:a};$jscomp.global=$jscomp.getGlobal(this);$jscomp.SYMBOL_PREFIX="jscomp_symbol_";$jscomp.initSymbol=function(){$jscomp.initSymbol=function(){};$jscomp.global.Symbol||($jscomp.global.Symbol=$jscomp.Symbol)};
$jscomp.SymbolClass=function(a,b){this.$jscomp$symbol$id_=a;$jscomp.defineProperty(this,"description",{configurable:!0,writable:!0,value:b})};$jscomp.SymbolClass.prototype.toString=function(){return this.$jscomp$symbol$id_};$jscomp.Symbol=function(){function a(d){if(this instanceof a)throw new TypeError("Symbol is not a constructor");return new $jscomp.SymbolClass($jscomp.SYMBOL_PREFIX+(d||"")+"_"+b++,d)}var b=0;return a}();
$jscomp.initSymbolIterator=function(){$jscomp.initSymbol();var a=$jscomp.global.Symbol.iterator;a||(a=$jscomp.global.Symbol.iterator=$jscomp.global.Symbol("Symbol.iterator"));"function"!=typeof Array.prototype[a]&&$jscomp.defineProperty(Array.prototype,a,{configurable:!0,writable:!0,value:function(){return $jscomp.iteratorPrototype($jscomp.arrayIteratorImpl(this))}});$jscomp.initSymbolIterator=function(){}};
$jscomp.initSymbolAsyncIterator=function(){$jscomp.initSymbol();var a=$jscomp.global.Symbol.asyncIterator;a||(a=$jscomp.global.Symbol.asyncIterator=$jscomp.global.Symbol("Symbol.asyncIterator"));$jscomp.initSymbolAsyncIterator=function(){}};$jscomp.iteratorPrototype=function(a){$jscomp.initSymbolIterator();a={next:a};a[$jscomp.global.Symbol.iterator]=function(){return this};return a};
(function(a){function b(e){if(d[e])return d[e].exports;var c=d[e]={i:e,l:!1,exports:{}};a[e].call(c.exports,c,c.exports,b);c.l=!0;return c.exports}var d={};b.m=a;b.c=d;b.d=function(a,c,d){b.o(a,c)||Object.defineProperty(a,c,{enumerable:!0,get:d})};b.r=function(a){$jscomp.initSymbol();$jscomp.initSymbol();"undefined"!==typeof Symbol&&Symbol.toStringTag&&($jscomp.initSymbol(),Object.defineProperty(a,Symbol.toStringTag,{value:"Module"}));Object.defineProperty(a,"__esModule",{value:!0})};b.t=function(a,
c){c&1&&(a=b(a));if(c&8||c&4&&"object"===typeof a&&a&&a.__esModule)return a;var d=Object.create(null);b.r(d);Object.defineProperty(d,"default",{enumerable:!0,value:a});if(c&2&&"string"!=typeof a)for(var e in a)b.d(d,e,function(b){return a[b]}.bind(null,e));return d};b.n=function(a){var c=a&&a.__esModule?function(){return a["default"]}:function(){return a};b.d(c,"a",c);return c};b.o=function(a,b){return Object.prototype.hasOwnProperty.call(a,b)};b.p="";return b(b.s=27)})({27:function(a,b){(function(){var a=
document.createElement("style");document.head.appendChild(a);a.type="text/css";a.innerText="* {\n        -webkit-user-select: text !important;\n        -moz-user-select: text !important;\n        -ms-user-select: text !important;\n         user-select: text !important;\n    }";[].forEach.call("contextmenu copy cut paste mouseup mousedown keyup keydown drag dragstart select selectstart".split(" "),function(a){document.addEventListener(a,function(a){a.stopPropagation()},!0)})})()}});}).call(this || window)