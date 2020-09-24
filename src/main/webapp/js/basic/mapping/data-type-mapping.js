/**
 * 数据类型映射页面JS
 * User: zhaoming
 * Date: 13-4-2
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    loadGrid();
});

/**
 *  结算单列表
 */
var lastIndex;

function loadGrid(){
    $('#ordertable').datagrid({
        width:950,
        height:310,
        url: '/dataTypeMapping/query.json',
        rownumbers : true,
        nowrap : true,
        striped : true,
        singleSelect : true,
        loadMsg:'数据加载中......',
        columns:[[
            {field:'databaseType',title:'Database数据类型',width:250},
            {
                field : 'jdbcType',
                title : 'Jdbc数据类型',
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
                field : 'javaType',
                title : 'Java数据类型',
                width : 250,
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
 *  新增数据类型映射
 */
function update() {
    var rows = $('#ordertable').datagrid('acceptChanges').datagrid('getRows');
    for ( var i = 0; i < rows.length; i++) {
        if (rows[i].jdbcType == "" || rows[i].jdbcType == null || rows[i].javaType == "" || rows[i].javaType == null) {
            Commons.showWarn("列表中不允许出现空值");
            return;
        }
    }
    var params = JSON.stringify(rows);

    $.post(springUrl+"/dataTypeMapping/update.json",
        {
            params : params
        },
        function(data){
            if(data.flag){
                Commons.showSuccess("数据类型映射变更成功");
            }else{
                Commons.showError("数据类型映射变更失败");
            }
        }
    );

}