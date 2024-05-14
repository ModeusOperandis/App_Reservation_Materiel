/**
 * 
 */
package com.usmb.grp1.info405api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usmb.grp1.info405api.model.Image;
import com.usmb.grp1.info405api.repository.ImageRepository;

/**
 * 
 * @author humban
 */
@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageRepository iRepository;
	
	@Override
	public List<Image> getImages() {
		return iRepository.findAll();
	}

	@Override
	public Image saveImage(Image image) {
		return iRepository.save(image);
	}

	@Override
	public Image getSingleImage(Long id) {
		Optional<Image> image = iRepository.findById(id);
		if (image.isPresent()) { // si image existe
			return image.get();
		}
		// si image existe pas alors on genere une exception
		throw new RuntimeException("Image is not found for the id:"+id);
	}

	@Override
	public void deleteImage(Long id) {
		iRepository.deleteById(id);
	}

	@Override
	public Image updateImage(Image image) {
		return iRepository.save(image);
	}

}
