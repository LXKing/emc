<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>采暖季列表</title>
    <script src="${ctx}/script/sys/huak.sys.file.list.js"></script>
</head>

<body class="gray-bg" >
<div class="wrapper wrapper-content" >
<div class="row" >
    <div class="col-sm-12" >
        <div class="ibox float-e-margins" >
            <div class="ibox-title">
                <h5>&nbsp;模板文件管理</h5>
            </div>
            <div class="ibox-content col-sm-3">
                <div class="file-manager">
                    <h5>显示：</h5>
                    <a  class="file-control active">数据</a>
                    <a  class="file-control">模板</a>
                    <a  class="file-control">文件</a>
                    <div class="hr-line-dashed"></div>
                    <button class="btn btn-primary btn-block" id="webupload" onclick="uploadFile()" >上传数据</button>
                    <button class="btn btn-primary btn-block" style="display: none;" id="templateupload" onclick="uploadModel()" >上传模板</button>
                    <button class="btn btn-primary btn-block" style="display: none;" id="fileupload" onclick="uploadFile()" >上传文件</button>
                    <div class="hr-line-dashed"></div>
                    <h5>文件夹</h5>
                    <ul class="folder-list" style="padding: 0">
                        <li><a ><i class="fa fa-folder"></i> 文件</a>
                        </li>
                        <li><a ><i class="fa fa-folder"></i> 图片</a>
                        </li>
                        <li><a ><i class="fa fa-folder"></i>pdf</a>
                        </li>
                        <li><a ><i class="fa fa-folder"></i> 视频</a>
                        </li>
                        <li><a ><i class="fa fa-folder"></i> 书籍</a>
                        </li>
                    </ul>
                    <h5 class="tag-title">查询</h5>
                    <form id="file-form">
                        <table>
                            <tr>
                                <td><input name="fileName" class="form-control" placeholder="请输入文件名" value=""></td>
                                <td><button type="button" class="btn btn-sm btn-primary" onclick="getfileList()"> 搜索
                                </button></td>
                            </tr>
                        </table>
                    </form>
                    <ul class="tag-list" style="padding: 0">
                        <%--<li><a href="file_manager.html">爱人</a>--%>
                        <%--</li>--%>
                    </ul>
                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="col-sm-9 animated fadeInRight" style="margin-top: 20px;">
            <div class="row">
            <div class="col-sm-12" >
            <div class="file-box">
                <div class="file">
                    <a href="file_manager.html#">
                        <span class="corner"></span>
                        <div class="icon">
                            <i class="fa fa-file"></i>
                        </div>
                        <div class="file-name">
                            Document_2014.doc
                            <br/>
                            <small>添加时间：2014-10-13</small>
                        </div>
                    </a>
                </div>

            </div>

            <div class="file-box">
                <div class="file">
                    <a href="file_manager.html#">
                        <span class="corner"></span>

                        <div class="icon">
                            <i class="fa fa-music"></i>
                        </div>
                        <div class="file-name">
                            Michal Jackson.mp3
                            <br/>
                            <small>添加时间：2014-10-13</small>
                        </div>
                    </a>
                </div>
            </div>


            <div class="file-box">
                <a href="file_manager.html#">
                    <div class="file">
                        <span class="corner"></span>

                        <div class="icon">
                            <i class="fa fa-bar-chart-o"></i>
                        </div>
                        <div class="file-name">
                            Annual report 2014.xls
                            <br/>
                            <small>添加时间：2014-10-13</small>
                        </div>
                    </div>
                </a>
            </div>
            <div class="file-box">
                <div class="file">
                    <a href="file_manager.html#">
                        <span class="corner"></span>

                        <div class="icon">
                            <i class="fa fa-file"></i>
                        </div>
                        <div class="file-name">
                            Document_2014.doc
                            <br/>
                            <small>添加时间：2014-10-13</small>
                        </div>
                    </a>
                </div>

            </div>
            </div>
            </div>
            </div>
        </div>
    </div>
</div>

</div>

<div id="layer-div" style="display: none"></div>

</body>

</html>

