/*!
 * @jQuery button select plugin
 * @Version 1.0  (August 13, 2012)
 * @author FengWeiping 360buy.com
 * @email me@itfe.org
 * @example $('.moveTable').mulSelMove();
 **/
;(function($){
	$.fn.extend({
		mulSelMove:function(ops){
			return $(this).each(function(){
				var _mulSelMove =new MulSelMove($(this));
				_mulSelMove.init(ops);
			});
		},
		bsHide:function(fid){
			
		}
	});
	function MulSelMove(obj){
		this.obj = obj;	
	};
	MulSelMove.liShow = function(obj){
		obj.removeClass('hidden');
		if(obj.attr('pid')) MulSelMove.liShow($("#"+obj.attr('pid')));		
	}
	MulSelMove.liHide = function(obj){
		obj.addClass('hidden');
		if(obj.attr('pid')&&obj.siblings(':visible').length==0) MulSelMove.liHide($("#"+obj.attr('pid')));		
	}
	MulSelMove.prototype={
		def:{
			"url":null,
			"type":"get",
			"leftList":"leftList",
			"rightList":"rightList",
			"selected":"selected",
			"add":"add",
			"remove":"remove"
		},
		init:function(ops){
			this.ops =jQuery.extend({},this.def, ops);//combine user's options width the default options		
			this.$L=this.obj.find("."+this.ops.leftList);
			this.$R=this.obj.find("."+this.ops.rightList);
			this.$add=this.obj.find("."+this.ops.add);
			this.$remove=this.obj.find("."+this.ops.remove);
			this.$L.bind("mousedown",{_this:this}, this.mulSel);
			this.$R.bind("mousedown",{_this:this}, this.mulSel);
			this.$add.bind("click",{_this:this}, this.add);
			this.$remove.bind("click",{_this:this}, this.remove);
			var _$left_0 = $('<ul id="L-0">').appendTo(this.$L);
			var _$right_0 = $('<ul id="R-0">').appendTo(this.$R);
			if(this.ops.url)
				$.ajax({
					"url":this.ops.url,
					"type":this.ops.type,
					"dataType":"json",
					"success":function(data){
						$.each(data,function(i,n){
							var _strL = '<li id="L-'+n.id+'" pid="L-'+n.pid+'" class="'+(n.selected?"selected hidden":"")+'">'+n.name+'</li>';
							var _strR = '<li id="R-'+n.id+'" pid="R-'+n.pid+'" class="'+(n.selected?"selected":"hidden")+'">'+n.name+'</li>';
							if($('#L-'+n.pid,_$left_0).length){
								if(!$('#L-'+n.pid+' ul:first',_$left_0).length){
									$('#L-'+n.pid,_$left_0).append('<ul>');
									$('#R-'+n.pid,_$right_0).append('<ul>');
								}
								$(_strL).appendTo('#L-'+n.pid+' ul:first',_$left_0);
								$(_strR).appendTo('#R-'+n.pid+' ul:first',_$left_0);
								
								if(n.selected)  MulSelMove.liShow($('#R-'+n.pid));
								
								
							}
							else{
								_$left_0.append(_strL);
								_$right_0.append(_strR);
							}				
						})
					} 
				})
		},
		mulSel:function(e){
			var $selRow = $(e.target);
			var _this=e?e.data._this:this;		
			if (e.shiftKey) {//begindof shiftKey
				if($selRow.parent('ul').hasClass('limit')){
					var _$li = $selRow.parent('ul').find('li'),
					_s = _$li.index(_this.$start[_this.$start.length-1]),
					_e = _$li.index($selRow),
					_min = _s<_e?_s:_e,_max = _s>_e?_s:_e;
					$.each(_$li,function(i,n){					
						if(i>=_min&&i<=_max) {
							$(n).addClass(_this.ops.selected);
						}
						else $(n).removeClass(_this.ops.selected);
					});
					if(0==_min&&(_$li.length-1)==_max)
					_this.liSelect($selRow.parent('ul').parent('li'));
				}
				else{
					_this.$start = [];
					_this.$start.push($selRow);
					$('.'+_this.ops.selected,_this.obj).not($selRow).removeClass(_this.ops.selected);
					
					_this.liSelect($selRow);
					_this.shihftLimit();	
					return;	
				}				
			} //endof shiftKey
			else if (e.ctrlKey) {//begindof ctrlKey
				if(!$selRow.hasClass(_this.ops.selected)) {
					_this.$start = _this.$start?_this.$start:[];			
					_this.$start.push($selRow);
					
					_this.liSelect($selRow);
					_this.shihftLimit();				
				}
				else {					
					var _i = -1;
					$.each(_this.$start, function(i, n){
					  if(!n.hasClass(_this.ops.selected)){
					  	_i = i;return false;
					  } 
					});
					_this.$start.splice(_i,1);
					
					_this.liUnselect($selRow);
					$('li',$selRow).removeClass(_this.ops.selected);
					
					_this.shihftLimit();	
				}
			}//endof ctrlKey
			else{//not ctrl or shift
				_this.$start = [];
				_this.$start.push($selRow);
				$('.'+_this.ops.selected,_this.obj).not($selRow).removeClass(_this.ops.selected);
				
				_this.liSelect($selRow);
				_this.shihftLimit();

			}
			//console.log(_this.$start);
		},
		shihftLimit:function(){
			$('.limit',this.obj).removeClass('limit');
			if(this.$start.length>=1)this.$start[this.$start.length-1].parent('ul').addClass('limit');	
		},
		liSelect:function(obj){
			obj.addClass(this.ops.selected);
			if(obj.parent('ul').parent('li').length&&obj.siblings().not('.'+this.ops.selected).not('.hidden').length==0){
				this.liSelect(obj.parent('ul').parent('li'));
			}
			$('li',obj).addClass(this.ops.selected);
		},
		liUnselect:function(obj){
			obj.removeClass(this.ops.selected);
			if(obj.parent('ul').parent('li').length){
				this.liUnselect(obj.parent('ul').parent('li'));
			}
		},
		remove:function(e){
			var _this=e?e.data._this:this;	
			$.each($('li.'+_this.ops.selected,_this.$R),function(i,n){
				var _fid = $(n).attr('id').replace('R-','');
				$('#L-'+_fid,_this.$L).addClass(_this.ops.selected);
				MulSelMove.liShow($('#L-'+_fid,_this.$L));
				MulSelMove.liHide($(n));
			});
		},
		add:function(e){
			var _this=e?e.data._this:this;	
			$.each($('li.'+_this.ops.selected,_this.$L),function(i,n){
				var _fid = $(n).attr('id').replace('L-','');
				$('#R-'+_fid,_this.$R).addClass(_this.ops.selected);
				MulSelMove.liShow($('#R-'+_fid,_this.$R));
				MulSelMove.liHide($(n));
			});
		}
	}
})(jQuery);