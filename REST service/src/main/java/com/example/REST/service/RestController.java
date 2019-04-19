package com.example.REST.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
//import org.springframework.cloud.client.discovery.DiscoveryClient;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private EurekaClient eurekaClient; 
	
	//@Autowired
	//private DiscoveryClient discoveryClient;

	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.POST,value="/post")
	void postMessage(@RequestParam(name="message",required=true) String message,@RequestParam(name="timestamp",required=true) String timestamp) throws Exception {
		Message messageItem = new Message();
		messageItem.setMessage(message);
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss-SSS");
		Date date;
		try {
			date = format.parse(timestamp);
		} catch (ParseException e) {
			throw new Exception("ParseException");
		}
			messageItem.setTimestamp(date);
			InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("Database-Service", false);
			//InstanceInfo instanceInfo2 = discoveryClient.getNextServerFromEureka("DATABASE-SERVICE", false);
			String host = instanceInfo.getHostName();
			int port = instanceInfo.getPort();
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
			parts.add("message", message);
			parts.add("timestamp", timestamp);
			restTemplate.postForEntity("http://"+host+":"+port+"/post",parts,null);
			
		
	}
	
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.GET,value="/get")
	List<Message> getMessage(@RequestParam(name="timestamp",required=false)String timestamp) throws Exception{
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss-SSS");
		Date d;
		if(!timestamp.equals("")) {
		try {
			d = df.parse(timestamp);
			InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("Database-Service", false);
			String host = instanceInfo.getHostName();
			int port = instanceInfo.getPort();
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			parts.add("timestamp", timestamp);
			ResponseEntity<List<Message>> ret = restTemplate.exchange("http://"+host+":"+port+"/get?timestamp="+timestamp, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		return ret.getBody();
		}
		catch (ParseException e){
					throw new Exception("ParseException");
				}
		}
		else {
			d = new Date(0);
			InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("DATABASE-SERVICE", false);
			String host = instanceInfo.getHostName();
			int port = instanceInfo.getPort();
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
			parts.add("timestamp", timestamp);
			ResponseEntity<List<Message>> ret = restTemplate.exchange("http://"+host+":"+port+"/get?timestamp="+timestamp, HttpMethod.GET, null, new ParameterizedTypeReference<List<Message>>() {});
		return ret.getBody();
		}
	}
	
}
