$(function(){

    $('#productFieldId').change(function () {
        $.get('/api/productType/listByProductFieldId?productFieldId='+$(this).val(),function (data) {
            if (data.status=='success') {
                $('#productTypeId').find('option').remove();
                var produceTypeOption = `<option value="">请选择所属类型</option>`;
                $('#productTypeId').append($(produceTypeOption));
                $.each(data.data,function (i,item) {
                    produceTypeOption = `<option value="${item.id}">${item.name}</option>`;
                    $('#productTypeId').append($(produceTypeOption));
                })
            }
        });
    });

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage') {
            return '/upload';
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
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|','simpleupload', 'insertimage','emotion', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable'
            ]
        ]
    });

    var ueParam = UE.getEditor('editorParamContent',{toolbar: [
            [
                'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat',
                'formatmatch', 'autotypeset', 'blockquote', 'pasteplain',
                '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|',
                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                'customstyle', '|',
                'directionalityltr', 'directionalityrtl', 'indent', '|',
                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|','simpleupload', 'insertimage','emotion', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable'
            ]
        ]
    });
    
    $('#add-message-form').validate({
        rules: {
            productFieldId:{required:true},
            productTypeId:{required:true},
            name        :{required:true},
        },messages:{
            productFieldId:{required:"请选择产品领域"},
            productTypeId:{required:"请选择产品类型"},
            name :{required:"必填"},
        }
    });
    $('#add-message-button').click(function(){
        var content = ue.getContent();
        $('#content').val(content);

        var ueParamContent = ueParam.getContent();
        $('#paramContent').val(ueParamContent);

        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/produce/save",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/produce/index.html';
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
        util.formChange($('#fileName'), $(this), $('#add-message-form'), $('#img'));
    })

    $('#video-img').click(function() {
        util2.formChange($('#fileName'), $(this), $('#add-message-form'), $('#video'));
    })

    $('#pdfFile-button').click(function() {
        utilPdf.formChange($('#fileName'), $(this), $('#add-message-form'), $('#pdfFile'));
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


var utilPdf = {
    isupload: false,
    fileupload: function (form, imgDom, input, $changeDom) {
        form.attr('action', config.imgUrl + 'api/pdfUpload')
        form.off('submit').on('submit', function () {
            $(this).ajaxSubmit({
                beforeSerialize: function () {
                    utilPdf.isupload = true
                },
                success: function (data) {
                    input.val(data.url)
                    imgDom.attr('src', config.imgUrl + "img" + data.url)
                    utilPdf.isupload = false
                    $changeDom.val('')
                    alert('上传成功')
                },
                error: function () {
                    utilPdf.isupload = false
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
            if (utilPdf.isupload) {
                return alert('正在上传图片,请稍候')
            }
            if (!/\.(pdf)$/.test(e.target.value)) {
                alert('仅支持pdf格式文件上传！')
                $(this).val('')
                return false;
            }
            if (e.target.files[0].size > 20480000) {
                $(this).val('')
                return alert('文件大小不能超过20MB')
            }
            utilPdf.fileupload($form, $imgLook, $val, $changeDom)
        })
        $changeDom.trigger('click')
    }
}


var util2 = {
    isupload: false,
    fileupload: function (form, imgDom, input, $changeDom) {
        form.attr('action', config.imgUrl + 'api/videoUpload')
        form.off('submit').on('submit', function () {
            $(this).ajaxSubmit({
                beforeSerialize: function () {
                    util2.isupload = true
                },
                success: function (data) {
                    input.val(config.imgUrl + "img" + data.url)
                    imgDom.attr('src', config.imgUrl + "img" + data.url)
                    util2.isupload = false
                    $changeDom.val('')
                    alert('上传成功')
                },
                error: function () {
                    util2.isupload = false
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
            if (util2.isupload) {
                return alert('正在上传视频,请稍候')
            }
            if (!/\.(ogv|flv|mp4|avi)$/.test(e.target.value)) {
                alert('视频类型请是.ogv,flv,mp4,avi中的一种')
                $(this).val('')
                return false;
            }
            if (e.target.files[0].size > 20480000) {
                $(this).val('')
                return alert('视频大小不能超过20MB')
            }
            util2.fileupload($form, $imgLook, $val, $changeDom)
        })
        $changeDom.trigger('click')
    }
}