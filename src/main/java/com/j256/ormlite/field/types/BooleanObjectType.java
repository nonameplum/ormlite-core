package com.j256.ormlite.field.types;

import java.lang.reflect.Field;
import java.sql.SQLException;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

/**
 * Type that persists a Boolean object.
 * 
 * @author graywatson
 */
public class BooleanObjectType extends BaseDataType {

	private static final BooleanObjectType singleTon = new BooleanObjectType();

	public static BooleanObjectType getSingleton() {
		return singleTon;
	}

	private BooleanObjectType() {
		super(SqlType.BOOLEAN, new Class<?>[] { Boolean.class });
	}

	protected BooleanObjectType(SqlType sqlType, Class<?>[] classes) {
		super(sqlType, classes);
	}

	@Override
	public Object parseDefaultString(FieldType fieldType, String defaultStr) {
		return Boolean.parseBoolean(defaultStr);
	}

	@Override
	public Object resultToJava(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
		return (Boolean) results.getBoolean(columnPos);
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
		// noop pass-thru
		return javaObject;
	}

	@Override
	public boolean isValidForField(Field field) {
		// this is a noop
		return true;
	}

	@Override
	public boolean isEscapedValue() {
		return false;
	}

	@Override
	public boolean isAppropriateId() {
		return false;
	}
}
