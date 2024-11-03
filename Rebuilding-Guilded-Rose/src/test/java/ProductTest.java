import com.guildedrose.models.Product;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void testRegularItemBeforeSellDate() {
        Product product = new Product("Normal Item", 10, 20, 0, "USD");
        product.updateQuality();
        assertEquals(9, product.getSellIn());
        assertEquals(19, product.getQuality());
    }

    @Test
    public void testRegularItemOnSellDate() {
        Product product = new Product("Normal Item", 0, 20, 0, "USD");
        product.updateQuality();
        assertEquals(-1, product.getSellIn());
        assertEquals(18, product.getQuality());
    }

    @Test
    public void testRegularItemAfterSellDate() {
        Product product = new Product("Normal Item", -1, 20, 0, "USD");
        product.updateQuality();
        assertEquals(-2, product.getSellIn());
        assertEquals(18, product.getQuality());
    }

    @Test
    public void testRegularItemQualityNeverNegative() {
        Product product = new Product("Normal Item", 5, 0, 0, "USD");
        product.updateQuality();
        assertEquals(4, product.getSellIn());
        assertEquals(0, product.getQuality());
    }

    @Test
    public void testAgedBrieBeforeSellDate() {
        Product product = new Product("Aged Brie", 10, 25, 0, "USD");
        product.updateQuality();
        assertEquals(9, product.getSellIn());
        assertEquals(26, product.getQuality());
    }

    @Test
    public void testAgedBrieOnSellDate() {
        Product product = new Product("Aged Brie", 0, 25, 0, "USD");
        product.updateQuality();
        assertEquals(-1, product.getSellIn());
        assertEquals(27, product.getQuality());
    }

    @Test
    public void testAgedBrieAfterSellDate() {
        Product product = new Product("Aged Brie", -1, 25, 0, "USD");
        product.updateQuality();
        assertEquals(-2, product.getSellIn());
        assertEquals(27, product.getQuality());
    }

    @Test
    public void testAgedBrieQualityMax50() {
        Product product = new Product("Aged Brie", 5, 50, 0, "USD");
        product.updateQuality();
        assertEquals(4, product.getSellIn());
        assertEquals(50, product.getQuality());
    }

    @Test
    public void testSulfurasNeverChanges() {
        Product product = new Product("Sulfuras, Hand of Ragnaros", 5, 80, 0, "USD");
        product.updateQuality();
        assertEquals(5, product.getSellIn());
        assertEquals(80, product.getQuality());
    }

    @Test
    public void testBackstagePassesLongBeforeSellDate() {
        Product product = new Product("Backstage passes to a TAFKAL80ETC concert", 15, 20, 0, "USD");
        product.updateQuality();
        assertEquals(14, product.getSellIn());
        assertEquals(21, product.getQuality());
    }

    @Test
    public void testBackstagePassesMediumCloseToSellDate() {
        Product product = new Product("Backstage passes to a TAFKAL80ETC concert", 10, 20, 0, "USD");
        product.updateQuality();
        assertEquals(9, product.getSellIn());
        assertEquals(22, product.getQuality());
    }

    @Test
    public void testBackstagePassesVeryCloseToSellDate() {
        Product product = new Product("Backstage passes to a TAFKAL80ETC concert", 5, 20, 0, "USD");
        product.updateQuality();
        assertEquals(4, product.getSellIn());
        assertEquals(23, product.getQuality());
    }

    @Test
    public void testBackstagePassesAfterSellDate() {
        Product product = new Product("Backstage passes to a TAFKAL80ETC concert", 0, 20, 0, "USD");
        product.updateQuality();
        assertEquals(-1, product.getSellIn());
        assertEquals(0, product.getQuality());
    }

    @Test
    public void testConjuredItemBeforeSellDate() {
        Product product = new Product("Conjured Mana Cake", 3, 6, 0, "USD");
        product.updateQuality();
        assertEquals(2, product.getSellIn());
        assertEquals(4, product.getQuality());
    }

    @Test
    public void testConjuredItemOnSellDate() {
        Product product = new Product("Conjured Mana Cake", 0, 6, 0, "USD");
        product.updateQuality();
        assertEquals(-1, product.getSellIn());
        assertEquals(2, product.getQuality());
    }

    @Test
    public void testConjuredItemAfterSellDate() {
        Product product = new Product("Conjured Mana Cake", -1, 6, 0, "USD");
        product.updateQuality();
        assertEquals(-2, product.getSellIn());
        assertEquals(2, product.getQuality());
    }

    @Test
    public void testConjuredItemQualityNeverNegative() {
        Product product = new Product("Conjured Mana Cake", 5, 1, 0, "USD");
        product.updateQuality();
        assertEquals(4, product.getSellIn());
        assertEquals(0, product.getQuality());
    }
}
