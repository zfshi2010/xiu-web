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
    $('#saveForm').validate({
        rules: {
            title        :{required:true},
            content      :{required:true},
            userName     :{required:true},
            publishPlace :{required:true}
        },messages:{
            title :{required:"必填"},
            content :{required:"必填"},
            userName     :{required:"必填"},
            publishPlace :{required:"必填"}
        }
    });
    $('.saveBtn').click(function(){
        var content = ue.getContent();
        $('#content').val(content);
        if($('#saveForm').valid()){
            $.ajax({
                type: "POST",
                url: "/bsArticles",
                data: $("#saveForm").serialize(),
                headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
                success: function (data) {
                    if (data.status == 'success') {
                        alert("保存成功");
                        window.self.location = '/article/index';
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

