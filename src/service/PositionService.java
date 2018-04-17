package service;


import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Position;
import dao.PositionDao;

public class PositionService {

	public List<Position> getPosition() {

		Connection connection = null;
		try {
			connection = getConnection();

			PositionDao branchDao = new PositionDao();
			List<Position> branch = branchDao.getPosition(connection);

			commit(connection);

			return branch;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}