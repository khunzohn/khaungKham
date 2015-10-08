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
import android.widget.Button;
import android.widget.TextView;

import com.hilllander.khunzohn.khwangkham.util.MarketDay;

import java.util.Calendar;


public class ModifideActivity extends AppCompatActivity {
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30,
            31, 30, 31};
    private FloatingActionButton fab;
    private MarketDay marketDay = new MarketDay();
    private Button back, today, next;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modified);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        back = (Button) findViewById(R.id.back);
        today = (Button) findViewById(R.id.today);
        next = (Button) findViewById(R.id.next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day--;
                if (day < 1) {
                    day = getDaysInPrevMonth(month);
                    month--;
                }
                if (month < 0) {
                    month = 11;
                    year--;
                }
                marketDay.set(year, month, day);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.market_day, MarketDayFragment.getInstance(marketDay))
                        .commit();
            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateToday();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day++;
                if (day > getDaysInCurrentMonth(month)) {
                    day = 1;
                    month++;
                }
                if (month > 11) {
                    month = 0;
                    year++;
                }
                marketDay.set(year, month, day);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.market_day, MarketDayFragment.getInstance(marketDay))
                        .commit();
            }
        });
        inflateToday();
    }

    private int getDaysInPrevMonth(final int month) {
        int prevMonth;
        if (month <= 0)
            prevMonth = 11;
        else
            prevMonth = month - 1;
        int numOfDays = daysInMonth[prevMonth];
        if (marketDay.isLeapYear(year) && prevMonth == 1)
            numOfDays++;

        return numOfDays;
    }

    private int getDaysInCurrentMonth(final int month) {

        int numOfDays = daysInMonth[month];
        if (marketDay.isLeapYear(year) && month == 1)
            numOfDays++;
        return numOfDays;
    }

    private void inflateToday() {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        marketDay.set(year, month, day);
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
        private static final String MARKET_DAY = "market mDay";
        private static final String DAY = "mDay";
        private static final String DATE = "date";
        private String mMarketDay, mDay, date;

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
            mDay = args.getString(DAY);
            date = args.getString(DATE);
            TextView marketDay = (TextView) view.findViewById(R.id.app_text_marketday);
            marketDay.setText(mMarketDay);
            TextView mDay = (TextView) view.findViewById(R.id.app_text_day);
            mDay.setText(this.mDay);
            TextView mDate = (TextView) view.findViewById(R.id.app_text_date);
            mDate.setText(date);
            return view;
        }


    }

}
