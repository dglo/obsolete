/*
 * class: DAQdataChannel
 *
 * Version $Id: DAQdataChannelMBean.java,v 1.15 2005/09/22 22:57:37 da5id Exp $
 *
 * Date: June 21 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.system.ServiceMBeanSupport;

import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;

import icecube.daq.common.DAQControlComponent;
import icecube.daq.common.channels.ChannelLocator;
import icecube.daq.common.channels.FarEnd;
import icecube.daq.common.channels.ChannelLocatorListener;
import icecube.icebucket.monitor.simple.ScalarFlowMonitorImpl;
import icecube.icebucket.monitor.ScalarFlowMonitor;

//import sun.rmi.runtime.Log;

/**
 * This class ...does what?
 *
 * @author mcp
 * @version $Id: DAQdataChannelMBean.java,v 1.15 2005/09/22 22:57:37 da5id Exp $
 */
public class DAQdataChannelMBean extends ServiceMBeanSupport {

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    // private static member data
    public static final String DAQ_READ_CHANNEL = "read";
    public static final String DAQ_WRITE_CHANNEL = "write";
    public static final String DAQ_CHANNEL_CLOSED = "closed";
    public static final String DAQ_CHANNEL_OPENNING = "opening";
    public static final String DAQ_CHANNEL_OPEN = "open";

    // private instance member data
    // set up logging channel for this component
    private Log log = LogFactory.getLog(DAQdataChannelMBean.class);
    // string processor manager-used for removing mbeans
    private MBeanServer JMXServer;
    private String DAQcomponent;
    private int DAQcomponentID;
    // need to keep around object name of this channels jmx monitor
    // bean so that we can delete it when we close the channel
    private ObjectName DAQdataChannelMBeanName;
    // this is the ChannelLocator created by the string processor
    // that will be used to locate all data channels
    private ChannelLocator chanLocator;
    private ChannelLocatorListener chanLocatorListener;
    // far end info
    private String farEndType = "";
    private int farEndID = 0;
    // near end info
    private String nearEndType = "";
    private int nearEndID = 0;
    // content type
    private String contentType = "";
    // channel direction flag. i.e. read or write
    private String channelType = "unknown";
    // channel state description
    private String channelState = "";
    // some monitoring fields
    protected long bytesProcessed = 0;
    protected long recordsProcessed = 0;
    // channel objects used for reads and writes.
    // note that for any particular DAQdataChannelMBean
    // object, only one of these is valid when a channel
    // has been opened....see channelType.
    ReadableByteChannel readChannel = null;
    WritableByteChannel writeChannel = null;

    // monitoring objects
    private ScalarFlowMonitor byteFlowMonitor = new ScalarFlowMonitorImpl();
    private ScalarFlowMonitor recordFlowMonitor = new ScalarFlowMonitorImpl();

    // constructors

    /**
     * Create an instance of this class.
     * Default constructor is declared, but private, to stop accidental
     * creation of an instance of the class.
     */
    public DAQdataChannelMBean(MBeanServer JMXServer,
                               String DAQcomponent,
                               int DAQcomponentID,
                               ChannelLocator chanLocator,
                               ChannelLocatorListener chanLocatorListener) {
        this.JMXServer = JMXServer;
        this.DAQcomponent = DAQcomponent;
        this.DAQcomponentID = DAQcomponentID;
        this.chanLocator = chanLocator;
        this.chanLocatorListener = chanLocatorListener;
        channelState = DAQ_CHANNEL_CLOSED;
    }

    // first the mbean visible methods
    public String getFarEndType() {
        return farEndType;
    }

    public int getFarEndID() {
        return farEndID;
    }

    public String getNearEndType() {
        return nearEndType;
    }

    public int getNearEndID() {
        return nearEndID;
    }

    public String getContentType() {
        return contentType;
    }

    public String getChannelType() {
        return channelType;
    }

    public String getChannelState() {
        return channelState;
    }

    public long getBytesProcessed() {
        //return bytesProcessed;
        return byteFlowMonitor.getTotal();
    }

    public float getByteRate(){
        return byteFlowMonitor.getRate();
    }

    public long getRecordsProcessed() {
        //return recordsProcessed;
        return recordFlowMonitor.getTotal();
    }

    public float getRecordRate(){
        return recordFlowMonitor.getRate();
    }

    public synchronized void incBytesProcessed(int byteCount) {
        //bytesProcessed += new Integer(byteCount).longValue();
        byteFlowMonitor.measure(byteCount);
    }

    public synchronized void clearBytesProcessed() {
        //bytesProcessed = 0;
        byteFlowMonitor.reset();
    }

    public synchronized void incRecordsProcessed(int recordCount) {
        //recordsProcessed += new Integer(recordCount).longValue();
        recordFlowMonitor.measure(recordCount);
    }

    public synchronized void clearRecordsProcessed() {
        //recordsProcessed = 0;
        recordFlowMonitor.reset();
    }

    // these sets are not mbean visible methods; but are used internally
    public void setFarEndType(String farEndType) {
        this.farEndType = farEndType;
    }

    public void setFarEndID(int farEndID) {
        this.farEndID = farEndID;
    }

    public void setNearEndType(String nearEndType) {
        this.nearEndType = nearEndType;
    }

    public void setNearEndID(int nearEndID) {
        this.nearEndID = nearEndID;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    // this method is only needed so that a
    // ChannelLocatedListener can properly set a channels state
    public void setChannelState(String channelState) {
        this.channelState = channelState;
    }

    public SelectableChannel getSelectableChannel() {
        if (channelType.compareTo(DAQ_READ_CHANNEL) == 0) {
            return (SelectableChannel) readChannel;
        } else {
            return (SelectableChannel) writeChannel;
        }
    }


    public void setSelectableChannel(SelectableChannel c) {
        if (channelType.compareTo(DAQ_READ_CHANNEL) == 0) {
            readChannel = (ReadableByteChannel) c;
        } else {
            writeChannel = (WritableByteChannel) c;
        }
        channelState = DAQ_CHANNEL_OPEN;
    }

    // instance member method (alphabetic)
    public void register() {
        // create the proper name and same it for unregistration purposes
        try {
            if (JMXServer != null) {
                DAQdataChannelMBeanName = icecube.daq.common.DAQMonitorComponent.
                        registerAcmeMonitor(this,
                                JMXServer,
                                DAQcomponent,
                                DAQcomponentID,
                                this.getClass().getName(),
                                nearEndType + "_" + nearEndID + "_" +
                        farEndType + "_" + farEndID + "_" + contentType);
            } else {
                DAQdataChannelMBeanName = null;
            }
        } catch (Exception e) {
            log.error("DAQ Component " + DAQcomponent + " " + DAQcomponentID
                    + " exception while registering MBean"
                    + e + ".");
        }
    }

    public void clear() {
        try {
            // force a close on any open channels before unregistering
            this.close();
            if (DAQdataChannelMBeanName != null) {
                JMXServer.unregisterMBean(DAQdataChannelMBeanName);
            }
        } catch (Exception e) {
            log.error("DAQ Component " + DAQcomponent + " " + DAQcomponentID
                    + " exception while closing and/or unregistering MBean"
                    + e + ".");
        }
    }


    public boolean open() throws Exception {
        FarEnd fe = new FarEnd(farEndType, farEndID, contentType);
        // differentiate between read and write channels
        if (channelType.compareTo(DAQ_READ_CHANNEL) == 0) {
            readChannel = chanLocator.getReadableByteChannel(fe,
                    chanLocatorListener);
            if (readChannel != null) {
                channelState = DAQ_CHANNEL_OPEN;
                return true;
            } else {
                channelState = DAQ_CHANNEL_OPENNING;
                return false;
            }
        } else if (channelType.compareTo(DAQ_WRITE_CHANNEL) == 0) {
            writeChannel = chanLocator.getWritableByteChannel(fe,
                    chanLocatorListener);
            if (writeChannel != null) {
                channelState = DAQ_CHANNEL_OPEN;
                return true;
            } else {
                channelState = DAQ_CHANNEL_OPENNING;
                return false;
            }
        } else {
            throw new Exception("Invalid channelType on open. " +
                    "Neither read or write channel");
        }
    }


    public void close() throws Exception {
        FarEnd fe = new FarEnd(farEndType, farEndID, contentType);
        // differentiate between read and write channels
        if (channelType.compareTo(DAQ_READ_CHANNEL) == 0) {
            if (readChannel != null) {
                log.info("DAQ Component " + DAQcomponent + " " + DAQcomponentID
                        + " ChannelLocator Close for " + farEndType + " "
                        + farEndID + " " + contentType);
                chanLocator.closeReadableByteChannel(fe);
                readChannel = null;
            }
        } else if (channelType.compareTo(DAQ_WRITE_CHANNEL) == 0) {
            if (writeChannel != null) {
                log.info("DAQ Component " + DAQcomponent + " " + DAQcomponentID
                        + " ChannelLocator Close for " + farEndType + " "
                        + farEndID + " " + contentType);
                chanLocator.closeWritableByteChannel(fe);
                writeChannel = null;
            }
        } else {
            throw new Exception("Invalid channelType on close. " +
                    "Neither read or write channel");
        }
        // reset byte and record counters
        bytesProcessed = 0;
        recordsProcessed = 0;
        // set channelState
        channelState = DAQ_CHANNEL_CLOSED;
    }

// instance member method (alphabetic)

// static member methods (alphabetic)

// Description of this object.
// public String toString() {}

// public static void main(String args[]) {}
}
