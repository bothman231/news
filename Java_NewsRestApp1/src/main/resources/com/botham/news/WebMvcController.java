package com.botham.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	

    	String mName="greeting";
        model.addAttribute("name", name);
        
        Map <String, String> columns = new TreeMap<String, String>();
        columns.put("name", "Name");
        columns.put("description", "Desc.");
        columns.put("lastRun", "Last");
        
        
        boolean firstTime=true;
        boolean firstRow=true;
        
        StringBuilder html=new StringBuilder("");
        
		List<Jobs> jobsList = jobsRepository.findAll();
		
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
        
        log.debug(mName+"html="+html);
        return "greeting";
    }

}