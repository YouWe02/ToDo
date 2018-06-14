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

    //method for defining a intervall of dates
    public List<Date> defineDateinterval(){
        //get actual date
        Date actualDate = Calendar.getInstance().getTime();

        //set pattern for formatting the date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        //format the date to the pattern
        String actualDateFormatted = dateFormatter.format(actualDate);

        try{
            //get the actual date in Date data type
            Date date = dateFormatter.parse(actualDateFormatted);

            //if the actual month isn't january or december
            if(date.getMonth() != 11 & date.getMonth() != 0) {
                //set the end of the interval to the date in 1 year and 2 months
                maximalDate = new Date(date.getYear() + 1, date.getMonth() + 2, 1);
                //set the begin of the interval to the date 1 year and 2 months ago
                minimalDate = new Date(date.getYear() - 1, date.getMonth() - 1, 1);
            }
            //if the actual month is december
            else if(date.getMonth() == 11){
                //set the end of the interval to the first january in 2 year
                maximalDate = new Date(date.getYear() + 2, 1, 1);
                //set the begin of the interval to the date 1 year and 2 months ago
                minimalDate = new Date(date.getYear() - 1, date.getMonth() - 1, 1);
            }
            //if the actual month is january
            else if(date.getMonth() == 0){
                //set the end of the interval to the date in 1 year and 2 months
                maximalDate = new Date(date.getYear() + 1, date.getMonth() + 2, 1);
                //set the begin of the interval to the first day from the actual months 1 year ago
                minimalDate = new Date(date.getYear() - 1, date.getMonth(), 1);
            }
        }
        catch (java.text.ParseException e){
            e.printStackTrace();
        }
        //return all dates between the begin and the end of the interval
        return(getDatesBetween(minimalDate, maximalDate));
    }

    //method to get all dates between 2 dates
    public List<Date> getDatesBetween(Date startDate, Date endDate) {
        //define a list for all dates
        List<Date> datesInRange = new ArrayList<>();

        //new calendar for the begin
        Calendar calendar = new GregorianCalendar();
        //set the time of the calendar to the startdate
        calendar.setTime(startDate);

        //new calendar for the end
        Calendar endCalendar = new GregorianCalendar();
        //set the time of the calendar to the end date
        endCalendar.setTime(endDate);

        //put all dates between those two calendars in the list of dates
        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        //return all the dates
        return datesInRange;
    }
}
