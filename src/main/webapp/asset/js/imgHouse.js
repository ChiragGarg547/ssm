var imgHouse= {
    loadImgHousePage:function () {

        //从服务器获取所有的该用户图片
        var aData = {
            username:GParam.username
        };
        getSourceFromWeb( '/file/getOtherImgByUsername', 'get', aData,function (ImgInfo) {
            getLocalResource("html/Img.html",function (source) {
                mixDataAndTemplate(source,{data:ImgInfo,username:GParam.username},function(res){
                    $("#page-wrapper").html(res);
                });
            });
        });

    }
}