/**
 * 模板管理页面JS
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
    var lastIndex;
    $('#ordertable').datagrid({
        width:950,
        height:310,
        nowrap: true,
        striped: true,
        collapsible:true,
        url: '/template/query.json',
        sortOrder: 'desc',
        remoteSort: true,
        idField:'id',
        loadMsg:'数据加载中......',
        columns:[[
            {field:'code',title:'模板编码',width:150},
            {field:'name',title:'模板描述',width:150},
            {field:'templateName',title:'模板名称',width:150},
            {field:'filePath',title:'生成文件的存放路径',width:150},
            {field:'fileName',title:'生成文件的后缀名',width:150},
            {field:'查看',title:'操作',width:150,
                formatter:function(value,rowData,rowIndex){
                    return "<a href='"+springUrl+"/template/show?templateName="+rowData.templateName+"&fileName="+rowData.fileName+"'>查看</a>    <a href='"+springUrl+"/template/update?params="+rowData.templateName+"'>编辑</a>";
                }
            }
        ]],
        rownumbers:true
    });
}
