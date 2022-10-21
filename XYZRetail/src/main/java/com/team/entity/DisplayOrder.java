package com.team.entity;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayOrder {
	
	Collection<DisplayItem> prodList;
	double total;

}
