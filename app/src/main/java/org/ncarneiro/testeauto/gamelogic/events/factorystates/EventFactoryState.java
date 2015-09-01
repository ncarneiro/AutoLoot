package org.ncarneiro.testeauto.gamelogic.events.factorystates;

import org.ncarneiro.testeauto.gamelogic.events.Event;
import org.ncarneiro.testeauto.gamelogic.player.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Gustavo on 9/1/2015.
 */
public abstract class EventFactoryState {



    private Stateable stateable;
    private Test t;

    protected EventFactoryState(Stateable stateable, Test t){
        this.stateable = stateable;
    }

    public abstract Event createEvent();

    public void setState(EventFactoryState state){
        stateable.setState(state);
    }

    protected Stateable getStateable(){
        return stateable;
    }


    public static interface Stateable { public void setState(EventFactoryState state); }
}
