<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="touming_bg"></div>
<!--收货成功-->
        <div class="shouhuo_success" id="Confirm_goods_success_div">
         
          <div class="shuohuo_pup">
              <p class="pup_text1">收货成功</p>
              <p class="pup_text2">客服将为您预约客户，您也可在详情中查看客户信息主动联系预约。</p>
           </div>
        </div>

        <!--确认收货-->
        <div class="Confirm_goods" id="Confirm_goods_div">
           <span class="quxiao_close">x</span>
            <div class="sure_pup">
            <p class="pup_text1">您确认要收货吗?</p>
            <p class="pup_text2">为避免不必要的损失，请在收货后确认！</p>
            </div>
            
            <div class="pup_btn"><a href="javascript:void();" class="confirm_window" id="confirm_window_href">确认</a><a href="javascript:void();" class="cancel_window" onclick="goodhide()">取消</a></div>
        </div>
        
        <!--修改失败-->
        <div class="popup_box" id="update_fail">
         
           <div class="failure" >
            <p class="pup_text1"><img src="../../images/sure_icon.png"/><span id="fail_title"></span></p>
            <p class="pup_text2" id="fail_msg"></p>
            <div class="sure_btn_down"><a id="failConfirm" href="javascript:void();" class="confirm_btn1 yj">确&nbsp;认</a></div>
            </div>
        </div>

         <!--修改成功-->
         <div class="popup_box  xg_success" id="update_success"
             <p class="pup_text1"><%--修改成功--%></p>
         </div>
         
            <!--确认提款-->
        <div class="Confirm_goods" id="sure_tikuan_pup">
           <span class="quxiao_close" onclick="tk_hide();">x</span>
            <div class="sure_pup">
            <p class="pup_text1">您确认要提款吗?</p>
            <p class="pup_text2">本次提款金额为<span>${orderCanDraw}</span>元</p>
            </div>
            
            <div class="pup_btn"><a href="javascript:void(0);"  onclick="qr_tijiao()" class="confirm_window">确认</a><a href="javascript:tk_hide();" class="cancel_window">取消</a></div>
        </div>

      <!--提款异常-->
        <div class="popup_box" id="tk_abnormal">
           <div class="failure" style="padding-left:25px;">
            <p class="pup_text1"><img src="../../images/sure_icon.png"/><span id="abnormal_title"></span></p>
            <div class="sure_btn_down"><a href="javascript:void(0);" class="confirm_btn1 yj" id="iknow" style="margin-left:60px;">知道了</a></div>
            </div>
        </div>
