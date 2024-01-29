package onliner.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pages.ProductPage;
import onliner.pages.helpers.ProductInfoHelper;

import java.util.List;
import java.util.Map;

public class ProductStep extends BaseStep {
    private final ProductPage productPage;

    public ProductStep() {
        this.productPage = new ProductPage();
    }

    @When("I write price before {double} on Product page")
    public void userNavigatesToTheSection(Double price) {
        productPage.sendTextInBeforePrice(price);
    }

    @And("I select filters on Product page")
    public void userNavigatesToTheSection(Map<String, String> filters) {
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            productPage.clickFilter(entry.getValue(), entry.getKey());
        }
        productPage.waitForResultsLoaded();
    }

    @Then("I verify that price is less than {double}")
    public void verifyProductPrice(Double price) {
        List<Double> pricesValueList = productPage.getPricesValue();
        softAssert.assertTrue(pricesValueList.stream()
                        .allMatch(element -> element <= price),
                "This product has more then " + price);
    }

    @Then("I verify that product is only {string}")
    public void verifyProductTitle(String titleText) {
        List<String> productsNameList = productPage.getProductsName();
        softAssert.assertTrue(productsNameList.stream()
                        .allMatch(element -> element.contains(titleText)),
                "This product doesn't contain " + titleText);
    }

    @Then("I verify that resolution is only {string}")
    public void verifyProductResolution(String resolution) {
        List<String> productsDescriptionList = productPage.getProductsDescription();
        softAssert.assertTrue(productsDescriptionList.stream()
                        .allMatch(element -> element.contains(resolution)),
                "This product description doesn't contain " + resolution);
    }

    @Then("I verify that  diagonal is between {int} and {int}")
    public void verifyProductDiagonalIsBetween(int startRange, int endRange) {
        List<Integer> propertyFromDescriptionList = productPage.extractPropertyFromDescription(ProductInfoHelper.extractTvDiagonal);
        softAssert.assertTrue(propertyFromDescriptionList.stream()
                        .allMatch(value -> value >= startRange && value <= endRange),
                "This product not between " + startRange + " and " + endRange);
    }
}
