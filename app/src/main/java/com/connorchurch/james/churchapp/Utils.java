package com.connorchurch.james.churchapp;

import java.util.ArrayList;

public class Utils {

    static String IMGS[] = {
            "http://connorpresbyterianchurch.org/wp-content/uploads/Image-50.png",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1502-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1503-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1504-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1505-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1506-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1507-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1508-resized-image-600x450.jpg",
            "http://connorpresbyterianchurch.org/wp-content/uploads/IMG_1510-resized-image-600x450.jpg",

    };

    public static ArrayList<ImageModel> getData() {
        ArrayList<ImageModel> arrayList = new ArrayList<>();
        for (int i = 0; i < IMGS.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setName("Image " + i);
            imageModel.setUrl(IMGS[i]);
            arrayList.add(imageModel);
        }
        return arrayList;
    }
}

