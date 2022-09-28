package com.christianscarselli.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	/** 
	 * @param nome
	 * @param cognome
	 * @param datanascita
	 * @return
	 * @throws IOException
	 */
	public byte[] generatePDFOutputFile(String nome, String cognome,Date datanascita) throws IOException {

		try (PDDocument document = Loader.loadPDF(loadFile(properties.getProperty("input_PdfPathFile")))){
			
			String jsString = Files.readString(Paths.get(properties.getProperty("jsFile")));
			
			PDActionJavaScript PDAjavascript = new PDActionJavaScript(jsString);
			document.getDocumentCatalog().setOpenAction(PDAjavascript);
			document.save(properties.getProperty("output_PdfPathFile"));
			document.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	
	public Date getDateFromString (String dataString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = null;
		try {  
		    date = formatter.parse(dataString);  
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return null;
		}  
		return date;
	}
	
	
  }
