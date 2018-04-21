package com.demo.recyclerview;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Datas {

    private static List<String> catItems = null;
    private static ArrayList<String> contentItems = null;

    public static List<String> getCats(Context context) {
        if (catItems != null) {
            return catItems;
        }

        String[] items = context.getResources().getStringArray(R.array.category);

        catItems = Arrays.asList(items);

        return catItems;
    }

    public static ArrayList<String> getContents(Context context) {
        if (contentItems != null) {
            return contentItems;
        }

        String[] items = context.getResources().getStringArray(R.array.content);

        contentItems = new ArrayList<>(Arrays.asList(items));

        return contentItems;
    }
}
