package com.test.oldsubject;

import java.time.Clock;
import java.time.LocalDateTime;

public class DateTimeTest {

	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt.getYear() + "年" + dt.getMonthValue() + "月" + dt.getDayOfMonth() + "日" + "--"
				+ dt.getHour() + ":" + dt.getMinute() + ":" + dt.getSecond());
		System.out.println(Clock.systemDefaultZone().millis());
	}
}
