
package com.parse.TekSee;

import android.support.multidex.MultiDexApplication;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends MultiDexApplication {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("828fe1fc7bb9a9a03e0a306f83487c877ec59a42")
            .clientKey("6a9ced4690ffcfca22511185d0155e87231ce9f7")
            .server("http://18.220.222.23:80/parse/")
            .build()
    );

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
