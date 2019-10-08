package com.kanhui.laowulao.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.kanhui.laowulao.locker.model.AppsModel;

import java.util.List;

public class AppUtils {

    private static AppUtils Instance;

    private Context context;

    public static AppUtils getInstance(Context context){
        if(Instance == null){
            Instance = new AppUtils(context);
        }
        return Instance;
    }

    private AppUtils(Context context){
        this.context = context;
    }

    private List<ApplicationInfo> pakageinfos;
    private PackageManager pm ;
    public Drawable getIcon(String name){

        if(pakageinfos == null || pm == null){
            pm = context.getPackageManager();
            pakageinfos = pm.getInstalledApplications(0);
        }
        for(int i = 0 ; i < pakageinfos.size() ; i++){
            ApplicationInfo info = pakageinfos.get(i);
            String label = info.loadLabel(pm).toString();
            if(label.equals(name)){
                Drawable d = info.loadIcon(pm);
                return d;
            }
        }
        return null;
    }


    public AppsModel getModelByName(String name){
        AppsModel model = new AppsModel();
        if(pakageinfos == null || pm == null){
            pm = context.getPackageManager();
            pakageinfos = pm.getInstalledApplications(0);
        }
        for(int i = 0 ; i < pakageinfos.size() ; i++){
            ApplicationInfo info = pakageinfos.get(i);
            String label = info.loadLabel(pm).toString();
            if(label.equals(name)){
                Drawable d = info.loadIcon(pm);
                model.setAppName(name);
                model.setPackageName(info.packageName);
                model.setAppIcon(d);
                return model;
            }
        }
        return model;
    }
}
