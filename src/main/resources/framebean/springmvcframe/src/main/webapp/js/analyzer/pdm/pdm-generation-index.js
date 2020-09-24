/**
 * PDM代码生成页面JS
 * User: zhaoming
 * Date: 13-4-2
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */

function ajaxSubmitTest() {
    $.ajax({
        type : "GET",
        async : false,
        cache : false,
        url :springUrl + "/pdmGenerator/ajaxSubmitTest.json",
        dataType : 'json',
        success : function(data) {
            alert(data.flag);
        },
        error: function (data) {
            alert(data.responseText);
        }


    });
}
function formSubmitTest(){
    var m = document.myform;
    var url = springUrl+"/pdmGenerator/formSubmitTest";
    m.action = url;
    m.submit();
}

