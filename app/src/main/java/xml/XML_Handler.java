package xml;

import android.util.Log;

import com.example.ungdungdocbao.Item;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XML_Handler extends DefaultHandler {
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
                String description = builder.substring(builder.indexOf("src = '") + 5);
                Log.e("des",description);
                String img1 = "src=";
                String img2 = builder.toString();
                img2 = img2.substring(img2.indexOf(img1)+img1.length()+3);
                String img = "ht" + img2.substring(0, img2.indexOf("alt=") - 2);
                Log.e("link img",img);
                description = description.substring(description.indexOf("<br />") + 6);
                item.setImg(img);
                item.setDescription(description);
                break;
            case LINK:
                String link = "http" + builder.substring(builder.indexOf("url=") + 6);
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
