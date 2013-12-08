package com.activity.se_conference;


import java.util.ArrayList;



import java.util.List;
import myViews.PDFOutlineElement;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Info_Fragment extends Fragment{
	private View view=null;
	private TreeViewAdapter treeViewAdapter = null;
	ListView listView;
	private ArrayList<PDFOutlineElement> mPdfOutlinesCount = new ArrayList<PDFOutlineElement>();
	private ArrayList<PDFOutlineElement> mPdfOutlines = new ArrayList<PDFOutlineElement>();
	String[] info={"Travel Information","Hotel Information","Previous Conference"};
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {  
		view=inflater.inflate(R.layout.info_fragment, container, false);
		listView = (ListView) view.findViewById(R.id.list);
        initialData();
        treeViewAdapter = new TreeViewAdapter(getActivity(), R.layout.myoutline,mPdfOutlinesCount);
        listView.setAdapter(treeViewAdapter);
        listView.setOnItemClickListener(
				new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
							// TODO Auto-generated method stub
							if ((!mPdfOutlinesCount.get(arg2).isMhasChild())&&(!mPdfOutlinesCount.get(arg2).isMhasParent())) {
								return;
							}
							

							if(!mPdfOutlinesCount.get(arg2).isMhasParent()){
								String clickName=mPdfOutlinesCount.get(arg2).getOutlineTitle();
								if(clickName.equals("Travel Information")){
									Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.icsp-conferences.org/icssp2014/Travel.html"));
									it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
									startActivity(it);
								}
								if(clickName.equals("Previous Conference")){
									Intent it = new Intent(getActivity(),Previous_conference.class);
									startActivity(it);
								}
								if (mPdfOutlinesCount.get(arg2).isExpanded()) {
									mPdfOutlinesCount.get(arg2).setExpanded(false);
									PDFOutlineElement pdfOutlineElement=mPdfOutlinesCount.get(arg2);
									ArrayList<PDFOutlineElement> temp=new ArrayList<PDFOutlineElement>();
									
									for (int i = arg2+1; i < mPdfOutlinesCount.size(); i++) {
										if (pdfOutlineElement.getLevel()>=mPdfOutlinesCount.get(i).getLevel()) {
											break;
										}
										temp.add(mPdfOutlinesCount.get(i));
									}
									
									mPdfOutlinesCount.removeAll(temp);
									
									treeViewAdapter.notifyDataSetChanged();
									
								} else {
									mPdfOutlinesCount.get(arg2).setExpanded(true);
									int level = mPdfOutlinesCount.get(arg2).getLevel();
									int nextLevel = level + 1;
									

									for (PDFOutlineElement pdfOutlineElement : mPdfOutlines) {
										int j=1;
										if (pdfOutlineElement.getParent()==mPdfOutlinesCount.get(arg2).getId()) {
											pdfOutlineElement.setLevel(nextLevel);
											pdfOutlineElement.setExpanded(false);
											mPdfOutlinesCount.add(arg2+j, pdfOutlineElement);
											j++;
										}			
									}
									treeViewAdapter.notifyDataSetChanged();
								}
								
							}else{
								String clickName=mPdfOutlinesCount.get(arg2).getOutlineTitle();
								if(clickName.equals("Golden Eagle Summit Hotel")){
									Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.goldstar-hotel.com"));
									it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
									startActivity(it);
								}
								if(clickName.equals("Zhongshan Hotel")){
									Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.zhongshan-hotel.com"));
									it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
									startActivity(it);
								}
								if(clickName.equals("Haoya Hotel")){
									Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hotelscombined.cn"));
									it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
									startActivity(it);
								}
								if(clickName.equals("Home Inn")){
									Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.homeinns.com"));
									it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
									startActivity(it);
								}
							}
						}
				}
		);
		
        return  view; 

	}
	private void initialData() {
		PDFOutlineElement pdfOutlineElement1=new PDFOutlineElement("01", "Travel Information", false	, true, "00", 0,true);
		PDFOutlineElement pdfOutlineElement2=new PDFOutlineElement("02", "Hotel Information", false	, true, "00", 0,true);
		PDFOutlineElement pdfOutlineElement3=new PDFOutlineElement("03", "Previous Conference", false	, true, "00", 0,true);
		PDFOutlineElement pdfOutlineElement4=new PDFOutlineElement("04", "Golden Eagle Summit Hotel", true	, false, "02", 1,false);
		PDFOutlineElement pdfOutlineElement5=new PDFOutlineElement("05", "Zhongshan Hotel", true	, false, "02", 1,false);
		PDFOutlineElement pdfOutlineElement6=new PDFOutlineElement("06", "Haoya Hotel", true	, false, "02", 1,false);
		PDFOutlineElement pdfOutlineElement7=new PDFOutlineElement("07", "Home Inn", true	, false, "02", 1,false);
		
		mPdfOutlinesCount.add(pdfOutlineElement1);
		mPdfOutlinesCount.add(pdfOutlineElement2);
		mPdfOutlinesCount.add(pdfOutlineElement4);
		mPdfOutlinesCount.add(pdfOutlineElement5);
		mPdfOutlinesCount.add(pdfOutlineElement6);
		mPdfOutlinesCount.add(pdfOutlineElement7);
		mPdfOutlinesCount.add(pdfOutlineElement3);
		
		mPdfOutlines.add(pdfOutlineElement1);
		mPdfOutlines.add(pdfOutlineElement2);
		mPdfOutlines.add(pdfOutlineElement4);
		mPdfOutlines.add(pdfOutlineElement5);
		mPdfOutlines.add(pdfOutlineElement6);
		mPdfOutlines.add(pdfOutlineElement7);
		mPdfOutlines.add(pdfOutlineElement3);

		
		
		
	}


	

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
    }   
    
    
	private class TreeViewAdapter extends ArrayAdapter {

		public TreeViewAdapter(Context context, int textViewResourceId,
				List objects) {
			super(context, textViewResourceId, objects);
			mInflater = LayoutInflater.from(context);
			mfilelist = objects;
			mIconCollapse = BitmapFactory.decodeResource(
					context.getResources(), R.drawable.outline_list_collapse);
			mIconExpand = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.outline_list_expand);
			this.textviewresourceId=textViewResourceId;

		}

		private LayoutInflater mInflater;
		private List<PDFOutlineElement> mfilelist;
		private Bitmap mIconCollapse;
		private Bitmap mIconExpand;
		private int textviewresourceId;


		public int getCount() {
			return mfilelist.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			/*if (convertView == null) {*/
				convertView = mInflater.inflate(textviewresourceId, null);
				holder = new ViewHolder();
				if(mfilelist.get(position).isMhasParent())
					holder.text = (TextView) convertView.findViewById(R.id.child);
				else
					holder.text = (TextView) convertView.findViewById(R.id.text);
				holder.icon = (ImageView) convertView.findViewById(R.id.icon);
				convertView.setTag(holder);
			/*} else {
				holder = (ViewHolder) convertView.getTag();
			}*/

			int level = mfilelist.get(position).getLevel();
 			holder.icon.setPadding(25 * (level + 1), holder.icon
					.getPaddingTop(), 0, holder.icon.getPaddingBottom());
			holder.text.setText(mfilelist.get(position).getOutlineTitle());
			if (mfilelist.get(position).isMhasChild()
					&& (mfilelist.get(position).isExpanded() == false)) {
			} else if (mfilelist.get(position).isMhasChild()
					&& (mfilelist.get(position).isExpanded() == true)) {
			} else if (!mfilelist.get(position).isMhasChild()){
				holder.icon.setImageBitmap(mIconCollapse);
				holder.icon.setVisibility(View.INVISIBLE);
			}
			return convertView;
		}

		class ViewHolder {
			TextView text;
			ImageView icon;

		}
	}
}
