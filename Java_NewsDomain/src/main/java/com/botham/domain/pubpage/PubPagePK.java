package com.botham.domain.pubpage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PubPagePK implements Serializable {

	private static final long serialVersionUID = -4715205076513185543L;
	
	@Column(name="pubid")
	private String pubId;
	
	@Column(name="datestr")
	private String dateStr;
	
	@Column(name="page")
	private String page;
}
