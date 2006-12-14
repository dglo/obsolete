/*
 * class: TrafficCompletionNotification
 *
 * Version $Id: TrafficCompletionNotification.java,v 1.2 2005/04/06 01:37:16 mcp Exp $
 *
 * Date: March 14 2005
 *
 * (c) 2005 IceCube Collaboration
 */

package icecube.daq.testUtil;

/**
 * This class ...does what?
 *
 * @version $Id: TrafficCompletionNotification.java,v 1.2 2005/04/06 01:37:16 mcp Exp $
 * @author mcp
 */
public interface TrafficCompletionNotification
{

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


    // instance member method (alphabetic)
    public void trafficStopNotification(String notificationID);

    public void trafficErrorNotification(String notificationID);

    // static member methods (alphabetic)

    // Description of this object.
    // public String toString() {}

    // public static void main(String args[]) {}
}