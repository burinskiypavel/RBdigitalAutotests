package Gson.Tests;

//import API.Wi;
//import API.Wi2;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Test
public class TesstUnderArmour2 {
    String token = null;
    String formValue = null;
    String time = null;
    String time7 = null;


        @Test
        public void apiTest1() throws UnirestException, FileNotFoundException {
            //String postApi = "https://trends.google.com/trends/api/explore?hl=en-US&tz=-120&req=%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22food%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D&tz=-120";
            //String postApi = "https://trends.google.com/trends/api/explore?hl=en-US&tz=-120&req=%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22%2Fm%2F0cmtsr%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D&tz=-120";
            String postApi = "https://trends.google.com/trends/api/explore?hl=en-US&tz=-120&req=%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22%2Fm%2F03sdzf%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D&tz=-120";
            String body = Unirest.get(postApi)
                    .header("hl", "ru")
                    .header("tz", "-120")
                    .header("req", "%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22food%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D")

                    .header("authority", "trends.google.com")
                    .header("path", "/trends/api/explore?hl=ru&tz=-120&req=%7B%22comparisonItem%22:%5B%7B%22keyword%22:%22food%22,%22geo%22:%22US%22,%22time%22:%22today+5-y%22%7D%5D,%22category%22:0,%22property%22:%22%22%7D&tz=-120")
                    .header("accept", "application/json, text/plain, */*")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("cookie", "__utmc=10102256; __utma=10102256.398216924.1572949697.1575372718.1575448579.16; __utmz=10102256.1575448579.16.4.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); HSID=AbcEJ3E7KS0UH40gF; SSID=ADO3ynr-V6VL9EB6p; APISID=R-SniXXBhbh-ZwI6/AxjGvs-oZzBCS9HM0; SAPISID=gmZJXB4skpnDKSo6/AnDHKnLTjpFuRSeZN; SEARCH_SAMESITE=CgQIsI0B; S=billing-ui-v3=d68qgOBhigRN6WpV5jl3V8u0UucPOon1:billing-ui-v3-efe=d68qgOBhigRN6WpV5jl3V8u0UucPOon1; SID=rAdtgVwnvMyZedtqgFeh2fAg_dvo4iOW-6o0qLGGQCN2PTySwKrURuAuuLY75B1vWKZT2w.; ANID=AHWqTUnRpPZDiVYxu6nUCi54lVPZ4xzRQ5gI8U_69_VjuO6veu774Izupd65t7hg; NID=192=SUANyzFji8Ogxx5WQKv9WIKG5XX7GOAiPs2bNTP_uT2Ilc3f3SXJ2cojicho_dP8S6vi2xkDZbzOb3pyD-Yj5BkfK3t5BJO4oA3J5Fpg4MeLpC0RamVP7yXUpTqx7QI7QK7mWw6l4-F379Ii1I3ukoBdbFxZXlqB9F6zI8FkJ-gLCT9F4jgEP_N9EgFvckmHp_cHsL-lRBxvwyQZI5Z05ljm7gLKtJKgbKK12hHRst0; 1P_JAR=2019-12-4-9; SIDCC=AN0-TYvA3fWHptnsf17sAKZ463Ojs2Ph1P5GVNzqppS5uU6AdPT-hZjHHXprhGfZk-auDcrp7kgB")
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                    .header("x-client-data", "CI22yQEIo7bJAQjBtskBCKmdygEIqKPKAQjiqMoBCJetygEIza3KAQjLrsoBCMqvygEI97TKAQ==")
                    //.body(template.render(model))
                    .asString()
                    //.asJson()
                    .getBody();
            System.out.println(body);         // gives the full json response
            String jsonString = body;
            Gson g = new Gson();

            Wiw5 request = g.fromJson(jsonString, Wiw5.class);

            for (Wiw5 wi : request.widgets) {

                System.out.println("token:" + wi.token);
                //System.out.println("id:" + wi.id);
                if (token == null) {
                    for (int i = 0; i < 1; i++) {
                        token = wi.token;
                    }
                }

            }
            System.out.println(token);
            //PrintStream listOut = new PrintStream("C://Users//Максим//Desktop//Testo.txt");
            //listOut.print(token);


            //String test1 = request.token;

            //System.out.println(test1);


        }


        @Test
        public void apiTest2() throws UnirestException, IOException {
            //String postApi = "https://trends.google.com/trends/api/widgetdata/multiline?tz=-120&req=%7B%22time%22:%222015-01-07+2020-01-07%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22BROAD%22,%22value%22:%22food%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D&token=" + token + "&tz=-120";
            //String postApi = "https://trends.google.com/trends/api/widgetdata/multiline?hl=en-US&tz=-120&req=%7B%22time%22:%222015-01-07+2020-01-07%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22ENTITY%22,%22value%22:%22%2Fm%2F0cmtsr%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D&token=" + token + "&tz=-120";
            String postApi = "https://trends.google.com/trends/api/widgetdata/multiline?hl=en-US&tz=-120&req=%7B%22time%22:%222015-01-15+2020-01-15%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22ENTITY%22,%22value%22:%22%2Fm%2F03sdzf%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D&token=" + token + "&tz=-120";
            String body = Unirest.get(postApi)
                    // .routeParam("hl", "ru")
                    //.header("tz", "-120")
                    //.header("req", "%7B%22time%22:%222014-12-04+2019-12-04%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22BROAD%22,%22value%22:%22food%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D")
                    //.header("token", "APP6_UEAAAAAXekxCQiy4BMDtREcWW08HCOR4bBU_MLo")

                    .header("authority", "trends.google.com")
                    //.header("path", "/trends/api/widgetdata/multiline?hl=en-US&tz=-120&req=%7B%22time%22:%222015-01-07+2020-01-07%22,%22resolution%22:%22WEEK%22,%22locale%22:%22en-US%22,%22comparisonItem%22:%5B%7B%22geo%22:%7B%22country%22:%22US%22%7D,%22complexKeywordsRestriction%22:%7B%22keyword%22:%5B%7B%22type%22:%22ENTITY%22,%22value%22:%22%2Fm%2F0cmtsr%22%7D%5D%7D%7D%5D,%22requestOptions%22:%7B%22property%22:%22%22,%22backend%22:%22IZG%22,%22category%22:0%7D%7D&token=APP6_UEAAAAAXhXUYb6cGhN6b8gx866rrzS5rbgY-Kdx&tz=-120")
                    .header("accept", "application/json, text/plain, */*")
                    .header("accept-encoding", "gzip, deflate, br")
                    //.header("cookie", "__utmc=10102256; __utma=10102256.398216924.1572949697.1575372718.1575448579.16; __utmz=10102256.1575448579.16.4.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); __utmt=1; __utmb=10102256.7.9.1575448586551; HSID=AbcEJ3E7KS0UH40gF; SSID=ADO3ynr-V6VL9EB6p; APISID=R-SniXXBhbh-ZwI6/AxjGvs-oZzBCS9HM0; SAPISID=gmZJXB4skpnDKSo6/AnDHKnLTjpFuRSeZN; SEARCH_SAMESITE=CgQIsI0B; S=billing-ui-v3=d68qgOBhigRN6WpV5jl3V8u0UucPOon1:billing-ui-v3-efe=d68qgOBhigRN6WpV5jl3V8u0UucPOon1; SID=rAdtgVwnvMyZedtqgFeh2fAg_dvo4iOW-6o0qLGGQCN2PTySwKrURuAuuLY75B1vWKZT2w.; ANID=AHWqTUnRpPZDiVYxu6nUCi54lVPZ4xzRQ5gI8U_69_VjuO6veu774Izupd65t7hg; NID=192=SUANyzFji8Ogxx5WQKv9WIKG5XX7GOAiPs2bNTP_uT2Ilc3f3SXJ2cojicho_dP8S6vi2xkDZbzOb3pyD-Yj5BkfK3t5BJO4oA3J5Fpg4MeLpC0RamVP7yXUpTqx7QI7QK7mWw6l4-F379Ii1I3ukoBdbFxZXlqB9F6zI8FkJ-gLCT9F4jgEP_N9EgFvckmHp_cHsL-lRBxvwyQZI5Z05ljm7gLKtJKgbKK12hHRst0; 1P_JAR=2019-12-4-8; SIDCC=AN0-TYvU12D7ngM85RKEETkd13Mno2V_rS0nPi-L-WvrnLymrLIiUUdnyIKAY3pn10vt1uJ_qQyq")
                    //.header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36")
                    //.header("x-client-data", "CI22yQEIo7bJAQjBtskBCKmdygEIqKPKAQjiqMoBCJetygEIza3KAQjLrsoBCMqvygEI97TKAQ==")
                    //.body(template.render(model))
                    .asString()
                    //.asJson()
                    .getBody();

            System.out.println(body);         // gives the full json response

            String jsonString = body;
            System.out.println(jsonString.length());
            int xx = jsonString.length();
            jsonString = jsonString.substring(17, xx - 1);
            System.out.println(jsonString);

            Gson g = new Gson();
            Wi6 request = g.fromJson(jsonString, Wi6.class);

            List<String> valueAndTime = new ArrayList<>();
            List<String> justTime = new ArrayList<>();


            for (Wi6 wi2 : request.timelineData) {

                for (int i = 0; i < wi2.formattedValue.length; i++) {

                    String formValue = wi2.formattedValue[i];

                    System.out.println(wi2.formattedAxisTime);
                    System.out.println("formattedValue :" + formValue);

                    //valueAndTime.add(wi2.formattedAxisTime);
                    valueAndTime.add(formValue);
                }
                for (int i = 0; i < wi2.formattedValue.length; i++) {

                    String formValue = wi2.formattedValue[i];

                    System.out.println(wi2.formattedAxisTime);
                    System.out.println("formattedValue :" + formValue);

                    justTime.add(wi2.formattedAxisTime);
                    //valueAndTime.add(formValue);


                    //if (formValue == null) {
                    //for (int j = 0; j < formValue.length(); j++) {
                    //formValue = wi2.formattedValue[j];
                    // }
                    // }


                    //PrintStream listOut = new PrintStream("C://Users//Максим//Desktop//Testo1.txt");
                    //listOut.print(formValue);


                }
            }
            List<Date> justTime2 = new ArrayList<>();

            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("MMMM dd, yyyy");
            for (String str : justTime) {
                Date docDate = null;
                try {
                    docDate = format.parse(str);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                justTime2.add(docDate);
            }







            //List<String> justTime2 = new ArrayList<>();




            //String timeStampCurentData = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
            //System.out.println(timeStampCurentData);

//    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    Calendar cal = Calendar.getInstance();
//
//    //Substract one day to current date.
//    //cal = Calendar.getInstance();
//    cal.add(Calendar.DATE, -9);
//    System.out.println(dateFormat.format(cal.getTime()));
//
//    //cal.add(Calendar.YEAR, -5);
//    //System.out.println(dateFormat.format(cal.getTime()));
//
//    for(int i = 0; i<259; i++) {
//      //Add seven days to current date.
//      cal.add(Calendar.DATE, -7);
//      //System.out.println(dateFormat.format(cal.getTime()));
//      justTime2.add(dateFormat.format(cal.getTime()));
//    }
//
//    for (String str: justTime2) {
//      System.out.println(str);
//
//    }





           // FileWriter writer = new FileWriter("C://Users//Максим//Desktop//Testo1.txt");
           // for (String str : valueAndTime) {
           //     writer.write(str);
          //      writer.write("\n");
           // }
//    for(Date str2: justTime2){
//      writer.write(str2);
//      writer.write("\n");
//    }
          //  writer.close();
            //System.out.println(valueAndTime);


            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Google Trends data");


            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat((short)14);
            sheet.setDefaultColumnStyle(0, cellStyle);


            CellStyle cellStyle2 = wb.createCellStyle();
            cellStyle2.setDataFormat(wb.createDataFormat().getFormat("0"));
            sheet.setDefaultColumnStyle(2, cellStyle2);

            CellStyle cellStyle3 = wb.createCellStyle();
            cellStyle3.setDataFormat(wb.createDataFormat().getFormat("0"));
            sheet.setDefaultColumnStyle(3, cellStyle3);




            CellStyle cellStyle4 = wb.createCellStyle();
            cellStyle4.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
            sheet.setDefaultColumnStyle(4, cellStyle4);

            CellStyle cellStyle5 = wb.createCellStyle();
            cellStyle5.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
            sheet.setDefaultColumnStyle(5, cellStyle5);


            int rownumValue = 0;
            int rownumTime = 0;
            int i = 0;
            Cell cell;
            Cell cell2;
            Cell cell3;
            Cell cell4;
            Row rowValue;
            Row rowTime;
            Row row2;
            Row rowTime2;
            Row rowValue2;

            int rownum2 = 0;
            int rownum3 = 0;
            int rownum4 = 0;
            int rownum5 = 0;


            rowValue = sheet.createRow(rownumValue);
            rowTime = sheet.createRow(rownumTime);

            cell = rowValue.createCell(0);
            //cell.setCellValue("Value");

            // EmpName
            //cell = rowTime.createCell(1);
            // cell.setCellValue("Other");

            // Data



            //rownumValue++;
            //rowValue = sheet.createRow(rownumValue);


   /* for (String data : valueAndTime) {


      rownumValue++;
      rowValue = sheet.createRow(rownumValue);
      //row2 = sheet.createRow(rownumValue); // сделать getRow


      // EmpNo (A)
      //for (int i = 0; i < valueAndTime.size(); i++) {
      //i++;
      //if( i % 2 == 0) {


      cell = rowValue.createCell(0, CellType.NUMERIC);
      cell.setCellValue(data);

      //}
    }*/

            //Sheet sheet2 = wb.createSheet("Google Trends Date");

            //for (Date data2 : justTime2) {


            InputStream inp = new FileInputStream("C:/demo/Crocs.xls");
            //InputStream inp = new FileInputStream("workbook.xlsx");

            try {
                wb = WorkbookFactory.create(inp);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            Sheet sheet2 = wb.createSheet("Second");
            Row row = sheet2.createRow(2);
            Cell cell12 = row.createCell(3);
            if (cell12 == null)
                cell12 = row.createCell(3);
            cell12.setCellType(Cell.CELL_TYPE_STRING);
            cell12.setCellValue("a test");



            for(int y = 0; y < justTime2.size(); y++) {
                rownumTime++;

                //CellStyle cellStyle = wb.createCellStyle();
                //cellStyle.setDataFormat((short)14);
                //sheet.setDefaultColumnStyle(0, cellStyle);
                //rownumTime++;
                //row = sheet.createRow(rownumTime);
                //row2 = sheet.createRow(rownumTime);
                rowTime = sheet2.createRow(rownumTime);  // сделать createRow


                // EmpNo (A)
                //for (int i = 0; i < valueAndTime.size(); i++) {
                //i++;
                //if( i % 2 == 0) {


                cell2 = rowTime.createCell(0);
                //cell2.setCellValue(data2);

                CellStyle style = wb.createCellStyle();
                style.setDataFormat((short)14);
                //cell2.setCellStyle(style);



                //style.setDataFormat((short)14);
                //cell2.setCellStyle(style);


                //DataFormat format2 = wb.createDataFormat();
                //CellStyle dateStyle = wb.createCellStyle();
                //dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
                // cell2.setCellStyle(dateStyle);

                cell2.setCellValue(justTime2.get(y));




                //cell2.setCellValue(data2);
                //cell3 = rowTime.createCell(2);

      /*for (int j = 0; i < 261; i++) {


        cell3 = rowTime.createCell(2);
        // String ref  = (char) ('A' + j) + "3:" + (char)('A'+j)+12;
        //int ref2 =   ('A') +  (2 + j);

        //cell3.setCellFormula("SUM(" + ref + ")");
        cell3.setCellFormula("'Google Trends data'!A2");


        //cell4 = rowTime.createCell(3);
        //String ref2  = (char) ('A' + j) + "2";

        //cell4.setCellFormula("Google Trends data'!" + ref2 + "");

      }*/
                //}

                //rowTime2 = sheet.getRow(rownumTime);


                //cell3 = rowTime2.createCell(3);
                //cell3.setCellFormula("DATEVALUE(A2)");
            }

            for (String data : valueAndTime) {


                rownumValue++;
                rowValue = sheet2.getRow(rownumValue);
                //row2 = sheet.createRow(rownumValue); // сделать getRow


                // EmpNo (A)
                //for (int i = 0; i < valueAndTime.size(); i++) {
                //i++;
                //if( i % 2 == 0) {


                cell = rowValue.createCell(1, CellType.NUMERIC);
                //cell.setCellValue(data);

                CellStyle style = wb.createCellStyle();


                //DataFormat format = wb.createDataFormat();
                CellStyle dateStyle = wb.createCellStyle();
                dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.0"));
                cell.setCellStyle(dateStyle);

                cell.setCellValue(Double.parseDouble(data));

                //style.setDataFormat();
                //style.setAlignment(CellStyle.ALIGN_RIGHT);

                //cell.setCellStyle(style);

                //}


                //rowValue2 = sheet.getRow(rownumValue);


                //cell3 = rowValue2.createCell(4);
                //cell3.setCellFormula("NUMBERVALUE(B2)");

      /*for (int j = 0; i < 261; i++) {


        cell4 = rowTime.createCell(4);
        // String ref  = (char) ('A' + j) + "3:" + (char)('A'+j)+12;
        //int ref2 =   ('A') +  (2 + j);

        //cell3.setCellFormula("SUM(" + ref + ")");
        cell4.setCellFormula("A2");

       *//* DataFormat format = wb.createDataFormat();
        CellStyle dateStyle = wb.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        cell3.setCellStyle(dateStyle);*//*

        //cell3.setCellFormula("DATEVALUE(A2)");


        //cell4 = rowTime.createCell(3);
        //String ref2  = (char) ('A' + j) + "2";

        //cell4.setCellFormula("Google Trends data'!" + ref2 + "");

      }*/
            }

            for (int j =3; j < 261; j++) {
                rownum2++;
                Row row3 = sheet2.getRow(j);
                //Cell cell6 = row3.createCell(6);
                //cell6.setCellValue(2);

                Cell cell7 = row3.createCell(2);
                String formula4 = "AVERAGE(B" + (rownum2) + ":" + "B" + (rownum2+3) + ")";
                cell7.setCellFormula(formula4);

            }

            for (int y = 12; y < 261; y++) {
                rownum3++;
                Row row4 = sheet2.getRow(y);
                //Cell cell8 = row4.createCell(8);
                //cell8.setCellValue(2);

                Cell cell9 = row4.createCell(3);
                String formula5 = "AVERAGE(B" + (rownum3) + ":" + "B" + (rownum3+12) + ")";
                cell9.setCellFormula(formula5);

            }

            for (int z = 52; z < 261; z++) {
                rownum4++;
                Row row5 = sheet2.getRow(z);
                //Cell cell8 = row4.createCell(8);
                //cell8.setCellValue(2);

                Cell cell10 = row5.createCell(4);
                String formula6 = "(D" + (z+1) + "/" + "D" + rownum4 +  ")-1";
                cell10.setCellFormula(formula6);

            }

            for (int x = 52; x < 261; x++) {
                rownum5++;
                Row row6 = sheet2.getRow(x);
                //Cell cell8 = row4.createCell(8);
                //cell8.setCellValue(2);

                Cell cell11 = row6.createCell(5);
                String formula7 = "(C" + (x+1) + "/" + "C" + rownum5 +  ")-1";
                cell11.setCellFormula(formula7);

            }





            //Cell cell3 = row2.createCell(2);
            //cell3.setCellFormula("A1 + B1");


            //if (i % 2 != 0) {
            // cell2 = row2.createCell(2, CellType.NUMERIC);
            //for(int a = 0; a < valueAndTime.size(); a+=2){
            //String rawData =  valueAndTime.get(a);
            //cell = row.createCell(2, CellType.NUMERIC);
            //  cell2.setCellValue(data);


            // EmpName (B)
            // cell = row.createCell(1);
            //cell.setCellValue(data);

//    InputStream inp = new FileInputStream("C:/demo/Crocs.xls");
//    //InputStream inp = new FileInputStream("workbook.xlsx");
//
////    try {
////      Workbook wb2 = WorkbookFactory.create(inp);
////    } catch (InvalidFormatException e) {
////      e.printStackTrace();
////    }
//    Sheet sheet2 = wb.createSheet("fd");
//    Row row = sheet2.createRow(2);
//    Cell cell12 = row.createCell(3);
//    if (cell12 == null)
//      cell12 = row.createCell(3);
//    cell12.setCellType(Cell.CELL_TYPE_STRING);
//    cell12.setCellValue("a test");



            File file = new File("C:/demo/Crocs.xls");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            wb.write(outFile);
            outFile.close();

   /* for (int j = 0; i < 261; i++) {


      cell4 = rowTime.createCell(3);
      String ref2 = (char) ('A' + j) + "2";

      cell4.setCellFormula("Google Trends data'!" + ref2 + "");


    }
    File file2 = new File("C:/demo/employee.xls");
    file2.getParentFile().mkdirs();

    FileOutputStream outFile2 = new FileOutputStream(file2, true);
    wb.write(outFile2);
    */


            //int rowNumb = 0;
            //for (String data : valueAndTime) {


            //Row row = sheet0.createRow(rowNumb++);
            //int colNum = 0;
            //for (Object field : data) {
            //  Cell cell = row.createCell(colNum++);
            //  if (field instanceof String) {
            //     cell.setCellValue((String) field);
            //   } else if (field instanceof Integer) {
            //   cell.setCellValue((Integer) field);

            //cell.setCellValue(field);
            //FileOutputStream fos = new FileOutputStream("C://Users//Максим//Desktop//Mysheet2.xls");


            //for (String data : valueAndTime) {
            // cell.setCellValue(data);
            //writer.write("\n");
            //((HSSFWorkbook) wb).write(fos);
            //fos.close();
            //}
            //((HSSFWorkbook) wb).write(fos);
            //FileOutputStream fos = new FileOutputStream("C://Users//Максим//Desktop//Mysheet2.xls");
            //((HSSFWorkbook) wb).write(fos);
            //fos.close();
            //((HSSFWorkbook) wb).write(fos);
            //fos.close();


            //Workbook wb = new HSSFWorkbook();
            //Sheet sheet0 = wb.createSheet("Google Trends data");
            //Row row = sheet0.createRow(1);
            //Cell cell = row.createCell(1);

            //FileOutputStream fos = new FileOutputStream("C://Users//Максим//Desktop//Mysheet2.xls");
            //cell.setCellValue(data);
            //FileOutputStream fos = new FileOutputStream("C://Users//Максим//Desktop//Mysheet2.xls");


            // for(String str : valueAndTime) {
            // cell.setCellValue(str);


            //FileOutputStream fos = new FileOutputStream("C://Users//Максим//Desktop//Mysheet2.xls");


            //((HSSFWorkbook) wb).write(fos);


            //fos.close();


        }
    }