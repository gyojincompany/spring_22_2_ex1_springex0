package com.javagyojin.spring_22_1_ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.javagyojin.spring_22_1_ex.dto.TicketDto;

public class TicketDao {

	JdbcTemplate template;
	
//	PlatformTransactionManager transactionManager;
	TransactionTemplate transactionTemplate;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
		
//	public void setTransactionManager(PlatformTransactionManager transactionManager) {
//		this.transactionManager = transactionManager;
//	}
	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	public void buyTicket(final TicketDto dto) {
		
//		TransactionDefinition definition = new DefaultTransactionDefinition();
//		TransactionStatus status = transactionManager.getTransaction(definition);
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// TODO Auto-generated method stub
				template.update(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					String query="insert into card (consumerId, amount) values (?, ?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, dto.getConsumerId());
					pstmt.setString(2, dto.getAmount() );				
					
					return pstmt;
					}
				
				});
				
				template.update(new PreparedStatementCreator() {
					
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					// TODO Auto-generated method stub
					String query="insert into ticket (consumerId, countnum) values (?, ?)";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setString(1, dto.getConsumerId());
					pstmt.setString(2, dto.getAmount() );				
						
					return pstmt;
					}
					
				});
				
				
			}
		});
		
		
//		try {
//		
//		this.template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				// TODO Auto-generated method stub
//				String query="insert into card (consumerId, amount) values (?, ?)";
//				PreparedStatement pstmt = con.prepareStatement(query);
//				pstmt.setString(1, dto.getConsumerId());
//				pstmt.setString(2, dto.getAmount() );				
//				
//				return pstmt;
//				}
//			
//			});
//		
//		this.template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				// TODO Auto-generated method stub
//				String query="insert into ticket (consumerId, countnum) values (?, ?)";
//				PreparedStatement pstmt = con.prepareStatement(query);
//				pstmt.setString(1, dto.getConsumerId());
//				pstmt.setString(2, dto.getAmount() );				
//				
//				return pstmt;
//				}
//			
//			});
//		
////			transactionManager.commit(status);
//		
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
////			transactionManager.rollback(status);
//		}
		
		
		
	}


	
	
}
