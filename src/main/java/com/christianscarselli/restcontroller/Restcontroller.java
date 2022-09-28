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

	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<byte[]> getPDF(@RequestBody Anagrafe anagrafe) {
			PdfUtil pdfUtil= new PdfUtil(properties);
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
			} 

		    
		    // Here you have to set the actual filename of your pdf
		    String filename = "output.pdf";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		    return response;
		    
	}}


