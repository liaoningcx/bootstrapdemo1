/**
 * MYSQL元数据代码生成页面JS
 * User: zhaoming
 * Date: 13-4-2
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
$(function(){
    loadGrid();
    $('#tableSchema').change(function() {
        $("#tableSqlName").empty();
        $("<option value=''>请选择</option>").appendTo("#tableSqlName");
        $("#tableComment").val();
        clear();
        var tableSchema = $(this).val();
        if(tableSchema != ""){
            selectTableNames(tableSchema);
        }
    });

    $('#tableSqlName').change(function() {
        $("#tableComment").val("");
        clear();
        var tableSqlName = $(this).val();
        if(tableSqlName != ""){
            selectColumnNames();
        }
    });

    $('#generatorType1').click(function() {
        $("#projectDirectory").val("");
        $("#projectDirectory").attr("disabled",true);
    });
    $('#generatorType2').click(function() {
        $("#projectDirectory").attr("disabled",false);
    });
});

/**
 *  结算单列表
 */
var lastIndex;
function loadGrid(){
    $('#ordertable').datagrid({
        title:'字段列表',
        width:950,
        height:310,
        url: '',
        rownumbers : true,
        nowrap : true,
        striped : true,
        singleSelect : true,
        loadMsg:'数据加载中......',
        columns:[[
            {field:'tableSchema',title:'数据库名',width:100,hidden:true},
            {field:'tableSqlName',title:'数据表名',width:200,hidden:true},
            {field:'columnName',title:'字段名称',width:200},
            {
                field : 'columnComment',
                title : '字段描述',
                width : 200,
                editor : {
                    type : 'text',
                    options : {
                        max : 100,
                        required : true
                    }
                }
            },
            {field:'dataType',title:'数据类型',width:200},
            {
                field : 'flag',
                title : '逻辑标识位',
                width : 150,
                align:'center',
                editor : {
                    type : 'checkbox',
                    options : {
                        on : 'Y',
                        off : ''
                    }
                }
            },{
                field : 'filter',
                title : '过滤标识',
                width : 150,
                align:'center',
                editor : {
                    type : 'checkbox',
                    options : {
                        on : 'Y',
                        off : ''
                    }
                }
            },
            {field:'columnKey',title:'主键',width:100,hidden:true},
            {field:'extra',title:'主键策略',width:100,hidden:true}
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
 * 获取数据表
 */
function selectTableNames(tableSchema){
    $.ajax({
        type : "POST",
        async : false,
        cache : false,
        url :springUrl + "/mysqlMetadataGenerator/selectTableNames.json",
        data : {
            tableSchema: tableSchema
        },
        dataType : 'json',
        success : function(data) {
            for(var i=0; i<data.length; i++){
                $("<option value='"+data[i].tableSqlName+"'>"+data[i].tableSqlName+"</option>").appendTo("#tableSqlName");
            }
        },
        error: function (data) {
            Commons.failure("获取表失败");
        }
    });
}

/**
 * 清空列表数据
 */
function clear(){
    //清空原有数据
    var item = $('#ordertable').datagrid('getRows');
    if (item) {
        for (var i = item.length - 1; i >= 0; i--) {
            var index = $('#ordertable').datagrid('getRowIndex', item[i]);
            $('#ordertable').datagrid('deleteRow', index);
        }
    }
    $('#ordertable').datagrid('loadData', { total: 0, rows: [] });
}

/**
 *  查询字段
 */
function selectColumnNames(){
    var queryParams = $('#ordertable').datagrid('options').queryParams;
    $('#ordertable').datagrid('options').url = springUrl+ '/mysqlMetadataGenerator/selectColumnNames.json';
    queryParams.tableSchema = $('#tableSchema').val();
    queryParams.tableSqlName = $('#tableSqlName').val();
    $("#ordertable").datagrid('clearSelections');
    $("#ordertable").datagrid('load');
}

/**
 *  代码生成
 */
function generator() {
    Commons.confirm("生成代码",function(){
        var validator=$('#generatorForm').validate(formValidator);
        if(validator.form()) {
            var rows = $('#ordertable').datagrid('acceptChanges').datagrid('getRows');
            for ( var i = 0; i < rows.length; i++) {
                if (rows[i].columnComment == "" || rows[i].columnComment == null) {
                    Commons.showWarn("字段描述信息不允许为空");
                    return;
                }
            }
            $("#mysqlMetadataGeneratorJson").val(JSON.stringify(rows));
            var projectDirectory = $("#projectDirectory").val();
            if(projectDirectory == null || projectDirectory == ""){
                downLoad();
            }else{
                importProject();
            }
        }
    })
}
/**
 *  代码生成
 */
function testws() {
    $(document).ready(function(){
        $("button").click(function(){
            $.post("/micMsg/testWebSer2",
                {
                    value:"Donald Duck"
                },
                function(data,status){
                    alert("success");
                });
        });
    });
}

/**
 * 压缩包下载
 */
function downLoad(){
    var m = document.generatorForm;
    m.action = springUrl+ '/mysqlMetadataGenerator/generator-downLoad';
    m.submit();
}

/**
 * 导入到工程
 */
function importProject(){
    $.ajax({
        type : "POST",
        async : false,
        cache : false,
        url :springUrl + "/mysqlMetadataGenerator/generator-importProject.json",
        data : {
            tableSchema: $("#tableSchema").val(),
            tableSqlName: $("#tableSqlName").val(),
            tableComment: $("#tableComment").val(),
            packagePath: $("#packagePath").val(),
            author: $("#author").val(),
            mysqlMetadataGeneratorJson: $("#mysqlMetadataGeneratorJson").val(),
            projectDirectory: $("#projectDirectory").val()
        },
        dataType : 'json',
        success : function(data) {
            Commons.showSuccess("代码生成成功");
        },
        error: function (data) {
            Commons.failure("代码生成失败");
        }
    });
}