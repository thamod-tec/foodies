package com.foodies.foodiesBackendImplementation.repository;

import com.foodies.foodiesBackendImplementation.model.Search;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface SearchRepository extends MongoRepository<Search, String> {
    List<Search> findByUserId(String userId);
    
    List<Search> findByCategory(String category);
    
    @Query("{'query': {$regex: ?0, $options: 'i'}}")
    List<Search> findByQueryContaining(String queryText);
    
    List<Search> findByLocationAndCategory(String location, String category);
    
    List<Search> findByPriceRangeLessThanEqual(Double maxPrice);
    
    List<Search> findByIsVegetarian(Boolean isVegetarian);
    
    void deleteByUserId(String userId);
}