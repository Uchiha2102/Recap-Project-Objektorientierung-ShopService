public class ProductNotExistException extends RuntimeException{

    public ProductNotExistException(String productId){
        super("Product with ID " + productId + " not found");
    }



}
