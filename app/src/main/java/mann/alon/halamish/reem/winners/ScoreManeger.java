package mann.alon.halamish.reem.winners;

/**
 * Created by Mr Bond on 27/05/2016.
 */


import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;


public class ScoreManeger {
    private static final java.lang.String TABLE_FILE_NAME = "all_data.txt";
    private static ScoreManeger initiation;
    private ScoreManeger() {
        try {
        /*
        1. for each row at the table, gather data and insert into Games
        2. for each Games object, delete multiple objects
        3. for each Games object left, call Games.polish()
         */
        App application=App.getApplication();
            InputStream stream = application.getAssets().open(TABLE_FILE_NAME);
            String table=get_string_from_input(stream);
            Log.d("score", stream.toString());
            String[] lines=table.split("\n");

            all_games=new ArrayList<>();
            all_groups_names=new ArrayList<>();

            for (String line : lines) {
                if (line.startsWith("GroupA")) {continue;} // header line

                String[] parts=line.split("\t");
                assert parts.length==4;
                String groupA, groupB;
                int scoreA, scoreB;
                groupA=parts[0];
                groupB=parts[1];
                while (groupA.startsWith(" ")) {
                    groupA = groupA.substring(1);
                }
                while (groupA.endsWith(" ")) {
                    groupA = groupA.substring(0,groupA.length()-1);
                }
                while (groupB.startsWith(" ")) {
                    groupB= groupB.substring(1);
                }
                while (groupB.endsWith(" ")) {
                    groupB = groupB.substring(0,groupB.length()-1);
                }
                scoreA=Integer.parseInt(parts[2].trim());
                scoreB=Integer.parseInt(parts[3].trim());
                Games relevantGames = bringRelevantGames(groupA, groupB, true);
                relevantGames.winsA.add(scoreA);
                relevantGames.winsB.add(scoreB);

                populate_group_names_if_not_exist(groupA);
                populate_group_names_if_not_exist(groupB);

            }
            for (Games current:all_games){
                current.polish();
                Log.d("games", "ready. meanA: " + current.meanA);
            }
            Collections.sort(all_groups_names);

// todo get the table :) and complete this functin


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Games bringRelevantGames(String groupA, String groupB, boolean createIfNotExist) {
        for (Games game:all_games){
            if (game.nameA.equals(groupA) && game.nameB.equals(groupB))  return game;
            if (game.nameB.equals(groupA) && game.nameA.equals(groupB))  {
                game.flip();
                return game;
            }
        }
        if (createIfNotExist) {
            Games newGame = new Games(groupA, groupB);
            all_games.add(newGame);
            return newGame;
        } else {
            return null;
        }
    }

    private String get_string_from_input(InputStream stream) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(stream, writer,"Unicode");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String theString = writer.toString();
        return theString;
    }

    public synchronized static ScoreManeger getManeger() {
        if (initiation==null)
            initiation= new ScoreManeger();
        return initiation;}

    /** properties:*/
    ArrayList<Games> all_games;
    ArrayList<String> all_groups_names;

//    public Games get_game(String NameA,String NameB){
//        //searches all_games and return game of interst
//        return null;}

//    public void delete_game(Games toDelete){
//        all_games.remove(toDelete);
//         }

    //if (gameA.getNameA()==gameB.getNameB()) and (gameA.getNameB()==gameB.getNameA())
//    public void merge_two_games(Games gameA,Games gameB) {
//
//                gameA.winsA.addAll(gameB.winsB);
//                gameA.winsB.addAll(gameB.winsA);
//                delete_game(gameB);
//
//    }

    void populate_group_names_if_not_exist(String name){
        for (String current:all_groups_names){
            if (current.equals(name)) return;

        }
        all_groups_names.add(name);
    }




}

