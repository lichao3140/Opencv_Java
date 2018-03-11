package com.util;

import java.util.LinkedList;
import java.util.List;

public class Correspondens {
	private List<Integer> index = new LinkedList<>();

	public List<Integer> getIndex() {
		return index;
	}

	public void setIndex(List<Integer> index) {
		this.index = index;
	}
	
	public void add(int i) {
		this.index.add(i);
	}
}
