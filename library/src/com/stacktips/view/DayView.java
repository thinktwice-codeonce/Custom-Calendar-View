/*
 * Copyright (c) 2016 Stacktips {link: http://stacktips.com}.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stacktips.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imanoweb.calendarview.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DayView extends LinearLayout {
    private Date date;
    private List<DayDecorator> decorators;
    private TextView tvDate;
    private TextView tvDetail1;
    private TextView tvDetail2;
    private TextView tvDetail3;
    private TextView tvHolidayName;

    public DayView(Context context) {
        super(context);
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addView(LayoutInflater.from(context).inflate(R.layout.item_date, this, false));
    }

    @SuppressWarnings("deprecation")
    public void bind(Date date, List<DayDecorator> decorators) {
        this.date = date;
        this.decorators = decorators;

        final SimpleDateFormat df = new SimpleDateFormat("d");
        int day = Integer.parseInt(df.format(date));
        tvDate = findViewById(R.id.item_date_tv_date);
        tvDetail1 = findViewById(R.id.item_date_tv_detail1);
        tvDetail2 = findViewById(R.id.item_date_tv_detail2);
        tvDetail3 = findViewById(R.id.item_date_tv_detail3);
        tvHolidayName = findViewById(R.id.item_date_tv_holiday_name);

        tvDetail1.setText("");
        tvDetail3.setText("");
        tvHolidayName.setText("");

        tvDate.setText(String.valueOf(day));
        if (date.getDay() == 0) { //Sunday
            tvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        } else if (date.getDay() == 6) {
            tvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.blue2));
        } else {
            tvDate.setTextColor(ContextCompat.getColor(getContext(), R.color.black_opacity_99));
        }
    }


    public void decorate() {
        //Set custom decorators
        if (null != decorators) {
            for (DayDecorator decorator : decorators) {
                decorator.decorate(this);
            }
        }
    }

    public Date getDate() {
        return date;
    }

    public void setTextColor(int disabledDayTextColor) {
        tvDate.setTextColor(disabledDayTextColor);
    }

    public void setTypeface(Typeface customTypeface) {
        tvDate.setTypeface(customTypeface);
    }

    public void setTextDetails(String text1, String text3) {
        tvDetail1.setText(text1);
        tvDetail3.setText(text3);
    }

    public void setHolidayName(String holidayName) {
        tvHolidayName.setText(holidayName);
    }
}