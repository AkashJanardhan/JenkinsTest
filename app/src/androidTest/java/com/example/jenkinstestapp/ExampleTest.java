package com.example.jenkinstestapp;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class ExampleTest {
    private UiDevice mDevice;
    @Before
    public void initSetup(){
        mDevice = UiDevice.getInstance(getInstrumentation());
    }

    @Test
    public void startApp(){
        openApp(mDevice,"com.android.vending");
    }

    private static void openApp(UiDevice mDevice,String packageName){

        final String launcherPackage = mDevice.getLauncherPackageName();
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), 10000);
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), 5000);

    }
}