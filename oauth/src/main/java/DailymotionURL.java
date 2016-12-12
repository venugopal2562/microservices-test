import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/**
 * Created by sadzeih on 12/11/16.
 */
public class DailymotionURL extends GenericUrl {
    @Key
    private String fields;

    public DailymotionURL(String encodedUrl) {
        super(encodedUrl);
    }

    /**
     * @return the fields
     */
    public String getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(String fields) {
        this.fields = fields;
    }

}
