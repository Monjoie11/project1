package com.revature.dao;

import static com.revature.util.LoggerUtil.error;
import static com.revature.util.LoggerUtil.trace;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Reimbursement;
import com.revature.util.ConnectionFactory;

public class ReimbursementJDBC implements ReimbursementDao {

	List<Reimbursement> reimbursementRepository = new ArrayList<>();

	private static Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Reimbursement getReimbursement(int reimbursementId) {
		String sql = "select * from reimbursement" + " where reimbursement_id = ?";

		PreparedStatement stmt;

		Reimbursement reimbursement = new Reimbursement();

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, String.valueOf(reimbursementId));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
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
				reimbursement.setStatus(Reimbursement.Status.valueOf(rs.getString("status")));
				reimbursement.setTotalAmount(rs.getDouble("total_amount"));
			}

		} catch (SQLException e) {
			error("reimbursementJDBC SQL getReimbursement");
			e.printStackTrace();
		}

		return reimbursement;
	}

	@Override
	public boolean createReimbursement(Reimbursement reimbursement) {

		String sql = "insert into reimbursement (date, time, location, description, cost, grading_format, event_type, "
				+ "work_related_justification, date_submitted, email, work_hours_missed, status, total_amount) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDate(1, Date.valueOf(reimbursement.getStartDate()));
			stmt.setTime(2, Time.valueOf(reimbursement.getStartTime()));
			stmt.setString(3, reimbursement.getLocation());
			stmt.setString(4, reimbursement.getDescription());
			stmt.setDouble(5, reimbursement.getCost());
			stmt.setString(6, reimbursement.getGradingFormat());
			stmt.setString(7, reimbursement.getEventType());
			stmt.setString(8, reimbursement.getJustification());
			stmt.setDate(9, Date.valueOf(reimbursement.getDateSubmitted()));
			stmt.setString(10, reimbursement.getEmail());
			stmt.setInt(11, reimbursement.getTimeMissed());
			stmt.setString(12, reimbursement.getStatus().toString());
			stmt.setDouble(13, reimbursement.getTotalAmount());
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

		String sql = "select * from reimbursment";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
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
				reimbursement.setStatus(Reimbursement.Status.valueOf(rs.getString("status")));
				reimbursement.setTotalAmount(rs.getDouble("total_amount"));
				// trace("get cars by user while block");
				reimbursementRepository.add(reimbursement);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbursementRepository;

	}

	@Override
	public void updateReimbursementStatus(int reimbursementId, Reimbursement.Status newStatus) {
		
	String sql = "update reimbursement set status = ? where reimbursement_id = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, newStatus.toString());
			stmt.setInt(2, reimbursementId);
			stmt.executeUpdate();
			trace("executing reimbursement status ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	



	@Override
	public void deleteReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reimbursement> getEmployeeReimbursement(String email) {

		String sql = "select * from reimbursment where email = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Reimbursement reimbursement = new Reimbursement();
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
				reimbursement.setStatus(Reimbursement.Status.valueOf(rs.getString("status")));
				reimbursement.setTotalAmount(rs.getDouble("total_amount"));
				// trace("get cars by user while block");
				reimbursementRepository.add(reimbursement);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reimbursementRepository;

	}

}
