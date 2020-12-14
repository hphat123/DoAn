package com.example.ungdungdocbao;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import drawer.Item_dr_adapter;
import drawer.Item_drawer;
import xml.XML_Asyntask;
import xml.XML_Asyntask_Search;

import static com.example.ungdungdocbao.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements Item_dr_adapter.OnClickItemListner, AdapterView.OnItemClickListener{
    public static final String LINK = "link";
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private RecyclerView recycler_drawer;
    private ListView listView;
    private ItemAdapter itemAdapter;
    private Item_dr_adapter item_dr_adapter;
    private ArrayList<Item> arrItem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        initView();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String link = "https://www.24h.com.vn/upload/rss/bongda.rss";
        XML_Asyntask asyntask = new XML_Asyntask(handler,MainActivity.this);
        asyntask.execute(link);
    }

    private void initView() {
        recycler_drawer = (RecyclerView) findViewById(R.id.recycler_Drawer);
        item_dr_adapter = new Item_dr_adapter(this);
        recycler_drawer.setItemAnimator(new DefaultItemAnimator());
        recycler_drawer.setLayoutManager(new LinearLayoutManager(this));
        recycler_drawer.setAdapter(item_dr_adapter);
        item_dr_adapter.setOnClickItemListener(this);

        listView = (ListView) findViewById(R.id.list_item);
        itemAdapter = new ItemAdapter(this,arrItem);
        listView.setAdapter( itemAdapter);
        listView.setOnItemClickListener(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String key = Uri.encode(query);
                String link = "https://news.google.de/news/feeds?pz=1&cf=vi_vn&ned=vi_vn&hl=vi_vn&q=" + key;
                arrItem.clear();
                XML_Asyntask_Search asyntask_search = new XML_Asyntask_Search(handler,MainActivity.this);
                asyntask_search.execute(link);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClick(Item_drawer item_drawer) {
        itemAdapter.clear();
        String link = item_drawer.getUrl().toString().trim();
        XML_Asyntask asyntask = new XML_Asyntask(handler,MainActivity.this);
        asyntask.execute(link);
        drawerLayout.closeDrawers();
    }

    private Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == XML_Asyntask.WHAT_NEWS) {
                arrItem.addAll((ArrayList<Item>) msg.obj);
            }
            itemAdapter.notifyDataSetChanged();
            // nhận dữ liệu từ asyntask
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String link = arrItem.get(i).getLink();
        Intent intent = new Intent(this,WebViewActivity.class);
        intent.putExtra(LINK,link);
        startActivity(intent);
    }
}