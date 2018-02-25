package com.gvmarc.tvshows.data.entity.details;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TvShowDetailsEntity implements Serializable {

    @Nullable
    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("overview")
    private String overview;

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

//    @SerializedName("created_by")
//    private List<CreatedBy> createdBy;
//
//    @SerializedName("episode_run_time")
//    private List<Integer> episodeRunTime;
//
//    @SerializedName("first_air_date")
//    private String firstAirDate;
//
//    @SerializedName("genres")
//    private List<Genre> genres;
//
//    @SerializedName("homepage")
//    private String homepage;
//
//    @SerializedName("id")
//    private Integer id;
//
//    @SerializedName("in_production")
//    private Boolean inProduction;
//
//    @SerializedName("languages")
//    private List<String> languages;
//
//    @SerializedName("last_air_date")
//    private String lastAirDate;
//
//    @SerializedName("name")
//    private String name;
//
//    @SerializedName("networks")
//    private List<Network> networks;
//
//    @SerializedName("number_of_episodes")
//    private Integer numberOfEpisodes;
//
//    @SerializedName("number_of_seasons")
//    private Integer numberOfSeasons;
//
//    @SerializedName("origin_country")
//    private List<String> originCountry;
//
//    @SerializedName("original_language")
//    private String originalLanguage;
//
//    @SerializedName("original_name")
//    private String originalName;
//    @SerializedName("popularity")
//    private Double popularity;
//
//    @Nullable
//    @SerializedName("poster_path")
//    private String posterPath;
//
//    @SerializedName("production_companies")
//    private List<ProductionCompany> productionCompanies;
//
//    @SerializedName("seasons")
//    private List<Season> seasons;
//
//    @SerializedName("status")
//    private String status;
//
//    @SerializedName("type")
//    private String type;
//
//    @SerializedName("vote_average")
//    private Integer voteAverage;
//
//    @SerializedName("vote_count")
//    private Integer voteCount;
//    public void setBackdropPath(String backdropPath) {
//        this.backdropPath = backdropPath;
//    }
//
//    public List<CreatedBy> getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(List<CreatedBy> createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public List<Integer> getEpisodeRunTime() {
//        return episodeRunTime;
//    }
//
//    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
//        this.episodeRunTime = episodeRunTime;
//    }
//
//    public String getFirstAirDate() {
//        return firstAirDate;
//    }
//
//    public void setFirstAirDate(String firstAirDate) {
//        this.firstAirDate = firstAirDate;
//    }
//
//    public List<Genre> getGenres() {
//        return genres;
//    }
//
//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }
//
//    public String getHomepage() {
//        return homepage;
//    }
//
//    public void setHomepage(String homepage) {
//        this.homepage = homepage;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Boolean getInProduction() {
//        return inProduction;
//    }
//
//    public void setInProduction(Boolean inProduction) {
//        this.inProduction = inProduction;
//    }
//
//    public List<String> getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(List<String> languages) {
//        this.languages = languages;
//    }
//
//    public String getLastAirDate() {
//        return lastAirDate;
//    }
//
//    public void setLastAirDate(String lastAirDate) {
//        this.lastAirDate = lastAirDate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Network> getNetworks() {
//        return networks;
//    }
//
//    public void setNetworks(List<Network> networks) {
//        this.networks = networks;
//    }
//
//    public Integer getNumberOfEpisodes() {
//        return numberOfEpisodes;
//    }
//
//    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
//        this.numberOfEpisodes = numberOfEpisodes;
//    }
//
//    public Integer getNumberOfSeasons() {
//        return numberOfSeasons;
//    }
//
//    public void setNumberOfSeasons(Integer numberOfSeasons) {
//        this.numberOfSeasons = numberOfSeasons;
//    }
//
//    public List<String> getOriginCountry() {
//        return originCountry;
//    }
//
//    public void setOriginCountry(List<String> originCountry) {
//        this.originCountry = originCountry;
//    }
//
//    public String getOriginalLanguage() {
//        return originalLanguage;
//    }
//
//    public void setOriginalLanguage(String originalLanguage) {
//        this.originalLanguage = originalLanguage;
//    }
//
//    public String getOriginalName() {
//        return originalName;
//    }
//
//    public void setOriginalName(String originalName) {
//        this.originalName = originalName;
//    }
//
//
//    public void setOverview(String overview) {
//        this.overview = overview;
//    }
//
//    public Double getPopularity() {
//        return popularity;
//    }
//
//    public void setPopularity(Double popularity) {
//        this.popularity = popularity;
//    }
//
//    public String getPosterPath() {
//        return posterPath;
//    }
//
//    public void setPosterPath(String posterPath) {
//        this.posterPath = posterPath;
//    }
//
//    public List<ProductionCompany> getProductionCompanies() {
//        return productionCompanies;
//    }
//
//    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
//        this.productionCompanies = productionCompanies;
//    }
//
//    public List<Season> getSeasons() {
//        return seasons;
//    }
//
//    public void setSeasons(List<Season> seasons) {
//        this.seasons = seasons;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Integer getVoteAverage() {
//        return voteAverage;
//    }
//
//    public void setVoteAverage(Integer voteAverage) {
//        this.voteAverage = voteAverage;
//    }
//
//    public Integer getVoteCount() {
//        return voteCount;
//    }
//
//    public void setVoteCount(Integer voteCount) {
//        this.voteCount = voteCount;
//    }
}
