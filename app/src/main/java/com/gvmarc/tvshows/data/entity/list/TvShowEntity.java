
package com.gvmarc.tvshows.data.entity.list;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TvShowEntity implements Serializable {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("id")
    private Integer id;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("origin_country")
    private List<String> originCountry;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("name")
    private String name;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("number_of_seasons")
    private int numberOfSeasons;

    @SerializedName("number_of_episodes")
    private int numberOfEpisodes;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
