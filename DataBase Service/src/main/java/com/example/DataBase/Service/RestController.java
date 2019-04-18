package com.example.DataBase.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.discovery.EurekaClient;

import DBItems.Message;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired MessagesRepository MessagesRepository;
	@Autowired
	@Lazy
	private EurekaClient eurekaClient; 
	
	//@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.POST,value="/post")
	void postMessage(@RequestParam(name="message",required=true) String message,@RequestParam(name="timestamp",required=true) String timestamp) throws Exception {
		Message messageItem = new Message();
		messageItem.setMessage(message);
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss-SSS");
		Date date;
		try {
			date = format.parse(timestamp);
			messageItem.setTimestamp(date);
			MessagesRepository.save(messageItem);
		} catch (ParseException e) {
			throw new Exception("ParseException");
		}
	}
	
	//@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping(method=RequestMethod.GET,value="/get")
	List<Message> getMessage(@RequestParam(name="timestamp",required=false)String timestamp) throws Exception{
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss-SSS");
		Date d;
		if(!timestamp.equals("")) {
		try {
			d = df.parse(timestamp);
			return MessagesRepository.findByTimestamp(d);	
		}
		catch (ParseException e){
					throw new Exception("ParseException");
				}
		}
		else {
			d = new Date(0);
			return MessagesRepository.findByTimestamp(d);
		}
	}
	
}
