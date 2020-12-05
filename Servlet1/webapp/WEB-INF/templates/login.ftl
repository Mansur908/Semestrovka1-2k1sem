<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        .main{
            margin:0px 0px;
            background-image:url("/images/back3.jpg");
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
            margin:15px 50px;
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
            margin:17px 110px 10px;
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
        .reg{
            margin:0px 94px;
            text-align:center;
            width:115px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .enter:active{
            margin:16px 110px;
        }
        .reg:active{
            margin:-12px 94px;
        }
    </style>
</head>
<body class="main">
<div class = "login">
    <form method="post" action="/login">
        <h2 class="sign">SIGN IN</h2>
        <#if message?has_content>
            <p class="message" ><font color="red"><b>${message}</b></font></p>
        </#if>
        <input id="login1" type="text" name="username" placeholder="enter login" class="name"/>
        <input type="password" name="password" placeholder="enter password" class="password"/>
        <input type="submit" value="ENTER" class="enter"/>
        <a href="http://localhost:8080/reg">
            <button type="button" class="reg">REGISTRATION</button>
        </a>
    </form>
</div>
<script type="text/javascript">
    function max() {
        document.getElementById('login1').setAttribute("maxlength","12");
    }
    max()
</script>
</body>
</html>