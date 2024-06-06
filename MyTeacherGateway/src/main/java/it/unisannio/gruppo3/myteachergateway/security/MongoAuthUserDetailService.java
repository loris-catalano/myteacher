package it.unisannio.gruppo3.myteachergateway.security;

import com.mongodb.DuplicateKeyException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.mongodb.client.model.Filters.eq;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {
    private static String host = System.getenv("MONGO_ADDRESS");
    private static String port = System.getenv("MONGO_PORT");
    private static String URI;

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public static final String DATABASE_NAME = "security";
    public static final String COLLECTION_USERS = "Users";
    public static final String ELEMENT_USERNAME = "email";
    public static final String ELEMENT_PASSWORD = "password";
    public static final String ELEMENT_ROLE = "roles";


    public MongoAuthUserDetailService() {
        if (host == null) {
            //host = "172.31.6.3";
            host = "127.0.0.1";
        }
        if (port == null) {
            port = "27017";
        }
        URI = "mongodb://" + host + ":" + port;
        this.mongoClient = MongoClients.create(URI);
        this.database = mongoClient.getDatabase(DATABASE_NAME);
        this.userCollection = database.getCollection(COLLECTION_USERS);
        createDB();
    }

    public boolean createDB() {
        System.out.println("Connecting to mongo with URI: " + URI);
        try {
            IndexOptions indexOptions = new IndexOptions().unique(true);
            String resultCreateIndex = this.userCollection.createIndex(Indexes.ascending(ELEMENT_USERNAME), indexOptions);
//            System.out.println(String.format("Index created: %s", resultCreateIndex));
        } catch (DuplicateKeyException e) {
            System.out.printf("duplicate field values encountered, couldn't create index: \t%s\n", e);
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("userName = " + userName);

        Document userDoc = userCollection.find(eq(ELEMENT_USERNAME, userName)).first();

        String username = userDoc.getString(ELEMENT_USERNAME);
        String password = userDoc.getString(ELEMENT_PASSWORD);
        List<String> list = userDoc.getList(ELEMENT_ROLE, String.class);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (String authority : list) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
//      mongoClient.close();

        User user = new User(username, password, grantedAuthorities);

        return user;
    }
}
