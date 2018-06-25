/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import com.hitex.menulife.business.MemberBusiness;
import com.hitex.menulife.model.RequestData;
import com.hitex.menulife.model.ResponseData;
import com.google.gson.Gson;
import com.hitex.menulife.business.CheckBusiness;
import com.hitex.menulife.model.Member;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.hitex.menulife.util.EmailCreate;
import com.hitex.menulife.util.StringEncrypt;
import com.hitex.menulife.util.Util;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author Asus
 */
@Path("/")
public class MemberService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewsService
     */
    public MemberService() {
    }

    @POST
    @Path("/getNameMembers")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getNameMembers(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            MemberBusiness membersBI = new MemberBusiness();
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
            if (jsonObject.has("language")) {
                String language = jsonObject.getString("language");
                ArrayList<String> lstMembers = membersBI.getNameMembers();
                response = new ResponseData("0", "Language: " + language, lstMembers);
            }
            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response insertMember(InputStream incomingData) throws EmailException {
        EmailCreate emailCreate = new EmailCreate();
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            MemberBusiness membersBI = new MemberBusiness();

            String name, fullName, passWord, phone, email;
            String rememberToken;

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

            name = jsonObject.getString("name");
            fullName = jsonObject.getString("fullName");
            passWord = jsonObject.getString("passWord");
            phone = jsonObject.getString("phone");
            email = jsonObject.getString("email");

            if (!membersBI.checkMemberEmail(email)) {
                rememberToken = membersBI.insertMember(name, fullName, passWord, phone, email);
                if (!rememberToken.equals("")) {
                    System.out.println(rememberToken + email);
                    emailCreate.newEmail(email, rememberToken);
                    response = new ResponseData("0", "Dang ki thanh cong email: " + email, null);
                }
            } else {
                response = new ResponseData("1", "email da ton tai", null);
            }
            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response valiDateMember(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            MemberBusiness membersBI = new MemberBusiness();
            String email;
            String passWord;
            Member member;

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

            email = jsonObject.getString("email");
            passWord = jsonObject.getString("passWord");
            member = membersBI.validateMember(email, passWord);

            System.out.println(member);
            response = membersBI.checkMember(member);

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Thiếu tham số ", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/getMemberInfo")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getMemberInfo(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();
            MemberBusiness membersBI = new MemberBusiness();
            String email;
            Member member;
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

            email = jsonObject.getString("email");

            if (checkBusiness.checkSession((String) requestData.getSessionId())) {
                member = membersBI.getMemberInfo(email);

                System.out.println(member);
                response = new ResponseData("0", "Thong tin member", member);
            } else {
                response = new ResponseData("1", "Moi dang nhap", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Thiếu tham số ", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @GET
    @Path("/{token}")
    @Produces("text/xml")
    public String activeUser(@PathParam("token") String token) {
        MemberBusiness membersBI = new MemberBusiness();
        if (membersBI.activeMember(token)) {
            return "actived";
        } else {
            return "cant actived";
        }

    }
    
    

    @POST
    @Path("/changePass")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response changePass(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();

            MemberBusiness membersBI = new MemberBusiness();
            String email = "";
            String oldPassWord = "";
            String newPassWord = "";

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

            email = jsonObject.getString("email");
            oldPassWord = jsonObject.getString("oldPassWord");
            newPassWord = jsonObject.getString("newPassWord");
            if (membersBI.changePass(email, oldPassWord, newPassWord)) {
                response = new ResponseData("1", "thanh cong", null);
            } else {
                response = new ResponseData("0", "sai ten dang nhap hoac mat khau", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/updateProfile")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response updateProfile(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();

            MemberBusiness membersBI = new MemberBusiness();
            CheckBusiness checkBusiness = new CheckBusiness();
            
            String email;
            String name;
            String fullName;
            String sex;
            String birthday;
            String phone;

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

            email = jsonObject.getString("email");
            fullName = jsonObject.getString("fullName");
            sex = jsonObject.getString("sex");
            birthday = Util.convertDate(jsonObject.getString("birthday"));
            phone = jsonObject.getString("phone");
            
            if (checkBusiness.checkSession((String) requestData.getSessionId())) {
                
                if (membersBI.updateProfile(email, fullName, sex, birthday, phone)) {
                    response = new ResponseData("0", "thanh cong", null);
                } else {
                    response = new ResponseData("0", "khong tim thay email", null);
                }
            } else {
                response = new ResponseData("1", "moi dang nhap lai", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }

    @POST
    @Path("/forgotPass")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response forgotPass(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();

            MemberBusiness membersBI = new MemberBusiness();
            String email = "";
            String id = "";
            EmailCreate emailCreate = new EmailCreate();

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

            email = jsonObject.getString("email");
            id = membersBI.getIdMember(email);
            
            if (id!="-1") {
                emailCreate.forgotPassMail(email, id);
                response = new ResponseData("0", "thanh cong", null);
            } else {
                response = new ResponseData("1", "that bai", null);
            }

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
    
    @POST
    @Path("/forgotPass2")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response forgotPass2(InputStream incomingData) {
        try {
            StringBuilder crunchifyBuilder = new StringBuilder();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            Gson gson = new Gson();

            MemberBusiness membersBI = new MemberBusiness();

            String idEncrypt = "";
            EmailCreate emailCreate = new EmailCreate();

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

            idEncrypt = jsonObject.getString("idEncypt");

            String newPass="";
            
            newPass = membersBI.forgotPass(StringEncrypt.getInstance().decrypt(idEncrypt));
            response = new ResponseData("1",newPass, newPass);

            return Response.status(200).entity(new Gson().toJson(response)).build();
        } catch (Exception e) {
            e.printStackTrace();
            ResponseData response = new ResponseData("1", "Không xác định", null);
            return Response.status(200).entity(new Gson().toJson(response)).build();
        }
    }
    
}
