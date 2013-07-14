package com.google.code.xpull.tag;

import java.util.Date;

import com.google.code.xpull.Tag;
import com.google.code.xpull.parser.DateTagParser;

public class DateTag extends Tag<Date>
{

    public DateTag(String name)
    {
        this(name, false);
    }

    public DateTag(String name, boolean required)
    {
        super(name, new DateTagParser(), required);
    }

    public DateTag(String name, String pattern)
    {
        this(name, pattern, false);
    }

    public DateTag(String name, String pattern, boolean required)
    {
        super(name, new DateTagParser(pattern), required);
    }

}
