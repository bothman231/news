package com.botham.aws;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.botham.domain.pubpage.PubPage;
import com.botham.domain.pubpage.PubPagePK;

@Repository
public interface PubPageRepository extends PagingAndSortingRepository<PubPage, PubPagePK> {
	
    public List<PubPage> findByContentsLike(String search);
    
    public PubPage getByPubPagePK(PubPagePK pubPagePK);
    
}
