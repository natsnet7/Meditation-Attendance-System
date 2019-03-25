package edu.mum.tmAttendanceReport.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.mum.tmAttendanceReport.entity.DateInfo;
import edu.mum.tmAttendanceReport.entity.Place;
import edu.mum.tmAttendanceReport.entity.Student;
import edu.mum.tmAttendanceReport.entity.TMAttendance;
import edu.mum.tmAttendanceReport.entity.TMAttendanceIdentity;
import edu.mum.tmAttendanceReport.entity.TimeInfo;
import edu.mum.tmAttendanceReport.service.DateInfoService;
import edu.mum.tmAttendanceReport.service.PlaceService;
import edu.mum.tmAttendanceReport.service.StudentService;
import edu.mum.tmAttendanceReport.service.TMAttendanceService;
import edu.mum.tmAttendanceReport.service.TimeInfoService;

@Component
public class LoadData {

	@Autowired
	private static StudentService studentService;

	@Autowired
	private static DateInfoService dateInfoService;

	@Autowired
	private static TimeInfoService timeInfoService;

	@Autowired
	private static PlaceService placeService;

	@Autowired
	private static TMAttendanceService tmAttendanceService;

	public static void load() {

		Stream<String> data = null;
		Path path = Paths.get("data/rc_data.txt");

		try {
			data = Files.lines(path).parallel().distinct();
			parseData(data.collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void parseData(List<String> list) {
		final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<TMAttendance> listOfTMAttendance1 = new ArrayList<TMAttendance>();
		
		for(String s: list){
			String[] values = s.split(",");
			TMAttendance tmAttendance = new TMAttendance();
			String studentBarCode = values[0].trim();
			System.out.println(studentService == null? "null":"not null");
		    Student listStudent = studentService.getStudentByBarCode(studentBarCode);
			
			if(listStudent != null){
				Student student = listStudent;
				
				try {
					Date date = format.parse(values[1].trim());
					DateInfo dateInfo = dateInfoService.getDateInfoByDate(date);

					TMAttendanceIdentity tmIdentity = new TMAttendanceIdentity();
					tmIdentity.setStudentId(student);
					tmIdentity.setDate(dateInfo);

					tmAttendance.setTmAttendanceIdentity(tmIdentity);
					System.out.println("SET TM ATTENDANCE ID ROW!!");
				} catch (ParseException e) {
					e.printStackTrace();
					showError();
				}
				
				TimeInfo timeInfo = timeInfoService.getByTimeOfDay(values[3].trim());
				tmAttendance.setTimeInfo(timeInfo);

				Place place = placeService.getPlaceByName(values[4].trim());
				tmAttendance.setPlace(place);

				listOfTMAttendance1.add(tmAttendance);
			}
			
		}
		saveDataRow(listOfTMAttendance1);
	}

	private static void showError() {
		System.out.println("Can't parse DATE");
	}

	public static void saveDataRow(List<TMAttendance> tm) {
		for (TMAttendance tmAttendance : tm) {
			if(!tm.equals(null)) {
				TMAttendance saveObject = tmAttendanceService.save(tmAttendance);
			}
		}
	}
}
