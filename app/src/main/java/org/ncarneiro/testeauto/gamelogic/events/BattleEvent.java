package org.ncarneiro.testeauto.gamelogic.events;

import org.ncarneiro.testeauto.gamelogic.player.Test;

/**
 * Created by Gustavo on 9/1/2015.
 */
public class BattleEvent extends Event{


    private Test t;
    public BattleEvent(Test t){
        this.t = t;
    }

    @Override
    protected void runLogic() {
        System.out.println("porrada");
    }
}
