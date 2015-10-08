package com.hilllander.khunzohn.khwangkham;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hilllander.khunzohn.khwangkham.util.MarketDay;


public class ModifideActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private MarketDay marketDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modified);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        marketDay = new MarketDay();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.market_day, MarketDayFragment.getInstance(marketDay))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modifide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MarketDayFragment extends Fragment {
        private static final String MARKET_DAY = "market day";
        private static final String DAY = "day";
        private static final String DATE = "date";
        private String mMarketDay, day, date;

        public MarketDayFragment() {
        }

        public static Fragment getInstance(MarketDay marketDay) {
            Fragment fragment = new MarketDayFragment();
            Bundle args = new Bundle();
            args.putString(MARKET_DAY, marketDay.getMarketDay());
            args.putString(DAY, marketDay.getMyaDay());
            args.putString(DATE, marketDay.getDayNMonth());
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_client, container, false);
            Bundle args = getArguments();
            mMarketDay = args.getString(MARKET_DAY);
            day = args.getString(DAY);
            date = args.getString(DATE);
            TextView marketDay = (TextView) view.findViewById(R.id.app_text_marketday);
            marketDay.setText(mMarketDay);
            TextView mDay = (TextView) view.findViewById(R.id.app_text_day);
            mDay.setText(day);
            TextView mDate = (TextView) view.findViewById(R.id.app_text_date);
            mDate.setText(date);
            return view;
        }


    }

}
