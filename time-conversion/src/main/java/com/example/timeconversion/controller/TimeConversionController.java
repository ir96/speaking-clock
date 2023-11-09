package com.example.timeconversion.controller;

import com.example.timeconversion.exception.InvalidTimeFormatException;
import com.example.timeconversion.service.TimeConversionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeConversionController {

    private TimeConversionService timeConversionService;

    public TimeConversionController(TimeConversionService timeConversionService) {
        this.timeConversionService = timeConversionService;
    }

    @ApiOperation(value = "Convert time to words")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully converted time to words"),
            @ApiResponse(code = 400, message = "Invalid input format")
    })
    @GetMapping("/convertTime")
    public ResponseEntity<String> convertTimeToWords(@RequestParam("time") String time) {
        try {
            String result = timeConversionService.convertTimeToWords(time);
            return ResponseEntity.ok(result);
        } catch (InvalidTimeFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
