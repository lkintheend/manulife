/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.business.ProductsGroupBusiness;
import com.hitex.menulife.model.ProductsGroup;
import com.hitex.menulife.model.RequestData;
import com.hitex.menulife.model.ResponseData;
import com.hitex.menulife.model.ResponseDataPaging;
import java.io.BufferedReader;
import java.io.IOException;
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
import org.json.JSONException;

/**
 *
 * @author lkintheend
 */
@Path("/")

public class ProductsGroupService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public ProductsGroupService() {
    }

    @POST
    @Path("/getAllProductsGroup")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllProductsGroup(InputStream incomingData) {
        try {
            ResponseDataPaging response = new ResponseDataPaging("1", "Không xác định", null, 0);
            Gson gson = new Gson();
            ProductsGroupBusiness productsGroupBusiness = new ProductsGroupBusiness();

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

            if (checkBusiness.checkSession((String) requestData.getSessionId())) {
                page = jsonObject.getInt("page");
                limit = jsonObject.getInt("limit");
                ArrayList<ProductsGroup> listProductsGroup = productsGroupBusiness.getAllProductsGroup(page* limit, limit);
                if (listProductsGroup.isEmpty()) {
                    response = new ResponseDataPaging("1", "Loi phan trang", null,0);
                } else {
                    int totalPage = (int) Math.ceil((float)productsGroupBusiness.countRow()/limit);
                    response = new ResponseDataPaging("0", "thanh cong", listProductsGroup,totalPage);
                }

            } else {
                response = new ResponseDataPaging("1", "moi dang nhap lai", null,0);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getProductsGroupById")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getProductsGroupById(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            ProductsGroupBusiness productsGroupBusiness = new ProductsGroupBusiness();
            CheckBusiness checkBusiness = new CheckBusiness();

            ProductsGroup productsGroup;
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
                String line = null;
                while ((line = in.readLine()) != null) {
                    crunchifyBuilder.append(line);
                }
            } catch (IOException e) {

                return Response.status(200).entity(new Gson().toJson(response)).build();
            }

            RequestData requestData = gson.fromJson(crunchifyBuilder.toString(), RequestData.class);
            org.json.JSONObject jsonObject = new org.json.JSONObject(new Gson().toJson(requestData.getWsRequest()));

            productsGroup = productsGroupBusiness.getProductsGroupById(Integer.valueOf(jsonObject.getString("id")));

            if (checkBusiness.checkSession((String) requestData.getSessionId())) {

                if (productsGroup.getId() != null) {
                    response = new ResponseData("0", "thanh cong", productsGroup);
                } else {
                    response = new ResponseData("1", "Khon tim thay id", null);

                }
            } else {
                response = new ResponseData("1", "Moi dang nhap lai", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (JsonSyntaxException | NumberFormatException | JSONException e) {
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
}
