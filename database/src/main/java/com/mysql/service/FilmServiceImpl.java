package com.mysql.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.model.Film;
import com.mysql.repository.FilmRepository;
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @SuppressWarnings("null")






    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

  @SuppressWarnings("null")
@Override
    public Film getFilmById(Long id) {
        Optional<Film> film = filmRepository.findById(id);
        if (film.isPresent()) {
            return film.get();
        }
        return null;
    }







    @Override 
    public List<Film> fetchFilmList(){
    List<Film> fetchFilmList = filmRepository.findAll();
    return fetchFilmList;   
    }

    @SuppressWarnings("null")
@Override
    public Film updateFilmById(Long id, Film film) {
        Optional<Film> film1 = filmRepository.findById(id);

        if (film1.isPresent()) {
            Film originalfilm = film1.get();

            if (Objects.nonNull(film.getTitle()) && !"".equalsIgnoreCase(film.getTitle())) {
                originalfilm.setTitle(film.getTitle());
            }
        
//     @SuppressWarnings({ "null", "unlikely-arg-type" })
//     @Override
//     public Film
//     updateFilm(Film film,Long id)
//     {
//         Film debFilm
//             = filmRepository.findById(id).get();
        
//         if (Objects.nonNull(film.getTitle())
//                 && !"".equalsIgnoreCase(
//                         film.getTitle())) {
//             debFilm.setTitle(
//                     film.getTitle());
//         }

//         if (Objects.nonNull(film.getRating())
//                 && !film.getRating().equals(0.0)) {
//             debFilm.setRating(
//                     film.getRating());
//         }
//         if (Objects.nonNull(film.getYear())
//                 && !"".equalsIgnoreCase(
//                         film.getYear())) {
//             debFilm.setYear(
//                     film.getYear());
//         }
//         if (Objects.nonNull(film.getMonth())
//                 && !"".equalsIgnoreCase(
//                         film.getMonth())) {
//             debFilm.setMonth(
//                     film.getMonth());
//         }
//         if (Objects.nonNull(film.getCertificate())
//                 && !"".equalsIgnoreCase(
//                         film.getCertificate())) {
//             debFilm.setCertificate(
//                     film.getCertificate());
//         }
//         if (Objects.nonNull(film.getRuntime())
//                 && !"".equalsIgnoreCase(
//                         film.getRuntime())) {
//             debFilm.setRuntime(
//                     film.getRuntime());
//         }
//         if (Objects.nonNull(film.getDirector())
//                 && !"".equalsIgnoreCase(
//                         film.getDirector())) {
//             debFilm.setDirector(
//                     film.getDirector());
//         }
//         if (Objects.nonNull(film.getStars())
//                 && !"".equalsIgnoreCase(
//                         film.getStars())) {
//             debFilm.setStars(
//                     film.getStars());
//         }
//         if (Objects.nonNull(film.getGenre())
//                 && !"".equalsIgnoreCase(
//                         film.getGenre())) {
//             debFilm.setGenre(
//                     film.getGenre());
//         }
//         if (Objects.nonNull(film.getFilmingLocation())
//                 && !"".equalsIgnoreCase(
//                         film.getFilmingLocation())) {
//             debFilm.setFilmingLocation(
//                     film.getFilmingLocation());
//         }
//         if (Objects.nonNull(film.getBudget()) && film.getBudget() != 0) {
//             debFilm.setBudget(film.getBudget());
//         }
//         if (Objects.nonNull(film.getIncome()) && film.getIncome() != 0) {
//             debFilm.setIncome(film.getIncome());
//         }
//         // if (Objects.nonNull(film.getCountryOfOrigin())
//         // && !"".equalsIgnoreCase(
//         //         film.getCountryOfOrigin())) {
//         //     debFilm.setCountryOfOrigin(
//         //     film.getCountryOfOrigin());
//         // }
//         return filmRepository.save(debFilm);
//     }
        return filmRepository.save(originalfilm);
}
return null;
}









    @SuppressWarnings("null")
    @Override
    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }
}