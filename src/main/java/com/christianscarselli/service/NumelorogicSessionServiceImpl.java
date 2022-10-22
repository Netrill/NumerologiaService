package com.christianscarselli.service;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class NumelorogicSessionServiceImpl implements NumerologicService {
	private HashMap<String, Integer> tabellaPitagorica;
	private HashMap<Integer, String> tabellaDifettiNumeroPersona;
	private String nome,cognome;
	private Integer giorno,mese,anno;
	private Integer sentiero,destino,realizzazione,anima,persona,espressione,quintaEssenza;
	private Integer culmine1,culmine2,culmine3,culmine4;
	private Integer sfida1,sfida2,sfida3,sfida4;
	private Integer annoTransizione1,annoTransizione2,annoTransizione3,annoTransizione4;
	private HashMap <Integer,Integer> distribuzioneCaratteriNome = new HashMap<Integer,Integer> ();
	private Integer ombraGioventu,ombraMaturità,ombraPrincipale;
	

	@Override
	public byte[] getNumerologicSession(String nome, String cognome, String dataNascita) {

		PrintStream fileStream;
		try {
			
			this.nome=nome;
			this.cognome=cognome;
			String dataNascitaScomposta []  = dataNascita.split("/");
			this.giorno = Integer.valueOf(dataNascitaScomposta[0]);	
			this.mese = Integer.valueOf(dataNascitaScomposta[1]);
			this.anno = Integer.valueOf(dataNascitaScomposta[2]);
			
			fileStream = new PrintStream("Numerologia_"+nome+cognome+".txt");
		
			System.setOut(fileStream);
			//n.calcoloNumeriNomi();
			
			generaTabellaPitagorica();
			generaTabellaDifettiNumeroPersona();
			
			
			
			//Calcolo giorno di nasciata
			this.calcoloGiornoNascita();
			System.out.println();
			//Somma vocali, la y se è l' unica vocale
			this.calcoloAnima();
			System.out.println();
			//Somma consonanti
			this.calcolaPersona();
			System.out.println();
			//Somma persona + anima 
			this.calcolaEspressione();
			System.out.println();
			// Somma data nascita
			this.calcoloSentiero();
			System.out.println();
			// Somma data nascita
			this.calcolaQuintaessenza();
			
			System.out.println();
			System.out.println();
			//somma tutti i caratteri del nome e cognome
			this.calcolaNumeroDestino();
			//Sentiero + destino
			this.calcolaRealizzazione();
			
			System.out.println();
			System.out.println();
			this.calcolaOmbre();
			
			System.out.println();
			System.out.println();
			this.calcoloCulmini();
			
			System.out.println();
			System.out.println();
			
			this.calcoloAnnoPersonale();
			
			System.out.println();
			System.out.println();
			
			this.calcolaCaratteristiche();
			
			;
			InputStream input = this.getClass().getClassLoader().getResourceAsStream("Numerologia_"+nome+cognome+".txt");
			return input.readAllBytes();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	private void calcoloNumeriNomi () {
		boolean condizione=false;
		Scanner sc = new Scanner(System. in );
		while(!condizione) {
		    System.out.println("Digita nome");
		    String nome = sc.nextLine();
		    nome = nome.replaceAll(" ", "");
		    if (nome.equals(""))
		    	condizione=true;
		    else{
		    	calcolaCifreNome(nome);
		    }
		    
		}
	}
	private void calcolaOmbre() {
		this.ombraGioventu=Math.abs(this.riduciAunaCifra(this.anno)-this.riduciAunaCifra(this.mese));
		this.ombraMaturità=Math.abs(this.riduciAunaCifra(this.anno)-this.riduciAunaCifra(this.giorno));
		this.ombraPrincipale=Math.abs(this.ombraGioventu-this.ombraMaturità);
		System.out.println("La prima ombra di gioventù che va dalla nascita agli 30 è data dal numero : " + ombraGioventu);
		System.out.println("La seconda ombra che va dai 30 anni in poi è data dal numero : " + this.ombraMaturità);
		System.out.println("L' ombra principale è  : " + this.ombraPrincipale);
		
	}
	private void calcolaCaratteristiche() {
		Iterator <Map.Entry<Integer,Integer>>  iterator = this.distribuzioneCaratteriNome.entrySet().stream()
		   .sorted(Map.Entry.comparingByValue()).iterator();
		Map.Entry<Integer, Integer> next ;
		while(iterator.hasNext()) {
			next = iterator.next();
			System.out.println ("Il numero " + next.getKey() + " è stato ripetuto "+ next.getValue());
			
		}
		
	}
 
	private void calcoloAnnoPersonale() {
		LocalDate current_date = LocalDate.now();
		int year=current_date.getYear();
		int mese = current_date.getMonthValue();
		int giorno = current_date.getDayOfMonth();
		System.out.println("Numero calcolato dell' anno personale del: " + year + " è  " + this.riduciAunaCifra(this.giorno+this.mese+year));
		System.out.println("Numero calcolato del mese personale: " + this.riduciAunaCifra(this.anno+mese));
		System.out.println("Numero calcolato del giorno personale :  " + this.riduciAunaCifra(giorno+mese+this.anno));
		
	}
	private void calcoloCulmini() {
		
		
		this.culmine1=riduciAunaCifra(this.giorno+this.mese);
		this.culmine2=riduciAunaCifra(this.giorno+this.anno);
		this.culmine3=riduciAunaCifra(this.culmine1+this.culmine2);
		this.culmine4=riduciAunaCifra(this.mese+this.anno);
		
		this.sfida1=riduciAunaCifra(Math.abs(this.mese-this.giorno));
		this.sfida2=riduciAunaCifra(Math.abs(this.giorno-this.anno));
		this.sfida3=riduciAunaCifra(Math.abs(this.sfida1-this.sfida2));
		this.sfida4=riduciAunaCifra(Math.abs(this.mese-this.anno));
		
		this.annoTransizione1=new Integer((36-riduciAunaCifra(this.sentiero)));
		this.annoTransizione2=annoTransizione1+9;
		this.annoTransizione3=annoTransizione2+9;
		this.annoTransizione4=annoTransizione3+9;
		
		System.out.println("Il 1° culmine dal 1 anno all' anno " + new Integer((36-riduciAunaCifra(this.sentiero))) + " e' "+ this.culmine1 + " e la sfida sarà data dal numero : "+ this.sfida1);
		System.out.println("Il 2° culmine dagli anni " + new Integer(this.annoTransizione1.intValue()+1) + " agli anni " + this.annoTransizione2 + " e': "+ this.culmine2 + " e la sfida sarà data dal numero : "+ this.sfida2);
		System.out.println("Il 3° culmine dagli anni " + new Integer(this.annoTransizione2.intValue()+1) + " agli anni " + this.annoTransizione3 + " e': "+ this.culmine3 + " e la sfida sarà data dal numero : "+ this.sfida3);
		System.out.println("Il 4° culmine dagli anni " + new Integer(this.annoTransizione3.intValue()+1) + " agli anni " + this.annoTransizione4 + " e': "+ this.culmine4 + " e la sfida sarà data dal numero : "+ this.sfida4);
		
	}
	private void calcoloGiornoNascita() {
		System.out.println(" Il valore del giorno di nascita  è " +this.giorno + " ridotto a una cifra: " + riduciAunaCifra(this.giorno));
		
	}
	private void calcolaQuintaessenza() {
		this.quintaEssenza=this.espressione+this.sentiero;
		System.out.println(" Il valore della quinta essenza è " +quintaEssenza + " ridotto a una cifra: " + riduciAunaCifra(quintaEssenza));
		
	}
	private void calcolaEspressione() {
		this.espressione=this.persona+this.anima;
		System.out.println(" Il valore dell' espressione è " +espressione + " ridotto a una cifra: " + riduciAunaCifra(espressione));
	}
	private void calcolaCifreNome(String nome) {
		String tot = nome;
		int somma =0;
		for (int i =0; i<tot.length();i++) {
			String a = Character.toString(tot.charAt(i));
			somma= somma + tabellaPitagorica.get(a.toUpperCase());
			
			
		}
		System.out.println(" Il valore della nome  è " +somma + " ridotto a una cifra: " + riduciAunaCifra(somma));
		
	}
	private void calcolaPersona() {
		String tot = nome+cognome;
		int somma =0;
		for (int i =0; i<tot.length();i++) {
			String a = Character.toString(tot.charAt(i));
			if (!isVocal(a))
				somma= somma + tabellaPitagorica.get(a.toUpperCase());
		}
		this.persona=somma;
		System.out.println(" Il valore della persona è " +somma + " ridotto a una cifra: " + riduciAunaCifra(somma));
		
	}
	private void calcolaRealizzazione() {
		this.realizzazione=Integer.valueOf(this.sentiero + this.destino);
		System.out.println(" Il valore della realizzazione è " + this.realizzazione + " ridotto a una cifra: " + riduciAunaCifra(this.sentiero + this.destino));
		
	}
	private void calcolaNumeroDestino() {
		HashMap <Integer,Integer>hs = new HashMap<Integer,Integer> ();
		String tot = nome+cognome;
		int somma =0;
		for (int i =0; i<tot.length();i++) {
			String carattereNome = Character.toString(tot.charAt(i)).toUpperCase();
			if (hs.get(tabellaPitagorica.get(carattereNome))!=null)
				hs.put(tabellaPitagorica.get(carattereNome), hs.get(tabellaPitagorica.get(carattereNome)).intValue()+1);
			else {
				hs.put(tabellaPitagorica.get(carattereNome), 1);
			}
			somma= somma + tabellaPitagorica.get(carattereNome.toUpperCase());
		}
		this.destino=somma;
		this.distribuzioneCaratteriNome=hs;
		System.out.println(" Il valore del destino è " + somma + " ridotto a una cifra: " + riduciAunaCifra(somma));
		
	}
	private void calcoloAnima() {
		
		String tot = nome+cognome;
		int somma =0;
		for (int i =0; i<tot.length();i++) {
			String a = Character.toString(tot.charAt(i));
			if (isVocal(a))
				somma= somma + tabellaPitagorica.get(a.toUpperCase());
		}
		//non ci sono vocali, controllo se è presente y
		if (somma==0) {
			if (tot.contains("y") || tot.contains("Y")) {
				for (int i =0; i<tot.length();i++) {
					String a = Character.toString(tot.charAt(i));
					if (isY(a))
						somma= somma + tabellaPitagorica.get(a.toUpperCase());
				}
			}
		}
		this.anima=somma;
		System.out.println(" Il valore del' anima è " +somma + " ridotto a una cifra: " + riduciAunaCifra(somma));
		
	}
	
	
	private int contaVocali (String nome) {
		int count=0;
		for (int i =0; i<nome.length();i++) {
			if(isVocal(Character.toString(nome.charAt(i))))
				count++;	
		}
		return count;
	}
	private boolean isVocal (String a) {
		if (a.toUpperCase().equals("A") || a.toUpperCase().equals("E") || a.toUpperCase().equals("I") || a.toUpperCase().equals("O") || a.toUpperCase().equals("U") )
			return true ;
		return false;
	}
	private boolean isY (String a) {
		if (a.toUpperCase().equals("Y"))
			return true ;
		return false;
	}

	private void calcoloSentiero() {

		String annoStringa =anno.toString();
		int somma=0;
		for (int i =0; i<annoStringa.length();i++) {
			somma = somma + Character.getNumericValue(annoStringa.charAt(i));
		}
		somma = somma + mese.intValue() + giorno.intValue() ;
		this.sentiero=somma;
		System.out.println(" Il valore del sentiero è: " +somma + " ridotto a una cifra: " + riduciAunaCifra(somma));
	}
	
	private int riduciAunaCifra (Integer numero) {
		String numeroStringa =numero.toString();
		int somma=0;
		boolean unacifra=false;
		while (!unacifra) {
			for (int i =0; i<numeroStringa.length();i++) {
				somma = somma + Character.getNumericValue(numeroStringa.charAt(i));
			}
			if (somma<10)
				unacifra=true;
			else {
				numeroStringa = new Integer(somma).toString();
				somma=0;
			}
				
		}
		return somma;
	}

	private void generaTabellaDifettiNumeroPersona() {
		tabellaDifettiNumeroPersona  = new HashMap<Integer, String>();
		
		tabellaDifettiNumeroPersona.put(1, "Egocentrico");
		tabellaDifettiNumeroPersona.put(2, "Dualità");
		tabellaDifettiNumeroPersona.put(3, "Giocherellone");
		tabellaDifettiNumeroPersona.put(4, "Serietà");
		tabellaDifettiNumeroPersona.put(5, "Libertino");
		tabellaDifettiNumeroPersona.put(6, "Dubbioso");
		tabellaDifettiNumeroPersona.put(7, "Snob");
		tabellaDifettiNumeroPersona.put(8, "Autoritario");
		tabellaDifettiNumeroPersona.put(9, "Vittimismo");
		
	}
	private HashMap<String, Integer> generaTabellaPitagorica () {
		
		tabellaPitagorica = new HashMap<String, Integer>();
		tabellaPitagorica.put("A", 1);
		tabellaPitagorica.put("J", 1);
		tabellaPitagorica.put("S", 1);
		
		tabellaPitagorica.put("B", 2);
		tabellaPitagorica.put("K", 2);
		tabellaPitagorica.put("T", 2);
		
		tabellaPitagorica.put("C", 3);
		tabellaPitagorica.put("L", 3);
		tabellaPitagorica.put("U", 3);
		
		tabellaPitagorica.put("D", 4);
		tabellaPitagorica.put("M", 4);
		tabellaPitagorica.put("V", 4);
		
		tabellaPitagorica.put("E", 5);
		tabellaPitagorica.put("N", 5);
		tabellaPitagorica.put("W", 5);
		
		tabellaPitagorica.put("F", 6);
		tabellaPitagorica.put("O", 6);
		tabellaPitagorica.put("X", 6);
		
		tabellaPitagorica.put("G", 7);
		tabellaPitagorica.put("P", 7);
		tabellaPitagorica.put("Y", 7);
		
		tabellaPitagorica.put("H", 8);
		tabellaPitagorica.put("Q", 8);
		tabellaPitagorica.put("Z", 8);
		
		tabellaPitagorica.put("I", 9);
		tabellaPitagorica.put("R", 9);


		return tabellaPitagorica;
		
	}
	
	
	
	
}
