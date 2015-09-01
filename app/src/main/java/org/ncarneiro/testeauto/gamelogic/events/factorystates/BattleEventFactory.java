package org.ncarneiro.testeauto.gamelogic.events.factorystates;

import org.ncarneiro.testeauto.gamelogic.events.BattleEvent;
import org.ncarneiro.testeauto.gamelogic.events.Event;
import org.ncarneiro.testeauto.gamelogic.player.Test;

/**
 * Created by Gustavo on 9/1/2015.
 */
public class BattleEventFactory extends EventFactoryState {


    private Test t;
    private int numSocos;

    protected BattleEventFactory(Stateable stateable, Test t) {
        super(stateable, t);
        this.t = t;
        this.numSocos = 0;
    }

    @Override
    public Event createEvent() {
        super.setState(new WalkingEventFactory(super.getStateable(), t));
        return new BattleEvent(t);
    }
}
