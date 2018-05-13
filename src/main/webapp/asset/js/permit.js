var permit = {
    permitList:[],
    loadPermitPage:function () {
        permit.permitList = [];
        //从服务器获取所有的该用户的授权申请
        var aData = {
            username:GParam.username
        };
        getSourceFromWeb( '/permit/gerAllPermitData', 'get', aData,function (permitInfo) {
            permit.permitList = permitInfo;
            getLocalResource("html/permit.html",function (source) {
                mixDataAndTemplate(source,{data:permitInfo},function(res){
                    $("#page-wrapper").html(res);
                });
            });
        });
    }
    ,authImg:function (i) {
        var aData = {
            pId:permit.permitList[i].pId
        };
        getSourceFromWeb( '/permit/authAccess', 'get', aData,function (res) {
            $("#ptr" + i).remove();
        });
    }
}