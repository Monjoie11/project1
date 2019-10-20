package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.Reimbursement;
import com.revature.util.ConnectionFactory;

import static com.revature.util.LoggerUtil.*;

public class ReimbursementJDBC implements ReimbursementDao {

	List<Reimbursement> reimbursementRepository = new ArrayList<>();
	
	private static Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Reimbursement getReimbursement(int reimbursementId) {
		String sql = "select * from reimbursement"
				+ " where reimbursement_id = ?";
		
		PreparedStatement stmt;
		
		Reimbursement reimbursement = new Reimbursement();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(reimbursementId));
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				reimbursement.setReimbursementId(rs.getInt(1));
				reimbursement.setStartDate(rs.getDate(2).toLocalDate());
				reimbursement.setStartTime(rs.getTime(3).toLocalTime());
				reimbursement.setLocation(rs.getString(4));
				reimbursement.setDescription(rs.getString(5));
				reimbursement.setCost(rs.getDouble(6));
				reimbursement.setGradingFormat(rs.getString(7));
				reimbursement.setEventType(rs.getString(8));
				reimbursement.setJustification(rs.getString(9));
				reimbursement.setDateSubmitted(rs.getDate(10).toLocalDate());
				reimbursement.setEmail(rs.getString(11));
				reimbursement.setTimeMissed(rs.getInt("work_hours_missed"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reimbursement;
	}

	@Override
	public boolean createReimbursement(Reimbursement reimbursement) {
		
		
		String sql = "insert into reimbursement (reimbursement_id, date, time, location, description, cost, grading_format, event_type, "
				+ "work_related_justification, date_submitted, email, work_hours_missed) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reimbursement.getReimbursementId());
			stmt.setDate(2, Date.valueOf(reimbursement.getStartDate()));
			stmt.setTime(3, Time.valueOf(reimbursement.getStartTime()));
			stmt.setString(4, reimbursement.getLocation());
			stmt.setString(5, reimbursement.getDescription());
			stmt.setDouble(6, reimbursement.getCost());
			stmt.setString(7, reimbursement.getGradingFormat());
			stmt.setString(8, reimbursement.getEventType());
			stmt.setString(9, reimbursement.getJustification());
			stmt.setDate(10, Date.valueOf(reimbursement.getDateSubmitted()));
			stmt.setString(11, reimbursement.getEmail());
			stmt.setInt(12, reimbursement.getTimeMissed());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			error("SQL error in createReimburse");
			return false;
		}
		
		return true;
	
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub

	}

}
