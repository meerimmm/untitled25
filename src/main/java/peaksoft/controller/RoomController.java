package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Cinema;
import peaksoft.model.Room;
import peaksoft.service.impl.CinemaServiceImpl;
import peaksoft.service.impl.RoomServiceImpl;
import peaksoft.service.impl.SessionServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomServiceImpl roomService;
    private final CinemaServiceImpl cinemaService;
    private final SessionServiceImpl sessionService;

    @Autowired
    public RoomController(RoomServiceImpl roomService, CinemaServiceImpl cinemaService, SessionServiceImpl sessionService) {
        this.roomService = roomService;
        this.cinemaService = cinemaService;
        this.sessionService = sessionService;
    }

    @ModelAttribute("cinemaList")
    public List<Cinema> cinemaList() {
        return cinemaService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        Room room = new Room();
        List<Cinema> cinemas = cinemaService.findAll();
        model.addAttribute("cinema1", cinemas);
        model.addAttribute("room", room);
        return "RoomHtml/room";

    }

    @PostMapping("/save_room")
    public String saveUser(@ModelAttribute Room room) {
        roomService.save(room);
        return "redirect:find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_rooms", roomService.findAll());
        return "RoomHtml/all_rooms";
    }

    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") int id, Model model) {
        model.addAttribute("all_room_id", roomService.findAllId(id));
        return "RoomHtml/all_room_id";
    }


    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Room room = roomService.findById(id);
        model.addAttribute("room", room);
        return "RoomHtml/update_room";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room, @PathVariable int id) {
        roomService.update(id, room);
        return "redirect:/room/find_all";
    }


    @GetMapping("/delete_by_id/{id}")
    public String deleteById(@PathVariable int id) {
        roomService.deleteById(id);
        return "redirect:/room/find_all";
    }

}
