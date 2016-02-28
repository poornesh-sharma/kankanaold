package com.kankana.kankana;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment{
    String[] mTestArray;
    TextView textView;
    final android.os.Handler myHandler = new android.os.Handler();

    public HomeFragment() {
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
        updateTextView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void updateTextView() {
        textView = (TextView)getView().findViewById(R.id.HomeText);
        Random random = new Random();
        mTestArray = getResources().getStringArray(R.array.kankana_slogans);
        int maxIndex = mTestArray.length;
        int generatedIndex = random.nextInt(maxIndex);

        textView.setText(mTestArray[generatedIndex]);
        textView.startAnimation(AnimationUtils.loadAnimation(((MainActivity) getActivity()), android.R.anim.slide_in_left));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void myClickMethod(View v) {
        switch(v.getId()){
            case R.id.facebookImage:
                Intent facebookIntent = getOpenFacebookIntent(getActivity());
                startActivity(facebookIntent);
                break;
            case R.id.emailIdImage:
                Intent emailIntent = getEmailIntent(getActivity());
                startActivity(emailIntent);
                break;
            case R.id.webSIteImage:
                Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://kankana.org/"));
                startActivity(browserIntent);
                break;
            case R.id.phoneImage:
                String phone = "+919945742076";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                break;
            case R.id.twitterImage:
                Intent twitterIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/kannadakankana"));
                startActivity(twitterIntent);
                break;
            case R.id.googlePlusImage:
                Intent gPlusIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://plus.google.com/u/0/100316414644558079466"));
                startActivity(gPlusIntent);
                break;
        }
    }

    public static Intent getOpenFacebookIntent(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1526059707617175")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/naadunudigagikankana")); //catches and opens a url to the desired page
        }
    }

    public static Intent getEmailIntent(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"kannadakankana@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Interested in joining");
        intent.putExtra(Intent.EXTRA_TEXT, "Please accept");
        Intent mailer = Intent.createChooser(intent, null);
        return mailer;
    }
}
