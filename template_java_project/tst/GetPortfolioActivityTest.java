import com.cryptoportfolio.activity.GetPortfolioActivity;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.responses.GetPortfolioResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GetPortfolioActivityTest {

    //@Mock
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;

    //@InjectMocks
    private GetPortfolioActivity getPortfolioActivity;

    @BeforeEach
    public void setUp() {

        //initMocks(this);
        portfolioDao = new PortfolioDao();
        assetDao = new AssetDao();
        getPortfolioActivity = new GetPortfolioActivity(portfolioDao,assetDao);
    }

    @Test
    public void handleRequest_savedPortfolioFound_returnsPortfolioModelInResult() {
        // GIVEN
        String expectedUsername = "david";


        Map<String, Double> expectedAssetQuantityMap = new HashMap<>();
        expectedAssetQuantityMap.put("ASSET1", 4.0);
        expectedAssetQuantityMap.put("ASSET2", 2.5);
        expectedAssetQuantityMap.put("ASSET3", 3.2);


        GetPortfolioRequest request = GetPortfolioRequest.builder()
            .withUsername(expectedUsername)
            .build();


        // WHEN
        GetPortfolioResponse result = getPortfolioActivity.handleRequest(request, null);

        // THEN
        assertTrue(result.getPortfolioAssetMap().containsKey(expectedUsername));
        assertNotNull(result.getPortfolioAssetMap());
    }

}
