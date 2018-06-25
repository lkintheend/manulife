/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.business.ProductBusiness;
import com.hitex.menulife.model.Product;
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

public class ProductService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public ProductService() {
    }

    @POST
    @Path("/getAllProducts")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllProducts(InputStream incomingData) {
        try {

            ResponseDataPaging response = new ResponseDataPaging("1", "Không xác định", null, 0);
            ProductBusiness productBusiness = new ProductBusiness();

            StringBuilder crunchifyBuilder = new StringBuilder();
            Gson gson = new Gson();
            CheckBusiness checkBusiness = new CheckBusiness();

            int page, limit;

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

            if (checkBusiness.checkSession((String) requestData.getSessionId())) {
                page = jsonObject.getInt("page");
                limit = jsonObject.getInt("limit");
                ArrayList<Product> listProduct = productBusiness.getAllProduct(page * limit, limit);
                int totalPage = (int) Math.ceil((float) productBusiness.countRow() / limit);

                if (listProduct.isEmpty()) {
                    response = new ResponseDataPaging("1", "Loi phan trang", null, totalPage);
                } else {
                    response = new ResponseDataPaging("0", "thanh cong", listProduct, totalPage);
                }

            } else {

                response = new ResponseDataPaging("1", "moi dang nhap lai", null, 0);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getTopProducts")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getTopProducts(InputStream incomingData) {
        try {

            ResponseData response = new ResponseData("1", "Không xác định", null);
            ProductBusiness productBusiness = new ProductBusiness();

            StringBuilder crunchifyBuilder = new StringBuilder();
            Gson gson = new Gson();
            CheckBusiness checkBusiness = new CheckBusiness();

            int page, limit;

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

            ArrayList<Product> listProduct = productBusiness.getTopProduct();
            if (listProduct.isEmpty()) {
                response = new ResponseData("1", "Loi", null);
            } else {
                response = new ResponseData("0", "thanh cong", listProduct);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getProductById")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getProductById(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            ProductBusiness productBusiness = new ProductBusiness();

            CheckBusiness checkBusiness = new CheckBusiness();

            Product product;
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

            product = productBusiness.getProductById(Integer.valueOf(jsonObject.getString("id")));
            if (product.getId() != null) {
                response = new ResponseData("0", "thanh cong", product);
            } else {
                response = new ResponseData("1", "khong tim thay id", product);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

}
