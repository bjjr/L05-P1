/* CardToStringConverter.java
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Background;

@Component
@Transactional
public class BackgroundToStringConverter implements Converter<Background, String> {

	@Override
	public String convert(Background background) {
		String result;

		if (background == null)
			result = null;
		else
			result = String.valueOf(background.getId());

		return result;
	}

}
