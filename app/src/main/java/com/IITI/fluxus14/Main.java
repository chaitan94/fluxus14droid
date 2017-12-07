package com.IITI.fluxus14;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class Main extends SherlockFragmentActivity {
	ActionBar actionBar;
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.pager);
		mViewPager.setBackgroundResource(R.drawable.bg);
		setContentView(mViewPager);
		actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mTabsAdapter = new TabsAdapter(this, mViewPager);

		mTabsAdapter.addTab(actionBar.newTab().setText("Fluxus"),FragmentFluxus.class, null);
		mTabsAdapter.addTab(actionBar.newTab().setText("Events"),FragmentEvents.class, null);
		mTabsAdapter.addTab(actionBar.newTab().setText("Contacts"),FragmentContact.class, null);
	}

	public static class TabsAdapter extends FragmentPagerAdapter implements
			ActionBar.TabListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args) {
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getSupportActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
			TabInfo info = new TabInfo(clss, args);
			tab.setTag(info);
			tab.setTabListener(this);
			mTabs.add(info);
			mActionBar.addTab(tab);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
		
		@Override
		public void onTabSelected(Tab tab,
				android.support.v4.app.FragmentTransaction ft) {
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++) {
				if (mTabs.get(i) == tag) {
					mViewPager.setCurrentItem(i);
				}
			}
		}

		@Override
		public void onTabUnselected(Tab tab,
				android.support.v4.app.FragmentTransaction ft) {
		}

		@Override
		public void onTabReselected(Tab tab,
				android.support.v4.app.FragmentTransaction ft) {
		}
	}

	@Override
	public void onBackPressed() {
		final Animation listoutrev = AnimationUtils.loadAnimation(this,
				R.anim.listoutrev);
		if (FragmentEvents.level == 1
				&& actionBar.getSelectedTab().getPosition() == 1) {
			FragmentEvents.onBack(listoutrev);
		} else {
			new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setMessage("Do you want to exit?")
				.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							finish();
						}
					}).setNegativeButton("No", null).show();
		}
		return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.aboutUs:
			startActivity(new Intent("com.IITI.fluxus14.ABOUT"));
			break;
		}
		return false;
	}

}
