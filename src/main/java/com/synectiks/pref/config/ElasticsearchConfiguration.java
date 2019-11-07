package com.synectiks.pref.config;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchConfiguration {

    private ObjectMapper mapper;

    public ElasticsearchConfiguration(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Bean
    public EntityMapper getEntityMapper() {
        return new CustomEntityMapper(mapper);
    }

//    @Bean
//    @Primary
//    public ElasticsearchOperations elasticsearchTemplate(final JestClient jestClient,
//                                                         final ElasticsearchConverter elasticsearchConverter,
//                                                         final SimpleElasticsearchMappingContext simpleElasticsearchMappingContext,
//                                                         EntityMapper mapper) {
//        return new JestElasticsearchTemplate(
//            jestClient,
//            elasticsearchConverter,
//            new DefaultJestResultsMapper(simpleElasticsearchMappingContext, mapper));
//    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client, Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        return new ElasticsearchTemplate(client, new CustomEntityMapper(jackson2ObjectMapperBuilder.createXmlMapper(false).build()));
    }
    
    public class CustomEntityMapper implements EntityMapper {

        private ObjectMapper objectMapper;

        public CustomEntityMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
            objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        }

        @Override
        public String mapToString(Object object) throws IOException {
            return objectMapper.writeValueAsString(object);
        }

        @Override
        public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
            return objectMapper.readValue(source, clazz);
        }
    }

}
