package org.chrishood.gameobject;

/**
 * Created by Chris on 10/6/2015.
 */
public interface IGameComponent {
    String getId();
    GameObject getGameObject();
    void receiveMessage(GameObjectMessage message);
}
