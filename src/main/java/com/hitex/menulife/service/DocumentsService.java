package com.hitex.menulife.service;

import com.google.gson.Gson;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.business.DocumentsBusiness;
import com.hitex.menulife.model.Documents;
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

public class DocumentsService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public DocumentsService() {
    }

    @POST
    @Path("/getAllDocuments")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllDocuments(InputStream incomingData) {
        try {
            DocumentsBusiness documentsBusiness = new DocumentsBusiness();
            ResponseDataPaging response = new ResponseDataPaging("1", "Không xác định", null, 0);

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
                ArrayList<Documents> listDocuments = documentsBusiness.getAllDocuments(page*limit, limit);
                                                
                if (listDocuments.isEmpty()) {
                    response = new ResponseDataPaging("1", "loi phan trang", null ,0 );
                } else {
                    int totalPage = (int) Math.ceil((float)documentsBusiness.countRow()/limit);
                    response = new ResponseDataPaging("0", "thanh cong", listDocuments, totalPage );
                }
            } else {
                response = new ResponseDataPaging("1", "moi dang nhap lai", null ,0);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
    
    @POST
    @Path("/getDocumentsById")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getDocumentsById(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            DocumentsBusiness documentsBusiness = new DocumentsBusiness();
            CheckBusiness checkBusiness = new CheckBusiness();

            Documents documents;
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
            if (checkBusiness.checkSession(String.valueOf(requestData.getSessionId()))) {
                org.json.JSONObject jsonObject = new org.json.JSONObject(new Gson().toJson(requestData.getWsRequest()));

                documents = documentsBusiness.getDocumentsById(Integer.valueOf(jsonObject.getString("id")));
                if (documents.getId() != null) {
                    response = new ResponseData("0", "thanh cong", documents);
                }
            } else {
                response = new ResponseData("1", "Moi dang nhap lai", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
}
