<html itemscope="" itemtype="http://schema.org/WebPage">
<head>
<title>Hadoop Query</title>

<script type="text/javascript">
    
    function executeQuery(){
        var status = document.getElementById("status");
        status.innerHTML = "executing query...";
        var res = document.getElementById("queryResult");
        res.innerHTML = "";
        var sql = document.getElementById("sql").value;
        
        var xmlhttp;
        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        
        xmlhttp.onreadystatechange=function() {
            if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                status.innerHTML = "";
                res.innerHTML=xmlhttp.responseText;
            }
        };
        xmlhttp.open("GET","/analytics-query-service/rest/query?sql=" + sql,true);
        xmlhttp.send();            
    }
    
</script>
</head>
<body>
<h2>Hadoop query</h2>
<form action="rest/query" method="post">
    <div>
        <h3>Enter query</h3>
    </div>
    <div>
        <textarea id="sql" cols="30"></textarea>
    </div>
    <div>
        <input type="button" id="executeBtn" onclick="executeQuery();" value="execute">
    </div>
    <div id="status" style="margin-top: 10px;"></div>
    <div id="queryResult" style="margin-top: 10px;"></div>
</form>
</body>
</html>
