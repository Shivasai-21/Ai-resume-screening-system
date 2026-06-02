package com.company.resumescreening.pdf;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ResumeParser {

    public String extractEmail(String text) {

        Pattern pattern =
                Pattern.compile(
                        "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+");

        Matcher matcher =
                pattern.matcher(text);

        return matcher.find()
                ? matcher.group()
                : "";
    }

    public String extractPhone(String text) {

        Pattern pattern =
                Pattern.compile(
                        "(\\+91[- ]?)?[6-9]\\d{9}");

        Matcher matcher =
                pattern.matcher(text);

        return matcher.find()
                ? matcher.group()
                : "";
    }
}
