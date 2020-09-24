/**
 * 模板维护页JS
 * User: zhaoming
 * Date: 13-4-2
 * Time: 下午5:23
 * To change this template use File | Settings | File Templates.
 */
var ue;
$(function(){
    ue = UE.getEditor('editor');
});

function save(){
    jQuery.ajax({
        type:"POST",
        cache:false,
        dataType : 'json',
        data:{
            template:ue.getPlainTxt()
        },
        url:springUrl + "/template/save.json",
        success:function (data) {
            Commons.showSuccess("保存成功");
        },
        error: function (data) {
            Commons.failure("保存失败");
        }
    });
}

