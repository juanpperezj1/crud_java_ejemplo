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
		<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
                  	<ul class="navbar-nav">
                            <li class="nav-item">
                                <a href="<%=request.getContextPath()%>/list" class="nav-link">FAQs</a>
                            </li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="container py-4">
            <div class="row pt-4">
                <div class="col-12">
                    <p class="text-center display-4">Preguntas Frecuentes</p>
                    <hr>
                </div>
            </div>
            <div class="row py-2">
                <div class="col-12 d-flex justify-content-start">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-primary btn-lg">Añadir pregunta nueva</a>
                </div>
            </div>
            <div class="row py-4">
                <div class="col-12 table-responsive">
                    <table class="table table-bordered table-striped table-hover">
                        <caption>List of FAQ's</caption>
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Pregunta</th>
                                <th scope="col">Respuesta</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="faq" items="${listFaq}">
                                    <tr>
                                            <td scope="row"><c:out value="${faq.id}" /></td>
                                            <td><c:out value="${faq.pregunta}" /></td>
                                            <td><c:out value="${faq.respuesta}" /></td>
                                            <td>
                                                <a class="btn-link" href="edit?id=<c:out value='${faq.id}' />">Editar</a>
                                                <a class="btn-link" href="delete?id=<c:out value='${faq.id}' />">Eliminar</a>
                                            </td>
                                    </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>
                    </table>
                </div>
            </div>	
	</div>
</body>
</html>
