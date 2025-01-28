import controllers.ProductsController;

public class App {
    public static void main(String[] args) throws Exception {
        ProductsController productsController = new ProductsController();
        productsController.initMainFrame();
    }
}
