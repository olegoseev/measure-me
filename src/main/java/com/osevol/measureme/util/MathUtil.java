package com.osevol.measureme.util;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalLong;

public final class MathUtil {
	
	private MathUtil() {}
	
	public static OptionalLong min(List<? extends BaseNumber> list) {
		return list.stream().mapToLong(BaseNumber::getLong).min();
	}
	
	public static OptionalDouble avg(List<? extends BaseNumber> list) {
		return list.stream().mapToLong(BaseNumber::getLong).average();
	}
	
	public static OptionalLong max(List<? extends BaseNumber> list) {
		return list.stream().mapToLong(BaseNumber::getLong).max();
	}
}
