<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: salekseev
  Date: 23.07.2021
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dictionaries</title>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script type="text/javascript">


        function remove(selectID,transID) {
            let mdw = {}
            mdw["idWord"] = document.getElementById(selectID).value;

            $.ajax({
                type: 'DELETE',
                contentType: 'application/json',
                url: "/bibl/delete",
                data: JSON.stringify(mdw),
                dataType: 'text',
                success: function (data) {
                    document.getElementById(data).remove();
                    alert("deleted" + data);
                    changeSelect(selectID, transID);
                }
            });
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
                        el.textContent = translates[i].translate
                        elementTrans.appendChild(el);
                    }
                }
            });
        }

        function searchAllByWord() {
            let word = document.getElementById('keyInAll').value;

            $.ajax({
                type: 'GET',
                url: "/bibl/searchAllByWord/" + word,
                dataType: 'json',
                success: writeSearchResult('wordsAll')
            });
        }

        function searchByWord(search, words, id) {
            let word = document.getElementById(search).value;

            if (word.length > 0) {
                $.ajax({
                    type: 'GET',
                    url: "/bibl/searchAllByWord/" + id + "/" + word,
                    dataType: 'json',
                    success: writeSearchResult(words)
                });
            }
        }

        function searchAllByTranslate() {
            let word = document.getElementById('keyInAll').value;

            $.ajax({
                type: 'GET',
                url: "/bibl/searchAllByTranslate/" + word,
                dataType: 'json',
                success: writeSearchResult('wordsAll')
            });
        }

        function searchByTranslate(search, words, id) {
            let word = document.getElementById(search).value;

            if (word.length > 0) {
                $.ajax({
                    type: 'GET',
                    url: "/bibl/searchAllByTranslate/" + id + "/" + word,
                    dataType: 'json',
                    success: writeSearchResult(words)
                });
            }
        }


        function writeSearchResult(wordsContainer) {
            return function (words) {
                let wordsAll = document.getElementById(wordsContainer);
                while (wordsAll.firstChild) {
                    wordsAll.removeChild(wordsAll.lastChild);
                }
                if (words.length > 0) {
                    for (let i = 0; i < words.length; i++) {
                        let container = document.createElement('div');
                        let label = document.createElement('h1');
                        label.textContent = words[i].word;

                        container.appendChild(label);

                        for (let j = 0; j < words[i].translates.length; j++) {
                            let el = document.createElement('p');
                            el.textContent = words[i].translates[j].translate;
                            container.appendChild(el);
                        }
                        wordsAll.appendChild(container);
                    }
                } else {

                }

            };
        }


        $(window).on("load", function () {
            changeSelect('select1', 'trans1')
            changeSelect('select2', 'trans2')
        });


    </script>
</head>
<body>

<div>
    <sf:form action="/bibl/edit/2" method="get">
        <input type="submit" value="Edit">
    </sf:form>

    <c:out value="${dict1.regex}"/>
    <br>
    <select id="select1" onchange="changeSelect('select1','trans1')">
        <c:forEach items="${dict1.words}" var="word">

            <option value="${word.id}" id="${word.id}">
                <c:out value="${word.word}"/>
            </option>
        </c:forEach>
    </select>
    <br>
    <button onclick="remove('select1','trans1')">Remove</button>
    <br>
    <div id="trans1"></div>

    <br>
    <div>
        <input id="search1" type="text" value="">
        <button onclick="searchByWord('search1','words1','${dict1.id}')">Search by word</button>
        <button onclick="searchByTranslate('search1','words1','${dict1.id}')">Search by translate</button>

        <div id="words1"></div>
    </div>
    <br>
    <hr>
</div>


<div>
    <sf:form action="/bibl/edit/3" method="get">
        <input type="submit" value="Edit">
    </sf:form>
    <c:out value="${dict2.regex}"/>
    <br>
    <select id="select2" onchange="changeSelect('select2','trans2')">
        <c:forEach items="${dict2.words}" var="word">
            <option value="${word.id}" id="${word.id}">
                <c:out value="${word.word}"/>
            </option>
        </c:forEach>
    </select>
    <br>
    <button onclick="remove('select2','trans2')">Remove</button>
    <br>
    <div id="trans2"></div>

    <br>
    <div>
        <input id="search2" type="text" value="">
        <button onclick="searchByWord('search2','words2','${dict2.id}')">Search by word</button>
        <button onclick="searchByTranslate('search2','words2','${dict2.id}')">Search by translate</button>

        <div id="words2"></div>
    </div>
    <br>
    <hr>
</div>

<div>
    <input id="keyInAll" type="text" value="">
    <button onclick="searchAllByWord()">Search by word</button>
    <button onclick="searchAllByTranslate()">Search by translate</button>

    <div id="wordsAll"></div>
</div>

</body>
</html>
