package com.google.code.xpull.parser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;

import com.google.code.xpull.ITagHandler;

@SuppressLint("SimpleDateFormat")
public class DateTagParser extends BaseTagParser<Date>
{

    private DateFormat dateFormat;

    public DateTagParser()
    {
        this("yyyy-MM-dd");
    }

    public DateTagParser(ITagHandler<Date> handler)
    {
        this(handler, "yyyy-MM-dd");
    }

    public DateTagParser(String datePattern)
    {
        this(null, datePattern);
    }

    public DateTagParser(ITagHandler<Date> handler, String datePattern)
    {
        super(handler);
        dateFormat = new SimpleDateFormat(datePattern);
    }

    @Override
    protected Date doParse(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        Date date = null;

        try
        {
            date = dateFormat.parse(parser.nextText());
        }
        catch (ParseException e)
        {
        }

        return date;
    }

}
