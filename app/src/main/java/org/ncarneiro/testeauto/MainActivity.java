package org.ncarneiro.testeauto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Character c1 = new NonPlayerCharacter();
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
