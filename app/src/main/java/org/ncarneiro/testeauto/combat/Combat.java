package org.ncarneiro.testeauto.combat;

/**
 * Created by Nikolas on 31/08/2015.
 */
public class Combat {

    private static Character attacker;
    private static Character defender;

    public static String fight(Character c1, Character c2) {
        StringBuffer log = new StringBuffer();
        while (c1.getHp()>0 && c2.getHp()>0) {
            setRoles(c1, c2);
            log.append(attack()+"\n");
        }
        if (c1.getHp()>0)
            log.append(c1.getName()+" defeated "+c2.getName());
        else
            log.append(c2.getName()+" defeated "+c1.getName());
        return log.toString();
    }

    private static void setRoles(Character c1, Character c2) {
        int i1 = Dice.rollHand(c1.getEvade());
        int i2 = Dice.rollHand(c2.getEvade());
        if (i1>i2) {
            attacker = c1;
            defender = c2;
        }
        else if (i2>i1) {
            attacker = c2;
            defender = c1;
        }
        else
            setRoles(c1, c2);
    }

    private static String attack() {
        String log;
        int atk = Dice.rollHand(attacker.getAttack());
        int def = Dice.rollHand(defender.getEvade());
        if (atk>def) {
            int damage = Dice.rollHand(attacker.getDamage());
            defender.setHp(defender.getHp()-damage);
            log = (attacker.getName() + " hit " + defender.getName() + " causing " + damage + " points of damage.");
        } else if (atk==def) {
            int damage = Dice.rollHand(attacker.getDamage())/2;
            defender.setHp(defender.getHp()-damage);
            log = (attacker.getName() + " hit " + defender.getName() + " causing " + damage + " points of damage.");
        } else {
            log = (defender.getName() + " avoided " + attacker.getName() + "'s attack.");
        }
        return log;
    }

}