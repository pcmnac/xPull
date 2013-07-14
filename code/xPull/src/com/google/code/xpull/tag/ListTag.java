package com.google.code.xpull.tag;

import java.util.List;

import com.google.code.xpull.Tag;
import com.google.code.xpull.parser.ListTagParser;

public class ListTag<T> extends Tag<List<T>>
{

    public ListTag(Tag<T> tag)
    {
        this(tag, false);
    }

    public ListTag(Tag<T> tag, boolean required)
    {
        super(tag.getName(), new ListTagParser<T>(tag.getName(), tag.getTagParser()), required);
    }

    @Override
    public boolean isList()
    {
        return true;
    }

}
