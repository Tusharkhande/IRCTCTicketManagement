package com.tk.search_service.controller;

import com.tk.search_service.dto.SearchRequest;
import com.tk.search_service.dto.SearchResponse;
import com.tk.search_service.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public List<SearchResponse> search(@RequestBody SearchRequest searchRequest) {
        return searchService.search(searchRequest);
    }


}
