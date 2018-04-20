var img = {
    uploadImg : function () {
        var formData = new FormData();
        var uploadFile = $("#img_up").get(0).files[0];
        var username = 'zcy';
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
}