/*
 * class: ChannelLocationEvent
 *
 * Version $Id: LocationEvent.java,v 1.1 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

import java.util.EventObject;

/**
 * This class contains all the information about a previously requested channel
 * that has now become available for use.
 *
 * @author patton
 * @version $Id: ChannelLocationEvent.java,v 1.2 2004/05/17 18:38:37 patton Exp
 *          $
 */
class LocationEvent
        extends EventObject
{
    // private instance member data

    /**
     * The detail of the far end of the channel with which the event is
     * associated.
     */
    private final FarEnd farEnd;

    // constructors

    /**
     * Create an instance of this class.
     *
     * @param source the object that located the channel.
     * @param farEnd a description of the far end of the channel.
     */
    LocationEvent(Object source,
                                   FarEnd farEnd)
    {
        super(source);
        this.farEnd = farEnd;
    }

    // instance member method (alphabetic)

    /**
     * Returns a description of the far end of the channel.
     *
     * @return a description of the far end of the channel.
     */
    public FarEnd getFarEnd()
    {
        return farEnd;
    }

    /**
     * Returns the conent of the channel, as defined by the far end.
     *
     * @return the conent of the channel, as defined by the far end.
     */
    public String getContent()
    {
        return getFarEnd().getContent();
    }

    /**
     * Returns the id of the type at the far end of the channel.
     *
     * @return the id of the type at the far end of the channel.
     */
    public int getId()
    {
        return getFarEnd().getId();
    }

    /**
     * Returns  the type at the far end of the channel.
     *
     * @return the type at the far end of the channel.
     */
    public String getType()
    {
        return getFarEnd().getType();
    }
}