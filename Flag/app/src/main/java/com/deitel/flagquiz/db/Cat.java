package com.deitel.flagquiz.db;

/**
 * Created by tommy522588 on 2017/5/17.
 */

public class Cat {
    private int id;
    private String cat_category;
    private String cat_introduction;
    private String image_path;

    public Cat(String cat_category, String cat_introduction, String image_path) {
        this(0, cat_category, cat_introduction, image_path);
    }

    public Cat(int id, String cat_category, String cat_introduction, String image_path) {
        this.id = id;
        this.cat_category = cat_category;
        this.cat_introduction = cat_introduction;
        this.image_path = image_path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCat_category(String cat_category) {
        this.cat_category = cat_category;
    }

    public String getCat_category() {
        return cat_category;
    }

    public void setCat_introduction(String cat_introduction) {
        this.cat_introduction = cat_introduction;
    }

    public String getCat_introduction() {
        return cat_introduction;
    }
    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_path() {
        return image_path;
    }

}
