package eu.epitech;

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
    private String email;

    @NotEmpty
    @JsonSerialize
    @JsonProperty
    private int id;

    private static int idMax = 0;

    public Account(String email)
    {
        this.email = email;
        this.id = this.idMax;
        this.idMax++;
    }

    public String getEmail()
    {
        return email;
    }

    public int getId()
    {
        return id;
    }
}
