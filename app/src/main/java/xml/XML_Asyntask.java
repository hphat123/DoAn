package xml;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.ungdungdocbao.Item;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XML_Asyntask extends AsyncTask<String,Void, ArrayList<Item>> {
   public static final int   WHAT_NEWS = 1 ;
    private Handler handler;
    private ProgressDialog progressDialog;

    public XML_Asyntask(Handler handler, Activity activity) {
        this.handler = handler;
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    public XML_Asyntask() {

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected ArrayList<Item> doInBackground(String... strings) {
        ArrayList<Item> arrItem = new ArrayList<>();
        String link = strings[0];
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XML_Handler xml_Handler = new XML_Handler();
            parser.parse(link, xml_Handler);
            arrItem = xml_Handler.getArrItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrItem;
    }
    @Override
    protected void onPostExecute(ArrayList<Item> items) {
        super.onPostExecute(items);
        Log.e("sizeAsyc = ",items.size()+"");
        Message message = new Message();
        message.what = WHAT_NEWS;
        message.obj = items;
        handler.sendMessage(message);
        progressDialog.dismiss();
    }
}
