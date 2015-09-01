package org.ncarneiro.testeauto.threads;

import android.os.AsyncTask;

import org.ncarneiro.testeauto.gamelogic.events.Event;
import org.ncarneiro.testeauto.gamelogic.events.EventFactory;
import org.ncarneiro.testeauto.utils.Constants;


/**
 * Created by Gustavo on 8/29/2015.
 */
public class GameLoopThread extends AsyncTask<EventFactory, Event, Void> implements Constants {

    private boolean running;

    @Override
    protected Void doInBackground(EventFactory... factories) {

        long time = System.currentTimeMillis(), sub;
        running = true;
        EventFactory factory = factories[0];

        while (running){

            sub = System.currentTimeMillis() - time;
            if(sub > 1000){
                time += 1000;
                publishProgress(factory.createEvent().run());
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("tread Cancelada");
        return null;
    }


    @Override
    protected void onProgressUpdate(Event... events) {
        events[0].notifyListener();
    }

    public void cancel(){
        this.running = false;
    }

}
