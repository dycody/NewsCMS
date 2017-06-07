package ssm.newscms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
	private static final String YYYY = "yyyy";
	private static final String YYYYMMDD = "yyyyMMdd";
	private static final SimpleDateFormat YEAR_FORMATER = new SimpleDateFormat(YYYY);
	private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(YYYYMMDD);
	
	/**
	 * 得到某年某周的第一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		week = week - 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);

		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		week = week - 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 取得当前日期所在周的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// SUNDAY
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // Sunday
		return calendar.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);// SUNDAY
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6); // Saturday
		return calendar.getTime();
	}

	/**
	 * 取得当前日期所在周的前一周最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfLastWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfWeek(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.WEEK_OF_YEAR) - 1);
	}

	/**
	 * 返回指定日期的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的第一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定年月的月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(Integer year, Integer month) {
		Calendar calendar = Calendar.getInstance();
		if (year == null) {
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null) {
			month = calendar.get(Calendar.MONTH);
		}
		calendar.set(year, month, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的上个月的最后一天
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) - 1, 1);
		calendar.roll(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
				getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的第一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 1 - 1;
		} else if (quarter == 2) {
			month = 4 - 1;
		} else if (quarter == 3) {
			month = 7 - 1;
		} else if (quarter == 4) {
			month = 10 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
				getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 3 - 1;
		} else if (quarter == 2) {
			month = 6 - 1;
		} else if (quarter == 3) {
			month = 9 - 1;
		} else if (quarter == 4) {
			month = 12 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的上一季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfLastQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
				getQuarterOfYear(date));
	}

	/**
	 * 返回指定年季的上一季的最后一天
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
		Calendar calendar = Calendar.getInstance();
		Integer month = new Integer(0);
		if (quarter == 1) {
			month = 12 - 1;
		} else if (quarter == 2) {
			month = 3 - 1;
		} else if (quarter == 3) {
			month = 6 - 1;
		} else if (quarter == 4) {
			month = 9 - 1;
		} else {
			month = calendar.get(Calendar.MONTH);
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 * 返回指定日期的季度
	 *
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) / 3 + 1;
	}
	
	/**
	 * 得到某年的周数
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static int getWeekCountOfYear(int year){
		Date endDate = getFirstDayOfWeek(year,54);
		String endYear = YEAR_FORMATER.format(endDate);
		if(Integer.parseInt(endYear)==year){
			return 54;
		}
		return 53;
	}

	/**
	 * 得到某年某周的起始日期
	 *
	 * @param year
	 * @param week
	 * @return
	 */
	public static String[] getNoSignWeekDateRange(int year,int week){
		String start = null;
		String end = null; 
		if(week<=1){
			start = year+"0101";
			Date endDate = getLastDayOfWeek(year,1);
			end = DATE_FORMATER.format(endDate);
		}else if(week>53){
			Date beginDate = getFirstDayOfWeek(year,54);
			String startYear = YEAR_FORMATER.format(beginDate);
			if(Integer.parseInt(startYear)>year){
				beginDate = getFirstDayOfWeek(year,53);
			}
			start = DATE_FORMATER.format(beginDate);
			end = year+"1231";
		}else{
			Date beginDate = getFirstDayOfWeek(year,week);
			start = DATE_FORMATER.format(beginDate);
			Date endDate = getLastDayOfWeek(year,week);
			String endYear = YEAR_FORMATER.format(endDate);
			if(Integer.parseInt(endYear)>year){
				end = year+"1231";
			}else{
				end = DATE_FORMATER.format(endDate);
			}
		}
		return new String[]{start,end}; 
	}
	
	/**
	 * 得到某年某月的起始日期
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[] getNoSignMonthDateRange(int year,int month){
		if(month<1){
			month = 1;
		}else if(month>12){
			month = 12;
		}
		String start = DATE_FORMATER.format(getFirstDayOfMonth(year, month-1));
		String end = DATE_FORMATER.format(getLastDayOfMonth(year, month-1));
		return new String[]{start,end}; 
	}
	
	/**
	 * 得到某年某季度的起始日期
	 *
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static String[] getNoSignQuarterDateRange(int year,int quarter){
		if(quarter<1){
			quarter = 1;
		}else if(quarter>4){
			quarter = 4;
		}
		String start = DATE_FORMATER.format(getFirstDayOfQuarter(year, quarter));
		String end = DATE_FORMATER.format(getLastDayOfQuarter(year, quarter));
		return new String[]{start,end}; 
	}
	
	/**
	 * 得到当前季度的起始日期
	 */
	public static String[] getNoSignCurrentQuarterDateRange(){
		Date now = new Date();
		int year = Integer.parseInt(YEAR_FORMATER.format(now));
		int quarter = getQuarterOfYear(now);
		String[] quarterRange = CalendarUtil.getNoSignQuarterDateRange(year,quarter);
		return new String[]{quarterRange[0]+"000000",quarterRange[1]+"235959"}; 
	}
	
	/**
	 * 得到周、月、季度的起始日期和结束日期
	 *
	 * @param year
	 * @param dimension
	 * @param index
	 * @return
	 */
	public static String[] getNoSignDimensionDateRange(int year,String dimension,int index){
		String[] dateRange = null; 
		if("week".equals(dimension)){
			dateRange = getNoSignWeekDateRange(year, index);
		}else if("month".equals(dimension)){
			dateRange = getNoSignMonthDateRange(year, index);
		}else if("quarter".equals(dimension)){
			dateRange = getNoSignQuarterDateRange(year, index);
			
		}
		if(dateRange!=null){
			dateRange[0] = dateRange[0] + "000000";
			dateRange[1] = dateRange[1] + "235959";
		}
		return dateRange; 
	}
	
	public static String[] getNoSignCurrentWeekRange(){
		Date now = new Date();
		return new String[]{DATE_FORMATER.format(getFirstDayOfWeek(now))+"000000",DATE_FORMATER.format(getLastDayOfWeek(now))+"235959"};
	}

	public static void main(String[] args) {
		//System.out.println(getWeekDateRange(2015,-1)[0]+"---"+getWeekDateRange(2015,-1)[1]);
		//System.out.println(getMonthDateRange(2012,2)[0]+"---"+getMonthDateRange(2012,2)[1]);
		//System.out.println(getQuarterDateRange(2012,2)[0]+"---"+getQuarterDateRange(2012,2)[1]);
		/*for(int year=1980;year<2015;year++){
			System.out.println(year+"---"+getWeekCountOfYear(year));
		}*/
		//System.out.println(getDimensionDateRange(2014,"week",32)[0]+"---"+getDimensionDateRange(2014,"week",32)[1]);
		/*Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.YEAR));*/
		
	}

}
