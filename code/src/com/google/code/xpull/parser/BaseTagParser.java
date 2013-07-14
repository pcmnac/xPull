package com.google.code.xpull.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.ITagHandler;
import com.google.code.xpull.ITagParser;

public abstract class BaseTagParser<T> implements ITagParser<T>
{

    private ITagHandler<T> handler;

    public BaseTagParser()
    {
        this(null);
    }

    public BaseTagParser(ITagHandler<T> handler)
    {
        this.handler = handler;
    }

    public final T parse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        if (parser.getEventType() != XmlPullParser.START_TAG)
        {
            throw new XmlPullParserException("START TAG expected");
        }

        T result = doParse(parser);

        if (handler != null)
        {
            handler.handle(result);
        }

        return result;
    }

    protected abstract T doParse(XmlPullParser parser) throws XmlPullParserException, IOException;

}
