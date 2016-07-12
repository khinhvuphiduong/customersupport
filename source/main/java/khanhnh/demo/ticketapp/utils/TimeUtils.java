package khanhnh.demo.ticketapp.utils;

public final class TimeUtils {
	public static String intervalToString(long timeInterval) {
		if(timeInterval<1000)
			return "less than on second";
		if(timeInterval<60000)
			return (timeInterval/1000)+" minutes";
		return "about " + (timeInterval/60000)+" minutes";
	}
}
