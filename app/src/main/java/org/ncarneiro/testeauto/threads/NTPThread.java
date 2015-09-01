package org.ncarneiro.testeauto.threads;

import android.os.AsyncTask;

import org.ncarneiro.testeauto.utils.Constants;
import org.ncarneiro.testeauto.utils.SntpClient;


/**
 * Created by Gustavo on 8/29/2015.
 */
public class NTPThread extends AsyncTask<Long, Void, Constants.GameValidation> implements Constants {

    OnNTPListener listener;

    @Override
    protected GameValidation doInBackground(Long... lastTime) {
        System.out.println("inicicou o ntpThread");

        //Tentar validar o relógio
        SntpClient sntpClient = new SntpClient();
        if (sntpClient.requestTime("1.pool.ntp.org", 3000)) {


            long ntp = sntpClient.getNtpTime();
            long loc = System.currentTimeMillis();
            long dif = Math.abs(loc - ntp);
            System.out.println("ntp Time: "+sntpClient.getNtpTime());
            System.out.println("loc Time: "+ System.currentTimeMillis());
            System.out.println("dif: "+ dif);


            if(dif > 60000){
                System.out.println("relógio errado");
                return GameValidation.WRONG_TIME;
            }

            System.out.println("online e válido");
            return GameValidation.VALID_ONLINE;
        }else{

            System.out.println("offline mode");

            if(lastTime[0] == 0){
                System.out.println("primeira vez offline");
                return GameValidation.FIRST_OFFLINE;
            }else if(lastTime[0] > System.currentTimeMillis()){
                System.out.println("voltou no tempo");
                return GameValidation.INVALID_OFFLINE;
            }else {
                System.out.println("offline normal");
                return GameValidation.VALID_OFFLINE;
            }
        }
    }

    @Override
    protected void onPostExecute(GameValidation gameValidation) {
        super.onPostExecute(gameValidation);
        this.listener.onVerified(gameValidation);
    }


    public NTPThread setOnNTPListener(OnNTPListener listener){
        this.listener = listener;
        return this;
    }


    public static interface OnNTPListener{
        public void onVerified(GameValidation gameValidation);
    }
}
