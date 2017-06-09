package main;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import com.ximpleware.EOFException;
import com.ximpleware.EncodingException;
import com.ximpleware.EntityException;
import com.ximpleware.NavException;
import com.ximpleware.ParseException;
import com.ximpleware.XPathEvalException;
import com.ximpleware.XPathParseException;

import dbConnections.ConAccess;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Hashtable;

import listeners.GUIWindowListener;

public class XML_EXTRACTOR extends JFrame  implements ActionListener{
	private static String myDoc=System.getProperty("user.home"+"/My Documents");
	private static JFileChooser chooser = new JFileChooser(myDoc);
	private static JFileChooser chooser1 = new JFileChooser(myDoc);
	
	/*************************************************************/
	/*Class properties or members or attributes
	 * 
	 **************************************************************/
	
	private static String xmlFileName;//xml filename
	private static String xmlPath_fileName;//xml filename with path
	
	private static int dbType;
	final static int  ACCESS_DB=1 ;//access database type
	final static int  SQLITE_DB=2 ;//sqlite database type
	final static int  SQLSERVER_DB=3 ;//sqlite database type
	
	private static File xmlFile;//xml file object
	public static byte[] xmlByteBuffer;
	
	
	/*************************************************************/
	/*Class properties or members or attributes for the GUI
	 * 
	 **************************************************************/
	 public static Container c ;// returns a container object
	 public static JPanel summaryTablePanel= new JPanel();
	 public static JButton getXMLButton;// button to import xml file to the application
	 public static JTable summaryTable;// table to highlight import progress
	 public static JProgressBar progressBar;
	 
	public XML_EXTRACTOR() throws IOException, SQLException{// empty constructor
		/*********************Setup GUI*****************************/
		
		//WindowListener of this window
        GUIWindowListener WinListener = new GUIWindowListener();
        addWindowListener(WinListener);
        
    	// Get toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get size
        Dimension dimension = toolkit.getScreenSize();
        int height=(int) (dimension.height-(dimension.height*0.3));
        int width=(int) (dimension.width-(dimension.width*0.4));
		setupGUI( width,height,"DATA EXTRACTOR FOR OPENCLINICA CDISC XML EXTRACT OPTION",true);//setup GUI
        
		this.setLocationRelativeTo(null);
		
		//set the default database type
		setDbType(ACCESS_DB);// set Access as the desired database where the data will be sent
		chooseDb();
	}
	
	/**
	 * @throws IOException ***********************************************************/
	/*Method or function to select and set the xml file name and path
	 * creates a file object 
	 **************************************************************/
	public static boolean getXMLFILE() throws IOException{
		
		
		
		
		// Get toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get size
        Dimension dimension = toolkit.getScreenSize();
        int height=(int) (dimension.height-(dimension.height*0.3));
        int width=(int) (dimension.width-(dimension.width*0.4));
		
		
		
		/***File Chooser****/
		
		//appearance
		
		chooser.setSize((int) (width-width*0.5), (int) (height-height*0.6));
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
                "xml files (*.xml)", "xml");
            chooser.setFileFilter(xmlfilter);
		chooser.setDialogTitle("Open an OC XML File to be extracted");
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal1 = chooser.showOpenDialog(XML_EXTRACTOR.c); // open a file
		
		boolean fileSelected=false; //flag to indicate that a file was successfully selected by the user
		
		if(returnVal1 == JFileChooser.APPROVE_OPTION) { //user selected a file
			
			
			setXmlFileName(chooser.getSelectedFile().getName()); //set the filename
			setXmlPath_fileName(chooser.getSelectedFile().getAbsolutePath()); //set filename with full path
			
			
			System.out.println("Selected filename: "+getXmlFileName());
			System.out.println("Selected filename: "+getXmlFileName());
			System.out.println("Selected filename with path: "+getXmlPath_fileName());
			
			
			setXmlFile(getXmlPath_fileName()); //create file object
			
			/**Populate xml byte buffer**/
			FileInputStream fis = new FileInputStream(getXmlFile()); //create a file input stream
			xmlByteBuffer = new byte[(int) getXmlFile().length()];// instantiate byte buffer array
			fis.read(xmlByteBuffer);// populate the byte buffer with the input stream
			
			
			fileSelected=true; // set the flag to true
			
			
		} else if(returnVal1 == JFileChooser.CANCEL_OPTION) { //user hit cancel
			System.out.println("No File Selected");
			fileSelected=false; // No file selected, then set flag to false.
		}
		
		return fileSelected; //return the flag indicating that the XML file data is stored in the byte buffer
	}
	
	/*************************************************************/
	/* Method or function to Setup the desired database
	 * 
	 **************************************************************/

	public static boolean chooseDb(){
		boolean dbSelected=false;
		if(getDbType()==XML_EXTRACTOR.ACCESS_DB){
				//get class path
				String path = XML_EXTRACTOR.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				String decodedPath=null;
				try {
					//needed to prevent errors with special characters and spaces
					decodedPath = URLDecoder.decode(path, "UTF-8");
					
				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(
			                  c, "An error occurred whilst decoding class path", 
			                  "Unsupported Encoding Exception",
			                  JOptionPane.ERROR_MESSAGE );
					System.exit(0); //exit program
				}
				
				if(decodedPath != null){
					File exeFolder=new File(decodedPath);
					File msaccessFileObject=new File(exeFolder.getAbsolutePath() + "\\DataExtractor.accdb");
					if (msaccessFileObject.exists()){
						ConAccess.setAccessDBFileName("DataExtractor.accdb"); //set the filename
						ConAccess.setAccessDBPath_fileName(exeFolder.getAbsolutePath() + "\\DataExtractor.accdb"); //set filename with full path
						System.out.println("Path to executable folder: "+exeFolder.getAbsolutePath());
						dbSelected=true;
					}
					else{
						JOptionPane.showMessageDialog(
				                  c, "\"DataExtractor.accdb\" not found under:\n" + exeFolder.getAbsolutePath(), 
				                  "Database not found",
				                  JOptionPane.ERROR_MESSAGE );
						}
					
				}
				
				
		
		}
		return dbSelected;
	}

	/*************************************************************/
	/* Getta and setter methods or functions
	 * 
	 **************************************************************/
	private static String getXmlFileName() {
		return xmlFileName;
	}

	private static void setXmlFileName(String xmlFileName) {
		XML_EXTRACTOR.xmlFileName = xmlFileName;
	}

	public static String getXmlPath_fileName() {// changed from private to public
		return xmlPath_fileName;
	}

	private static void setXmlPath_fileName(String xmlPath_fileName) {
		XML_EXTRACTOR.xmlPath_fileName = xmlPath_fileName;
	}

	public static  int getDbType() {
		return dbType;
	}

	private static  void setDbType(int dbType) {
		XML_EXTRACTOR.dbType = dbType;
	}

	private static File getXmlFile() {
		return xmlFile;
	}

	private static void setXmlFile(String xmlFile) {
		XML_EXTRACTOR.xmlFile = new File(xmlFile);
	}
	
	public static int getDBSize(){
		return (int) (XML_EXTRACTOR.xmlFile.length()/1024);
	}

	/************************************************************************************************************/
	//
	//											GUI- SETUP AND HELPER METHODS
	//
	/************************************************************************************************************/
	
	public void setupGUI(int width,int height,String title,boolean visible)
    {

        
        /*
    	 * 1. Setup content panel and summaryTablePanel layouts
    	 */
        c = getContentPane();
        c.setLayout(new BorderLayout());
        
        summaryTablePanel.setLayout(new BorderLayout());
        
        c.add(summaryTablePanel,BorderLayout.CENTER);
        
         /*
     	 * 2. Add a button to the container 
     	 */
    	getXMLButton=new JButton("Click to import XML file");
    	getXMLButton.addActionListener(this);
    	getXMLButton.setActionCommand("getXMLFile");
    	
    	c.add(getXMLButton,BorderLayout.PAGE_START);
         
         
        
       
        
        
       
        
        
    	ControlFrame(width,height,title,visible);//controls the appearance of the window
       
    }
	
	/*
     * Method to control the window
     */
    public void ControlFrame(int width,int height,String title,boolean visible){
    	 setSize(width,height);
    	 setResizable(true);
         setTitle(title);
         setVisible(visible);
    	
    }
    
  //close this window
    public void Close(){
        this.setVisible(false);
        this.dispose();
        
    }
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("getXMLFile")) {
			
			
			
			
		      //choose xml file from any location
			
				try {
					
					
					boolean fs=getXMLFILE();// get xml file and populate the byte buffer; returns true if xml data is saved in the byte buffer
					if (!fs){
						JOptionPane.showMessageDialog(
				                  this, "Failed to import XML File. Please try again", 
				                  "Import failed",
				                  JOptionPane.ERROR_MESSAGE );
						}
					else{
						//setDbType(ACCESS_DB);// set Access as the desired database where the data will be sent

							getXMLButton.enable(false);
							
							 /*
					     	 *  Add a progressbar to the container 
					     	 */ 
					         
					        progressBar = new JProgressBar();
					       
					        progressBar.setStringPainted(false);
					        progressBar.setIndeterminate(true); 
					        
					        summaryTablePanel.add(progressBar,BorderLayout.SOUTH);
					        
					        
					       
							
							/*
					     	 *  Add a table to display import summary 
					     	 */  
					         
					        summaryTable= new JTable(new MyTableModel());
					        summaryTablePanel.add(summaryTable.getTableHeader(),BorderLayout.PAGE_START);
					        summaryTablePanel.add(summaryTable,BorderLayout.CENTER);
					        
					        summaryTablePanel.revalidate();
							
							
							final bg_WorkerThread thread=new bg_WorkerThread(); // The parsing of the XML file is done in a background thread to prevent it from blocking the GUI
							
							
							/*Update the record counts for the Meta tables**/
							thread.addPropertyChangeListener(
								     new PropertyChangeListener() {
								         public  void propertyChange(PropertyChangeEvent evt) {
								        	 
								        	 if ("progress".equals(evt.getPropertyName())) {
								               
								                 
								                 if((Integer)evt.getNewValue()== 100){// execution has completed
									                 summaryTable.setValueAt(thread.getMETAOrderofDataCount(), 1, 2);
									                 summaryTable.setValueAt(true, 1, 1);
									              
									                 summaryTable.setValueAt(thread.getEAVdataCount(), 0, 2);
									                 summaryTable.setValueAt(true, 0, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAcodesCount(), 2, 2);
									                 summaryTable.setValueAt(true, 2, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAFormCount(), 3, 2);
									                 summaryTable.setValueAt(true, 3, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAitemdefdataCount(), 4, 2);
									                 summaryTable.setValueAt(true, 4, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAitemgroupdefCount(), 5, 2);
									                 summaryTable.setValueAt(true, 5, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAmultiSelectListCount(), 6, 2);
									                 summaryTable.setValueAt(true, 6, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAOrderofDataCount(), 7, 2);
									                 summaryTable.setValueAt(true, 7, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAStudyEventCount(), 8, 2);
									                 summaryTable.setValueAt(true, 8, 1);
									                 
									                 summaryTable.setValueAt(thread.getMETAStudyEventFormCount(),9 , 2);
									                 summaryTable.setValueAt(true, 9, 1);
									                 
									                 summaryTablePanel.revalidate();
									                 progressBar.setIndeterminate(false); 
								                 }
								                 
								             }
								        	 
								         }

										
								     });

							
							thread.execute();// Execute the thread which calls doInBackground() 
							
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(
			                  this, "Failed to import XML File. Please try again", 
			                  "Import failed",
			                  JOptionPane.ERROR_MESSAGE );
				}
		          
		}

	}
	
	/**Table model class***/
	class MyTableModel extends AbstractTableModel {

		private String[] columnNames = {"Table name","XML data processed","Number of records"}; // Array of table columns

		private Object[][] data = {// Array of rows
		    {"EAVdata",new Boolean(false), new Integer(0)},
		    {"METAcodedefdata",new Boolean(false), new Integer(0)},
		    {"METAcodes",new Boolean(false), new Integer(0)},
		    {"METAForm",new Boolean(false), new Integer(0)},
		    {"METAitemdefdata",new Boolean(false), new Integer(0)},
		    {"METAitemgroupdef",new Boolean(false), new Integer(0)},
		    {"METAmultiSelectList",new Boolean(false), new Integer(0)},
		    {"METAOrderofData", new Boolean(false),new Integer(0)},
		    {"METAStudyEvent",new Boolean(false), new Integer(0)},
		    {"METAStudyEventForm",new Boolean(false), new Integer(0)}
		};
		
		
		public Class getColumnClass(int c) {
	        return getValueAt(0, c).getClass();
	    }
		
		public int getColumnCount() {
            return columnNames.length;
        }
 
        public int getRowCount() {
            return data.length;
        }
 
        public String getColumnName(int col) {
            return columnNames[col];
        }
 
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
		
        public boolean isCellEditable(int row, int col) {
           return false;
        }
		
        public void setValueAt(Object value, int row, int col) { // updates the value in the specofied column
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
	
	}
	
	
}
