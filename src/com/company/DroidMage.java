package com.company;

import java.util.Random;

public class DroidMage implements Droid {
    private String name;
    private int health;
    private int damage;
    boolean fireball;
    private final int maxHealth = 50;
    DroidMage(){
            name = names[ (int) (Math.random() * names.length )];
            health = maxHealth;
            damage = 20;
            fireball = false;
    }
    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int decreaseHealth(int amount) {
        if (health > amount) {
            health -= amount;
        }
        else {
            health = 0;
        }
        return health;
    }

    @Override
    public int increaseHealth(int amount) {
        if( (health+amount) >= maxHealth){
            health = maxHealth;
        }
        else
            health+=amount;
        return health;
    }

    @Override
    public String getAction() {
        if(!fireball) {
            return "1) Атака(" + damage + " одиниць)\n2) Вогненна куля(подвійний урон, 1 раз за гру)";
        }
        else{
            return "1) Атака(" + damage + " одиниць)";
        }
    }
    @Override
    public int setAction(int action){
        if(action == 1) {
            return damage;
        }
        else if(action == 2) {
            fireball = true;
            return damage * 2;
        }
        else return 0;
    }
    @Override
    public int getSkill(){
        if( !fireball ) return 1;
        else return 0;
    }
    @Override
    public DroidClases getType() {
        return DroidClases.MAGE;
    }
    @Override
    public String getStringType(){
        return "Маг";
    }
    @Override
    public String toString() {
        return "[ Маг:"+name+"|ХП:"+health+"|Урон:"+damage+"|Осибливе вміння: вогненна куля ]";
    }
}
