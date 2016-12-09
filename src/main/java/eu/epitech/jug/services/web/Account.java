package eu.epitech.jug.services.web;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by sadzeih on 12/9/16.
 */
@JsonRootName("Account")
public class Account {
    private int _id;
    private String _email;

    public Account()
    {
    }

    public void setId(int id)
    {
        _id = id;
    }

    public void setEmail(String email)
    {
        _email = email;
    }

    public String getEmail()
    {
        return _email;
    }

    public int getId()
    {
        return _id;
    }

    @Override
    public String toString()
    {
        return "[" + _id + "][" + _email + "]";
    }
}
