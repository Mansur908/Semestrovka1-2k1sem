<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Search page</title>
</head>
<body>
<p><input id="query" />
    <input id="query1"/>
    <input type="submit" onclick="f()"/>
</p>
<div id="res"></div>
<script type="application/javascript">
    function f() {
        $.ajax({
            url: "/ticket",
            data: {"query": $("#query").val(),"query1": $("#query1").val()},
            dataType: "json",
            success: function (msg) {
                $("#res").html("");
                $("#res").append("<li>" + msg + "</li>");
            },
            error: function (err) {
                $("#res").append("<li>" + err + "</li>");
            }
        })
    }
</script>
</body>
</html>