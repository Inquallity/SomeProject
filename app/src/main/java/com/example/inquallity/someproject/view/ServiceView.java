package com.example.inquallity.someproject.view;

/**
 * @author Maksim Radko
 */
public interface ServiceView {

    void bindWithBinder();

    void bindWithMessenger();

    void updateViewVisibility();

    void showError(String errorText);
}
