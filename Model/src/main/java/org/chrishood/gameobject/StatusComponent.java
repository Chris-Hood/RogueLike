package org.chrishood.gameobject;

/**
 * Created by Chris on 10/6/2015.
 */
public class StatusComponent implements IGameComponent {
    private GameObject owner;

    @Override
    public String getId() {
        return null;
    }

    @Override
    public GameObject getGameObject() {
        return owner;
    }

    @Override
    public void receiveMessage(GameObjectMessage message) {

    }

    public void test() {
        owner.broadcast("thing", new GameObjectMessage<String>("stuff", this));
    }
}
