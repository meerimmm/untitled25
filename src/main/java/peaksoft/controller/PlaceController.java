package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Place;
import peaksoft.service.impl.PlaceServiceImpl;

    @Controller
    @RequestMapping("/place")
    public class PlaceController {
        private final PlaceServiceImpl placeService;

        @Autowired
        public PlaceController(PlaceServiceImpl placeService) {
            this.placeService = placeService;
        }

        @GetMapping("/save")
        public String save(Model model) {
            Place place = new Place();
            model.addAttribute("place", place);
            return "PlaceHtml/place";
        }

        @PostMapping("/save_place")
        public String saveUser(@ModelAttribute Place place) {
            placeService.save(place);
            return "redirect:place/place";
        }

        @GetMapping("/find_all")
        public String findAll(Model model) {
            model.addAttribute("all_places", placeService.findAll());
            return "PlaceHtml/all_places";
        }

        @GetMapping("/find_by_id/{id}")
        public String findById(@PathVariable int id, Model model) {
            model.addAttribute("find_by_id", placeService.findById(id));
            return "PlaceHtml/find_by_id";
        }

        @GetMapping("/update/{id}")
        public String update(Model model, @PathVariable int id) {
            Place place = placeService.findById(id);
            model.addAttribute("place", place);
            return "PlaceHtml/update_place";
        }

        @PostMapping("/merge_update/{id}")
        public String mergeUpdate(@ModelAttribute Place place, @PathVariable int id) {
            placeService.update(id, place);
            return "redirect:/place/find_all";
        }


        @GetMapping("/delete_by_id/{id}")
        public String deleteById(@PathVariable int id) {
            placeService.deleteById(id);
            return "redirect:/place/find_all";
        }
}
