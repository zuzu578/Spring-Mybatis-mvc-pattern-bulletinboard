package com.javalec.java.DAO;

import java.util.ArrayList;

import com.javalec.java.DTO.DTO;

public interface DAO {
	public ArrayList<DTO> list();
	public DTO contentView(int bId);
	public int checkuser(String userid, String passcode);
	public void delete(int bId);
	public void write(String bTitle, String bName, String bContent);
	public void modify(int bId, String bTitle, String bName, String bContent);
	public void upHit(int bId);
}
