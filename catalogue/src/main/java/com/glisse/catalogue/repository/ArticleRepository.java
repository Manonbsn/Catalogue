package com.glisse.catalogue.repository;

import com.glisse.catalogue.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long > {
}
