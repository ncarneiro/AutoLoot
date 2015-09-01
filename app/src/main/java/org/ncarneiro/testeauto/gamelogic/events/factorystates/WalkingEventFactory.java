package org.ncarneiro.testeauto.gamelogic.events.factorystates;

import org.ncarneiro.testeauto.gamelogic.events.CountEvent;
import org.ncarneiro.testeauto.gamelogic.events.Event;
import org.ncarneiro.testeauto.gamelogic.events.EventFactory;
import org.ncarneiro.testeauto.gamelogic.player.Test;

/**
 * Created by Gustavo on 9/1/2015.
 */
public class WalkingEventFactory extends EventFactoryState {


    private Test t;
    private int steps;

    public WalkingEventFactory(Stateable stateable, Test t) {
        super(stateable, t);
        this.t = t;
        steps = 0;
    }

    @Override
    public Event createEvent() {
        if(steps++ > 10){
            steps = 0;
            super.setState(new BattleEventFactory(super.getStateable(), t));
        }
        return new CountEvent(t);
    }
}
