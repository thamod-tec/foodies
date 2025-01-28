package com.foodies.foodiesBackendImplementation.model;
import  org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="searches")
public class Search {
    @Id
    private String id;
    private String query;
    private String category; // e.g., "RESTAURANT", "FOOD_ITEM", "CUISINE"
    private String userId;
    private long timestamp;
    private String location;
    private Double priceRange;
    private String sortBy;
    private Boolean isVegetarian;

    // Constructors
    public Search() {}

    public Search(String query, String category,String userId){
        this.query = query;
        this.category =category;
        this.userId = userId;
        this.timestamp = System.currentTimeMillis()

    }





    
}
