$(function () {
    //初始化分页
    getTheData();

    $('#add-button').click(function(){
        if (!$('#parameterId').val()) {
            alert("请选择要绑定的属性！");
            return
        }
        $.ajax({
            type: "POST",
            url: "/api/measurementTaskTypeParameter/save",
            data: $("#add-message-form").serialize(),
            headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
            success: function (data) {
                if (data.status == 'success') {
                    alert("保存成功");
                    getTheData();
                } else {
                    alert(data.message);
                }
            }
        });
    });
});

function getTheData() {
    $.get('/api/measurementTaskTypeParameter/index?t='+new Date().getTime(),{measurementTaskTypeId:$("#measurementTaskTypeId").val()},function(data){
        fillData(data.data);
    });
}
//填充分页数据
function fillData(data){
    var $list = $('#tbodyContent').empty();
    $.each(data,function(k,v){
        var html = "" ;
        html += `<tr>
            <td>${v.name}</td>
            <td>${v.createTime}</td>
            <td>
				<a class="btn btn-info btn-sm" href="javascript:void(0)" onclick="del('${v.id}')">删除</a>
			</td>
       </tr>` ;
        $list.append($(html));
    });
}

function del(id){
    if(!confirm("您确定删除此记录吗？")){
        return false;
    }
    $.ajax({
        url: "/api/measurementTaskTypeParameter/delete?id=" + id,
        type: 'DELETE',
        success: function(data) {
            if (data.status == 'success') {
                alert("删除成功");
                getTheData();
            } else {
                alert(data.message);
            }
        }
    });
}
