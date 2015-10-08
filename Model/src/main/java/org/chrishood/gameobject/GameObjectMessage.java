package org.chrishood.gameobject;

import org.chrishood.gameobject.components.IGameComponent;

/**
 * Created by Chris on 10/6/2015.
 */
public class GameObjectMessage<T> {
    private T contents;
    private IGameComponent source;

    public T getContents() {
        return contents;
    }

    public Class getContentType() {
        return contents.getClass();
    }

    public IGameComponent getSource() {
        return source;
    }

    public GameObjectMessage(T contents, IGameComponent source) {
        this.contents = contents;
        this.source = source;
    }
}
