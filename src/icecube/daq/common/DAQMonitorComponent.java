/*
 * class: DAQMonitorComponent
 *
 * Version $Id: DAQMonitorComponent.java,v 1.9 2005/11/21 17:49:55 toale Exp $
 *
 * Date: May 25 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * This class ...does what?
 *
 * @version $Id: DAQMonitorComponent.java,v 1.9 2005/11/21 17:49:55 toale Exp $
 * @author mcp
 */
public class DAQMonitorComponent  {

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    // private static member data

    // private instance member data

    // constructors

    /**
     * Create an instance of this class.
     * Default constructor is declared, but private, to stop accidental
     * creation of an instance of the class.
     */
    public DAQMonitorComponent() {
       }

    public static ObjectName createAcmeMonitorName(String componentType,
                                       int componentID,
                                       String componentClassName,
                                       String componentName)
               throws Exception {
         String classString = "";
         if(componentClassName.compareTo("") != 0){
              classString = DAQCmdInterface.DAQ_MBEAN_CLASS_NAME + "=" +
                      componentClassName + ",";
         }
           ObjectName name = new ObjectName(
                   "iceCube.daq:" +
                   DAQCmdInterface.DAQ_MBEAN_TYPE + "=" + componentType + "," +
                   DAQCmdInterface.DAQ_MBEAN_ID + "=" + componentID + "," +
                   DAQCmdInterface.DAQ_MBEAN_ACME_ASPECT + "=" +
                   DAQCmdInterface.DAQ_MBEAN_ACME_ASPECT_MONITOR + "," +
                   classString +
                   DAQCmdInterface.DAQ_MBEAN_NAME + "=" + componentName);
             return name;
     }

     public static ObjectName registerAcmeMonitor(Object mBean,
                                                  MBeanServer server,
                                                  String componentType,
                                       int componentID,
                                       String componentClassName,
                                       String componentName)
               throws Exception {
         String classString = "";
         if(componentClassName.compareTo("") != 0){
	     classString = DAQCmdInterface.DAQ_MBEAN_CLASS_NAME + "=" +
		 componentClassName + ",";
         }
	   ObjectName name = new ObjectName(
                   "iceCube.daq:" +
                   DAQCmdInterface.DAQ_MBEAN_TYPE + "=" + componentType + "," +
                   DAQCmdInterface.DAQ_MBEAN_ID + "=" + componentID + "," +
                   DAQCmdInterface.DAQ_MBEAN_ACME_ASPECT + "=" +
                   DAQCmdInterface.DAQ_MBEAN_ACME_ASPECT_MONITOR + "," +
                   classString +
                   DAQCmdInterface.DAQ_MBEAN_NAME + "=" + componentName);
         icecube.icebucket.jmx.Ice3Jmx.registerXMbean(mBean,name,server);
             return name;
       }
    // instance member method (alphabetic)
    // static member methods (alphabetic)

    // Description of this object.
    // public String toString() {}

    // public static void main(String args[]) {}
}