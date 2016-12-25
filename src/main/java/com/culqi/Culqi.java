package com.culqi;

import com.culqi.controller.*;
import com.culqi.model.*;


/**
 * Created by culqi on 12/23/16.
 */
public class Culqi {

    Secure secure = new Secure();

    public Culqi(String COD_ECOMERCE, String API_KEY) {
        secure.setCOD_ECOMERCE(COD_ECOMERCE);
        secure.setAPI_KEY(API_KEY);
    }

    public String createToken(String card_number, String currency_code, String cvv, int expiration_month, int expiration_year,
                              String fingerprint, String last_name, String email, String first_name) throws Exception {
        Token token = new Token();
        token.setCard_number(card_number);
        token.setCurrency_code(currency_code);
        token.setFingerprint(fingerprint);
        token.setCvv(cvv);
        token.setEmail(email);
        token.setExpiration_month(expiration_month);
        token.setExpiration_year(expiration_year);
        token.setLast_name(last_name);
        token.setFirst_name(first_name);
        Tokens tokens = new Tokens();
        return tokens.create(secure,token).getId();
    }

    public String createCharge(String address, String address_city, int amout, String country_code, String currency_code, String cvv, String email,
                               String first_name, int installments, String last_name, String metadata, int order_id, int phone_number, String product_description,
                               String toke_id) throws Exception {
        Charge charge = new Charge();
        charge.setAddress(address);
        charge.setAddress_city(address_city);
        charge.setAmount(amout);
        charge.setCountry_code(country_code);
        charge.setCurrency_code(currency_code);
        charge.setCvv(cvv);
        charge.setEmail(email);
        charge.setFirst_name(first_name);
        charge.setInstallments(installments);
        charge.setLast_name(last_name);
        charge.setMetadata(metadata);
        charge.setOrder_id(order_id);
        charge.setPhone_number(phone_number);
        charge.setProduct_description(product_description);
        charge.setToken_id(toke_id);
        return new Charges().createCharge(secure,charge).getId();
    }

    public String createPlan(String alias, int amount, String currency_code, String interval, int interval_count, int limit, String name,
                             int trial_days) throws Exception {
        Plan plan = new Plan();
        plan.setAlias(alias);
        plan.setAmount(amount);
        plan.setCurrency_code(currency_code);
        plan.setInterval(interval);
        plan.setInterval_count(interval_count);
        plan.setLimit(limit);
        plan.setName(name);
        return new Plans().createPlan(secure, plan).getMessage();
    }

    public String createSubscription(String address, String address_city, String country_code, String email, String last_name, String first_name,
                                     int phone_number, String plan_alias, String token_id) throws Exception {
        Subscription subscription = new Subscription();
        subscription.setAddress(address);
        subscription.setAddress_city(address_city);
        subscription.setCountry_code(country_code);
        subscription.setEmail(email);
        subscription.setLast_name(last_name);
        subscription.setFirst_name(first_name);
        subscription.setPhone_number(phone_number);
        subscription.setPlan_alias(plan_alias);
        subscription.setToken_id(token_id);
        return new Subscriptions().createSubscription(secure,subscription).getMessage();
    }

    public String createRefund(int amount, String charge_id, String reason) throws Exception {
        Refund refund = new Refund();
        refund.setAmount(amount);
        refund.setCharge_id(charge_id);
        refund.setReason(reason);
        return new Refunds().createRefund(secure, refund).getMessage();
    }

}