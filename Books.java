/*
 * Copyright (c) 2021. Created by Ihor Hudyma for TestMatick
 */

public class Books {
    private String name;
    private String authorName;
    private String price;
    private String ref;
    private boolean bestseller;

    public Books(String name, String authorName, String price, String ref, boolean bestseller) {
        this.name = name;
        this.authorName = authorName;
        this.price = price;
        this.ref = ref;
        this.bestseller = bestseller;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPrice() {
        return price;
    }

    public String getRef() {
        return ref;
    }

    public boolean isBestseller() {
        return bestseller;
    }
}
