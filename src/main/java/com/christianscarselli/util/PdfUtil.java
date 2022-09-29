package com.christianscarselli.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;



public class PdfUtil {
	
	private Properties properties;
	
	
	public PdfUtil (Properties properties) {
		this.properties=properties;
	}
	
	
	public byte [] loadFile (String filePath) {
		byte [] contents = null;
		try (FileInputStream iStream = new FileInputStream(filePath)) {
			contents=iStream.readAllBytes();
		}catch (IOException e) {
		    e.printStackTrace();
	    }
		catch (NullPointerException e) {
		       e.printStackTrace();
		}
		return contents;
	  }
	
	public boolean writeFile (String name,byte [] contents) {
		try (FileOutputStream stream = new FileOutputStream(properties.getProperty(name))) {
		    stream.write(contents);		    
		}catch (IOException e) {
		       e.printStackTrace();
		       return false;
	    }
		catch (NullPointerException e) {
	       e.printStackTrace();
	       return false;
		}
		return true;
	}
	

	public byte[] generatePDFOutputFile(String nome, String cognome,String [] date) throws IOException,NullPointerException,FileNotFoundException {

		try (PDDocument document = Loader.loadPDF(loadFile(properties.getProperty("input_PdfPathFile")))){
			
			String jsString = Files.readString(Paths.get(properties.getProperty("jsFile")));
			
			jsString =modificiesJS(jsString,nome,cognome,date);
			
			PDActionJavaScript PDAjavascript = new PDActionJavaScript(jsString);
						
			document.getDocumentCatalog().setOpenAction(PDAjavascript);
			document.save(properties.getProperty("output_PdfPathFile"));
			document.close();
		}
		
		return loadFile(properties.getProperty("output_PdfPathFile"));
	}
	
	
	private String modificiesJS(String jsString, String nome, String cognome, String [] dataNascita) {
		jsString = jsString.replace("NOME_VARIABILE", nome);
		jsString = jsString.replace("COGN_VARIABILE", cognome);
		jsString = jsString.replace("GIORNO_VARIABILE", String.valueOf(dataNascita[0]));
		jsString = jsString.replace("MESE_VARIABILE", String.valueOf(dataNascita[1]));
		jsString = jsString.replace("ANNO_VARIABILE", String.valueOf(dataNascita[2]));
		return jsString;
	}


	public String [] getDateFromString (String dataString) {
 
		return  dataString.split("/");

	}
	
	
 }
