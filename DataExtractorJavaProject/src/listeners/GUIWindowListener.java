package listeners;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import main.WinRegistry;
import dbConnections.ConAccess;

//WindowListener only no GUI
public class GUIWindowListener implements WindowListener {
 
  public void windowClosing(WindowEvent e) {  
	  Window w = e.getWindow(); //alternative way to get the Window
    
      w.setVisible(false); //make Window not visible
      w.dispose();  //remove Window from JVM so it would be disposed
  }
  public void windowClosed(WindowEvent e) {
	  
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
		try {
			Process process = Runtime.getRuntime().exec(command);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  
	  
      System.exit(0); //exit program
  }
@Override
public void windowOpened(WindowEvent e) {
	
	
}


@Override
public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
}
}
