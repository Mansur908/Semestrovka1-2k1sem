<#ftl encoding="utf-8">
<#include "macros.ftl"/>
<#macro content>
    <style type="text/css">
        body {
            background-color:#99b1c6;
            background-size:100%;
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            font-size: 20px;
            font-weight: normal;
            max-width: 450px;
            margin: 20px 430px;
            display: flex;
            flex-direction: column;
            position:absolute;
        }

        p {
            max-width: 255px;
            word-wrap: break-word;
            margin-bottom: 12px;
            line-height: 24px;
            position: relative;
            padding: 10px 20px;
            border-radius: 25px;
        }

        p:before, p:after {
            content:"";
            position:absolute;
            bottom:-2px;
            height:20px;
        }


        .from-me {
            color: white;
            background: #0B93F6;
            align-self: flex-end;
        }

        from-me:before {
             right:-7px;
             border-right:20px solid #0B93F6;
             border-bottom-left-radius: 16px 14px;
             transform:translate(0, -2px);
         }

        from-me:after {
             right:-56px;
             width:26px;
             background:white;
             border-bottom-left-radius: 10px;
             transform:translate(-30px, -2px);
         }

        .from-them {
            background: #E5E5EA;
            color: black;
        }

        from-them:before {
             left:-7px;
             border-left:20px solid #E5E5EA;
             border-bottom-right-radius: 16px 14px;
             transform:translate(0, -2px);
         }

        from-them:after {
             left:4px;
             width:26px;
             background:white;
             border-bottom-right-radius: 10px;
             transform:translate(-30px, -2px);
         }

        .date-me{
            margin:-20px 317px;
            color:#000;
            width: max-content;
            font-size: 70%;
        }
        .date-them{
            margin:-20px 160px;
            color:#000;
            width: max-content;
            font-size: 70%;
        }
        .mainPage{
            margin:17px 0px 0px 700px;
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
            margin:70px -60px;
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
        .butt{
            margin:-2px -2px;
            width:604px;
            height:50px;
            border:1px solid #000;
            background-position:7px 7px;
            outline:none;
            border-radius:0px 0px 10px 10px;
        }
    </style>
    </head>
<body>
<a href="http://localhost:8080/profile">
    <button type="button" class="mainPage">main</button>
</a>
<div class ="search">
    <form method="post" action="/addmessage">
    <textarea class="coment" name="message" id="query" placeholder="Enter message"></textarea>
    <input class="butt" type="submit" />
    </form>
</div>

<script type="application/javascript">
    function f() {
        $.ajax({
            url: "/messages",
            dataType: "json",
            success: function (msg) {
                if (msg.length > 0) {
                    for (var i = msg.length-1;i >= 0; i--) {
                        if (msg[i].egualCookie === true) {
                            $("body").append("<p class=\"from-me\"><font color=\"black\"><small>" + msg[i].username + "</small></font> <br/>" + msg[i].mes + "</p>\n" +
                                "<p class=\"date-me\">" + msg[i].date + "</p>");
                        }
                        else {
                            $("body").append("<p class=\"from-them\"><font color=\"#0B93F6\"><small>" + msg[i].username + "</small></font> <br/>" + msg[i].mes + "</p>\n" +
                                "<p class=\"date-them\">" + msg[i].date + "</p>");
                        }
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