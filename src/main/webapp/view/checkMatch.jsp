<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;1,500&display=swap"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
    />
    <title>Tinder</title>
</head>
<body>
<h1></h1>
<div class="container">
    <div class="side">
        <div class="header">
            <div class="avatar">
                <img src="${requestScope['proFile'].getImgSrc()}" alt=""/>
            </div>

            <div class="title">${requestScope["proFile"].getName()}</div>

        </div>
        <div class="menu">
            <ul>
                <li class="active">Matches</li>
                <li><a href="/home" style="text-decoration: none">Mensagens</a></li>
            </ul>
        </div>
        <div class="messages">
            <c:forEach var="friends" items='${requestScope["proFileFriends"]}'>
                <div class="avatar">
                    <img src="${friends.imgSrc}" alt=""/>
                </div>
                <a class="message" id="chatuser1" href="#chatbox">
                    <div class="user">${friends.name}</div>
                    <div class="text">${friends.sex} ${friends.age} tuổi,đến từ ${friends.address}</div>
                </a>
                <div class="buttons">
                    <a href="/checkMatch?action=unmatch&id=${friends.getIdProfile()}" style="text-decoration: none">
                        <div class="no">
                            <i class="fas fa-times"></i>
                        </div>
                    </a>
                    <a href="/checkMatch?action=friend&id=${friends.getIdProfile()}" style="text-decoration: none">
                        <div class="heart">
                            <i class="fas fa-heart"></i>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="content">
        <div class="card">
            <div class="user">
                <img id="img"
                     onclick="changImage()"
                     class="user"
                     src="${requestScope['proFileDate'].getImgSrc()}"
                     alt=""
                />
                <div class="profile">
                    <div class="name">${requestScope['proFileDate'].getName()}
                        <span>${requestScope['proFileDate'].getAge()}</span>
                    </div>
                    <div class="local">
                        <i class="fas fa-map-marker-alt"></i>
                        <span>${requestScope['proFileDate'].getAddress()}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="buttons">
            <a href="/home" style="text-decoration: none">
                <div class="no">
                    <i class="fas fa-times"></i>
                </div>
            </a>
            <a href="/home?action=match&id=${requestScope['proFileDate'].getIdProfile()}" style="text-decoration: none">
                <div class="heart">
                    <i class="fas fa-heart"></i>
                </div>
            </a>
        </div>
    </div>
</div>
</body>
</html>