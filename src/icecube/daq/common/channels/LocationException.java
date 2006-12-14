/*
 * class: NoSuchFarEndException
 *
 * Version $Id: LocationException.java,v 1.1 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

/**
 * This class is thrown when ever a ChannelLocator can not fails to loacte
 * a channel.
 *
 * @author patton
 * @version $Id: NoSuchFarEndException.java,v 1.2 2004/08/04 04:13:45 patton
 *          Exp $
 */
public class LocationException
        extends Exception
{
    /**
     * Constructs a NoSuchFarEndException with null as its error message
     * string.
     */
    public LocationException()
    {
    }

    /**
     * Constructs a NoSuchFarEndException with the specified error message.
     *
     * @param s the error message.
     */
    public LocationException(String s)
    {
        super(s);
    }

    /**
     * Constructs a NoSuchFarEndException with the specified cause.
     *
     * @param cause the scause of the exception.
     */
    public LocationException(Throwable cause)
    {
        super(cause);
    }
}