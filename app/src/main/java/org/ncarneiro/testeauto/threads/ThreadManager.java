package org.ncarneiro.testeauto.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.ncarneiro.testeauto.gamelogic.events.Event;
import org.ncarneiro.testeauto.gamelogic.events.EventFactory;
import org.ncarneiro.testeauto.gamelogic.events.factorystates.WalkingEventFactory;
import org.ncarneiro.testeauto.gamelogic.player.Test;
import org.ncarneiro.testeauto.model.ModelFacade;
import org.ncarneiro.testeauto.utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Gustavo on 9/1/2015.
 */
public class ThreadManager implements NTPThread.OnNTPListener, Event.EventListener, Constants {


    private NTPThread ntpThread;
    private GameLoopThread gameLoopThread;
    private long lastTime;
    private Context context;

    private ArrayList<ThreadsListener> listeners;



    private static ThreadManager threadManager;
    private ThreadManager(){}



    public static ThreadManager getInstanceManager(Context context){
        if(ThreadManager.threadManager == null){
            ThreadManager.threadManager = new ThreadManager();
            ThreadManager.threadManager.listeners = new ArrayList<>();
        }


        ThreadManager.threadManager.context = context;
        return ThreadManager.threadManager;
    }


    public void stopTimeThread(){
        if(this.ntpThread != null && ntpThread.getStatus() == AsyncTask.Status.RUNNING)
            this.ntpThread.cancel(true);
    }

    public void stopLoopGameThread(){
        if(gameLoopThread != null && gameLoopThread.getStatus() == AsyncTask.Status.RUNNING)
            this.gameLoopThread.cancel();
    }

    public void verifyTime (long lastTime){
        this.lastTime = lastTime;
        this.ntpThread = new NTPThread().setOnNTPListener(this);
        this.ntpThread.execute(lastTime);
    }


    @Override
    public void onVerified(GameValidation gameValidation) {
        int count =0;
        EventFactory factory = new EventFactory();
        Test offlineObj = ModelFacade.getOfflinePlayerInstance();
        Test validObj = ModelFacade.getValidPlayerInstance();
        long acc = (System.currentTimeMillis() - lastTime)/1000;
        switch (gameValidation){
            case FIRST_OFFLINE:
                Toast.makeText(context, "Você deve entrar online na primeira vez", Toast.LENGTH_LONG);
                break;
            case INVALID_OFFLINE:
                System.out.println("penalidade");
                Toast.makeText(context, "Você foi penalizado por voltar no tempo", Toast.LENGTH_LONG);
                break;
            case VALID_OFFLINE:
                factory.setPlayerObject(offlineObj);
                factory.setState(new WalkingEventFactory(factory, offlineObj));
                while(count < acc){
                    factory.createEvent().run();
                    count++;
                }
                factory.setEventListener(this);
                gameLoopThread = new GameLoopThread();
                gameLoopThread.execute(factory);
                break;
            case VALID_ONLINE:
                validObj.count(offlineObj.getCount());
                System.out.println("validObj: " + validObj.getCount());
                offlineObj.clear();
                factory.setPlayerObject(validObj);
                factory.setState(new WalkingEventFactory(factory, validObj));
                while(count < acc){
                    factory.createEvent().run();
                    count++;
                }
                factory.setEventListener(this);
                gameLoopThread = new GameLoopThread();
                gameLoopThread.execute(factory);
                break;
            case WRONG_TIME:
                Toast.makeText(context, "O tempo está errado", Toast.LENGTH_LONG);
                break;
            default:
                System.out.println("não sei mais agora.");
                break;

        }
    }

    @Override
    public void onEventRunned(Event event) {
        Iterator<ThreadsListener> it = listeners.iterator();
        while (it.hasNext()){
            it.next().onEventRunned(event);
        }
    }


    public void addListener(ThreadsListener listener){
        this.listeners.add(listener);
    }

    public void removeListener(ThreadsListener listener){
        this.listeners.remove(listener);
    }

    public static interface ThreadsListener extends Event.EventListener{}
}
