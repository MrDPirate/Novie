package com.example.novie.service;

import com.example.novie.model.UserProfile;
import com.example.novie.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfile updateProfileImage(Long profileId, MultipartFile image) throws IOException {

        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setImageName(image.getOriginalFilename());
        profile.setImageType(image.getContentType());
        profile.setImageData(image.getBytes());

        return userProfileRepository.save(profile);
    }
}
