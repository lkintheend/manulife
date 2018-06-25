/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.business.ProductsPromotionsBusiness;
import com.hitex.menulife.model.ProductsPromotions;
import com.hitex.menulife.model.RequestData;
import com.hitex.menulife.model.ResponseData;
import com.hitex.menulife.model.ResponseDataPaging;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author lkintheend
 */
@Path("/")

public class ProductsPromotionsService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public ProductsPromotionsService() {
    }

    @POST
    @Path("/getAllProductsPromotions")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllProductsPromotions(InputStream incomingData) {
        try {

            ResponseDataPaging response = new ResponseDataPaging("1", "Không xác định", null, 0);
            ProductsPromotionsBusiness productsPromotionsBusiness = new ProductsPromotionsBusiness();
            Gson gson = new Gson();

            int page, limit;

            StringBuilder crunchifyBuilder = new StringBuilder();
            CheckBusiness checkBusiness = new CheckBusiness();

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
                String line = null;
                while ((line = in.readLine()) != null) {
                    crunchifyBuilder.append(line);
                }
            } catch (Exception e) {
                return Response.status(200).entity(new Gson().toJson(response)).build();
            }

            System.out.println("Data Received: " + crunchifyBuilder.toString());

            RequestData requestData = gson.fromJson(crunchifyBuilder.toString(), RequestData.class);
            org.json.JSONObject jsonObject = new org.json.JSONObject(new Gson().toJson(requestData.getWsRequest()));

            page = jsonObject.getInt("page");
            limit = jsonObject.getInt("limit");
            ArrayList<ProductsPromotions> listProductsPromotions = productsPromotionsBusiness.getAllProductssPromotions(page * limit, limit);
            int totalPage = (int) Math.ceil((float) productsPromotionsBusiness.countRow() / limit);
            if (!listProductsPromotions.isEmpty()) {

                response = new ResponseDataPaging("0", "thanh cong", listProductsPromotions, totalPage);
            } else {
                response = new ResponseDataPaging("1", "loi phan trang", null, totalPage);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getTopProductsPromotions")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getTopProductsPromotions(InputStream incomingData) {
        try {

            ResponseData response = new ResponseData("1", "Không xác định", null);
            ProductsPromotionsBusiness productsPromotionsBusiness = new ProductsPromotionsBusiness();
            Gson gson = new Gson();

            StringBuilder crunchifyBuilder = new StringBuilder();
            CheckBusiness checkBusiness = new CheckBusiness();

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
                String line = null;
                while ((line = in.readLine()) != null) {
                    crunchifyBuilder.append(line);
                }
            } catch (Exception e) {
                return Response.status(200).entity(new Gson().toJson(response)).build();
            }

            System.out.println("Data Received: " + crunchifyBuilder.toString());

            RequestData requestData = gson.fromJson(crunchifyBuilder.toString(), RequestData.class);
            org.json.JSONObject jsonObject = new org.json.JSONObject(new Gson().toJson(requestData.getWsRequest()));

            ArrayList<ProductsPromotions> listProductsPromotions = productsPromotionsBusiness.getTopProductssPromotions();
            if (!listProductsPromotions.isEmpty()) {
                response = new ResponseData("0", "thanh cong", listProductsPromotions);
            } else {
                response = new ResponseData("1", "loi", listProductsPromotions);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getProductsPromotionsById")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getProductsPromotionsById(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            ProductsPromotionsBusiness productsPromotionsBusiness = new ProductsPromotionsBusiness();
            CheckBusiness checkBusiness = new CheckBusiness();

            ProductsPromotions productsPromotions;
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
                String line = null;
                while ((line = in.readLine()) != null) {
                    crunchifyBuilder.append(line);
                }
            } catch (Exception e) {

                return Response.status(200).entity(new Gson().toJson(response)).build();
            }
            System.out.println("Data Received: " + crunchifyBuilder.toString());

            RequestData requestData = gson.fromJson(crunchifyBuilder.toString(), RequestData.class);
            org.json.JSONObject jsonObject = new org.json.JSONObject(new Gson().toJson(requestData.getWsRequest()));

            productsPromotions = productsPromotionsBusiness.getProductsPromotionsById(Integer.valueOf(jsonObject.getString("id")));

            if (productsPromotions.getId() != null) {
                response = new ResponseData("0", "thanh cong", productsPromotions);
            } else {
                response = new ResponseData("1", "Khong tim thay id", null);

            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
}
