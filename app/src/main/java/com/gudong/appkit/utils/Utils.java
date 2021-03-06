package com.gudong.appkit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import com.gudong.appkit.R;

/**
 * the util class for app
 * Created by mao on 7/21/15.
 */
public class Utils {
    public static void setCurrentVersion(Context context,String version){
        putStringPreference(context,"current_version",version);
    }

    public static String getLocalVersion(Context context){
        return getStringPreference(context, "current_version", "");
    }

    /**
     * 最近列表是否显示App+
     * @param context 上下文对象
     * @return return true if recent listview need show app+
     */
    public static boolean isShowSelf(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(context.getString(R.string.switch_preference_show_self_key), false);
    }
    /**
     * 发送邮件的头信息
     * @param activity
     * @return
     */
    public static String getLog(Activity activity) {
        PackageManager pm = activity.getPackageManager();
        StringBuffer sb = new StringBuffer();
        try {
            PackageInfo info = pm.getPackageInfo(activity.getPackageName(), 0);
            sb.append("versionName:" + info.versionName).append("\n");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String brand = android.os.Build.BRAND;
        String device = android.os.Build.DEVICE;
        String product = android.os.Build.PRODUCT;
        String hardware = android.os.Build.HARDWARE;
        String SDK = android.os.Build.VERSION.SDK;
        String androidv = android.os.Build.VERSION.RELEASE;
        sb.append("ANDROID:" + androidv).append("\n");
        sb.append("BRAND:" + brand).append("\n");
        sb.append("DEVICE:" + device).append("\n");
        sb.append("PRODUCT:" + product).append("\n");
        sb.append("HARDWARE:" + hardware).append("\n");
        sb.append("SDK:" + SDK).append("\n");

        return sb.toString();
    }

    /**
     * get brand info
     * @return brand info
     */
    public static String getBrand(){
        return android.os.Build.BRAND;
    }

    /**
     * get app version info
     * @param context context
     * @return app version info if occur exception return unknow
     */
    public static String getAppVersion(Context context){
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return context.getString(R.string.unknow);
        }
    }

    public static float getDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    public static int convertDensityPix(Context context, int dip){
        float scale = getDensity(context);
        return (int) (dip * scale + 0.5f);
    }

    // -------------------    SharePreference Util Begin   -------------------  //

    public static void removeKey(Context context,String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(key).apply();
    }

    public static void putStringPreference(Context context,String key,String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).apply();
    }

    public static String getStringPreference(Context context,String key,String def){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, def);
    }

    public static void putIntPreference(Context context,String key,int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(key, value).apply();
    }

    public static int getIntPreference(Context context,String key,int def){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(key, def);
    }

    public static void putBooleanPreference(Context context,String key,boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBooleanPreference(Context context,String key){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(key,false);
    }

    // -------------------    SharePreference Util End   -------------------  //

    public static class Setting{

        /**
         * set preference for whether show sum bug point
         * @param context context
         */
        public static void setDoNotShowPointForSumBug(Context context){
            putBooleanPreference(context,"do_not_show_point",true);
        }

        /**
         * get preference for whether show sum bug point
         * @param context context
         * @return return false if app need show bug point dialog
         */
        public static boolean isNotShowPointForSumBug(Context context){
            return getBooleanPreference(context,"do_not_show_point");
        }
    }
}

