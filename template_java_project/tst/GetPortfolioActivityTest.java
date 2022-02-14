import com.cryptoportfolio.activity.GetPortfolioActivity;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.results.GetPortfolioResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPortfolioActivityTest {

    //@Mock
    private PortfolioDao portfolioDao;

    //@InjectMocks
    private GetPortfolioActivity getPortfolioActivity;

    @BeforeEach
    public void setUp() {

        //initMocks(this);
        portfolioDao = new PortfolioDao();
        getPortfolioActivity = new GetPortfolioActivity(portfolioDao);
    }

    @Test
    public void handleRequest_savedPlaylistFound_returnsPlaylistModelInResult() {
        // GIVEN
        String expectedUsername = "david";


        Map<String, Double> expectedAssetQuantityMap = new HashMap<>();
        expectedAssetQuantityMap.put("ASSET1", 4.0);
        expectedAssetQuantityMap.put("ASSET2", 2.5);
        expectedAssetQuantityMap.put("ASSET3", 3.2);

//        UserPortfolio userPortfolio = new UserPortfolio();
//        userPortfolio.setUsername(expectedUsername);
//        userPortfolio.setAssetQuantityMap(expectedAssetQuantityMap);
//
//        when(userPortfolioDao.getUserPortfolio(expectedUsername)).thenReturn(userPortfolio);

        GetPortfolioRequest request = GetPortfolioRequest.builder()
            .withUsername(expectedUsername)
            .build();


        // WHEN
        GetPortfolioResult result = getPortfolioActivity.handleRequest(request, null);
        System.out.println("result : " +result);

        // THEN
        assertEquals(expectedUsername, result.getPortfolio().getUsername());
        assertEquals(expectedAssetQuantityMap, result.getPortfolio().getAssetQuantities());
    }

}
