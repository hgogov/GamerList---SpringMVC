<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>GamerList</title>
    <jsp:include page="/WEB-INF/jsp/shared/head.jsp" />

</head>

<body>
    <jsp:include page="/WEB-INF/jsp/shared/navbar.jsp" />
    <h2>Edit game ${game.title}</h2>

    <div class="container">
        <div class="row">
            <form:form method="POST" action="/games/edit/${id}" modelAttribute="game">
                <div class="form-group">
                    <label for="title">Title</label>
                    <form:input path="title" value="${game.title}" type="text" cssClass="form-control" />
                    <form:errors path="title" cssClass="error" />
                </div>
                <div class="form-group">
                    <label for="developer">Developer</label>
                    <form:select path="developer" id="developer">
                        <form:options items="${developers}" itemValue="id" itemLabel="name" />
                    </form:select>
                    <form:errors path="developer" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="genre">Genre</label>
                    <form:select path="genre" itemValue="id" items="${genres}" />
                    <form:errors path="genre" cssClass="error" />
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <form:input path="description" cssClass="form-control" />
                    <form:errors path="description" cssClass="error" />
                </div>
                <div class="form-group">
                    <input type="submit" value="Submit" />
                </div>
            </form:form>
        </div>


        <jsp:include page="/WEB-INF/jsp/shared/load-scripts.jsp" />
</body>
<jsp:include page="/WEB-INF/jsp/shared/footer.jsp" />

</html>