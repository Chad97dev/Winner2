/**
 * 
 */

//fonction qui affiche dynamiquement l'emploi du temps 

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
function afficheEDT ()
	{
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	var numSemaine = this.value;
	var numSemaineDefaut = document.getElementById("weekNumber").value;
	var sel= document.getElementById("weekNumber");
	
	
	// Vérification de la valeur de numSemaine et utilisation de la valeur par défaut si nécessaire.
 	var semaineParam = (numSemaine === undefined || numSemaine === null) ? numSemaineDefaut : numSemaine;

  	// Requête au serveur avec les paramètres éventuels.
  	xhr.open("GET", "ctrlEDT?numSemaine=" + semaineParam);


	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{   
			// Elément html que l'on va mettre à jour.
			var xmlDoc = xhr.responseXML;
        	var seances = xmlDoc.getElementsByTagName("seance");
        	var numSeances = xmlDoc.getElementsByTagName("numSeance");
        	var date = xmlDoc.getElementsByTagName("dateSeance");
        	var heureDebSeance = xmlDoc.getElementsByTagName("heureDebSeance");
        	var heureFinSeance = xmlDoc.getElementsByTagName("heureFinSeance");
        	var cours = xmlDoc.getElementsByTagName("cours");
        	var salle = xmlDoc.getElementsByTagName("salle");
        	
        	//var elt = document.getElementById("seances");
        	//var c = "<ul> ";
        	var listeSeances = [];
			
			for (var b=1; b<=9; b++){
				for(var c=0; c<=5; c++){
					var id = 't' + `${b}` + `${c}`;
					document.getElementById(id).innerHTML="";
					document.getElementById(id).style.removeProperty('border-top');
					document.getElementById(id).style.removeProperty('border-bottom');
				}
			}
        	
        	for (var i = 0; i < seances.length; i++) {
            //	c = c + "<li><a href=CtrlFicheAppel?numSeance=" + numSeances[i].textContent + "&date=" + date[i].textContent+ "&heureDeb=" + heureDebSeance[i].textContent + "&heureFin=" + heureFinSeance[i].textContent + ">" + seances[i].textContent + "</a></li>";
        		var dict = {};
        		var duree = getNumSe(date[i].textContent, heureDebSeance[i].textContent, heureFinSeance[i].textContent);
        		dict["date"] = date[i].textContent;
        		dict["debut"] = heureDebSeance[i].textContent.substring(0,2);
        		dict["num"] = duree;
        		dict["cours"] = cours[i].textContent;
        		dict["salle"] = salle[i].textContent;
        		dict["numSeance"]=numSeances[i].textContent;
        		dict["dateA"] = date[i].textContent;
        		dict["heureDeb"] = heureDebSeance[i].textContent;
        		dict["heureFin"] = heureFinSeance[i].textContent;
        		listeSeances.push(dict);
        	} 
        	//console.log(listeSeances);
            //elt.innerHTML = c + "</ul>";
            
            // 表头
			var text= sel.options[sel.selectedIndex].text;
			var date=text.substring(text.length-10);
			document.getElementById("t11").insertAdjacentHTML("beforeend","<p>" +  date + "</p>" + "<p>LUNDI</p>");
			document.getElementById("t12").insertAdjacentHTML("beforeend","<p>" + getDate(date, 1) + "</p>" + "<p>MARDI</p>");
			document.getElementById("t13").insertAdjacentHTML("beforeend","<p>" + getDate(date, 2) + "</p>" + "<p>MERCREDI</p>");
			document.getElementById("t14").insertAdjacentHTML("beforeend","<p>" + getDate(date, 3) + "</p>" + "<p>JEUDI</p>");
			document.getElementById("t15").insertAdjacentHTML("beforeend","<p>" + getDate(date, 4) + "</p>" + "<p>VENDREDI</p>");
			
			document.getElementById("t10").insertAdjacentHTML("beforeend", "");
			document.getElementById("t20").insertAdjacentHTML("beforeend", "08:00 - 09:30");
			document.getElementById("t30").insertAdjacentHTML("beforeend", "09:30 - 11:00");
			document.getElementById("t40").insertAdjacentHTML("beforeend", "11:00 - 12:30");
			document.getElementById("t50").insertAdjacentHTML("beforeend", "12:30 - 14:00");
			document.getElementById("t60").insertAdjacentHTML("beforeend", "14:00 - 15:30");
			document.getElementById("t70").insertAdjacentHTML("beforeend", "15:30 - 17:00");
			document.getElementById("t80").insertAdjacentHTML("beforeend", "17:00 - 18:30");
			document.getElementById("t90").insertAdjacentHTML("beforeend", "18:30 - 20:00");
			
			listeSeances.forEach(function(item){
				var colonne;
				var ligne;
				switch (item["date"]){
					case date:
						colonne = "1";
						break;
					case getDate(date, 1):
						colonne = "2";
						break;
					case getDate(date, 2):
						colonne = "3";
						break;
					case getDate(date, 3):
						colonne = "4";
						break;
					case getDate(date, 4):
						colonne = "5";
						break;
				}
				
				switch (item["debut"]){
					case '08':
						ligne = "2";
						break;
					case '09':
						ligne = "3";
						break;
					case '11':
						ligne = "4";
						break;
					case '12':
						ligne = "5";
						break;
					case '14':
						ligne = "6";
						break;
					case '15':
						ligne = "7";
						break;
					case '17':
						ligne = "8";
						break;
					case '18':
						ligne = "9";
						break;
						
				}
				
				if(item["num"]==1){
					var idSeance = 't' + `${ligne}` + colonne;
					console.log(idSeance);
					document.getElementById(idSeance).insertAdjacentHTML("beforeend", "<div><a href=CtrlFicheAppel?numSeance=" + item["numSeance"] + "&date=" + item["dateA"] + "&heureDeb=" + item["heureDeb"] + "&heureFin=" + item["heureFin"] + "><p>" + item["cours"] + "</p><p> " + item["salle"] + "</p></a></div>");
				}else if (item["num"]==2){
					var idSeance = 't' + `${ligne}` + colonne;
					console.log(idSeance);
					document.getElementById(idSeance).insertAdjacentHTML("beforeend", "<div><a href=CtrlFicheAppel?numSeance=" + item["numSeance"] + "&date=" + item["dateA"] + "&heureDeb=" + item["heureDeb"] + "&heureFin=" + item["heureFin"] + "><p>" + item["cours"] + "</p><p> " + item["salle"] + "</p></a></div>");
					document.getElementById(idSeance).style.cssText = "border-bottom:0px solid #000;";
					
					ligne = +ligne + 1;
					var idSeance = 't' + `${ligne}` + colonne;
					console.log(idSeance);
					document.getElementById(idSeance).style.cssText = "border-top:0px solid #000";
				}
				
			})
			
			document.getElementById("seancesbis").style.display = "block";

            
			}
		}; 

	// Envoie de la requête.
	xhr.send();
	}


	function getDate(dateM, i){
		var dd = new Date(dateM);
	
		dd.setDate(dd.getDate()+i);
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1;
		var zero = "0";
		if(m<10){m = zero + m};
	　　	var d = dd.getDate() ;
		if(d<10){d =zero+ d};
	　　  return y + "-" + m + "-" + d;
		
	}
	
	function getNumSe(date, debut, fin){
		var d1 = date.replace(/\-/g, "/");
		var debH = new Date (d1 + " " + debut);
		var finH = new Date (d1 + " " + fin);
		return Math.floor((finH - debH) / 1000 / 60 / 60 / 1.5);
	}
	

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
	numSemaineDefaut = document.getElementById("weekNumber").value;
	document.getElementById("weekNumber").addEventListener("change",afficheEDT);
	afficheEDT();
});
