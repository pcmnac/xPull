package com.google.code.xpull.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.ITagHandler;

public class IntTagParser extends BaseTagParser<Integer>
{

    public IntTagParser(ITagHandler<Integer> handler)
    {
        super(handler);
    }

    public IntTagParser()
    {
        this(null);
    }

    @Override
    protected Integer doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        Integer result = Integer.parseInt(parser.nextText());

        return result;
    }

}
