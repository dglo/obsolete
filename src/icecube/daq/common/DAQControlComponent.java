/*
 * class: DAQControlComponent
 *
 * Version $Id: DAQControlComponent.java,v 1.6 2005/08/17 19:22:14 mcp Exp $
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
 * @author mcp
 * @version $Id: DAQControlComponent.java,v 1.6 2005/08/17 19:22:14 mcp Exp $
 */
public class DAQControlComponent {

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
    public DAQControlComponent() {
    }

    public static ObjectName create(String componentType,
                                    String componentFcn,
                                    int componentID)
            throws Exception {
        return new ObjectName("IceCubeDAQ:" +
                DAQCmdInterface.DAQ_MBEAN_TYPE + "=" + componentType + "," +
                DAQCmdInterface.DAQ_MBEAN_ID + "=" + componentID + "," +
                DAQCmdInterface.DAQ_MBEAN_ASPECT + "=" +
                DAQCmdInterface.DAQ_MBEAN_ASPECT_CONTROL + "," +
                DAQCmdInterface.DAQ_MBEAN_FCN + "=" + componentFcn);
    }

    public static ObjectName createWithName(String componentType,
                                            String componentFcn,
                                            int componentID,
                                            String componentName)
            throws Exception {
        return new ObjectName("IceCubeDAQ:" +
                DAQCmdInterface.DAQ_MBEAN_TYPE + "=" + componentType + "," +
                DAQCmdInterface.DAQ_MBEAN_ID + "=" + componentID + "," +
                DAQCmdInterface.DAQ_MBEAN_ASPECT + "=" +
                DAQCmdInterface.DAQ_MBEAN_ASPECT_CONTROL + "," +
                DAQCmdInterface.DAQ_MBEAN_FCN + "=" + componentFcn + "," +
                DAQCmdInterface.DAQ_MBEAN_NAME + "=" + componentName);
    }

    public static ObjectName createAcmeControlObjectName(String componentType,
                                                  int componentID)
            throws Exception {
        ObjectName name = new ObjectName("iceCube.daq:" +
                DAQCmdInterface.DAQ_MBEAN_TYPE + "=" + componentType + "," +
                DAQCmdInterface.DAQ_MBEAN_ID + "=" + componentID + "," +
                DAQCmdInterface.DAQ_MBEAN_ACME_ASPECT + "=" +
                DAQCmdInterface.DAQ_MBEAN_ACME_ASPECT_CONTROL +
                ",name=" + componentType + "Commands");
        return name;
    }
    // instance member method (alphabetic)

    // static member methods (alphabetic)

    // Description of this object.
    // public String toString() {}

    // public static void main(String args[]) {}
}