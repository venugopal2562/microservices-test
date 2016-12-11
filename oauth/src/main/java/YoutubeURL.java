import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/**
 * Created by sadzeih on 12/11/16.
 */
public class YoutubeURL extends GenericUrl {
    @Key
    private String part;

    @Key
    private boolean mine;

    public YoutubeURL(String encodedUrl) {
        super(encodedUrl);
    }

    /**
     * @return the part
     */
    public String getPart() {
        return part;
    }

    public boolean getMine() {
        return mine;
    }

    public void setMine(boolean mine)
    {
        this.mine = mine;
    }

    /**
     * @param part the part to set
     */
    public void setPart(String part) {
        this.part = part;
    }

}
