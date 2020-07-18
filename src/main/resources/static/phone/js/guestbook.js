$(function () {
    $("#measurementTaskId").change(function () {
        $.get('/open/measurementTaskType/listByMeasurementTaskId?measurementTaskId='+$(this).val(),function (data) {
            if (data.status=='success') {

                $('#measurementTaskTypeId').find('option').remove();
                var measurementTaskTypeOption = `<option value="">请选择</option>`;
                $('#measurementTaskTypeId').append($(measurementTaskTypeOption));

                $('#textureId').find('option').remove();
                var textureOption = `<option value="">请选择</option>`;
                $('#textureId').append($(textureOption));

                $.each(data.data,function (i,item) {
                    measurementTaskTypeOption = `<option value="${item.id}">${item.name}</option>`;
                    $('#measurementTaskTypeId').append($(measurementTaskTypeOption));
                })
            }
        });
    });

    $("#measurementTaskTypeId").change(function () {
        //获取材质
        $.get('/open/texture/listByMeasurementTaskTypeId?measurementTaskTypeId='+$(this).val(),function (data) {
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
        //获取其他属性
        $.get('/open/parameter/listByMeasurementTaskTypeId?measurementTaskTypeId='+$(this).val(),function (data) {
            if (data.status=='success') {
                $('#other-params').find('div').remove();
                $.each(data.data, function (stat,parameter) {
                    var divTemp =
                        `<div class="form_1">
                             <label><span>${parameter.name}</span><em></em></label>
                             <select class="parameter" id="${'guestbookParameterValues[' + stat +'].parameterValueId'}" name="${'guestbookParameterValues[' + stat +'].parameterValueId'}">
                                 <option value="">请选择</option>`;
                    parameter.parameterValues.forEach(function(parameterValue){
                        divTemp+=`<option value="${parameterValue.id}" ifInput="${parameterValue.ifInput}">${parameterValue.value}</option>`
                    })
                    divTemp += `</select>
                             <div>
                                 <input class="content" id="${'guestbookParameterValues[' + stat +'].content'}" name="${'guestbookParameterValues[' + stat +'].content'}" type="text" style="display: none"/>
                                 <input class="ifInput" id="${'guestbookParameterValues[' + stat +'].ifInput'}" name="${'guestbookParameterValues[' + stat +'].ifInput'}" type="hidden" value="false"/>
                                 <input id="${'guestbookParameterValues[' + stat +'].parameterId'}" name="${'guestbookParameterValues[' + stat +'].parameterId'}" type="hidden" value="${parameter.id}"/>
                                 <input id="${'guestbookParameterValues[' + stat +'].parameterName'}" name="${'guestbookParameterValues[' + stat +'].parameterName'}" type="hidden" value="${parameter.name}"/>
                             </div>
                         </div>`;

                    $('#other-params').append($(divTemp));

                    $('.parameter').change(function () {
                        if($(this).find("option:selected").attr('ifInput') == 'true') {
                            $(this).next().find('.content').show();
                        } else {
                            $(this).next().find('.content').hide();
                        }
                        $(this).next().find('.ifInput').val($(this).find("option:selected").attr('ifInput'));
                    });
                });
            }
        });
    });


    $('#myform').validate({
        rules: {
            measurementTaskId        :{required:true},
            measurementTaskTypeId    :{required:true},
            textureId        :{required:true},
            companyName        :{required:true},
            name        :{required:true},
            phone:{required:true}
        },messages:{
            measurementTaskId        :{required:"请选择测量任务！"},
            measurementTaskTypeId    :{required:"请选择产品类型！"},
            textureId        :{required:"请选择材质！"},
            companyName        :{required:"请输入公司名称!"},
            name :{required:"请输入姓名！"},
            phone:{required:"请输入联系电话！"}
        }
    });
    $('#save-button').click(function(){
        if($('#myform').valid()){
            $('#myform').attr('action', '/phone/guestbook/save.html');
            $('#myform').off('submit').submit();
        } else{
            // alert('数据验证失败，请检查！');
        }
    });

})