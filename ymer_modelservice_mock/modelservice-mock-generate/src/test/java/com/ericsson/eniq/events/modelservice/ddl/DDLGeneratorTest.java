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
package com.ericsson.eniq.events.modelservice.ddl;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.modelservice.ddl.gen.DDLGenerator;
import com.ericsson.eniq.events.modelservice.ddl.gen.DDLGeneratorHadoop;

public class DDLGeneratorTest {

	DDLGenerator generator;
	String file = "e:\\edecmcc\\test\\table.xml";
	String dgfile = "e:\\edecmcc\\test\\datagen.xml";

	@Before
	public void setUp(){
		generator = new DDLGeneratorHadoop();
	}

	@Test
	public void testGenerateDDL() {

		generator.unmarshalXML(dgfile);

		final ArrayList<StringBuilder> statements = generator.generate();

		for (final StringBuilder statement : statements) {
			System.out.println(statement.toString());
		}
	}

}
