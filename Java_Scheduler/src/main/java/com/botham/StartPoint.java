package com.botham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.botham.db.resource.QuestionRepository;
import com.botham.domain.qa.Question;

import java.awt.datatransfer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;

@SpringBootApplication
public class StartPoint {
	
    //public static JdbcTemplate jdbc = new JdbcTemplate();
    
    @Autowired
    static JdbcTemplate jdbc;
    
    @Autowired 
    static QuestionRepository questionRepository;
    
    private static final Logger log = LoggerFactory.getLogger(StartPoint.class);
    
	public static void main(String[] args) throws UnsupportedFlavorException, IOException {
      // doDbStuff();
       
       //getClipBoard();
       
       String mName="main";
       for (int x=0; x<10; x++) {
    	   Random rand = new Random();

    	   int n = rand.nextInt(3);
    	   
    	   log.debug(mName+" x="+x+" n="+n);
    	   
    	   System.out.println(mName+" x="+x+" n="+n);
       }
       
       //askQuestion();
	}
	
	
	/*
	 * For question and answer,
	 * 1. Read all questions for a given category
	 * 2. Generate random from total (length or preset max) - already asked
	 * 3. Ask the Q
	 * 4. Gather and validate answers
	 * 5. Score
	 * 6. Mark as asked - loop back to 2.
	 */
	
	public static void doDbStuff() {
	       jdbc.execute("Select * from dbo.questions");
	       log.debug("done");


		}
	
	
	public static String questionStart="1.";
	public static String answerStart1="A.";
	public static String answerStart2="B.";
	public static String answerStart3="C.";
	public static String answerStart4="D.";
	public static String answerStart5="E.";
	public static String text="";

    public static void getClipBoard() throws UnsupportedFlavorException, IOException {

    	String mName="getClipBoard";

            // Create a Clipboard object using getSystemClipboard() method
            Clipboard c=Toolkit.getDefaultToolkit().getSystemClipboard();

            // Get data stored in the clipboard that is in the form of a string (text)
            System.out.println(c.getData(DataFlavor.stringFlavor));
            
            
            String clipBoardAsString = c.getData(DataFlavor.stringFlavor).toString();

            String startText="";
            boolean textStarted=false;
            for (int x=0; x<clipBoardAsString.length()-1; x++) {
              if (clipBoardAsString.substring(x, x+2).equals(questionStart)) {
            	  
            	  
            	  log.debug(mName+" Started Q");
              } else {
              if (clipBoardAsString.substring(x, x+2).equals(answerStart1)) {
            	  saveOff(text);
            	  log.debug(mName+" Started A");
              } else {
              if (clipBoardAsString.substring(x, x+2).equals(answerStart2)) {
            	  saveOff(text);
            	  log.debug(mName+" Started B");
              } else {
              if (clipBoardAsString.substring(x, x+2).equals(answerStart3)) {
            	  saveOff(text);
            	  log.debug(mName+" Started C");
              } else {
              if (clipBoardAsString.substring(x, x+2).equals(answerStart4)) {
            	  saveOff(text);
            	  log.debug(mName+" Started D");
              } else {
              if (clipBoardAsString.substring(x, x+2).equals(answerStart5)) {
            	  saveOff(text);
            	  log.debug(mName+" Started E");
              }}}}}}
              
              text=text+clipBoardAsString.substring(x, x+1);
              
              //log.debug(x+" "+clipBoardAsString.substring(x, x+2));
        
            }
            
            saveOff(text);
            
            
        }
    
    public static void saveOff(String text1) throws IOException {
    	String mName="saveOff";
    	//log.debug(mName+" text="+text1);
    	text1 = text1.substring(3, text1.length());
    	log.debug(mName+" text="+text1+"*");
   
    	

// Copy text to clipboard        
        StringSelection selection = new StringSelection(text1);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    	
// Wait for enter to be pressed        
    	InputStreamReader in=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(in);
        String a=br.readLine();
        
        
    	text="";
    }
    

    

    
}
