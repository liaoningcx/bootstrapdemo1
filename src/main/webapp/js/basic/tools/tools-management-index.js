/**
 * 工具类管理页面JS
 * User: zhaoming
 * Date: 13-4-2
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    loadGrid();
});

var lastIndex;
function loadGrid(){
    $('#ordertable').datagrid({
        width:950,
        height:310,
        url: '/toolsManagement/query.json',
        rownumbers : true,
        nowrap : true,
        striped : true,
        singleSelect : true,
        loadMsg:'数据加载中......',
        columns:[[
            {field:'className',title:'类名',width:250},
            {
                field : 'packagePath',
                title : '包路径',
                width : 250,
                editor : {
                    type : 'text',
                    options : {
                        max : 100,
                        required : true
                    }
                }
            },
            {
                field : 'classDesc',
                title : '描述',
                width : 400,
                editor : {
                    type : 'text',
                    options : {
                        max : 100,
                        required : true
                    }
                }
            }
        ]],
        onClickRow : function(rowIndex, rowData) {
            if (lastIndex != rowIndex) {
                $('#ordertable').datagrid('endEdit', lastIndex).datagrid('beginEdit', rowIndex);
            }
            lastIndex = rowIndex;
        }
    });
}


/**
 *  修改工具类
 */
function update() {
    var rows = $('#ordertable').datagrid('acceptChanges').datagrid('getRows');
    for ( var i = 0; i < rows.length; i++) {
        if (rows[i].packagePath == "") {
            Commons.showWarn("列表中的包路径不允许出现空值");
            return;
        }
    }
    var params = JSON.stringify(rows);

    $.post(springUrl+"/toolsManagement/update.json",
        {
            params : params
        },
        function(data){
            if(data.flag){
                Commons.showSuccess("变更成功");
            }else{
                Commons.showError("变更失败");
            }
        }
    );

}