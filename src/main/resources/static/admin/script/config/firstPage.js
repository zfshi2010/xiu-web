$(function(){
    $('#saveForm').validate({
        rules: {
            title        :{required:true},
            keywords      :{required:true},
            description      :{required:true}
        },messages:{
            title :{required:"必填"},
            keywords      :{required:'必填'},
            description      :{required:'必填'}
        }
    });
    $('#saveButton').click(function(){
        if($('#saveForm').valid()){
            $.ajax({
                type: "POST",
                url: "/api/config/save",
                data: $("#saveForm").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/config/firstPage.html';
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
