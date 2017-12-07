package com.IITI.fluxus14;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ResourceAsColor")
public class FragmentContact extends SherlockFragment {
	LinearLayout ll;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_contact, container,
				false);
		setupVars(view);
		return view;
	}
	
	private class ContactGroup{
		String title;
		String[] names;
		public ContactGroup(String a,String[] n){
			title=a;
			names=n;
		}
	}

	@SuppressLint("ResourceAsColor")
	protected void setupVars(View view) {
		ll = (LinearLayout) view.findViewById(R.id.llContact);
		MyTextView mt;
		List<ContactGroup> contacts = new ArrayList<ContactGroup>();
		contacts.add(new ContactGroup("Overall Coordinators",new String[]{"Vibhor Pandhare +91 xx xx xxx xxx","Anupreet Gupta +91 xx xx xxx xxx"}));
		contacts.add(new ContactGroup("Technical Head",new String[]{"Dharmil Chandarana +91 xx xx xxx xxx","Yogesh Dorbala +91 xx xx xxx xxx"}));
		contacts.add(new ContactGroup("Cultural Head",new String[]{"Kaushik Barodiya +91 xx xx xxx xxx","Deepak Sattiraju +91 xx xx xxx xxx"}));
		contacts.add(new ContactGroup("Social Coordinator",new String[]{"Deepak Raj +91 xx xx xxx xxx"}));
		contacts.add(new ContactGroup("PR Manager",new String[]{"Vaibhav Jain +91 xx xx xxx xxx"}));
		contacts.add(new ContactGroup("Faculty Coordinators",new String[]{"Dr. Antony Vijesh Dean, Student Affairs","Dr.I.A. Palani Mech. Engg. Dept."}));
		for (int i = 0; i < contacts.size(); i++) {
			mt = new MyTextView(getSherlockActivity());
			mt.setText(contacts.get(i).title);
			mt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
			mt.setTextSize(30);
			mt.setTextColor(getResources().getColor(R.color.bluetext));
			ll.addView(mt);
			for (int j = 0; j < contacts.get(i).names.length; j++) {
				mt = new MyTextView(getSherlockActivity());
				mt.setAutoLinkMask(Linkify.ALL);
				mt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
				mt.setPadding(20, 0, 0, 0);
				mt.setTextSize(22);
				mt.setText(contacts.get(i).names[j]);
				ll.addView(mt);
			}
		}
	}
}
