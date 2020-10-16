<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration</title>
    <style>
        .main{
            margin:0px 0px;
            background-image:url("/images/back.jpg");
            background-size:100%;
        }
        .login{
            margin:200px 500px;
            width:300px;
            height:220px;
            border:1px solid #ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .name{
            text-align:center;
            margin:30px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .password{
            text-align:center;
            margin:-10px 50px;
            width:200px;
            height:30px;
            border:1px solid #000;
            outline:none;
            border-radius:10px;
        }
        .enter{
            margin:25px 110px;
            text-align:center;
            width:80px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .sign{
            margin:0px;
            text-align:center;
            color:#ffffff
        }
        .message{
            margin:5px;
            text-align:center;
        }
    </style>
</head>
<body class="main">
<div class = "login">
    <form method="post" action="/reg">
        <h2 class="sign">REGISTRATION</h2>
        <#if message?has_content>
            <p class="message" ><font color="red">${message}</font></p>
        </#if>
        <input type="text" name="username" placeholder="enter login" class="name"/>
        <input type="password" name="password" placeholder="enter password" class="password"/>
        <input type="submit" value="ENTER" class="enter"/>
<#--        <a href="http://localhost:8080/login">-->
<#--            <button type="button" class="enter">ENTER</button>-->
<#--        </a>-->
    </form>
</div>
</body>
</html>