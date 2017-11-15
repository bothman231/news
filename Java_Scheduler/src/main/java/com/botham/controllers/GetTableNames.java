package com.botham.controllers;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;

import com.botham.schedule.ScheduledTasks;

class GetTableNames implements DatabaseMetaDataCallback {

	private static final Logger log = LoggerFactory.getLogger(GetTableNames.class);
	
        public Object processMetaData(DatabaseMetaData dbmd) throws SQLException {
            ResultSet rs  = dbmd.getTables(dbmd.getUserName(),null,null,new String[] {"TABLE"});
            ArrayList l = new ArrayList();
            while (rs.next()) {
            	l.add(rs.getString(3));
            	log.info(rs.getString(3));
            }
            return l;
        }
    }
