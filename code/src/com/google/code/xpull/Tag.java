package com.google.code.xpull;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/**
 * @author 04343650413
 * 
 * @param <T>
 */
public class Tag<T> implements ITag<T>
{

    private T value;

    private ITagParser<T> tagParser;

    private String name;

    private boolean required;

    public Tag(String name, ITagParser<T> parser)
    {
        this(name, parser, false);
    }

    public Tag(String name, ITagParser<T> parser, boolean required)
    {
        this.name = name;
        this.required = required;
        this.tagParser = parser;
    }

    @Override
    public T val()
    {
        return value;
    }

    public String getName()
    {
        return name;
    }

    public boolean isRequired()
    {
        return required;
    }

    public ITagParser<T> getTagParser()
    {
        return tagParser;
    }

    public boolean isList()
    {
        return false;
    }

    public void parse(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        value = tagParser.parse(parser);
    }

    @Override
    public String toString()
    {
        StringBuilder tag = new StringBuilder("<");
        tag.append(getName());
        if (isList())
        {
            tag.append(isRequired() ? "+" : "*");
        }
        else
        {
            tag.append(isRequired() ? "" : "?");
        }
        tag.append("/>");

        return tag.toString();
    }

}
