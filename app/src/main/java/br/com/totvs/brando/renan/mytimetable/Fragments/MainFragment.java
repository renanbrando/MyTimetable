package br.com.totvs.brando.renan.mytimetable.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import br.com.totvs.brando.renan.mytimetable.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by renanribeirobrando on 10/06/17.
 */

public class MainFragment extends Fragment {

    private Calendar calendar;
    @BindView(R.id.edInitDay)
    EditText edInitDay;
    @BindView(R.id.edInitTime) EditText edInitTime;
    @BindView(R.id.edRestTime) EditText edRestTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btnGetDay)
    public void getDay(View view){
        // To Do
        // Get Current Date
        calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edInitDay.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btnGetTime)
    public void getTime(View view){
        // Get Current Time
        calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        edInitTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.btnGetETA)
    public void getEta(View view){
        // Get Current Time
        calendar = Calendar.getInstance();
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        edRestTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    @OnClick(R.id.btnGenerate)
    public void generateTimetable(View view){
        if (edInitTime.getText().toString().isEmpty() || edInitDay.getText().toString().isEmpty() || edRestTime.getText().toString().isEmpty()) {
            Snackbar.make(view, R.string.empty_input, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else{
            TimeTableFragment timeTableFragment = new TimeTableFragment();
            FragmentTransaction transaction = this.getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_main, timeTableFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


}
