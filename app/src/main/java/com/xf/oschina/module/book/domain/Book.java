package com.xf.oschina.module.book.domain;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
public class Book {

    @Convert(converter = RatingBeanConverter.class, columnType = String.class)
    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    @Convert(converter = ImagesBeanConverter.class, columnType = String.class)
    private ImagesBean images;
    private String alt;
    @Id
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String tag;
    @Convert(converter = SeriesBeanConverter.class, columnType = String.class)
    private SeriesBean series;
    private String price;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> author;
    //    @Convert(columnType = String.class, converter = TagsBeanConverter.class)
//    private List<TagsBean> tags;
    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> translator;


    @Generated(hash = 601531436)
    public Book(RatingBean rating, String subtitle, String pubdate, String origin_title, String image,
            String binding, String catalog, String pages, ImagesBean images, String alt, String id,
            String publisher, String isbn10, String isbn13, String title, String url, String alt_title,
            String author_intro, String summary, String tag, SeriesBean series, String price,
            List<String> author, List<String> translator) {
        this.rating = rating;
        this.subtitle = subtitle;
        this.pubdate = pubdate;
        this.origin_title = origin_title;
        this.image = image;
        this.binding = binding;
        this.catalog = catalog;
        this.pages = pages;
        this.images = images;
        this.alt = alt;
        this.id = id;
        this.publisher = publisher;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.title = title;
        this.url = url;
        this.alt_title = alt_title;
        this.author_intro = author_intro;
        this.summary = summary;
        this.tag = tag;
        this.series = series;
        this.price = price;
        this.author = author;
        this.translator = translator;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SeriesBean getSeries() {
        return series;
    }

    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

//    public List<TagsBean> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<TagsBean> tags) {
//        this.tags = tags;
//    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public static class StringConverter implements PropertyConverter<List<String>, String> {

        @Override
        public List<String> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            } else {
                List<String> list = Arrays.asList(databaseValue.split(","));
                return list;
            }
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            if (entityProperty == null) {
                return null;
            } else {
                StringBuilder sb = new StringBuilder();
                for (String link : entityProperty) {
                    sb.append(link);
                    sb.append(",");
                }
                return sb.toString();
            }
        }
    }
//
//    public static class TagsBeanConverter implements PropertyConverter<List<TagsBean>, String> {
//
//        @Override
//        public List<TagsBean> convertToEntityProperty(String databaseValue) {
//            if (databaseValue == null) {
//                return null;
//            } else {
//                Type type = new TypeToken<List<TagsBean>>() {
//                }.getType();
//                return new Gson().fromJson(databaseValue, type);
//            }
//        }
//
//        @Override
//        public String convertToDatabaseValue(List<TagsBean> entityProperty) {
//
//            return new Gson().toJson(entityProperty);
//        }
//    }


    public static class ImagesBeanConverter implements PropertyConverter<ImagesBean, String> {
        @Override
        public ImagesBean convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, ImagesBean.class);
        }

        @Override
        public String convertToDatabaseValue(ImagesBean entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class RatingBeanConverter implements PropertyConverter<RatingBean, String> {
        @Override
        public RatingBean convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, RatingBean.class);
        }

        @Override
        public String convertToDatabaseValue(RatingBean entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class SeriesBeanConverter implements PropertyConverter<SeriesBean, String> {
        @Override
        public SeriesBean convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, SeriesBean.class);
        }

        @Override
        public String convertToDatabaseValue(SeriesBean entityProperty) {

            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }


    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 112
         * average : 8.8
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/subject/s/public/s1003786.jpg
         * large : https://img3.doubanio.com/view/subject/l/public/s1003786.jpg
         * medium : https://img3.doubanio.com/view/subject/m/public/s1003786.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class SeriesBean {
        /**
         * id : 2
         * title : 文化生活译丛
         */

        private String id;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

//    public static class TagsBean {
//        /**
//         * count : 77
//         * name : 王佐良
//         * title : 王佐良
//         */
//
//        private int count;
//        private String name;
//        private String title;
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//    }
}
