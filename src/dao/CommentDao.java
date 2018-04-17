package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("text");
			sql.append(", user_id");
			sql.append(", message_id");
			sql.append(", created_date");
			sql.append(", updated_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // text
			sql.append(", ?"); // user_id
			sql.append(", ?"); // message_id
			sql.append(", CURRENT_TIMESTAMP"); // created_date
			sql.append(", CURRENT_TIMESTAMP"); // updated_date
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getMessageId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Comment> getComments(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comments ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Comment> toCommentList(ResultSet rs)
			throws SQLException {

		List<Comment> ret = new ArrayList<Comment>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int messageId = rs.getInt("message_id");
				String text = rs.getString("text");
				Timestamp createdDate = rs.getTimestamp("created_date");

				Comment message = new Comment();
				message.setId(id);
				message.setUserId(userId);
				message.setMessageId(messageId);
				message.setText(text);
				message.setCreatedDate(createdDate);


				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
