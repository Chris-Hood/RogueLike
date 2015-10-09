package org.chrishood.gameobject.messages;

/**
 * Created by Chris on 10/8/2015.
 */
public interface IMessageConsumer {
    void receiveMessage(String topic, GameObjectMessage message);
}
