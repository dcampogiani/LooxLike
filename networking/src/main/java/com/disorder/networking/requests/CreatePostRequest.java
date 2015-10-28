package com.disorder.networking.requests;

import java.io.File;

public class CreatePostRequest {

    private final String description;
    private final String c10;
    private final File file;

    public CreatePostRequest(String description, String c10, File file) {
        this.description = description;
        this.c10 = c10;
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public String getC10() {
        return c10;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreatePostRequest that = (CreatePostRequest) o;

        if (!description.equals(that.description)) return false;
        if (!c10.equals(that.c10)) return false;
        return file.equals(that.file);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + c10.hashCode();
        result = 31 * result + file.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CreatePostRequest{" +
                "description='" + description + '\'' +
                ", c10='" + c10 + '\'' +
                ", file=" + file +
                '}';
    }
}
