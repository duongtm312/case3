<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/7/2022
  Time: 8:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Profile </title>
  <link rel="stylesheet" href="/css/editProfile.css"/>

</head>
<body>
<div class="box">
  <!-- Account page navigation-->
  <nav class="nav nav-borders">

  </nav>
    <div class="col-xl-4">
      <div class="img">
        <a href="#">
          <img src="/img/logo.png">
        </a>
      </div>
      <div class="card mb-4 mb-xl-0">
        <div class="card-header"><h1> Edit Profile</h1> </div>
        <div class="card-body text-center">
          <img class="img-account-profile rounded-circle mb-2"
               src="https://scontent.fhan2-4.fna.fbcdn.net/v/t39.30808-6/271600752_1110273243051729_6986374984730511913_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=174925&_nc_ohc=qhsqhRQYTxAAX-KPU4A&_nc_ht=scontent.fhan2-4.fna&oh=00_AT-9zXjukgDrA6r8CHP1ua0V36V5bvBCxtCwLWcqnsImgA&oe=62CBBA72"
               alt="">
        </div>
      </div>
    </div>
    <div class="col-xl-8">
      <div class="card mb-4">
        <div class="card-header"><h2>Account Details</h2></div>
        <div class="card-body">
          <form action="/proFile" method="post">
            <!-- Form Group (username)-->
            <div class="mb-3">
              <!--                            <label class="small mb-1" for="inputUsername">Username </label>-->
              <p>Enter your Name</p>
              <input class="form-control" id="inputUsername" name="inputName" type="text" value="${requestScope["Profile"].getName()}">
            </div>


            <!-- Form Group (email address)-->
            <div class="mb-3">
              <!--                            <label class="small mb-1" for="inputEmailAddress">Email address</label>-->
              <p>Enter your Gender</p>
              <select class="form-control" name="inputGender" >
                <option value="Nam">Nam</option>
                <option value="Nữ">Nữ</option>
              </select>
            </div>
            <!-- Form Row-->
            <div class="row gx-3 mb-3">
              <!-- Form Group (phone number)-->
              <div class="col-md-6">
                <!--                                <label class="small mb-1" for="inputPhone">Phone number</label>-->
                <p>Enter your age</p>
                <input class="form-control" id="inputPhone" type="tel" name="inputAge"
                       value="${requestScope["Profile"].getAge()}" >
              </div>
              <!-- Form Group (birthday)-->
              <div class="col-md-6">
                <!--                                <label class="small mb-1" for="inputBirthday">Age</label>-->
                <p>Enter your Address</p>
                <input class="form-control" id="inputBirthday" type="text" name="inputAddress"
                       value="${requestScope["Profile"].getAddress()}" >
              </div>
              <div class="col-md-6">
                <!--                                <label class="small mb-1" for="inputLinkImage">Link Image</label>-->
                <p>Enter your ImgSrc</p>
                <input class="form-control" id="inputLinkImage" type="text" name="LinkImage"
                       value="${requestScope["Profile"].getImgSrc()}">
              </div>
            </div>
            <!-- Save changes button-->
            <button class="saveChanges" type="submit">Save changes</button>
          </form>
        </div>
      </div>
    </div>

</div>
</body>
</html>