package dao;

import java.util.List;

import model.Board101Bean;

public interface Board101DAO {

	List<Board101Bean> getList();

	Board101Bean getCont(int no);
	
}
