package it.ariadne.dao;

import java.util.TreeMap;

import it.ariadne.bookingresources.Resource;

public interface DaoIF<T> {
	
	//I dao devono implementare almeno i metodi CRUD
	
	public TreeMap<String, T> listAll();
	public T getById(String id);
	public boolean update(T t);
	public boolean delete(T t);
	public boolean add(T t);

}
