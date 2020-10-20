<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello!</title>
    <style>
        .main{
            margin:0px 0px;
            background-image:url("/images/back.jpg");
            background-size:100%;
        }
        .exit{
            /*margin:0px 0px 0px 1200px;*/
            text-align:center;
            width:80px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .change{
            margin:17px 0px 0px 1070px;
            text-align:center;
            width:120px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent;
        }
    </style>
</head>
<body class="main">
<form method="post" action="/profile">
<#--    <input type="submit" value="change password" name="change" class="change"/>-->
    <a href="http://localhost:8080/change">
        <button type="button" class="change">change password</button>
    </a>
    <input type="submit" value="exit" name="exit" class="exit"/>
</form>
</body>
</html>