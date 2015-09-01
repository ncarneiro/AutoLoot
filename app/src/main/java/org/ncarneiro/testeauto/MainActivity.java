package org.ncarneiro.testeauto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ncarneiro.testeauto.combat.*;
import org.ncarneiro.testeauto.combat.Character;
import org.ncarneiro.testeauto.gamelogic.events.BattleEvent;
import org.ncarneiro.testeauto.gamelogic.events.Event;
import org.ncarneiro.testeauto.model.ModelFacade;
import org.ncarneiro.testeauto.threads.ThreadManager;
import org.ncarneiro.testeauto.utils.Constants;

public class MainActivity extends AppCompatActivity implements Constants, ThreadManager.ThreadsListener {


    private TextView txtValid;
    private TextView txtInvalid;

    private ThreadManager threadManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtValid = (TextView) findViewById(R.id.txt_valid);
        this.txtInvalid = (TextView) findViewById(R.id.txt_invalid);

        //Inicializa a base de dados.
        ModelFacade.initDatabase(this);
        ThreadManager.getInstanceManager(this).addListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.threadManager = ThreadManager.getInstanceManager(this);

        this.threadManager.stopTimeThread();
        this.threadManager.stopLoopGameThread();
        //Roda a thread de verificação do tempo de jogo, sendo assim, incrementa os contadores no objeto apropriado.
        this.threadManager.verifyTime(getPreferences(MODE_PRIVATE).getLong(StorageKeys.LAST_TIME, 0));
        System.out.println("terminou o onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();

        ThreadManager.getInstanceManager(this).stopTimeThread();

        //Commita as modificações para a base.
        ModelFacade.commitDatabase(this);
        System.out.println("terminou o onPause");
    }


    @Override
    public void onEventRunned(Event event) {
        if(event instanceof BattleEvent){
            Toast.makeText(this, "Porrada!", Toast.LENGTH_SHORT).show();
        }
        this.txtInvalid.setText("pontos offline: " + ModelFacade.getOfflinePlayerInstance().getCount());
        this.txtValid.setText("pontos válidos: " + ModelFacade.getValidPlayerInstance().getCount());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View v) {
        org.ncarneiro.testeauto.combat.Character c1 = new NonPlayerCharacter();
        c1.setName("NPC1");
        c1.setHp(30);
        c1.setAttack(3);
        c1.setDamage(3);
        c1.setEvade(3);
        Character c2 = new NonPlayerCharacter();
        c2.setName("NPC2");
        c2.setHp(30);
        c2.setAttack(3);
        c2.setDamage(3);
        c2.setEvade(3);
        String t = Combat.fight(c1, c2);
        ((TextView) findViewById(R.id.tv_text)).setText(t);
    }

}
