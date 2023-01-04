package cn.edu.zjut.uhcms.service;

import java.util.ArrayList;

import cn.edu.zjut.uhcms.dao.CollegeDAO;
import cn.edu.zjut.uhcms.model.College;

public class CollegeService {
	public boolean add(College college) 
	{
		CollegeDAO collegedao=new CollegeDAO();
		if(collegedao.insertCollege(college))
		{
		return true;
		}
		else
		{
		return false;	
		}
	}
	public ArrayList<College> queryCollegeInfo(String name) 
	{
		CollegeDAO collegedao=new CollegeDAO();
		return collegedao.SelectByFuzzyName(name);		
	}
}