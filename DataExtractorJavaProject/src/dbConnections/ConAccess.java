package dbConnections;



import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;

import main.XML_EXTRACTOR;


public class ConAccess{
	

	/*************************************************************/
	/*Class properties or members or attributes
	 * 
	 **************************************************************/
	private String myDoc=System.getProperty("user.home");
	private JFileChooser chooser = new JFileChooser(myDoc);
	private static Database db;
	static Table TMETAOrderofData,TEAVdata,TMETAmultiSelectList,TMETAcodedefdata,TMETAcodes,TMETAStudyEvent,TMETAForm,TMETAStudyEventForm,TMETAitemdefdata,TMETAitemgroupdef; 
	private static String accessDBFileName;//xml filename
	private static String accessDBPath_fileName;//xml filename with path
	
	public static Connection conn;//connection object
	public static Statement st;//statement object
	static PreparedStatement pst,pst1,pst2,pst3,pst4,pst5,pst6,pst7,pst8,pst9;//statement object
		public static int counter1=0,counter2=0;
	
	public final static int METAOrderofData=1,EAV=2,METAmultiSelectList=3,METAcodedefdata=4,METAcodes=5,METAStudyEvent=6,METAForm=7,METAStudyEventForm=8,METAitemdefdata=9,METAitemgroupdef=10;// TABLE NAMES 
	

	
	public static  void createConnection(){
		
		String filename = getAccessDBPath_fileName();
		try {
			
			DatabaseBuilder dbb=new DatabaseBuilder(new File(filename));
			dbb.setAutoSync(false);
			db=dbb.open();
			TMETAOrderofData = db.getTable("METAOrderofData");
			TEAVdata = db.getTable("EAVdata");
			TMETAmultiSelectList = db.getTable("METAmultiSelectList");
			TMETAcodedefdata= db.getTable("METAcodedefdata");
			TMETAcodes = db.getTable("METAcodes");
			TMETAStudyEvent = db.getTable("METAStudyEvent");
			TMETAForm = db.getTable("METAForm");
			TMETAStudyEventForm = db.getTable("METAStudyEventForm");
			TMETAitemdefdata = db.getTable("METAitemdefdata");
			TMETAitemgroupdef = db.getTable("METAitemgroupdef");
	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	public  static void populateDbEAVTable(int tableName, List<Object[]> ListData) throws SQLException, IOException{
		TEAVdata.addRows(ListData);
	}
	

	
	
	@SuppressWarnings("deprecation")
	public  static void populateDbTable(int tableName, Map<String, String> tableData) throws SQLException, IOException{
		
		switch(tableName){
		
		case 1://METAOrderofData
			
			TMETAOrderofData.addRow(++counter1,(String) tableData.get("OID"),(String) tableData.get("Name"),(int)Integer.valueOf((String)tableData.get("OrderNumber")),(String) tableData.get("ItemOID"));
			break;
		case 2://EAVdata
			
			TEAVdata.addRow((String) tableData.get("SubjectKey"),(String) tableData.get("StudyEventOID"),(String) tableData.get("StudyEventRepeatKey"), (String) tableData.get("FormOID"),(String) tableData.get("ItemGroupOID") , (String) tableData.get("ItemGroupRepeatKey"),(String) tableData.get("ItemOID"),(String) tableData.get("Value"));
			break;
			
		case 3://METAmultiSelectList
			
			TMETAmultiSelectList.addRow((String) tableData.get("ID"),(String) tableData.get("Name"),(String) tableData.get("DataType"),(String) tableData.get("ActualDataType"),(String) tableData.get("CodedOptionValue"),(String) tableData.get("#text"));
			break;
		case 4://METAcodedefdata
			
			TMETAcodedefdata.addRow((String) tableData.get("OID"),(String) tableData.get("Name"),(String) tableData.get("Repeating"),(String) tableData.get("SASFormatName"));
			break;
		case 5://METAcodes
			
			TMETAcodes.addRow( escapeNonAscii((String) tableData.get("OID") ),escapeNonAscii((String) tableData.get("CodedValue")),escapeNonAscii((String) tableData.get("#text")));
			break;
		case 6://METAStudyEvent
			
			TMETAStudyEvent.addRow((String) tableData.get("OID") ,(String) tableData.get("Name"),(String) tableData.get("Repeating"),(String) tableData.get("Type"));
			break;
		case 7://METAForm
			
			TMETAForm.addRow((String) tableData.get("OID"),(String) tableData.get("Name"),(String) tableData.get("Repeating"));
			break;
		case 8://METAStudyEventForm
			
			TMETAStudyEventForm.addRow((String) tableData.get("OID"),(String) tableData.get("FormOID"),(String) tableData.get("Mandatory"));
			break;
		case 9://METAitemdefdata
			String OID;
			if(tableData.get("OID")!=null){
				OID=(String) tableData.get("OID") ;
			}
			else{
				OID="";
			}
			
			String DataType;
			if(tableData.get("DataType")!=null){
				DataType=(String) tableData.get("DataType") ;
			}
			else{
				DataType="";
			}
			int Length;			
			if (!tableData.get("DataType").equalsIgnoreCase("date") && !tableData.get("DataType").equalsIgnoreCase("partialDate") && !tableData.get("DataType").equals("no xml attribute") ) {
				Length=(int)Integer.valueOf((String) tableData.get("Length") );
			}
			else{
				Length =0;
			}
			String Name;
			if(tableData.get("Name")!=null){
				Name=(String) tableData.get("Name");
			}
			else{
				Name="";
			}
			String SASFieldName;
			if(tableData.get("SASFieldName")!=null){
				SASFieldName=(String) tableData.get("SASFieldName") ;
			}
			else{
				SASFieldName="";
			}
				
			String Comment;
			if(tableData.get("Comment")!=null){	
				if(tableData.get("Comment").length() < 256 ){
					Comment=(String) tableData.get("Comment") ;
					
				}else{
					Comment=(String) tableData.get("Comment").substring( 0, 255 ) ;
				}
				
			}
			else{
				Comment ="";
			}
			
			String OpenClinica_FormOIDs;
			if(tableData.get("OpenClinica:FormOIDs")!=null){	
				OpenClinica_FormOIDs=(String) tableData.get("OpenClinica:FormOIDs");
			}
			else{
				OpenClinica_FormOIDs="";
			}
			String _text;
			if(tableData.get("#text")!=null){
				String strippedValue1=(String) tableData.get("#text").replaceAll("\\p{Cntrl}", "");// remove all control characters
				String strippedValue2=strippedValue1.trim();// remove all leading and trailing spaces
				_text=strippedValue2;//question column
				
			}
			else{
				_text="";
			}
			String CodeListOID;
			if(tableData.get("CodeListOID")!=null){
				CodeListOID= (String) tableData.get("CodeListOID");
			}
			else{
				CodeListOID="";
			}
			String MultiSelectListID;
			if(tableData.get("MultiSelectListID")!=null){
				MultiSelectListID=(String) tableData.get("MultiSelectListID") ;
			}
			else{
				MultiSelectListID="";
			}
			String MeasurementUnitOID;
			if(tableData.get("MeasurementUnitOID")!=null){
				MeasurementUnitOID=(String) tableData.get("MeasurementUnitOID");
			}
			else{
				MeasurementUnitOID="";
				
			}
			
			
	       TMETAitemdefdata.addRow(OID,DataType,Length,Name,SASFieldName,Comment,OpenClinica_FormOIDs,_text,CodeListOID,MultiSelectListID,MeasurementUnitOID);
			
			break;
		case 10://METAitemgroupdef
			
			TMETAitemgroupdef.addRow((String) tableData.get("OID"),(String) tableData.get("Name"),(String) tableData.get("Repeating"),(String) tableData.get("SASDatasetName"),(String) tableData.get("Comment"));
			++counter2;
			break;
		}
		
		
		
	}
	
	/*************************************************************/
	/* Getta and setter methods or functions
	 * 
	 **************************************************************/
	public static String escapeNonAscii(String str) {

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
	
	
	public static  String getAccessDBFileName() {
		return accessDBFileName;
	}

	public static void setAccessDBFileName(String accessDBPath_fileName) {
		accessDBFileName = accessDBPath_fileName;
	}

	public static String getAccessDBPath_fileName() {
		return accessDBPath_fileName;
	}

	public static void setAccessDBPath_fileName(String accessDBPath_fileName) {
		ConAccess.accessDBPath_fileName = accessDBPath_fileName;
	}
	
	
}
