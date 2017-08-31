
$(function () {
    loadDataFun();
});

function loadDataFun() {

    // http://www.treejs.cn/v3/main.php#_zTreeInfo

    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, nodes);



    var pageSize;
    var tbodyHtml = "";
    $.each(data.data.evaluationTable, function (index, evaluationTable) {
        var _index = index + 1;
        tbodyHtml += "<tr>" + "<td>" + _index + "</td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.projectName + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.projectNumber + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.projectContent + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.implemenPurpose + "</div></td>";
        tbodyHtml += "<td><div class='text-left'>" + evaluationTable.personinCharge + "</div></td>";
        tbodyHtml += "<td>" + evaluationTable.investmentIntensity + "</td>";
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

var nodes = [
    { id: 1, pId: 0, name: "计量采集管理" },
    { id: 11, pId: 1, name: "换表记录",url :_web+"/record/change",target:"_self" },
    { id: 12, pId: 1, name: "预存记录",url :_web+"/record/prestore",target:"_self" }
];

var data = {
    "success": "true",
    "message": "信息(包括错误信息)",
    "data": {
        "pageCount": "11112",
        "pageNow": "6",
        "evaluationTable": [
            {
                "projectId": "1",
                "projectName": "2013年无人值守站项目",
                "projectNumber": "HUAK90070152",
                "projectContent": "2013年两会周转项目",
                "implemenPurpose": "节能改造",
                "personinCharge": "刘经理",
                "investmentIntensity": "600元/T(标煤)"
            },
            {
                "projectId": "2",
                "projectName": "2014年二次网站项目",
                "projectNumber": "HUAK90070816",
                "projectContent": "2014年两会周转项目",
                "implemenPurpose": "自动化改造",
                "personinCharge": "张云飞",
                "investmentIntensity": "1200元/T(标煤)"
            },
            {
                "projectId": "3",
                "projectName": "2013年无人值守站项目",
                "projectNumber": "HUAK90070152",
                "projectContent": "2013年两会周转项目",
                "implemenPurpose": "节能改造",
                "personinCharge": "刘经理",
                "investmentIntensity": "600元/T(标煤)"
            },
            {
                "projectId": "4",
                "projectName": "2014年二次网站项目",
                "projectNumber": "HUAK90070816",
                "projectContent": "2014年两会周转项目",
                "implemenPurpose": "自动化改造",
                "personinCharge": "张云飞",
                "investmentIntensity": "1200元/T(标煤)"
            },
            {
                "projectId": "5",
                "projectName": "2013年无人值守站项目",
                "projectNumber": "HUAK90070152",
                "projectContent": "2013年两会周转项目",
                "implemenPurpose": "节能改造",
                "personinCharge": "刘经理",
                "investmentIntensity": "600元/T(标煤)"
            },
            {
                "projectId": "6",
                "projectName": "2014年二次网站项目",
                "projectNumber": "HUAK90070816",
                "projectContent": "2014年两会周转项目",
                "implemenPurpose": "自动化改造",
                "personinCharge": "张云飞",
                "investmentIntensity": "1200元/T(标煤)"
            },
            {
                "projectId": "7",
                "projectName": "2013年无人值守站项目",
                "projectNumber": "HUAK90070152",
                "projectContent": "2013年两会周转项目",
                "implemenPurpose": "节能改造",
                "personinCharge": "刘经理",
                "investmentIntensity": "600元/T(标煤)"
            },
            {
                "projectId": "8",
                "projectName": "2014年二次网站项目",
                "projectNumber": "HUAK90070816",
                "projectContent": "2014年两会周转项目",
                "implemenPurpose": "自动化改造",
                "personinCharge": "张云飞",
                "investmentIntensity": "1200元/T(标煤)"
            },
            {
                "projectId": "9",
                "projectName": "2013年无人值守站项目",
                "projectNumber": "HUAK90070152",
                "projectContent": "2013年两会周转项目",
                "implemenPurpose": "节能改造",
                "personinCharge": "刘经理",
                "investmentIntensity": "600元/T(标煤)"
            },
            {
                "projectId": "10",
                "projectName": "2014年二次网站项目",
                "projectNumber": "HUAK90070816",
                "projectContent": "2014年两会周转项目",
                "implemenPurpose": "自动化改造",
                "personinCharge": "张云飞",
                "investmentIntensity": "1200元/T(标煤)"
            }
        ]
    }
}