package com.github.langsdorf.blackbird.user.list;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BirdListImp<T> extends ArrayList<T> implements BirdList<T> {

	private static final long serialVersionUID = -604790975019671108L;

	private String nextCursor;
	private String previousCursor;
}
