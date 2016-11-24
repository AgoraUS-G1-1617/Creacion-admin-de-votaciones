package converters; 

import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import domain.Survey; 

@Component 
@Transactional 
public class SurveyToStringConverter implements Converter<Survey, String>{ 

	@Override 
	public String convert(Survey survey){ 
		String result; 
		if(survey == null){ 
			result = null; 
		}else{ 
			result = String.valueOf(survey.getId()); 
		} 
		return result; 
	} 

} 
