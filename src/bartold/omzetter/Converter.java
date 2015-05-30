package bartold.omzetter;

import android.app.Activity;
import android.app.Application;

import android.content.Context;

public class Converter extends Application{

    private static Context context;

    public void onCreate(){
        super.onCreate();
        Converter.context = getApplicationContext();
        
        System.out.println("Created");
        DataManager.initPresets();
    }

    public static Context getAppContext() {
        return Converter.context;
    }
}
