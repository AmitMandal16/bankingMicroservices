package com.cpmfcu.ivr.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cpmfcu.ivr.dto.RequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class FundTransferService {

	private RestTemplate restTemplate;
	
	private final XmlMapper xmlMapper = new XmlMapper();
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Value("${cpm.request.xml}")
	private String xmlTemplete;

	@Value("${cpm.request.url}")
	private String url;

	public FundTransferService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * @author Amit Mandal
	 * @param template
	 * @param requestDTO
	 * @return
	 */
	private String replaceValues(String template, RequestDTO requestDTO) {

		Map<String, String> values = new HashMap<>();

		values.put("personSerial", requestDTO.getPersonSerial());
		values.put("loginSerial", requestDTO.getLoginSerial());
		values.put("targetCategory", requestDTO.getTargetCategory());
		values.put("targetSerial", requestDTO.getTargetSerial());
		values.put("category", requestDTO.getCategory());
		values.put("regDOption", requestDTO.getRegDOption());
		values.put("transferOption", requestDTO.getTransferOption());
		values.put("amount", requestDTO.getAmount());
		values.put("recipientSerial", requestDTO.getRecipientSerial());
		values.put("recipientCategory", requestDTO.getRecipientCategory());
		values.put("description", requestDTO.getDescription());

		String result = template;

		for (Map.Entry<String, String> entry : values.entrySet()) {
			result = result.replace("#" + entry.getKey() + "#", entry.getValue());
		}

		System.out.println(result);

		return result;
	}

	/**
	 * @author Amit Mandal
	 * @param requestDTO
	 * @return
	 */
	public String sendRequest(RequestDTO requestDTO) {

		ResponseEntity<String> response = null;

		String xmlBody = replaceValues(xmlTemplete, requestDTO);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<String> requestEntity = new HttpEntity<>(xmlBody, httpHeaders);
		
		try {
			response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
			 String xmlResponse = response.getBody();
			 
			 return convertResponseToJson(xmlResponse);
			 	
		} catch (HttpClientErrorException e) {
			
			System.err.println("HTTP error: " + e.getStatusCode());
			System.err.println("Response body: " + e.getResponseBodyAsString());
			
			return "Error: " + e.getStatusCode();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception occurred: " + e.getMessage();
		}
	}
	
	/**
	 * @author Amit Mandal
	 * @param requestDTO
	 * @return
	 * @throws IOException
	 */
	public String convertResponseToJson(String xmlResponse) throws IOException {
		
		 try {
			JsonNode jsonNode = xmlMapper.readTree(xmlResponse.getBytes());	
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }

}
