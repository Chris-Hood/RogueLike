package org.chrishood.gameobject.components;

import org.chrishood.gameobject.GameObject;
import org.chrishood.gameobject.GameObjectMessage;

/**
 * Created by Chris on 10/6/2015.
 */
public interface IGameComponent {
    String getId();
    GameObject getGameObject();
    void receiveMessage(String topic, GameObjectMessage message);
}
