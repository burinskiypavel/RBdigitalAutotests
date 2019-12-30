package Gson;

import org.testng.annotations.Test;

import javax.xml.datatype.Duration;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
//import java.time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Data_generete {

    @Test
    public void data() {
        //String timeStampCurentData = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
        //System.out.println(timeStampCurentData);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();

        //Substract one day to current date.
        //cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        System.out.println(dateFormat.format(cal.getTime()));

        //cal.add(Calendar.YEAR, -5);
        //System.out.println(dateFormat.format(cal.getTime()));

        for(int i = 0; i<260; i++) {
            //Add seven days to current date.
            cal.add(Calendar.DATE, -7);
            System.out.println(dateFormat.format(cal.getTime()));
        }



    }

}
