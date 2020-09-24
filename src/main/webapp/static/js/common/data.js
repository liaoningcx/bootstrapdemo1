/**
 * Created with IntelliJ IDEA.
 * User: zhaoming
 * Date: 13-3-23
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */


var dataOperation = {
    jsonData : [] //数据对象
};

/**
 * 返回全部数据数据.
 */
dataOperation.getJsonData = function(){
    return this.jsonData;
};

/**
 * 根据Id获取一条数据.
 */
dataOperation.findElement = function(pid){
    var jsonData = this.getJsonData();
    var result;
    $.each(jsonData,function(i){
        if(jsonData[i].pid == pid){
            result = jsonData[i];
        }
    })
    return result;
}
/**
 * 保存或更新一条数据.
 */
dataOperation.saveOrUpdateElement = function(pid, elements){
    var jsonData = this.getJsonData();
    var flag = true;
    $.each(jsonData,function(i){
        if(jsonData[i].pid == pid){
            elements.pid = pid;
            jsonData[i] = elements;
            flag = false;
        }
    })
    if(flag){
        elements.pid = pid;
        jsonData.push(elements);
    }

}

/**
 * 删除一条数据
 */
dataOperation.deleteElement = function(pid){
    var jsonData = this.getJsonData();
    $.each(jsonData,function(i){
        if(jsonData[i].pid == pid){
            dataOperation.jsonData = jsonData.deleteIndex(i);
        }
    })
}


/**
 * 根据下标删除数组中的元素
 */
Array.prototype.deleteIndex = function(index) {
    if(index < 0){
        return this;
    }else{
        return this.slice(0,index).concat(this.slice(index + 1,this.length));
    }
};

/**
 * 删除所有数据
 */
dataOperation.deleteAllElement = function(){
    dataOperation.jsonData = [];
}