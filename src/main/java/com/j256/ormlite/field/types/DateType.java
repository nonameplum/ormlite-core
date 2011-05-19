package com.j256.ormlite.field.types;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;

/**
 * Type that persists a {@link java.util.Date} object.
 * 
 * <p>
 * NOTE: This is <i>not</i> the same as the {@link java.sql.Date} class.
 * </p>
 * 
 * @author graywatson
 */
public class DateType extends BaseDateType {

	public static final DateStringFormatConfig defaultDateFormatConfig = new DateStringFormatConfig(
			"yyyy-MM-dd HH:mm:ss.SSSSSS");

	private static final DateType singleTon = new DateType();

	public static DateType getSingleton() {
		return singleTon;
	}

	private DateType() {
		super(SqlType.DATE, new Class<?>[] { Date.class });
	}

	@Override
	public Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
		DateStringFormatConfig dateFormatConfig = convertDateStringConfig(fieldType);
		try {
			return new Timestamp(parseDateString(dateFormatConfig, defaultStr).getTime());
		} catch (ParseException e) {
			throw SqlExceptionUtil.create("Problems parsing default date string '" + defaultStr + "' using '"
					+ dateFormatConfig + '\'', e);
		}
	}

	@Override
	public Object resultToJava(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
		Timestamp timeStamp = results.getTimestamp(columnPos);
		if (timeStamp == null) {
			return null;
		} else {
			return new Date(timeStamp.getTime());
		}
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject) {
		Date date = (Date) javaObject;
		return new Timestamp(date.getTime());
	}

	@Override
	public boolean isValidForField(Field field) {
		// by default this is a noop
		return true;
	}

	@Override
	public boolean isSelectArgRequired() {
		return true;
	}
}
