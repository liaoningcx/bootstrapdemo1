/**
 * 公共组件.
 * User: chengxuejing
 * Date: 12-11-13
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
Commons = (function() {
    return {
        /**
         * 功能页中获取父级页面, 直接用<b>parent</b>会获取到最外层的frameset
         *
         * @return {*}
         */
        parent : function() {
            return parent.frames["main"];
        },

        /**
         * 获取随机数
         *
         * @return {Number}
         */
        random : function() {
            return parseInt(10000 * Math.random());
        },
        /**
         * 弹出窗口
         *
         * @param _context_ 内容
         * @param _title_ 标题
         * @param _width_ 长度
         * @param _type_ 类型
         * @param _call_back_ 回调函数
         */
        alert : function(_context_, _title_, _width_, _type_, _call_back_,param) {
            if (undefined == _width_) {
                _width_ = 260;
            }

            jqm.alert({
                w : _width_,
                title : _title_,
                type : _type_,
                content : _context_,
                self : self,
                onHide : _call_back_,
                param : param
            });
        },
        /**
         * 错误提示
         *
         * @param _context_ 内容
         * @param _title_ 标题
         * @param _width_ 长度
         * @param _call_back_ 回调函数
         */
        showError : function(_context_, _title_, _width_, _call_back_) {
            if (undefined == _title_) {
                _title_ = "错误";
            } else {
                if ($.isFunction(_title_)) {
                    _call_back_ = _title_;
                    _title_ = "错误";
                } else if (typeof(_title_) == "number") {
                    if (undefined != _width_) {
                        if ($.isFunction(_width_)) {
                            _call_back_ = _width_;
                        }
                    }
                    _width_ = _title_;
                    _title_ = "错误";
                }
            }
            if (undefined != _width_) {
                if ($.isFunction(_width_)) {
                    _call_back_ = _width_;
                    _width_ = undefined;
                }
            }

            Commons.alert(_context_, _title_, _width_, "error", _call_back_);
        },

        /**
         * 警告提示
         *
         * @param _context_ 内容
         * @param _title_ 标题
         * @param _width_ 长度
         * @param _call_back_ 回调函数
         */
        showWarn : function(_context_, _title_, _width_, _call_back_) {
            if (undefined == _title_) {
                _title_ = "警告";
            } else {
                if ($.isFunction(_title_)) {
                    _call_back_ = _title_;
                    _title_ = "警告";
                } else if (typeof(_title_) == "number") {
                    if (undefined != _width_) {
                        if ($.isFunction(_width_)) {
                            _call_back_ = _width_;
                        }
                    }
                    _width_ = _title_;
                    _title_ = "警告";
                }
            }
            if (undefined != _width_) {
                if ($.isFunction(_width_)) {
                    _call_back_ = _width_;
                    _width_ = undefined;
                }
            }

            Commons.alert(_context_, _title_, _width_, "alert", _call_back_);
        },

        /**
         * 普通提示
         *
         * @param _context_ 内容
         * @param _title_ 标题
         * @param _width_ 长度
         * @param _call_back_ 回调函数
         */
        showInfo : function(_context_, _title_, _width_, _call_back_) {
            if (undefined == _title_) {
                _title_ = "提示";
            } else {
                if ($.isFunction(_title_)) {
                    _call_back_ = _title_;
                    _title_ = "提示";
                } else if (typeof(_title_) == "number") {
                    if (undefined != _width_) {
                        if ($.isFunction(_width_)) {
                            _call_back_ = _width_;
                        }
                    }
                    _width_ = _title_;
                    _title_ = "提示";
                }
            }
            if (undefined != _width_) {
                if ($.isFunction(_width_)) {
                    _call_back_ = _width_;
                    _width_ = undefined;
                }
            }

            Commons.alert(_context_, _title_, _width_, "attention", _call_back_);
        },

        /**
         * 成功提示
         *
         * @param _context_ 内容
         * @param _title_ 标题
         * @param _width_ 长度
         * @param _call_back_ 回调函数
         */
        showSuccess : function(_context_, _title_, _width_, _call_back_,param) {
        	 if (undefined == _title_) {
                 _title_ = "成功";
             } else {
                 if ($.isFunction(_title_)) {
                     _call_back_ = _title_;
                     _title_ = "成功";
                     param = _width_;
                     _width_ = undefined;
                 } else if (typeof(_title_) == "number") {
                     if (undefined != _width_) {
                         if ($.isFunction(_width_)) {
                             _call_back_ = _width_;
                             param = _call_back_;
                         }
                     }
                     _width_ = _title_;
                     _title_ = "成功";
                     param = _call_back_;
                 }
             }
             if (undefined != _width_) {
                 if ($.isFunction(_width_)) {
                     _call_back_ = _width_;
                     _width_ = undefined;
                     param = _call_back_;
                 }
             }

             Commons.alert(_context_, _title_, _width_, "success", _call_back_,param);
        },

        /**
         * 失败提示
         *
         * @param _context_ 内容
         * @param _title_ 标题
         * @param _width_ 长度
         * @param _call_back_ 回调函数
         */
        failure : function(_context_, _title_, _width_, _call_back_) {
            if (undefined == _title_) {
                _title_ = "失败";
            }else {
                if ($.isFunction(_title_)) {
                    _call_back_ = _title_;
                    _title_ = "失败";
                } else if (typeof(_title_) == "number") {
                    if (undefined != _width_) {
                        if ($.isFunction(_width_)) {
                            _call_back_ = _width_;
                        }
                    }
                    _width_ = _title_;
                    _title_ = "失败";
                }
            }
            if (undefined != _width_) {
                if ($.isFunction(_width_)) {
                    _call_back_ = _width_;
                    _width_ = undefined;
                }
            }
            Commons.alert(_context_, _title_, _width_, "failure", _call_back_);
        },
        
        /**
         * 确认提示
         *
         * @param _title_ 标题
         * @param callbackFun 回调函数
         * @param paramArray 参数数组
         */
        confirm : function( _title_,callbackFun,paramArray) {
            if (undefined == _title_) {
                return;
            }
            var self = this;
            jqm.confirm({
                w:400,
                title:_title_,
                type:'attention',
                retrieveTop:function(){
                    return 0;
                },
                content:'<h4>确定要提交此操作吗？</h4>',
                self:self,
                onConfirm:function(){
                    if (callbackFun) {
                        if (callbackFun != '' && typeof (callbackFun) == 'function') {
                            if(paramArray == null || typeof (paramArray) == 'undefined') {
                                callbackFun();
                            } else {
                                callbackFun(paramArray);
                            }
                        }
                    }
                }
            });
        }

    }
} )();
//数据比对
Array.prototype.contains = function (element) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == element) {
            return true;
        }
    }
    return false;
}
//处理datagrid可编辑修改时去掉样式
function setDataGirdInput(editors,index){
    jQuery.each(editors,function(i) {
        if(!index.contains(i)){
            this.target.removeClass("datagrid-editable-input");
            this.target.addClass("defaultInput");
            this.target.attr("readonly","readonly");
        }
    })
}

Date.prototype.format = function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)){
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(format)){
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}

/**
 * 保留小数点后2位
 * @param x
 * @return {*}
 */
function changeTwoDecimal(x)
{
    var f_x = parseFloat(x);
    if (isNaN(f_x))
    {
        return "";
    }
    var f_x = Math.round(x*100)/100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0)
    {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2)
    {
        s_x += '0';
    }
    return s_x;
}

/**
 * 保留小数点后6位
 * @param x
 * @return {*}
 */
function changeSixDecimal(x)
{
    var f_x = parseFloat(x);
    if (isNaN(f_x))
    {
        return false;
    }
    var f_x = Math.round(x*1000000)/1000000;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0)
    {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 6)
    {
        s_x += '0';
    }
    return s_x;
}

/**
 * 验证整数
 * @param _val
 * @return {Boolean}
 */
function isNaturalNumber(_val){
    if(typeof(_val)=="undefined")
        return false;
    if(_val=="" || _val==null){
        return false;
    }
    var regExp = /^\d*$/;
    if(regExp.test(_val)){
        return true;
    }else{
        return false;
    }
}
/**
 * 验证两位小数点
 * @param _val
 * @return {Boolean}
 */
function twoNumAfterPoint(_val){
    if(isNaN(_val)){
        return false;
    } else if(_val.indexOf(".")>0 && _val.substring(_val.indexOf(".")+1).length>2){
        return true;
    }
}
/**
 * 验证整数、小数或者0
 * @param val
 * @return 不符合条件的false，符合条件的true
 */
function checkNumBox(val) {
    var pattern = /^[1-9]+(\.\d+)?$|^0(\.\d+)?$|^[1-9]+[0-9]*(\.\d+)?$/;
    if (!pattern.test(changeTwoDecimal(val))) {
//        Commons.showWarn("请输入正整数、小数或者0！");
        return false;
    }
    return true;
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

//赋值黏贴到剪切板
function copyToClipboard(txt) {
    if(window.clipboardData) {
        window.clipboardData.clearData();
        window.clipboardData.setData("Text", txt);
    } else if(navigator.userAgent.indexOf("Opera") != -1) {
        window.location = txt;
    } else if (window.netscape) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
//            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip)
            return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans)
            return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = txt;
        str.data = copytext;
        trans.setTransferData("text/unicode",str,copytext.length*2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip)
            return false;
        clip.setData(trans,null,clipid.kGlobalClipboard);
    }
}