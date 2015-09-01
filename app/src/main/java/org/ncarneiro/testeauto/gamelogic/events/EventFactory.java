package org.ncarneiro.testeauto.gamelogic.events;


import org.ncarneiro.testeauto.gamelogic.events.factorystates.EventFactoryState;
import org.ncarneiro.testeauto.gamelogic.player.Test;

/**
 * Created by Gustavo on 8/29/2015.
 */
public class EventFactory implements EventFactoryState.Stateable {

    private EventFactoryState state;

    private Test t;
    private Event.EventListener listener;

    public EventFactory(){

    }

    public EventFactory(Test t, Event.EventListener listener){
        this.listener = listener;
        this.t = t;
    }

    public EventFactory(Test t){
        this.t = t;
    }

    public EventFactory(Event.EventListener listener){
        this.listener = listener;
    }


    public Event createEvent(){
        Event e = state.createEvent();
        e.setListener(listener);
        return e;
    }

    public void setPlayerObject(Test t){
        this.t = t;
    }

    public void setEventListener(Event.EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void setState(EventFactoryState state){
        this.state = state;
    }
}
