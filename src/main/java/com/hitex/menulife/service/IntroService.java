/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.hitex.menulife.business.IntroBusiness;
import com.hitex.menulife.model.Intro;
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
public class IntroService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public IntroService() {
    }

    @POST
    @Path("/getIntro")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getIntro(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            IntroBusiness introBusiness = new IntroBusiness();

            Intro intro;
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

            intro = introBusiness.getIntro();
            if (intro.getId() != null) {
                response = new ResponseData("0", "thanh cong", intro);
            } else {
                response = new ResponseData("1", "khong tim thay intro", intro);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

}
