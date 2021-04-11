package mapper;

import dto.ProductDto;
import entity.Product;

public class ProductMapper {

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setFastingItem(product.isFastingItem());
        productDto.setId(product.getId());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setWeight(product.getWeight());
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setFastingItem(productDto.isFastingItem());
        product.setId(productDto.getId());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setWeight(productDto.getWeight());
        return product;
    }
}
