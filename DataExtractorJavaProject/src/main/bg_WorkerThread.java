package main;



import java.awt.Cursor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.ximpleware.AutoPilot;
import com.ximpleware.EOFException;
import com.ximpleware.EncodingException;
import com.ximpleware.EntityException;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;








import dbConnections.ConAccess;

public class bg_WorkerThread extends SwingWorker<List<Integer>, Integer> {

	
	
	
	/**
	 * @return the mETAOrderofDataCount
	 */
	public int getMETAOrderofDataCount() {
		return METAOrderofDataCount;
	}


	/**
	 * @param mETAOrderofDataCount the mETAOrderofDataCount to set
	 */
	public void setMETAOrderofDataCount(int mETAOrderofDataCount) {
		METAOrderofDataCount = mETAOrderofDataCount;
	}


	/**
	 * @return the eAVdataCount
	 */
	public int getEAVdataCount() {
		return EAVdataCount;
	}


	/**
	 * @param eAVdataCount the eAVdataCount to set
	 */
	public void setEAVdataCount(int eAVdataCount) {
		EAVdataCount = eAVdataCount;
	}


	/**
	 * @return the mETAmultiSelectListCount
	 */
	public int getMETAmultiSelectListCount() {
		return METAmultiSelectListCount;
	}


	/**
	 * @param mETAmultiSelectListCount the mETAmultiSelectListCount to set
	 */
	public void setMETAmultiSelectListCount(int mETAmultiSelectListCount) {
		METAmultiSelectListCount = mETAmultiSelectListCount;
	}


	/**
	 * @return the mETAcodedefdataCount
	 */
	public int getMETAcodedefdataCount() {
		return METAcodedefdataCount;
	}


	/**
	 * @param mETAcodedefdataCount the mETAcodedefdataCount to set
	 */
	public void setMETAcodedefdataCount(int mETAcodedefdataCount) {
		METAcodedefdataCount = mETAcodedefdataCount;
	}


	/**
	 * @return the mETAcodesCount
	 */
	public int getMETAcodesCount() {
		return METAcodesCount;
	}


	/**
	 * @param mETAcodesCount the mETAcodesCount to set
	 */
	public void setMETAcodesCount(int mETAcodesCount) {
		METAcodesCount = mETAcodesCount;
	}


	/**
	 * @return the mETAStudyEventCount
	 */
	public int getMETAStudyEventCount() {
		return METAStudyEventCount;
	}


	/**
	 * @param mETAStudyEventCount the mETAStudyEventCount to set
	 */
	public void setMETAStudyEventCount(int mETAStudyEventCount) {
		METAStudyEventCount = mETAStudyEventCount;
	}


	/**
	 * @return the mETAFormCount
	 */
	public int getMETAFormCount() {
		return METAFormCount;
	}


	/**
	 * @param mETAFormCount the mETAFormCount to set
	 */
	public void setMETAFormCount(int mETAFormCount) {
		METAFormCount = mETAFormCount;
	}


	/**
	 * @return the mETAStudyEventFormCount
	 */
	public int getMETAStudyEventFormCount() {
		return METAStudyEventFormCount;
	}


	/**
	 * @param mETAStudyEventFormCount the mETAStudyEventFormCount to set
	 */
	public void setMETAStudyEventFormCount(int mETAStudyEventFormCount) {
		METAStudyEventFormCount = mETAStudyEventFormCount;
	}


	/**
	 * @return the mETAitemdefdataCount
	 */
	public int getMETAitemdefdataCount() {
		return METAitemdefdataCount;
	}


	/**
	 * @param mETAitemdefdataCount the mETAitemdefdataCount to set
	 */
	public void setMETAitemdefdataCount(int mETAitemdefdataCount) {
		METAitemdefdataCount = mETAitemdefdataCount;
	}


	/**
	 * @return the mETAitemgroupdefCount
	 */
	public int getMETAitemgroupdefCount() {
		return METAitemgroupdefCount;
	}


	/**
	 * @param mETAitemgroupdefCount the mETAitemgroupdefCount to set
	 */
	public void setMETAitemgroupdefCount(int mETAitemgroupdefCount) {
		METAitemgroupdefCount = mETAitemgroupdefCount;
	}
	
	/**
	 * @return the extractTime
	 */
	public int getExtractTime() {
		return extractTime;
	}


	/**
	 * @param extractTime the extractTime to set
	 */
	public void setExtractTime(int extractTime) {
		this.extractTime = extractTime;
	}
	public int METAOrderofDataCount=0;
	public int EAVdataCount=0;
	public int METAmultiSelectListCount=0;
	public int METAcodedefdataCount=0;
	public int METAcodesCount=0;
	public int METAStudyEventCount=0;
	public int METAFormCount=0;
	public int METAStudyEventFormCount=0;
	public int METAitemdefdataCount=0;
	public int METAitemgroupdefCount=0;
	
	public int extractTime=0;

	


	public bg_WorkerThread() {
		
	}


	@Override
	protected List<Integer> doInBackground() throws Exception {
		setDBToUse();
		
		return null;
	}
	
	
	public  void setDBToUse() throws IOException, EncodingException,
			EOFException, EntityException, ParseException, XPathEvalException,
			NavException, XPathParseException, SQLException {
		/***************Microsoft access DB***************/
		/*************************************************/
		
		if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){
			//XML_EXTRACTOR.progressBar.setIndeterminate(false);
			
			parseToAccess();//extract to ms access
			//XML_EXTRACTOR.progressBar.setIndeterminate(true);
			/*
			 * Get path to ms access application using the windows registry
			 */
			 String value = null;
			 String accessLocationPathEscaped=null;
			try{

				value = WinRegistry.readString (
			    WinRegistry.HKEY_LOCAL_MACHINE,                             //HKEY
			   "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\App Paths\\MSACCESS.EXE",           //Key
			   "Path");                                              //ValueName
			    System.out.println("Access location = " + value);
			    accessLocationPathEscaped=value.replaceAll("\\\\", "\\\\\\\\");
			    System.out.println("Access location escaped= " + accessLocationPathEscaped);
			    }catch(Exception asd){
			           System.out.println(asd);
			    }
			
			//re open access application
			String accessDBpathEscaped=ConAccess.getAccessDBPath_fileName().replaceAll("\\\\", "\\\\\\\\");
			System.out.println("Access DB path escaped= " + accessDBpathEscaped);
			String command = accessLocationPathEscaped + "Msaccess.exe \"" + ConAccess.getAccessDBPath_fileName() + "\"";
			Process process = Runtime.getRuntime().exec(command);
			  
		}	
		
		System.exit(0);
	}
	
	
	public  void parseToAccess()throws IOException, EncodingException, EOFException, EntityException, ParseException, XPathEvalException, NavException, XPathParseException, SQLException{
		
		System.out.println("Access database selected");
		
		long startTime = System.currentTimeMillis();//TIMER START
		/**********parse xml file*****(1)*****/
		VTDGen vg = new VTDGen();// 
		
		vg.setDoc(XML_EXTRACTOR.xmlByteBuffer);
		vg.parse(true);
		
		VTDNav vn = vg.getNav();//navigation object
		
		//=================================================================================================================//
		// Do the EAV table bulk insert, before opening connection, getTableRows_EAV opens own connection
		
		//AutoPilot ap1= new AutoPilot(); // auto pilot object
		//ap1.bind(vn);
		//ap1.selectXPath("//ODM/ClinicalData/SubjectData");

		//int [] num_of_levels1={1,2,1,2,2};
		//String [][] attr_names1={{"SubjectKey"},{"StudyEventOID","StudyEventRepeatKey"},{"FormOID"},{"ItemGroupOID","ItemGroupRepeatKey"},{"ItemOID","Value"}};
		//String [] levnames1={"SubjectData","StudyEventData","FormData","ItemGroupData","ItemData"};
		
		//getTableRows_EAV(vn,ap1, num_of_levels1, attr_names1, levnames1);
		//setProgress(20);
		
		//System.out.println("EAVData Count:"+EAVdataCount);
		
		//=================================================================================================================//
		ConAccess.createConnection();//open a new connection
		AutoPilot ap= new AutoPilot(); // auto pilot object
		ap.bind(vn);
		ap.selectXPath("//ODM/Study/MetaDataVersion/ItemGroupDef");

		
		int [] num_of_levels={2,2};
		String [][] attr_names={{"OID","Name"},{"OrderNumber","ItemOID"}};
		String [] levnames={"ItemGroupDef","ItemRef"};
		
		getTableRows(vn,ap, num_of_levels, attr_names, levnames,ConAccess.METAOrderofData);
		//setProgress(10);
		
		System.out.println("METAOrderofData Count:"+METAOrderofDataCount);
		//=================================================================================================================//
		
		AutoPilot ap1= new AutoPilot(); // auto pilot object
		ap1.bind(vn);
		ap1.selectXPath("//ODM/ClinicalData/SubjectData");

		int [] num_of_levels1={1,2,1,2,2};
		String [][] attr_names1={{"SubjectKey"},{"StudyEventOID","StudyEventRepeatKey"},{"FormOID"},{"ItemGroupOID","ItemGroupRepeatKey"},{"ItemOID","Value"}};
		String [] levnames1={"SubjectData","StudyEventData","FormData","ItemGroupData","ItemData"};
		
		getTableRows_EAV(vn,ap1, num_of_levels1, attr_names1, levnames1,ConAccess.EAV);
		setProgress(20);
		
		System.out.println("EAVData Count:"+EAVdataCount);
		//=================================================================================================================//
		
		AutoPilot ap2= new AutoPilot(); // auto pilot object
		ap2.bind(vn);
		ap2.declareXPathNameSpace("OpenClinica","http://www.openclinica.org/ns/odm_ext_v130/v3.1");
		ap2.selectXPath("//ODM/Study/MetaDataVersion/OpenClinica:MultiSelectList");

		int [] num_of_levels2={4,1,0,1};
		String [][] attr_names2={{"ID","Name","DataType","ActualDataType"},{"CodedOptionValue"},{},{"#text"}};
		String [] levnames2={"OpenClinica:MultiSelectList","OpenClinica:MultiSelectListItem","Decode","TranslatedText"};
		
		getTableRows(vn,ap2, num_of_levels2, attr_names2, levnames2,ConAccess.METAmultiSelectList);
		System.out.println("METAmultiSelectList Count:"+METAmultiSelectListCount);
		//setProgress(30);
		//=================================================================================================================//
		AutoPilot ap3= new AutoPilot(); // auto pilot object
		ap3.bind(vn);
		ap3.selectXPath("//ODM/Study/MetaDataVersion/CodeList");

		int [] num_of_levels3={4};
		String [][] attr_names3={{"OID","Name","Repeating","SASFormatName"}};
		String [] levnames3={"CodeList"};
		
		getTableRows(vn,ap3, num_of_levels3, attr_names3, levnames3,ConAccess.METAcodedefdata);
		System.out.println("METAcodedefdata Count:"+METAcodedefdataCount);
		//setProgress(40);
		//=================================================================================================================//
		AutoPilot ap4= new AutoPilot(); // auto pilot object
		ap4.bind(vn);
		ap4.selectXPath("//ODM/Study/MetaDataVersion/CodeList");

		int [] num_of_levels4={1,1,0,1};
		String [][] attr_names4={{"OID"},{"CodedValue"},{},{"#text"}};
		String [] levnames4={"CodeList","CodeListItem","Decode","TranslatedText"};
		
		getTableRows(vn,ap4, num_of_levels4, attr_names4, levnames4,ConAccess.METAcodes);
		System.out.println("METAcodes Count:"+METAcodesCount);
		//setProgress(50);
		//=================================================================================================================//
		
		AutoPilot ap5= new AutoPilot(); // auto pilot object
		ap5.bind(vn);
		ap5.selectXPath("//ODM/Study/MetaDataVersion/StudyEventDef");

		int [] num_of_levels5={4};
		String [][] attr_names5={{"OID","Name","Repeating","Type"}};
		String [] levnames5={"StudyEventDef"};
		
		getTableRows(vn,ap5, num_of_levels5, attr_names5, levnames5 ,ConAccess.METAStudyEvent);
		System.out.println("METAStudyEvent Count:"+METAStudyEventCount);
		//setProgress(60);
		//=================================================================================================================//
		
		AutoPilot ap6= new AutoPilot(); // auto pilot object
		ap6.bind(vn);
		ap6.selectXPath("//ODM/Study/MetaDataVersion/FormDef");

		int [] num_of_levels6={3};
		String [][] attr_names6={{"OID","Name","Repeating"}};
		String [] levnames6={"FormDef"};
		
		getTableRows(vn,ap6, num_of_levels6, attr_names6, levnames6 ,ConAccess.METAForm);
		System.out.println("METAForm Count:"+METAFormCount);
		//setProgress(70);
		//=================================================================================================================//
		AutoPilot ap7= new AutoPilot(); // auto pilot object
		ap7.bind(vn);
		ap7.selectXPath("//ODM/Study/MetaDataVersion/StudyEventDef");

		int [] num_of_levels7={1,2};
		String [][] attr_names7={{"OID"},{"FormOID","Mandatory"}};
		String [] levnames7={"StudyEventDef","FormRef"};
		
		getTableRows(vn,ap7, num_of_levels7, attr_names7, levnames7 ,ConAccess.METAStudyEventForm);
		
		System.out.println("METAStudyEventForm Count:"+METAStudyEventFormCount);
		//setProgress(80);
		
		//=================================================================================================================//
		AutoPilot ap8= new AutoPilot(); // auto pilot object
		ap8.bind(vn);
		ap8.declareXPathNameSpace("OpenClinica","http://www.openclinica.org/ns/odm_ext_v130/v3.1");
		ap8.selectXPath("//ODM/Study/MetaDataVersion/ItemDef");

		int [] num_of_levels8={7,4,1};
		String [][] attr_names8={{"OID","DataType","Length","Name","SASFieldName","Comment","OpenClinica:FormOIDs"},{"","CodeListOID","MultiSelectListID","MeasurementUnitOID"},{"#text"}};
		String [][] levnames8={{"ItemDef"},{"Question","CodeListRef","OpenClinica:MultiSelectListRef","MeasurementUnitRef"},{"TranslatedText"}};
		
		getTableRows_V2(vn,ap8, num_of_levels8, attr_names8, levnames8 ,ConAccess.METAitemdefdata);
		System.out.println("METAitemdefdata Count:"+METAitemdefdataCount);
		//setProgress(90);
		//=================================================================================================================//
		
		AutoPilot ap9= new AutoPilot(); // auto pilot object
		ap9.bind(vn);
		ap9.selectXPath("//ODM/Study/MetaDataVersion/ItemGroupDef");

		int [] num_of_levels9={5};
		String [][] attr_names9={{"OID","Name","Repeating","SASDatasetName","Comment"}};
		String [] levnames9={"ItemGroupDef"};
		
		getTableRows(vn,ap9, num_of_levels9, attr_names9, levnames9 ,ConAccess.METAitemgroupdef);
		System.out.println("METAitemgroupdef Count:"+METAitemgroupdefCount);
		System.out.println("Total inserts: "+ConAccess.counter2);
		
		
		//=================================================================================================================//
		setProgress(100);
		//=================================================================================================================//
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	   
	    System.out.println("Total Elapsed Time (s): " + (elapsedTime)/1000);
	    
	    JOptionPane.showMessageDialog(null, "XML data extracted to :"+ConAccess.getAccessDBPath_fileName() +"\n" + "Total Elapsed Time: " + elapsedTime/1000 + "(Seconds)","Extraction Complete!!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public  void printRow(int tableId,Map<String, String> rowMap){

		
		if (tableId==ConAccess.METAOrderofData){
			METAOrderofDataCount+=1;
		}
		else if (tableId==ConAccess.EAV){
			EAVdataCount+=1;
		}
		else if (tableId==ConAccess.METAmultiSelectList){
			METAmultiSelectListCount+=1;
		}
		else if (tableId==ConAccess.METAcodedefdata){
			METAcodedefdataCount+=1;
		}
		else if (tableId==ConAccess.METAcodes){
			METAcodesCount+=1;
		}
		else if (tableId==ConAccess.METAStudyEvent){
			METAStudyEventCount+=1;
		}
		else if (tableId==ConAccess.METAForm){
			METAFormCount+=1;
		}
		else if (tableId==ConAccess.METAStudyEventForm){
			METAStudyEventFormCount+=1;
		}
		else if (tableId==ConAccess.METAitemdefdata){
			METAitemdefdataCount+=1;
		}
		else if (tableId==ConAccess.METAitemgroupdef){
			METAitemgroupdefCount+=1;
		}
	}
	
	public  String escapeNonAscii(String str) {

		  StringBuilder retStr = new StringBuilder();
		  for(int i=0; i<str.length(); i++) {
		    int cp = Character.codePointAt(str, i);
		    int charCount = Character.charCount(cp);
		    if (charCount > 1) {
		      i += charCount - 1; // 2.
		      if (i >= str.length()) {
		        throw new IllegalArgumentException("truncated unexpectedly");
		      }
		    }

		    if (cp < 128) {
		      retStr.appendCodePoint(cp);
		    } else {
		      retStr.append(String.format("\\u%x", cp));
		    }
		  }
		  return retStr.toString();
		}
	
	public   void getTableRows(VTDNav vn,AutoPilot ap,int [] num_of_levels,String [][] attr_names,String [] levnames,int tableNames) throws XPathEvalException, NavException, SQLException, IOException{
		
//		
//		//check if StudyEvent
//		if (tableNames == ConAccess.METAStudyEvent){
//			System.out.println("METAStudyEvent");
//			int studyEventCount=0;
//			while((ap.evalXPath())!=-1){
//				studyEventCount++;
//			}
//			System.out.println("METAStudyEvent Count: "+ studyEventCount);
//			
//		}
			
		
		Map<String, String> uniqueRowsToInsert = new HashMap<String, String>();
		
		while((ap.evalXPath())!=-1){//loop through all the top level
			String uniqueKey="";
			String uniqueKey1="";
			Map<String, String> rowMap=new HashMap<String, String>();
			
			if(num_of_levels.length>=0){//first top level node(0)
					for (int j = 0; j < num_of_levels[0]; j++) {
						if(vn.hasAttr(attr_names[0][j])){//check whether an attribute exists
							int attributeID = vn.getAttrVal(attr_names[0][j]); //get attribute index
							
							if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
								uniqueKey1+=vn.toString(attributeID) + "|";
							}
						
							rowMap.put(attr_names[0][j], vn.toString(attributeID));
					        
						}
						else if(attr_names[0][j].equalsIgnoreCase("#text")){
							int attributeID = vn.getText();
							
							if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
								uniqueKey1+=vn.toString(attributeID) + "|";
							}
							rowMap.put(attr_names[0][j], vn.toString(attributeID));
						}
						else {
							if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
								uniqueKey1+="no xml attribute" + "|";
							}
							rowMap.put(attr_names[0][j], "no xml attribute");
						}
					}
					if(num_of_levels.length==1){
						printRow(tableNames,rowMap);
						if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){
							
							if (tableNames == ConAccess.METAStudyEvent || tableNames == ConAccess.METAcodedefdata || tableNames == ConAccess.METAForm || tableNames == ConAccess.METAitemgroupdef){// check if table row requires uniqueness
								boolean exists=uniqueRowsToInsert.containsKey(uniqueKey1);//if true don't insert record
								uniqueRowsToInsert.put(uniqueKey1,uniqueKey1);
								if(!exists){//insert record only if it's false
									ConAccess.populateDbTable(tableNames,rowMap );
								}
							}else{
								ConAccess.populateDbTable(tableNames,rowMap );
							}
							
						}
						
						
					}
					
				
				}
				
				
						
					if(num_of_levels.length>=2){//node(1)
						uniqueKey=uniqueKey1; // IF DOESN'T GO INTO LOOP DEFINE HERE
						vn.toElement(VTDNav.FIRST_CHILD,levnames[1]);
						do{
							uniqueKey=uniqueKey1;
							for (int j = 0; j < num_of_levels[1]; j++) {

								if(vn.hasAttr(attr_names[1][j])){//check whether an attribute exists
									int attributeID = vn.getAttrVal(attr_names[1][j]); //get attribute index
									if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										uniqueKey+=vn.toString(attributeID) + "|";
									}
									rowMap.put(attr_names[1][j], vn.toString(attributeID));
								}
								else if(attr_names[1][j].equalsIgnoreCase("#text")){
									int attributeID = vn.getText();
									if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										uniqueKey+=vn.toString(attributeID) + "|";
									}
									rowMap.put(attr_names[1][j], vn.toString(attributeID));
								}else {
									if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										uniqueKey+="no xml attribute" + "|";
									}
									rowMap.put(attr_names[1][j], "no xml attribute");
								}
							}
						
							if(num_of_levels.length==2){
								printRow(tableNames,rowMap);
								if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){
									
									if (tableNames == ConAccess.METAStudyEventForm || tableNames == ConAccess.METAOrderofData){// check if table row requires uniqueness
										boolean exists=uniqueRowsToInsert.containsKey(uniqueKey);//if true don't insert record
										uniqueRowsToInsert.put(uniqueKey,uniqueKey);
										if(!exists){//insert record only if it's false
											ConAccess.populateDbTable(tableNames,rowMap );
										}
									}else{
										ConAccess.populateDbTable(tableNames,rowMap );
									}
								}

								
							}
						
						if(num_of_levels.length>=3){//node(2)
							uniqueKey=uniqueKey1; // IF DOESN'T GO INTO LOOP DEFINE HERE
							vn.toElement(VTDNav.FIRST_CHILD,levnames[2]);
							do{
								uniqueKey=uniqueKey1;
								for (int j = 0; j < num_of_levels[2]; j++) {
									if(vn.hasAttr(attr_names[2][j])){//check whether an attribute exists
										int attributeID = vn.getAttrVal(attr_names[2][j]); //get attribute index
										if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
											uniqueKey+=vn.toString(attributeID) + "|";
										}
										rowMap.put(attr_names[2][j], vn.toString(attributeID));
									}
									else if(attr_names[2][j].equalsIgnoreCase("#text")){
										int attributeID = vn.getText();
										if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
											uniqueKey+=vn.toString(attributeID) + "|";
										}
										rowMap.put(attr_names[2][j], vn.toString(attributeID));
									}else {
										if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
											uniqueKey+="no xml attribute" + "|";
										}
										rowMap.put(attr_names[2][j], "no xml attribute");
									}
								}
								
								if(num_of_levels.length==3){

								}
								
								if(num_of_levels.length>=4){//node(3)
									uniqueKey=uniqueKey1; // IF DOESN'T GO INTO LOOP DEFINE HERE
									vn.toElement(VTDNav.FIRST_CHILD,levnames[3]);
									do{
										uniqueKey=uniqueKey1;
										for (int j = 0; j < num_of_levels[3]; j++) {
											if(vn.hasAttr(attr_names[3][j])){//check whether an attribute exists
												int attributeID = vn.getAttrVal(attr_names[3][j]); //get attribute index
												if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
													uniqueKey+=vn.toString(attributeID) + "|";
												}
												rowMap.put(attr_names[3][j], vn.toString(attributeID));
										            
											}
											else if(attr_names[3][j].equalsIgnoreCase("#text")){
												int attributeID = vn.getText();
												if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
													uniqueKey+=vn.toString(attributeID) + "|";
												}
												rowMap.put(attr_names[3][j], vn.toString(attributeID));
											}else {
												if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
													uniqueKey+="no xml attribute" + "|";
												}
												rowMap.put(attr_names[3][j], "no xml attribute");
											}
										}
										
										if(num_of_levels.length==4){
											printRow(tableNames,rowMap);
											if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){
												if (tableNames == ConAccess.METAmultiSelectList || tableNames == ConAccess.METAcodes){// check if table row requires uniqueness
													boolean exists=uniqueRowsToInsert.containsKey(uniqueKey);//if true don't insert record
													uniqueRowsToInsert.put(uniqueKey,uniqueKey);
													if(!exists){//insert record only if it's false
														ConAccess.populateDbTable(tableNames,rowMap );
													}
												}else{
													ConAccess.populateDbTable(tableNames,rowMap );
												}
											}
											
										}
										
										if(num_of_levels.length>=5){//node(4)
											vn.toElement(VTDNav.FIRST_CHILD,levnames[4]);
											do{												
												for (int j = 0; j < num_of_levels[4]; j++) {
													if(vn.hasAttr(attr_names[4][j])){//check whether an attribute exists
														int attributeID = vn.getAttrVal(attr_names[4][j]); //get attribute index
														rowMap.put(attr_names[4][j], vn.toString(attributeID));
												      
												        
													}
													else if(attr_names[4][j].equalsIgnoreCase("#text")){
														int attributeID = vn.getText();
														rowMap.put(attr_names[4][j], vn.toString(attributeID));
													}else rowMap.put(attr_names[4][j], "no xml attribute");
												}
												
												if(num_of_levels.length==5){
													printRow(tableNames,rowMap);
													if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){
														ConAccess.populateDbTable(tableNames,rowMap );
													}
													
												}
												
												
												
											}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[4]));
											
											vn.toElement(VTDNav.PARENT)	;	
										}
										
										
									}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[3]));
									
									vn.toElement(VTDNav.PARENT)	;	
								}
								
							}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[2]));
							
							vn.toElement(VTDNav.PARENT)	;	
						}
					}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[1]));
					
					vn.toElement(VTDNav.PARENT)	;	
				}
					
					
			
		}
		
	
		
		
	}
	
	public   void getTableRows_V2(VTDNav vn,AutoPilot ap,int [] num_of_levels,String [][] attr_names,String [][] levnames,int tableNames) throws XPathEvalException, NavException, SQLException, IOException{
		int i=0;
		Map<String, String> uniqueRowsToInsert = new HashMap<String, String>();
		while((ap.evalXPath())!=-1){//loop through all the top level
			String uniqueKey="";
			String uniqueKey1="";
			Map<String, String> rowMap=new HashMap<String, String>();
			if(num_of_levels.length>=0){//first top level node(0)
					
					for (int j = 0; j < num_of_levels[0]; j++) {
						if(vn.hasAttr(attr_names[0][j])){//check whether an attribute exists
							int attributeID = vn.getAttrVal(attr_names[0][j]); //get attribute index
							//System.out.print(vn.toString(attributeID)+" ");
							if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
								uniqueKey1+=vn.toString(attributeID) + "|";
							}
							rowMap.put(attr_names[0][j], vn.toString(attributeID));
					        
						}
						else if(attr_names[0][j].equalsIgnoreCase("#text")){
							int attributeID = vn.getText();
							if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
								uniqueKey1+=vn.toString(attributeID) + "|";
							}
							rowMap.put(attr_names[0][j], vn.toString(attributeID));
						}
						else {
							if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
								uniqueKey1+="no xml attribute" + "|";
							}
							rowMap.put(attr_names[0][j], "no xml attribute");
						}
					}
					
				}
				
				
						
					//if(vn.matchElement("Question") || vn.matchElement("CodeListRef") || vn.matchElement("OpenClinica:MultiSelectListRef") || vn.matchElement("MeasurementUnitRef") ){//node(1)
			            uniqueKey=uniqueKey1; //HAS TO BE HERE TOO AS MAY NOT HAVE A FIRST CHILD
						if(vn.toElement(VTDNav.FIRST_CHILD)){//move to first child of Element ItemRef and visit all child nodes
						do{
							uniqueKey=uniqueKey1;
								if(vn.matchElement("Question")){// 
										vn.toElement(VTDNav.FIRST_CHILD,levnames[2][0]);
										
										
										 if(attr_names[2][0].equalsIgnoreCase("#text")){
											int attributeID = vn.getText();
											if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
												uniqueKey+=vn.toString(attributeID) + "|";
											}
											rowMap.put(attr_names[2][0], vn.toString(attributeID));
										 }
									
										 vn.toElement(VTDNav.PARENT)	;	
								}
								else if(vn.matchElement("CodeListRef")){
									int attributeID =  vn.getAttrVal(attr_names[1][1]); //get attribute index;
									if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										uniqueKey+=vn.toString(attributeID) + "|";
									}
									rowMap.put(attr_names[1][1], vn.toString(attributeID));
								}
								else if(vn.matchElement("OpenClinica:MultiSelectListRef")){
									int attributeID = vn.getAttrVal(attr_names[1][2]); //get attribute index;
									if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										uniqueKey+=vn.toString(attributeID) + "|";
									}
									rowMap.put(attr_names[1][2], vn.toString(attributeID));
								}
								else if(vn.matchElement("MeasurementUnitRef")){
									int attributeID = vn.getAttrVal(attr_names[1][3]); //get attribute index;
									if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										uniqueKey+=vn.toString(attributeID) + "|";
									}
									rowMap.put(attr_names[1][3], vn.toString(attributeID));
								}
							
								
							
						
						
					}while(vn.toElement(VTDNav.NEXT_SIBLING));
					
					vn.toElement(VTDNav.PARENT)	;	
						}
						
					if(num_of_levels.length==3){
						printRow(tableNames,rowMap);
						if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){														
							if (tableNames == ConAccess.METAitemdefdata){// check if table row requires uniqueness
								boolean exists=uniqueRowsToInsert.containsKey(uniqueKey);//if true don't insert record
								uniqueRowsToInsert.put(uniqueKey,uniqueKey);
								if(!exists){//insert record only if it's false
									ConAccess.populateDbTable(tableNames,rowMap );
								}
							}else{
								ConAccess.populateDbTable(tableNames,rowMap );
							}
						}
						
					}	
					
			i++;
			
		}
		
	System.out.println(i);
			
	}

	
	public   void getTableRows_EAV(VTDNav vn,AutoPilot ap,int [] num_of_levels,String [][] attr_names,String [] levnames,int tablename) throws XPathEvalException, NavException, SQLException, IOException{
		
//		
//		//check if StudyEvent
//		if (tableNames == ConAccess.METAStudyEvent){
//			System.out.println("METAStudyEvent");
//			int studyEventCount=0;
//			while((ap.evalXPath())!=-1){
//				studyEventCount++;
//			}
//			System.out.println("METAStudyEvent Count: "+ studyEventCount);
//			
//		}
			
		List<Object[]> ListToInsert = new ArrayList<Object[]>();		
		// int i = -1;
		// String[][] X = new String[4000][8];
		int i=0;
		while((ap.evalXPath())!=-1){//loop through all the top level
			//String uniqueKey="";
			//String uniqueKey1="";
			HashMap<String, String> rowMap=new HashMap<String, String>();
			
			if(num_of_levels.length>=0){//first top level node(0)
					for (int j = 0; j < num_of_levels[0]; j++) {
						if(vn.hasAttr(attr_names[0][j])){//check whether an attribute exists
							int attributeID = vn.getAttrVal(attr_names[0][j]); //get attribute index
							
							//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
							//	uniqueKey1+=vn.toString(attributeID) + "|";
							//}
						
							rowMap.put(attr_names[0][j], vn.toString(attributeID));
					        
						}
						else if(attr_names[0][j].equalsIgnoreCase("#text")){
							int attributeID = vn.getText();
							
							//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
							//	uniqueKey1+=vn.toString(attributeID) + "|";
							//}
							rowMap.put(attr_names[0][j], vn.toString(attributeID));
						}
						else {
							//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
							//	uniqueKey1+="no xml attribute" + "|";
							//}
							rowMap.put(attr_names[0][j], "no xml attribute");
						}
					}				
				}
				
				
						
					if(num_of_levels.length>=2){//node(1)
						//uniqueKey=uniqueKey1; // IF DOESN'T GO INTO LOOP DEFINE HERE
						vn.toElement(VTDNav.FIRST_CHILD,levnames[1]);
						do{
							//uniqueKey=uniqueKey1;
							for (int j = 0; j < num_of_levels[1]; j++) {

								if(vn.hasAttr(attr_names[1][j])){//check whether an attribute exists
									int attributeID = vn.getAttrVal(attr_names[1][j]); //get attribute index
									//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
									//	uniqueKey+=vn.toString(attributeID) + "|";
									//}
									rowMap.put(attr_names[1][j], vn.toString(attributeID));
								}
								else if(attr_names[1][j].equalsIgnoreCase("#text")){
									int attributeID = vn.getText();
									//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
									//	uniqueKey+=vn.toString(attributeID) + "|";
									//}
									rowMap.put(attr_names[1][j], vn.toString(attributeID));
								}else {
									//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
									//	uniqueKey+="no xml attribute" + "|";
									//}
									rowMap.put(attr_names[1][j], "no xml attribute");
								}
							}	
												
						if(num_of_levels.length>=3){//node(2)
							//uniqueKey=uniqueKey1; // IF DOESN'T GO INTO LOOP DEFINE HERE
							vn.toElement(VTDNav.FIRST_CHILD,levnames[2]);
							do{
								//uniqueKey=uniqueKey1;
								for (int j = 0; j < num_of_levels[2]; j++) {
									if(vn.hasAttr(attr_names[2][j])){//check whether an attribute exists
										int attributeID = vn.getAttrVal(attr_names[2][j]); //get attribute index
										//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										//	uniqueKey+=vn.toString(attributeID) + "|";
										//}
										rowMap.put(attr_names[2][j], vn.toString(attributeID));
									}
									else if(attr_names[2][j].equalsIgnoreCase("#text")){
										int attributeID = vn.getText();
										//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										//	uniqueKey+=vn.toString(attributeID) + "|";
										//}
										rowMap.put(attr_names[2][j], vn.toString(attributeID));
									}else {
										//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
										//	uniqueKey+="no xml attribute" + "|";
										//}
										rowMap.put(attr_names[2][j], "no xml attribute");
									}
								}
								
								
								if(num_of_levels.length>=4){//node(3)
									//uniqueKey=uniqueKey1; // IF DOESN'T GO INTO LOOP DEFINE HERE
									vn.toElement(VTDNav.FIRST_CHILD,levnames[3]);
									do{
										//uniqueKey=uniqueKey1;
										for (int j = 0; j < num_of_levels[3]; j++) {
											if(vn.hasAttr(attr_names[3][j])){//check whether an attribute exists
												int attributeID = vn.getAttrVal(attr_names[3][j]); //get attribute index
												//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
												//	uniqueKey+=vn.toString(attributeID) + "|";
												//}
												rowMap.put(attr_names[3][j], vn.toString(attributeID));
										            
											}
											else if(attr_names[3][j].equalsIgnoreCase("#text")){
												int attributeID = vn.getText();
												//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
												//	uniqueKey+=vn.toString(attributeID) + "|";
												//}
												rowMap.put(attr_names[3][j], vn.toString(attributeID));
											}else {
												//if (tableNames != ConAccess.EAV){// create a unique key for the uniqueRowsToInsert
												//	uniqueKey+="no xml attribute" + "|";
												//}
												rowMap.put(attr_names[3][j], "no xml attribute");
											}
										}
																	
										if(num_of_levels.length>=5){//node(4)
											vn.toElement(VTDNav.FIRST_CHILD,levnames[4]);
											do{												
												for (int j = 0; j < num_of_levels[4]; j++) {
													if(vn.hasAttr(attr_names[4][j])){//check whether an attribute exists
														int attributeID = vn.getAttrVal(attr_names[4][j]); //get attribute index
														rowMap.put(attr_names[4][j], vn.toString(attributeID));
												      
												        
													}
													else if(attr_names[4][j].equalsIgnoreCase("#text")){
														int attributeID = vn.getText();
														rowMap.put(attr_names[4][j], vn.toString(attributeID));
													}else rowMap.put(attr_names[4][j], "no xml attribute");
												}
												
												if(num_of_levels.length==5){
													printRow(tablename,rowMap);
													if (XML_EXTRACTOR.getDbType()==XML_EXTRACTOR.ACCESS_DB){
														ListToInsert.add(new Object[] { rowMap.get("SubjectKey"),rowMap.get("StudyEventOID"), rowMap.get("StudyEventRepeatKey"), rowMap.get("FormOID"), rowMap.get("ItemGroupOID"),rowMap.get("ItemGroupRepeatKey"),rowMap.get("ItemOID"),rowMap.get("Value")});							
														//uniqueRowsToInsert.put(rowMap.toString(),rowMap.toString());
														//ConAccess.populateDbTable(tableNames,rowMap );
													}
													
												}
												
												
												
											}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[4]));
											
											vn.toElement(VTDNav.PARENT)	;	
										}
										
										
									}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[3]));
									
									vn.toElement(VTDNav.PARENT)	;	
								}
								
							}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[2]));
							
							vn.toElement(VTDNav.PARENT)	;	
						}
					}while(vn.toElement(VTDNav.NEXT_SIBLING,levnames[1]));
					
					vn.toElement(VTDNav.PARENT)	;	
				}
					
				i++;	
			
		}
		System.out.println(i);
	// write out the data
		//Database db;
		//Table TEAVdata;
		//String filename ="F:\\submit_data_extractor\\DataExtractorJavaProject\\build\\classes\\DataExtractor.accdb";	
        //DatabaseBuilder dbb=new DatabaseBuilder(new File(filename));
	    //dbb.setAutoSync(false);
	    //db=dbb.open();
	    //TEAVdata = db.getTable("EAVdata");

	    //TEAVdata.addRow(ListToInsert.get("SubjectKey"),(String) tableData.get("StudyEventOID"),(String) tableData.get("StudyEventRepeatKey"), (String) tableData.get("FormOID"),(String) tableData.get("ItemGroupOID") , (String) tableData.get("ItemGroupRepeatKey"),(String) tableData.get("ItemOID"),(String) tableData.get("Value"));
	    //TEAVdata.addRows(ListToInsert);
	    //db.close();
		ConAccess.populateDbEAVTable(tablename, ListToInsert);
	}


	
	

}


