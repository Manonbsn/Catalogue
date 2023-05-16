package com.glisse.catalogue.controller;

import com.glisse.catalogue.ArticleObject;
import com.glisse.catalogue.entity.Article;
import com.glisse.catalogue.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("")
    public List<Article> findAll() {
        return (List<Article>) articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Article>> findArticleById(@PathVariable(value = "id") long id) {
        Optional<Article> salle = articleRepository.findById(id);
        if(salle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(salle);
    }
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleObject articleObject) {
        Article newArticle = Article.builder()
                .nom(articleObject.getNom())
                .couleur(articleObject.getCouleur())
                .prix(articleObject.getPrix())
                .description(articleObject.getDescription())
                .build();

        Article savedArticle = articleRepository.save(newArticle);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id, @RequestBody ArticleObject articleObject) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Article existingArticle = optionalArticle.get();
        existingArticle.setNom(articleObject.getNom());
        existingArticle.setDescription(articleObject.getDescription());
        existingArticle.setPrix(articleObject.getPrix());
        existingArticle.setCouleur(articleObject.getCouleur());

        Article updatedArticle = articleRepository.save(existingArticle);

        return ResponseEntity.ok(updatedArticle);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        articleRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}