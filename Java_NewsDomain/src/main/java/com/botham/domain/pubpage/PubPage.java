package com.botham.domain.pubpage;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="pages")
public class PubPage {
	public PubPage(String contents, PubPagePK pubPagePK) {
		super();
		this.contents = contents;
		this.pubPagePK=pubPagePK;

	}

	@Column(name="contents")
	private String contents;

	
	
	 	
	@EmbeddedId
    private PubPagePK pubPagePK;
}
