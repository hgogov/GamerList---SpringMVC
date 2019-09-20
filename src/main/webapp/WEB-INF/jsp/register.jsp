<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>Register - GamerList</title>
    <jsp:include page="/WEB-INF/jsp/shared/head.jsp" />


</head>

<body>
    <jsp:include page="/WEB-INF/jsp/shared/navbar.jsp" />
    <h2>Register<h2>
            <div class="container">
                <div class="row">
                    <form:form method="POST" action="/register" modelAttribute="user">
                        <!-- <form:errors path="*" cssClass="alert alert-danger" element="div" /> -->
                        <div class="form-group">
                            <form:input path="username" value="${user.username}" type="text" cssClass="form-control"
                                placeholder="Username" />
                            <form:errors path="username" cssClass="alert alert-danger" element="div" />
                        </div>
                        <div class="form-group">
                            <form:password path="password" cssClass="form-control" placeholder="Password" />
                            <form:errors path="password" cssClass="alert alert-danger" element="div" />
                        </div>
                        <div class="form-group">
                            <form:input path="email" type="email" cssClass="form-control" placeholder="Email" />
                            <form:errors path="email" cssClass="alert alert-danger" element="div" />
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-primary btn-block" value="Register" />
                        </div>
                    </form:form>
                </div>


                <jsp:include page="/WEB-INF/jsp/shared/load-scripts.jsp" />
</body>
<jsp:include page="/WEB-INF/jsp/shared/footer.jsp" />

</html>