package com.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.mysql.model.Film;
import com.mysql.service.FilmService;

@RestController
@RequestMapping("/hotfilms")

public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/get")
    public List<Film> fetchFilmList() {
        return filmService.fetchFilmList();
    }

    // Save operation
    @PostMapping("/post")
    public Film saveFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @GetMapping("/get/{id}")
    public Film fetchFilmListById(@PathVariable("id") Long id) {
        return filmService.getFilmById(id);
    }
    // Read operation

    // Update operation
    @PutMapping("/put/{id}")
// public Film updateFilm(@RequestBody Film film, @PathVariable("id") Long id) {
//     return film.updateFilm(film, id);
public Film updateEmployee(@PathVariable("id") Long id, @RequestBody Film fim) {
    return filmService.updateFilmById(id, fim);
}


    // Delete operation
    @DeleteMapping("/del/{id}")
    public String deleteFilmById(@PathVariable("id") Long id) {
        filmService.deleteFilmById(
                id);
        return "Deleted Successfully";
    }
    // @GetMapping("/checkDatabase")
    // public String checkDatabaseConnection() {
    // try (Connection connection = DataSource.getConnection()) {
    // return "Database connection established successfully.";
    // } catch (SQLException e) {
    // return "Failed to establish database connection: " + e.getMessage();
    // }
    // }

}