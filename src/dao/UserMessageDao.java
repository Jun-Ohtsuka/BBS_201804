package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserMessage;
import exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessages(Connection connection, int num, String category, String startDate, String endDate) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("messages.id as id, ");
			sql.append("messages.title as title, ");
			sql.append("messages.text as text, ");
			sql.append("messages.user_id as user_id, ");
			sql.append("messages.category as category, ");
			sql.append("users.account as account, ");
			sql.append("users.name as name, ");
			sql.append("messages.created_date as created_date ");
			sql.append("FROM messages ");
			sql.append("INNER JOIN users ");
			sql.append("ON messages.user_id = users.id ");
			sql.append("WHERE (CASE WHEN category IS NULL THEN '' ELSE category END) LIKE ? ");
			sql.append("AND messages.created_date BETWEEN ? AND ? ");
			sql.append("ORDER BY created_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, category);
			ps.setString(2, startDate);
			ps.setString(3, endDate);

//			System.out.println(ps.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				String account = rs.getString("account");
				String name = rs.getString("name");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp createdDate = rs.getTimestamp("created_date");

				UserMessage message = new UserMessage();
				message.setAccount(account);
				message.setName(name);
				message.setId(id);
				message.setUserId(userId);
				message.setTitle(title);
				message.setText(text);
				message.setCategory(category);
				message.setCreated_date(createdDate);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
