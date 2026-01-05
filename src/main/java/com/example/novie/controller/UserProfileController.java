package com.example.novie.controller;

import com.example.novie.model.UserProfile;
import com.example.novie.repository.UserProfileRepository;
import com.example.novie.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/{profileId}/image")
    public ResponseEntity<?> updateProfileImage(
            @PathVariable Long profileId,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            UserProfile updatedProfile =
                    userProfileService.updateProfileImage(profileId, image);

            return ResponseEntity.ok("Profile image updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{profileId}/image")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable Long profileId) {

        UserProfile profile = userProfileService.getProfile(profileId);

        return ResponseEntity.ok()
                .header("Content-Type", profile.getImageType())
                .body(profile.getImageData());
    }
}
