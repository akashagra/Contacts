package com.akashdeveloper.avma1997.contacts_kisan.Activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.akashdeveloper.avma1997.contacts_kisan.Fragments.ContactFragment;
import com.akashdeveloper.avma1997.contacts_kisan.Fragments.SentMessageFragment;
import com.akashdeveloper.avma1997.contacts_kisan.R;

public class MainActivity extends AppCompatActivity {
    private TabLayout tab;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // In this activity two fragments are created , contacts fragment and message fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Instantiating Contacts Pager Adapter Class which would be used for the two tabs( fragments)
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tab = (TabLayout) findViewById(R.id.tab);
        tab.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        //Log.i("akash","aaya");





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                // contact fragment on the first tab is used to display the list of contacts
                // message fragment is used to display the list of messages
                switch (position) {
                    case 0:
                        ContactFragment contactFragment =new ContactFragment();
                        return contactFragment;
                    case 1:
                         SentMessageFragment messageFragment= new SentMessageFragment();
                         return messageFragment;

                         default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // Show 2 total pages.
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Contact";
                    case 1:
                        return "Messages";
                }
                return null;
            }
        }
}
