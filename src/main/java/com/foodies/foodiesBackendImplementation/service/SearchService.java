package com.foodies.foodiesBackendImplementation.service;

import com.foodies.foodiesBackendImplementation.model.Search;
import com.foodies.foodiesBackendImplementation.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private SearchRepository searchRepository;

    public List<Search> getAllSearches() {
        return searchRepository.findAll();
    }

    public Search getSearchById(String id) {
        return searchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Search not found with id: " + id));
    }

    public List<Search> getSearchesByUser(String userId) {
        return searchRepository.findByUserId(userId);
    }

    public List<Search> searchByQuery(String query) {
        return searchRepository.findByQueryContaining(query);
    }

    public List<Search> searchByCategory(String category) {
        return searchRepository.findByCategory(category);
    }

    public List<Search> searchByLocationAndCategory(String location, String category) {
        return searchRepository.findByLocationAndCategory(location, category);
    }

    public List<Search> searchByPriceRange(Double maxPrice) {
        return searchRepository.findByPriceRangeLessThanEqual(maxPrice);
    }

    public List<Search> searchVegetarianOptions(Boolean isVegetarian) {
        return searchRepository.findByIsVegetarian(isVegetarian);
    }

    public Search saveSearch(Search search) {
        search.setTimestamp(System.currentTimeMillis());
        return searchRepository.save(search);
    }

    public void deleteSearch(String id) {
        searchRepository.deleteById(id);
    }

    public void deleteUserSearchHistory(String userId) {
        searchRepository.deleteByUserId(userId);
    }
}
