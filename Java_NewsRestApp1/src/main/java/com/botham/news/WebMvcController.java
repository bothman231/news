package com.botham.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.botham.news.db.jobs.JobsRepository;
import com.botham.news.domain.jobs.Jobs;

@Controller

public class WebMvcController {
	   Logger log = LoggerFactory.getLogger(WebMvcController.class);
	
	   @Autowired
	   JobsRepository jobsRepository;
	   
	   
	   
	   // http://www.thymeleaf.org/doc/articles/springmvcaccessdata.html	
	   
	   
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name,
    		               @RequestParam(value="pageSize", required=false, defaultValue="5") String pageSize,
    		               @RequestParam(value="search", required=false, defaultValue="") String search,
    		               Model model) {
    	

    	String mName="greeting";
    	if (log.isDebugEnabled()) {
    		log.debug(mName+" Starts "+"pagesize="+pageSize);
    		log.debug(mName+"        "+"search="+search);
    	}
    	
    	
    	if (pageSize==null || pageSize.isEmpty()) {
    	   pageSize="5";	
    	}
    	
    	
        model.addAttribute("name", name);
        
        Map <String, String> columns = new TreeMap<String, String>();
        columns.put("name", "Name");
        columns.put("description", "Desc.");
        columns.put("lastRun", "Last");
        columns.put("status", "Status");
        columns.put("currentStatus", "Current Status");
        columns.put("start", "Start");
        columns.put("end", "End");
        columns.put("info", "Info");
        columns.put("schedule", "Schedule");
        columns.put("id", "Id");
        columns.put("notificationsSent", "NS");
        
        columns.put("activeStart", "Active Start");
        columns.put("activeEnd", "Active End");
        
        columns.put("runOnInstances", "Instance");
        columns.put("runOnNodes", "Nodes");
        columns.put("statusAcknowledged", "Ack");

        
        //Pageable 
        
        
        int startPage=0;
        //int pageSize1=5;
        
        
        boolean firstTime=true;
        boolean firstRow=true;
        
        StringBuilder html=new StringBuilder("");
        
		//List<Jobs> jobsList = jobsRepository.findAll();
		//List<Jobs> jobsList = jobsRepository.findByNameLike("%"+search+"%");
		List<Jobs> jobsList = jobsRepository.findByNameLike(new PageRequest(startPage, Integer.parseInt(pageSize)), "%"+search+"%");
		
		for (Jobs j:jobsList) {
			
			if (firstTime) {
				firstTime=false;
				html.append("<TABLE>");
				
			}
			
			html.append("<TR>");
			
			for(Map.Entry<String,String> entry : columns.entrySet()) {
				  String key = entry.getKey();
				  String value = entry.getValue();
				  html.append("<TD>");
				  html.append(value);
				  html.append("<TD>");
				  //System.out.println(key + " => " + value);
				}
			
			html.append("</TR>");
			
			if (log.isDebugEnabled()) {
				log.debug(mName+" job="+j.toString());
			}
			
			
		}
		
		if (!firstTime) {
			html.append("</TABLE>");
		}
		
		String tableName=html.toString();
		
		//re.setObjects((List<Jobs>) jobsList);
		name=html.toString();
    	model.addAttribute("tableName", tableName);
    	model.addAttribute("jobs", jobsList);
    	model.addAttribute("columns", columns);
    	
    	if (columns.containsKey("description")) {
    		
    	}
    	
    	model.addAttribute("search", search);    	
    	model.addAttribute("pageSize", pageSize);
    	
        
        log.debug(mName+"html="+html);
        return "greeting";
    }

}