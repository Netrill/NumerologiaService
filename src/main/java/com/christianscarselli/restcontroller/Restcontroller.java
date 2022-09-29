package com.christianscarselli.restcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.christianscarselli.model.Anagrafe;
import com.christianscarselli.util.PdfUtil;

@RestController
@RequestMapping (value="/api")
public class Restcontroller {
	
	@Autowired 
	Properties properties;
	
	@Autowired 
	PdfUtil pdfUtil;

	@PostMapping (value="/getpdf",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<byte[]> getPDF(@RequestBody Anagrafe anagrafe) {
		    byte[] contents=null;
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_PDF);
			try {
				contents = pdfUtil.generatePDFOutputFile(anagrafe.getNome(), anagrafe.getCognome(), pdfUtil.getDateFromString(anagrafe.getDataNascita()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			    return response;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			    return response;
 
		    } catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		    return response;
		}

		    String filename = "ScopriChiSeiIn5Click!.pdf";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		    return response;
		    
	}

	@GetMapping (value="/gethtml")
	public  ResponseEntity<byte[]> getHTML() {
		
		    byte[] contents=null;
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.TEXT_HTML);
			
			contents = pdfUtil.loadFile(properties.getProperty("htmlfile"));
				
		    String filename = "ScopriChiSeiIn5Click!.html";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response;
		    if (contents==null)
		    	response = new ResponseEntity<>(contents, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		    else {
		    	response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		    }
		    return response;
		    
	}}


