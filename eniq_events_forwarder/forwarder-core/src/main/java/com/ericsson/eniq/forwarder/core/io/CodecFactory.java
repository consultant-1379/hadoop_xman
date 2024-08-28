/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.forwarder.core.io;

import java.net.MalformedURLException;
import java.rmi.*;

import com.ericsson.oss.itpf.datalayer.modelservicelib.ModelServiceInterface;
import com.ericsson.oss.itpf.datalayer.modelservicelib.codec.*;
import com.ericsson.eniq.events.modelservice.*;
import com.ericsson.eniq.forwarder.core.properties.PropertyManager;

public class CodecFactory {

    private static final String MODEL_SERVICE_ADDRESS = PropertyManager.getProperty("model.service");
    private static ModelServiceInterface modelService = null;

    private CodecFactory() {}
	
	private static void initModelService() {
        try {
            modelService = (ModelServiceInterface) Naming.lookup(MODEL_SERVICE_ADDRESS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
	}
	
    public static Codec getCodec(String eventType) {
        Codec requiredCodec = null;

        if (modelService == null) {
        	initModelService();
        }
        
        try {
			requiredCodec = modelService.getCodec(eventType).newInstance();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

        return requiredCodec;
    }	
	
    public static void setModelService(ModelServiceInterface newModelService) {
    	modelService = newModelService;
    }
}
