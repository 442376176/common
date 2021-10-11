<%--
  Created by IntelliJ IDEA.
  User: iweb002
  Date: 2020/8/6
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<h1>
    秒杀！！！
</h1>
<form id="zcc_from" action="${pageContext.request.contextPath}/doseckill"
enctype="application/x-www-form-urlencoded">
    <input type="hidden" id="prodid" name="prodid" value="0101">
    <input type="button" id="zcc_btn" name="seckill_btn" value="秒杀点这里"/>
</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery/jquery-3.1.1.min.js"/>
<script type="text/javascript">
    $(function () {
        $("zcc_btn").click(function () {
            var url = $("zcc_from").attr("action");
            $.post(url,$("zcc_from").serialize(),function (data) {
                if (data=="false"){
                    alert("已抢光");
                    $("zcc_btn").attr("disabled",true);
                }
            });
        })
    })
</script>
</html>
