<!DOCTYPE html>
<html>

<head>
  <title>GamerList</title>
  <jsp:include page="/WEB-INF/jsp/shared/head.jsp" />


</head>

<body>
  <jsp:include page="/WEB-INF/jsp/shared/navbar.jsp" />
  <h2>Details for ${game.title}</h2>

  <table class="table table-striped table-bordered">
    <tr>
      <td>Title</td>
      <td>Description</td>
      <td>Developer</td>
      <td>Genre</td>
    </tr>

    <tr>
      <td>${game.title}</td>
      <td>${game.description}</td>
      <td>${game.developer.name}</td>
      <td>${game.genre.name}</td>
    </tr>
  </table>
  <jsp:include page="/WEB-INF/jsp/shared/load-scripts.jsp" />
</body>
<jsp:include page="/WEB-INF/jsp/shared/footer.jsp" />

</html>