package com.hilllander.khunzohn.khwangkham;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modified);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getString(R.string.app_name));
        } else
            Toast.makeText(this, "Actionbar is not null", Toast.LENGTH_SHORT).show();

        TextView heart = (TextView) findViewById(R.id.heart);
        heart.setText(" " + getString(R.string.heart) + " ");
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MarketDayFragment.getInstance(position);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_help) {
            Intent aboutUs = new Intent(this, HelpActivity.class);
            startActivity(aboutUs);
        }

        return super.onOptionsItemSelected(item);
    }

    public void aboutUs(View view) {
        Intent aboutUS = new Intent(this, AboutUsActivity.class);
        startActivity(aboutUS);
    }

    public static class MarketDayFragment extends Fragment {
        private static final String POSITION = "position";

        public MarketDayFragment() {
        }

        public static Fragment getInstance(int position) {
            Fragment fragment = new MarketDayFragment();
            Bundle args = new Bundle();
            args.putString(POSITION, String.valueOf(position));
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_market_day, container, false);
            TextView temp = (TextView) view.findViewById(R.id.temp);
            Bundle args = getArguments();
            if (args != null)
                temp.setText(args.getString(POSITION, "DEFAult"));
            else
                temp.setText("Bundle is null");
            return view;
        }
    }
}
