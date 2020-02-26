<%@ page import="ru.itpark.model.Recipe" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.itpark.constant.Constants" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><%= request.getAttribute("page-title") %>
    </title>
</head>
<body>

<h2 mt-3>МАМИНЫ РЕЦЕПТЫ</h2>




<form action="<%= request.getContextPath() %>/search">
    <input name="q" placeholder="Поиск по названию" value="<%= request.getAttribute(Constants.SEARCH_QUERY1) == null ? "" : request.getAttribute(Constants.SEARCH_QUERY1) %>">
    <input type="submit" value="Давай поищем по названию рецепта!"></input>
</form>
<p></p>
<form action="<%= request.getContextPath() %>/searchByIngredients">
    <input name="queryIngredients" placeholder="Поиск по ингредиентам" value="<%= request.getAttribute(Constants.SEARCH_QUERY2) == null ? "" : request.getAttribute(Constants.SEARCH_QUERY2) %>">
    <input type="submit" value="Давай поищем по ингредиентам!"></input>
</form>
<p></p>
<p></p>

Можешь записать новый рецепт (только хороший!)

<p></p>

<ul>
    <% for (Recipe item : (Collection<Recipe>)request.getAttribute(Constants.ITEMS)) { %>
    <li>
<%--       <img src="<%= request.getContextPath() %>/images/<%= item.getId() %>">--%>
       <h4> <%= item.getName() %></h4>

<%--        <form  action="<%= request.getContextPath() %>/houses" method="post">--%>
<%--            <input type="hidden" name="id" value="<%= item.getId() %>">--%>
<%--            <input type="hidden" name="action" value="edit">--%>
<%--            <button>Редактировать</button>--%>
<%--        </form>--%>
<%--        <form  action="<%= request.getContextPath() %>/houses" method="post">--%>
<%--            <input type="hidden" name="id" value="<%= item.getId() %>">--%>
<%--            <input type="hidden" name="action" value="remove">--%>
<%--            <button>Удалить</button>--%>
<%--        </form>--%>
        <ul>
            <% for (String ingredient : item.getIngredients()) { %>
            <li><%= ingredient %></li>
            <% } %>
        </ul>
    <%= item.getDescription() %>
</li>
    <% } %>
</ul>


<% Recipe sheet = (Recipe) request.getAttribute(Constants.ITEM); %>
<form action="<%= request.getContextPath()%>" method="post">
    <input type="hidden" name="id" value="<%= sheet == null ? "" : sheet.getId() %>">
    <input type="hidden" name="action" value="save">
    <input name="name" placeholder="Название рецепта" value="<%= sheet == null ? "" : sheet.getName() %>">
    <input name="ingredients" placeholder="Состав" value="<%= sheet == null ? "" : sheet.getIngredients() %>">
    <input name="description" placeholder="Описание" value="<%= sheet == null ? "" : sheet.getDescription()%>">
    <button>Сохранить</button>
</form>


<%--&lt;%&ndash;<% Recipe item = (Recipe) request.getAttribute(Constants.ITEM); %>&ndash;%&gt;--%>
<%--&lt;%&ndash;<form action="<%= request.getContextPath() %>/" method="get">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="hidden" name="id" value="<%= item == null ? "" : item.getId() %>">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input type="hidden" name="action" value="save">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input name="recipe" placeholder="Название" value="<%= item == null ? "" : item.getName() %>">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input name="description" type="text" placeholder="Описание" value="<%= item == null ? "" : item.getDescription() %>">&ndash;%&gt;--%>
<%--&lt;%&ndash;    <input name="undergrounds" placeholder="Метро через пробел" value="<%= item == null ? "" : item.getIngredients() %>">&ndash;%&gt;--%>

<%--&lt;%&ndash;    <button>Сохранить</button>&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>


</body>
</html>