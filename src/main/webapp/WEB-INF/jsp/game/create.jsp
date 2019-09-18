<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>GamerList</title>
    <jsp:include page="/WEB-INF/jsp/shared/head.jsp" />


</head>

<body>
    <jsp:include page="/WEB-INF/jsp/shared/navbar.jsp" />
    <h2>Create game</h2>
    <div class="container">
        <div class="row">
            <form:form method="POST" action="/games/create" modelAttribute="game">
                <!-- <form:errors path="*" cssClass="errorblock" element="div" /> -->
                <div class="form-group">
                    <label for="title">Title</label>
                    <form:input path="title" value="${game.title}" type="text" cssClass="form-control" />
                    <form:errors path="title" cssClass="errorblock" element="div" />
                </div>
                <div class="form-group">
                    <label for="developer">Developer</label>
                    <form:select path="developer">
                        <form:options items="${developers}" itemValue="id" itemLabel="name" />
                    </form:select>
                    <form:errors path="developer" cssClass="errorblock" element="div" />
                </div>
                <div class="form-group">
                    <label for="genre">Genre</label>
                    <form:select path="genre">
                        <form:options items="${genres}" itemValue="id" />
                    </form:select>
                    <form:errors path="genre" cssClass="errorblock" element="div" />
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <form:input path="description" cssClass="form-control" />
                    <form:errors path="description" cssClass="errorblock" element="div" />
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary" value="Submit" />
                </div>
            </form:form>
        </div>


        <jsp:include page="/WEB-INF/jsp/shared/load-scripts.jsp" />
</body>
<jsp:include page="/WEB-INF/jsp/shared/footer.jsp" />

</html>