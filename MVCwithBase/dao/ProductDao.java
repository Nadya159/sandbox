package by.javaguru.MVCwithBase.dao;

import by.javaguru.dao.DaoBase;
import by.javaguru.entity.Product;
import jakarta.persistence.EntityManager;

public class ProductDao extends DaoBase<Integer, Product> {
    public ProductDao(EntityManager entityManager) {
        super(Product.class, entityManager);
    }
}
