<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() +"://" +
          request.getServerName() + ":" +
          request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <title>用户评价</title><meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
    <link rel="alternate icon" type="image/png" href="<%=basePath%>/images/logo_40.png">
    <link href="<%=basePath%>/css/link.css" rel="stylesheet" />
    <link href="<%=basePath%>/css/page.css" rel="stylesheet" />
    <link href="<%=basePath%>/css/common.css" rel="stylesheet" />
    <link href="<%=basePath%>/css/tikuan.css" rel="stylesheet" />
    <script src="<%=basePath%>/common/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/common/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/basic.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/validate.css?v=20160312"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/comment.css?v=20160516"/>
    <style>
        .photos-thumb {list-style-type: none;margin: 10px 0;}
        .photos-thumb li{float:left;border:2px solid #f2f2f2;*zoom:1;padding:2px;margin-right:8px;position:relative;transition:border-color .2s ease-out}
        .photos-thumb li.curren{border:2px solid #f03061;}
        .photos-thumb li span{position:absolute; bottom:-7px; right:22px; height:7px;width:13px; background-image:url(http://static.qichechaoren.com/upload/2015/09/choose.png); display:none;}
        .photos-thumb li.curren span{ display:block;}
        .photos-thumb li img{ width:60px;height:60px;cursor:pointer}
        .imgxob{clear:both;padding:10px 0; display:none;}
        .imgxob .bigimg .toleft,.imgxob .toright{height:100%;width:50%;position:absolute;}
        .imgxob .bigimg .toleft{left:0; top:0;}
        .imgxob .bigimg .toright{right:0; top:0;}
        .imgxob .bigimg .prev{position:absolute; top:125px; left:5px; display:none;cursor: pointer;width:30px;height:30px;z-index:100;}
        .imgxob .bigimg .next{position:absolute; top:125px; right:5px; display:none;cursor: pointer;width:30px;height:30px;z-index:100;}
        .imgxob .bigimg{width:320px; height:320px;position:relative;padding: 3px;  border: 2px solid #eaeaea;}
        .imgxob .bigimg>img{width:100%; height:100%; }
    </style>
    <script type="text/javascript">
        var basePath = '<%=basePath%>';
    </script>
</head>
<body>
<div class="content">
  <jsp:include page="../addon/link.jsp"></jsp:include>
  <div class="box_right">
    <jsp:include page="../addon/head.jsp"></jsp:include>
      <div class="wrap_right">
          <div class="title_mune">用户评价</div>
          <div class="radiu">
              <div class="user_top clearfix">
                  <div class="uleft_wrap">
                      <p><span id="store_name_span"></span></p>
                      <div class="star_down">
                          <div class="star_img"><span id="score_star_img"><img src="<%=basePath%>/images/start1.png"/><img src="<%=basePath%>/images/start1.png"/><img src="<%=basePath%>/images/start1.png"/><img src="<%=basePath%>/images/start1.png"/><img src="<%=basePath%>/images/start1.png"/></span></div>
                          <div class="user_score"><span id="store_score_left"></span>分</div>
                      </div>
                  </div>
                  <div class="uright_wrap" id="ruler" style="display:none;">
                      <span class="progress_icon" id="store_score_right"></span>
                  </div>
              </div>
              <div class="tk_time user_service">
              <table><tr>
              <td><span>服务项目</span>
                  <input type = "hidden"  name="rank_hidden" id="rank_hidden" value="-1" />
                  <input type = "hidden"  name="page_offset" id="page_offset" value="0" />
                  <input type = "hidden"  name="page_size" id="page_size" value="20" />
                  <input type = "hidden"  name="pageSize" id="pageSize" value="20" />
                  <select id="serviceParentId" name="serviceParentId" onchange="serviceSelectOnchange();" class="s_obj">
                      <option value="">全部</option>
                  </select>
                  <select id="serviceId" name="serviceId" class="s_obj">
                      <option value="">全部</option>
                  </select></td>
              <td width="500"><span style="padding-left:70px;">评价时间</span><input type="text" class="Wdate" id="createTimeStart" name="createTimeStart" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createTimeEnd\')||\'%y-%M-%d\'}'})"
                                                                     style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" /> --
                                                                  <input type="text" class="Wdate" id="createTimeEnd" name="createTimeEnd" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createTimeStart\')}',maxDate:'%y-%M-%d'})"
                                                                     style="width:129px; height:30px; margin-left:5px; border:#eee 1px solid;" />
               </td>
              <td class="down_btn_tr"><a href="#" class="look_btn" onclick="searchOnclick();" >查&nbsp;找</a>
                  <a href="#" class="reset_btn" onclick="reset();">重&nbsp;置</a></td></tr></table>

              </div>
              <div class="user_evaluate">
                  <ul class="year_nav pinlun_list">
                      <li rank="-1" class="on">全部（<span id="total_comment_choose_span">0</span>）</li>
                      <li rank="3">有图（<span id="image_comment_choose_span">0</span>）</li>
                      <li rank="2">好评（<span id="good_comment_choose_span">0</span>）</li>
                      <li rank="1">中评（<span id="medium_comment_choose_span">0</span>）</li>
                      <li rank="0">差评（<span id="bad_comment_choose_span">0</span>）</li>
                  </ul>
                  <div class="u_list_pj clearfix" id="data_list_div"></div>
                  <div id="no_data_div" style="width:112px;height:158px; margin:0 auto;display: none;"><img src="<%=basePath%>/images/no_pingjia.jpg"></div>
              </div>
              <div class="page clearfix"> </div>
          </div> <!--radiu-->
        
      </div><!--wrap_right-->
  </div>
</div>
<script src="<%=basePath%>/page/comment/user_comment.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/js/page-control.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/util.js"></script>

</body>
</html>
