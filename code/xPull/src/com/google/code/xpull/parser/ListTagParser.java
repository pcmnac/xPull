package com.google.code.xpull.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.google.code.xpull.ITagHandler;
import com.google.code.xpull.ITagParser;
import com.google.code.xpull.util.ParsingUtils;

public class ListTagParser<T> extends BaseTagParser<List<T>>
{

    private String name;
    private ITagParser<T> tagParser;
    private List<T> list;

    public ListTagParser(String name, ITagParser<T> tagParser, ITagHandler<List<T>> handler)
    {
        super(handler);
        this.name = name;
        this.tagParser = tagParser;
    }

    public ListTagParser(String name, ITagParser<T> tagParser)
    {
        this(name, tagParser, (ITagHandler<List<T>>) null);
    }

    public ListTagParser(String name, ITagParser<T> tagParser, List<T> destination)
    {
        super(null);
        this.name = name;
        this.tagParser = tagParser;
        this.list = destination;
    }

    @Override
    protected List<T> doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        if (list == null)
        {
            list = new ArrayList<T>();
        }

        int depth = parser.getDepth();

        do
        {

            if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals(name))
            {
                list.add(tagParser.parse(parser));
            }

            ParsingUtils.findNextStartTag(parser);

        }
        while (parser.getDepth() >= depth);

        return list;
    }
}
