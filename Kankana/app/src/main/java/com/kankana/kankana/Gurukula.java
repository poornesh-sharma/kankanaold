package com.kankana.kankana;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Gurukula extends Fragment {
    private Spinner spinner;
    private ListView lv;
    private SimpleAdapter simpleAdapter;

    public Gurukula() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gurukula, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinner = (Spinner) getView().findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lv = (ListView) getView().findViewById(R.id.listView1);

                ArrayList<HashMap<String, String>> feedList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map;

                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                SQLiteDatabase qdb = dbHelper.getReadableDatabase();
                Cursor definitionRecords;
                if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("All")) {
                    definitionRecords = qdb.rawQuery("SELECT * FROM alldefinitions", null);
                } else {
                    definitionRecords = qdb.rawQuery("SELECT * FROM alldefinitions WHERE category='" + parent.getItemAtPosition(position).toString() + "'", null);
                }

                if (definitionRecords != null) {
                    if (definitionRecords.moveToFirst()) {
                        do {
                            String english = definitionRecords.getString(0);
                            String kannada = definitionRecords.getString(1);
                            String englishkannada = definitionRecords.getString(2);
                            map = new HashMap<String, String>();
                            map.put("english", english);
                            map.put("englishkannada", englishkannada);
                            feedList.add(map);
                        } while (definitionRecords.moveToNext());
                    }
                }

                simpleAdapter = new SimpleAdapter(getActivity(), feedList, R.layout.gurukula_item, new String[]{"english", "englishkannada"}, new int[]{R.id.textViewEnglish, R.id.textViewEnglishKannada});
                lv.setAdapter(simpleAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //EditText searchTextBox = (EditText) getView().findViewById(R.id.inputSearch);
        SearchView searchView = (SearchView) getView().findViewById(R.id.searchView);
        searchView.setSelected(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                simpleAdapter.getFilter().filter(query);
                lv.setAdapter(simpleAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                simpleAdapter.getFilter().filter(newText);
                lv.setAdapter(simpleAdapter);
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
