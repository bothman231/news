package com.botham.domain.newspaper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewspaperResult {
	private Newspaper newspaper;
	private String dateStr; 
}
