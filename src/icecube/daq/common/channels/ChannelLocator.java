/*
 * interface: ChannelLocator
 *
 * Version $Id: ChannelLocator.java,v 1.7 2005/04/29 17:06:22 patton Exp $
 *
 * Date: May 12 2004
 *
 * (c) 2004 IceCube Collaboration
 */

package icecube.daq.common.channels;

import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * <p/>This interface defines the methods available to clients that wish to
 * locate a communications channel to another object. A Channel is located by
 * calling one of the getChannel routines specifying the far end to which the
 * channel shouldbe connected.</p>
 *
 * <p/>The interface allows a channel to become available asynchronously, by
 * use of a {@link ChannelLocatorListener} object. This object can be passed
 * with any request to get a channel. If the channel is not immediately
 * available then the appropriate channelLocated method of the listener will be
 * called when the channel become available.</p>
 *
 * <p/>If an object no longer has use for a channel it has located, it can drop
 * that channel by calling one of the closeChannel methods. These methods also
 * remove any {@link ChannelLocatorListener} object which may be waiting for
 * that channel to become available.</p>
 *
 * <p>When a channel is closed the communication link can usually  be
 * re-established by issuing another getChannel call. The one case where this
 * will not work is when the near end is requesting a writeable channel that is
 * has closed, but the far end object had previously located the original
 * channel and not closed it. In this case the new request will appear to be a
 * request for an existing channel and so throw an exception.</p>
 *
 * <p/>The following example demonstrates how a {@link ChannelLocatorListener}
 * object can be used. It is important to realize that the
 * <code>startChannelLocating</code> and <code>finishChannelLocating</code>
 * methods are called from the same thread. </p>
 *
 * <pre>
 * public class Example
 *         implements ChannelLocatorListener
 * {
 *     // Member data containing this object's ChannelLocator.
 *     private ChannelLocator = locator;
 *
 *     // Member data specifying the object to which this object wants to
 *     // connect.
 *     private FarEnd = farEnd;
 *
 *     // Member data the channel to use to read input.
 *     ReadableByteChannel channel;
 *
 *     // Member data flag to indicate that attempt to locate the channel has
 *     // timed out.
 *     boolean timedOut;
 *
 *         ...
 *         locator = locatorFactory.createChannelLocator("StringProcessor",
 *                                                       5);
 *         farEnd = new FarEnd("domSet",
 *                             5003,
 *                             "physics");
 *         ...
 *         startChannelLocating();
 *         ...
 *         if (!finishChannelLocating()) {
 *             // action as no channel has been located.
 *         }
 *
 *     synchronized private void startChannelLocating()
 *     {
 *         timedOut = false;
 *         channel = locator.getReadableByteChannel(farEnd,
 *                                                  this);
 *     }
 *
 *     synchronized private boolean finishChannelLocating(int timeout)
 *     {
 *         if (null == channel) {
 *             try {
 *                 wait(timeout);
 *             } catch (InterruptedException e) {
 *                 // do nothing special if interrupted.
 *             }
 *             if (null == channel) {
 *                 timedOut = true;
 *                 locator.closeReadableBytechannel(farEnd);
 *             }
 *         }
 *         return null != channel;
 *     }
 *
 *     synchronized public void
 *         readableByteChannelLocated(ChannelLocationEvent event)
 *     {
 *         if (!timedOut) {
 *             channel = event.getReadableByteChannel();
 *             notify();
 *         }
 *     }
 * }
 * </pre>
 *
 * @author patton
 * @version $Id: ChannelLocator.java,v 1.7 2005/04/29 17:06:22 patton Exp $
 */
public interface ChannelLocator
{
    // instance member method (alphabetic)

    /**
     * Tells this object that the specified ReadableByteChannel is no longer in
     * use and can the discarded. If the channel is not currently available
     * then this call will stop any attempts at making it available.
     *
     * @param farEnd the description of the target object.
     * @throws NoSuchFarEndException if far end can not be found by this
     * object.
     */
    void closeReadableByteChannel(FarEnd farEnd)
            throws NoSuchFarEndException;

    /**
     * Tells this object that the specified ReadableByteChannel is no longer in
     * use and can the discarded. If the channel is not currently available
     * then this call will stop any attempts at making it available.
     *
     * @param type the type of the object with which the channel should
     * communicate.
     * @param id the id, given the type, of the object with which the channel
     * should communicate.
     * @param content the type of data that will pass over the channel.
     * @throws NoSuchFarEndException if far end can not be found by this
     * object.
     */
    void closeReadableByteChannel(String type,
                                  int id,
                                  String content)
            throws NoSuchFarEndException;

    /**
     * Tells this object that the specified WritableByteChannel is no longer in
     * use and can the discarded. If the channel is not currently available
     * then this call will stop any attempts at making it available.
     *
     * @param farEnd the description of the target object.
     * @throws NoSuchFarEndException if far end can not be found by this
     * object.
     */
    void closeWritableByteChannel(FarEnd farEnd)
            throws NoSuchFarEndException;

    /**
     * Tells this object that the specified WritableByteChannel is no longer in
     * use and can the discarded. If the channel is not currently available
     * then this call will stop any attempts at making it available.
     *
     * @param type the type of the object with which the channel should
     * communicate.
     * @param id the id, given the type, of the object with which the channel
     * should communicate.
     * @param content the type of data that will pass over the channel.
     * @throws NoSuchFarEndException if far end can not be found by this
     * object.
     */
    void closeWritableByteChannel(String type,
                                  int id,
                                  String content)
            throws NoSuchFarEndException;

    /**
     * Returns the ReadableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param farEnd the description of the target object.
     * @return a ReadableByteChannel from the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    ReadableByteChannel getReadableByteChannel(FarEnd farEnd)
            throws LocationException;

    /**
     * Returns the ReadableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param farEnd the description of the target object.
     * @param listener ChannelLocationListener to be called later if the
     * channel is not currently available.
     * @return a ReadableByteChannel from the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    ReadableByteChannel
            getReadableByteChannel(FarEnd farEnd,
                                   ChannelLocatorListener listener)
            throws LocationException;

    /**
     * Returns the ReadableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param type the type of the object with which the channel should
     * communicate.
     * @param id the id, given the type, of the object with which the channel
     * should communicate.
     * @param content the type of data that will pass over the channel.
     * @return a ReadableByteChannel from the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    ReadableByteChannel getReadableByteChannel(String type,
                                               int id,
                                               String content)
            throws LocationException;

    /**
     * Returns the ReadableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param type the type of the object with which the channel should
     * communicate.
     * @param id the id, given the type, of the object with which the channel
     * should communicate.
     * @param content the type of data that will pass over the channel.
     * @param listener ChannelLocationListener to be called later if the
     * channel is not currently available.
     * @return a ReadableByteChannel from the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    ReadableByteChannel
            getReadableByteChannel(String type,
                                   int id,
                                   String content,
                                   ChannelLocatorListener listener)
            throws LocationException;

    /**
     * Returns the WritableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param farEnd the description of the target object.
     * @return a WritableByteChannel to the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    WritableByteChannel getWritableByteChannel(FarEnd farEnd)
            throws LocationException;

    /**
     * Returns the WritableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param farEnd the description of the target object.
     * @param listener ChannelLocationListener to be called later if the
     * channel is not currently available.
     * @return a WritableByteChannel to the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    WritableByteChannel
            getWritableByteChannel(FarEnd farEnd,
                                   ChannelLocatorListener listener)
            throws LocationException;

    /**
     * Returns the WritableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param type the type of the object with which the channel should
     * communicate.
     * @param id the id, given the type, of the object with which the channel
     * should communicate.
     * @param content the type of data that will pass over the channel.
     * @return a WritableByteChannel to the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    WritableByteChannel getWritableByteChannel(String type,
                                               int id,
                                               String content)
            throws LocationException;

    /**
     * Returns the WritableByteChannel which connects to the specified object.
     * If the channel in not current available the 'null' is returned.
     *
     * @param type the type of the object with which the channel should
     * communicate.
     * @param id the id, given the type, of the object with which the channel
     * should communicate.
     * @param content the type of data that will pass over the channel.
     * @param listener ChannelLocationListener to be called later if the
     * channel is not currently available.
     * @return a WritableByteChannel to the specified target object.
     * @throws LocationException if there is a problem locating the channel.
     */
    WritableByteChannel
            getWritableByteChannel(String type,
                                   int id,
                                   String content,
                                   ChannelLocatorListener listener)
            throws LocationException;
}
