function isY (a) {
	if (a.toUpperCase() === ("Y"))
		return true;
	return false;
};
function isVocal(a) {
	if ((a.toUpperCase() === ("A")) || (a.toUpperCase() === ("E")) || (a.toUpperCase() === ("I")) || (a.toUpperCase() === ("O")) || (a.toUpperCase() === ("U")))
		return true;
	return false;
};
function calcoloAnima (tot, tabellaPitagorica) {
	var somma = 0;
	for (var i = 0; i < tot.length; i++) {
		
			var a = tot.charAt(i);
			if (this.isVocal(a)) 
               {
				somma = somma + tabellaPitagorica.get(a.toUpperCase());
               }
    
		
	}
	if (somma === 0) {
		if ( /* contains */(tot.indexOf("y") != -1) || /* contains */ (tot.indexOf("Y") != -1)) {
			for (var i = 0; i < tot.length; i++) {
			
					var a = tot.charAt(i);
					if (this.isY(a))
						somma = somma + tabellaPitagorica.get(a.toUpperCase());
				
			}
		}
	}
	return somma;
};
function calcolaPersona(tot, tabellaPitagorica) {
	var somma = 0;
	for (var i = 0; i < tot.length; i++) {
	
		var a = tot.charAt(i);
		if (!this.isVocal(a))
			somma = somma + tabellaPitagorica.get(a.toUpperCase());

	}
	return somma;
}


function generaTabellaDifettiNumeroPersona()
{
	var tabellaDifettiNumeroPersona = new Map();
	tabellaDifettiNumeroPersona.set(1,"Egocentrico");
	tabellaDifettiNumeroPersona.set(2,"Dualit?");
	tabellaDifettiNumeroPersona.set(3,"Giocherellone");
	tabellaDifettiNumeroPersona.set(4,"Seriet?");
	tabellaDifettiNumeroPersona.set(5,"Libertino");
	tabellaDifettiNumeroPersona.set(6,"Dubbioso");
	tabellaDifettiNumeroPersona.set(7,"Snob");
	tabellaDifettiNumeroPersona.set(8,"Autoritario");
	tabellaDifettiNumeroPersona.set(9,"Vittimismo");
	return tabellaDifettiNumeroPersona;
}
function generaTabellaPitagorica()
{
	var tabellaPitagorica = new Map();
	tabellaPitagorica.set("A",1);
	tabellaPitagorica.set("J",1);
	tabellaPitagorica.set("S",1);
	tabellaPitagorica.set("B",2);
	tabellaPitagorica.set("K",2);
	tabellaPitagorica.set("T",2);
	tabellaPitagorica.set("C",3);
	tabellaPitagorica.set("L",3);
	tabellaPitagorica.set("U",3);
	tabellaPitagorica.set("D",4);
	tabellaPitagorica.set("M",4);
	tabellaPitagorica.set("V",4);
	tabellaPitagorica.set("E",5);
	tabellaPitagorica.set("N",5);
	tabellaPitagorica.set("W",5);
	tabellaPitagorica.set("F",6);
	tabellaPitagorica.set("O",6);
	tabellaPitagorica.set("X",6);
	tabellaPitagorica.set("G",7);
	tabellaPitagorica.set("P",7);
	tabellaPitagorica.set("Y",7);
	tabellaPitagorica.set("H",8);
	tabellaPitagorica.set("Q",8);
	tabellaPitagorica.set("Z",8);
	tabellaPitagorica.set("I",9);
	tabellaPitagorica.set("R",9);
	return tabellaPitagorica;
}



function riduciAunaCifra(numero) {
	var numeroStringa = numero.toString();
	var somma = 0;
	var unacifra = false;
	while ((!unacifra)) 
	{
			for (var i = 0; i < numeroStringa.length; i++) {
				
					somma = somma + parseInt((numeroStringa.charAt(i)));
			}

			if (somma < 10)
				unacifra = true;
			else {
				numeroStringa = new Number(somma).valueOf().toString();
				somma = 0;
			}
		
	}
	
	return somma;
}
var anno = 'ANNO_VARIABILE' ;
var giorno = 'GIORNO_VARIABILE';
var mese = 'MESE_VARIABILE' ;
var nome = 'NOME_VARIABILE';
var cognome = 'COGN_VARIABILE' ;

this.getField("Giorno").value = GIORNO_VARIABILE;
this.getField("Mese").value = MESE_VARIABILE;
this.getField("Anno").value = "ANNO_VARIABILE";
this.getField("Nome").value = "NOME_VARIABILE";
this.getField("Cognome").value = "COGN_VARIABILE";


var sentiero=riduciAunaCifra(anno+giorno+mese);
var tabellaPitagorica = generaTabellaPitagorica();
var anima =riduciAunaCifra(calcoloAnima (nome + cognome ,tabellaPitagorica ));
var persona =riduciAunaCifra(calcolaPersona(nome + cognome ,tabellaPitagorica));
var espressione = riduciAunaCifra(persona+anima);
var quintaEssenza = riduciAunaCifra(espressione+sentiero) ;
var ombraGioventu=Math.abs(riduciAunaCifra(anno)-riduciAunaCifra(giorno));
var ombraAdulto=Math.abs(riduciAunaCifra(mese)-riduciAunaCifra(giorno));
var ombraPrincipale=Math.abs(ombraGioventu-ombraAdulto);
var icon,icon2 ;

switch(sentiero) {
     case 1 : icon = this.getField("Numero1Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero1").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Guerriero";
              this.getField("TextSentiero").value = "1";
			break;
     case 2 : icon = this.getField("Numero2Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero2").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Fanciullo";
              this.getField("TextSentiero").value = "2";
			break;
     case 3 : icon = this.getField("Numero3Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero3").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Giullare";
              this.getField("TextSentiero").value = "3";
			break;
     case 4 : icon = this.getField("Numero4Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero4").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Costruttore";
              this.getField("TextSentiero").value = "4";
			break;
     case 5 : icon = this.getField("Numero5Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero5").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Ricercatore";
              this.getField("TextSentiero").value = "5";
              break;
     case 6 : icon = this.getField("Numero6Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero6").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Angelo Custode";
              this.getField("TextSentiero").value = "6";
              break;
	case 7 : icon = this.getField("Numero7Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero7").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Saggio";
              this.getField("TextSentiero").value = "7";
			break;
     case 8 : icon = this.getField("Numero8Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero8").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Sovrano";
              this.getField("TextSentiero").value = "8";
			break;
     case 9 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoSentiero").value = "Liberatore";
              this.getField("TextSentiero").value = "9";
			break;
	default: app.alert('Errore' + sentiero);
              icon = this.getField("NumeroError").buttonGetIcon();
		     break;
		
}
this.getField("ButtonSentiero").buttonSetIcon(icon);
this.getField("ButtonSentiero").buttonPosition=position.iconOnly;
this.getField("ButtonRiassuntoSentiero").buttonSetIcon(icon2);
this.getField("ButtonRiassuntoSentiero").buttonPosition=position.iconOnly;
switch(anima) {
     case 1 : icon = this.getField("Numero1Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero1").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Guerriero";
              this.getField("TextAnima").value = "1";
			break;
     case 2 : icon = this.getField("Numero2Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero2").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Fanciullo";
              this.getField("TextAnima").value = "2";
			break;
     case 3 : icon = this.getField("Numero3Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero3").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Giullare";
              this.getField("TextAnima").value = "3";
			break;
     case 4 : icon = this.getField("Numero4Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero4").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Costruttore";
              this.getField("TextAnima").value = "4";
			break;
     case 5 : icon = this.getField("Numero5Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero5").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Ricercatore";
              this.getField("TextAnima").value = "5";
              break;
     case 6 : icon = this.getField("Numero6Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero6").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Angelo Custode";
              this.getField("TextAnima").value = "6";
              break;
	case 7 : icon = this.getField("Numero7Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero7").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Saggio";
              this.getField("TextAnima").value = "7";
			break;
     case 8 : icon = this.getField("Numero8Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero8").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Sovrano";
              this.getField("TextAnima").value = "8";
			break;
     case 9 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoAnima").value = "Liberatore";
              this.getField("TextAnima").value = "9";
			break;
	default: icon = this.getField("NumeroError").buttonGetIcon();
              
		     break;
		
}
this.getField("ButtonAnima").buttonSetIcon(icon);
this.getField("ButtonAnima").buttonPosition=position.iconOnly;

this.getField("ButtonRiassuntoAnima").buttonSetIcon(icon2);
this.getField("ButtonRiassuntoAnima").buttonPosition=position.iconOnly;

switch(espressione) {
     case 1 : icon = this.getField("Numero1Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero1").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Guerriero";
              this.getField("TextEspressione").value = "1";
			break;
     case 2 : icon = this.getField("Numero2Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero2").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Fanciullo";
              this.getField("TextEspressione").value = "2";
			break;
     case 3 : icon = this.getField("Numero3Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero3").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Giullare";
              this.getField("TextEspressione").value = "3";
			break;
     case 4 : icon = this.getField("Numero4Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero4").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Costruttore";
              this.getField("TextEspressione").value = "4";
			break;
     case 5 : icon = this.getField("Numero5Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero5").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Ricercatore";
              this.getField("TextEspressione").value = "5";
              break;
     case 6 : icon = this.getField("Numero6Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero6").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Angelo Custode";
              this.getField("TextEspressione").value = "6";
              break;
	case 7 : icon = this.getField("Numero7Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero7").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Saggio";
              this.getField("TextEspressione").value = "7";
			break;
     case 8 : icon = this.getField("Numero8Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero8").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Sovrano";
              this.getField("TextEspressione").value = "8";
			break;
     case 9 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoEspressione").value = "Liberatore";
              this.getField("TextEspressione").value = "9";
			break;
	default: icon = this.getField("NumeroError").buttonGetIcon();
		     break;
		
}
this.getField("ButtonEspressione").buttonSetIcon(icon);
this.getField("ButtonEspressione").buttonPosition=position.iconOnly;

this.getField("ButtonRiassuntoEspressione").buttonSetIcon(icon2);
this.getField("ButtonRiassuntoEspressione").buttonPosition=position.iconOnly;
switch(persona) {
     case 1 : icon = this.getField("Numero1Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero1").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Guerriero";
              this.getField("TextPersona").value = "1";
			break;
     case 2 : icon = this.getField("Numero2Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero2").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Fanciullo";
              this.getField("TextPersona").value = "2";
			break;
     case 3 : icon = this.getField("Numero3Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero3").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Giullare";
              this.getField("TextPersona").value = "3";
			break;
     case 4 : icon = this.getField("Numero4Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero4").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Costruttore";
              this.getField("TextPersona").value = "4";
			break;
     case 5 : icon = this.getField("Numero5Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero5").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Ricercatore";
              this.getField("TextPersona").value = "5";
              break;
     case 6 : icon = this.getField("Numero6Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero6").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Angelo Custode";
              this.getField("TextPersona").value = "6";
              break;
	case 7 : icon = this.getField("Numero7Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero7").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Saggio";
              this.getField("TextPersona").value = "7";
			break;
     case 8 : icon = this.getField("Numero8Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero8").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Sovrano";
              this.getField("TextPersona").value = "8";
			break;
     case 9 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoPersona").value = "Liberatore";
              this.getField("TextPersona").value = "9";
			break;
	default: icon = this.getField("NumeroError").buttonGetIcon();
		     break;
		
}
this.getField("ButtonPersona").buttonSetIcon(icon);
this.getField("ButtonPersona").buttonPosition=position.iconOnly;
this.getField("ButtonRiassuntoPersona").buttonSetIcon(icon2);
this.getField("ButtonRiassuntoPersona").buttonPosition=position.iconOnly;
switch(quintaEssenza) {
     case 1 : icon = this.getField("Numero1Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero1").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Guerriero";
              this.getField("TextQuintaessenza").value = "1";
			break;
     case 2 : icon = this.getField("Numero2Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero2").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Fanciullo";
              this.getField("TextQuintaessenza").value = "2";
			break;
     case 3 : icon = this.getField("Numero3Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero3").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Giullare";
              this.getField("TextQuintaessenza").value = "3";
			break;
     case 4 : icon = this.getField("Numero4Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero4").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Costruttore";
              this.getField("TextQuintaessenza").value = "4";
			break;
     case 5 : icon = this.getField("Numero5Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero5").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Ricercatore";
              this.getField("TextQuintaessenza").value = "5";
              break;
     case 6 : icon = this.getField("Numero6Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero6").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Angelo Custode";
              this.getField("TextQuintaessenza").value = "6";
              break;
	case 7 : icon = this.getField("Numero7Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero7").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Saggio";
              this.getField("TextQuintaessenza").value = "7";
			break;
     case 8 : icon = this.getField("Numero8Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero8").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Sovrano";
              this.getField("TextQuintaessenza").value = "8";
			break;
     case 9 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoQuintaessenza").value = "Liberatore";
              this.getField("TextQuintaessenza").value = "9";
			break;
	default: icon = this.getField("NumeroError").buttonGetIcon();
		     break;
		
}
this.getField("ButtonQuintaessenza").buttonSetIcon(icon);
this.getField("ButtonQuintaessenza").buttonPosition=position.iconOnly;
this.getField("ButtonRiassuntoQuintaessenza").buttonSetIcon(icon2);
this.getField("ButtonRiassuntoQuintaessenza").buttonPosition=position.iconOnly;
switch(ombraPrincipale) {
     case 1 : icon = this.getField("Numero1Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero1").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Guerriero";
              this.getField("TextOmbra").value = "1";
			break;
     case 2 : icon = this.getField("Numero2Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero2").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Fanciullo";
              this.getField("TextOmbra").value = "2";
			break;
     case 3 : icon = this.getField("Numero3Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero3").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Giullare";
              this.getField("TextOmbra").value = "3";
			break;
     case 4 : icon = this.getField("Numero4Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero4").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Costruttore";
              this.getField("TextOmbra").value = "4";
			break;
     case 5 : icon = this.getField("Numero5Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero5").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Ricercatore";
              this.getField("TextOmbra").value = "5";
     case 6 : icon = this.getField("Numero6Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero6").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Angelo Custode";
              this.getField("TextOmbra").value = "6";
	case 7 : icon = this.getField("Numero7Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero7").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Saggio";
              this.getField("TextOmbra").value = "7";
			break;
     case 8 : icon = this.getField("Numero8Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero8").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Sovrano";
              this.getField("TextOmbra").value = "8";
			break;
     case 9 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Liberatore";
              this.getField("TextOmbra").value = "9";
			break;
     case 0 : icon = this.getField("Numero9Desc").buttonGetIcon();
              icon2 = this.getField("ButtonNumero9").buttonGetIcon();
              this.getField("TextRisultatoOmbra").value = "Liberatore";
              this.getField("TextOmbra").value = "9";
			break;
	default: icon = this.getField("NumeroError").buttonGetIcon();
		     break;
		
}
this.getField("ButtonOmbra").buttonSetIcon(icon);
this.getField("ButtonOmbra").buttonPosition=position.iconOnly;
this.getField("ButtonRiassuntoOmbra").buttonSetIcon(icon2);
this.getField("ButtonRiassuntoOmbra").buttonPosition=position.iconOnly;
