package mann.alon.halamish.reem.winners;

/**
 * Created by Mr Bond on 27/05/2016.
 */
public class ScoreManeger {
    private static ScoreManeger initiation;
    private ScoreManeger() {}
    public synchronized static ScoreManeger getManeger() {
        if (initiation==null)
            initiation= new ScoreManeger();
        return initiation;

    }



    }

