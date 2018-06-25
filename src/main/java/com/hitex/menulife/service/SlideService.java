/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.business.NewsBusiness;
import com.hitex.menulife.business.SlideBusiness;
import com.hitex.menulife.model.News;
import com.hitex.menulife.model.RequestData;
import com.hitex.menulife.model.ResponseData;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class SlideService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public SlideService() {
    }

    @POST
    @Path("/getSlides")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getSlides(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            SlideBusiness slideBusiness = new SlideBusiness();
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

            response = new ResponseData("0", "thanh cong", slideBusiness.getListSlides());
                
            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
}
