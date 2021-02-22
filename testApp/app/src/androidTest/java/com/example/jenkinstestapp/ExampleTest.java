package com.example.jenkinstestapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
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
        mDevice.pressBack();
        //added code to test app install from playsto
        install("com.zenprise");
    }

    public void install(String packageId){
        openPlayStorePageOfApp(packageId);
        waitForTextAndClick("Install",10000);
        assertTrue(waitForInstallSuccess());
        mDevice.pressBack();
    }

    private boolean waitForInstallSuccess(){
        return isObjectVisible(mDevice, By.text("Uninstall"),40000);
    }

    private void openPlayStorePageOfApp(String packageId){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packageId));
        intent.setPackage("com.android.vending");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Context context = ApplicationProvider.getApplicationContext();
        context.startActivity(intent);
    }

    private boolean isObjectVisible(UiDevice mDevice, BySelector object, long timeout){
        return mDevice.wait(Until.hasObject(object),timeout);
    }

    private void waitForTextAndClick(String text,long timeout){
        isObjectVisible(mDevice,By.textContains(text),timeout);
        mDevice.findObject(By.textContains(text)).click();
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