package com.university.examination.constant;
public interface Error {
    String ERROR_OPEN_IMAGE= "Error: can open image";
    String ERROR_FIND_IMAGE= "Error: No image";
    String ERROR_DELETE_IMAGE= "Error: Failed to delete file from storage";
    String ERROR_NOT_FOUND_IMAGE="Error: File not found in storage";
    String ERROR_EMPTY_FILE= "Error: File is empty";
    String ERROR_TYPE_FILE= "Error: Invalid file type";
    String ERROR_LIMIT_SIZE_FILE= "Error: File size exceeds the allowed limit";
    String ERROR_DIRECTORY_FILE= "Error: Failed to create directory.";
    String ERROR_SAVE_FILE= "Error: Failed to save file.";
    String ERROR_EXIT_EMAIL = "Error: Email is already taken";
    String ERROR_OLD_PASSWORD = "Error: Old password is not collect.";
    String ERROR_SET_AUTHENTICATION = "Error: Cannot set user authentication: {}";
    String ERROR_NOT_ROLE = "Error: You have not role";
    String ERROR_NOT_EXIT = "Error: Not exit";
    String ERROR_ALREADY_EXIT = "Error: Already exit.";

}
