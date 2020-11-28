package com.company;

public class DroidWarrior implements Droid{
    private String name;
    private int health;
    private int damage;
    boolean shield;
    private final int maxHealth = 80;
    DroidWarrior(){
        name = names[ (int) (Math.random() * names.length )];
        health = maxHealth;
        damage = 10;
        shield = false;
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
        if(!shield) {
            return "1) Атака(" + damage + " одиниць)\n2) Щит (зменшує урон по команді на 50%, 1 раз за гру)";
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
            shield = true;
            return -1;
        }
        else return 0;
    }
    @Override
    public int getSkill(){
        if( !shield )return 1;
        else return 0;
    }
    @Override
    public String getStringType(){
        return "Воїн";
    }
    @Override
    public DroidClases getType() {
        return DroidClases.WARRIOR;
    }

    @Override
    public String toString() {
        return "[ Воїн:"+name+"|ХП:"+health+"|Урон:"+damage+"|Осибливе вміння: щит ]";
    }
}
