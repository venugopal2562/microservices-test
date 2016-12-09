package eu.epitech.jug.services.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by sadzeih on 12/9/16.
 */
@Service
public class WebAccountService {
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(WebAccountService.class.getName());

    public WebAccountService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
                serviceUrl : "http://" + serviceUrl;
    }

    public Account getByEmail(String email) {
        logger.info("serviceurl = " + serviceUrl);
        return restTemplate.getForObject(serviceUrl
                + "/accounts/{email}", Account.class, email);
    }

}
