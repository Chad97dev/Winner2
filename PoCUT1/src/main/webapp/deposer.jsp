<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulaire d'upload de fichier</title>
</head>
<body>
	<h2>Ajouter un justificatif sous format PDF</h2>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file"><br><br>
		<input type="submit" value="Déposer">
	</form>
	<% //version long
	   String msg_info = (String)request.getAttribute("msg_error");
	   if(msg_info != null){
		   out.print("<div>" + msg_info + "</div>");
	   }
	   
	
	
	%>
<%
String mail = URLEncoder.encode((String) session.getAttribute("email"), "utf-8");
out.print("<a href=\"CtrlConnexion?username="+mail+"&password="+session.getAttribute("pwd")+"&signin=Log+in\">Annuler</a>");
%>
<a href="CtrlDeconnexion">Se deconnecter</a>
</body>
</html> --> --%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../assets/img/favicon.png">
  <title>
    déposer justificatif 
  </title>
  <!--     Fonts and icons     -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
  <!-- Nucleo Icons -->
  <link href="../assets/css/nucleo-icons.css" rel="stylesheet" />
  <link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <link href="../assets/css/font-awesome.css" rel="stylesheet" />
  <link href="../assets/css/nucleo-svg.css" rel="stylesheet" />
  <!-- CSS Files -->
  <link href="../assets/css/argon-design-system.css?v=1.2.2" rel="stylesheet" />
  <link href="css/styleArgon.css" rel="stylesheet" />
</head>

<body class="profile-page">
  <!-- Navbar -->
  <nav id="navbar-main" class="navbar navbar-main navbar-expand-lg navbar-transparent navbar-light py-2">
    <div class="container">
      <a class="navbar-brand mr-lg-5" href="../index.html">
        <img src="https://upload.wikimedia.org/wikipedia/fr/2/2f/Universit%C3%A9_Toulouse_1_%28logo%29.svg">
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar_global" aria-controls="navbar_global" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="navbar-collapse collapse" id="navbar_global">
        <div class="navbar-collapse-header">
          <div class="row">
            <div class="col-6 collapse-brand">
              <a href="../../../index.html">
                <img src="">
              </a>
            </div>
            <div class="col-6 collapse-close">
              <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar_global" aria-controls="navbar_global" aria-expanded="false" aria-label="Toggle navigation">
                <span></span>
                <span></span>
              </button>
            </div>
          </div>
        </div>
        <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
          
          <li class="nav-item dropdown">
            <a href="#" class="nav-link" data-toggle="dropdown" href="#" role="button">
              <i class="ni ni-collection d-lg-none"></i>
              <span class="nav-link-inner--text">Menu </span>
            </a>
            <div class="dropdown-menu">
              <a href="CtrlProfil" class="dropdown-item">Mon profil</a>
              <a href="CtrlAbsJus" class="dropdown-item">Absences justifiées</a>
              
            </div>
          </li>
        </ul>
        <ul class="navbar-nav align-items-lg-center ml-lg-auto">
          
          <li class="nav-item d-none d-lg-block">
            <a href="CtrlDeconnexion" target="_blank" class="btn btn-neutral btn-icon">
              <span class="btn-inner--icon">
                
              </span>
              <span class="nav-link-inner--text"> Déconnexion </span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- End Navbar -->
  <div class="wrapper">
    <section class="section-profile-cover section-shaped my-0">
      <!-- Circles background -->
      <img class="bg-image" src="https://thumb.ccsd.cnrs.fr/5230156/large" style="width: 100%;">
      <!-- SVG separator -->
      <div class="separator separator-bottom separator-skew">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1" xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-secondary" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </section>
    <section class="section bg-secondary">
      <div class="container">
        <div class="card card-profile shadow mt--300">
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  
                </div>
              </div>
              
              </div>
              <div class="col-lg-4 order-lg-1">
                <div class="card-profile-stats d-flex justify-content-center">
                 
                </div>
              </div>
            </div>
            <div class="text-center mt-5">
              <p> <h2>Ajouter un justificatif</h2>
				<form action="upload" method="post" enctype="multipart/form-data">
					<input type="file" name="file"><br><br>
					<input type="submit" value="Déposer">
					<a href="CtrlCfrmAbs"><input type="button" value="Retour"></a>
				</form>
				<p>${requestScope.msg_error}</p>
				
			</p>
            </div>
            <div class="mt-5 py-5 border-top text-center">
              <div class="row justify-content-center">
                <div class="col-lg-9">
                 
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
  </div>
  <!--   Core JS Files   -->
  <script src="../assets/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="../assets/js/core/popper.min.js" type="text/javascript"></script>
  <script src="../assets/js/core/bootstrap.min.js" type="text/javascript"></script>
  <script src="../assets/js/plugins/perfect-scrollbar.jquery.min.js"></script>
  <!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
  <script src="../assets/js/plugins/bootstrap-switch.js"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src="../assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
  <script src="../assets/js/plugins/moment.min.js"></script>
  <script src="../assets/js/plugins/datetimepicker.js" type="text/javascript"></script>
  <script src="../assets/js/plugins/bootstrap-datepicker.min.js"></script>
  <!-- Control Center for Argon UI Kit: parallax effects, scripts for the example pages etc -->
  <!--  Google Maps Plugin    -->
  <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
  <script src="../assets/js/argon-design-system.min.js?v=1.2.2" type="text/javascript"></script>
  <script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
  <script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "argon-design-system-pro"
      });
  </script>
</body>

</html>