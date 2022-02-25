//package activity;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.cryptoportfolio.activity.CreatePortfolioActivity;
//import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
//import com.cryptoportfolio.dynamodb.dao.TransactionDao;
//import com.cryptoportfolio.dynamodb.dao.UserDao;
//import com.cryptoportfolio.utils.Auth;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//
//import java.util.Date;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//public class CreatePortfolioActivityTest {
//    @Mock
//    private PortfolioDao portfolioDao;
//    @Mock
//    private Gson gson;
//    @Mock
//    private UserDao userDao;
//    @Mock
//    private TransactionDao transactionDao;
//    String username = "testUsername";
//    private static final int TOKEN_DURATION = 3_600_000;
//    @InjectMocks
//    private CreatePortfolioActivity createPortfolioActivity;
//
//    @BeforeEach
//    public void setUp() {
//        initMocks(this);
//        createPortfolioActivity = new CreatePortfolioActivity(portfolioDao, userDao, transactionDao, gson);
//    }
//
//    Happy Case
//    @Test
//    public void handleRequest_withProperUsername_createsPortfolio() {
//         GIVEN
//        Algorithm algorithm = Algorithm.HMAC256("crypto-wizards");
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withIssuer("cryptoportfolio")
//                .withClaim("username", username)
//                .build(); Reusable verifier instance
//        Date expiry = new Date();
//        expiry.setTime(expiry.getTime() + TOKEN_DURATION);
//        String token = JWT.create()
//                .withIssuer("cryptoportfolio")
//                .withClaim("username", username)
//                .withExpiresAt(expiry)
//                .sign(algorithm);
//        DecodedJWT jwt = verifier.verify(token);
//
//        WHEN
//        try (MockedStatic<Auth> mockedStatic = Mockito.mockStatic(Auth.class)) {
//            mockedStatic
//                    .when(() -> Auth.authenticateToken(eq(username), token))
//                    .thenReturn("Howdy duke!");
//            String result = Auth.verifyToken(eq(username), token);
//            assertEquals("Howdy duke!", result);
//        }
//        Auth.authenticateToken(createPortfolioRequest.getUsername(), createPortfolioRequest.getAuthToken());
//
//
//    }
//
//}
//
//
//
//public class LoginActivityTest {
//    @Mock
//    private UserDao userDao;
//    @Mock
//    private Gson gson;
//    @InjectMocks
//    private LoginActivity loginActivity;
//
//    @BeforeEach
//    public void setUp() {
//        initMocks(this);
//        loginActivity = new LoginActivity(userDao, gson);
//    }
//
//    @Test
//    public void handleRequest_provideCorrectFormatUsernameAndPassword_registersUserSuccessfully() {
//         GIVEN
//        String username = "testing";
//        String password = "testing";
//        final int TOKEN_DURATION = 3_600_000;
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
//        user.setIsNewUser(true);
//        Algorithm algorithm = Algorithm.HMAC256("crypto-wizards");
//        Date expiry = new Date();
//        expiry.setTime(expiry.getTime() + TOKEN_DURATION);
//
//        String token = JWT.create()
//                .withIssuer("cryptoportfolio")
//                .withClaim("username", username)
//                .withExpiresAt(expiry)
//                .sign(algorithm);
//
//        try (MockedStatic<Auth> mockedStatic = Mockito.mockStatic(Auth.class)) {
//            mockedStatic
//                    .when(() -> Auth.authenticateToken(eq(username), token))
//                    .thenReturn("Howdy duke!");
//            String result = Auth.verifyToken(eq(username), token);
//            assertEquals("Howdy duke!", result);
//        }
//
//
//
//
//
//
//        LoginRequest request = new LoginRequest(username, password);
//
//         WHEN
//        when(userDao.getUser(username)).thenReturn(user);
//        LoginResponse response = loginActivity.handleRequest(request, null);
//
//        THEN
//        assertEquals(username, response.getUsername());
//    }
//
//    @Test
//    public void handleRequest_provideInCorrectUsername_throwsLoginException() {
//         GIVEN
//        String username = "testing123";
//        String password = "testing";
//
//
//        RegisterRequest request = new RegisterRequest(username, password);
//
//         WHEN
//        doNothing().when(userDao).createUser(isA(User.class));
//
//        THEN
//        assertThrows(IllegalArgumentException.class, () -> registerActivity.handleRequest(request, null));
//    }
//
//    @Test
//    public void handleRequest_provideInCorrectFormatPassword_throwsIllegalArgumentException() {
//         GIVEN
//        String username = "testing";
//        String password = "tes";
//
//
//        RegisterRequest request = new RegisterRequest(username, password);
//
//         WHEN
//        doNothing().when(userDao).createUser(isA(User.class));
//
//        THEN
//        assertThrows(IllegalArgumentException.class, () -> registerActivity.handleRequest(request, null));
//    }
//}