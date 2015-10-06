package org.chrishood.gameobject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chris on 10/6/2015.
 */
public class GameObject {
    private final long id;
    private final Map<String, IGameComponent> components;
    private final Map<String, Set<IGameComponent>> messageBoard;

    public GameObject(long id) {
        this.id = id;
        components = new HashMap<>();
        messageBoard = new HashMap<>();
    }

    public void addComponent (IGameComponent component) {
        components.put(component.getId(), component);
    }

    public boolean removeComponents (String id) {
        return components.remove(id) != null;
    }

    public void broadcast(String topic, GameObjectMessage message) {
        Set<IGameComponent> callees = messageBoard.get(topic);
        if (callees == null || callees.size() == 0) return;
        for (IGameComponent callee : callees) {
            callee.receiveMessage(message);
        }
    }

    public void subscribe(String topic, IGameComponent listener) {
        Set<IGameComponent> listeners = messageBoard.get(topic);
        if (listeners == null) {
            listeners = new HashSet<>();
        }
        listeners.add(listener);
        messageBoard.put(topic, listeners);
    }

    public void unsubscribe(String topic, IGameComponent listener) {
        Set<IGameComponent> listeners = messageBoard.get(topic);
        if (listeners == null) return;
        if (listeners.contains(listener)) listeners.remove(listener);
    }
}
