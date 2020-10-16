<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>main</title>

    <style>
        .main{
            margin:0px 0px;
            width:100%;
            height:100%;
            background-color:#0c73fe;
        }
        .block{
            margin:170px 500px;
            width:300px;
            height:230px;
            border-radius:10px;
        }
        .start{
            text-align:center;
            margin:0px 0px;
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
        <div class="block">
            <input type="submit" name="start" value="START" class="start"/>
        </div>
    </form>
</body>
</html>