package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Movie;
import peaksoft.service.impl.MovieServiceImpl;

    @Controller
    @RequestMapping("/movie")
    public class MovieController {
        private final MovieServiceImpl movieService;
        @Autowired
        public MovieController(MovieServiceImpl movieService) {
            this.movieService = movieService;
        }
        @GetMapping("/save")
        public String save(Model model) {
            Movie movie=new Movie();
            model.addAttribute("movie", movie);
            return "MovieHtml/movie";
        }

        @PostMapping("/save_movie")
        public String saveUser(@ModelAttribute Movie movie) {
            movieService.save(movie);
            return "redirect:movie/movie";
        }

        @GetMapping("/find_all")
        public String findAll(Model model) {
            model.addAttribute("all_movies", movieService.findAll());
            return "MovieHtml/all_movies";
        }

        @GetMapping("/find_by_id/{id}")
        public String findById(@PathVariable int id, Model model) {
            model.addAttribute("find_by_id", movieService.findById(id));
            return "MovieHtml/find_by_id";
        }

        @GetMapping("/update/{id}")
        public String update(Model model, @PathVariable int id) {
            Movie movie = movieService.findById(id);
            model.addAttribute("movie", movie);
            return "MovieHtml/update_movie";
        }

        @PostMapping("/merge_update/{id}")
        public String mergeUpdate(@ModelAttribute Movie movie, @PathVariable int id) {
            movieService.update(id, movie);
            return "redirect:/movie/find_all";
        }


        @GetMapping("/delete_by_id/{id}")
        public String deleteById(@PathVariable int id) {
            movieService.deleteById(id);
            return "redirect:/movie/find_all";
        }

}
