package com.edwise.pocs.springrestclient.config;

import org.springframework.web.client.RestTemplate;

public class RestTemplateFactory {

    public static RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
//        addJsonToRestTemplateConverters(restTemplate);
        return restTemplate;
    }

    private static void addJsonToRestTemplateConverters(RestTemplate restTemplate) {
//        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
//        converters.stream()
//                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
//                .forEach(converter -> {
//                    MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
//                    jsonConverter.setObjectMapper(new ObjectMapper());
//                    jsonConverter.setSupportedMediaTypes(
//                            Arrays.asList(
//                                    new MediaType("application", "json", MappingJackson2HttpMessageConverter.DEFAULT_CHARSET)));
//                });
    }
}
