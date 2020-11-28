package com.company;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Fight {//Клас паттерну стратегія
    private Vector<Droid> team_player;//Поміняти на ArrayList
    private Vector<Droid> team_opponent;
    ArrayList<String> playerFightersList;
    ArrayList<String> opponentFightersList;
    Fight(){
        team_player = new Vector<Droid>();
        team_opponent = new Vector<Droid>();
    }/** Конструктор */
    boolean[] getTrueBoolean(int count){
        boolean[] array = new  boolean[count];
        for(int i = 0; i<array.length; i++) array[i] = true;
        return array;
    }
    void addPlayerTeam(Droid droid){
        team_player.add(droid);
    } /** Додавання бійця в вашу команду */
    void addOpponentTeam(Droid droid){
        team_opponent.add(droid);
    } /** Додавання бійця в команду оппонента */
    boolean containsInTeams(Droid droid){
        return (team_player.contains(droid)||team_opponent.contains(droid));
    }
    void clear(){
        team_player.clear();
        team_opponent.clear();
    } /** Очищення команд */
    String getPlayerTeamString(){
        String string = "";
        for(Droid droid:team_player){
            string += droid.toString() + "\n";
        }
        return string;
    } /** Список бійців вашої команди */
    String getOpponentTeamString(){
        String string = "";
        for(Droid droid:team_opponent){
            string += droid.toString() + "\n";
        }
        return string;
    } /** Список бійців команди оппонента */
    void pause(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    } /** Призупинка програми на 1с */
    private ArrayList<String> getFightersList(Vector<Droid> fighters, boolean[] usedFighters){
        int i = 1;
        ArrayList<String> list = new ArrayList<String>();
        for(Droid droid:fighters){
            if(usedFighters[i-1]) {
                list.add(i + ")" + droid.getStringType() + "|HP:" + droid.getHealth() +
                        "|ATK:" + droid.getDamage() + "|ОА:" + droid.getSkill() + "|");
            }
            else{
                list.add("(X)" + i + ")" + droid.getStringType() + "|HP:" + droid.getHealth() +
                        "|ATK:" + droid.getDamage() + "|ОА:" + droid.getSkill() + "|");
            }
            i++;
        }
        return list;
    } /** Список бійців */
   private ArrayList<String> getFightersList(Vector<Droid> fighters){
        int i = 1;
        ArrayList<String> list = new ArrayList<String>();
        for(Droid droid:fighters){
                list.add(i + ")" + droid.getStringType() + "|HP:" + droid.getHealth() +
                        "|ATK:" + droid.getDamage() + "|ОА:" + droid.getSkill() + "|");
            i++;
        }
        return list;
    }
    private void printFightTable( ArrayList<String> playerFightersList, ArrayList<String> opponentFightersList){
        System.out.println("|Гравець\t\t\t\t\tОппонент|");
        for(int i = 0; i < playerFightersList.size(); i++){
            System.out.println("|"+playerFightersList.get(i)+"\t"+opponentFightersList.get(i)+"|");
        }
    }

    public int battle(){
        Scanner in = new Scanner(System.in);
        if((team_player.size()!=team_opponent.size()) || (team_player.size()==0) || (team_opponent.size()==0)){
            System.out.println("В командах повинна бути рівна кількість бійців відмінна від нуля!\n" +
                    "В команді гравця " + team_player.size() + " бійців\n " +
                    "В команді опонента " + team_opponent.size() + " бійців");
                    return -1;
        }

        boolean[] usedPlayerFighters = getTrueBoolean(team_player.size());;
        playerFightersList =  getFightersList(team_player,usedPlayerFighters);;
        opponentFightersList = getFightersList(team_opponent);;
        printFightTable(playerFightersList,opponentFightersList);
        pause();
        int current;
        int action;
        int target;
        double damageMultiplierPlayer = 1;
        double damageMultiplierOpponent = 1;
        while (team_player.size()!=0 && team_opponent.size()!=0) {
            usedPlayerFighters = getTrueBoolean(team_player.size());
            damageMultiplierPlayer = 1;
            System.out.println("******Хід гравця******");
            for(int i = 0; i < team_player.size() && team_opponent.size() > 0; i++) { //Хід гравця
                playerFightersList = getFightersList(team_player, usedPlayerFighters);
                opponentFightersList = getFightersList(team_opponent);
                System.out.print(playerFightersList.toString());
                System.out.print("\nВиберіть дроїда->");
                current = in.nextInt() - 1;
                if(current>=team_player.size() || current<0 || !usedPlayerFighters[current]){
                    System.out.println("Такого дроїда не існує, або він вже виконував дію в цьому ході....Спробуйте ще раз");
                    i--;
                    continue;
                } //Перевірка на правельність вибору бійця
                System.out.println("Виберіть дію:\n" + team_player.get(current).getAction()); //Вивід можлиих ходів
                action = in.nextInt();
                action = team_player.get(current).setAction(action);
                if(action == 0){
                    System.out.println("Помилка вводу дії....Спробуйте знову");
                    i--;
                    continue;
                }

                if(action == -1 && team_player.get(current).getType() == DroidClases.HEALER){
                    for(int j = 0; j<team_player.size(); j++) {
                        team_player.get(j).increaseHealth((int) (team_player.get(j).getMaxHealth() * 0.25));
                    }
                }
                else if( action == -1 && team_player.get(current).getType() == DroidClases.WARRIOR ){
                    damageMultiplierPlayer = 0.75;
                }
                else{
                    System.out.println("Виберіть бійця команди оппонента:");
                    System.out.println(opponentFightersList.toString());
                    target = in.nextInt() - 1;
                    if(target>=team_opponent.size() || target<0){
                        System.out.println("Такого дроїда не існує....Спробуйте ще раз");
                        i--;
                        continue;
                    }
                    team_opponent.get(target).decreaseHealth((int) ( (double) action*damageMultiplierOpponent));
                    if(team_opponent.get(target).getHealth()==0){
                        team_opponent.remove(target);
                    }
                }
                usedPlayerFighters[current] = false;
            }

            damageMultiplierOpponent = 1;
            for(int i = 0; i < team_opponent.size() && team_player.size() > 0; i++) { //Хід оппонента
                current = i;
                if(team_opponent.get(current).getSkill()!=0){
                    action = (int) (Math.random()*2)+1;
                }
                else action = 1;
                action = team_opponent.get(current).setAction(action);
                if(action == -1 && team_opponent.get(current).getType() == DroidClases.HEALER){
                    System.out.println("Ворожий лікар вилікував команду");
                    for(int j = 0; j < team_opponent.size(); j++) {
                        team_opponent.get(j).increaseHealth((int) (team_opponent.get(j).getMaxHealth() * 0.25));
                    }
                }
                else if( action == -1 && team_opponent.get(current).getType() == DroidClases.WARRIOR ){
                    System.out.println("Ворожий воїн використав щит");
                    damageMultiplierPlayer = 0.75;
                }
                else{
                    target = (int) (Math.random()*( team_player.size() ));
                    team_player.get(target).decreaseHealth((int) ( (double) action*damageMultiplierPlayer));
                    System.out.println("Ворожий "+ team_opponent.get(current).getStringType() +
                            " наніс " + team_player.get(target).getName() +
                            " " + (int) ( (double) action*damageMultiplierPlayer) + " шкоди");
                    pause();
                    if(team_player.get(target).getHealth()==0){
                        System.out.println(team_player.get(target).getName() + " помер :(");
                        pause();
                        team_player.remove(target);
                    }
                }

            }
        }
        if(team_player.size()==0) {
            System.out.println("На жаль ви програли:(");
        }
        if(team_opponent.size()==0) {
            System.out.println("!!!ВІТАЮ З ПЕРЕМОГОЮ!!!");
        }
        pause();
        clear();
        return 0;
    }/** Командна битва */
    void duel(Droid player, Droid opponent){
            Scanner in = new Scanner(System.in);
            int action;
            double damageMultiplierPlayer = 1;
            double damageMultiplierOpponent = 1;
            System.out.println(player.getName() + "|" + player.getStringType() + "\tпроти\t" +
                    opponent.getName() + "|" + opponent.getStringType() );
            pause();
            while (true){
                System.out.println(player.getName() + ":" + player.getHealth() + "HP");
                System.out.println(opponent.getName() + ":" + opponent.getHealth() + "HP");
                pause();
                System.out.println("Ваш хід");
                System.out.println("Виберіть дію:\n" + player.getAction()); //Вивід можлиих ходів
                action = in.nextInt();
                action = player.setAction(action);

                damageMultiplierPlayer = 1;
                if(action == 0){
                    System.out.println("Помилка вводу дії....Спробуйте знову");
                    continue;
                }
                if(action == -1 && player.getType() == DroidClases.HEALER){
                       player.increaseHealth((int) (player.getMaxHealth() * 0.25));

                }
                else if( action == -1 && player.getType() == DroidClases.WARRIOR ){
                    damageMultiplierPlayer = 0.75;
                }
                else{
                    opponent.decreaseHealth((int) ( (double) action * damageMultiplierOpponent));
                    if(opponent.getHealth()==0){
                        System.out.println("\t!!!Ви перемогли!!!");
                        break;
                    }
                }

                damageMultiplierOpponent = 1;

                if(opponent.getSkill()!=0){
                    action = (int) (Math.random()*2)+1;
                }
                else action = 1;
                action = opponent.setAction(action);
                if(action == -1 && opponent.getType() == DroidClases.HEALER){
                    opponent.increaseHealth((int) (opponent.getMaxHealth() * 0.25));
                    System.out.println("Оппонент вилікувався");
                }
                else if( action == -1 && opponent.getType() == DroidClases.WARRIOR ){
                    damageMultiplierOpponent = 0.75;
                    System.out.println("Оппонент використав щит");
                }
                else{
                    player.decreaseHealth((int) ( (double) action * damageMultiplierPlayer));
                    System.out.println("Оппонент наніс "+ (int) ( (double) action * damageMultiplierPlayer) + "шкоди");
                    if(player.getHealth()==0){
                        System.out.println("\t!!!Ви програли :(!!!");
                        break;
                    }
                }
                pause();
            }
            clear();
    }
}
