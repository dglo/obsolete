/*
 * class: ChannelLocationEvent
 *
 * Version $Id: ChannelLocationEvent.java,v 1.6 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

import java.nio.channels.Channel;

/**
 * This class contains all the information about a previously requested channel
 * that has now become available for use.
 *
 * @author patton
 * @version $Id: ChannelLocationEvent.java,v 1.2 2004/05/17 18:38:37 patton Exp
 *          $
 */
public class ChannelLocationEvent
        extends LocationEvent
{
    // private instance member data

    /**
     * The channel associated with this event.
     */
    private Channel channel;

    // constructors

    /**
     * Create an instance of this class.
     *
     * @param source the object that located the channel.
     * @param farEnd a description of the far end of the channel.
     */
    protected ChannelLocationEvent(Object source,
                                   FarEnd farEnd)
    {
        this(source,
             farEnd,
             null);
    }

    /**
     * Create an instance of this class.
     *
     * @param source the object that located the channel.
     * @param farEnd a description of the far end of the channel.
     * @param channel the channel associated with this event.
     */
    protected ChannelLocationEvent(Object source,
                                   FarEnd farEnd,
                                   Channel channel)
    {
        super(source,
              farEnd);
        this.channel = channel;
    }

    // instance member method (alphabetic)

    /**
     * Returns the channel that has been located.
     *
     * @return the channel that has been located.
     */
    public Channel getChannel()
    {
        return channel;
    }

    void setChannel(Channel channel)
    {
        this.channel = channel;
    }
}