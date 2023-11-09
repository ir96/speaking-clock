package com.example.timeconversion.service;

import com.example.timeconversion.exception.InvalidTimeFormatException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class TimeConversionServiceImpl implements TimeConversionService{

    private static final String[] hours = {"Midnight", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen", "Twenty", "Twenty One", "Twenty Two", "Twenty Three"};
    private static final String[] minutes = {"", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
            "nineteen"};
    public static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty"};
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");

    @Override
    public String convertTimeToWords(String time) throws InvalidTimeFormatException {

        if (!isValidTimeFormat(time)) {
            throw new InvalidTimeFormatException("Invalid time format. Please enter HH:MM format.");
        }

        String[] inputs = time.split(":");
        int hour = Integer.parseInt(inputs[0]);

        String[] minuteInput = inputs[1].split("");
        int tensMinute = Integer.parseInt(minuteInput[0]);
        int minute = Integer.parseInt(minuteInput[1]);

        if (hour == 12 && tensMinute == 0 && minute == 0) {
            return "It's Midday";
        } else if (hour == 0 && tensMinute == 0 && minute == 0) {
            return "It's Midnight";
        } else {
            String timeInWords = "It's " + hours[hour] + " and ";
            if (tensMinute > 1) {
                timeInWords += " " + tens[tensMinute];
            }
            if (tensMinute == 0 && minute == 0) {
                return timeInWords + "zero minutes";
            }
            if (tensMinute == 1) {
                return timeInWords + minutes[minute + 10] + " minutes";
            }
            timeInWords += minutes[minute] + " minutes";
            return timeInWords;
        }
    }

    private boolean isValidTimeFormat(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }

}
