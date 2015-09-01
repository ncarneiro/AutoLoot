package org.ncarneiro.testeauto.gamelogic.events;


import org.ncarneiro.testeauto.gamelogic.player.Test;

/**
 * Created by Gustavo on 8/29/2015.
 */
public class CountEvent extends Event{

    private Test test;

    public CountEvent(Test t){
        test = t;
    }

    @Override
    public void runLogic() {
        test.count(1);
    }
}
