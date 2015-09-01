package org.ncarneiro.testeauto.gamelogic.events;

/**
 * Created by Gustavo on 8/29/2015.
 */
public abstract class Event {

    EventListener listener;

    public void setListener(EventListener listener){
        this.listener = listener;
    }

    public final Event run() {
        runLogic();
        return this;
    }

    public final void notifyListener(){
        listener.onEventRunned(this);
    }

    protected abstract void runLogic();
    public static interface EventListener{ public void onEventRunned(Event event); }
}
