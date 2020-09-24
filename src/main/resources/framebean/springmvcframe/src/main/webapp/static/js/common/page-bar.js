/**
 * 分页组件
 * 
 * @returns {PageBar}
 * 
 * @author suihonghua
 */
function PageBar(){}
PageBar.prototype = new Object();

//PageBar.prototype.length = 1;

/**
 * 填充分页框
 * 
 * clickFunction 点击分页时调用的函数名（String类型）
 * elementId	所填充的元素ID
 * page			Page对象
 * 
 * @author suihonghua
 */
PageBar.fillHtml = function(clickFunction, elementId, page){
	var pagebar_html = this.getHtml(clickFunction, page.totalRow, page.currentPage, page.totalPage);
	document.getElementById(elementId).innerHTML = pagebar_html;
};
function toPage(clickFunction){
    clickFunction($(".page-skipto").val());
}
/**
 * 获得分页框Html
 * 
 * clickFunction 点击分页时调用的函数名（String类型）
 * totalRow 	分页总条数
 * currentPage	当前页数
 * totalPage	分页总页数
 * 
 * @author suihonghua
 */
PageBar.getHtml = function(clickFunction, totalRow, currentPage, totalPage){
	var tmp = [];// html 构建
	var start = 0;
	var end = 0;
    currentPage= parseInt(currentPage)  ;
	// ////
	if(totalPage > 3){
		start = currentPage - 1;
		if(start < 1){
			start = 1;
		}
		end = start + 2;
		if(end > totalPage){
			end = totalPage;
			start = end - 2;
		}
	}else{
		start = 1;
		end = totalPage;
	}
	var _prePage = start - 1;
	var _nextPage = end + 1;
	var prePage = currentPage - 1;
	var nextPage = currentPage + 1;

	if(totalRow>0){



		//tmp.push('<div class="pagin-wrap fr">');
            tmp.push('<div class="pagin pagin-mini">');
            tmp.push('<b class="page-num">');
            if(currentPage==1){
                tmp.push('<b class="page-first page-disabled">首页</b>');
                tmp.push('<b class="pm-prev page-disabled"></b>');
            }else{
                tmp.push('<b class="page-first " onclick="' + clickFunction + '(1)">首页</b>');
                tmp.push('<b class="pm-prev " onclick="' + clickFunction + '(' + prePage + ')"></b>');
            }
            if(currentPage==totalPage){
                tmp.push('<b class="pm-next page-disabled" ></b>');
                tmp.push('<a class="page-last page-disabled">末页</a>');
            } else{
                tmp.push('<b class="pm-next" onclick="' + clickFunction + '(' + nextPage + ')"></b>');
                tmp.push('<a class="page-last" href="javascript:;" onclick="' + clickFunction + '(' + totalPage + ')">末页</a>');
            }

            tmp.push('</b>');
            tmp.push('<a class="page-last" href="javascript:toPage('+clickFunction+');">跳转</a>');
            tmp.push('<b class="page-skip">到<input type="text" id="page_input" class="page-skipto validate[custom[number],maxSize[20]]" value="'+currentPage+'"/>/<em>'+totalPage+'</em>页</b>');
            tmp.push('</div>');
       // tmp.push('</div>');
	}
	else{
		tmp.push('<div class="pagin pagin-mini"><span class="text">暂无相关记录</span></div>');
	}

	return tmp.join("");
};
