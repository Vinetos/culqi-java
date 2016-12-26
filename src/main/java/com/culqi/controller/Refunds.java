package com.culqi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.culqi.model.Refund;
import com.culqi.model.Secure;
import com.culqi.modelreponse.RefundResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.culqi.util.Result;
import com.culqi.util.Util;

/**
 * Created by culqi on 12/23/16.
 */
public class Refunds {

    private static final String URL = "/refunds/";

    Util util = new Util();

    ObjectMapper mapper = new ObjectMapper();

    public Result createRefund(Secure secure, Refund refund) throws Exception {
        Result result = new Result();
        HttpResponse response;
        String jsonData = mapper.writeValueAsString(refund);
        response = util.response(secure, URL, jsonData);
        HttpEntity entity = response.getEntity();
        String statusCode = response.getStatusLine().toString();
        // get json result to string
        String jsonResult = EntityUtils.toString(entity,"UTF-8");
        // convert json string to object
        Result resultError = util.getErrorMessage(statusCode,jsonResult);
        if(!resultError.getMessage().equals("")){
            result.setId(resultError.getId());
            result.setMessage(resultError.getMessage());
            result.setStatus(resultError.getStatus());
        }
        if(statusCode.contains("201")) {
            RefundResponse refundResponse = mapper.readValue(jsonResult, RefundResponse.class);
            result.setId(refundResponse.getId());
            result.setMessage(refundResponse.getReason());
            result.setStatus("201");
        }
        return  result;
    }

}
