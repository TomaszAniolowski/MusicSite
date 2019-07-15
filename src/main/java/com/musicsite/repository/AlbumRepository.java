package com.musicsite.repository;

import com.musicsite.entity.Album;
import com.musicsite.entity.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> getAlbumsByPerformer(Performer performer);
    List<Album> getAlbumsByPerformerOrderByYearOfPublicationDesc(Performer performer);
    List<Album> getAlbumsByNameIgnoreCase(String name);
    Album getFirstAlbumByNameIgnoreCase(String name);
}
