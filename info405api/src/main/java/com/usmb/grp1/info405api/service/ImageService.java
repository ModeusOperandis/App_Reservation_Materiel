/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;

import com.usmb.grp1.info405api.model.Image;

/**
 * 
 * @author humban
 */
public interface ImageService {
	List<Image> getImages();
	
	Image saveImage(Image image);
	
	Image getSingleImage(Long id);
	
	void deleteImage(Long id);
	
	Image updateImage(Image image);
}
