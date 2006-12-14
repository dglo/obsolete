/*
 * interface: ChannelLocatorListener
 *
 * Version $Id: ChannelLocatorListener.java,v 1.4 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

import java.util.EventListener;

/**
 * This interface defined the callback that a ChannelLocator makes when a
 * previously requested channel becomes available.
 *
 * @author patton
 * @version $Id: ChannelLocatorListener.java,v 1.2 2004/05/17 18:38:50 patton
 *          Exp $
 */
public interface ChannelLocatorListener
        extends EventListener
{
    // instance member method (alphabetic)

    /**
     * Used to handle a newly available ReadableByteChannel.
     *
     * @param event a description of the newly available channel.
     */
    void readableByteChannelLocated(ChannelLocationEvent event);

    /**
     * Used to handle a failure when locating a ReadableByteChannel.
     *
     * @param event a description of the failure.
     */
    void readableByteChannelFailure(LocationFailureEvent event);

    /**
     * Used to handle a newly available WritableByteChannel.
     *
     * @param event a description of the newly available channel.
     */
    void writableByteChannelLocated(ChannelLocationEvent event);

    /**
     * Used to handle a failure when locating a WritableByteChannel.
     *
     * @param event a description of failure.
     */
    void writableByteChannelFailure(LocationFailureEvent event);
}
