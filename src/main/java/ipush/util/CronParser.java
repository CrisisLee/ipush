package ipush.util;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;

/**
 * 用于解析cron表达式，并获取下一次发送的时间
 * @author arlabsurface
 *
 */
public class CronParser {

	public static Date getNextTime(String cron) {
		CronExpression cronExpression = null;
		Date nextPushTime = null;
		try {
			cronExpression = new CronExpression(cron);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			nextPushTime = getnextTime(cronExpression);
		}
		return nextPushTime;
	}

	public static Date getnextTime(CronExpression cron) {
		Date nextPushTime = cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
		return nextPushTime;
	}
	
	public static void main(String[] args) {
		String cron = "0 46,48,50 * 11 8  ?";
		Date nextTime = getNextTime(cron);
		System.out.println(nextTime);
	}
}
