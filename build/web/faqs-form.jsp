<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	@email juanpperezj1@gmail.com
	@author Juan Pablo Perez Juarez 4IM9
	@date 27/03/2020
 -->
<html>
<head>
<title>Faqs</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link  rel="icon"   href="./assets/img/logoEWG.png" type="image/png" />
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
                  	<ul class="navbar-nav">
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/list" class="nav-link">&lt; Volver</a>
                            </li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${faq != null}">
                                    <form action="update" method="post">
				</c:if>
				<c:if test="${faq == null}">
                                    <form action="insert" method="post">
				</c:if>

                                    <caption>
                                            <h2>
                                                    <c:if test="${faq != null}">
                                                            Editar Pregunta
                                                    </c:if>
                                                    <c:if test="${faq == null}">
                                                            Añadir Pregunta
                                                    </c:if>
                                            </h2>
                                    </caption>

                                    <c:if test="${faq != null}">
                                            <input type="hidden" name="id" value="<c:out value='${faq.id}' />" />
                                    </c:if>

                                    <fieldset class="form-group">
                                            <label>Pregunta</label> <input type="text"
                                                    value="<c:out value='${faq.pregunta}' />" class="form-control"
                                                    name="pregunta" required="required">
                                    </fieldset>

                                    <fieldset class="form-group">
                                            <label>Respuesta</label> <input type="text"
                                                    value="<c:out value='${faq.respuesta}' />" class="form-control"
                                                    name="respuesta" required="required">
                                    </fieldset>

                                    <button type="submit" class="btn btn-primary btn-lg btn-block">Save</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
