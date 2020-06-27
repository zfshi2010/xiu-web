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
                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                'simpleupload', 'emotion', '|',
                'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                'inserttable', 'deletetable'
            ]
        ]
    });
    ue.addListener("ready", function () {
        ue.setContent($('#content').val());
    });
    $('#add-message-form').validate({
        rules: {
            title        :{required:true},
        },messages:{
            title :{required:"必填"},
        }
    });
    $('#add-message-button').click(function(){
        var content = ue.getContent();
        $('#content').val(content);
        if($('#add-message-form').valid()){
            $.ajax({
                type: "POST",
                url: "/api/serviceForTypeMessage/update",
                data: $("#add-message-form").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/admin/serviceForTypeMessage/index.html?serviceForTypeId=' + $('#serviceForTypeId').val();
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
