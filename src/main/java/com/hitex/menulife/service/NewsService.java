/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.business.NewsBusiness;
import com.hitex.menulife.model.News;
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

public class NewsService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public NewsService() {
    }

    @POST
    @Path("/getAllNews")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllNews(InputStream incomingData) {
        try {

            ResponseDataPaging response = new ResponseDataPaging("1", "Không xác định", null, 0);
            NewsBusiness newsBusiness = new NewsBusiness();

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

            page = jsonObject.getInt("page");
            limit = jsonObject.getInt("limit");
            ArrayList<News> listNews = newsBusiness.getAllNews(page * limit, limit);
            int totalPage = (int) Math.ceil((float) newsBusiness.countRow() / limit);

            if (listNews.isEmpty()) {
                response = new ResponseDataPaging("1", "loi phan trang", null, totalPage);
            } else {
                response = new ResponseDataPaging("0", "thanh cong", listNews, totalPage);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getNewsById")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getNewsById(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            NewsBusiness newsBusiness = new NewsBusiness();
            CheckBusiness checkBusiness = new CheckBusiness();
            News news;
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

            news = newsBusiness.getNewsById(Integer.valueOf(jsonObject.getString("id")));
            if (news.getId() != null) {
                response = new ResponseData("0", "thanh cong", news);
            } else {
                response = new ResponseData("0", "khong tim thay id", news);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

//    @POST
//    @Path("/getIntro")
//    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
//    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
//    public Response getIntro(InputStream incomingData) {
//        try {
//            StringBuilder crunchifyBuilder = new StringBuilder();
//            ResponseData response = new ResponseData("1", "Không xác định", null);
//            Gson gson = new Gson();
//            NewsBusiness newsBusiness = new NewsBusiness();
//
//            CheckBusiness checkBusiness = new CheckBusiness();
//
//            News news;
//            try {
//                BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
//                String line = null;
//                while ((line = in.readLine()) != null) {
//                    crunchifyBuilder.append(line);
//                }
//            } catch (Exception e) {
//
//                return Response.status(200).entity(new Gson().toJson(response)).build();
//            }
//            System.out.println("Data Received: " + crunchifyBuilder.toString());
//
//            RequestData requestData = gson.fromJson(crunchifyBuilder.toString(), RequestData.class);
//            org.json.JSONObject jsonObject = new org.json.JSONObject(new Gson().toJson(requestData.getWsRequest()));
//
//            news = newsBusiness.getIntro();
//            if (news.getId() != null) {
//                response = new ResponseData("0", "thanh cong", news);
//            } else {
//                response = new ResponseData("1", "khong tim thay intro", news);
//            }
//
//            return Response.status(200).entity(new Gson().toJson(response)).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseData response = new ResponseData("1", "Không xác định", null);
//            return Response.status(200).entity(new Gson().toJson(response)).build();
//        }
//    }

    @POST
    @Path("/getCustomerCare")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getCustomerCare(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            NewsBusiness newsBusiness = new NewsBusiness();

            CheckBusiness checkBusiness = new CheckBusiness();

            News news;
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

            news = newsBusiness.getCustomerCare();
            if (news.getId() != null) {
                response = new ResponseData("0", "thanh cong", news);
            } else {
                response = new ResponseData("1", "khong tim thay Customer Care", news);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

}
