package com.target.redsky.myRetail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * @author Prashant Vaikole
 *
 */


@Configuration
@ComponentScan(basePackages="com.*")
@EnableMongoRepositories(basePackages="com.target.redsky.myRetail.repository*")

public class AppConfiguration {
	
	@Autowired
	MongoDatabaseFactory mongoDbFactory;
	@Autowired
	MongoMappingContext mongoMappingContext;
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   
	   return builder.build();
	}
	
	//Creating custom objectMapper
	@Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
	
	//Adding MongoConverter to avoid adding class field in MongoDB collection
	@Bean
	public MappingMongoConverter mappingMongoConverter() {

	    DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
	    MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
	    converter.setTypeMapper(new DefaultMongoTypeMapper(null));

	    return converter;
	    }
	
	
	

}
