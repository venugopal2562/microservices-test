import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.util.Arrays;


public class OAuthTest {

    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/yt");

    private static FileDataStoreFactory DATA_STORE_FACTORY;

    private static final String SCOPE = "https://www.googleapis.com/auth/youtube.readonly";

    private static final String CLIENT_ID = "682517828264-bemq23s5frn4ihf3ld19fbo6atifnsh0.apps.googleusercontent.com";
    private static final String SECRET_ID = "3qgALi88RnGygOEkm";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String TOKEN_SERVER_URL = "https://accounts.google.com/o/oauth2/token";
    private static final String AUTHORIZATION_SERVER_URL =
            "https://accounts.google.com/o/oauth2/auth";

    /** Authorizes the installed application to access user's protected data. */
    private static Credential authorize() throws Exception {
        AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(BearerToken
                .authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl(TOKEN_SERVER_URL),
                new ClientParametersAuthentication(
                        CLIENT_ID, SECRET_ID),
                CLIENT_ID,
                AUTHORIZATION_SERVER_URL).setScopes(Arrays.asList(SCOPE))
                .setDataStoreFactory(DATA_STORE_FACTORY).build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setHost("127.0.0.1").setPort(8080).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private static void run(HttpRequestFactory requestFactory) throws IOException {
        YoutubeURL url = new YoutubeURL("https://www.googleapis.com/youtube/v3/channels");
        url.setPart("contentDetails");
        url.setMine(true);
        System.out.println("Test");
        HttpRequest request = requestFactory.buildGetRequest(url);
        System.out.println(request.getUrl());
        Channels channels = request.execute().parseAs(Channels.class);
        System.out.println("Channel title: " + channels.items.get(0).snippet.title);
        System.out.println("Channel description: " + channels.items.get(0).snippet.description);
    }

    public static void main(String[] args) {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final Credential credential = authorize();
            System.out.println("qzdqzd");
            HttpRequestFactory requestFactory =
                    HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        public void initialize(HttpRequest request) throws IOException {
                            credential.initialize(request);
                            request.setParser(new JsonObjectParser(JSON_FACTORY));
                        }
                    });
            System.out.println("qzdzdqzdqzdqzd");
            run(requestFactory);
            // Success!
            return;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.exit(1);
    }
}
