package edu.upb.pumatiti.repository.local;

public class LocalRepository {
    private static final LocalRepository ourInstance = new LocalRepository();

    public static LocalRepository getInstance() {
        return ourInstance;
    }

    private LocalRepository() {
    }
}
