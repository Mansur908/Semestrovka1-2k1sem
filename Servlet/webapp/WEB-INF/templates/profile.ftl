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
        .enter{
            margin:17px 900px;
            text-align:center;
            width:80px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
    </style>
</head>
<body class="main">
<form method="post" action="/profile">
    <input type="submit" value="exit" name="exit" class="enter"/>
</form>
</body>
</html>