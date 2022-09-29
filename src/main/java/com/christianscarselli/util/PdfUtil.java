package com.christianscarselli.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;

import org.apache.commons.io.FileUtils;

public class PdfUtil {
	
	private Properties properties;
	
	
	public PdfUtil (Properties properties) {
		this.properties=properties;
	}
	
	
	public byte [] loadFile (String name) {
		byte [] contents = null;
		try (InputStream iStream = this.getClass().getClassLoader().getResourceAsStream(name)) {
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

		try (PDDocument document = Loader.loadPDF(this.getClass().getClassLoader().getResourceAsStream(properties.getProperty("input_Pdf")))){
		
		
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(properties.getProperty("jsFile"));
			
			String jsString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			
			jsString =modificiesJS(jsString,nome,cognome,date);
			
			PDActionJavaScript PDAjavascript = new PDActionJavaScript(jsString);
			File temporaneypdf = new File("temporaneypdf.pdf");		
			document.getDocumentCatalog().setOpenAction(PDAjavascript);
			document.save(temporaneypdf);
			document.close();
			byte [] contents = FileUtils.readFileToByteArray(temporaneypdf);
			temporaneypdf.delete();
			return  contents;
		}
		
		
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
