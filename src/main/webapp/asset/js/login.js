var login={
    loginCheck:function(){
        var l_username = $("#username").val()
        var l_password = $("#password").val()
        var aData = {
            username:l_username,
            password:l_password
        }
        getSourceFromWeb( '/user/checkLogin', 'get', aData, function(res){
            if(res.success){
                alert("登陆成功！");
                getLocalResource('html/mainpage.html',function (source) {
                    var data={};
                    mixDataAndTemplate(source,data,function (html) {
                        $("#mainContainer").html(html);
                    });
                });
            }else{
                alert("密码错误！");
            }
        })
    }
    ,register:function(){
        var l_username = $("#username").val()
        var l_password = $("#password").val()
        var aData = {
            username:l_username,
            password:l_password
        }
        getSourceFromWeb( '/user/register', 'get', aData, function(res){
            if(res){
                alert("注册成功！");
            }else{
                alert("注册失败！");
            }
        })
    }
}