/*
 * class: DAQComponentInputProcess
 *
 * Version $Id: DAQComponentInputProcess.java,v 1.4 2005/04/06 01:37:42 mcp Exp $
 *
 * Date: March 24 2005
 *
 * (c) 2005 IceCube Collaboration
 */

package icecube.daq.common;

import java.util.Collection;

/**
 * This class ...does what?
 *
 * @version $Id: DAQComponentInputProcess.java,v 1.4 2005/04/06 01:37:42 mcp Exp $
 * @author mcp
 */
public interface DAQComponentInputProcess
{

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    // private static member data

    // private instance member data

    // constructors

    public void startProcessing() throws Exception;

    public void startDisposing() throws Exception;

    public void forcedStopProcessing();
    
    public void destroyProcessor() throws Exception;
    
    public boolean isRunning();
    
    public boolean isStopped();

    public boolean isDisposing();

    public void addDataChannel(DAQdataChannelMBean dataChan) throws Exception;
    
    public void addDataChannelList(Collection dataChanList) throws Exception;
    
    public void registerStopNotificationCallback(DAQComponentProcessManager app,
                                                 String notificationTag);

    public void registerErrorNotificationCallback(DAQComponentProcessManager app,
                                                 String notificationTag);

    // instance member method (alphabetic)

    // static member methods (alphabetic)

    // Description of this object.
    // public String toString() {}

    // public static void main(String args[]) {}
}
