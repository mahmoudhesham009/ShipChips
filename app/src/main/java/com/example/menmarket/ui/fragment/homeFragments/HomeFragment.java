package com.example.menmarket.ui.fragment.homeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.menmarket.R;
import com.example.menmarket.ui.acivity.ProductRecyclerViewActivity;
import com.example.menmarket.ui.fragment.BaseFragment;

public class HomeFragment extends BaseFragment {
    GridLayout gridLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inif();
        View v=inflater.inflate(R.layout.fragment_home,container,false);
        gridLayout=v.findViewById(R.id.mainGrid);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for(int i=0; i<gridLayout.getChildCount();i++){
            final CardView cardView=(CardView) gridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardView.getId()==R.id.cncCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","cnc");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.printerCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","3D printer");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.motorCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","motors");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.embeddedCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","embedded");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.arduinoCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","arduino");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.icCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","ic");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.pcbCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","pcb");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }else if (cardView.getId()==R.id.toolsCard){
                        Intent mainIntent = new Intent(getActivity(), ProductRecyclerViewActivity.class);
                        Bundle dataBundle = new Bundle();
                        dataBundle.putString("category","tools");
                        mainIntent.putExtras(dataBundle);
                        startActivity(mainIntent);

                    }
                }
            });
        }
    }
}
