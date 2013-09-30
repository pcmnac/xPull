package com.google.code.xpull.tag;

import com.google.code.xpull.Tag;
import com.google.code.xpull.parser.LongTagParser;

public class LongTag extends Tag<Long>
{

    public LongTag(String name)
    {
        this(name, false);
    }

    public LongTag(String name, boolean required)
    {
        super(name, new LongTagParser(), required);
    }

}
