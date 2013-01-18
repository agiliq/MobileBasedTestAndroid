package com.agiliq.exam;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InteractiveArrayAdapter extends ArrayAdapter<String> {

	String tag = "Events";

	private final List<String> list;
	private final Context context;

	public InteractiveArrayAdapter(Context context, List<String> list) {
		super(context, R.layout.mbt_test, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView question_number;
		protected TextView question;
		protected CheckBox checkbox;
		protected RadioGroup mgroup;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(tag," 3");
		View view = convertView;
		ViewHolder viewHolder = null;
		RadioButton[] mbutton = null;
		if (view == null) {
			
			LayoutInflater inflator = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.mbt_test, null);
			
			viewHolder = new ViewHolder();
			viewHolder.question_number = (TextView) view.findViewById(R.id.question_no);
			viewHolder.question = (TextView) view.findViewById(R.id.question_no);
			//viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			//viewHolder.checkbox1 = (CheckBox) view.findViewById(R.id.checkbox1);
			viewHolder.mgroup = (RadioGroup) view.findViewById(R.id.radio_group);

			// I'm not sure about this..
			// Begin
			mbutton=new RadioButton[5];
			for(int l=0;l<5;l++){
				mbutton[l]=new RadioButton(context);
				mbutton[l].setText("test"+l);

				viewHolder.mgroup.addView(mbutton[l]);

			}

			viewHolder.mgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				public void onCheckedChanged(RadioGroup mRadioGroup, int checkedId) {
					for(int i=0; i<mRadioGroup.getChildCount(); i++) {
						RadioButton btn = (RadioButton) mRadioGroup.getChildAt(i);
						//int t=table.indexOfChild(table_row);
						//System.out.println(t);
						int t=mRadioGroup.getId();
						System.out.println(t);

						if(btn.getId() == checkedId) {
							String text = btn.getText().toString();
							// do something with text
							Log.d(text," event1");
							return;
						}
					}
				}
			});
			// End 

			view.setTag(viewHolder);
			Log.d(tag,"me");

		} else {
			
	
			viewHolder = (ViewHolder) convertView.getTag();

			// Begin
			//mbutton = viewHolder.mgroup;
			// End

			Log.d(tag,"meeee");

		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.question.setText(list.get(position));

		Log.d(tag," event7");

		return view;

	}
}