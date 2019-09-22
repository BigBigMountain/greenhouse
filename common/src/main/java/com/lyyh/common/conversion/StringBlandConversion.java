package com.lyyh.common.conversion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringBlandConversion implements Converter<String, String>{

	@Override
	public String convert(String source) {
		
		if(StringUtils.isBlank(source)){
			return null;
			
		}else {
			return source.trim();
			
		}
	}

}
