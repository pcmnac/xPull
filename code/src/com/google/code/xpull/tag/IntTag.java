package com.google.code.xpull.tag;

import com.google.code.xpull.Tag;
import com.google.code.xpull.parser.IntTagParser;

public class IntTag extends Tag<Integer>
{

    public IntTag(String name)
    {
        this(name, false);
    }

    public IntTag(String name, boolean required)
    {
        super(name, new IntTagParser(), required);
    }

}
