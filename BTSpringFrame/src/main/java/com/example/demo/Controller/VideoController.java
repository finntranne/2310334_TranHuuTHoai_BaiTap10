package com.example.demo.Controller;


import com.example.demo.Entity.Video;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.StringUtils;
import java.util.List;

@Controller
@RequestMapping("admin/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private CategoryService categoryService; 
    @GetMapping("add")
    public String addVideo(ModelMap model) {
        model.addAttribute("video", new Video());
        model.addAttribute("categories", categoryService.findAll()); 
        return "admin/videos/add";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdateVideo(@ModelAttribute("video") Video video, ModelMap model) {
        videoService.save(video);
        model.addAttribute("message", "Video saved successfully!");
        return new ModelAndView("redirect:/admin/videos", model);
    }

    @RequestMapping("") // Trang danh sách video
    public String listVideos(ModelMap model) {
        List<Video> videos = videoService.findAll();
        model.addAttribute("videos", videos);
        return "admin/videos/list";
    }

    @GetMapping("edit/{videoId}")
    public String editVideo(@PathVariable("videoId") int videoId, ModelMap model) {
        Video video = videoService.findById(videoId).orElse(null);
        if (video != null) {
            model.addAttribute("video", video);
            model.addAttribute("categories", categoryService.findAll()); 
            return "admin/videos/edit"; 
        }
        model.addAttribute("message", "Video not found!");
        return "redirect:/admin/videos";
    }

    @GetMapping("delete/{videoId}")
    public ModelAndView deleteVideo(@PathVariable("videoId") int videoId, ModelMap model) {
        videoService.deleteById(videoId);
        model.addAttribute("message", "Video is deleted!");
        return new ModelAndView("redirect:/admin/videos", model);
    }

    @RequestMapping("search")
    public String searchVideos(ModelMap model,
                               @RequestParam(name = "title", required = false) String title,
                               @RequestParam(name = "categoryName", required = false) String categoryName) {
        List<Video> videos = null;

        if (StringUtils.hasText(title) && StringUtils.hasText(categoryName)) {

            videos = videoService.findByCategory_CategoryNameContaining(categoryName); 

            if (StringUtils.hasText(title)) {
                videos.removeIf(v -> !v.getTitle().toLowerCase().contains(title.toLowerCase()));
            }
        } else if (StringUtils.hasText(title)) {

            videos = videoService.findByTitleContaining(title);
        } else if (StringUtils.hasText(categoryName)) {

            videos = videoService.findByCategory_CategoryNameContaining(categoryName);
        } else {

            videos = videoService.findAll();
        }

        model.addAttribute("videos", videos);
        model.addAttribute("searchTitle", title);
        model.addAttribute("searchCategoryName", categoryName);
        return "admin/videos/search"; 
    }
}