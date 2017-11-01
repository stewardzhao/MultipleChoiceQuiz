package ca.bcit.comp2052.a00643427.multiplechoicequiz;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import android.app.Fragment;
//import android.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    //record choosing question's option
    static CharSequence answers[]={" "," "," "," "," "};
    final int MAX_POSITION = answers.length;

    //static int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        //test button
        Button button0 = (Button) findViewById(R.id.previous);
        button0.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                // Implement your logic here
                int currentPosition = mViewPager.getCurrentItem();

                if (currentPosition==0){
                    currentPosition = MAX_POSITION+1;
                }
                currentPosition-=1;

                mViewPager.setCurrentItem(currentPosition);
                setTitle(getString(R.string.app_name));
                //mSectionsPagerAdapter.finishUpdate(mViewPager);

               // Toast.makeText(MainActivity.this, "CurrentPosition: "+ currentPosition,
                //        Toast.LENGTH_SHORT).show();

            }
        });

        Button button1 = (Button) findViewById(R.id.next);
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                // Implement your logic here
                int currentPosition = mViewPager.getCurrentItem();

                if (currentPosition>=MAX_POSITION){
                    currentPosition = -1;
                }
                currentPosition+=1;

               mViewPager.setCurrentItem(currentPosition);
                setTitle(getString(R.string.app_name));
                        //mSectionsPagerAdapter.finishUpdate(mViewPager);

                //Toast.makeText(MainActivity.this, "CurrentPosition: "+ currentPosition,
                 //       Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.clear();
        for (int i = 0; i < MAX_POSITION; i++) {
            int labelNum =i +1;
            menu.add(MAX_POSITION,labelNum,labelNum,"Q"+labelNum);
        }
        //menu.add("test item");
        menu.add(MAX_POSITION,MAX_POSITION+1,MAX_POSITION+1,"RESULTS");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        mViewPager.setCurrentItem(id-1);
        setTitle(getString(R.string.app_name));
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            setTitle(getString(R.string.app_name));
            // If it's the last fragment, show Results Fragment
            if(position == getCount() - 1) {
                return new ResultsFragment();
            }
            // Otherwise send position to Rating Fragment as an argument.
            // Create a new Bundle (used to pass data between various components):
            Bundle args = new Bundle();
            // Put integer: add the position value, give it a name
            args.putInt("position", position);
            // Create a new instance of RatingFragment class
            QuestionFragment questionFrag = new QuestionFragment();
            // Set arguments for the new instance
            questionFrag.setArguments(args);
            // Return the new RatingFragment instance
            return questionFrag;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

            return answers.length+1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            /*switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }*/
            // If it's the last fragment, show Results title:
            if(position == getCount() - 1) {
                return getResources().getString(R.string.title_results);
            }
            // Otherwise show Section title and its number (position + 1):
            return String.format(getResources().getString(R.string.title_section), position + 1);
        }
    }
    //setTitle(getString(R.string.app_name));
}
