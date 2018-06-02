package com.github.langsdorf.blackbird.user.list;

import java.util.List;

public interface BirdList<T> extends List<T> {
	
	String getNextCursor();
	String getPreviousCursor();
	void setNextCursor(String next_cursor);
	void setPreviousCursor(String previous_cursor);
	

}
