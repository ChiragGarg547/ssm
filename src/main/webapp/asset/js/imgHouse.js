var imgHouse= {
    imgList : [],
    imgIndex: -1
    ,loadImgHousePage:function () {
        imgHouse.imgList = []
        imgHouse.imgIndex = -1
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
    ,downloadImg:function () {//校验并下载
        var pubKey = $("#Key").val();
        var imgId = imgHouse.imgList[imgHouse.imgIndex].imgId
        var aData = {
            imgId: imgId,
            pubKey: pubKey
        }
        getSourceFromWeb('/file/downLoadImg' , 'get' , aData, function (res) {

        })
    }
    ,getImgPubKey:function (i) {
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
    ,hideDownModal:function () {
        $("#downLoadModal").modal('hide');
    }
    ,showDownModal:function (i) {
        imgHouse.imgIndex = i;
        $("#downLoadModal").modal('show');
    }
}