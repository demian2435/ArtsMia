package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.Arco;
import it.polito.tdp.artsmia.model.ArtObject;

public class ArtsmiaDAO {

	public void listObjects(Map<Integer, ArtObject> idMap) {

		String sql = "SELECT * from objects";
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				int id = res.getInt("object_id");

				if (!idMap.containsKey(id)) {

					ArtObject artObj = new ArtObject(id, res.getString("classification"), res.getString("continent"),
							res.getString("country"), res.getInt("curator_approved"), res.getString("dated"),
							res.getString("department"), res.getString("medium"), res.getString("nationality"),
							res.getString("object_name"), res.getInt("restricted"), res.getString("rights_type"),
							res.getString("role"), res.getString("room"), res.getString("style"),
							res.getString("title"));

					idMap.put(id, artObj);
				}
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Arco> listArchi(Map<Integer, ArtObject> idMap) {

		String sql = "SELECT o1.object_id AS O1, o2.object_id AS O2, COUNT(*) AS C FROM exhibition_objects AS o1, exhibition_objects AS o2 WHERE o1.exhibition_id = o2.exhibition_id "
				+ "AND o1.object_id > o2.object_id GROUP BY o1.object_id, o2.object_id";
		List<Arco> result = new ArrayList<Arco>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(new Arco(idMap.get(res.getInt("O1")), idMap.get(res.getInt("O2")), res.getInt("C")));
			}
			conn.close();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
	}

}
