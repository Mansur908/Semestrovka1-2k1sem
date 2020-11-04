<#ftl encoding="utf-8">
<#include "macros.ftl"/>
<#macro content>
    <style>
        .main{
            margin:0px 0px;
            background-color:#99b1c6;
            background-size:100%;
        }
        .mainPage{
            margin:17px 0px 0px 1100px;
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
            margin:70px 350px;
        }
        .searchs{
            margin:40px 350px;
        }
        .coment{
            margin:-1px -1px;
            text-align:center;
            width:597px;
            height:100px;
            border:transparent;
            outline:none;
            border-radius:10px 10px 0px 0px;
        }
        .coments{
            margin:7px -1px 30px;
            text-align:center;
            width:597px;
            height:100px;
            border:transparent;
            outline:none;
            border-radius:0px 0px 10px 10px;
        }
        .prof{
            margin:130px -1px;
            width:599px;
            height:49px;
            border-radius:10px 10px 0px 0px;
            background-color:#ffffff;
            border:1px solid #000;
            text-align:center;
        }
        .butt{
            margin:-2px -2px;
            width:604px;
            height:50px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:0px 0px 10px 10px;
        }
        .time{
            margin:3px 0px 0px;
        }
    </style>
</head>
<body class="main">
<a href="http://localhost:8080/profile">
    <button type="button" class="mainPage">main</button>
</a>
<div class ="search">
    <form method="post" action="/addmessage">
    <textarea class="coment" name="message" id="query" placeholder="Enter message"></textarea>
    <input class="butt" type="submit" />
    </form>
</div>

<div class="searchs" id="mes">

</div>

<script type="application/javascript">
    function f() {
        $.ajax({
            url: "/messages",
            dataType: "json",
            success: function (msg) {
                if (msg.length < 1){
                    $("#mes").append("<div class=\"tickets\">\n" +
                        "<h3 class=\"notF\">NOT FOUND</h3>\n" +
                        "</div>");
                }
                else {
                    // for (var i = 0; i < msg.length; i++) {
                    for (var i = msg.length-1;i >= 0; i--) {
                        $("#mes").append("<div class=\"prof\"><h3 class=\"time\">" + msg[i].username + "</h3>" + msg[i].date + "<div>\n"+
                        "<textarea readonly class=\"coments\">" + msg[i].mes + "</textarea>");
                    }
                }
            }
        })
    }
    f()
</script>
</body>
</html>
</#macro>
<#macro title>
    <title>Messages</title>
</#macro>
<@main/>