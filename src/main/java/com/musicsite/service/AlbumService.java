package com.musicsite.service;

import com.musicsite.entity.Album;
import com.musicsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumService {

    private PerformerRepository performerRepository;
    private AlbumRepository albumRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public AlbumService(PerformerRepository performerRepository,
                        AlbumRepository albumRepository,
                        TrackRepository trackRepository,
                        UserRepository userRepository,
                        RatingRepository ratingRepository) {

        this.performerRepository = performerRepository;
        this.albumRepository = albumRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    public List<Album> getAlbumPropositions() {
        return albumRepository.getAlbumsByPropositionFalseOrderByAverageDesc();
    }

}
