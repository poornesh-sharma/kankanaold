package com.kankana.kankana;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OurTeam extends Fragment {

    private ListView lv;
    private SimpleAdapter simpleAdapter;

    private String[] teamTitle = {
            "ಕಾವೇರಿ",
            "ಕೃಷ್ಣಾ",
            "ಶರಾವತಿ",
            "ತುಂಗಾ",
            "ಭದ್ರಾ"
    };

    private String[] sanchalakaraName = {
            "ಪ್ರಕಾಶ್ ರಾಜು",
            "ಬ್ರಮೇಶ್",
            "ಪೃಥ್ವಿ ಕಿರಣ್",
            "ಗಣೇಶ",
            "ಯದುನಂದನ"
    };

    private String[] sahaSanchalakaraNames = {
            "ವಿಶ್ವೇಶ್ವರಯ್ಯ",
            "ಶ್ರೀಧರ್",
            "ಅರುಂಧತಿ ಪೈ",
            "ಅಂಜೂರ ಕೆ ವಿ",
            "ದಿನೇಶ್"
    };

    private String[] sanchalakaraNo = {
            "9738774619, 9880155277",
            "9986209097, 9844557665",
            "9964895332, 9618149185",
            "9945611656, 9483481932",
            "8971775817, 9036898226"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_our_team, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv = (ListView) getView().findViewById(R.id.teamList);
        ArrayList<HashMap<String, String>> feedList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map;
        for(int i=0; i<teamTitle.length; i++) {
            map = new HashMap<String, String>();
            map.put("teamTitle", teamTitle[i]);
            map.put("sanchalakaruTitle", "ಸಂಚಾಲಕರು");
            map.put("sanchalakaruText", sanchalakaraName[i]);
            map.put("sahaSanchalakaruTitle", "ಸಹ ಸಂಚಾಲಕರು");
            map.put("sahaSanchalakaruText", sahaSanchalakaraNames[i]);
            map.put("phoneNoText", sanchalakaraNo[i]);
            feedList.add(map);
        }

        simpleAdapter = new SimpleAdapter(getActivity(), feedList, R.layout.ourteam_layout_item, new String[]{"teamTitle", "sanchalakaruTitle", "sanchalakaruText", "sahaSanchalakaruTitle", "sahaSanchalakaruText", "phoneNoText"}, new int[]{R.id.teamTitle, R.id.sanchalakaruTitle, R.id.sanchalakaruText, R.id.sahaSanchalakaruTitle, R.id.sahaSanchalakaruText, R.id.phoneNoText});
        lv.setAdapter(simpleAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
