/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.itpf.datalayer.query;

import java.io.*;
import java.util.Random;

public class CreateData {
	public static void main(String[] args) throws Exception {
		File f = new File("E:\\emicned\\haddop.txt");
		PrintWriter pw = new PrintWriter(new FileWriter(f));
		Random r = new Random();
		for (int i = 0; i < 10000000; i++) {
			pw.println(r.nextInt(999999) + "," + r.nextDouble());
		}
		pw.close();
	}
}
