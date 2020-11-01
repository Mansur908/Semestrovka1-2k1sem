<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>

    <style>
        .main{
            margin:0px 0px;
            background:linear-gradient(100deg,#cc2b5e,#753a88);
            background-size:100%;
        }
        .head{
            margin:100px 0px 0px 0px;
            text-align:center;
            color:#ffffff;
        }
        .head1{
            margin:130px 0px;
            text-align:center;
            color:#ffffff;
        }
        .start{
            text-align:center;
            position:absolute;
            left:38%;
            top:210px;
            width:300px;
            height:115px;
            border:transparent;
            outline:none;
            color:#4372af;
            border-radius:10px;
            font-size:150%;

        }
        .start:active{
            margin:2px 2px;
            width:296px;
            height:111px;
        }
    </style>

</head>
<body class="main">
<#if message?has_content>
    <p>${message}</p>
</#if>
    <form method="post" action="/main">
        <h1  class = "head"><big><big><big><big>Поиск авиабилетов</big></big></big></big></h1>
<#--        <input type="submit" name="start" value="SIGN IN" class="start"/>-->
        <a href="http://localhost:8080/login">
            <button type="button" class="start">SIGN IN</button>
        </a>
        <h1  class = "head1"><br >Мы не продаём авиабилеты,<br/>а помогаем найти самые дешевые. Бесплатно.</h1>
    </form>
</body>
</html>