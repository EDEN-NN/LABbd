package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jogo;

public class JogoDao {
	
	GenericDao gDao;
	TimeDao tDao;
	
	public JogoDao() {
		this.gDao = new GenericDao();
		this.tDao = new TimeDao(this.gDao);
	}

	public List<Jogo> gerarJogos() throws SQLException, ClassNotFoundException{
		List<Jogo> jogos = new ArrayList<>();
		Connection c = gDao.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM Jogos WHERE DiaDoJogo IS NOT NULL");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setTimeA(tDao.getTime(rs.getInt("CodigoTimeA")));
			jogo.setTimeB(tDao.getTime(rs.getInt("CodigoTimeB")));
			jogo.setGolsTimeA(rs.getInt("GolsTimeA"));
			jogo.setGolsTimeB(rs.getInt("GolsTimeB"));
			jogo.setDiaDoJogo(rs.getString("DiaDoJogo"));
			jogos.add(jogo);
		}
		return jogos;
	}
	
	public void randomizarJogos() throws SQLException, ClassNotFoundException{
		Connection c = gDao.getConnection();
		CallableStatement cs = c.prepareCall("CALL pr_g ");
		cs.execute();
		cs.close();
		c.close();
	}
	
	public List<Jogo> pesquisarJogo(String date) throws SQLException, ClassNotFoundException{
		List<Jogo> jogos = new ArrayList<>();
		Connection c = gDao.getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * "
				+ "FROM Jogos "
				+ "WHERE DiaDoJogo = ? ");
		ps.setString(1, date);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setCodigoJogo(rs.getInt("CodigoJogo"));
			jogo.setTimeA(tDao.getTime(rs.getInt("CodigoTimeA")));
			jogo.setTimeB(tDao.getTime(rs.getInt("CodigoTimeB")));
			jogo.setGolsTimeA(rs.getInt("GolsTimeA"));
			jogo.setGolsTimeB(rs.getInt("GolsTimeB"));
			jogo.setDiaDoJogo(rs.getString("DiaDoJogo"));
			jogos.add(jogo);
		}
		return jogos;
	}
	
}
