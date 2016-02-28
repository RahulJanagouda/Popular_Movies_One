package com.rahuljanagouda.popularmoviesone.utils;

/**
 * Created by rahuljanagouda on 14/02/16.
 */
public class Network {
    public static final String TMDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String TMDB_IMAGE_HQ_BASE_URL = "http://image.tmdb.org/t/p/w780";
    private static final String TMDB_API_KEY = "32678d6d18172964c5930ff3e5d3b385";
    private static final String STRING_MOVIE = "movie";
    private static final String STRING_SORT_BY_KEY = "sort_by";
    private static final String STRING_SORT_BY_POPULARITY_VALUE = "popularity.desc";
    private static final String STRING_SORT_BY_HIGHEST_RATED_VALUE = "vote_average.desc";
    private static final String URL_TMDB_API_BASE = "http://api.themoviedb.org/3/";
    private static final String URL_TMDB_DISCOVER = URL_TMDB_API_BASE + "discover/";
    private static final String URL_TMDB_DISCOVER_MOVIES = URL_TMDB_DISCOVER + STRING_MOVIE;
    private static final String URL_TMDB_DISCOVER_MOVIES_POPULAR = URL_TMDB_DISCOVER_MOVIES + "?" + STRING_SORT_BY_KEY + "=" + STRING_SORT_BY_POPULARITY_VALUE;
    public static final String URL_TMDB_DISCOVER_MOVIES_POPULAR_API = URL_TMDB_DISCOVER_MOVIES_POPULAR + "?" +
            "&api_key=" + TMDB_API_KEY;
    private static final String URL_TMDB_DISCOVER_MOVIES_HIGHEST_RATED = URL_TMDB_DISCOVER_MOVIES + "?" + STRING_SORT_BY_KEY + "=" + STRING_SORT_BY_HIGHEST_RATED_VALUE;
    public static final String URL_TMDB_DISCOVER_MOVIES_HIGHEST_RATED_API = URL_TMDB_DISCOVER_MOVIES_HIGHEST_RATED + "?" +
            "&api_key=" + TMDB_API_KEY;


}
