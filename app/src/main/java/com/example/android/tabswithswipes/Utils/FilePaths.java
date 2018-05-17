package com.example.android.tabswithswipes.Utils;

import android.os.Environment;

/**
 * Created by Chaitanya Shiva on 14-05-2018.
 */

public class FilePaths {
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();
    public String PICTURES = ROOT_DIR + "/Pictures";
    public String CAMERA = ROOT_DIR + "/DCIM/Camera";
    public String STORIES = ROOT_DIR + "/Stories";
}
