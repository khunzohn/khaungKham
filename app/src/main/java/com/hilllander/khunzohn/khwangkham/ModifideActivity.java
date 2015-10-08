package com.hilllander.khunzohn.khwangkham;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hilllander.khunzohn.khwangkham.util.MarketDay;

import java.util.Calendar;
import java.util.Random;


public class ModifideActivity extends AppCompatActivity {
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30,
            31, 30, 31};
    private FloatingActionButton fab;
    private MarketDay marketDay = new MarketDay();
    private ImageButton back, next, goTo;
    private Button today;
    private int _year, _month, _day;
    private String todayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modified);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inflateToday();

        back = (ImageButton) findViewById(R.id.back);
        today = (Button) findViewById(R.id.today);
        next = (ImageButton) findViewById(R.id.next);
        goTo = (ImageButton) findViewById(R.id.date_picker);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                marketDay.set(year, monthOfYear, dayOfMonth);
                _year = marketDay.get(Calendar.YEAR);
                _month = marketDay.get(Calendar.MONTH);
                _day = marketDay.get(Calendar.DAY_OF_MONTH);
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom)
                        .replace(R.id.market_day, MarketDayFragment.getInstance(marketDay))
                        .commit();
            }
        };
        goTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ModifideActivity.this, AlertDialog.THEME_TRADITIONAL, dateSetListener, _year, _month, _day).show();
            }
        });
        today.setText(todayDate);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _day--;
                if (_day < 1) {
                    _day = getDaysInPrevMonth(_month);
                    _month--;
                }
                if (_month < 0) {
                    _month = 11;
                    _year--;
                }
                marketDay.set(_year, _month, _day);
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
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
                _day++;
                if (_day > getDaysInCurrentMonth(_month)) {
                    _day = 1;
                    _month++;
                }
                if (_month > 11) {
                    _month = 0;
                    _year++;
                }
                marketDay.set(_year, _month, _day);
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .replace(R.id.market_day, MarketDayFragment.getInstance(marketDay))
                        .commit();
            }
        });
    }

    private int getDaysInPrevMonth(final int month) {
        int prevMonth;
        if (month <= 0)
            prevMonth = 11;
        else
            prevMonth = month - 1;
        int numOfDays = daysInMonth[prevMonth];
        if (marketDay.isLeapYear(_year) && prevMonth == 1)
            numOfDays++;

        return numOfDays;
    }

    private int getDaysInCurrentMonth(final int month) {

        int numOfDays = daysInMonth[month];
        if (marketDay.isLeapYear(_year) && month == 1)
            numOfDays++;
        return numOfDays;
    }

    private void inflateToday() {
        Calendar cal = Calendar.getInstance();
        _year = cal.get(Calendar.YEAR);
        _month = cal.get(Calendar.MONTH);
        _day = cal.get(Calendar.DAY_OF_MONTH);
        marketDay.set(_year, _month, _day);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
                .replace(R.id.market_day, MarketDayFragment.getInstance(marketDay))
                .commit();
        todayDate = marketDay.getDayNMonth() + "," + marketDay.get(Calendar.YEAR);
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
        private static final String MYEAR = "mYear";
        private String mMarketDay, mDay, date, mYear;

        public MarketDayFragment() {
        }

        public static Fragment getInstance(MarketDay marketDay) {
            Fragment fragment = new MarketDayFragment();
            Bundle args = new Bundle();
            args.putString(MARKET_DAY, marketDay.getMarketDay());
            args.putString(DAY, marketDay.getMyaDay());
            args.putString(DATE, marketDay.getDayNMonth());
            args.putString(MYEAR, String.valueOf(marketDay.get(Calendar.YEAR)));
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
            mYear = args.getString(MYEAR);
            TextView marketDay = (TextView) view.findViewById(R.id.app_text_marketday);
            marketDay.setText(mMarketDay);
            TextView mDay = (TextView) view.findViewById(R.id.app_text_day);
            mDay.setText(this.mDay);
            TextView mDate = (TextView) view.findViewById(R.id.app_text_date);
            mDate.setText(date);
            TextView year = (TextView) view.findViewById(R.id.app_text_year);
            year.setText(mYear);

            RelativeLayout background = (RelativeLayout) view.findViewById(R.id.app_widget_background);
            background.setBackgroundResource(getRandomResId());
            return view;
        }

        private int getRandomResId() {
            int id = new Random().nextInt(11);
            switch (id) {
                case 0:
                    return R.drawable.back_0;
                case 1:
                    return R.drawable.back_1;
                case 2:
                    return R.drawable.back_2;
                case 3:
                    return R.drawable.back_3;
                case 4:
                    return R.drawable.back_4;
                case 5:
                    return R.drawable.back_5;
                case 6:
                    return R.drawable.back_6;
                case 7:
                    return R.drawable.back_7;
                case 8:
                    return R.drawable.back_8;
                case 9:
                    return R.drawable.back_9;
                case 10:
                    return R.drawable.back_10;
                case 11:
                    return R.drawable.back_11;
                default:
                    return R.drawable.back_3;
            }
        }
    }
}
