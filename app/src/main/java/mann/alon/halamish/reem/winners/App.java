package mann.alon.halamish.reem.winners;

/**
 * Created by Mr Bond on 24/06/2016.
 */
public class App extends android.app.Application {
  static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        ScoreManeger.getManeger();
    }

    static App getApplication() {
        return app;
    }
}
