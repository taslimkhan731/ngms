package com.stl.ngms.orrisaone.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableAsync
public class ApiConnector {

//    @Autowired RestTemplate restTemplate;
    @Autowired private ObjectMapper mapper;

    private static final String CONNECTOR = "ApiConnector";
    final static Logger logger = Logger.getLogger(ApiConnector.class);
    /**
     * Hit Server to call API
     *
     * @param reqDTO
     * @param providerType
     * @param apiType
     * @param apiUrl
     *
     * @return void
     */
    public <T> JsonNode hitServer(T reqDTO, String apiUrl) {
        ResponseEntity<String> responseEntity = null;

        // Send request with POST method, and Headers.
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
        // Create HttpEntity
        HttpEntity<T> entity = new HttpEntity<>(reqDTO, headers);
       // logger.debug("{} -- {} -- {} -- REQUEST ENTITY: {}", CONNECTOR, providerType, apiType, entity);
        Gson gson = new Gson();
        logger.info(" REQUEST ENTITY: In JSON:- {}"+ gson.toJson(reqDTO));
        try {
        	RestTemplate restTemplate = new RestTemplate();
        	
            responseEntity = restTemplate.postForEntity(apiUrl, entity, String.class);
            logger.info(" REQUEST ENTITY: In JSON:- {}"+responseEntity);
           //log.debug("{} -- {} -- {} -- RESPONSE ENTITY: {}", CONNECTOR, providerType, apiType, responseEntity);
            if(responseEntity != null) {
                return mapper.readTree(responseEntity.getBody());
            }
        } catch(Exception ex) {
        	ex.printStackTrace();
            //log.error(QuarksConstants.EXCEPTION_OCCURRED, "{} -- {} -- {} -- ", CONNECTOR, providerType, apiType, ex.getMessage());
        }
        return null;
    }

 
    
    
//    public RestTemplate getRestTemplate(){
//    	return new RestTemplate();
//    } 
//    
    
    
    

//    public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
//            @Override
//            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//                return true;
//            }
//        };
//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//        return new RestTemplate(requestFactory);
//    }


//    RestTemplate newHttpRestTemplate() throws NoSuchAlgorithmException {
//        SSLContext sslContext = SSLContext.getInstance("SSL");
//        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(
//                sslContext,
//                new String[]{"TLSv1.2"}, // important
//                null,
//                NoopHostnameVerifier.INSTANCE);
//
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("https", sslConnectionFactory)
//                .register("http", PlainConnectionSocketFactory.INSTANCE)
//                .build();
//
//        HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);
//        CloseableHttpClient httpclient = HttpClientBuilder.create().setSSLSocketFactory(sslConnectionFactory)
//                .setConnectionManager(ccm)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpclient);
//        return new RestTemplate(requestFactory);
//    }
//
//
//    public RestTemplate newRestV1() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        org.apache.http.conn.ssl.TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
//        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
//                NoopHostnameVerifier.INSTANCE);
//
//        Registry<ConnectionSocketFactory> socketFactoryRegistry =
//                RegistryBuilder.<ConnectionSocketFactory> create()
//                        .register("https", sslsf)
//                        .register("http", new PlainConnectionSocketFactory())
//                        .build();
//
//        BasicHttpClientConnectionManager connectionManager =
//                new BasicHttpClientConnectionManager(socketFactoryRegistry);
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
//                .setConnectionManager(connectionManager).build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//
//        return restTemplate;
//    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Set the core pool size
        executor.setMaxPoolSize(50);   // Set the maximum pool size
        executor.setQueueCapacity(100); // Set the queue capacity
        executor.setThreadNamePrefix("PGTaskExecutor-"); // Set the thread name prefix
        executor.initialize(); // Initialize the executor
        return executor;
    }

}