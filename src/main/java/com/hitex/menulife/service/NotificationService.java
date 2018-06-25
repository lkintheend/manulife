/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

/**
 *
 * @author lkintheend
 */
import com.google.gson.Gson;
import com.hitex.menulife.business.NotificationBusiness;
import com.hitex.menulife.model.Notification;
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

@Path("/")

public class NotificationService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public NotificationService() {
    }

    @POST
    @Path("/getListNotificationByUserId")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getListNotificationByUserId(InputStream incomingData) {
        try {
            NotificationBusiness notificationBusiness = new NotificationBusiness();
            ResponseData response = new ResponseData("1", "Không xác định", null);

            StringBuilder crunchifyBuilder = new StringBuilder();
            Gson gson = new Gson();

            String userId;
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
            try {
                userId = jsonObject.getString("userId");
            } catch (Exception e) {
                userId = "0";
            }
            
            page = jsonObject.getInt("page");
            limit = jsonObject.getInt("limit");

            ArrayList<Notification> listNotification = notificationBusiness.getListNotificationByUserId(userId,page*limit, limit);


            if (listNotification.isEmpty()) {
                response = new ResponseData("0", "", listNotification);
            } else {
                int totalPage = (int) Math.ceil((float)notificationBusiness.countRowByUserId(userId)/limit);
                response = new ResponseDataPaging("0", "thanh cong", listNotification, totalPage);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }

    }

    @POST
    @Path("/countNotificationUnseenByUserId")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response countNotificationUnseenByUserId(InputStream incomingData) {
        try {
            NotificationBusiness notificationBusiness = new NotificationBusiness();
            ResponseData response = new ResponseData("1", "Không xác định", null);

            StringBuilder crunchifyBuilder = new StringBuilder();
            Gson gson = new Gson();

            String userId;

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

            userId = jsonObject.getString("userId");

            int count = notificationBusiness.countNotificationUnseenByUserId(userId);

            response = new ResponseData("0", "lay thong tin thanh cong", count);

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }

    }

    @POST
    @Path("/getDetailNotificationById")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getDetailNotificationById(InputStream incomingData) {
        try {
            NotificationBusiness notificationBusiness = new NotificationBusiness();
            ResponseData response = new ResponseData("1", "Không xác định", null);

            StringBuilder crunchifyBuilder = new StringBuilder();
            Gson gson = new Gson();

            String id;

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

            id = jsonObject.getString("id");

            Notification notification = notificationBusiness.getDetailNotificationById(id);

            if (notification != null) {
                response = new ResponseData("0", "thanh cong", notification);
            } else {
                response = new ResponseData("1", "cant get notification", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }

    }

    @POST
    @Path("/updateSeenNotification")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response updateSeenNotification(InputStream incomingData) {
        try {
            NotificationBusiness notificationBusiness = new NotificationBusiness();
            ResponseData response = new ResponseData("1", "Không xác định", null);

            StringBuilder crunchifyBuilder = new StringBuilder();
            Gson gson = new Gson();

            String id;

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

            id = jsonObject.getString("id");

            notificationBusiness.updateSeenNotification(id);

            response = new ResponseData("0", "thanh cong", null);

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }

    }

}
