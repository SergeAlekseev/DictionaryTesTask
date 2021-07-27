<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: salekseev
  Date: 26.07.2021
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script>


        function addWord(word, translate, id) {
            let mnw = {}
            mnw["word"] = document.getElementById(word).value;
            mnw["translate"] = document.getElementById(translate).value;
            mnw["idDict"] = id;

            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: "/bibl/addWord",
                data: JSON.stringify(mnw),
                dataType: 'text',
                success: function (data) {
                    alert(data);
                    location.reload()
                }
            });
        }


        function addTranslate(select, addTranslate) {
            let mnt = {}
            mnt["translate"] = document.getElementById(addTranslate).value;
            mnt["idWord"] = document.getElementById(select).value;

            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: "/bibl/addTranslate",
                data: JSON.stringify(mnt),
                dataType: 'json',
                success: function (data) {
                    let el = document.createElement('p');
                    let delBtn = document.createElement('button');
                    el.textContent = data.translate
                    delBtn.textContent = 'Delete '+data.translate;

                    delBtn.onclick = delTranslate(data.idTranslate);
                    el.setAttribute('id', data.idTranslate + 'el')
                    delBtn.setAttribute('id', data.idTranslate + 'btn')
                    let elementTrans = document.getElementById('translates');

                    elementTrans.appendChild(delBtn);
                    elementTrans.appendChild(el);
                }
            });
        }


        function delTranslate(transId) {
            return function () {
                let mt = {};
                mt["idTranslate"] = transId;

                $.ajax({
                    type: 'DELETE',
                    contentType: 'application/json',
                    url: "/bibl/deleteTrans",
                    data: JSON.stringify(mt),
                    dataType: 'text',
                    success: function (data) {
                        document.getElementById(transId + 'btn').remove();
                        document.getElementById(transId + 'el').remove();
                        alert("deleted " + data);
                    }
                });
            };
        }

        function changeSelect(selectID, transID) {
            let idWord = document.getElementById(selectID).value;

            $.ajax({
                type: 'GET',
                url: "/bibl/translates/" + idWord,
                dataType: 'json',
                success: function (translates) {
                    let elementTrans = document.getElementById(transID);
                    while (elementTrans.firstChild) {
                        elementTrans.removeChild(elementTrans.lastChild);
                    }
                    for (let i = 0; i < translates.length; i++) {
                        let el = document.createElement('p');
                        let delBtn = document.createElement('button');
                        el.textContent = translates[i].translate
                        delBtn.textContent = 'Delete '+translates[i].translate;

                        delBtn.onclick = delTranslate(translates[i].id);
                        el.setAttribute('id', translates[i].id + 'el')
                        delBtn.setAttribute('id', translates[i].id + 'btn')
                        el.setAttribute('id', translates[i].id + 'el')

                        elementTrans.appendChild(delBtn);
                        elementTrans.appendChild(el);

                    }
                }
            });
        }

        $(window).on("load", function () {
            changeSelect('select', 'translates')

        });

    </script>
</head>
<body>
<c:out value="${dict.regex}"/>
<br>
<div>
    <input id="word" type="text" value="" placeholder="word">
    <input id="translate" type="text" value="" placeholder="first translate">

    <button onclick="addWord('word','translate','${dict.id}')">Add new word</button>
</div>
<br>
<select id="select" onchange="changeSelect('select','translates')">
    <c:forEach items="${dict.words}" var="word">

        <option value="${word.id}" id="${word.id}">
            <c:out value="${word.word}"/>
        </option>
    </c:forEach>
</select>
<div id="translates"></div>
<div>
    <input id="addTranslate" type="text" value="" placeholder="translate">


    <button onclick="addTranslate('select','addTranslate')">Add other translate</button>

</div>
</body>
</html>
