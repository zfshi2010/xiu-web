$(function(){
    $('#add-message-form').validate({
        rules: {
            title        :{required:true},
        },messages:{
            title :{required:"必填"},
        }
    });
    $('#add-message-button').click(function(){
        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/banner/update",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/banner/index.html';
                    } else {
                        alert(data.message);
                    }
                }
            });
        }else{
            // alert('数据验证失败，请检查！');
        }
    });

    $('#img-img').click(function() {
        util.formChange($('#fileName'), $(this), $('#add-message-form'), $('#img'))
    })
});


var util = {
    isupload: false,
    fileupload: function (form, imgDom, input, $changeDom) {
        form.attr('action', config.imgUrl + 'api/imageUpload')
        form.off('submit').on('submit', function () {
            $(this).ajaxSubmit({
                beforeSerialize: function () {
                    util.isupload = true
                },
                success: function (data) {
                    input.val(config.imgUrl + "img" + data.url)
                    imgDom.attr('src', config.imgUrl + "img" + data.url)
                    util.isupload = false
                    $changeDom.val('')
                    alert('上传成功')
                },
                error: function () {
                    util.isupload = false
                    alert('上传异常')
                }
            })
            return false
        })
        form.trigger('submit')
    },
    formChange: function ($changeDom, $imgLook, $form, $val) {
        $changeDom.off('change').on('change', function (e) {
            e.preventDefault()
            if (util.isupload) {
                return alert('正在上传图片,请稍候')
            }
            if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(e.target.value)) {
                alert('图片类型请是.gif,jpeg,jpg,png中的一种')
                $(this).val('')
                return false;
            }
            if (e.target.files[0].size > 20480000) {
                $(this).val('')
                return alert('图片大小不能超过20MB')
            }
            util.fileupload($form, $imgLook, $val, $changeDom)
        })
        $changeDom.trigger('click')
    }
}