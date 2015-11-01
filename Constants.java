package com.example.karthika.connect;

import android.app.Activity;
import android.provider.Settings.Global;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by karthika on 10/27/2015.
 */
public class Constants {
    public static ArrayList<WeakReference<Activity>> activity_stack=new ArrayList<WeakReference<Activity>>();
    /**
     * Add the activity as weak reference to activity stack.
     * @param act
     */
    public static void addToActivityStack(Activity act)
    {
        WeakReference<Activity> ref = new WeakReference<Activity>(act);
        activity_stack.add(ref);

    }
    /**
     * Kill all the activities on activity stack except act.
     * To kill all the passed parameter should be null.
     */
    public static void killAllExcept(Activity act)
    {
        for(WeakReference<Activity> ref: activity_stack)
        {
            if(ref != null && ref.get() != null)
            {
                if(act != null && ref.get().equals(act))
                {
                    continue;//dont finish this up.
                }
                ref.get().finish();
            }
        }
        activity_stack.clear();//but clear all the activity references
    }
}
