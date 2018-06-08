package net.ictcampus.weberyo.todo;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImportDates {
    private Date maximalDate;
    private Date minimalDate;

    public List<Date> defineDateinterval(){
        Date actualDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String actualDateFormatted = dateFormatter.format(actualDate);
        try{
            Date date = dateFormatter.parse(actualDateFormatted);
            if(date.getMonth() != 11 & date.getMonth() != 0) {
                maximalDate = new Date(date.getYear() + 1, date.getMonth() + 2, 1);
                minimalDate = new Date(date.getYear() - 1, date.getMonth() - 1, 1);
            }
            else if(date.getMonth() == 11){
                maximalDate = new Date(date.getYear() + 2, 1, 1);
                minimalDate = new Date(date.getYear() - 1, date.getMonth() - 1, 1);
            }
            else if(date.getMonth() == 0){
                maximalDate = new Date(date.getYear() + 1, date.getMonth() + 2, 1);
                minimalDate = new Date(date.getYear() - 1, date.getMonth(), 1);
            }
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
        return(getDatesBetween(minimalDate, maximalDate));
    }

    public List<Date> getDatesBetween(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }
}
