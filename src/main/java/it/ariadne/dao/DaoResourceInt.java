package it.ariadne.dao;

import java.util.TreeMap;

public interface DaoResourceInt<T> {
	
	//I dao devono implementare almeno i metodi CRUD
	
	public TreeMap<String, T> listAll();
	public boolean update(T t);
	public boolean delete(T t);
	public boolean add(T t);

}
