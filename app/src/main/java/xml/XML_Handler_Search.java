package xml;

import android.util.Log;

import com.example.ungdungdocbao.Item;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XML_Handler_Search extends DefaultHandler {
    private ArrayList<Item> arrItems = new ArrayList<>();
    private Item item;

    public static final String ITEM = "item";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String LINK = "link";
    public static final String PUB_DATE = "pubDate";
    private StringBuilder builder;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        builder = new StringBuilder();
        if (qName.equals(ITEM)) {
            item = new Item();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (item == null) {
            return;
        }
        switch (qName) {
            case ITEM:
                arrItems.add(item);
                break;
            case TITLE:
                item.setTitle(builder.toString());
                break;
            case DESCRIPTION:
                builder.length();
                String s1 = "<img src=";
                String s = builder.toString();
                Log.e("des", s);
                s = s.substring(s.indexOf(s1) + s1.length() + 3);
                String img = "http://" + s.substring(0, s.indexOf("alt=") - 2);
                String s2 = "</font></b></font><br><font size=\"-1\">";
                s = s.substring(s.indexOf(s2) + s2.length());
                String desc = s.substring(0, s.indexOf("</font>"));
                desc = desc.replace("<b>", "");
                desc = desc.replace("</b>", "");
                desc = desc.replace("<&nbsp>", "");
                item.setImg(img);
                item.setDescription(desc);
                break;
            case LINK:
                String link = builder.substring(builder.indexOf("url=") + 4);
                item.setLink(link);
                break;
            case PUB_DATE:
                item.setPubdate(builder.toString());
                break;
        }
    }

    public ArrayList<Item> getArrItems() {
        return arrItems;
    }
}
