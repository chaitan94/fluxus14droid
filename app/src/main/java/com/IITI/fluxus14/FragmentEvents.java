package com.IITI.fluxus14;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentEvents extends SherlockFragment implements OnItemClickListener{
	static LinearLayout llEvents;
	static ListView lvEventTypes;

	static int level = 0;
	static int typeOfEvent = -1;
	List<List<String>> events;
	static List<ListView> evtls;

	@SuppressLint("Recycle")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Resources res = getResources();
		events = new ArrayList<List<String>>();
		TypedArray evts = res.obtainTypedArray(R.array.eventTypesIdArr);
		for (int i = 0; i < evts.length(); i++) {
			int tres = evts.getResourceId(i, 0);
			String[] tarr = res.getStringArray(tres);
			events.add(Arrays.asList(tarr));
		}
		evtls = new ArrayList<ListView>();
		
		View view = inflater.inflate(R.layout.fragment_events, container, false);
		setupVars(view);
		final Animation listin = AnimationUtils.loadAnimation(view.getContext(), R.anim.listin);
		final Animation listoutrev = AnimationUtils.loadAnimation(view.getContext(), R.anim.listoutrev);
		lvEventTypes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (typeOfEvent != position) {
					if (level == 1) {
						llEvents.getChildAt(1).startAnimation(listoutrev);
						llEvents.removeViewAt(1);
					}
					level = 1;
					typeOfEvent = position;
					llEvents.addView(evtls.get(position));
					evtls.get(position).startAnimation(listin);
				}
			}
		});
		return view;
	}

	protected void setupVars(View view) {
		llEvents = (LinearLayout) view.findViewById(R.id.llEvents);
		lvEventTypes = (ListView) view.findViewById(R.id.lvEventTypes);

		for (int i = 0; i < events.size(); i++) {
			ListView a = new ListView(getSherlockActivity());
			a.setAdapter(new ArrayAdapter<String>(getSherlockActivity(), R.layout.evt_list_item, events.get(i)));
			a.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,(float) 2.0));
			a.setOnItemClickListener(this);
			evtls.add(a);
		}
		lvEventTypes.setAdapter(new ArrayAdapter<String>(getSherlockActivity(), R.layout.evt_list_item, getResources().getStringArray(R.array.eventTypes)));
	}

	public static void onBack(Animation listoutrev) {
		evtls.get(typeOfEvent).startAnimation(listoutrev);
		llEvents.removeView(evtls.get(typeOfEvent));
		level = 0;
		typeOfEvent = -1;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
		Bundle basket = new Bundle();
		basket.putInt("typeEvent", typeOfEvent);
		basket.putInt("key", position);
		try {
			@SuppressWarnings("unchecked")
			Class<Activity> ourClass = (Class<Activity>) Class.forName("com.IITI.fluxus14.EventDialog");
			Intent ourIntent = new Intent(arg0.getContext(), ourClass);
			ourIntent.putExtras(basket);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
