package eu.epitech.jug.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by sadzeih on 12/9/16.
 */

@Controller
public class WebAccountController {

    @Autowired
    protected WebAccountService _accountService;

    public WebAccountController(WebAccountService service)
    {
        _accountService = service;
    }

    @RequestMapping("/account/{email}")
    public String getAccount(@PathVariable("email") String email)
    {
        Account account = _accountService.getByEmail(email);
        return account.toString();
    }
}
