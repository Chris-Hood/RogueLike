package org.chrishood.gameobject.components;

import org.chrishood.gameobject.GameObject;
import org.chrishood.gameobject.messages.GameObjectMessage;
import org.chrishood.gameobject.messages.IMessageConsumer;

/**
 * Created by Chris on 10/6/2015.
 */
public interface IGameComponent extends IMessageConsumer {
    String getId();
    GameObject getGameObject();
}
