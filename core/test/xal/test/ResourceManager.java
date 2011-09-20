//
// ResourceManager.java
// Open XAL 
//
// Created by Tom Pelaia on 9/13/11
// Copyright 2011 Oak Ridge National Lab. All rights reserved.
//

package xal.test;

import xal.smf.data.XMLDataManager;

import xal.smf.Accelerator;

import java.util.*;
import java.net.URL;


/** ResourceManager is not a test suite, but rather it is a utility used by test suites to load recources from the common test resources package. */
public class ResourceManager {
    /** 
     * Get the URL to the specified resource in the common test resources package.
     * @param resourcePath full modified package path (see the notes for the getResource() method of java.lang.Class) relative to the resources location
     * @return URL to the resource or null if there is none found
     */
    static public URL getResourceURL( final String resourcePath ) {
        return ResourceManager.class.getResource( "/xal/" + resourcePath );
    }
    
    
    /** Load and get the default test accelerator */
    static public Accelerator getTestAccelerator() {
        return getAcceleratorForResource( "config/main.xal" );
    }
    
    
    /** 
     * Load and get the accelerator at the specified resource path
     * @param resourcePath fully qualified modified package path to the resource (see the notes for the getResource() method of java.lang.Class)
     */
    static public Accelerator getAcceleratorForResource( final String resourcePath ) {
        final URL opticsURL = getResourceURL( resourcePath );
        return opticsURL != null ? XMLDataManager.getInstance( opticsURL ).getAccelerator() : null;
    }
}