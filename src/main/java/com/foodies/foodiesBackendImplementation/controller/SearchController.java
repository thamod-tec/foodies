package com.foodies.foodiesBackendImplementation.controller;

import com.foodies.foodiesBackendImplementation.model.Search;
import com.foodies.foodiesBackendImplementation.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    // Get all searches (Admin)
    @GetMapping
    public List<Search> getAllSearches() {
        return searchService.getAllSearches();
    }

    // Get search by ID
    @GetMapping("/{id}")
    public Search getSearchById(@PathVariable String id) {
        return searchService.getSearchById(id);
    }

    // Get user's search history
    @GetMapping("/user/{userId}")
    public List<Search> getUserSearchHistory(@PathVariable String userId) {
        return searchService.getSearchesByUser(userId);
    }

    // Search by query text
    @GetMapping("/query")
    public List<Search> searchByQuery(@RequestParam String q) {
        return searchService.searchByQuery(q);
    }

    // Search by category
    @GetMapping("/category/{category}")
    public List<Search> searchByCategory(@PathVariable String category) {
        return searchService.searchByCategory(category);
    }

    // Search by location and category
    @GetMapping("/location")
    public List<Search> searchByLocationAndCategory(
            @RequestParam String location,
            @RequestParam String category) {
        return searchService.searchByLocationAndCategory(location, category);
    }

    // Search by price range
    @GetMapping("/price")
    public List<Search> searchByPriceRange(@RequestParam Double maxPrice) {
        return searchService.searchByPriceRange(maxPrice);
    }

    // Search vegetarian options
    @GetMapping("/vegetarian")
    public List<Search> searchVegetarianOptions(@RequestParam Boolean isVegetarian) {
        return searchService.searchVegetarianOptions(isVegetarian);
    }

    // Save a new search
    @PostMapping
    public Search saveSearch(@RequestBody Search search) {
        return searchService.saveSearch(search);
    }

    // Delete a search
    @DeleteMapping("/{id}")
    public void deleteSearch(@PathVariable String id) {
        searchService.deleteSearch(id);
    }

    // Delete user's search history
    @DeleteMapping("/user/{userId}")
    public void deleteUserSearchHistory(@PathVariable String userId) {
        searchService.deleteUserSearchHistory(userId);
    }
}
