package com.stratio.cct.servicestatus.api.model;

import java.util.EnumMap;

public class ServiceStatusHistoricalRangeTimeUnit {

	public static final char TIMEUNIT_MINUTE = 'm';
	public static final char TIMEUNIT_HOUR = 'h';
	public static final char TIMEUNIT_DAY = 'D';
	public static final char TIMEUNIT_MONTH = 'M';
	public static final char TIMEUNIT_YEAR = 'Y';


	public static EnumMap<ServiceStatusRangeEnum, Character> stateMap;
	static {
		stateMap = new EnumMap<ServiceStatusRangeEnum, Character>(ServiceStatusRangeEnum.class);
		stateMap.put(ServiceStatusRangeEnum.MINUTE, TIMEUNIT_MINUTE); // Minute
		stateMap.put(ServiceStatusRangeEnum.HOUR, TIMEUNIT_HOUR); // Hour
		stateMap.put(ServiceStatusRangeEnum.DAY, TIMEUNIT_DAY); // Day
		stateMap.put(ServiceStatusRangeEnum.MONTH, TIMEUNIT_MONTH); // Month
		stateMap.put(ServiceStatusRangeEnum.YEAR, TIMEUNIT_YEAR); // Year
	}
	

}
