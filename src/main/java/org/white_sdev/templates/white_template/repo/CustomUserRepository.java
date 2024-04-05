package org.white_sdev.templates.white_template.repo;

public interface CustomUserRepository {
    void createStoredProcedure();
    int callStoredProcedure(int num1, int num2);
}