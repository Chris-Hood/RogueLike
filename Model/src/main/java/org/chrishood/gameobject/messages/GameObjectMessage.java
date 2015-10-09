package org.chrishood.gameobject.messages;

import org.chrishood.gameobject.GameObject;
import org.chrishood.gameobject.components.IGameComponent;

/**
 * Created by Chris on 10/6/2015.
 */
public class GameObjectMessage<T> {
    private T contents;
    private GameObject source;

    public T getContents() {
        return contents;
    }

    public Class getContentType() {
        return contents.getClass();
    }

    public GameObject getSource() {
        return source;
    }

    public GameObjectMessage(T contents, GameObject source) {
        this.contents = contents;
        this.source = source;
    }
}
