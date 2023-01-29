package o.yurchenko.homeexercise;

import java.text.SimpleDateFormat;

public interface COMMON {

    String BASE_URL = "https://api.github.com/";

    String REPOSITORY_KEY = "repository";

    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
}
