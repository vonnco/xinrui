package com.xinrui.framework.utils;

import com.xinrui.framework.common.Enum.EnumInterface;
import com.xinrui.framework.common.Enum.EnumMultiInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName: EnumUtil
 * @Description: 枚举工具类
 */
public class EnumUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(EnumUtil.class);
	private EnumUtil(){
		
	}
	/**
	 * 获取value返回枚举对象(通过value获取name)
	 * @param value
	 * @param clazz
	 * @throws Exception 
	 * */
	@SuppressWarnings("rawtypes")
	public static String getNameByVal(Object value, Class clazz) {
		if(value == null) {
			return null;
		}
		Map<String, String> enumMap = transEnumToMap(clazz);
		if(String.valueOf(value).contains(",")) {
			StringBuilder values = new StringBuilder();
			for(String temp : String.valueOf(value).split(",")) {
				values.append(enumMap.get(temp)).append(",");
			}
			return values.substring(0, values.length()-1);
		} else {
			return enumMap.get(String.valueOf(value));
		}
	}

	/**
	 * 枚举、map转换器
	 * @param clazz
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> transEnumToMap(Class clazz){
		Map<String, String> enumMap = new TreeMap<>();
		try {
			if(EnumMultiInterface.class.isAssignableFrom(clazz)) {
				Method method = clazz.getMethod("values");
				EnumMultiInterface[] inter = (EnumMultiInterface[]) method.invoke(null, null);
				for (EnumMultiInterface enumMessage : inter) {
					enumMap.put(String.valueOf(enumMessage.getValue()), String.valueOf(enumMessage.getName()));
				}
			} else {
				Method method = clazz.getMethod("values");
				EnumInterface[] inter = (EnumInterface[]) method.invoke(null, null);
				for (EnumInterface enumMessage : inter) {
					enumMap.put(String.valueOf(enumMessage.getValue()), String.valueOf(enumMessage));
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return enumMap;
	}
	/**
	 * 通过code 获取枚举
	 * 枚举必须有code值，枚举类必须有getCode()方法
	 * 不建议使用
	 * @param clazz
	 * @param code
	 * @return
	 */
	public static <T> T getEnumByCode(Class<T> clazz, String code) {
		T[] objs = (T[]) clazz.getEnumConstants();
		for (int i = 0; i < objs.length; i++) {
			try {
				Method m = clazz.getMethod("getName");
				if (code.equals(m.invoke(objs[i]))) {
					return objs[i];
				}
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}

