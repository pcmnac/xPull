package com.google.code.xpull.test.rss;

public class Item
{

    private String title;

    private String description;

    private Image image;

    public Item(String title, String description)
    {
        super();
        this.title = title;
        this.description = description;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

}
