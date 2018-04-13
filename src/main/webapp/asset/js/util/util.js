
//从服务器获取资源
function getSourceFromWeb(aUrl,method,aData,aCallback){
	$.ajax({
		url: aUrl ,
		type: method,
        async:false,
		contentType: 'application/json;charset=utf-8',
        data: aData,
        dataType:'json',
		success: function(data){
            aCallback(data);
        },
		error:function(err){
			alert(err);
		}
	});
}

//获取本地资源
function getLocalResource(path,aCallback) {
    $.ajax({
        type: "GET",
        url: path,
        dataType: "text",
        success: function(res) {
            aCallback(res);
        },
        error: function(err) {
            alert(err);
        }
    });
}

//模板合成
function mixDataAndTemplate(source,data,aCallback) {
    var bt = baidu.template;
    var backStr = bt(source,data);
    aCallback(backStr);
}

//sublime激活码
/*
TwitterInc
200 User License
EA7E-890007
1D77F72E 390CDD93 4DCBA022 FAF60790
61AA12C0 A37081C5 D0316412 4584D136
94D7F7D4 95BC8C1C 527DA828 560BB037
D1EDDD8C AE7B379F 50C9D69D B35179EF
2FE898C4 8E4277A8 555CE714 E1FB0E43
D5D52613 C3D12E98 BC49967F 7652EED2
9D2D2E61 67610860 6D338B72 5CF95C69
E36B85CC 84991F19 7575D828 470A92AB
*/