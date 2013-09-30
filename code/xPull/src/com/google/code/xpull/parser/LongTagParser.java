package com.google.code.xpull.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.ITagHandler;

public class LongTagParser extends BaseTagParser<Long>
{

    public LongTagParser(ITagHandler<Long> handler)
    {
        super(handler);
    }

    public LongTagParser()
    {
        this(null);
    }

    @Override
    protected Long doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        Long result = Long.parseLong(parser.nextText());

        return result;
    }

}
