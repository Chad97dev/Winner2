/**
 * 
 */

//fonction qui affiche dynamiquement l'emploi du temps 

 function afficheEDT ()
	{
	// Objet XMLHttpRequest.
	var xhr = new XMLHttpRequest();
	var numSemaine = this.value;
	// Requête au serveur avec les paramètres éventuels.
	xhr.open("GET", "ctrlEDT?numSemaine=" + numSemaine );

	// On précise ce que l'on va faire quand on aura reçu la réponse du serveur.
	xhr.onload = function()
		{
		// Si la requête http s'est bien passée.
		if (xhr.status === 200)
			{   
			// Elément html que l'on va mettre à jour.
			var xmlDoc = xhr.responseXML;
        	var seances = xmlDoc.getElementsByTagName("seance");
        	var elt = document.getElementById("seances");
        	var c = "<ul> "
        	for (var i = 0; i < seances.length; i++) {
            	c = c + "<li><a href=>" + seances[i].textContent + "</a></li>";
        	}
            elt.innerHTML = c + "</ul>";
			}
		};

	// Envoie de la requête.
	xhr.send();
	}


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /**
 * Lancement après le chargement du DOM.
 */
document.addEventListener("DOMContentLoaded", () => {
	
	document.getElementById("weekNumber").addEventListener("change",afficheEDT);
	
	
});