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


import mann.alon.halamish.reem.winners.Games;


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
            for (String line : lines) {
                Log.d("line", line);
            }

// todo get the table :) and complete this functin


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String get_string_from_input(InputStream stream) {
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(stream, writer,"UTF-8");
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

    public Games get_game(String NameA,String NameB){
        //searches all_games and return game of interst
        return null;}

    public void delete_game(Games toDelete){
        all_games.remove(toDelete);
         }

    //if (gameA.getNameA()==gameB.getNameB()) and (gameA.getNameB()==gameB.getNameA())
    public void merge_two_games(Games gameA,Games gameB) {

                gameA.winsA.addAll(gameB.winsB);
                gameA.winsB.addAll(gameB.winsA);
                delete_game(gameB);

    }




    }

