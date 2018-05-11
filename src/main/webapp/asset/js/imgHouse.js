var imgHouse= {
    imgList : ''
    ,loadImgHousePage:function () {

        new ClipboardJS('#clipBtn');
        //从服务器获取所有的该用户图片
        var aData = {
            username:GParam.username
        };
        getSourceFromWeb( '/file/getOtherImgByUsername', 'get', aData,function (ImgInfo) {
            imgHouse.imgList = ImgInfo;
            getLocalResource("html/ImgHouse.html",function (source) {
                mixDataAndTemplate(source,{data:ImgInfo},function(res){
                    $("#page-wrapper").html(res);
                    new Clipboard('.btn');
                });
            });
        });

    }
    ,askPermit:function (i) {
        var img = imgHouse.imgList[i]
        var aData = {
            imgId : img.imgId,
            username : GParam.username
        }
        getSourceFromWeb('/permit/askForImgPermit' , 'get' , aData, function (res) {

        })
    }
    ,downloadImg:function () {

    }
    ,getImgPubKey:function (i, obj) {
        var img = imgHouse.imgList[i]
        var aData = {
            imgId : img.imgId,
            username : GParam.username
        }
        getSourceFromWeb('/permit/getImgPubKey' , 'get' , aData, function (res) {
            var pubKey = res.publicKey;
            $("#pubKey").val(pubKey);
            imgHouse.showKeyModal();
        })
    }
    ,hideKeyModal:function () {
        $("#keyClipModal").modal('hide');
    }
    ,showKeyModal:function () {
        $("#keyClipModal").modal('show');
    }
}