$(function(){
    $.validator.addMethod("checkPassword", function (value, element) {
        var checkPassword = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}$/;
        return this.optional(element) || (checkPassword.test(value));
    }, "密码格式不正确，密码必须包含字母、数字、特殊符号，且长度范围为8到30位!");
    $('#add-message-form').validate({
        rules: {
            username        :{required:true,rangelength:[5,20]},
            pwd        :{required:true,checkPassword:true},
            rePwd: {equalTo: "#pwd"},
        },messages:{
            username :{required:"用户名不能为空",rangelength:"输入长度为5到10"},
            pwd :{required:"密码不能为空",checkPassword:"密码格式不正确，密码必须包含字母、数字、特殊符号，且长度范围为8到30位!"},
            rePwd: {equalTo: "两次密码不一致！"},
        }
    });
    $('#add-message-button').click(function(){
        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/user/user",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/user/index.html';
                    } else {
                        alert(data.message);
                    }
                }
            });
        }else{
            // alert('数据验证失败，请检查！');
        }
    });
});
