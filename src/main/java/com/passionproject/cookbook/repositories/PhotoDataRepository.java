package com.passionproject.cookbook.repositories;


import com.passionproject.cookbook.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDataRepository extends JpaRepository<Photo, Long> {
}
