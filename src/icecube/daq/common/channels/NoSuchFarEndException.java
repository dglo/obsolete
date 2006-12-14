/*
 * class: NoSuchFarEndException
 *
 * Version $Id: NoSuchFarEndException.java,v 1.4 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

/**
 * This class is thrown when a ChannelLocator can not locate a channel because
 * it does not know anything about the requested far end with respect to
 * ChannelLocator's end.
 *
 * @author patton
 * @version $Id: NoSuchFarEndException.java,v 1.2 2004/08/04 04:13:45 patton
 *          Exp $
 */
public final class NoSuchFarEndException
        extends LocationException
{
    /**
     * Constructs a NoSuchFarEndException with null as its error message
     * string.
     */
    public NoSuchFarEndException()
    {
    }

    /**
     * Constructs a NoSuchFarEndException with the specified error message.
     *
     * @param s the error message.
     */
    public NoSuchFarEndException(String s)
    {
        super(s);
    }
}