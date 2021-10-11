package giasuomt.demo.modelmapper.configuation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper()
	{
		
		ModelMapper mapper=new ModelMapper();
		
		mapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT);
		
		return mapper;
	}
	
}
