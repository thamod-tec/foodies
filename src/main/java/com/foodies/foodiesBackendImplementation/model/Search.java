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
        this.timestamp = System.currentTimeMillis();

    }
     // Getters and Setters
     public String getId(){
        return id;
     }

      public void setId(String id){
        this.id = id;
      }

      public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Double priceRange) {
        this.priceRange = priceRange;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }
}







    
}
