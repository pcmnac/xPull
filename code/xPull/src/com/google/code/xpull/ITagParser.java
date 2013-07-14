package com.google.code.xpull;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public interface ITagParser<T>
{
    T parse(XmlPullParser parser) throws XmlPullParserException, IOException;
}
