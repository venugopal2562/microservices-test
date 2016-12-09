package eu.epitech.jug.services.account;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by sadzeih on 12/8/16.
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @NotEmpty
    @JsonSerialize
    @JsonProperty
    private String _email;

    @NotEmpty
    @JsonSerialize
    @JsonProperty
    private int _id;

    private static int _idMax = 0;

    public Account(String email)
    {
        _email = email;
        _id = _idMax;
        _idMax++;
    }

    public String getEmail()
    {
        return _email;
    }

    public int getId()
    {
        return _id;
    }
}
