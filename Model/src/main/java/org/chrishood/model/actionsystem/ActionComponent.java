package org.chrishood.model.actionsystem;

import org.chrishood.model.gameobject.GameObject;
import org.chrishood.model.gameobject.components.IGameComponent;
import org.chrishood.model.gameobject.messages.GameObjectMessage;

/**
 * Created by Chris on 10/9/2015.
 */
public class ActionComponent implements IGameComponent {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public GameObject getGameObject() {
        return null;
    }

    @Override
    public void receiveMessage(String topic, GameObjectMessage message) {

    }
}
