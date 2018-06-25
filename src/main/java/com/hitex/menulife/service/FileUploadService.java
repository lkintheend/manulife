package com.hitex.menulife.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

//import org.glassfish.jersey.media.multipart.FormDataParam;
@Path("/")
public class FileUploadService {

    @Context
    private UriInfo context;

    public FileUploadService() {

    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
//        String fileLocation = "/home/lkintheend/WORK_DDL/" + fileDetail.getFileName();
        String fileLocation = "/home/manulife/public_html/public/upload/img/a.jpg";
        //saving file  
        System.out.println("filetype" + fileDetail.getType() + "" + fileDetail.getFileName());
        try {
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            String output = "File successfully uploaded to : " + fileLocation;

            out.flush();
            out.close();
            return Response.status(200).entity(output).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Response.status(200).entity(null).build();
    }
}
