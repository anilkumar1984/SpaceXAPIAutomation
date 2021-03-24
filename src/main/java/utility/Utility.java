package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Utility {

	public static String getCurrentDateAndTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sdf.format(date);

	}

	public static String generateRandomUniqueEmailAddress() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String strDate = sdf.format(d);
		return "ak-" + strDate + "@gmail.com";

	}
}
