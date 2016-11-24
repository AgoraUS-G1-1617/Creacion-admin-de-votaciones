package converters; 

import org.apache.commons.lang.StringUtils; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.core.convert.converter.Converter; 
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional; 

import repositories.SurveyRepository; 
import domain.Survey; 

@Component 
@Transactional 
public class StringToSurveyConverter implements Converter<String, Survey>{ 

	@Autowired 
	SurveyRepository surveyRepository; 

	@Override 
	public Survey convert(String text){ 
		Survey result; 
		int id; 

		try{ 
			if(StringUtils.isEmpty(text)){ 
				result = null; 
			}else{ 
				id = Integer.valueOf(text); 
				result = surveyRepository.findOne(id); 
			} 
		}catch (Throwable oops) { 
			throw new IllegalArgumentException(oops); 
		} 

		return result; 
	} 

} 
