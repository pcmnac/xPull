package com.google.code.xpull.test.maven;

public class Mirror
{
    private String id;
    private String name;
    private String url;
    private String mirrorOf;

    public Mirror(String id, String name, String url, String mirrorOf)
    {
        super();
        this.id = id;
        this.name = name;
        this.url = url;
        this.mirrorOf = mirrorOf;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMirrorOf()
    {
        return mirrorOf;
    }

    public void setMirrorOf(String mirrorOf)
    {
        this.mirrorOf = mirrorOf;
    }

}
