/*
 * File : PaginationInterceptor.java
 * date : Sep 18, 2012 9:55:09 AM
 */
package com.mic.generator.beforecommon.dao.interceptor;

/**
* 此拦截器可实现数据列表的排序及物理分页
* <br>User: admin
* <br>DateTime: Sep 18, 2012 9:55:09 AM
* <br>Version 1.0
*/

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	protected static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);

	/* (non-Javadoc)  
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)  
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);

		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler.getValue("delegate.parameterHandler");

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");

		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (databaseType == null) {
			throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "
					+ configuration.getVariables().getProperty("dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
			case ORACLE:
				dialect = new OracleDialect();
				break;
            case MYSQL:
                dialect = new MySqlDialect();
                break;
            case SQLSERVER:
                dialect = new SqlServerDialect();
                break;

		}

		@SuppressWarnings("rawtypes")
        Map parameterMap = (Map) defaultParameterHandler.getParameterObject();

		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

		if (parameterMap.get("_sorts") != null) {
			if (parameterMap.get("_sorts") instanceof LinkedHashMap) {
				@SuppressWarnings("unchecked")
                LinkedHashMap<String, String> sorts = (LinkedHashMap<String, String>) parameterMap.get("_sorts");
				//使用自定义排序
				originalSql = dialect.getOrderString(originalSql, sorts);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("the sort parameter must be 'LinkedHashMap' type");
				}
			}
		}

		//使用自定义的物理分页方法。若不使用自定义分页（此处注释该方法），则使用mybatis的逻辑分页。物理分页效率高于逻辑分页
		originalSql = dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
		//如果使用自定义的物理分页法，请打开下边的两个注释。
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		
		metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);

		if (log.isDebugEnabled()) {
			BoundSql boundSql = statementHandler.getBoundSql();
			log.debug("生成分页SQL : " + boundSql.getSql());
		}
		return invocation.proceed();
	}

	/* (non-Javadoc)  
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)  
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/* (non-Javadoc)  
	 * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)  
	 */
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub    

	}

}
