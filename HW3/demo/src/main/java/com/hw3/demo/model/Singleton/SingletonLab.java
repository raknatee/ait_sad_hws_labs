package com.hw3.demo.model.Singleton;

public class SingletonLab {
	private static volatile SingletonLab instance;
	private SingletonLab() {}
	public static SingletonLab getInstance() {
		if(SingletonLab.instance==null) {
			synchronized (SingletonLab.class) {
				if(SingletonLab.instance==null) {
					SingletonLab.instance = new SingletonLab();
				}
			}
		}
		return SingletonLab.instance;
	}
}
