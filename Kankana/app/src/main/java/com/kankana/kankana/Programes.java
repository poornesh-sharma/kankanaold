package com.kankana.kankana;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Programes extends Fragment {
    private ListView lv;
    private SimpleAdapter simpleAdapter;

    private String[] abhiyaanaPhotosLink = {
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibcGFrRzMxYnBiX0k",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibODlmNlpOanR2aVE",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibWHVlZVZkb2RkbzQ",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibSnNRc1ZvLVZnUkU",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibdGpTdmZqRjdZNk0",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibY3pnTW56WnJjMDA",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibOXYxMWRHNnBMbzg",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibME9yaEd4aEZIcXM",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibQXk3WlBDbW1QUk0",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibd2RMakVrSVl5c2s",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibQTEwc2ZWVEpSYVE",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibQ2w5TDlWc3d0WG8",
            "https://drive.google.com/drive/folders/0B-qo4nTQz9ibT3p6Tk1pUkZ3Vjg"
    };

    private Integer[] abhiyaanaThumbNails = {
            R.drawable.gallery1,
            R.drawable.gallery2,
            R.drawable.gallery3,
            R.drawable.gallery4,
            R.drawable.gallery5,
            R.drawable.gallery6,
            R.drawable.gallery7,
            R.drawable.gallery8,
            R.drawable.gallery9,
            R.drawable.gallery10,
            R.drawable.gallery11,
            R.drawable.gallery12,
            R.drawable.gallery13
    };

    private String[] locationList = {
            "ಜೆ ಪಿ ನಗರ, ಸೆಂಟ್ರಲ್ ಮಾಲ್",
            "ಜಯನಗರ",
            "ಲಾಲ್ ಬಾಗ್",
            "ಮಲ್ಲೇಶ್ವರಂ ಸಂಪಿಗೆ ರಸ್ತೆ",
            "ಜೆ ಪಿ ಉದ್ಯಾನವನ, ಮತ್ತಿಕೆರೆ",
            "ಬನಶಂಕರಿ ಬಿಡಿಎ ವಾಣಿಜ್ಯ ಸಂಕೀರ್ಣ",
            "ಎಂ ಜಿ ರಸ್ತೆ, ಸೆಂಟ್ರಲ್ ಮಾಲ್",
            "ಸ್ಯಾಂಕಿ ಟ್ಯಾಂಕ್, ಸದಾಶಿವನಗರ",
            "ಲಾಲ್ ಬಾಗ್",
            "ಕೃಷ್ಣ ರಾವ್ ಉಧ್ಯಾನವ, ಬಸವನಗುಡಿ",
            "ಬಿಗ್ ಬಜ಼ಾರ್ ರಸ್ತೆ, ಕತ್ರಿಗುಪ್ಪೆ",
            "ಲಾಲ್ ಬಾಗ್",
            "ಸೋನಿ ವರ್ಲ್ಡ್ ಹತ್ತಿರ, ಕೋರಮಂಗಲ"
    };

    private String[] abhiyaanaDates = {
            "23ನೇ ನವೆಂಬರ್ 2014",
            "21ನೇ ಡಿಸೆಂಬರ್ 2014",
            "11ನೇ ಜನವರಿ 2015",
            "8ನೇ ಮಾರ್ಚ್ 2015",
            "19ನೇ ಏಪ್ರಿಲ್ 2015",
            "31ನೇ ಮೇ 2015",
            "28ನೇ ಜೂನ್ 2015",
            "26ನೇ ಜುಲೈ 2015",
            "16ನೇ ಆಗಸ್ಟ್ 2015",
            "27ನೇ ಸೆಪ್ಟೆಂಬರ್ 2015",
            "18ನೇ ಅಕ್ಟೋಬರ್ 2015",
            "24 ನೇ ಜನವರಿ 2016",
            "28 ನೇ ಫೆಬ್ರವರಿ 2016"
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programes, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv = (ListView) getView().findViewById(R.id.programsList);
        ArrayList<HashMap<String, String>> feedList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map;
        for(int i=abhiyaanaThumbNails.length-1; i>=0; i--) {
            map = new HashMap<String, String>();
            map.put("imageSrc", abhiyaanaThumbNails[i].toString());
            map.put("locationText", locationList[i]);
            map.put("abhiyaanaDate", abhiyaanaDates[i]);
            map.put("viewPhotosButtonText", "View Photos in Web");
            feedList.add(map);
        }

        simpleAdapter = new SimpleAdapter(getActivity(), feedList, R.layout.programes_layout_item, new String[]{"imageSrc", "locationText", "abhiyaanaDate", "viewPhotosButtonText"}, new int[]{R.id.thumbnailImage, R.id.locationText, R.id.abiyaanaDate, R.id.viewPhotosButton});
        lv.setAdapter(simpleAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void btnRemoveClick(View v)
    {
        final int position = lv.getPositionForView((View) v.getParent());
        //listItem.remove(position);
        //ItemAdapter.notifyDataSetChanged();

        GridLayout vwParentRow = (GridLayout)v.getParent();

        TextView child = (TextView)vwParentRow.getChildAt(2);
        Button btnChild = (Button)vwParentRow.getChildAt(3);
        Uri uri = Uri.parse(abhiyaanaPhotosLink[abhiyaanaThumbNails.length - 1 - position]); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
