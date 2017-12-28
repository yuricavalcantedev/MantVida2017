package com.heavendevelopment.mantvida2018.Activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heavendevelopment.mantvida2018.R;

/**
 * Created by yuri on 27/12/17.
 */

public class LeituraTardeFragment extends Fragment {


    public LeituraTardeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.leitura_tarde_fragment, container, false);


        return view;
    }
}
