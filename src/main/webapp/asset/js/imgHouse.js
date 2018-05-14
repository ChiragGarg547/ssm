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
        getSourceFromWeb( GParam.url + '/file/getOtherImgByUsername', 'get', aData,function (ImgInfo) {
            imgHouse.imgList = ImgInfo;
            getLocalResource("html/ImgHouse.html",function (source) {
                mixDataAndTemplate(source,{data:ImgInfo},function(res){
                    $("#page-wrapper").html(res);
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
        getSourceFromWeb(GParam.url + '/permit/askForImgPermit' , 'get' , aData, function (res) {
        })
    }
    ,downloadImg:function () {//校验并下载
        var pubKey = $("#Key").val();
        var imgId = imgHouse.imgList[imgHouse.imgIndex].imgId
        var aData = {
            imgId: imgId,
            pubKey: pubKey
        }
        getSourceFromWeb(GParam.url + '/file/downLoadImg' , 'get' , aData, function (res) {

            var url = res.url;
            var site = window.location.href;
            window.open(site.replace('mainpage.html',url))
        })
    }
    ,getImgPubKey:function (i) {
        var img = imgHouse.imgList[i]
        var aData = {
            imgId : img.imgId,
            username : GParam.username
        }
        getSourceFromWeb(GParam.url + '/permit/getImgPubKey' , 'get' , aData, function (res) {
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