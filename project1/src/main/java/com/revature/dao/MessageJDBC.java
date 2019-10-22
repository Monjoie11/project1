package com.revature.dao;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.trace;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Message;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Message.Status;
import com.revature.util.ConnectionFactory;

public class MessageJDBC implements MessageDao {
	
	List<Message> messageRepository = new ArrayList<>();
	
	private static Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Message getMessage(int messageId) {
		String sql = "select * from reimbursement"
				+ " where reimbursement_id = ?";
		
		PreparedStatement stmt;
		
		Message message = new Message();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(messageId));
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				message.setMessageId(rs.getInt(1));
				message.setOriginEmail(rs.getString(2));
				message.setTargetEmail(rs.getString(3));
				message.setStatus(Message.Status.valueOf(rs.getString(4)));
				message.setContent(rs.getString(5));
				message.setDateCreated(rs.getDate(6).toLocalDate());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
	@Override
	public boolean createMessage(Message message) {
		

		String sql = "insert into reimbursement (origin_email, target_email, status, content, date_created) values(?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, message.getOriginEmail());
			stmt.setString(2, message.getTargetEmail());
			stmt.setString(3, message.getStatus().toString());
			stmt.setString(4, message.getContent());
			stmt.setDate(5, Date.valueOf(message.getDateCreated()));
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			error("SQL error in createReimburse");
			return false;
		}
		
		return true;
	
	}

	@Override
	public List<Message> getAllMessages(String email) {

		
		String sql = "select * from message where target_email = ?";
		 
		PreparedStatement stmt;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();
				message.setMessageId(rs.getInt(1));
				message.setOriginEmail(rs.getString(2));
				message.setTargetEmail(rs.getString(3));
				message.setStatus(Message.Status.valueOf(rs.getString(4)));
				message.setContent(rs.getString(5));
				message.setDateCreated(rs.getDate(6).toLocalDate());
			//	trace("get cars by user while block");
				messageRepository.add(message);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messageRepository;
	}
	

	@Override
	public void updateMessage(Message message, String newStatus) {
		
String sql = "update message set status = ? where message_id = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, newStatus);
			stmt.setInt(2, message.getMessageId());
			stmt.executeUpdate();
			trace("executing message status");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void deleteMessage(Message message) {
		// TODO Auto-generated method stub

	}

}
