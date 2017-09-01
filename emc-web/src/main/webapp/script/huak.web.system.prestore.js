$(function(){
    loadDataFun();
})
function loadDataFun() {

    var pageSize;
    var tbodyHtml = "";
    $.each(data.data.evaluationTable, function (index, evaluationTable) {
        var _index = index + 1;
        tbodyHtml += "<tr>" + "<td>" + _index + "</td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.dwmc + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.jlcjbm + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.hbsj + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.jbbd + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.xbbd + "</div></td>";
        tbodyHtml += "<td>" + evaluationTable.cjr + "</td>";
        tbodyHtml += "<td>" + evaluationTable.cjsj + "</td>";
        tbodyHtml += "<td><div ><a href='javascript:detailId(" + evaluationTable.projectId + ");' class='operationbtn icon-edit'></a><a href='javascript:detailId(" + evaluationTable.projectId + ");' class='operationbtn icon-delete'></a></div></td>";
        tbodyHtml += "</tr>";
    });
    $("#projectTbody").append(tbodyHtml);

    $("#redtipspad").text(data.data.pageCount);

    pageSize = Math.ceil(parseInt(data.data.pageCount) / 10);

    /*分页效果*/
    $("#paging").createPage({
        pageCount: pageSize, //总页数
        current: data.data.pageNow, //当前页数
        backFn: function (p) { //单击回调方法，p是当前页码
            console.log(p);
        }
    })

}


var data = {
    "success": "true",
    "message": "信息(包括错误信息)",
    "data": {
        "pageCount": "11112",
        "pageNow": "6",
        "evaluationTable": [
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            },
            {
                "projectId": "1",
                "dwmc": "柳芳换热站",
                "jlcjbm": "总水表",
                "hbsj": "2017-01-02 12:00:00",
                "jbbd": "23",
                "xbbd": "34",
                "jbxs": "1",
                "xbxs": "1",
                "cjr": "刘润明",
                "cjsj": "2017-01-02 12:32:33"
            }
        ]
    }
}