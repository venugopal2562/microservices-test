package eu.epitech;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sadzeih on 12/9/16.
 */

@RestController
public class WebAccountController {

    protected WebAccountService _accountService;
    protected Logger logger = Logger.getLogger(WebAccountService.class.getName());

    public WebAccountController(WebAccountService service)
    {
        _accountService = service;
    }

    @RequestMapping("/account/{email}")
    public String getAccount(@PathVariable("email") String email)
    {
        if (_accountService == null)
            logger.info("ACCOUNT service null");
        Account account = _accountService.getByEmail(email);
        if (account == null)
            logger.info("account is null");
        return account.toString();
    }
}
