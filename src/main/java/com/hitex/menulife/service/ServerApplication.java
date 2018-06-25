/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.service;

import java.util.HashSet;
import java.util.Set;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;


/**
 *
 * @author Asus
 */

    public class ServerApplication extends ResourceConfig {

    public ServerApplication() {
        this.packages("com.hitex.menulife.service");
        Set<Class<?>> resourceClass = new HashSet<>();
        resourceClass.add(MemberService.class);
        this.register(MultiPartFeature.class);
        this.register(resourceClass);
    }
}


