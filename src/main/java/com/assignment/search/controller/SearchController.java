package com.assignment.search.controller;

import com.assignment.search.model.Request;
import com.assignment.search.service.SearchServiceImpl;
import com.assignment.search.util.InputValidatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Rest Controller for search functionality
 */
@RestController
public class SearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchServiceImpl searchServiceImpl;

    /**
     * Endpoint for exposing search functionality
     * @param requestVO
     * @param bindingResult
     * @param user
     * @param response
     * @return
     * @throws ParseException
     */
    @PostMapping("/search")
    public List getSearchResult(@Valid @RequestBody Request requestVO, BindingResult bindingResult,
                                @AuthenticationPrincipal User user, HttpServletResponse response) throws ParseException {
        if(bindingResult.hasErrors()){
            if(bindingResult.hasFieldErrors("accountId")){
                LOGGER.error("Account id is not present in request");
                return Arrays.asList("Account Id should be present in the request");
            }
            LOGGER.error("request is not correctly formed");
            return Arrays.asList("Incorrect input. Please check the input. Date should be in DD.MM.YYYY format only");
        }
        if(InputValidatorImpl.validateUser(user, requestVO)){
            response.setStatus(401);
            LOGGER.error("User role is sending parameters");
            return Arrays.asList("User is not allowed to send parameters");
        }

        return this.searchServiceImpl.search(requestVO);
    }

    @GetMapping("/ping")
    public String ping(){
        return "SUCCESS";
    }
}
