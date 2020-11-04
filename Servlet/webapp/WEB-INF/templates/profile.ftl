<#ftl encoding="utf-8">
<#include "macros.ftl"/>
<#macro content>
    <style>
        .main{
            margin:0px 0px;
            background: linear-gradient(100deg,#e55d87,#5fc3e4);
            background-size:100%;
        }
        .exit{
            text-align:center;
            width:80px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent
        }
        .adm{
            text-align:center;
            width:120px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .change{
            text-align:center;
            width:120px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .mes{
            margin:17px 0px 0px 870px;
            text-align:center;
            width:120px;
            height:25px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:transparent;
        }
        .search{
            margin:70px 250px;
            width:800px;
            height:100px;
            border:1px solid #ffffff;
            outline:none;
            color:#ffffff;
            border-radius:10px;
            background-color:#edeef0;
        }
        .tickets{
            margin:20px;
            width:600px;
            height:122px;
            border: 1px solid #000;
            background-color:#ffffff;
            border-radius:10px;
        }
        .ticket{
            margin:20px 26%;
            width:0px;
            height:0px;
            background-color:#ffffff;
            border-radius:10px;
        }
        .com{
            margin:-130px 190px;
        }
        .depT{
            margin:140px 50px;
        }
        .ariT{
            margin:-167px 360px;
        }
        .depP{
            margin:175px 50px;
        }
        .ariP{
            margin:-193px 360px;
        }
        .from{
            text-align:center;
            margin:20px 30px;
            width:200px;
            height:25px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .to{
            margin:0px -20px;
            text-align:center;
            width:200px;
            height:25px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .day{
            margin:0px 30px;
            text-align:center;
            width:150px;
            height:25px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .butt{
            margin:0px 20px;
            width:100px;
            height:25px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:10px;
        }
        .notF{
            margin:30px 0px;
            text-align:center;
        }
        .next{
            margin:0px 480px;
            height:122px;
            width:120px;
            border:transparent;
            background-color:#1f76ec;
            border-radius:0px 8px 8px 0px;
        }
        .picture{
            margin:150px 180px;
            width:130px;
        }
    </style>
</head>
<body class="main">
<form method="post" action="/profile">
    <a href="http://localhost:8080/addmessage">
        <button type="button" class="mes">messages</button>
    </a>
    <a href="http://localhost:8080/change">
        <button type="button" class="change">change password</button>
    </a>
    <input type="submit" value="exit" name="exit" class="exit"/>
    <#if message?has_content>
        <a href="http://localhost:8080/admin">
            <button type="button" class="adm">admin</button>
        </a>
    </#if>
</form>

<div class ="search">
    <p><input class="from" id="query" placeholder="Departure place" />
        <input class="to" id="query1" placeholder="Arrival place"/>
        <input class="day" id="query2" placeholder="Date"/>
        <input class="butt" type="submit" value="FIND" onclick="f()"/>>
    </p>
</div>

<script type="application/javascript">
    function f() {
        $.ajax({
            url: "/ticket",
            data: {"query": $("#query").val(),"query1": $("#query1").val(),"query2": $("#query2").val()},
            dataType: "json",
            success: function (msg) {
                if ( document.getElementById("ticket")){
                    document.getElementById("ticket").remove();
                }
                if ((msg.length < 1) || (msg.length === 1 && msg[0].company === "")){
                    $("body").append("<div class='ticket' id='ticket'>\n"+
                        "<div class=\"tickets\">\n" +
                        "<h3 class=\"notF\">NOT FOUND</h3>\n" +
                        "</div>\n" +
                        "</div>");
                }
                else {
                    $("body").append("<div class='ticket' id='ticket'>");
                    for (var i = 0; i < msg.length; i++) {
                        $("#ticket").append("<div class=\"tickets\">\n" +
                            "<a href=\""+ msg[i].link +"\">"+
                            "<button type=\"button\" class=\"next\"><h2>Choose "+ msg[i].price +"руб</h2></button>\n"+
                            "</a>"+
                            "<p><h3 class=\"com\">" + msg[i].company + "</h3></p>\n" +
                            "<p><h2 class=\"depT\">" + msg[i].departureTime + "</h2></p>\n" +
                            "<p><h2 class=\"ariT\" >" + msg[i].arrivalTime + "</h2></p>\n" +
                            "<p class=\"depP\" >" + msg[i].departurePlace + "</p>\n" +
                            "<p class=\"ariP\" >" + msg[i].arrivalPlace + "</p>\n" +
                            "<img class=\"picture\" src=\"images/ticket.png\"></img>\n"+
                        "</div>");
                    }
                    $("body").append("</div>");
                }
            }
        })
    }
</script>
</body>
</html>
</#macro>
<#macro title>
    <title>Profile</title>
</#macro>
<@main/>