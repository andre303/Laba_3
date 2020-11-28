package com.company;

public interface Droid {
     String[] names = {"Bill", "Bob", "James", "Joe", "Dwayne", "Rayan", "Zoey", "Kira", "Alex", "Joan", "Anna", "Jhon" , "Nicole", "Julia"};
     @Override
     String toString();
     int getHealth();
     int getMaxHealth();
     int getDamage();
     String getName();
     int decreaseHealth(int amount);
     int increaseHealth(int amount);
     public String getAction();
     DroidClases getType();
     String getStringType();
     int setAction(int action);
     int getSkill();
}
