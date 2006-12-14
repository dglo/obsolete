/*
 * class: ConnectionEnd
 *
 * Version $Id: ConnectionEnd.java,v 1.2 2004/12/10 22:33:32 patton Exp $
 *
 * Date: September 5 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

/**
 * This class encapsulates the information about the near end of a connection.
 *
 * @author patton
 * @version $Id: ConnectionEnd.java,v 1.2 2004/12/10 22:33:32 patton Exp $
 */
class ConnectionEnd
{

    // public static final member data

    // protected static final member data

    // static final member data

    // private static final member data

    // private static member data

    // private instance member data

    /**
     * The type of the object bound to this object.
     */
    private final String type;

    /**
     * The identifier of the object bound to this object.
     */
    private final int id;

    // constructors

    /**
     * Create an instance of this class.
     *
     * @param type the type of the object to which to bind.
     * @param id the identifier of the object to which to bind.
     */
    ConnectionEnd(String type,
               int id)
    {
        if (null == type) {
            throw new NullPointerException("type can not be 'null'");
        }
        this.type = type;
        this.id = id;
    }

    /**
     * Create an instance of this class.
     *
     * @param rhs the obejct to copy into this one.
     */
    ConnectionEnd(ConnectionEnd rhs)
    {
        type = rhs.type;
        id = rhs.id;
    }

    // instance member method (alphabetic)

    public boolean equals(Object o)
    {
        if (!(o instanceof ConnectionEnd)) {
            return false;
        }

        final ConnectionEnd rhs = (ConnectionEnd) o;
        return type.equals(rhs.type) && (id == rhs.id);
    }

    /**
     * Returns the type of the object bound to this object.
     *
     * @return the type of the object bound to this object.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Returns the identifier of the object bound to this object.
     *
     * @return the identifier of the object bound to this object.
     */
    public int getId()
    {
        return id;
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    // static member methods (alphabetic)

    // Description of this object.
    public String toString()
    {
        return type + '#' + id;
    }

    // public static void main(String args[]) {}
}