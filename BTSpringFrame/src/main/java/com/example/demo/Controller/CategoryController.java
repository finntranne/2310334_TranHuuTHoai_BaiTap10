package com.example.demo.Controller;



import com.example.demo.Entity.Category;
import com.example.demo.Entity.Video;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.VideoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VideoService videoService;

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("message", "Danh sách categories đã được tải sau khi đăng nhập!");
        return "admin/categories/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories/add";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(
            @ModelAttribute("category") @Validated Category category,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("editCategory");
        }
        try {
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("message", "Category saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to save category: " + e.getMessage());
            return new ModelAndView("editCategory");
        }
        return new ModelAndView("redirect:/admin/categories");
    }

    @GetMapping("edit/{categoryId}")
    public String edit(@PathVariable("categoryId") int categoryId, Model model) {
        Category category = categoryService.findById(categoryId).orElse(null);
        if (category != null) {
            model.addAttribute("category", category);
            return "admin/categories/edit"; 
        }
        model.addAttribute("message", "Category not found!");
        return "redirect:/admin/categories";
    }

    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(@PathVariable("categoryId") int categoryId, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(categoryId);
            redirectAttributes.addFlashAttribute("message", "Danh mục đã được xóa thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa danh mục: " + e.getMessage());
        }
        return new ModelAndView("redirect:/admin/categories");
    }

    @GetMapping("search")
    public String search(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Category> list = null;
        if (StringUtils.hasText(name)) {
            list = categoryService.findByCategoryNameContaining(name);
        } else {
            list = categoryService.findAll();
        }
        model.addAttribute("categories", list);
        model.addAttribute("searchName", name);
        return "admin/categories/list";
    }

    @GetMapping("view/{categoryId}")
    public String view(@PathVariable("categoryId") int categoryId, Model model, RedirectAttributes redirectAttributes) {
    	
        List<Video> videos = videoService.findByCategoryId(categoryId);
        model.addAttribute("videos", videos);
        return "admin/categories/view";
    }
}