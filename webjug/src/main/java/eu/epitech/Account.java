package eu.epitech;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by sadzeih on 12/9/16.
 */
@JsonRootName("Account")
public class Account {
    private int id;
    private String email;

    public Account()
    {
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public int getId()
    {
        return this.id;
    }

    @Override
    public String toString()
    {
        return "[" + this.id + "][" + this.email + "]";
    }
}
