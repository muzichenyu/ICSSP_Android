package com.activity.se_conference;


import java.util.ArrayList;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Info_Fragment extends Fragment{
	private View view=null;
	ListView listView;
	String[] info={"Travel Information","Hotel Information","Previous Conference"};
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {  
		view=inflater.inflate(R.layout.info_fragment, container, false);
		listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),  
                R.layout.list_layout, info));//listview_item
        listView.setCacheColorHint(0);
        
        listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String clickName=(String) listView.getItemAtPosition(arg2);
				 if(clickName.equals("Travel Information")){
					 Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.icsp-conferences.org/icssp2014/Travel.html"));

					it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
					startActivity(it);
				 }
				 if(clickName.equals("Previous Conference")){
/*					Intent intent = new Intent(getActivity(),Previous_conference.class);
			         startActivity(intent);*/
				 }
            }
        });
        return view; 

	}
}
