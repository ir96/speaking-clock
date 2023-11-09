package com.example.timeconversion.service;

import com.example.timeconversion.exception.InvalidTimeFormatException;

public interface TimeConversionService {

    String convertTimeToWords(String time) throws InvalidTimeFormatException;

}
