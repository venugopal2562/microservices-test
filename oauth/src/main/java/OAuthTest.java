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

    private static final String SCOPE = "read";

    private static final String CLIENT_ID = "5f9b80166f0af02021d7";
    private static final String SECRET_ID = "90886de4678fb695830d7b59bc0343769a6b761f";

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String TOKEN_SERVER_URL = "https://api.dailymotion.com/oauth/token";
    private static final String AUTHORIZATION_SERVER_URL =
            "https://www.dailymotion.com/oauth/authorize";

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
        DailymotionURL url = new DailymotionURL("https://api.dailymotion.com/videos/favorites");
        url.setFields("id,title,description,views_total,url");
        HttpRequest request = requestFactory.buildGetRequest(url);
        System.out.println(request.getUrl());
        DVideos videos = request.execute().parseAs(DVideos.class);
        if (videos.list.isEmpty())
        {
            System.out.println("No favorite videos.");
            return;
        }
        for (DVideo video : videos.list)
        {
            System.out.println("Video id: " + video.id);
            System.out.println("Video title: " + video.title);
            System.out.println("Video description: " + video.description);
            System.out.println("Video views: " + video.views_total);
            System.out.println("Video URL: " + video.url);
            System.out.println("------------------------------------------");
        }
    }

    public static void main(String[] args) {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            final Credential credential = authorize();
            HttpRequestFactory requestFactory =
                    HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                        public void initialize(HttpRequest request) throws IOException {
                            credential.initialize(request);
                            request.setParser(new JsonObjectParser(JSON_FACTORY));
                        }
                    });
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
