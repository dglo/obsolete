/*
 * class: FarEnd
 *
 * Version $Id: FarEnd.java,v 1.8 2004/12/10 22:33:30 patton Exp $
 *
 * Date: May 13 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;


/**
 * This class encapsulated the details of the far end of a channel which is
 * being located by the {@link ChannelLocator} class.
 *
 * @author patton
 * @version $Id: FarEnd.java,v 1.8 2004/12/10 22:33:30 patton Exp $
 */
public final class FarEnd
        extends ConnectionEnd
{
    // private instance member data

    /**
     * The content of the channel connecting this object.
     */
    private final String content;

    // constructors

    /**
     * Create an instance of this class.
     *
     * @param connectionEnd a description of type at the end of the
     * connection.
     * @param content the content of the channel connecting this object.
     */
    FarEnd(ConnectionEnd connectionEnd,
           String content)
    {
        super(connectionEnd);
        if (null == content) {
            throw new NullPointerException("content can not be 'null'");
        }
        this.content = content;
    }

    /**
     * Create an instance of this class.
     *
     * @param type the type of the object at the far end of the channel.
     * @param id the identifier of the object at the far end of the channel.
     * @param content the content of the channel connecting this object.
     */
    public FarEnd(String type,
                  int id,
                  String content)
    {
        super(type,
              id);
        if (null == content) {
            throw new NullPointerException("content can not be 'null'");
        }
        this.content = content;
    }

    // instance member method (alphabetic)

    public boolean equals(Object o)
    {
        if (!super.equals(o)) {
            return false;
        }

        if (!(o instanceof FarEnd)) {
            return false;
        }

        final FarEnd rhs = (FarEnd) o;
        return content.equals(rhs.content);
    }

    /**
     * Returns an instance of the ConnectionEnd class that this object used.
     *
     * @return a ConnectionEnd object matching the description of this object.
     */
    ConnectionEnd getChannelEnd()
    {
        return new ConnectionEnd(this);
    }

    /**
     * Returns the content to be exchanged with the far end.
     *
     * @return the content to be exchanged with the far end.
     */
    public String getContent()
    {
        return content;
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    // Description of this object.
    public String toString()
    {
        return super.toString() + '-' + content;
    }
}