package eu.epitech.jug.services.account;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sadzeih on 12/8/16.
 */
@RestController
public class AccountController {

    @RequestMapping("/account")
    public String account()
    {
        return "Welcome to the accounts";
    }

    @RequestMapping("/accounts")
    public String accounts()
    {
        return "Welcome to the accountsssssssssssss";
    }

    @RequestMapping("/account/{email}")
    public @ResponseBody Account newAccount(@PathVariable("email") String email)
    {
        return new Account(email);
    }
}
