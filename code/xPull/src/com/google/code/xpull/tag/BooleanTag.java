package com.google.code.xpull.tag;

import com.google.code.xpull.Tag;
import com.google.code.xpull.parser.BooleanTagParser;

public class BooleanTag extends Tag<Boolean>
{

    public BooleanTag(String name)
    {
        this(name, false);
    }

    public BooleanTag(String name, boolean required)
    {
        super(name, new BooleanTagParser(), required);
    }

}
