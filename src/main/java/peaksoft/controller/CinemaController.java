package peaksoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Room;
import peaksoft.service.impl.CinemaServiceImpl;
import peaksoft.service.impl.RoomServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaServiceImpl cinemaService;
    private final RoomServiceImpl roomService;

    public CinemaController(CinemaServiceImpl cinemaService, RoomServiceImpl roomService) {
        this.cinemaService = cinemaService;
        this.roomService = roomService;
    }


    @GetMapping("/save")
    public String save(Model model) {
        Cinema cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return "CinemaHtml/cinema";
    }

    @PostMapping("/save_cinema")
    public String saveUser(@ModelAttribute Cinema cinema) {
        cinemaService.save(cinema);
        return "redirect:find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_cinemas", cinemaService.findAll());
        return "CinemaHtml/all_cinemas";
    }

    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("only_one_user", cinemaService.findById(id));
        return "CinemaHtml/find_by_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Cinema cinema = cinemaService.findById(id);
        model.addAttribute("cinema", cinema);
        return "CinemaHtml/update_cinema";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema, @PathVariable int id) {
        cinemaService.update(id, cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/delete_by_id/{id}")
    public String deleteById(@PathVariable int id) {
        cinemaService.deleteById(id);
        return "redirect:/cinema/find_all";
    }

}
