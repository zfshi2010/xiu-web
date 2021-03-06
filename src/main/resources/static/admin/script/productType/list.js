$(function () {
    $('#searchBtn').click(function(){
        pageaction();
    });
    $('#addUserInf').click(function(){
        create();
    });
    //初始化分页
    pageaction();
    var pg = $('.pagination');
    $('#pageSelect').on("change",function(){
        pg.trigger('setPage', [$(this).val()-1]);
    });
});

//分页的参数设置
var getOpt = function(data){
    var opt = {
        items_per_page: 10,	//每页记录数
        num_display_entries: 3, //中间显示的页数个数 默认为10
        current_page: data.data.number,	//当前页
        num_edge_entries:1, //头尾显示的页数个数 默认为0
        link_to:"javascript:void(0)",
        prev_text:"上页",
        next_text:"下页",
        load_first_page:true,
        show_total_info:true ,
        show_first_last:true,
        first_text:"首页",
        last_text:"尾页",
        ellipse_text:"...",
        hasSelect:false,
        callback: pageselectCallback //回调函数
    }
    return opt;
}
//分页开始
var currentPageData = null ;
var pageaction = function(){
    $.get('/api/productType/index?t='+new Date().getTime(),{size:10,productBrandId:$("#productBrandId").val(),name:$('#name').val()},function(data){
        currentPageData = data.data.content;
        $(".pagination").pagination(data.data.totalElements, getOpt(data));
    });
}

var pageselectCallback = function(page_index, jq, size){
    var html = "" ;
    if(currentPageData!=null){
        fillData(currentPageData);
        currentPageData = null;
    }else {
        $.get('/api/productType/index?t='+new Date().getTime(),{size:size,page:page_index, productBrandId:$("#productBrandId").val(),name:$('#name').val()
        },function(data){
            fillData(data.data.content);
        });
    }
}
//填充分页数据
function fillData(data){
    var $list = $('#tbodyContent').empty();
    $.each(data,function(k,v){
        var html = "" ;
        html += `<tr>
            <td>${v.name}</td>
            <td>${v.author}</td>
            <td>${v.createTime}</td>
            <td>
				<a class="btn btn-info btn-sm" href="/admin/productType/update.html?id=${v.id}">修改</a>
				<a class="btn btn-info btn-sm" href="javascript:void(0)" onclick="del('${v.id}')">删除</a>
			</td>
       </tr>` ;
        $list.append($(html));
    });
}

function closeDialog() {
    artdialog.close();
}

function del(id){
    if(!confirm("您确定删除此记录吗？")){
        return false;
    }
    $.ajax({
        url: "/api/productType/delete?id=" + id,
        type: 'DELETE',
        success: function(data) {
            if (data.status == 'success') {
                alert("删除成功");
                window.self.location = '/admin/productType/index.html?productBrandId=' + $('#productBrandId').val();
            } else {
                alert(data.message);
            }
        }
    });
}
