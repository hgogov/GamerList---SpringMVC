<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
  <title>GamerList</title>
  <jsp:include page="/WEB-INF/jsp/shared/head.jsp" />
</head>

<body>
  <jsp:include page="/WEB-INF/jsp/shared/navbar.jsp" />

  <h2>Games</h2>

  <table class="table table-striped table-bordered">
    <tr>
      <th>Title</th>
      <th>Description</th>
      <th>Developer</th>
      <th>Genre</th>
    </tr>

    <c:forEach var="game" items="${games}">
      <tr>
        <td>
          <c:out value="${game.title}"></c:out>
        </td>
        <td>
          <c:out value="${game.description}" />
        </td>
        <td>${game.developer.name}</td>
        <td>${game.genre.name}</td>
      </tr>
    </c:forEach>
  </table>


  <jsp:include page="/WEB-INF/jsp/shared/load-scripts.jsp" />
</body>

<jsp:include page="/WEB-INF/jsp/shared/footer.jsp" />

</html>