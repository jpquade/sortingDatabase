package com.sorting.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * TimingApplication is the class containing the main method.
 * It is the entry point for running the application.
 * First a size is selected for the data structure, then random data is added.
 * After duplicating the data for multiple sorting classes, the data is then
 * sorted in different ways using threads to maximize performance.
 * Once sorted, timing is recorded and each set of runs from all sorter classes
 * become inserted into a database together by number.
 */
@SpringBootApplication
@EnableAsync
public class TimingApplicaton {

	/**
	 * The entry point of application.
	 *
	 * @param args The input arguments for the main program
	 */
	public static void main(String[] args) {
		SpringApplication.run(TimingApplicaton.class, args);
	}
}