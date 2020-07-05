$(function(){
    $('#add-message-form').validate({
        rules: {
            name        :{required:true},
        },messages:{
            name :{required:"测量任务名称不能为空"},
        }
    });
    $('#add-message-button').click(function(){
        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/measurementTask/save",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/measurementTask/index.html';
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
