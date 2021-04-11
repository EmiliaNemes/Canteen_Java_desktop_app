package service;

import dto.ProductDto;
import entity.Product;
import exception.*;
import mapper.ProductMapper;
import repository.ProductRepo;
import utils.ApplicationUtils;

import java.util.*;
import java.util.logging.Logger;

public class ProductService {

    private final static Logger log = Logger.getLogger(ProductService.class.getName());

    private final ProductRepo productRepo;
    private IllegalArgumentException illegalArgumentException;

    public ProductService(){
     this.productRepo= new ProductRepo();
    }

    public ProductDto getProdByName(String name){
        try{
            Product prod= productRepo.findByName(name);
            log.info("Product with name " + name + " was found");
            return ProductMapper.entityToDto(prod);
        }
        catch(Exception e){
            log.warning("Product with name " + name + " does not exists");
            throw illegalArgumentException;
        }
    }

    public List<ProductDto> getProdByCategory(String category){
            List<Product> products=productRepo.findByCategory(category);
            if(products.isEmpty()){
                log.warning("Invalid product category");
                throw new IllegalArgumentException(CustomExceptionMessages.INVALID_CATEGORY_MESSAGE1);
            }
            List<ProductDto> productsDto= new ArrayList<>();

            for (Product p: products) {
              ProductDto prodDto=ProductMapper.entityToDto(p);
              productsDto.add(prodDto);
            }
            return productsDto;
    }

    public void addProduct(ProductDto productDto) throws InvalidCategoryException, InvalidFastingItemException, InvalidProductNameException, InvalidPriceException, InvalidQuantityException, InvalidWeightException, InvalidUrlException {
        Product prod = ProductMapper.dtoToEntity(productDto);
        prod.setId(ApplicationUtils.generateUUID());

        if (!ApplicationUtils.isCategoryValid(productDto.getCategory())){
            log.warning("Invalid category");
            throw new InvalidCategoryException(CustomExceptionMessages.INVALID_CATEGORY_MESSAGE);
        }

        if (!ApplicationUtils.isProductNameValid(productDto.getName())){
            log.warning("Invalid product name");
            throw new InvalidProductNameException(CustomExceptionMessages.INVALID_PROD_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isPriceValid(productDto.getPrice())){
            log.warning("Invalid price");
            throw new InvalidPriceException(CustomExceptionMessages.INVALID_PRICE_MESSAGE);
        }

        if (!ApplicationUtils.isQuantityValid(productDto.getQuantity())){
            log.warning("Invalid quantity");
            throw new InvalidQuantityException(CustomExceptionMessages.INVALID_QUANTITY_MESSAGE);
        }

        if (!ApplicationUtils.isWeightValid(productDto.getWeight())){
            log.warning("Invalid weight");
            throw new InvalidWeightException(CustomExceptionMessages.INVALID_WEIGHT_MESSAGE);
        }

        if (!ApplicationUtils.isUrlValid(productDto.getImageURL(), productDto.getCategory())){
            log.warning("Invalid Url image");
            throw new InvalidUrlException(CustomExceptionMessages.INVALID_URL_MESSAGE);
        }

        productRepo.insertNewProduct(prod);
        log.info("New product successfully added");
        log.info(prod.toString());
    }

    public void removeProduct(String id){
        productRepo.removeProduct(id);
        log.info("Product successfully deleted");
    }

    public void updateProduct(ProductDto productDto) throws InvalidCategoryException, InvalidProductNameException, InvalidFastingItemException, InvalidPriceException, InvalidQuantityException, InvalidWeightException, InvalidUrlException {
        Product prod = ProductMapper.dtoToEntity(productDto);

        if (!ApplicationUtils.isCategoryValid(productDto.getCategory())){
            log.warning("Invalid category");
            throw new InvalidCategoryException(CustomExceptionMessages.INVALID_CATEGORY_MESSAGE);
        }

        if (!ApplicationUtils.isProductNameValid(productDto.getName())){
            log.warning("Invalid product name");
            throw new InvalidProductNameException(CustomExceptionMessages.INVALID_PROD_NAME_MESSAGE);
        }

        if (!ApplicationUtils.isPriceValid(productDto.getPrice())){
            log.warning("Invalid price");
            throw new InvalidPriceException(CustomExceptionMessages.INVALID_PRICE_MESSAGE);
        }

        if (!ApplicationUtils.isQuantityValid(productDto.getQuantity())){
            log.warning("Invalid quantity");
            throw new InvalidQuantityException(CustomExceptionMessages.INVALID_QUANTITY_MESSAGE);
        }

        if (!ApplicationUtils.isWeightValid(productDto.getWeight())){
            log.warning("Invalid weight");
            throw new InvalidWeightException(CustomExceptionMessages.INVALID_WEIGHT_MESSAGE);
        }

        if (!ApplicationUtils.isUrlValid(productDto.getImageURL(), productDto.getCategory())){
            log.warning("Invalid Url image");
            throw new InvalidUrlException(CustomExceptionMessages.INVALID_URL_MESSAGE);
        }

        productRepo.updateProduct(prod);
        log.info("Product successfully updated");
        log.info(prod.toString());
    }

    public List<ProductDto> getAllFoods(){

        List<Product> products=productRepo.findAllFoods();
        if(products.isEmpty()){
            log.warning("Invalid product category");
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_CATEGORY_MESSAGE1);
        }
        List<ProductDto> productsDto=new ArrayList<>();

        for (Product p: products) {
            ProductDto prodDto=ProductMapper.entityToDto(p);
            productsDto.add(prodDto);
        }
        return productsDto;
    }

}
