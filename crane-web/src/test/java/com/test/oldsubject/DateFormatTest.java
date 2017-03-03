package com.test.oldsubject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatTest {

	public static void main(String[] args) {
		SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy:MM:dd");
		Date date1 = new Date();
		System.out.println(oldFormatter.format(date1));
		
		
		//java8以上
		DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter newFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDate date2 = LocalDate.now();
		LocalDateTime date3 = LocalDateTime.now();
		System.out.println(date2.format(newFormatter));
		System.out.println(date3.format(newFormatter2));
	}
}
