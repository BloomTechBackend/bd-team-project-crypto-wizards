//package dynamodb;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.cryptoportfolio.dynamodb.dao.AssetDao;
//import com.cryptoportfolio.dynamodb.models.Asset;
//import com.cryptoportfolio.exceptions.AssetNotFoundException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//public class AssetDaoTest {
//    @Mock
//    private DynamoDBMapper dynamoDBMapper;
//    @InjectMocks
//    private AssetDao assetDao;
//
//    @BeforeEach
//    public void setUp() {
////        dynamoDbMapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
//        assetDao = new AssetDao(dynamoDBMapper);
////        deleteTestData();
//    }
//
//    @AfterEach
////    private void teardown() {
////        deleteTestData();
////    }
//
//    @Test
//    public void getAsset_thatExists_returnsExpected() throws AssetNotFoundException {
//
//
//        String assetId = "bitcoin";
//        Integer rankByMarketCap = 1;
//        String assetName = "Bitcoin";
//        String assetSymbol = "btc";
//        Double marketCap = 757436478342D;
//        String assetImage = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579";
//        Double totalSupply = 21000000D;
//        Double usdValue = 39997D;
//        Double priceChangePercentage24h = -1.69141;
//
//        Asset asset = new Asset();
//        asset.setAssetId(assetId);
//        asset.setAssetName(assetName);
//        asset.setAssetSymbol(assetSymbol);
//        asset.setMarketCap(marketCap);
//        asset.setAssetImage(assetImage);
//        asset.setTotalSupply(totalSupply);
//        asset.setUsdValue(usdValue);
//        asset.setPriceChangePercentage24h(priceChangePercentage24h);
//
//
//        when(dynamoDBMapper.load(Asset.class, assetId)).thenReturn(asset);
//
//        Asset dbAsset = assetDao.getAsset(assetId);
//
//        assertEquals(dbAsset, asset, "Matching Asset Found");
//    }
//
////    private void deleteTestData() {
////        Playlist playList = new Playlist();
////        playList.setId(playListID);
////        dynamoDbMapper.delete(playList);
////    }
//}
