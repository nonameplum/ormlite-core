package com.j256.ormlite.field.types;

import java.lang.reflect.Field;
import java.sql.SQLException;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.support.DatabaseResults;

/**
 * Type that persists a boolean primitive.
 * 
 * @author graywatson
 */
public class FloatObjectType extends BaseDataType {

	private static final FloatObjectType singleTon = new FloatObjectType();

	public static FloatObjectType getSingleton() {
		return singleTon;
	}

	private FloatObjectType() {
		super(SqlType.FLOAT, new Class<?>[] { Float.class });
	}

	protected FloatObjectType(SqlType sqlType, Class<?>[] classes) {
		super(sqlType, classes);
	}

	@Override
	public Object resultToJava(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
		return (Float) results.getFloat(columnPos);
	}

	@Override
	public Object parseDefaultString(FieldType fieldType, String defaultStr) {
		return Float.parseFloat(defaultStr);
	}

	@Override
	public Object javaToSqlArg(FieldType fieldType, Object javaObject) throws SQLException {
		// noop pass-thru
		return javaObject;
	}

	@Override
	public boolean isValidForField(Field field) {
		// by default this is a noop
		return true;
	}

	@Override
	public boolean isEscapedValue() {
		return false;
	}
}
