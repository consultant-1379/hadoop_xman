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
package com.ericsson.eniq.events.modelservice.ddl.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.*;

import com.ericsson.eniq.events.modelservice.ddl.helper.SQLSyntaxHelper;
import com.ericsson.eniq.events.modelservice.jaxb.*;

public class DDLGeneratorHadoop implements DDLGenerator {
	Database data = null;

	@Override
	public ArrayList<StringBuilder> generate() {

		final ArrayList<StringBuilder> statements = new ArrayList<StringBuilder>();

		for (final TableType table : data.getTable()) {
			final StringBuilder ddl = new StringBuilder();
			final List<ColumnType> cols = table.getColumn();

			ddl.append(SQLSyntaxHelper.CREATE_TABLE + " ");
			ddl.append(table.getNamePrefix() + table.getName());
			ddl.append(SQLSyntaxHelper.SQL_BRACE_OPEN);
			ddl.append(SQLSyntaxHelper.NEW_LINE);
			createColumns(cols,ddl);

			ddl.append(SQLSyntaxHelper.SQL_BRACE_CLOSE);

			ddl.append(SQLSyntaxHelper.STORAGE_TEXT + " " + table.getStorage());
			ddl.append(SQLSyntaxHelper.SEMI_COLON);
			statements.add(ddl);
			System.out.println("Partition: " + isPartitioningRequired(table));

		}

		return statements;
	}

	/**
	 * @param cols
	 * @param ddl
	 */
	private void createColumns(final List<ColumnType> cols, final StringBuilder ddl) {

		for (final ColumnType col : cols) {
			final String columnLine = buildColumnLine(col);
			ddl.append(columnLine);
			// add comma to the end if not last column
			if (cols.indexOf(col) + 1 < cols.size()) {
				ddl.append(SQLSyntaxHelper.COMMA_SEPARATOR);
			}
			ddl.append(SQLSyntaxHelper.NEW_LINE);
		}

	}

	/**
	 * @param table
	 * @return
	 */
	private boolean isPartitioningRequired(final TableType table) {
		return table.getNTables() > 1;
	}

	/**
	 * @param column
	 * @return
	 */
	private String buildColumnLine(final ColumnType column) {
		final String line = "   " + column.getName() + " "
				+ column.getDataType().getType();
		return line.toUpperCase();
	}

	@Override
	public void unmarshalXML(final String file) {
		try {
			// create JAXB context and initializing Marshaller
			final JAXBContext jaxbContext = JAXBContext
					.newInstance(Database.class);

			final Unmarshaller jaxbUnmarshaller = jaxbContext
					.createUnmarshaller();

			// specify the location and name of xml file to be read
			final File XMLfile = new File(file);

			data = (Database) jaxbUnmarshaller.unmarshal(XMLfile);

		} catch (final JAXBException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Override public ArrayList<StringBuilder> generate(final String xmlFile)
	 * { final ArrayList<StringBuilder> statements = new
	 * ArrayList<StringBuilder>(); StringBuilder ddl = null;
	 * 
	 * try { // create JAXB context and initializing Marshaller final
	 * JAXBContext jaxbContext = JAXBContext.newInstance(Events.class);
	 * 
	 * final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	 * 
	 * // specify the location and name of xml file to be read final File
	 * XMLfile = new File(xmlFile);
	 * 
	 * // this will create Java object - country from the XML file final Events
	 * list = (Events) jaxbUnmarshaller .unmarshal(XMLfile);
	 * 
	 * final Field[] fields = CallFailure.class.getFields(); ddl = new
	 * StringBuilder(); ddl.append("CREATE"); for(final Field field : fields) {
	 * ddl.append(" " + field.getName()); }
	 * 
	 * statements.add(ddl);
	 * 
	 * // for(final CallFailure table : list.getClazz()) { // ddl = new
	 * StringBuilder(); // ddl.append("CREATE"); // ddl.append(" "+
	 * table..getClass().getSimpleName().toUpperCase()); // ddl.append(" cols: "
	 * + table.getEventId() + " , " // + table.getCauseValue()); //
	 * statements.add(ddl); // }
	 * 
	 * } catch (final JAXBException e) { e.printStackTrace(); } return
	 * statements; }
	 */
}
