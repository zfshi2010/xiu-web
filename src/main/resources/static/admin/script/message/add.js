$(function(){
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
            return '/imgUpload';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
    var ue = UE.getEditor('editor',{toolbar: [
            [
                'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat',
                'formatmatch', 'autotypeset', 'blockquote', 'pasteplain',
                '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|','simpleupload', 'insertimage','|',
                'simpleupload', 'emotion', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable'
            ]
        ]
    });
    $('#add-message-form').validate({
        rules: {
            title        :{required:true},
            content      :{required:true}
        },messages:{
            title :{required:"必填"},
            content :{required:"必填"}
        }
    });
    $('#add-message-button').click(function(){
        var content = ue.getContent();
        $('#content').val(content);
        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/message/save",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/message/index.html';
                    } else {
                        alert(data.message);
                    }
                }
            });
        }else{
            // alert('数据验证失败，请检查！');
        }
    });

    $('#small-img').click(function() {
        util.formChange($('#fileName'), $(this), $('#add-message-form'), $('#smallImg'))
    })
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

