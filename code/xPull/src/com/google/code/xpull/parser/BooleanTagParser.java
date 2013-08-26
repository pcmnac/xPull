package com.google.code.xpull.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.ITagHandler;

public class BooleanTagParser extends BaseTagParser<Boolean>
{

    public BooleanTagParser(ITagHandler<Boolean> handler)
    {
        super(handler);
    }

    public BooleanTagParser()
    {
        this(null);
    }

    @Override
    protected Boolean doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        Boolean result = Boolean.valueOf(parser.nextText());

        return result;
    }

}
