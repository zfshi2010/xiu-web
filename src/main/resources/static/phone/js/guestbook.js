$(function () {
    $("#measurementTaskId").change(function () {
        $.get('/open/texture/listByMeasurementTaskId?measurementTaskId='+$(this).val(),function (data) {
            if (data.status=='success') {
                $('#textureId').find('option').remove();
                var textureOption = `<option value="">请选择</option>`;
                $('#textureId').append($(textureOption));
                $.each(data.data,function (i,item) {
                    textureOption = `<option value="${item.id}">${item.name}</option>`;
                    $('#textureId').append($(textureOption));
                })
            }
        });
    })
    $('.parameter').change(function () {
        if($(this).find("option:selected").attr('ifInput') == 'true') {
            $(this).next().find('.content').show();
        } else {
            $(this).next().find('.content').hide();
        }
        $(this).next().find('.ifInput').val($(this).find("option:selected").attr('ifInput'));
    });
})