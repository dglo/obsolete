/*
 * class: ChannelLocationEvent
 *
 * Version $Id: LocationFailureEvent.java,v 1.1 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;



/**
 * This class contains information about why a location request failed.
 *
 * @author patton
 * @version $Id: LocationFailureEvent.java,v 1.1 2005/04/29 17:06:22 patton Exp $
 */
public class LocationFailureEvent
        extends LocationEvent
{
    // private instance member data

    /**
     * The exception that caused the failure.
     */
    private LocationException exception;

    // constructors

    /**
     * Create an instance of this class.
     *
     * @param source the object that located the channel.
     * @param farEnd a description of the far end of the channel.
     */
    protected LocationFailureEvent(Object source,
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
     * @param exception the exception that caused the failure.
     */
    protected LocationFailureEvent(Object source,
                                   FarEnd farEnd,
                                   LocationException exception)
    {
        super(source,
              farEnd);
        this.exception = exception;
    }

    // instance member method (alphabetic)

    /**
     * Returns the exception that caused the failure.
     *
     * @return the exception that caused the failure.
     */
    public LocationException getException()
    {
        return exception;
    }
}