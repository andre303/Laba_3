package com.company;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
   static Droid getDroid(int type){
        if(type == 1) return new DroidMage();
        if(type == 2) return new DroidWarrior();
        if(type == 3) return new DroidHealer();
        return null;
    }
   static void pause(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    } /** Призупинка програми на 2с */
    static void printDroids(Vector<Droid> droids){
        int i = 1;
        for (Droid droid:droids) {
            System.out.println(i + ")" + droid.toString());
            i++;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        Fight fight = new Fight();
        Vector<Droid> droids = new Vector<Droid>();
        Droid a = new DroidMage();
        Droid b = new DroidMage();
        int menu;
        int choice;
        int team_choice;
        int player, opponent;
        while(true){
            System.out.println("1) Створити дроїда\n" +
                    "2) Список створених дроїдів\n" +
                    "3) Бій 1 на 1\n" +
                    "4) Команда на команду\n" +
                    "5) FAQ\n" +
                    "6) Вийти з програми");
            menu = in.nextInt();
            switch (menu){
                case 1:
                    System.out.println("Виберіть клас дроїда:\n1 - Маг\n2 - Воїн\n3 - Лікар");
                    choice = in.nextInt();
                    if(choice < 1 || choice > 3){
                        System.out.println("Помилка! Такого класу не існує!!!");
                        continue;
                    }
                    else{
                        droids.add(getDroid(choice));
                    }
                    break;
                case 2:
                    printDroids(droids);
                    pause();
                    break;
                case 3:
                    printDroids(droids);
                    System.out.println("Виберіть вашого дроїда->");
                    player = in.nextInt()-1;
                    System.out.println("Виберіть дроїда оппонента->");
                    opponent = in.nextInt()-1;
                    if(player < 0 || player >= droids.size() || opponent < 0 || opponent >= droids.size() || player == opponent){
                        System.out.println("Помилка! Такого дроїду не існує, або ви вибрали одинакових дроїдів!!!");
                        continue;
                    }
                    fight.duel(droids.get(player), droids.get(opponent));
                    pause();
                    break;
                case 4:
                    printDroids(droids);
                    System.out.println("Сформуйте свою команду(0 для закінчення):");
                    team_choice = in.nextInt();
                    do{
                        if((team_choice - 1) >= 0 && (team_choice - 1) < droids.size()){
                            if(!fight.containsInTeams(droids.get(team_choice-1))){
                                fight.addPlayerTeam(droids.get(team_choice-1));
                            }
                        }
                        team_choice = in.nextInt();
                    }while(team_choice!=0);
                    System.out.println("Сформуйте команду оппонента(0 для закінчення):");
                    team_choice = in.nextInt();
                    do{
                        if((team_choice - 1) >= 0 && (team_choice - 1) < droids.size()){
                            if(!fight.containsInTeams(droids.get(team_choice - 1))){
                                fight.addOpponentTeam(droids.get(team_choice - 1));
                            }
                        }
                        team_choice = in.nextInt();
                    }while(team_choice!=0);
                    fight.battle();
                    break;
                case 5:
                    System.out.println("Маг - це юніт який може наносити баго урону, проте має малий обсяг здоров'я\n" +
                            "Воїн - це збалансований юніт який може наносити непоганий урон, і має багато здоров'я\n" +
                            "Лікар - має мало здоров'я і невелику силу атаки, проте може лікувати своїх товаришів");
                    pause();
                    pause();
                    break;
                case 6:return;
            }
        }
    }
}
