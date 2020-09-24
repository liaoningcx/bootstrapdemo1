/*!
 * @jQuery button select plugin
 * @Version 1.0  (August 13, 2012)
 * @author FengWeiping 360buy.com
 * @email me@itfe.org
 * @example $('.tab').btnSelect();
 **/
;(function($){
	$.fn.extend({
		btnSelect:function(ops){
			return $(this).each(function(){
				var _btnSelect =new BtnSelect($(this));
				_btnSelect.init(ops);
			});
		},
		bsHide:function(fid){
			var _this=this.data('bs_Opts');
			_this.btnTipHide();					
			return this;
		}
	});
	function BtnSelect(obj){
		this.obj = obj;	
	};
	BtnSelect.prototype={
		def:{
		},
		init:function(ops){
			this.$tip=$(this.obj.attr('fid'));
			this.obj.width(this.obj.width());
			this.obj.bind('click',{msg:this},this.btnTipShow);
			this.$tip.bind('click',function(e){e.stopPropagation();});
			this.$tip.find('.bsClose').bind('click',{msg:this},this.btnTipHide);
			this.obj.data('bs_Opts',this);
			this.$tip.data('bs_Opts',this);
		},
		btnTipShow:function(event){
			var _this=event?event.data.msg:this;
			if(_this.obj.hasClass('current')) return;
			var _h=_this.obj.height()-1,_position =_this.obj.position(),
			_fw = _position.left+_this.$tip.outerWidth()-_this.obj.parent().outerWidth();
			_fw = _fw>0?_fw:0;
			_this.$tip.css({
				"top":_position.top+_h,
				"left":_position.left-_fw
			}).show();
			setTimeout(function(){
				$(document).bind('click',{msg:_this},_this.btnTipHide);
			},100);
			_this.obj.addClass('current');
		},
		btnTipHide:function(event){
			var _this=event?event.data.msg:this;
			_this.obj.removeClass('current');
			_this.$tip.hide();
			$(document).unbind('click',_this.btnTipHide);
		}
	}
})(jQuery);