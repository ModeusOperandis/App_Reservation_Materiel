package com.usmb.grp1.info405api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmb.grp1.info405api.model.Image;
import com.usmb.grp1.info405api.service.ImageService;

import jakarta.validation.Valid;

/**
 * 
 * @author humban
 */
@RestController
public class ImageController {
	@Autowired
	private ImageService iService;
	
	@GetMapping("/images")
	public List<Image> getImages() {
		return iService.getImages();
	}
	
	@PostMapping("/images")
	public Image saveImage(@Valid @RequestBody Image image) {
		return iService.saveImage(image);
	}
	
	@GetMapping("/images/{id}")
	public Image getImage(@PathVariable("id") Long id) {
		return iService.getSingleImage(id);
	}
	
	@DeleteMapping("/images")
	public void deleteImage(@RequestParam("id") Long id) {
		iService.deleteImage(id);
	}
	
	@PutMapping("/images/{id}")
	public Image updateImage(@PathVariable("id") Long id, @RequestBody Image image) {
		// on ajoute l'id a l'element pour pouvoir realiser l'update et non un ajout
		image.setId(id);
		return iService.updateImage(image);
	}
}
