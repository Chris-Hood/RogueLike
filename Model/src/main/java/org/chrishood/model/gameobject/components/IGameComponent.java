package org.chrishood.model.gameobject.components;

import org.chrishood.model.gameobject.GameObject;
import org.chrishood.model.gameobject.messages.IMessageConsumer;

/**
 * Created by Chris on 10/6/2015.
 */
public interface IGameComponent extends IMessageConsumer {
    String getId();
    GameObject getGameObject();
}
