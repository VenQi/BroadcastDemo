package com.qwh.demo.broadcast.broadcastrecevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.qwh.demo.broadcast.MainActivity;

/**
 * From Android 3.1, newly installed apps are always put into a "stopped" state and the only way to move it out of the stopped
 * state is to manually launch any Activity of the app at least once.
 * 1) You need to design a mechanism where the user needs to first open any Activity of the app manually after installing the app.
 * 2) After that, your BootReceiver will work correctly and it will be able to launch any Activity of that app automatically. Your
 * implementation is absolutely correct.
 * I faced the same problem in one of my apps, where I was trying to open an Activity every time the device boots but it didn't
 * work for a newly installed app. Once the user opens the app manually at least once, the app moves out of "stopped" state and
 * everything works normally.
 *
 * EDIT
 * 1) Please ensure that the <uses-permission> is a direct child of the <manifest> tag.
 * 2) Please ensure that you specify android:installLocation="internalOnly" otherwise you will not receive any Boot Complete actions if the app is installed in the SD card.
 * 3) As I explained before also.
 * enter image description here
 * From Android 3.1, all apps are put in the stopped state which is the same as when user Force Closes any app.
 * While in this state, the application will not run automatically for any reason, until and unless launched
 * manually by the user from the launcher.
 * Meaning you will not receive ACTION_PACKAGE_INSTALLED, BOOT_COMPLETED etc. until the user manually launches the app. Google has
 * taken this decision to prevent malware apps from auto-launching. The user needs to open that app at least once, for it to perform
 * actions automatically after that.
 */
public class BootRecevier extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if ((Intent.ACTION_BOOT_COMPLETED).equalsIgnoreCase(intent.getAction())){
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
            Toast.makeText(context, "boot completed", Toast.LENGTH_SHORT).show();
            Log.e("Bootcompleted","Bootcompleted");
        }else if ((Intent.ACTION_BATTERY_CHANGED).equalsIgnoreCase(intent.getAction())){
            Toast.makeText(context,"battery changed",Toast.LENGTH_LONG).show();
            Log.e("ACTION_BATTERY_CHANGED","ACTION_BATTERY_CHANGED");
        }
//        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        throw new UnsupportedOperationException("Not yet implemented");
    }

}
