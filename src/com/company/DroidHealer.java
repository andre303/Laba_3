package com.company;

public class DroidHealer implements Droid {
    private String name;
    private int health;
    private int damage;
    short heal;
    private final int maxHealth = 50;
    DroidHealer(){
        name = names[ (int) (Math.random() * names.length )];
        health = maxHealth;
        damage = 5;
        heal = 2;
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
        if(heal!=0) {
            return "1) Атака(" + damage + " одиниць)\n2) Зцілення (Відновлює всій команді 25% здоров'я, 2 рази за гру)";
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
        else if(action == 2 && heal > 0) {
            heal--;
            return -1;
        }
        else return 0;
    }
    @Override
    public int getSkill(){
        return heal;
    }
    @Override
    public DroidClases getType() {
        return DroidClases.HEALER;
    }
    @Override
    public String getStringType(){
        return "Лікар";
    }
    @Override
    public String toString() {
        return "[ Лікар:"+name+"|ХП:"+health+"|Урон:"+damage+"|Осибливе вміння: зцілення ]";
    }
}
