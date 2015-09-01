package org.ncarneiro.testeauto.model;

import android.app.Activity;
import android.content.Context;

import org.ncarneiro.testeauto.gamelogic.player.Test;
import org.ncarneiro.testeauto.utils.Constants;

/**
 * Created by Gustavo on 9/1/2015.
 */
public class ModelFacade implements Constants {


    private static Test offlineObj;
    private static Test validObj;

    private ModelFacade(){}


    public static void initDatabase(Activity context){
        offlineObj = new Test();
        offlineObj.count(context.getPreferences(Context.MODE_PRIVATE).getInt(StorageKeys.OFFLINE_OBJ, 0));

        validObj = new Test();
        validObj.count(context.getPreferences(Context.MODE_PRIVATE).getInt(Constants.StorageKeys.VALID_OBJ, 0));
    }

    public static void commitDatabase(Activity context){
        context.getPreferences(Context.MODE_PRIVATE).edit().putInt(StorageKeys.OFFLINE_OBJ, offlineObj.getCount()).commit();
        context.getPreferences(Context.MODE_PRIVATE).edit().putInt(StorageKeys.VALID_OBJ, validObj.getCount()).commit();
        context.getPreferences(Context.MODE_PRIVATE).edit().putLong(StorageKeys.LAST_TIME, System.currentTimeMillis()).commit();
    }

    public static Test getOfflinePlayerInstance(){
        return offlineObj;
    }

    public static Test getValidPlayerInstance(){
        return validObj;
    }
}
