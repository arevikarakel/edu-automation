Feature: This feature will cover image upload functionality teacher's, student's, parent's profiles from corresponding
  section of admin page, BB integration, and validations checking
  For having images that were used during testing, you should download them from src/main/resources/images
  directory and change file upload path in the uploadImage() method

  Background: Login as admin
    When Login as admin

    @TC6.986.2 @TC6.986.3 @TC6.986.4 @TC6.986.5 @Regression @Smoke
    Scenario: Check "Upload image" functionality
      When Select teachers section
      And Click on the first item in the list
      And Select Profile section in the dashboard
      And Upload wrongProfileImg.webp image from desktop to 'Image Upload' section
      And Check message for uploading images with wrong formats
      And Click on 'OK' button and accept wrong format message
      And Upload bigSizeImg.jpg image from desktop to 'Image Upload' section
      And Check message for uploading images with big sizes
      And Click on 'OK' button and accept big sizes message
      Then Upload profileImage.png image from desktop to 'Image Upload' section
      And Check image is uploaded
