package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Time;

public class TimeDao {
	
	GenericDao gDao;
	
	public TimeDao (GenericDao gDao) {
		this.gDao = gDao;
	}
	
	public Time getTime(Integer id) throws SQLException, ClassNotFoundException{
		Connection c = gDao.getConnection();
		
		PreparedStatement ps = c.prepareStatement("SELECT * "
				+ "FROM Times "
				+ "WHERE CodigoTime = ? ");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Time time = null;
		if(rs.next()) {
			time = new Time();
			time.setCodigoTime(rs.getInt("CodigoTime"));
			time.setNome(rs.getString("NomeTime"));
			time.setCidade(rs.getString("Cidade"));
			time.setEstadio(rs.getString("Estadio"));
		}
		
		return time;
	}
	
}
