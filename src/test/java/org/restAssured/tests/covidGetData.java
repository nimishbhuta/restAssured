package org.restAssured.tests;

import org.mozilla.javascript.ast.Symbol;
import org.omg.Messaging.SyncScopeHelper;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class covidGetData {
 
  @Test(priority=3)
  public void gettInfectionCountAndDate() {
	  
	  
	  int[] dayWiseCount; //Defining the variable for day wise count
	  int previousDateCaseCount=0;// Define and initialize the previous date case count 
	  int nextDateCaseCount=0; //Define and initialize the previous date case count
	  int maxCaseCount=0;
	  
	  //Define the base URI 
	  
	  Response resp=given()
			   .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
			   .get("https://api.covid19api.com/dayone/country/sweden/status/confirmed")
			   			.then().contentType(ContentType.JSON)
			   			.extract().response();
	 
	  
	  
	  
	 
	  
	 
	  String allCases=resp.jsonPath().getString("Cases").replace("[","").replace("]","").trim();
	 
	  String[] sArr = allCases.split(",");
	  
	  List <String> listStatus=new ArrayList<String>();
	  listStatus=Arrays.asList(sArr);
	  
	  //Iniializing the variable for day wise count 
	  
	  dayWiseCount=new int[listStatus.size()];
	  
	  for (int i=0;i<=listStatus.size()-1;i++){
		  
		  //System.out.println("The list " + listStatus.get(i).trim());
		  if (listStatus.get(i)==null){
			  
			  nextDateCaseCount=0;
			  
		  }
				  
		  else {
			  nextDateCaseCount=Integer.valueOf(listStatus.get(i).trim());
		  }
		  dayWiseCount[i]=nextDateCaseCount-previousDateCaseCount;
		  previousDateCaseCount=nextDateCaseCount;
		  
				  
	  }
	  
	 
	  //Find the max count for the infection case 
	  
	  for(int caseCount:dayWiseCount) {
		  
		  if (caseCount > maxCaseCount){
			  
			  maxCaseCount=caseCount;
			  
		  }
		  
		   
		  
	  }
	  
	  //System.out.println("This is the max count " + maxCaseCount);
	  
	  
  }
  
  @Test(priority=1)	
  public void getHighestInfectionCountAndDate() {
	  

	  Integer[] dayWiseCount; //Defining the variable for day wise count
	  int previousDateCaseCount=0;// Define and initialize the previous date case count 
	  int nextDateCaseCount=0; //Define and initialize the previous date case count
	  int maxCaseCount=0;
	  String[] dateCaseList;
	  String[] dateCaseArr;
	  String dateInfection = null;
	  
  		 Response resp=given()
  			   .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
  			   .get("https://api.covid19api.com/dayone/country/sweden/status/confirmed")
  			   			.then().contentType(ContentType.JSON)
  			   			.extract().response();
  	 
  		
  		ArrayList<String> dateList=resp.jsonPath().get("Date");
  		ArrayList<Integer> caseList=resp.jsonPath().get("Cases");
  		
  		//System.out.println(String.valueOf(caseList.get(0)));
  		
        int totalElement=dateList.size();
        dateCaseList=new String[totalElement];       
        dayWiseCount=new Integer[totalElement];
        
        //DayWise Count
        
        for (int i=0;i<=totalElement-1;i++){
  		  
  		  //System.out.println("The list " + listStatus.get(i).trim());
  		  if (caseList.get(i)==null){
  			  
  			  nextDateCaseCount=0;
  		  }
  				  
  		  else {
  			  nextDateCaseCount=caseList.get(i);
  		  }
  		  dayWiseCount[i]=nextDateCaseCount-previousDateCaseCount;
  		  previousDateCaseCount=nextDateCaseCount;
  		  
  		if (dayWiseCount[i] > maxCaseCount){
			  
			  maxCaseCount=dayWiseCount[i];
			  
		  }
		  
  		 
  				  
  	  }
  		
      
      //Check for the date on which there was maximum infection count
        for (int i=0;i<=totalElement-1;i++){
        	
        	dateCaseList[i]=dateList.get(i)+";"+dayWiseCount[i];
        	dateCaseArr= dateCaseList[i].split(";");
        	 
        	
        	if (Integer.valueOf(dateCaseArr[1])==maxCaseCount){
  			  
        		dateInfection=dateCaseArr[0];
  			  
  		  }
        	
  	  }
        
        
     System.out.println("The highest infection count is " + maxCaseCount);
     System.out.println("The date on which the highest infection " + dateInfection);	
  		
	  
  	}


  @Test(priority=2)	
  public void getHighestDeathCountAndDate() {
	  

	  Integer[] dayWiseCount; //Defining the variable for day wise count
	  int previousDateCaseCount=0;// Define and initialize the previous date case count 
	  int nextDateCaseCount=0; //Define and initialize the previous date case count
	  int maxCaseCount=0;
	  String[] dateCaseList;
	  String[] dateCaseArr;
	  String dateDeath = null;
	  
  		 Response resp=given()
  			   .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
  			   .get("https://api.covid19api.com/dayone/country/sweden/status/deaths")
  			   			.then().contentType(ContentType.JSON)
  			   			.extract().response();
  	 
  		
  		ArrayList<String> dateList=resp.jsonPath().get("Date");
  		ArrayList<Integer> caseList=resp.jsonPath().get("Cases");
  		
  		//System.out.println(String.valueOf(caseList.get(0)));
  		
        int totalElement=dateList.size();
        dateCaseList=new String[totalElement];       
        dayWiseCount=new Integer[totalElement];
        
        //DayWise Count
        
        for (int i=0;i<=totalElement-1;i++){
  		  
  		  //System.out.println("The list " + listStatus.get(i).trim());
  		  if (caseList.get(i)==null){
  			  
  			  nextDateCaseCount=0;
  		  }
  				  
  		  else {
  			  nextDateCaseCount=caseList.get(i);
  		  }
  		  dayWiseCount[i]=nextDateCaseCount-previousDateCaseCount;
  		  previousDateCaseCount=nextDateCaseCount;
  		  
  		if (dayWiseCount[i] > maxCaseCount){
			  
			  maxCaseCount=dayWiseCount[i];
			  
		  }
		  
  		 
  				  
  	  }
  		
      
      //Check for the date on which there was maximum infection count
        for (int i=0;i<=totalElement-1;i++){
        	
        	dateCaseList[i]=dateList.get(i)+";"+dayWiseCount[i];
        	dateCaseArr= dateCaseList[i].split(";");
        	
        	
        	
        	if (Integer.valueOf(dateCaseArr[1])==maxCaseCount){
  			  
        		dateDeath=dateCaseArr[0];
  			  
  		  }
        	
  	  }
        
        
     System.out.println("The highest death count is " + maxCaseCount);
     System.out.println("The date on which the highest date " + dateDeath);	
  		
	  
  	}

}
