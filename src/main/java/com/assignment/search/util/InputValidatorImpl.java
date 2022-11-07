package com.assignment.search.util;

import com.assignment.search.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Util class to validate the input
 */
public class InputValidatorImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputValidatorImpl.class);

    private InputValidatorImpl(){

    }

    /**
     * Method to validate the search input
     * @param requestVO
     * @return
     */
    public  static List<String> validateInput(Request requestVO) {
        ArrayList<String> criteriaList = new ArrayList<>();
        validateRequest(requestVO,criteriaList);
        if("".equalsIgnoreCase(requestVO.getFromAmount()) || "".equalsIgnoreCase(requestVO.getToAmount())){
            LOGGER.error("Proper Amount range is not provided");
            criteriaList.add(SearchConstants.AMOUNT_ERROR);
        } else if("".equalsIgnoreCase(requestVO.getToDate()) || "".equalsIgnoreCase(requestVO.getFromDate())){
            LOGGER.error("Proper Date range is not provided");
            criteriaList.add(SearchConstants.DATE_ERROR);
        } else if((requestVO.getFromAmount() != null && requestVO.getToAmount() == null) || (requestVO.getFromAmount() == null && requestVO.getToAmount() != null)){
            LOGGER.error("Proper Amount range is not provided");
            criteriaList.add(SearchConstants.AMOUNT_ERROR);
        } else if((requestVO.getToDate() != null && requestVO.getFromDate() == null) || (requestVO.getToDate() == null && requestVO.getFromDate() != null)){
            LOGGER.error("Proper Date range is not provided");
            criteriaList.add(SearchConstants.DATE_ERROR);
        }
        return criteriaList;
    }

    /**
     * Method to validate USER role and check if it have any parameters.
     * @param user
     * @param request
     * @return
     */
    public static Boolean validateUser(User user, Request request){
        List<String> criteria = new ArrayList<>();
        if(user.getAuthorities().toString().equalsIgnoreCase("[ROLE_USER]") && (validateRequest(request, criteria).contains(SearchConstants.DATE) || validateRequest(request, criteria).contains(SearchConstants.AMOUNT))){
            return true;
        }
        return false;
    }

    private static List<String> validateRequest(Request requestVO, List<String> criteriaList){
        if(StringUtils.hasText(requestVO.getFromAmount()) && StringUtils.hasText(requestVO.getToAmount())){
            criteriaList.add(SearchConstants.AMOUNT);
        }
        if(StringUtils.hasText(requestVO.getToDate()) && StringUtils.hasText(requestVO.getFromDate())){
            criteriaList.add(SearchConstants.DATE);
        }
        if(requestVO.getFromAmount() == null && requestVO.getToAmount() == null
                && requestVO.getToDate() == null && requestVO.getFromDate() == null){
            criteriaList.add(SearchConstants.NO_CRITERIA);
        }
        return  criteriaList;
    }
}
