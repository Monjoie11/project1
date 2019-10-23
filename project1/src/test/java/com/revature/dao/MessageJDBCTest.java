package com.revature.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.pojos.Message;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class MessageJDBCTest {
	
private MessageJDBC messJdbc = new MessageJDBC();
	
	Message message;
	
	@Mock
	private Connection conn;
	
	                                                 
	@Spy
	private PreparedStatement addStmt = ConnectionFactory.getConnection().prepareStatement("insert into message(origin_email, target_email, status, content, date_created) values(?, ?, ?, ?, ?)");

	@Spy
	private PreparedStatement addStmtExe = ConnectionFactory.getConnection().prepareStatement("insert into message(origin_email, target_email, statuses, content, date_created) values(?, ?, ?, ?, ?)");
	
	@Spy
	private PreparedStatement readStmt = ConnectionFactory.getConnection().prepareStatement("select * from car where vin = ?");
	
	@Spy
	private PreparedStatement updateStmt = ConnectionFactory.getConnection().prepareStatement("update message set status = ? where message_id = ?");
	
	@Spy
	private PreparedStatement updateStmtExe = ConnectionFactory.getConnection().prepareStatement("update message set status = ? where message_id = ?");
	
	@Spy
	private PreparedStatement getStmt = ConnectionFactory.getConnection().prepareStatement("select * from message where target_email = ?");
	
	@Spy
	private PreparedStatement getStmtExe = ConnectionFactory.getConnection().prepareStatement("select * from message where target_email = ?");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		message = new Message();
		message.setMessageId(99);
		message.setOriginEmail("depthead@email.com");
		message.setTargetEmail("supervisor@email.com");
		message.setStatus(Message.Status.SENT);
		message.setContent("I'm really impressed with Shalom Nguyen's character and natural ability");
		message.setDateCreated(LocalDate.parse("2022-04-05"));
		
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public final void testGetMessage() {
		fail("Not yet implemented");
	}

	@Test
	public final void testCreateMessage() {
		
		try {
			when(conn.prepareStatement("insert into message(origin_email, target_email, status, content, date_created) values(?, ?, ?, ?, ?)")).thenReturn(addStmt);
			messJdbc.setConn(conn);
			
			messJdbc.createMessage(message);
			Mockito.verify(addStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void testCreateMessageException() {
		
		try {
			when(conn.prepareStatement("insert into message(origin_email, target_email, statuses, content, date_created) values(?, ?, ?, ?, ?)")).thenReturn(addStmtExe);
			messJdbc.setConn(conn);
			
			messJdbc.createMessage(message);
			Mockito.verify(addStmtExe).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		


	@Test
	public final void testGetAllMessages() {
			
			try {
				when(conn.prepareStatement("select * from message where target_email = ?")).thenReturn(getStmt);
				messJdbc.setConn(conn);
				
				messJdbc.getAllMessages("supervisor@email.com");
				Mockito.verify(getStmt).executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	@Test
	public final void testGetAllMessagesExe() {
			
			try {
				when(conn.prepareStatement("select * from message where target_email = ?")).thenReturn(getStmtExe);
				messJdbc.setConn(conn);
				
				messJdbc.getAllMessages("supervisor@email.com");
				Mockito.verify(getStmtExe).executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	@Test
	public final void testUpdateMessage() {
		try {
			when(conn.prepareStatement("update message set status = ? where message_id = ?")).thenReturn(updateStmt);
			messJdbc.setConn(conn);
			
			messJdbc.updateMessage(message, Message.Status.READ.toString());
			Mockito.verify(updateStmt).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void testUpdateMessageExe() {
		//for this test to be effective change the messageid in the @before constructor to be 99, or any id not found
		try {
			when(conn.prepareStatement("update message set status = ? where message_id = ?")).thenReturn(updateStmtExe);
			messJdbc.setConn(conn);
			
			messJdbc.updateMessage(message, Message.Status.READ.toString());
			Mockito.verify(updateStmtExe).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void testDeleteMessage() {
		fail("Not yet implemented");
	}
	

	
	
	
	public MessageJDBCTest() throws SQLException {
		super();
	}

}
