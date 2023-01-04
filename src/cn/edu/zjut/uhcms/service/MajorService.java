package cn.edu.zjut.uhcms.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zjut.uhcms.dao.MajorDAO;
import cn.edu.zjut.uhcms.model.Major;

public class MajorService {
	public boolean add(Major major) 
	{
		MajorDAO majordao=new MajorDAO();
		if(majordao.insertMajor(major))
		{
		return true;
		}
		else
		{
		return false;	
		}
	}
	public ArrayList<Major> queryMajorInfo(String name) 
	{
		MajorDAO majordao=new MajorDAO();
		return majordao.selectByFuzzyMname(name);		
	}
	public List<Major> queryMajorInfo(String mname, String tno) {
		MajorDAO majordao=new MajorDAO();
		return majordao.selectByFuzzyTnoMname(tno, mname);	
	}
}
