var img = {
    uploadImg : function () {
        var formData = new FormData();
        var uploadFile = $("#img_up").get(0).files[0];
        var username = GParam.username;
        formData.append('uploadFile',uploadFile);
        formData.append('username',username);
        $.ajax({
            url:'/file/imgUpload',
            type:'POST',
            data:formData,
            async: false,
            cache: false,
            contentType: false, //不设置内容类型
            processData: false, //不处理数据
            success:function(data){
                console.log(data);
                alert(data.msg);
            },
            error:function(){
                alert("上传失败！");
            }
        });
    }
    ,loadImgPage:function () {
        //从服务器获取所有的该用户图片
        var aData = {
            username:GParam.username
        }
        getSourceFromWeb( '/file/getImgByUsername', 'get', aData,function (ImgInfo) {
            getLocalResource("html/Img.html",function (source) {
                mixDataAndTemplate(source,{data:ImgInfo},function(res){
                    $("#page-wrapper").html(res);
                });
            });
        })
    }
    ,showUploadModal:function () {
        $("#uploadModal").modal('show');
    }
}