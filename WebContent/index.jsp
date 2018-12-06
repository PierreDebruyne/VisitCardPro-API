<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
    <head>
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
      <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <title>Pierre DE BRUYNE</title>
      <link rel="icon" href="img/favicon.ico" />
    </head>

    <body>

    	<ul id="slide-out" class="sidenav sidenav-fixed hoverable table-of-contents">
    		<li class="sidenav-header">
    			<img src="img/menuBackground.jpg">
    		</li>
    		<li><a class="waves-effect waves-orange" href="#about">A propos</a></li>
    		<li><a class="waves-effect waves-orange" href="#services">Mes services</a></li>
    		<li><a class="waves-effect waves-orange" href="#competences">Mes compétences</a></li>
    		<li><a class="waves-effect waves-orange beforelast" href="#portfolio">Portfolio</a></li>
    		<li><a class="waves-effect waves-orange last" href="#contact">Contact</a></li>
    	</ul>
    	
    	<div class="main">

    		<div class="header z-depth-1 white">
      			<a href="#" data-target="slide-out" class="sidenav-trigger show-on-large right blue-text valign-wrapper"><i class="material-icons">menu</i></a>
    		</div>

    		<div id="about" class="container section scrollspy">

    			<h1 class="blue-text">Auto Entrepreneur Développeur</h1>
    			<div class="divider"></div>
    			<div class="row">
    				<div class="col s12 m3 center-on-small-only valign-wrapper about">
    					<div class="image-container">
    						<img class="circle responsive-img" src="img/photo.jpg">
    					</div>
    				</div>
    				<div class="col s12 m9 about">
    					<h4  class="orange-text">Pierre DE BRUYNE</h4>
    					<p>
    						Je m’appelle Pierre DE BRUYNE, j’ai 24 ans. Passionné d’informatique depuis tout jeune, j’ai étudié à Epitech Lille où j’ai pu acquérir les compétences nécessaires à la réalisation de vos projets. J’exerce une activité d’Auto-entrepreneur en tant que Développeur Web FullStack depuis un an maintenant.
    					</p>
    					<p>
    						Je suis quelqu’un de minutieux, d’un naturel rigoureux, autonome et productif, j’aime assurer un travail efficace et de qualité. Ma motivation fait de moi quelqu’un d’impliqué dans les tâches qui me sont attribuées.
    					</p>

    				</div>
    			</div>
    		</div>
    		<div class="parallax-container">
      			<div class="parallax"><img src="img/divider1.jpg"></div>
    		</div>
    		<div id="services" class="container section scrollspy">

    			<h1 class="blue-text">Services</h1>
    			<div class="divider"></div>
    			<div class="row">
    				<div class="col s12 m4 l12 xl4 center-on-small-only">
    					<div class="card-panel center services">
    						<i class="material-icons large orange-text">home</i>
    						<h2 class="orange-text">Sites Vitrines</h2>
    						<p>Référencés, fidèles à votre identité graphique et transmettants les valeurs de votre entreprise.</p>
    					</div>
    				</div>
    				<div class="col s12 m4 l12 xl4 center-on-small-only">
    					<div class="card-panel center services">
    						<i class="material-icons large orange-text">apps</i>
    						<h2 class="orange-text">Web apps</h2>
    						<p>Adaptées au besoin de votre entreprise, sur toutes les plateformes: desktop, tablette et mobile.</p>

    					</div>
    				</div>
    				<div class="col s12 m4 l12 xl4 center-on-small-only">
    					<div class="card-panel center services">
    						<i class="material-icons large orange-text">cloud</i>
    						<h2 class="orange-text">Rest API</h2>
    						<p>Des services utiles, respectant les standards en vigeur.</p>
    					</div>
    				</div>
    			</div>

    		</div>
    		<div class="parallax-container">
      			<div class="parallax"><img src="img/divider1.jpg"></div>
    		</div>
    		<div id="competences" class="container section scrollspy">

    			<h1 class="blue-text">Compétences</h1>
    			<div class="divider"></div>
    			<div class="row">
    				<div class="col s12 m4 center-on-small-only">
    					<div class="card-panel center competences">
    						<i class="material-icons large orange-text">edit</i>
    						<h2 class="orange-text">Front-end</h2>
    						<div class="row">
    							<div class="col s6">
    								<p>HTML5</p>
    								<p>CSS3 / SASS</p>
    								<p>JavaScript</p>
    							</div>
    							<div class="col s6">
    								<p>Jquery</p>
    								<p>AngularJS</p>
    								<p>Webpack</p>
    							</div>
    						</div>
    					</div>
    				</div>
    				<div class="col s12 m4 center-on-small-only">
    					<div class="card-panel center competences">
    						<i class="material-icons large orange-text">settings</i>
    						<h2 class="orange-text">Back-end</h2>
    						<div class="row">
    							<div class="col s6">
    								<p>PHP</p>
    								<p>Java EE</p>
    								<p>Jersey</p>
    							</div>
    							<div class="col s6">
    								<p>MongoDB</p>
    								<p>Morphia</p>
    								<p>MySQL</p>
    							</div>
    						</div>
    					</div>
    				</div>
    				<div class="col s12 m4 center-on-small-only">
    					<div class="card-panel center competences">
    						<i class="material-icons large orange-text">build</i>
    						<h2 class="orange-text">Autres</h2>
    						<div class="row">
    							<div class="col s6">
    								<p>C</p>
    								<p>C++</p>
    								<p>Java</p>
    							</div>
    							<div class="col s6">
    								<p>Ogre3D</p>
    								<p>LeapMotion</p>
    								<p>Unix</p>
    							</div>
    						</div>
    					</div>
    				</div>

    			</div>

    		</div>
    		<div class="parallax-container">
      			<div class="parallax"><img src="img/divider1.jpg"></div>
    		</div>
    		<div id="portfolio" class="container section scrollspy">

    			<h1 class="blue-text">Port-Folio</h1>
    			<div class="divider"></div>
    			<div class="row">
    				<div class="col s12 m4 center-on-small-only">
    					<div class="card center competences">
    						
    						<div class="card-image">
    							<img src="img/guidam.png">
    						</div>
    						<div class="card-content">
    							<h2 class="orange-text">Guid'am</h2>
    							<p>
    								Guid'am est une progressive web app référancant les lieux à visiter dans la région amiénoise.
    								Elle met en scène une carte afin de demander un itinéraire.
    							</p>
    						</div>
    					</div>
    				</div>
    				<div class="col s12 m4 center-on-small-only">
    					<div class="card center competences">
    						<div class="card-image">
    							<img src="img/sfam-mesure.png">
    						</div>
    						<div class="card-content">
    							<h2 class="orange-text">SFAM-Mesures</h2>
    							<p>
    								Un logiciel d'aide à la réalisation de contrôles qualité réalisé pour l'entreprise SFAM.
    							</p>
    						</div>
    					</div>
    				</div>
    				<div class="col s12 m4 center-on-small-only">
    					<div class="card center competences">
    						<div class="card-image">
    							<img src="img/soncolis.png">
    						</div>
    						<div class="card-content">
    							<h2 class="orange-text">SonColis</h2>
    							<p>
    								SonColis permet de trouver un prestataire afin de faire livrer un colis.
    								Le projet dispose d'une progressive wep app ainsi que d'une REST api.
    							</p>
    						</div>
    					</div>
    				</div>

    			</div>

    		</div>
    		<div class="parallax-container">
      			<div class="parallax"><img src="img/divider1.jpg"></div>
    		</div>
    		<div id="contact" class="container section scrollspy">

    			<h1 class="blue-text">Contact</h1>
    			<div class="divider"></div>
    			<div class="row">
    				<div class="col s12 m4 l12 xl4 center-on-small-only">
    					<div class="card-panel center contact">
    						<p>Mes coordonnées:</p>
    						<p class="contactInfo"><i class="material-icons orange-text">email</i><span>pierre0debruyne@gmail.com</span></p>
    						<p class="contactInfo"><i class="material-icons orange-text">phone</i><span>06 68 64 59 38</span></p>
    						<a href="https://www.malt.fr/profile/pierredebruyne" target="_blanck" class="blue-text">Mon profil Malt.fr</a>
    					</div>
    				</div>
    				<div class="col s12 m1 l12 xl1 center valign-wrapper contact">
    					<p class="or"> OU </p>
    				</div>
    				<div class="col s12 m7 l12 xl7 center-on-small-only">
    					<div class="card-panel center contact">
    						<div class="contactForm">
    						Envoyez moi un message ici:
    						<form id="contactForm">
    							<div class="input-field orange-text">
    								<i class="material-icons prefix">email</i>
    								<input id="email_inline" type="email" class="validate" name="email">
    								<label for="email_inline">Votre email:</label>
    								<span class="helper-text" data-error="Mauvais email" data-success="">Indispensable afin que je puise vous recontacter.</span>
    							</div>
    							<div class="input-field orange-text">
    								<i class="material-icons prefix">chat</i>
    								<textarea id="textarea2" class="materialize-textarea" data-length="120" name="message"></textarea>
    								<label for="textarea2">Votre message ici:</label>
    							</div>
    							<button type="submit" class="waves-effect waves-light btn blue">Envoyer</button>
    						</form>
    						</div>
    						<div class="contactLoading hide">
    							<div class="preloader-wrapper big active">
    								<div class="spinner-layer spinner-blue-only">
    									<div class="circle-clipper left">
    										<div class="circle"></div>
    									</div><div class="gap-patch">
    										<div class="circle"></div>
    									</div><div class="circle-clipper right">
    										<div class="circle"></div>
    									</div>
    								</div>
    							</div>
    						</div>
    						<div class="contactSuccess hide">
    							<p class="green-text">Votre message a bien été envoyé!</p>
    						</div>
    						<div class="contactError hide">
    							<p class="red-text">Une erreur est survenue, veuillez m'excuser pour la gène occasionnée.</p>
    						</div>
    					</div>
    				</div>

    			</div>

    		</div>
    		<footer class="orange">
      			<p class="white-text center-on-small-only">© 2018 Copyright Pierre DE BRUYNE</p>
    		</footer>
    	</div>

    	

    	<!--JavaScript at end of body for optimized loading-->
    	<script type="text/javascript" src="js/materialize.min.js"></script>
    	<script src="js/jquery-2.2.3.min.js" type="text/javascript"></script>
    	<script src="js/jquery.matchHeight.js" type="text/javascript"></script>
    	<script type="text/javascript" src="js/script.js"></script>
    </body>