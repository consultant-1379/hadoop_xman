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
package com.ericsson.oss.datalayer.event;

import com.ericsson.oss.itpf.common.event.ComponentEvent;

public interface Event extends ComponentEvent, HeaderAware, ResourceIdAware, RopAware {
	
	long getTypeId();
}
