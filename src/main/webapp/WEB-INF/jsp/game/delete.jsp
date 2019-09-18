<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>GamerList</title>
    <jsp:include page="/WEB-INF/jsp/shared/head.jsp" />


</head>

<body>
    <jsp:include page="/WEB-INF/jsp/shared/navbar.jsp" />

    <h2>Delete ${game.title}</h2>

    <h3>Are you sure you want to delete ${game.title}?</h3>
    <div>
        <hr />
        <dl class="dl-horizontal">
            <dt>
                Title
            </dt>

            <dd>
                ${game.title}
            </dd>

            <dt>
                Developer
            </dt>

            <dd>
                ${game.developer}
            </dd>

            <dt>
                Genre
            </dt>

            <dd>
                ${game.genre}
            </dd>

            <dt>
                Description
            </dt>

            <dd>
                ${game.description}
            </dd>

        </dl>

        <form:form method="POST" action="/games/delete/${id}" modelAttribute="game">
            <div class="form-actions no-color">
                <input type="submit" value="Delete" class="btn btn-default" /> |
                <a href="/games">Back to List</a>
            </div>
        </form:form>
    </div>


    <jsp:include page="/WEB-INF/jsp/shared/load-scripts.jsp" />
</body>
<jsp:include page="/WEB-INF/jsp/shared/footer.jsp" />

</html>