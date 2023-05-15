package com.example.demo.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("/get-header")
	public ResponseEntity<String> getHeader(@RequestHeader Map<String, String> headers) {

		var httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));

		StringBuilder str = new StringBuilder();
		str.append(request.getMethod() + " " + request.getProtocol());
		
		for (var header : headers.entrySet()) {
			String key = header.getKey();
			String value = header.getValue();
			str.append(System.lineSeparator() + Character.toUpperCase(key.charAt(0)) + key.substring(1) + ": " + value);
		}

		return new ResponseEntity<>(str.toString(), httpHeaders, HttpStatus.OK);
	}

}
