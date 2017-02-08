package it.ariadne.controller;

import java.util.TreeMap;

import it.ariadne.dao.DaoIF;

public class Controller<T> {

	private DaoIF<T> dao;

	public Controller(DaoIF<T> dao) {
		this.dao = dao;
	}

	public boolean add(T t) {		
		return dao.add(t);
	}

	public boolean update(T t) {
		
		return dao.update(t);
	}

	public boolean delete(T t) {

		return dao.delete(t);

	}

	public TreeMap<String, T> listAll() {
		return dao.listAll();

	}

	public T getById(String id) {
		return dao.getById(id);
	}

}
