package com.example.administrator.filemanager.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public class FileSizeUtils {

    // k M G
    public static String getSizeFromKM(long size) {
        if (size / 1000 > 1) {
            if (size / 1000 / 1000 > 1) {
                if (size / 1000 / 1000 / 1000 > 1) {
                    return size / 1000 / 1000 / 1000 + "GB";
                } else {
                    return size / 1000 / 1000 + "MB";
                }
            } else {
                return size / 1000 + "KB";
            }
        }
        return size + "B";
    }

    public static final int TYPE_NAME = 1;
    public static final int TYPE_SIZE = 2;
    public static final int TYPE_LAST = 3;
    public static final int TYPE_CLASS = 4;

    //排序
    public static final List<File> sortByName(int type, final boolean isAscending, List<File> fileList) {
        List<File> dirs = new ArrayList<>();
        List<File> files = new ArrayList<>();
        for (File file : fileList) {
            if (file.isDirectory())
                dirs.add(file);
            else
                files.add(file);
        }
        Comparator<File> comparator = null;
        switch (type) {
            case TYPE_NAME:
                comparator = new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        int result = o1.getName().compareTo(o2.getName());
                        return isAscending ? result : -result;
                    }
                };
                break;
            case TYPE_CLASS:
                comparator = new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        String o1name = "";
                        String o2name = "";
                        try {
                            o1name = o1.getName().substring(o1.getName().lastIndexOf("."));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            o2name = o2.getName().substring(o2.getName().lastIndexOf("."));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        int result = o1name.compareTo(o2name);
                        return isAscending ? result : -result;
                    }
                };
                break;
            case TYPE_LAST:
                comparator = new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        int result = (int) (o1.lastModified() - o2.lastModified());
                        return isAscending ? result : -result;
                    }
                };
                break;
            case TYPE_SIZE:
                comparator = new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        int result = (int) (o1.length() - o2.length());
                        return isAscending ? result : -result;
                    }
                };
                break;
        }
        Collections.sort(dirs, comparator);
        Collections.sort(files, comparator);
        fileList = new ArrayList<>();
        fileList.addAll(dirs);
        fileList.addAll(files);
        return fileList;
    }

}
