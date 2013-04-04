<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Count to 10 Example(using JSTL)</title>
  </head>

  <body>
    <table border="1">
      <tr>
        <td valign="top">
          <h3>From 1 to 10</h3>

          <c:forEach var="i" begin="1" end="10">
            <c:out value="${i}" />

            <br />
          </c:forEach>
        </td>

        <td valign="top">
          <h3>From 10 to 1</h3>

          <c:forEach var="i" begin="1" end="10">
            <c:out value="${11-i}" />

            <br />
          </c:forEach>
        </td>
      </tr>

      <tr>
        <td>
          <h3>By Twos</h3>

          <c:forEach var="i" begin="2" end="10" step="2">
            <c:out value="${i}" />

            <br />
          </c:forEach>
        </td>

        <td valign="top">&nbsp;
        </td>
      </tr>
    </table>
  </body>
</html>