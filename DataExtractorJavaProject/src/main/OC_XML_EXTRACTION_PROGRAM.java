package main;



import java.awt.Cursor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

import javax.swing.JOptionPane;

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

public class OC_XML_EXTRACTION_PROGRAM {

	
	public static void main(String[] args) throws IOException, EncodingException, EOFException, EntityException, ParseException, XPathEvalException, NavException, XPathParseException, SQLException  {
		
		
		new XML_EXTRACTOR();// instantiate the GUI - main action is when the get xml button is clicked
		
	}

}
