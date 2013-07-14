package com.google.code.xpull.tag;

import com.google.code.xpull.Tag;
import com.google.code.xpull.parser.StringTagParser;

public class StringTag extends Tag<String>
{

    public StringTag(String name)
    {
        this(name, false);
    }

    public StringTag(String name, boolean required)
    {
        super(name, new StringTagParser(), required);
    }

}
