
import com.cryptoportfolio.activity.CreatePortfolioActivity;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.dao.PortfolioDao;
import com.cryptoportfolio.models.requests.CreatePortfolioRequest;
import com.cryptoportfolio.models.results.CreatePortfolioResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatePortfolioActivityTest {
    //@Mock
    private PortfolioDao portfolioDao;
    private AssetDao assetDao;
    String username = "david";
    private CreatePortfolioActivity createPortfolioActivity;

    @BeforeEach
    public void setUp() {
        //initMocks(this);
        portfolioDao = new PortfolioDao();
        assetDao = new AssetDao();
        createPortfolioActivity = new CreatePortfolioActivity(portfolioDao, assetDao);
    }

    //Happy Case
    @Test
    public void handleRequest_withProperUsername_createsPortfolio() {
        // GIVEN

        Map<String, Double> assetQuantityMap = new HashMap<>();
        assetQuantityMap.put("ASSET1", 4.0);
        assetQuantityMap.put("ASSET2", 2.5);
        assetQuantityMap.put("ASSET3", 3.2);

        CreatePortfolioRequest request = CreatePortfolioRequest.builder()
                                         .withusername(username)
                                         .withAssetQuantityMap(assetQuantityMap)
                                         .build();

        // WHEN
        CreatePortfolioResult result = createPortfolioActivity.handleRequest(request, null);

        Map<String, Double> createdPortfolio = result.getPortfolio().getAssetQuantities();

        System.out.println("createdPortfolio : " + createdPortfolio);

         // THEN

        assertEquals(username, result.getPortfolio().getUsername());
    }

//    @Test
//    public void handleRequest_withIncorrectName_createsPlaylist() {
//        // GIVEN
//        String expectedName = "expectedName's";
//        String expectedCustomerId = "expectedCustomerId";
//
//        List<String> expectedTags = Lists.newArrayList("tag");
//
//        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
//                .withCustomerId(expectedCustomerId)
//                .withName(expectedName)
//                .withTags(expectedTags)
//                .build();
//
//
//        // THEN
//        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request, null));
//    }
//
//    @Test
//    public void handleRequest_withIncorrectCustomerId_createsPlaylist() {
//        // GIVEN
//        String expectedName = "expectedName";
//        String expectedCustomerId = "expectedCustomerId's";
//
//        List<String> expectedTags = Lists.newArrayList("tag");
//
//        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
//                .withCustomerId(expectedCustomerId)
//                .withName(expectedName)
//                .withTags(expectedTags)
//                .build();
//
//
//        // THEN
//        assertThrows(InvalidAttributeValueException.class, () -> createPlaylistActivity.handleRequest(request, null));
//    }
}