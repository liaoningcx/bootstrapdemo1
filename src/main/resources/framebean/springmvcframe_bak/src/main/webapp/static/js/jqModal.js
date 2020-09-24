/*
 * jqModal - Minimalist Modaling with jQuery
 * (http://dev.iceburg.net/jquery/jqModal/)
 *
 * Copyright (c) 2007,2008 Brice Burgess <bhb@iceburg.net>
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 * $Version: 03/01/2009 +r14
 * @fileoverview plugin of modal base on jQuery
 * @author Brice Burgess
 * @version 03/01/2009 +r14
 * @example
 * $("#demo").jqm();
 */
(function(e) {
    e.fn.jqDrag = function(f) {
        return b(this, f, "d")
    };
    e.fn.jqResize = function(f) {
        return b(this, f, "r")
    };
    e.jqDnR = {
        dnr : {},
        e : 0,
        drag : function(f) {
            if (g.k == "d") {
                if ((g.X + f.pageX - g.pX + g.ml) <= 0) {
                    d.css({
                        left : -g.ml
                    })
                } else {
                    if ((g.X + f.pageX - g.pX + g.ml) >= (e("body").width() - g.W)) {
                        d.css({
                            left : (e("body").width() - g.W - g.ml)
                        })
                    } else {
                        d.css({
                            left : g.X + f.pageX - g.pX
                        })
                    }
                }
                if ((g.Y + f.pageY - g.pY) <= 0) {
                    d.css({
                        top : 0
                    })
                } else {
                    if ((g.Y + f.pageY - g.pY + g.H) >= g.dH) {
                        d.css({
                            top : g.dH - g.H
                        })
                    } else {
                        d.css({
                            top : g.Y + f.pageY - g.pY
                        })
                    }
                }
            } else {
                d.css({
                    width : Math.max(f.pageX - g.pX + g.W, 0),
                    height : Math.max(f.pageY - g.pY + g.H, 0)
                })
            }
            return true
        },
        stop : function() {
            e(document).unbind("mousemove", a.drag).unbind("mouseup", a.stop)
        }
    };
    var a = e.jqDnR, g = a.dnr, d = a.e, b = function(j, i, f) {
        return j.each(function() {
            i = (i) ? e(i, j) : j;
            i.bind("mousedown", {
                e : j,
                k : f
            }, function(h) {
                var m = h.data, l = {};
                d = m.e;
                if (d.css("position") != "relative") {
                    try {
                        d.position(l)
                    } catch (k) {
                    }
                }
                g = {
                    X : l.left || c("left") || 0,
                    Y : l.top || c("top") || 0,
                    W : c("width") || d[0].scrollWidth || 0,
                    H : c("height") || d[0].scrollHeight || 0,
                    pX : h.pageX,
                    pY : h.pageY,
                    k : m.k,
                    o : d.css("opacity"),
                    ml : d.css("margin-left").replace("px", "") * 1,
                    dH : e(window).height()
                        || document.body.parentNode.scrollHeight
                };
                e(document).mousemove(e.jqDnR.drag).mouseup(e.jqDnR.stop);
                return true
            })
        })
    }, c = function(f) {
        return parseInt(d.css(f)) || false
    }
})(jQuery);
(function(d) {
    d.fn.jqm = function(f) {
        var e = {
            overlay : 20,
            overlayClass : "whiteOverlay",
            closeClass : "jqmClose",
            trigger : ".jqModal",
            ajax : o,
            ajaxText : "",
            target : o,
            modal : true,
            movable : false,
            retrieveTop : undefined,
            toTop : o,
            onShow : o,
            onHide : o,
            onLoad : o
        };
        return this.each(function() {
            if (this._jqm) {
                return n[this._jqm].c = d.extend({}, n[this._jqm].c, f)
            }
            p++;
            this._jqm = p;
            n[p] = {
                c : d.extend(e, d.jqm.params, f),
                a : o,
                w : d(this).addClass("jqmID" + p),
                s : p
            };
            if (e.trigger) {
                d(this).jqmAddTrigger(e.trigger)
            }
        })
    };
    d.fn.jqmAddClose = function(f) {
        return l(this, f, "jqmHide")
    };
    d.fn.jqmAddTrigger = function(f) {
        return l(this, f, "jqmShow")
    };
    d.fn.jqmShow = function(e) {
        return this.each(function() {
            e = e || window.event;
            d.jqm.open(this._jqm, e)
        })
    };
    d.fn.jqmHide = function(e) {
        return this.each(function() {
            e = e || window.event;
            d.jqm.close(this._jqm, e)
        })
    };
    d.jqm = {
        hash : {},
        open : function(B, A) {
            var m = n[B], q = m.c, i = "." + q.closeClass, v = (parseInt(m.w
                .css("z-index"))), v = (v > 0) ? v : 3000, f = d(
                "<div></div>").css({
                    height : "100%",
                    width : "100%",
                    position : "fixed",
                    left : 0,
                    top : 0,
                    "z-index" : v - 1,
                    opacity : q.overlay / 100
                });
            if (m.a) {
                return o
            }
            m.t = A;
            m.a = true;
            m.w.css("z-index", v);
            if (q.modal) {
                if (!a[0]) {
                    k("bind")
                }
                a.push(B)
            } else {
                if (q.overlay > 0) {
                    m.w.jqmAddClose(f)
                } else {
                    f = o
                }
            }
            m.o = (f) ? f.addClass(q.overlayClass).prependTo("body") : o;
            if (c) {
                d("html,body").css({
                    height : "100%",
                    width : "100%"
                });
                if (f) {
                    f = f.css({
                        position : "absolute"
                    })[0];
                    for ( var w in {
                        Top : 1,
                        Left : 1
                    }) {
                        f.style.setExpression(w.toLowerCase(),
                            "(_=(document.documentElement.scroll" + w
                                + " || document.body.scroll" + w
                                + "))+'px'")
                    }
                }
            }
            if (q.ajax) {
                var e = q.target || m.w, x = q.ajax, e = (typeof e == "string") ? d(
                    e, m.w)
                    : d(e), x = (x.substr(0, 1) == "@") ? d(A).attr(
                    x.substring(1)) : x;
                e.html(q.ajaxText).load(x, function() {
                    if (q.onLoad) {
                        q.onLoad.call(this, m)
                    }
                    if (i) {
                        m.w.jqmAddClose(d(i, m.w))
                    }
                    j(m)
                })
            } else {
                if (i) {
                    m.w.jqmAddClose(d(i, m.w))
                }
            }
            if (q.toTop && m.o) {
                m.w.before('<span id="jqmP' + m.w[0]._jqm + '"></span>')
                    .insertAfter(m.o)
            }
            (q.onShow) ? q.onShow(m) : m.w.show();
            j(m);
            _top = q.retrieveTop ? q.retrieveTop(m.w) : 0;
            m.w.css({
                "margin-left" : -m.w.width() / 2,
                width : m.w.width(),
                left : "50%"
            });
            if (q.retrieveTop) {
                m.w.css({
                    top : _top
                })
            } else {
                m.w.css({
                    top : m.w.offset().top - d(document).scrollTop()
                })
            }
            if (q.movable && !m.w.find(".move").length) {
                m.w.jqDrag(".tl").find(".tl").addClass("move")
            }
            return o
        },
        close : function(f) {
            var e = n[f];
            if (!e.a) {
                return o
            }
            e.a = o;
            if (a[0]) {
                a.pop();
                if (!a[0]) {
                    k("unbind")
                }
            }
            if (e.c.toTop && e.o) {
                d("#jqmP" + e.w[0]._jqm).after(e.w).remove()
            }
            if (e.c.onHide) {
                e.c.onHide(e)
            } else {
                e.w.hide();
                if (e.o) {
                    e.o.remove()
                }
            }
            return o
        },
        params : {}
    };
    var p = 0, n = d.jqm.hash, a = [], c = d.browser.msie
        && (d.browser.version == "6.0"), o = false, g = d(
        '<iframe src="javascript:false;document.write(\'\');" class="jqm"></iframe>')
        .css({
            opacity : 0
        }), j = function(e) {
        if (c) {
            if (e.o) {
                e.o.html('<p style="width:100%;height:100%"/>').prepend(g)
            } else {
                if (!d("iframe.jqm", e.w)[0]) {
                    e.w.prepend(g)
                }
            }
        }
        h(e)
    }, h = function(f) {
        try {
            d(":input:visible", f.w)[0].focus()
        } catch (e) {
        }
    }, k = function(e) {
        d()[e]("keypress", b)[e]("keydown", b)[e]("mousedown", b)
    }, b = function(m) {
        var f = n[a[a.length - 1]], i = (!d(m.target).parents(".jqmID" + f.s)[0]);
        if (i) {
            h(f)
        }
        return !i
    }, l = function(e, f, i) {
        return e.each(function() {
            var m = this._jqm;
            d(f).each(function() {
                if (!this[i]) {
                    this[i] = [];
                    d(this).click(function() {
                        for ( var q in {
                            jqmShow : 1,
                            jqmHide : 1
                        }) {
                            for ( var r in this[q]) {
                                if (n[this[q][r]]) {
                                    n[this[q][r]].w[q](this)
                                }
                            }
                        }
                        return o
                    })
                }
                this[i].push(m)
            })
        })
    }
})(jQuery);
if (typeof (jqm) == "undefined") {
    jqm = new Object()
} else {
}
jqm.confirm = function(c) {
    var d = {
        w : 370,
        self : null,
        title : "确认",
        content : "内容",
        type : "alert",
        onConfirm : null,
        overlay : 20,
        overlayClass : "whiteOverlay",
        retrieveTop : undefined,
        closeClass : "jqmClose"
    };
    d.w = c.w ? c.w : d.w;
    d.self = c.self ? c.self : d.self;
    d.title = c.title ? c.title : d.title;
    d.content = c.content ? c.content : d.content;
    d.type = c.type ? c.type : d.type;
    d.onConfirm = c.onConfirm ? c.onConfirm : d.onConfirm;
    d.overlay = c.overlay ? c.overlay : d.overlay;
    d.overlayClass = c.overlayClass ? c.overlayClass : d.overlayClass;
    d.jqmClose = c.jqmClose ? c.closeClass : d.closeClass;
    d.retrieveTop = c.retrieveTop ? c.retrieveTop : d.retrieveTop;
    var b = {
        overlay : d.overlay,
        overlayClass : d.overlayClass,
        retrieveTop : d.retrieveTop,
        closeClass : d.closeClass
    };
    if ($("#jqmConfirm").length) {
        $("#jqmConfirm").remove()
    }
    var a = '<div id="jqmConfirm" style="width:'
        + d.w
        + 'px;"class="modal"><h1 class="tl"><span class="tr"><span class="tit">'
        + d.title
        + '</span><span class="modalClose jqmClose">&#20851;&#38381;</span></span></h1><div class="moadalCon"><div class="clearfix fakeMsg '
        + d.type
        + '"><i class="ico"></i><div class="conText">'
        + d.content
        + '</div></div></div><div class="modalFooter pb20"><a href="javascript:void(0);"id="jqmConfirmBtn"class="btn mr25"><s><b><span>&#30830;&emsp;&#23450;</span></b></s></a><a href="javascript:void(0);"id="jqmConfirmBtn"class="btn jqmClose"><s><b><span>&#21462;&nbsp;&#28040;</span></b></s></a></div><div class="bl"><div class="br"></div></div></div>';
    $("body").append(a);
    $("#jqmConfirm").jqm(b);
    $("#jqmConfirm").jqmShow();
    $("#jqmConfirmBtn").click(function() {
        if (d.onConfirm) {
            $("#jqmConfirm").jqmHide().remove();
            d.onConfirm.call(d.self)
        } else {
            return
        }
    })
};
jqm.alert = function(c) {
    var d = {
        w : 200,
        self : null,
        title : "确认",
        content : "内容",
        type : "success",
        onConfirm : null,
        overlay : 20,
        overlayClass : "whiteOverlay",
        closeClass : "jqmClose",
        retrieveTop : undefined,
        onShow : undefined,
        onHide : function() {
            return true;
        },
        param : null
    };
    d.w = c.w ? c.w : d.w;
    d.self = c.self ? c.self : d.self;
    d.title = c.title ? c.title : d.title;
    d.content = c.content ? c.content : d.content;
    d.type = c.type ? c.type : d.type;
    d.onConfirm = c.onConfirm ? c.onConfirm : d.onConfirm;
    d.overlay = c.overlay ? c.overlay : d.overlay;
    d.overlayClass = c.overlayClass ? c.overlayClass : d.overlayClass;
    d.jqmClose = c.jqmClose ? c.closeClass : d.closeClass;
    d.onHide = c.onHide ? c.onHide : d.onHide;
    d.param = c.param ? c.param : d.param;
    d.retrieveTop = c.retrieveTop ? c.retrieveTop : d.retrieveTop;
    var b = {
        overlay : d.overlay,
        overlayClass : d.overlayClass,
        retrieveTop : d.retrieveTop,
        closeClass : d.closeClass
    };
    if ($("#jqmConfirm").length) {
        $("#jqmConfirm").remove();
    }
    var a = '<div id="jqmAlert" style="width:'
        + d.w
        + 'px;"class="modal"><h1 class="tl"><span class="tr"><span class="tit">'
        + d.title
        + '</span><span class="modalClose '
        + b.closeClass
        + '">&#20851;&#38381;</span></span></h1><div class="moadalCon"><div class="clearfix fakeMsg '
        + d.type
        + '"><i class="ico"></i><div class="conText">'
        + d.content
        + '</div></div></div><div class="modalFooter pb20"><a href="javascript:void(0);"id="jqmAlertBtn" class="btn '
        + b.closeClass
        + '"><s><b><span>&#20851;&emsp;&#38381;</span></b></s></a></div><div class="bl"><div class="br"></div></div></div>';
    $("body").append(a);
    $("#jqmAlert").jqm(b);
    $("#jqmAlert").jqmShow();
    $("#jqmAlert ." + b.closeClass + "").click(function() {
    	if(d.param == null){
    		d.onHide();
    	}else{
    		d.onHide(d.param);
    	}
        $("#jqmAlert").jqmHide().remove();
    })
};
jqm.msg = function(b) {
    var d = {
        w : 200,
        self : null,
        title : "确认",
        content : "内容",
        type : "success",
        onConfirm : null,
        overlay : 20,
        overlayClass : "whiteOverlay",
        closeClass : "jqmClose",
        retrieveTop : undefined,
        onShow : undefined,
        onHide : function() {
            return true
        }
    };
    d.w = b.w ? b.w : d.w;
    d.self = b.self ? b.self : d.self;
    d.title = b.title ? b.title : d.title;
    d.content = b.content ? b.content : d.content;
    d.type = b.type ? b.type : d.type;
    d.onConfirm = b.onConfirm ? b.onConfirm : d.onConfirm;
    d.overlay = b.overlay ? b.overlay : d.overlay;
    d.overlayClass = b.overlayClass ? b.overlayClass : d.overlayClass;
    d.jqmClose = b.jqmClose ? b.closeClass : d.closeClass;
    d.onHide = b.onHide ? b.onHide : d.onHide;
    d.retrieveTop = b.retrieveTop ? b.retrieveTop : d.retrieveTop;
    var a = {
        overlay : d.overlay,
        overlayClass : d.overlayClass,
        retrieveTop : d.retrieveTop,
        closeClass : d.closeClass
    };
    if ($("#jqmMsg").length) {
        $("#jqmMsg").remove()
    }
    var c = '<div id="jqmMsg" class="modal jqMsg '
        + d.type
        + '"style="width:'
        + d.w
        + 'px;"><h3 class="jqTit"><a href=""class="modalClose '
        + a.closeClass
        + '">&#20851;&#38381;</a></h3><div class="jqMsgCon clearfix"><i></i><div class="msgHtml">'
        + d.content + "</div></div></div>";
    $("body").append(c);
    $("#jqmMsg").jqm(a);
    $("#jqmMsg").jqmShow();
    $("#jqmMsg ." + a.closeClass + "").click(function() {
        d.onHide();
        $("#jqmMsg").jqmHide().remove()
    })
};
jqm.loading = function(c) {
    var d = {
        content : "",
        size : 32,
        overlay : 10,
        overlayClass : "whiteOverlay",
        w : 200
    };
    d.w = c.w ? c.w : d.w;
    d.content = c.content ? c.content : d.content;
    d.size = c.size ? c.size : d.size;
    d.overlay = c.overlay ? c.overlay : d.overlay;
    d.overlayClass = c.overlayClass ? c.overlayClass : d.overlayClass;
    var a = {
        overlay : d.overlay,
        overlayClass : d.overlayClass,
        closeClass : d.closeClass
    };
    //2012-10-24 战勇（zhanyong@360buy.com）添加组件ID定义部分，解决同一页面加载多个组件隐藏失败问题
    var loadinPluginId='jqmLoading';
    if(typeof(c.jqmId)!="undefined"){
        loadinPluginId=c.jqmId;
    }
    if ($("#"+loadinPluginId).length) {
        $("#"+loadinPluginId).remove()
    }
    var b = '<div id="'+loadinPluginId+'" style="width:' + d.w
        + 'px;"class="jqmLoading hide"><span class="loading loading_'
        + d.size + '"></span><span style="line-height:' + d.size
        + 'px;" class="ml10 font1">' + d.content + "</span></div>";
    $("body").append(b);
    $("#"+loadinPluginId).jqm(a).jqDrag(".jqDrag");
    $("#"+loadinPluginId).jqmShow()
};