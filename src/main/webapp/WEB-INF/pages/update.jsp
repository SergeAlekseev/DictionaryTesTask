<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
    function putUpdate() {
        var key = document.getElementById("putKey").value;
        var value = document.getElementById("putValue").value;
        var idGroup = document.getElementById("idGroup").value;
        var prefix = '/update/';
        //     var record = { key: key, value: value , idGroup: idGroup};

        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url:  prefix + key,
            data: key +" " +value + " "+ idGroup,
            dataType: 'text',
            success: function (data) {
                alert(data);
                location.reload()
            }
        });
    }
</script>
<body>

<h3>Update</h3>
<a href="/result">Result</a>
<a href="/create">Create</a>
<a href="/remove">Remove</a>
<a href="/search">Search</a>
<a href="/update">Update</a>
<br/>
<br/>

<table>
    <tr>
        <th>Key</th>
    </tr>
    <tr>
        <td><input id="putKey" name="key" type="text" value=""></td>
    </tr>
    <tr>
    </tr>
    <tr>
        <th>Value</th>
    </tr>
    <tr>
        <td><input id="putValue" name="value" type="text" value=""></td>
    </tr>
    <br/>
    <br/>
    <tr>
        Алфавит:
        <select id="idGroup" name="idGroup">
            <c:forEach var="grps" items="${alphaStringList}">
                <option value="${grps}">
                    <c:out value="${grps}"/>
                </option>
            </c:forEach>
        </select>
    </tr>
    <br/>
    <br/>
    <tr>
        <td><button onclick="putUpdate()">Put</button></td>

    </tr>
</table>


</body>
</html>