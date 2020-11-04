<#ftl encoding="utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <style>
        .main{
            margin:0px 0px;
            background-image:url("/images/back.jpg");
            background-size:100%;
        }
        .addTicket{
            margin:80px 500px;
            width:300px;
            height:460px;
            border:1px solid #ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .company{
            text-align:center;
            margin:15px 50px 5px;
            width:200px;
            height:30px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .password{
            text-align:center;
            margin:5px 50px;
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
        .head{
            margin:0px;
            text-align:center;
            color:#ffffff
        }
        .message{
            margin:5px;
            text-align:center;
        }
        .enter:active{
            margin:16px 110px;
        }
        .exit{
            margin:17px 0px 0px 1150px;
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
<a href="http://localhost:8080/profile">
    <button type="button" class="exit">home</button>
</a>
<div class = "addTicket">
    <form method="post" action="/admin">
        <h2 class="head">ADD TICKET</h2>
        <#if message?has_content>
            <p class="message" ><font color="red"><b>${message}</b></font></p>
        </#if>
        <input type="text" name="com" placeholder="Company" class="company"/>
        <input type="text" name="depP" placeholder="Departure place" class="password"/>
        <input type="text" name="ariP" placeholder="Arrival place" class="password"/>
        <input type="text" name="depT" placeholder="Departure time" class="password"/>
        <input type="text" name="ariT" placeholder="Arrival time" class="password"/>
        <input type="text" name="day" placeholder="Day" class="password"/>
        <input type="text" name="price" placeholder="Price" class="password"/>
        <input type="text" name="link" placeholder="Link" class="password"/>
        <input type="submit" value="ENTER" class="enter"/>
    </form>
</div>
</body>
</html>