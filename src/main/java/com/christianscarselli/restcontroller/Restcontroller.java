package com.christianscarselli.restcontroller;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.christianscarselli.entities.Iscritto;
import com.christianscarselli.model.IscrittoModel;
import com.christianscarselli.service.IscrittoService;
import com.christianscarselli.util.PdfUtil;

@RestController
@RequestMapping (value="/api")
public class Restcontroller {
	
	@Autowired 
	Properties properties;
	
	@Autowired 
	PdfUtil pdfUtil;
	
	@Autowired
	IscrittoService iscrittoServiceImpJPQL;
	

	@PostMapping (value="/getpdf",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<byte[]> getPDF(@RequestBody IscrittoModel iscritto) {
		    byte[] contents=null;
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_PDF);
		    String filename = "ScopriChiSeiIn5Click!.pdf";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    int numInsertRow = 0;
		    ResponseEntity<byte[]> response = null;
		    
			try {
				Iscritto i = new Iscritto ();
				i.setEmail(iscritto.getEmail());
				i.setNome(iscritto.getNome());
				i.setCognome(iscritto.getCognome());
				
				i.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse(iscritto.getDataNascita())  );
				numInsertRow = iscrittoServiceImpJPQL.insert(i);
				contents = pdfUtil.generatePDFOutputFile(iscritto.getNome(), iscritto.getCognome(), pdfUtil.getDateFromString(iscritto.getDataNascita()));
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response = new ResponseEntity<>(contents, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			    return response;
			}

			if (contents!=null && numInsertRow==1)
				response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
			else {
				response = new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return response;
		    
	}

	@GetMapping (value="/gethtml")
	public  ResponseEntity<byte[]> getHTML(@RequestParam("email") String email,
			@RequestParam("nome") String nome,@RequestParam("cognome") String cognome,@RequestParam("datanascita") String datanascita) {
		try {
			
		    byte[] contents=null;
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.TEXT_HTML);
		    contents = pdfUtil.loadFile(properties.getProperty("htmlfile"));
		    String filename = "ScopriChiSeiIn5Click!.html";
		    headers.setContentDispositionFormData(filename, filename);
		    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		    ResponseEntity<byte[]> response;
		    int numInsertRow = 0;
		    
		    Iscritto i = new Iscritto ();
			i.setEmail(email);
			i.setNome(nome);
			i.setCognome(cognome);
			i.setDataNascita(new SimpleDateFormat("dd/MM/yyyy").parse(datanascita)  );
			numInsertRow = iscrittoServiceImpJPQL.insert(i);
			
		
		    if (contents!=null && numInsertRow==1)
		    	response = new ResponseEntity<>(contents, headers, HttpStatus.OK);	
		    else {
		    	response = new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		    return response;
		
		}catch(Exception e) {
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}}


