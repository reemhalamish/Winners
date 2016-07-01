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


public class ScoreManeger {
    private static ScoreManeger initiation;
    private ScoreManeger() {
        try {
        /*
        1. for each row at the table, gather data and insert into Games
        2. for each Games object, delete multiple objects
        3. for each Games object left, call Games.polish()
         */
        App application=App.getApplication();
            InputStream stream = application.getAssets().open("all_data.txt");
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
                scoreA=Integer.parseInt(parts[2].trim());
                scoreB=Integer.parseInt(parts[3].trim());
                Games relevantGames = bringRelevantGames(groupA, groupB);
                relevantGames.winsA.add(scoreA);
                relevantGames.winsB.add(scoreB);

            }
            for (Games current:all_games){
                current.polish();
                Log.d("games", "ready. meanA: " + current.meanA);
            }

// todo get the table :) and complete this functin


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Games bringRelevantGames(String groupA, String groupB) {
        for (Games game:all_games){
            if (game.nameA.equals(groupA) && game.nameB.equals(groupB))  return game;
            if (game.nameB.equals(groupA) && game.nameA.equals(groupB))  {
                game.flip();
                return game;
            }
        }
        Games newGame = new Games(groupA, groupB);
        all_games.add(newGame);
        return newGame;
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

