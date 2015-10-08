package org.chrishood.gameobject;

import org.chrishood.gameobject.components.IGameComponent;
import org.chrishood.gameobject.messages.GameObjectMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Chris on 10/6/2015.
 */
public class GameObject {
    private final long id;
    private final String name;
    private final Map<String, IGameComponent> components;
    private final Map<String, Set<IGameComponent>> messageBoard;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GameObject(long id, String name) {
        this.id = id;
        this.name = name;
        components = new HashMap<>();
        messageBoard = new HashMap<>();
    }

    public void addComponent (IGameComponent component) {
        components.put(component.getId(), component);
    }

    public boolean removeComponents (String id) {
        IGameComponent component = components.remove(id);
        if(component == null) return false;
        for(Set<IGameComponent> components : messageBoard.values()) {
            if(components.contains(component)) components.remove(component);
        }
        return true;
    }

    public void broadcast(String topic, GameObjectMessage message) {
        Set<IGameComponent> callees = messageBoard.get(topic);
        if (callees == null || callees.size() == 0) return;
        for (IGameComponent callee : callees) {
            callee.receiveMessage(topic, message);
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
