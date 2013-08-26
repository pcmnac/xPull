package com.google.code.xpull.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.ITagHandler;

public class StringTagParser extends BaseTagParser<String>
{

    public StringTagParser(ITagHandler<String> handler)
    {
        super(handler);
    }

    public StringTagParser()
    {
        super(null);
    }

    @Override
    protected String doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        String result = parser.nextText();

        return result;
    }

}
