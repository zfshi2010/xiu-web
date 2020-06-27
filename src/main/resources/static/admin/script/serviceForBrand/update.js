$(function(){
    $('#add-message-form').validate({
        rules: {
            brandName        :{required:true},
            title        :{required:true},
            keywords      :{required:true},
            description      :{required:true}
        },messages:{
            brandName :{required:"必填"},
            title :{required:"必填"},
            keywords      :{required:'必填'},
            description      :{required:'必填'}
        }
    });
    $('#add-message-button').click(function(){
        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/serviceForBrand/update",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/serviceForBrand/index.html';
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
