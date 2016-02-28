package com.kankana.kankana;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private Gurukula gurukula;
    private AboutUs aboutUs;
    private OurTeam ourTeam;
    private Programes programes;

    private GoogleApiClient client;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        //CustomList adapter = new CustomList(MainActivity.this, fragments, imageId);
        navList = (ListView) findViewById(R.id.navList);
        myAdapter = new MyAdapter(this);
        navList.setAdapter(myAdapter);
        //navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        navList.setOnItemClickListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();

        loadSelection(0);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void loadSelection(int position) {
        navList.setItemChecked(position, true);


        switch (position) {
            case 0:
                homeFragment = new HomeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                fragmentTransaction.commit();
                this.setTitle(getString(R.string.HometitleText));
                break;
            case 1:
                aboutUs = new AboutUs();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, aboutUs);
                fragmentTransaction.commit();
                this.setTitle(getString(R.string.AboutUsTitleText));
                break;
            case 2:
                ourTeam = new OurTeam();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, ourTeam);
                fragmentTransaction.commit();
                this.setTitle(getString(R.string.OurTeamTitleText));
                break;
            case 3:
                programes = new Programes();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, programes);
                fragmentTransaction.commit();
                this.setTitle(getString(R.string.ProgramesTitleText));
                break;
            case 4:
                gurukula = new Gurukula();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, gurukula);
                fragmentTransaction.commit();
                this.setTitle(getString(R.string.GurukulaHeaderTitleText));
                break;
            case 5:
                break;
        }
    }

    public void myClickMethod(View v) {
        homeFragment.myClickMethod(v);
    }

    public void btnRemoveClick(View v)
    {
        programes.btnRemoveClick(v);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(navList)) {
                drawerLayout.closeDrawer(navList);
            } else {
                drawerLayout.openDrawer((navList));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayout.closeDrawer(navList);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://kankana.kankana/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://kankana.kankana/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

class MyAdapter extends BaseAdapter{
    private Context context;
    String[] fragments = {
            "Home (ಮುಖಪುಟ)",
            "About Us (ನಮ್ಮ ಬಗ್ಗೆ)",
            "Our Team (ನಮ್ಮ ತಂಡ)",
            "Programes (ಕಾರ್ಯಕ್ರಮಗಳು)",
            "Gurukula (ಗುರುಕುಲ)"
    };

    Integer[] imageId = {
            R.drawable.home_color_50,
            R.drawable.abouts_us_50,
            R.drawable.our_team_50,
            R.drawable.planner_color_50,
            R.drawable.gurukula_color_50
    };

    public MyAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Object getItem(int position) {
        return fragments[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.navlist_items, parent, false);
        } else {
            row = convertView;
        }
        TextView titleTextView = (TextView) row.findViewById(R.id.itemListText);
        ImageView titleImageView = (ImageView) row.findViewById(R.id.iconImage);
        titleTextView.setText(fragments[position]);
        titleImageView.setImageResource(imageId[position]);

        return row;
    }
}





