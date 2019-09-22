//@author Igor Pikuza

/*
2.queryis valid only for 1. and 3.... Result is 83+117/2
You need to take 117 and 83 in brackets in the example they're without brackets
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int size;
        try {
            size = Integer.parseInt(s);
        }
        catch (NumberFormatException ex){
            System.out.println("You must input number");
            return;
        }


        String[] line = new String[size];
        int spaceCount = 0;
        for (int i = 0; i < size; i++) {
            s = bufferedReader.readLine();
            line[i] = s;
        }

        ArrayList<LineD> lineDArrayList = new ArrayList<LineD>();
        ArrayList<LineC> lineCArrayList = new ArrayList<LineC>();
        StringBuilder number = new StringBuilder();
        int j = 0;


        for (int i = 0; i < size; i++) {
            if(line[i].charAt(0) == 'D') {
                LineD lineD = new LineD();
                lineD.setCharacter('D');
                while(line[i].length() > j) {
                    if (line[i].charAt(j) == ' ') {
                        spaceCount++;
                        j++;
                        if (spaceCount == 1)
                        {
                            j = getJ(line, number, j, i);
                            lineD.setService_id(Double.parseDouble(number.toString()));
                            number = new StringBuilder();
                        }
                        if (spaceCount == 2)
                        {
                            if (line[i].charAt(j) == '*')
                                lineD.setQuestion_type_id(0);
                            else
                                {
                                    j = getJ(line, number, j, i);
                                    lineD.setQuestion_type_id(Double.parseDouble(number.toString()));
                                    number = new StringBuilder();
                                }
                        }
                        if (spaceCount == 3)
                        {
                            lineD.setResponse_type(line[i].charAt(j));
                        }
                        if (spaceCount == 4) {
                            while (line[i].length() != j && line[i].charAt(j) != '-')
                            {
                                number.append(line[i].charAt(j));
                                j++;
                            }
                            String copyDate = String.valueOf(number);
                            SimpleDateFormat format = new SimpleDateFormat();
                            format.applyPattern("dd.MM.yyyy");
                            Date docDate= format.parse(copyDate);
                            lineD.setDateFrom(docDate);
                            if(line[i].length() != j)
                            {
                                number = new StringBuilder();
                                j++;
                                while (line[i].length() != j)
                                {
                                    number.append(line[i].charAt(j));
                                    j++;
                                }
                                copyDate = String.valueOf(number);
                                format = new SimpleDateFormat();
                                number = new StringBuilder();
                                format.applyPattern("dd.MM.yyyy");
                                docDate= format.parse(copyDate);
                                lineD.setDateTo(docDate);
                            }
                        }
                    }
                    else
                        j++;
                }
                 lineDArrayList.add(lineD);
            }
            else if(line[i].charAt(0) == 'C')
            {
                LineC lineС = new LineC();
                lineС.setCharacter('С');
                while(line[i].length() > j) {
                    if (line[i].charAt(j) == ' ') {
                        j++;
                        spaceCount++;
                        if (spaceCount == 1)
                        {
                            j = getJ(line, number, j, i);
                            lineС.setService_id(Double.parseDouble(number.toString()));
                            number = new StringBuilder();
                        }
                        else if (spaceCount == 2)
                        {
                            while (line[i].length() != j && line[i].charAt(j) != ' ' &&line[i].charAt(j) != '.')
                            {
                                number.append(line[i].charAt(j));
                                j++;
                            }
                            lineС.setQuestion_type_id(Double.parseDouble(number.toString()));
                            number = new StringBuilder();
                        }
                       else  if (spaceCount == 3)
                        {
                            lineС.setResponse_type(line[i].charAt(j));
                        }
                        else if (spaceCount == 4) {
                            while (line[i].length() != j && line[i].charAt(j) != '-' && line[i].charAt(j) != ' ')
                            {
                                number.append(line[i].charAt(j));
                                j++;
                            }
                            String copyDate = String.valueOf(number);
                            SimpleDateFormat format = new SimpleDateFormat();
                            number = new StringBuilder();
                            format.applyPattern("dd.MM.yyyy");
                            Date docDate= format.parse(copyDate);
                            lineС.setDate(docDate);
                        }
                        else if (spaceCount == 5) {
                            while (line[i].length() > j)
                            {
                                    number.append(line[i].charAt(j));
                                    j++;
                            }
                            lineС.setWaiting_time(Integer.parseInt(number.toString()));
                            number = new StringBuilder();
                        }
                    }

                    else
                        j++;
                }
                lineCArrayList.add(lineС);
            }
            else
            {
                System.out.println("Input is not correct. Try again!");
            }
            spaceCount = 0;
            j = 0;
        }
        int countSuitable = 0;
        int result = 0;
        for(LineD lineD : lineDArrayList) {
            for(LineC lineC : lineCArrayList) {
                if(lineD.getQuestion_type_id() == 0)
                {
                    if (lineD.getService_id() <= lineC.getService_id() &&
                            lineD.getResponse_type() == lineC.getResponse_type() &&
                            ((lineD.getDateTo().after(lineC.getDate()) && lineD.getDateFrom().before(lineC.getDate()))
                                    ||lineD.getDateTo().equals(lineC.getDate()))) {
                        countSuitable++;
                        result += lineC.getWaiting_time();
                    }
                }
                else if (lineD.getService_id() <= lineC.getService_id() &&
                        lineD.getQuestion_type_id() == lineC.getQuestion_type_id() &&
                        lineD.getResponse_type() == lineC.getResponse_type()&&
                        ((lineD.getDateTo().after(lineC.getDate()) && lineD.getDateFrom().before(lineC.getDate()))
                                ||lineD.getDateTo().equals(lineC.getDate()))) {
                    countSuitable++;
                    result += lineC.getWaiting_time();
                }

            }
            if(countSuitable == 0)
                System.out.println("-");
            else
                System.out.println(result/countSuitable);
            countSuitable = 0;
            result = 0;
        }
    }

    private static int getJ(String[] line, StringBuilder number, int j, int i) {
        while (line[i].length() != j && line[i].charAt(j) != ' ')
        {
            if (number != null) {
                    number.append(line[i].charAt(j));
            }
            j++;
        }
        return j;
    }
}
